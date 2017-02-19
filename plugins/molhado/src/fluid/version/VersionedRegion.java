/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedRegion.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

import fluid.FluidError;
import fluid.FluidRuntimeException;
import fluid.NotImplemented;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentKind;
import fluid.ir.IRPersistentObserver;
import fluid.ir.IRRegion;
import fluid.util.EmptyEnumeration;
import fluid.util.FileLocator;
import fluid.util.UniqueID;

/** A versioned region is essentially a region that grows
 * with the version space.  In every era, more nodes may be added.
 * (Currently, we never lose nodes.)  Since the storage system would
 * not be able to handle regions growning and shrinking and also since
 * we do not want nodes from alternate eras to be confused, a version
 * region does not actually hold any nodes, except temporarily.  All
 * the nodes are distributed between regions, one for each era
 * for this versioned region.  Each of these regions is of private type
 * VersionedRegionDelta.
 * <p> A new versioned region must be created in a new version (in a new era).
 * and all the nodes in a VR must similarly be created in new versions (eras)
 * so we know where to put them.  If necessary the version is bumped
 * before creating a VR or a node in a VR.
 * A special kind of versioned region,
 * which starts in the initial version/era, is called a <em>shared versioned
 * region</em>.  These can only be created through the persistence
 * mechanism because the initial version is never new. </p>
 * <p> The versioned region only holds nodes, it does not hold attribute
 * values.  The values are in chunks. </p>
 * @see IRPersistent
 * @see IRRegion
 * @see VersionedChunk
 */
public class VersionedRegion extends IRRegion
  implements IRPersistentObserver 
{
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR");

  private static final int magic = 0x56520a00; // "VR\n\0"

  private Version initialVersion;
  private Era initialEra;

  /** Create a VersionedRegion:
   * a region of nodes that increases in each era.
   * If the current version is not new, the version is bumped,
   * because a new VR must be created in a new version.
   * <p> A versioned region is
   * implemented as a set of VRD: VersionedRegionDeltas.
   * Each VRD must be requested before chunks using
   * the nodes in the VRD are saved. </p>
   */
  public VersionedRegion() {
    this(magic);
  }

  protected VersionedRegion(int magic) {
    super(magic);
    complete(0);
    Version v = Version.getVersionLocal();
    if (v.getEra() != null && !v.getEra().isNew()) {
      Version.bumpVersion();
      v = Version.getVersionLocal();
    }
    initialVersion = v;
  }

  protected VersionedRegion(UniqueID id) {
    this(magic, id);
  }

  protected VersionedRegion(int magic, UniqueID id) {
    super(magic, id);
  }

  public static VersionedRegion loadVersionedRegion(
    UniqueID id,
    FileLocator floc)
    throws IOException {
    VersionedRegion vr = (VersionedRegion) find(id);
    if (vr == null) {
      vr = new VersionedRegion(id);
    }
    vr.load(floc);
    return vr;
  }

  /** Return the first era in which this region was defined. */
  public Era getInitialEra() {
    if (initialEra == null)
      initialEra = initialVersion.getEra();
    return initialEra;
  }

  /* deltas */

  /** Hashtable from versions to vectors of nodes. */
  private final Hashtable versionNodes = new Hashtable();
  /** Hashtable from nodes to versions. */
  private final Hashtable nodeVersion = new Hashtable();
  /** Hashtable from eras to ir regions. */
  private final Hashtable regionDeltas = new Hashtable();
  /** Hashtable from ir nodes to VR (while holding) */
  private static final Hashtable tempRegion = new Hashtable();

  /** Put this node into the correct VRD.
   * We first make sure the current version is new, by bumping
   * if necessary.  Then if the era has been assigned, then place
   * in a VRD for that era, otherwise hold it until the era is known.
   * @return true if node was added, or held.
   */
  public boolean saveNode(IRNode node) {
    if (!hasOwner(node)) { // NB: we check if the node may be in a VR
      Version v = Version.getVersionLocal();
      Era e = v.getEra();
      if (e != null && !e.isNew()) {
        Version.bumpVersion();
        v = Version.getVersionLocal();
        e = v.getEra();
      }
      v.addAssociated(this);
      if (e != null) {
        VersionedRegionDelta vrd = getDelta(e);
        vrd.saveNode(node);
      } else {
        Vector vec = (Vector) versionNodes.get(v);
        if (vec == null) {
          vec = new Vector();
          versionNodes.put(v, vec);
        }
        vec.addElement(node);
        nodeVersion.put(node, v);
        tempRegion.put(node, this);
	v.addPersistentObserver(this);
      }
      return true;
    }
    return false;
  }

  /** Return true if the node has a region or
   * has been assigned to some VersionedRegion.
   */
  public static boolean hasOwner(IRNode node) {
    return IRRegion.hasOwner(node) || tempRegion.get(node) != null;
  }

  public static IRRegion getOwner(IRNode node) {
    VersionedRegion vr = (VersionedRegion) tempRegion.get(node);
    if (vr != null)
      return vr;
    return IRRegion.getOwner(node);
  }

  /** Return the versioned region this node is created for.
   * @return null if not assigned to a VersionedRegion
   */
  public static VersionedRegion getVersionedRegion(IRNode node) {
    IRRegion r = getOwner(node);
    if (r instanceof VersionedRegion) {
      return (VersionedRegion)r;
    } else if (r instanceof VersionedRegionDelta) {
      return ((VersionedRegionDelta)r).getBase();
    } else {
      return null;
    }
  }

  /** Return the version when this node was added,
   * or if already assigned to an era, return some version
   * in the era.
   */
  public Version getVersion(IRNode n) {
    Version v = (Version) nodeVersion.get(n);
    if (v == null) {
      VersionedRegionDelta vrd = (VersionedRegionDelta) IRRegion.getOwner(n);
      return vrd.getEra().getVersion(1);
    }
    LOG.debug("Version is " + v);
    if (v.getEra() != null)
      getDelta(v.getEra());
    return v;
  }

  /** Check to see if all nodes are assigned or can be assigned.
   * If nodes can be assigned to VRDs, then assign them.
   * Return true if all nodes assigned.
   */
  public boolean finishNodes() {
    Set eraset = new HashSet();
    boolean interrupted = false;
    try {
      for (Iterator keys = versionNodes.keySet().iterator(); keys.hasNext();) {
        Version v = (Version) keys.next();
        Era e = v.getEra();
        if (e != null) {
          eraset.add(e);
        }
      }
    } catch (ConcurrentModificationException ex) {
      interrupted = true;
    }

    // we have to wait until after we have finished the iterator
    // before creating any deltas, because creating deltas modifies
    // the hash table
    for (Iterator eras = eraset.iterator(); eras.hasNext();) {
      getDelta((Era) eras.next());
    }

    // if we were interrupted, then try again
    if (interrupted)
      return finishNodes();

    // otherwise done if there are no more nodes waiting to be assigned
    return versionNodes.isEmpty();
  }

  /** Add to a versioned region delta the nodes
   * created in versions of the era.
   */
  protected synchronized void fillDelta(VersionedRegionDelta vrd, Era era) {
    Enumeration versions = era.elements();
    while (versions.hasMoreElements()) {
      Version v = (Version) versions.nextElement();
      fillDelta(vrd,v);
    }
  }

  /** Add to a versioned region delta the nodes
   * created for this version.
   * <p>
   * <B>NB:</B> the version <em>must</em> be in the era.
   */
  protected synchronized void fillDelta(VersionedRegionDelta vrd, Version v) {
    if (v.getEra() != vrd.getEra()) {
      LOG.error("fillDelta(Version) called illegally: " + vrd + " with " + v);
      return;
    }
    LOG.debug("Filling delta " + vrd + " for " + v);
    Vector vec = (Vector) versionNodes.get(v);
    if (vec != null) {
      Enumeration nodes = vec.elements();
      while (nodes.hasMoreElements()) {
	IRNode node = (IRNode) nodes.nextElement();
	vrd.saveNode(node);
	LOG.debug("Saving " + node);
	tempRegion.remove(node);
	nodeVersion.remove(node);
      }
      versionNodes.remove(v);
    }
  }

  /**
   * Notified that the version has been assigned an era.
   */
  public void updatePersistent(IRPersistent p, Object o) {
    if (p instanceof Era && o instanceof Version) {
      fillDelta(getDelta((Era)p),(Version)o);
    }
  }

  /** Create or find the specified delta.
   * If the era is not new and the delta does not already exist,
   * it creates an undefined delta placeholder.
   * @param era era for delta.
   */
  public VersionedRegionDelta getDelta(Era era) {
    LOG.debug("In " + this +".getDelta(" + era + ")");
    VersionedRegionDelta vrd = (VersionedRegionDelta) regionDeltas.get(era);
    if (vrd == null) {
      if (era.isNew()) {
        vrd = new VersionedRegionDelta(this, era);
        LOG.debug("Adding " + vrd + " to " + this);
        fillDelta(vrd, era);
      } else {
        if (era.isAssociated(this) < 0) {
          vrd = new VersionedRegionDelta(this, era, 0);
          LOG.debug("Era " + era + " has no record for " + vrd);
        } else {
          vrd = new VersionedRegionDelta(this, era, false);
        }
      }
      regionDeltas.put(era, vrd);
    }
    if (era.isNew() && era.isComplete())
      vrd.complete();
    return vrd;
  }

  /** Create or find the specified delta.
   * @param era era for delta.
   * @param numNodes the number of nodes in this delta.
   * @throws FluidRuntimeException if number of nodes wrong.
   */
  protected VersionedRegionDelta getDelta(Era era, int numNodes) {
    VersionedRegionDelta vrd = (VersionedRegionDelta) regionDeltas.get(era);
    if (vrd == null) {
      vrd = new VersionedRegionDelta(this, era, numNodes);
      regionDeltas.put(era, vrd);
    }
    vrd.complete(numNodes);
    if (vrd.getNumNodes() != numNodes)
      throw new FluidRuntimeException("delta has different number of nodes");
    return vrd;
  }

  public void exportTo(IRPersistent other) {
    Era era = Version.getVersion().getEra();
    exportTo(other, era);
  }

  public void exportTo(IRPersistent p, Era era) {
    if (era != null) {
      if (!era.equals(initialEra)) {
        exportTo(p, era.getParentEra());
      }
      p.importRegion(getDelta(era));
    }
  }

  /** Return an enumeration of all nodes in the region
   * from the initial time to the era of the version given.
   * If the version is not yet in an era, we use the nodes in it
   * and parent versions back until one *is* in an era.
   */
  public Enumeration allNodes(final Version v) {
    if (v.getEra() != null)
      return allNodes(v.getEra()); // shortcut
    return new Enumeration() {
      private Enumeration rest = null;
      private Enumeration here = null;
      private Version version = v;

      {
        initialize();
      }

      public boolean hasMoreElements() {
        for (;;) {
          if (rest != null)
            return rest.hasMoreElements();
          if (here.hasMoreElements())
            return true;
          moveVersion();
        }
      }
      public Object nextElement() throws NoSuchElementException {
        for (;;) {
          if (rest != null)
            return rest.nextElement();
          if (here.hasMoreElements())
            return here.nextElement();
          moveVersion();
        }
      }

      private void moveVersion() {
        version = version.parent();
        initialize();
      }
      private void initialize() {
        if (version == null) {
          rest = EmptyEnumeration.prototype;
        } else if (version.getEra() == null) {
          Vector vc = (Vector) versionNodes.get(version);
          if (vc == null)
            moveVersion();
          else
            here = vc.elements();
        } else {
          rest = allNodes(version.getEra());
        }
      }
    };
  }

  /** Return an enumeration of all nodes in the region from
   * initial time to the era given.  Order not guaranteed.
   */
  public Enumeration allNodes(final Era e) {
    return new Enumeration() {
      private Era era = e;
      private int i = 0;
      private VersionedRegionDelta vrd = getDelta(e);
      private int n = vrd.getNumNodes();
      public boolean hasMoreElements() {
        moveEra();
        return i < n;
      }
      private void moveEra() {
        while (i >= n && era != null) {
          if (era.equals(getInitialEra()) || era.getRoot()==null) {
            era = null;
          } else {
            era = era.getRoot().getEra();
            if (era == null)
              throw new NullPointerException("era is null");
            vrd = getDelta(era);
            i = 0;
            n = vrd.getNumNodes();
          }
        }
      }
      public Object nextElement() throws NoSuchElementException {
        moveEra();
        if (i < n) {
          try {
            return vrd.getNode(++i);
          } catch (IOException ex) {
            // won't happen:
            throw new FluidError("VRD getNode died!");
          }
        } else {
          throw new NoSuchElementException("no more nodes in this era");
        }
      }
    };
  }

  // shared versioned regions

  Hashtable copies = null;

  /** Return a region that copies this versioned region at a
   * particular version.  The region is not extensible.
   */
  public synchronized IRRegion getCopy(Version v) {
    IRRegion reg = null;
    if (copies == null)
      copies = new Hashtable();
    else
      reg = (IRRegion) copies.get(v);
    if (reg == null) {
      //reg = new VersionedRegionCopy(this,v);
      //copies.put(v,reg);
      throw new NotImplemented("VersionedRegion#getCopy(Version)");
    }
    return reg;
  }

  /* persistent kind */

  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException {
      VersionedRegion vr = (VersionedRegion) p;
      vr.getID().write(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException {
      UniqueID id = UniqueID.read(in);
      VersionedRegion vr = (VersionedRegion) find(id);
      if (vr == null) {
        vr = new VersionedRegion(id);
      }
      return vr;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind, 0x56); // 'V'
    IRPersistent.registerPersistentKind(VersionedRegionDelta.kind, 0x16);
    // '^V'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  protected String getFileName() {
    return getID().toString() + ".vr";
  }

  protected void write(IROutput out) throws IOException {
    if (getInitialEra() == null) {
      throw new IOException(
        "VersionedRegion "
          + this
          + " created in version "
          + initialVersion
          + " which has not yet been assigned an era.");
    }
    getInitialEra().writeReference(out);
  }

  protected void read(IRInput in) throws IOException {
    initialEra = (Era) IRPersistent.readReference(in);
    //System.out.println("Read initial era = " + initialEra);
    if (in.debug())
      LOG.debug("Read initial era = " + initialEra);
  }

  public void describe(PrintStream out) {
    super.describe(out);
    out.println(
      "  initial era = "
        + (initialEra == null ? "<null>" : initialEra.toString()));
    for (Enumeration eras = regionDeltas.keys(); eras.hasMoreElements();) {
      Era era = (Era) eras.nextElement();
      VersionedRegionDelta vrd = (VersionedRegionDelta) regionDeltas.get(era);
      out.println("  " + era + " -> " + vrd.getNumNodes());
    }
    for (Enumeration vs = versionNodes.keys(); vs.hasMoreElements();) {
      Version v = (Version)vs.nextElement();
      out.println("  " + v + " => " + ((Vector)versionNodes.get(v)).size());
    }
  }

  public static void ensureLoaded() {
    VersionedRegionDelta.ensureLoaded();
  }
}
