/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRChunk.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import fluid.util.FileLocator;
import fluid.util.Hashtable2;

/** 
 * This class represents a <em>chunk</em> of IR that can be written out
 * or read in.  The chunk is defined by a set of nodes and attributes,
 * this is, by an IR region and a bundle.  A chunk is mutable.
 *
 * @see IRRegion
 * @see Bundle
 */
public class IRChunk extends IRPersistent {
  private static final int magic = 0x49524300; // "IRC\0"
  private final IRRegion region;
  private final Bundle bundle;

  /** Create a new chunk. */
  protected IRChunk(int magic, IRRegion r, Bundle b) {
    super(magic, false);
    region = r;
    bundle = b;
    //importRegion(r);
    complete(); //?? Really? 7/18/2001: 7/27/2001
    //?? If the region is incomplete, the chunk may not be done.
    //?? But usually it should be OK.
  }

  /** Set up an existing chunk to load. */
  protected IRChunk(int magic, IRRegion r, Bundle b, boolean unused) {
    super(magic, null);
    region = r;
    bundle = b;
  }

  /* Accessors */

  public IRRegion getRegion() {
    return region;
  }

  public Bundle getBundle() {
    return bundle;
  }

  protected String getFileName() {
    return region.getID() + "-" + bundle.getID() + ".ir";
  }

  /* We never need this feature, and it slows us down
  public boolean equals(Object other) {
    if (other instanceof IRChunk) {
      IRChunk chunk = (IRChunk)other;
      return
  chunk.getRegion().equals(region) &&
  chunk.getBundle().equals(bundle);
    } else {
      return false;
    }
  }
  public int hashCode() {
    return region.hashCode() + bundle.hashCode();
  }
  */

  private static Hashtable2 chunks = new Hashtable2();

  /** Look for a chunk and return null if not found. */
  public static IRChunk find(IRRegion r, Bundle b) {
    return (IRChunk) chunks.get(r, b);
  }

  /** Add a chunk to the database */
  protected static void add(IRChunk ch) {
    chunks.put(ch.getRegion(), ch.getBundle(), ch);
  }

  /** Make a new chunk for region and bundle or return an existing one. */
  public static IRChunk get(IRRegion r, Bundle b) {
    IRChunk installed = (IRChunk) chunks.get(r, b);
    if (installed == null) {
      if (r.isNew())
        installed = new IRChunk(magic, r, b);
      else
        installed = new IRChunk(magic, r, b, false);
      add(installed);
    }
    return installed;
  }

  /** Load a chunk for region and bundle unless already loaded. */
  public static void load(IRRegion r, Bundle b, FileLocator floc)
    throws IOException {
    get(r, b).load(floc);
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
      IRRegion r = (IRRegion) IRPersistent.readReference(in);
      Bundle b = (Bundle) IRPersistent.readReference(in);
      return get(r, b);
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind, 0x43);
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  /* Output */

  /** Return the number of nodes attributed in this chunk.
   * In subclasses, it may include nodes from other regions.
   */
  protected int getNumAttributedNodes() {
    return region.getNumNodes();
  }

  /** Return the i'th node to store attributes for.
   * In subclasses, it may be from another chunk.
   * @param i 1-indexed node.  (0 = null)
   */
  protected IRNode getAttributedNode(int i) throws IOException {
    return region.getNode(i);
  }

  /** Write a chunk of IR to an output stream. */
  protected void write(IROutput out) throws IOException {
    out.debugBegin("chunk name=" + this);
    writeImports(out);
    writeAttributeValues(out);
    out.debugEnd("chunk");
  }

  /** Write a table of all attribute values. */
  protected void writeAttributeValues(IROutput out) throws IOException {
    // output attribute values:
    out.debugBegin("body");
    out.debugBegin("numAttributes");
    int n = bundle.getNumAttributes();
    out.writeInt(n);
    out.debugEnd("numAttributes");
    for (int j = 1; j <= n; ++j) {
      PersistentSlotInfo si = (PersistentSlotInfo) bundle.getAttribute(j);
      out.debugBegin("al name=" + si);
      int m = getNumAttributedNodes();
      for (int i = 1; i <= m; ++i) {
        IRNode node = getAttributedNode(i);
        si.writeSlot(node, out);
      }
      out.debugBegin("sentinel");
      out.writeInt(0);
      out.debugEnd("sentinel");
      out.debugEnd("al");
    }
    out.debugEnd("body");
  }

  /* Input */

  protected void read(IRInput in) throws IOException {
    in.debugBegin("chunk name=" + this);
    readImports(in);
//    if (in.debug()) {
//      System.out.print("Partway through loading ");
//      this.describe(System.out);
//    }
    readAttributeValues(in);
    in.debugEnd("chunk");
  }

  /** Read a table of attribute values. */
  protected void readAttributeValues(IRInput in) throws IOException {
    // attributes:
    in.debugBegin("body");
    int n = bundle.getNumAttributes();
    in.debugBegin("numAttributes");
    if (in.getRevision() >= 1)
      n = in.readInt();
    in.debugEnd("numAttributes");
    for (int j = 1; j <= n; ++j) {
      PersistentSlotInfo si = (PersistentSlotInfo) bundle.getAttribute(j);
      in.debugBegin("al name=" + si);
      IRNode node;
      in.debugBegin("pair");
      while ((node = in.readNode()) != null) {
        si.readSlotValue(node, in);
        in.debugEnd("pair");
        in.debugBegin("pair");
      }
      in.debugEnd("pair");
      in.debugEnd("al");
    }
    in.debugEnd("body");
  }

  public String toString() {
    return "Ch(" + region + "," + bundle + ")";
  }
}