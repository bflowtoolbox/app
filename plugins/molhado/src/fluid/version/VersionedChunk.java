/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedChunk.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

import fluid.ir.Bundle;
import fluid.ir.IRChunk;
import fluid.ir.IRCompound;
import fluid.ir.IRCompoundType;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentKind;
import fluid.ir.IRRegion;
import fluid.ir.IRType;
import fluid.ir.PersistentSlotInfo;
import fluid.ir.SlotInfo;
import fluid.util.FileLocator;

/** A chunk of IR including some versioned attributes.
 * The versioned chunk contains a set of attributes as does a chunk,
 * but the set of nodes is a versioned region rather than a normal region.
 * The versioned chunk includes a tree of versions of itself.
 * Each versioned chunk delta (VCD, for short) is an separately
 * storable entity.  It is also possible to store a versioned chunk
 * snapshot (VCS) at any particular version.
 * </p>
 */
public class VersionedChunk extends IRChunk {
  private static final int magic = 0x56430a00; // VC\n\0

  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR");

  /** Create a new versioned chunk.
   */
  protected VersionedChunk(VersionedRegion vr, Bundle b) {
    super(magic, vr, b, false);
  }

  private Hashtable versionedStructures = new Hashtable();

  /** A versioned structure is used here temporarily until
   * the nodes are assigned to VRDs.
   */
  protected class Structure extends VersionedStructureProxy {
    final Version version;
    Structure(Version v) {
      version = v;
    }

    protected VersionedStructure computeReplacement() {
      if (version.getEra() == null)
        return null;
      VersionedRegion vr = getVersionedRegion();
      IRChunk ch = IRChunk.get(vr.getDelta(version.getEra()), getBundle());
      versionedStructures.remove(version);
      return IRChunkVersionedStructure.get(ch);
    }
  };

  VersionedStructure getVersionedStructure(IRNode n) {
    if (IRRegion.hasOwner(n)) {
      IRChunk ch = IRChunk.get(IRRegion.getOwner(n), getBundle());
      return IRChunkVersionedStructure.get(ch);
    }
    VersionedRegion vr = getVersionedRegion();
    Version v = vr.getVersion(n);
    VersionedStructure vs = (VersionedStructure) versionedStructures.get(v);
    if (vs == null) {
      Structure s = new Structure(v);
      vs = s;
      versionedStructures.put(v, vs);
      v.addPersistentObserver(s);
    }
    return vs;
  }

  /** Find or create a versioned chunk for the given versioned region
   * and bundle.
   * @deprecated use @{link #get(VersionedRegion,Bundle)}
   */
  public static VersionedChunk getVersionedChunk(
    VersionedRegion vr,
    Bundle b) {
    return get(vr, b);
  }
  /** Find or create a versioned chunk for the given versioned region
   * and bundle.
   */
  public static VersionedChunk get(VersionedRegion vr, Bundle b) {
    VersionedChunk vc = (VersionedChunk) find(vr, b);
    if (vc == null) {
      vc = new VersionedChunk(vr, b);
      LOG.info("Creating new " + vc);
      add(vc);
    }
    return vc;
  }

  /* accessor methods */

  public VersionedRegion getVersionedRegion() {
    return (VersionedRegion) super.getRegion();
  }

  public Era getInitialEra() {
    return getVersionedRegion().getInitialEra();
  }

  /** Return delta for era. */
  public VersionedDelta getDelta(Era era) {
    return Delta.get(this, era);
  }

  /** Return snapshot for version. */
  public VersionedSnapshot getSnapshot(Version v) {
    return Snapshot.get(this, v);
  }

  public boolean isDefined(Version v) {
    VersionedRegion vr = getVersionedRegion();
    Bundle b = getBundle();
    Era e0 = vr.getInitialEra();
    while (v.getEra() == null)
      v = v.parent();
    if (e0.getRoot().comesFrom(v))
      return true;
    for (Era e = v.getEra();; e = e.getParentEra()) {
      IRChunk c = IRChunk.get(vr.getDelta(e), b);
      VersionedStructure vs = IRChunkVersionedStructure.get(c);
      if (!vs.isDefined(v))
        return false;
      if (e == e0)
        return true;
    }
  }

  public static double debugIsDefined;

  protected String getFileName() {
    return null; // do not save this entity
  }

  public String toString() {
    return "V" + super.toString();
  }

  public void describe(PrintStream out) {
    super.describe(out);
  }

  /* Kind */

  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException {
      IRChunk chunk = (IRChunk) p;
      chunk.getRegion().writeReference(out);
      chunk.getBundle().writeReference(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException {
      VersionedRegion vr = (VersionedRegion) IRPersistent.readReference(in);
      Bundle b = (Bundle) IRPersistent.readReference(in);
      return get(vr, b);
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind, 0x76); // 'v'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  public static void ensureLoaded() {
    Delta.ensureLoaded();
    Snapshot.ensureLoaded();
  }

  /** A versioned chunk delta (VCD) stores information about a
   * versioned chunk relative to a single era.
   * It is a combination of two things: <ol>
   * <li> a normal chunk of values of attributes of new nodes for
   *      the versioned region for the era.
   * <li> a set of delta values for attributes of all nodes for
   *	the versioned region for the era.
   */
  protected static class Delta extends VersionedDelta {
    static final int magic = 0x56434400; // VCD\0

    Delta(VersionedChunk vc, Era e) {
      super(magic, vc, e);
    }

    Delta(VersionedChunk chunk, Era e, boolean unused) {
      super(magic, chunk, e, false);
    }

    VersionedChunk getVersionedChunk() {
      return (VersionedChunk) getChunk();
    }

    VersionedRegion getVersionedRegion() {
      return getVersionedChunk().getVersionedRegion();
    }

    public static Delta get(VersionedChunk ch, Era e) {
      Delta d = (Delta) find(ch, e);
      if (d == null) {
        if (e.isNew())
          d = new Delta(ch, e);
        else
          d = new Delta(ch, e, false);
        add(d);
      }
      return d;
    }

    /* Kind */

    private static IRPersistentKind kind = new IRPersistentKind() {
      public void writePersistentReference(IRPersistent p, DataOutput out)
        throws IOException {
        VersionedChunk.Delta vcd = (VersionedChunk.Delta) p;
        vcd.getVersionedRegion().writeReference(out);
        vcd.getChunk().getBundle().writeReference(out);
        vcd.getEra().writeReference(out);
      }
      public IRPersistent readPersistentReference(DataInput in)
        throws IOException {
        VersionedRegion vr = (VersionedRegion) IRPersistent.readReference(in);
        Bundle b = (Bundle) IRPersistent.readReference(in);
        Era e = (Era) IRPersistent.readReference(in);
        VersionedChunk vc = VersionedChunk.get(vr, b);
        return get(vc, e);
      }
    };
    static {
      IRPersistent.registerPersistentKind(kind, 0x03); // Control-C
    }

    public IRPersistentKind getKind() {
      return kind;
    }

    /* Output */

    /** When writing, we write the pieces that make up this thing.
     * It is nothing more than a hollow shell.
     */
    public void write(IROutput out) throws IOException {
      VersionedRegion vr = getVersionedRegion();
      Version alpha = Version.getInitialVersion();
      Era era = getEra();
      Bundle bundle = getChunk().getBundle();
      IRChunk ch0 = IRChunk.get(vr.getDelta(era), bundle);
      writeImports(out);
      VersionedSnapshot vs = VersionedSnapshot.get(ch0, alpha);
      out.debugBegin("subchunk name=" + vs);
      vs.writeAttributeValues(out);
      out.debugEnd("subchunk");
      for (Era e = era; e != null; e = e.getParentEra()) {
        IRChunk ch = IRChunk.get(vr.getDelta(e), bundle);
        if (era.isAssociated(ch) == 1) {
          e.writeReference(out);
          VersionedDelta vd = VersionedDelta.get(ch, era);
          out.debugBegin("subdelta name=" + vd);
          vd.writeChangedAttributes(out);
          out.debugEnd("subdelta");
        }
      }
      out.debugMark("sentinel");
      Era.getInitialEra().writeReference(out);
    }

    /* Input */

    /** Before a load, we make sure that the versioned chunk and
     * version region or loaded and that either the parent is loaded,
     * or else we have a snapshot for the root version.
     * @see IRPersistent#load(FileLocator floc)
     */
    public void load(FileLocator floc) throws IOException {
      if (isNew() || isComplete())
        return;
      VersionedChunk ch = getVersionedChunk();
      Era era = getEra();
      Version v = era.getRoot();
      if (!ch.isDefined(v)) {
        //! Should be changed to use SlotUnknown catchers!
        try {
          ch.getSnapshot(v).load(floc);
        } catch (IOException ex) {
          ch.getDelta(v.getEra()).load(floc);
        }
      }
      // System.out.println("Loading " + this);
      super.load(floc);
    }

    /** Read in changes for all attributes for all
     * nodes in the growing versioned region.
     * In persistence v1.4, we read in the pieces individually.
     * In earlier versions we have a more monolithic solution.
     */
    protected void read(IRInput in) throws IOException {
      VersionedRegion vr = getVersionedRegion();
      Version alpha = Version.getInitialVersion();
      Era era = getEra();
      Bundle bundle = getChunk().getBundle();
      IRChunk ch0 = IRChunk.get(vr.getDelta(era), bundle);
      VersionedSnapshot vs = VersionedSnapshot.get(ch0, alpha);
      if (in.getRevision() >= 4) {
        readImports(in);
        in.debugBegin("subchunk name=" + vs);
        vs.readAttributeValues(in);
        in.debugEnd("subchunk");
        vs.define();
        Era e;
        Era e0 = Era.getInitialEra();
        while ((e = (Era) IRPersistent.readReference(in)) != e0) {
          IRChunk ch = IRChunk.get(vr.getDelta(e), bundle);
          VersionedDelta vd = VersionedDelta.get(ch, era);
          in.debugBegin("subdelta name=" + vd);
          vd.readChangedAttributes(in);
          in.debugEnd("subdelta");
          vd.define();
        }
        return;
      }
      // old way:
      readImports(in);
      in.debugBegin("subchunk name=" + vs);
      vs.readAttributeValues(in);
      in.debugEnd("subchunk");
      vs.define();
      VersionedStructureFactory.pushVS(null);
      try {
        Version.saveVersion();
        try {
          // permit readDeltas to set Version
          // (Hence they are inside this 'try')
          if (in.debug()) {
            LOG.debug("Reading deltas for " + this);
          }
          readDeltas(in);
        } finally {
          Version.restoreVersion();
        }
      } finally {
        VersionedStructureFactory.popVS();
      }
      // force all the deltas implicitly loaded to be defined.
      for (Era e = era; e != Era.getInitialEra(); e = e.getParentEra()) {
        IRChunk ch = IRChunk.get(vr.getDelta(e), bundle);
        VersionedDelta vd = VersionedDelta.get(ch, era);
        vd.define();
      }
    }

    protected void readDeltas(IRInput in) throws IOException {
      VersionedChunk vc = getVersionedChunk();
      Bundle b = vc.getBundle();
      Era era = getEra();
      try {
        VersionedSlot.pushEra(era); // change behavior of versioned slots
        int n = b.getNumAttributes();
        in.debugBegin("body");
        if (in.getRevision() >= 1) {
          in.debugBegin("numAttributes");
          n = in.readInt();
          in.debugEnd("numAttributes");
        }
        for (int i = 1; i <= n; ++i) {
          SlotInfo attr = b.getAttribute(i);
          PersistentSlotInfo psi = (PersistentSlotInfo) attr;
          in.debugBegin("dl name=" + psi);
          IRNode node;
          while ((node = in.readNode()) != null) {
            VersionedStructureFactory.setVS(vc.getVersionedStructure(node));
            psi.readSlotValue(node, in); //NB: and version
          }
          IRType t = psi.getType();
          if (t instanceof IRCompoundType) {
            if (in.debug()) {
              LOG.debug("Reading changed contents for " + attr.name());
            }
            in.debugMark("changedcontents");
            while ((node = in.readNode()) != null) {
              VersionedStructureFactory.setVS(vc.getVersionedStructure(node));
              if (in.debug()) {
                IRRegion r = IRRegion.getOwner(node);
                LOG.debug("  Node " + r + " #" + r.getIndex(node));
              }
              Version v;
              v = Version.getInitialVersion();
              Version.setVersion(v);
              IRCompound c = (IRCompound) node.getSlotValue(attr);
              c.readChangedContents((IRCompoundType) t, in);
            }
          }
          in.debugEnd("dl");
        }
        in.debugEnd("body");
      } finally {
        VersionedSlot.popEra();
      }
    }

    protected void forceComplete() {
      VersionedRegion vr = getVersionedRegion();
      Version alpha = Version.getInitialVersion();
      Era era = getEra();
      Bundle bundle = getChunk().getBundle();
      IRChunk ch0 = IRChunk.get(vr.getDelta(era), bundle);
      VersionedSnapshot.get(ch0, alpha).forceComplete();
      Era e0 = Era.getInitialEra();
      for (Era e = era; e != e0; e = e.getParentEra()) {
        IRChunk ch = IRChunk.get(vr.getDelta(e), bundle);
        VersionedDelta.get(ch, era).forceComplete();
      }
      super.forceComplete();
    }

    public String toString() {
      return "VCD("
        + getVersionedRegion()
        + ","
        + getChunk().getBundle()
        + ","
        + getEra()
        + ")";
    }

    public static void ensureLoaded() {
    }
  }

  /** A versioned chunk snapshot (VCS) is snapshot of the state of versioned
   * chunk at a particular version.  It consists of snapshots for all
   * the VRD's making up a VR.
   */
  protected static class Snapshot extends VersionedSnapshot {
    private static final int magic = 0x56435300; // VCS\0

    public VersionedRegion getVersionedRegion() {
      return (VersionedRegion) getRegion();
    }

    public VersionedChunk getVersionedChunk() {
      return (VersionedChunk) getChunk();
    }

    /** Create a versioned chunk version in preparation for saving. */
    protected Snapshot(VersionedChunk vc, Version v) {
      super(magic, vc, v);
    }

    /** Create a versioned chunk version in preparation for loading. */
    protected Snapshot(VersionedChunk vc, Version v, boolean unused) {
      super(magic, vc, v, false);
      vc.getVersionedRegion().exportTo(this, v.getEra());
    }

    /** Find or create a snapshot */
    public static Snapshot get(VersionedChunk vc, Version v) {
      Snapshot vcs = (Snapshot) find(vc, v);
      if (vcs == null) {
        if (v.getEra() == null || v.getEra().isNew())
          vcs = new Snapshot(vc, v);
        else
          vcs = new Snapshot(vc, v, false);
        add(vcs);
      }
      return vcs;
    }

    /* Kind */

    private static IRPersistentKind kind = new IRPersistentKind() {
      public void writePersistentReference(IRPersistent p, DataOutput out)
        throws IOException {
        Snapshot vcs = (Snapshot) p;
        vcs.getRegion().writeReference(out);
        vcs.getBundle().writeReference(out);
        vcs.getVersion().getEra().writeReference(out);
        vcs.getVersion().write(out);
      }
      public IRPersistent readPersistentReference(DataInput in)
        throws IOException {
        VersionedRegion vr = (VersionedRegion) IRPersistent.readReference(in);
        Bundle b = (Bundle) IRPersistent.readReference(in);
        Era e = (Era) IRPersistent.readReference(in);
        VersionedChunk vc = VersionedChunk.get(vr, b);
        return get(vc, Version.read(in, e));
      }
    };
    static {
      IRPersistent.registerPersistentKind(kind, 0x13); // control-S
    }

    public IRPersistentKind getKind() {
      return kind;
    }

    /* Output */

    // the following two methods are needed only for reading old IR:

    protected int getNumAttributedNodes() {
      int n = 0;
      VersionedRegion vr = getVersionedRegion();
      for (Era e = getVersion().getEra(); e != null; e = e.getParentEra()) {
        n += vr.getDelta(e).getNumNodes();
      }
      return n;
    }

    protected IRNode getAttributedNode(int i) throws IOException {
      IRNode n = getImportedNode(i);
      VersionedChunk vc = getVersionedChunk();
      VersionedStructureFactory.setVS(vc.getVersionedStructure(n));
      return n;
    }

    /** When writing, we jam together VS's for each VRD
     */
    public void write(IROutput out) throws IOException {
      writeImports(out);
      VersionedRegion vr = getVersionedRegion();
      Bundle b = getBundle();
      Version version = getVersion();
      Era e = version.getEra();
      Era e0 = vr.getInitialEra();
      for (;;) {
        IRChunk ch = IRChunk.get(vr.getDelta(e), b);
        VersionedSnapshot vs = VersionedSnapshot.get(ch, version);
        out.debugBegin("subchunk name=" + vs);
        vs.writeAttributeValues(out);
        out.debugEnd("subchunk");
        if (e == e0)
          break; // stop loop
        e = e.getParentEra();
      }
    }

    /** Read snapshot from file.
     * For new IR, read in little VS's.
     * For old IR, more monolithic.
     */
    public void read(IRInput in) throws IOException {
      VersionedRegion vr = getVersionedRegion();
      Bundle b = getBundle();
      Version version = getVersion();
      Era e0 = vr.getInitialEra();
      if (in.getRevision() >= 4) {
        readImports(in);
        for (Era e = version.getEra();; e = e.getParentEra()) {
          IRChunk ch = IRChunk.get(vr.getDelta(e), b);
          VersionedSnapshot vs = VersionedSnapshot.get(ch, version);
          in.debugBegin("subchunk name=" + vs);
          vs.readAttributeValues(in);
          in.debugEnd("subchunk");
          vs.define();
          if (e == e0)
            break; // stop loop
        }
        return;
      }
      // old
      VersionedStructureFactory.pushVS(null);
      Version.saveVersion();
      version.clamp();
      try {
        Version.setVersion(version);
        super.read(in);
      } finally {
        version.unclamp();
        Version.restoreVersion();
        VersionedStructureFactory.popVS();
      }
    }

    protected void forceComplete() {
      VersionedRegion vr = getVersionedRegion();
      Bundle b = getBundle();
      Version version = getVersion();
      Era e0 = vr.getInitialEra(); // Now force complete on little VSs
      for (Era e = version.getEra();; e = e.getParentEra()) {
        IRChunk ch = IRChunk.get(vr.getDelta(e), b);
        VersionedSnapshot.get(ch, version).forceComplete();
        if (e == e0)
          break;
      }
      super.forceComplete();
    }

    public String toString() {
      Version version = getVersion();
      return "VCS("
        + getVersionedRegion()
        + ","
        + getBundle()
        + ","
        + version.getEra()
        + ","
        + version.getEraOffset()
        + ")";
    }

    public static void ensureLoaded() {
    }
  }
}
