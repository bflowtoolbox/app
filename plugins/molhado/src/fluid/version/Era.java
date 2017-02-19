/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/Era.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import fluid.FluidError;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentKind;
import fluid.ir.IRRegion;
import fluid.tree.DepthFirstSearch;
import fluid.util.FileLocator;
import fluid.util.UniqueID;

/** A set of contiguous versions off of a single root.
 * An Era is used in persistence, each delta identifies
 * its era.  An era can be defined by a <em>root</em>
 * version (exclusive) and a <em>fringe</em>, a set of descendant
 * versions (inclusive).  A version
 * is in the era if it is a descendant of the root and
 * equal to a member of the fringe or an ancestor of a fringe member.
 * In other words: \exists f \in F: r > v >= f.
 * Each era has a unique identifier used for identification
 * and persistence.
 * <p> The "initial" era is a special case.  This era has root = null
 * and the initial version as its only fringe.  This era is needed to give 
 * a place for the initial version. </p>
 * <p>  An era (other than the initial era)
 * refers to a version in its parent era.
 * As versions aren't set up to have undefined parents, we
 * currently require the parent era to be loaded before we
 * can refer to the child era.  Perhaps in a more sophisticated
 * system, loading on demand could be handled.  In the meantime, I
 * don't imagine the cost will be excessive because eras
 * are relatively small. </p>
 * <p> An era has a <em>shadow region</em> associated it which
 * holds the shadow nodes in the version tree for the versions in
 * the era. This shadow region has a special type, so we can
 * persist it correctly.  It might be easier to have make versions
 * IR nodes and eras regions, to avoid the need to shadow, but that
 * would be confusing.</p>
 * <p> With every era we associate persistent entities.
 * Entities can be associated with an era while it is new until
 * it is stored or written as a complete persistent object.
 * Clients can ask whether a particular entity is associated with
 * an era.  The answer can be yes, no or maybe. </p>
 */
public class Era extends IRPersistent {
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR");

  private static final int ERAmagic = 0x45524100; // ERA\0
  private Version root;
  private Vector fringe;
  private Vector members; /* root plus every member of era */
  private FileLocator floc;
  private ArrayList associated;
  private boolean associatedFrozen = false;

  private static final Hashtable present = new Hashtable();

  /** Create the initial era. */
  private Era()
  {
    super(ERAmagic,UniqueID.parseUniqueID("initial"));
    Version initial = Version.getInitialVersion();
    if (initial == null) {
      throw new FluidError("initial version is null");
    }
    root = null;
    fringe = new Vector(1);
    members = new Vector(2);
    fringe.addElement(initial);
    members.addElement(null); // not actually a member
    members.addElement(initial);
    try {
      initial.setEra(this,1);
    } catch (OverlappingEraException ex) {
      throw new FluidError("Initial era created multiply!");
    }
    define();
    forceComplete();
    associatedFrozen = true;
  }

  private static Era initialEra;

  /** Return era for initial version. */
  public static Era getInitialEra() {
    if (initialEra == null) initialEra = new Era();
    return initialEra;
  }

  /** Create an incomplete era with the given root.
   * @see #addVersion
   * @exception DisconnectEraException if r does not have an era.
   */
  public Era(Version r) throws DisconnectedEraException
  {
    super(ERAmagic,true);
    getInitialEra();
    if (r.getEra() == null) {
      throw new DisconnectedEraException("cannot start era off unassigned version " + r);
    }
    root = r;
    fringe = new Vector();
    members = new Vector();
    members.addElement(root); // not actually a member
  }

  /** Create an era with given root and fringe.
   * @exception OverlappingEraException if overlaps
   * with existing era.
   * @exception DisconnectEraException if r does not have an era.
   */
  public Era(Version r, Version[] f)
       throws OverlappingEraException, DisconnectedEraException
  {
    super(ERAmagic,true);
    getInitialEra();
    if (r.getEra() == null) {
      throw new DisconnectedEraException("cannot start era off unassigned version " + r);
    }
    root = r;
    fringe = new Vector(f.length);
    for (int i=0; i < f.length; ++i)
      fringe.addElement(f[i]);
    members = new Vector();
    members.addElement(root);
    int index = 0;
    LOG.debug("Creating Era " + this);
    for (Enumeration vs = new EraEnumeration(this); vs.hasMoreElements();) {
      Version v = (Version)vs.nextElement();
      members.addElement(v);
      v.setEra(this,++index);
      LOG.debug("Added " + v + " to " + this);
    }
    complete();
  }

  private Era(UniqueID id) throws IOException {
    super(ERAmagic,id);
    getInitialEra();
  }

  public Version getRoot() { return root; }
  public Enumeration fringe() { return fringe.elements(); }

  public Era getParentEra() {
    if (root == null) return null;
    else return root.getEra();
  }

  /** Add another version to an incomplete era.
   * @throws OverlappingEraException if the version already
   * is assigned to an era, or if its parent is of a different era,
   * or if the version passed is the root version (which cannot be
   * assigned an era) or if the era is complete.
   */
  public void addVersion(Version v)
       throws OverlappingEraException
  {
    if (!isNew() || isComplete())
      throw new OverlappingEraException("era cannot receive new versions");
    Version p = v.parent();
    if (p == null)
      throw new OverlappingEraException("root version cannot be in an era");
    if (p != root && p.getEra() != this)
      throw new OverlappingEraException("unrelated version cannot be added");
    members.addElement(v);
    // if parent was in fringe, remove it, and
    // in any case, add new Version on fringe.
    int i = fringe.indexOf(p);
    if (i == -1) {
      fringe.addElement(v);
    } else {
      fringe.setElementAt(v,i);
    }
    // Delay until memebers and fringe set because
    // setEra calls observers.
    v.setEra(this,members.size()-1);
  }

  /* Return the maximum version index (currently) for this era. */
  public int maxVersionOffset() {
    return members.size()-1;
  }

  public Version getVersion(int index) {
    return (Version)members.elementAt(index);
  }

  /** Return true if version is in era. */
  public boolean contains(Version v) {
    // System.out.println(this + " contains? " + v);
    Era era = v.getEra();
    if (era == this) return true;
    if (era != null) return false;
    if (isComplete()) return false; // force early termination if root == null
    /* otherwise, we compute it */
    if (v.equals(root)) return false;
    if (!v.comesFrom(root)) return false;
    for (int i=0; i < fringe.size(); ++i) {
      if (((Version)fringe.elementAt(i)).comesFrom(v)) return true;
    }
    return false;
  }

  /** Return true if on fringe of complete version. */
  public boolean isFringe(Version v) {
    if (root != null && root.equals(v)) return false;
    return fringe.contains(v);
  }

  /** Return the versions in the eras starting from the top
   * (excluding the root).
   */
  public Enumeration elements() {
    Enumeration en = members.elements();
    en.nextElement(); // drop root
    return en;
  }

  /** Add the given persistent entity to the list of associated
   * entities.  This function is idempotent.
   * The era must be new and not yet stored or written
   * as a complete entity.  This condition should not be checked for
   * by the client, nor should the exception be trapped as a matter of
   * course.  It is a serious error that shows that there is
   * a bug in Fluid code.  If an entity needs to be associated and cannot
   * be, versioned slot consistency is imperiled.
   * @throws FluidError
   *   if the era is not new or stored or written complete.
   * @throws NullPointerException
   *   if p is null.
   */
  public synchronized void addAssociated(IRPersistent p) {
    if (!isNew() || associatedFrozen) {
      throw new FluidError("association for era is fixed.");
    }
    if (associated == null) associated = new ArrayList();
    if (p == null) throw new NullPointerException("cannot associate null");
    if (!associated.contains(p))
      associated.add(p);
  }

  /** Determine whether the given entity is associated
   * this era.  We may not know if the association exists
   * if the era is not stored completely, or if it was loaded
   * without the list.
   * @return <dl> 
   * <dt>1</dt><dd>If the entity is associated.</dd>
   * <dt>-1</dt><dd>If the entity is not associated.</dd>
   * <dt>0</dt><dd>If we do not know.</dd></dl>
   */
  public synchronized int isAssociated(IRPersistent p) {
    if (associated != null && associated.contains(p)) 
      return 1;
    if (associatedFrozen)
      return -1;
    return 0;
  }


  /** Find an era given an ID.
   * @return era or null, if none with this ID
   * @see #loadEra(UniqueID,FileLocator)
   */
  public static Era findEra(UniqueID id) {
    return (Era)IRPersistent.find(id);
  }

  /** Find an era using an ID or load if not present.
   * @see #findEra(UniqueID)
   */
  public static Era loadEra(UniqueID id, FileLocator floc)
       throws IOException
  {
    Era era = findEra(id);
    if (era == null) {
      era = new Era(id);
      era.load(floc);
    }
    return era;
  }

  /** era as a filename */
  protected String getFileName() {
    return getID().toString()+".era";
  }

  protected void write(IROutput out) throws IOException {
    out.debugBegin("era name=" + this);
    if (isComplete() && isNew()) associatedFrozen = true;
    out.debugBegin("root");
    IRVersionType.writeVersion(root,out);
    out.debugEnd("root");
    out.debugBegin("versions");
    for (Enumeration en = elements(); en.hasMoreElements();) {
      Version v = (Version)en.nextElement();
      out.debugBegin("item");
      if (isFringe(v)) out.writeByte(2); else out.writeByte(1);
      Version p = v.parent();
      out.debugMark("parent");
      if (p == root) out.writeShort(0);
      else out.writeShort(p.getEraOffset());
      out.debugEnd("item");
    }
    out.debugMark("sentinel");
    out.writeByte(0);
    out.debugEnd("versions");
    out.debugBegin("numAssociated");
    out.writeInt(associated == null ? 0 : associated.size());
    out.debugEnd("numAssociated");
    if (associated != null && associated.size() > 0) {
      out.debugBegin("associated");
      for (Iterator i = associated.iterator(); i.hasNext(); ) {
	out.writePersistentReference((IRPersistent)i.next());
      }
      out.debugEnd("associated");
    }
    out.debugBegin("frozen");
    out.writeBoolean(associatedFrozen);
    out.debugEnd("frozen");
  }

  protected void read(IRInput in) throws IOException {
    in.debugBegin("era name=" + this);
    in.debugBegin("root");
    if (in.getRevision() < 4) {
      int i = in.readByte();
      if (i == 0) {
	root = Version.getInitialVersion();
      } else if (i == 1) {
	UniqueID id = UniqueID.read(in);
	Era era = findEra(id);
	if (era == null || !era.isDefined())
	  throw new IOException("parent era not defined");
	root = Version.read(in,era);
      } else {
	root = null;
      }
    } else {
      root = IRVersionType.readVersion(in);
    }
    in.debugEnd("root");
    if (in.debug())
      System.out.println("Root of era " + getID() + " is " + root);
    byte b;
    Vector newMembers = new Vector();
    Vector newFringe = new Vector();
    newMembers.addElement(root);
    try {
      Version.pushDefaultEra(null);
      in.debugBegin("versions");
      in.debugBegin("item");
      while ((b = in.readByte()) != 0) {
	in.debugMark("parent");
	int vpi = in.readShort();
	Version vp = (Version)newMembers.elementAt(vpi);
	if (in.debug())
	  System.out.println("Parent of " + ((b == 2) ? "" : "non-") +
			     "fringe version " + newMembers.size() +
			     " is version " + vpi + " = " + vp);
	Version v;
	if (members == null || members.size() <= newMembers.size()) {
	  v = Version.createVersion(vp);
	  v.setEra(this,newMembers.size());
	} else {
	  v = (Version)members.elementAt(newMembers.size());
	}
	newMembers.addElement(v);
	if (b == 2) newFringe.addElement(v);
	in.debugEnd("item");
	in.debugBegin("item");
      }
      in.debugEnd("item");
      in.debugEnd("versions");
    } catch (OverlappingEraException ex) {
      // doesn't happen
      throw new FluidError("impossible");
    } finally {
      Version.popDefaultEra();
    }
    if (members == null || newMembers.size() > members.size()) {
      members = newMembers;
      fringe = newFringe;
    }
    if (in.getRevision() >= 4) {
      associatedFrozen = false;
      in.debugBegin("numAssociated");
      int n = in.readInt();
      in.debugEnd("numAssociated");
      if (n == 0) {
	// this should be unnecessary, but may be necessary if
	// sneaky editing of IR files is done.
	associated = null;
      } else {
	associated = new ArrayList(n);
	in.debugBegin("associated");
	while (--n >= 0) {
	  associated.add(in.readPersistentReference());
	}
	in.debugEnd("associated");
      }
      in.debugBegin("frozen");
      associatedFrozen = in.readBoolean();
      in.debugEnd("frozen");
    }
  }


  /* Persistent kind */

  public static final IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException
    {
      ((Era)p).getID().write(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException
    {
      UniqueID id = UniqueID.read(in);
      IRPersistent p = IRPersistent.find(id);
      if (p == null) {
	p = new Era(id);
	try {
	  p.load(fluid.ir.IRPersistent.fluidFileLocator);
	} catch (IOException e) {
	  LOG.warn("cannot demand load parent era " + p);
	}
      }
      return p;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind,0x45); // 'E'
    IRPersistent.registerPersistentKind(EraShadowRegion.kind,0x05); // '^e'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    Version v = (Version)members.elementAt(0);
    if (v != null) {
      out.println("Root: " + v.getEra().getID() + " v" + v.getEraOffset());
    }
    for (int i=1; i < members.size(); ++i) {
      v = (Version)members.elementAt(i);
      Version p = v.parent();
      int po = (p != null && p.getEra() == this) ? p.getEraOffset() : 0;
      out.println("Version v" + v.getEraOffset() + " under v" + po);
    }
    out.println("Associations:");
    if (associated != null)
      for (Iterator it = associated.iterator(); it.hasNext();)
	out.println("  " + it.next());
    if (!associatedFrozen)
      out.println("  ...");
  }

  public static void ensureLoaded() {
    EraShadowRegion.ensureLoaded();
    Version.ensureLoaded();
//    System.out.println("Era loaded");
  }

  private final IRRegion shadowRegion = new EraShadowRegion(this);

  /** Return the shadow region of the era: the region
   * holding the version shadow nodes.
   */
  public IRRegion getShadowRegion() {
    return shadowRegion;
  }
}

/** Enumeration versions in era.  We reuse tree enumerations
 * on the shadow tree to do most of the work for us.
 */
class EraEnumeration extends DepthFirstSearch {
  private final Era era;

  EraEnumeration(Era e) {
    super(Version.getShadowTree(),e.getRoot().getShadowNode());
    era = e;
    nextElement(); // suck up root (not in era)
  }

  /** Return true if in era (or if root). */
  protected boolean mark(IRNode node) {
    /* we need to permit the root to get things going. */
    if (Version.getShadowVersion(node).equals(era.getRoot())) return true;
    return era.contains(Version.getShadowVersion(node));
  }

  /** Return next version.
   * Overrides method in superclass to fetch version from shadow node.
   */
  public Object nextElement() throws NoSuchElementException {
    Object next = super.nextElement();
    if (next instanceof IRNode)
      next = Version.getShadowVersion((IRNode)next);
    return next;
  }
}

class EraShadowRegion extends IRRegion {
  private static final int magic = 0x45535200; // "ESR\0"

  private final Era era;

  /** The era's shadow region. */
  EraShadowRegion(Era e) {
    super(magic,null);
    era = e;
  }

  // defer all these to the era:

  public UniqueID getID() {
    return era.getID();
  }
  public boolean isNew() {
    return era.isNew();
  }
  public boolean isDefined() {
    return era.isDefined();
  }
  public boolean isComplete() {
    return era.isComplete();
  }
  public boolean isStored() {
    return era.isStored();
  }

  /* persistent kind */

  static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException
    {
      EraShadowRegion esr = (EraShadowRegion)p;
      esr.era.writeReference(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException
    {
      Era e = (Era)IRPersistent.readReference(in);
      return e.getShadowRegion();
    }
  };

  public IRPersistentKind getKind() {
    return kind;
  }

  public String toString() {
    return "ESR(" + era + ")";
  }

  public static void ensureLoaded() {}
}
