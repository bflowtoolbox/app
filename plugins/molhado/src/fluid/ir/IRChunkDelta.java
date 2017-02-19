/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRChunkDelta.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import fluid.util.UniqueID;

/** A loadable set of information changes of a chunk.
 * 
 * <p>
 * An IRChunkDelta by default does not have identity
 * but subclasses in the @{link fluid.version} package
 * use specific versions to have identity.
 *
 * @see IRChunk
 */
public class IRChunkDelta extends IRPersistent {
  private static final int magic = 0x49524344; // "IRCD"
  private final IRChunk chunk;

  /** Create a new delta with identity. */
  protected IRChunkDelta(IRChunk c) {
    this(magic, c, false);
  }

  /** Create a new delta. */
  protected IRChunkDelta(int magic, IRChunk c, boolean hasID) {
    super(magic, hasID);
    chunk = c;
  }

  /** Set up an existing chunk to load. */
  protected IRChunkDelta(int magic, IRChunk c, UniqueID id) {
    super(magic, id);
    chunk = c;
  }

  /* Accessors */

  public IRChunk getChunk() {
    return chunk;
  }

  protected String getFileName() {
    UniqueID id = getID();
    if (id == null)
      return null;
    return getID().toString() + ".ird";
  }

  /* Kind */

  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException {
      IRChunkDelta cd = (IRChunkDelta) p;
      cd.getID().write(out);
      cd.getChunk().writeReference(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException {
      UniqueID id = UniqueID.read(in);
      IRChunk chunk = (IRChunk) IRPersistent.readReference(in);
      IRPersistent p = IRPersistent.find(id);
      if (p == null)
        p = new IRChunkDelta(magic, chunk, id);
      return p;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind, 0x44);
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  /* Output */

  /** Write a chunk delta to an output stream. */
  protected void write(IROutput out) throws IOException {
    out.debugBegin("delta name=" + this);
    writeImports(out);
    writeChangedAttributes(out);
    out.debugEnd("delta");
  }

  /** Write the changed attributes */
  protected void writeChangedAttributes(IROutput out) throws IOException {
    out.debugBegin("body");
    Bundle bundle = chunk.getBundle();
    // output attribute values:
    int n = bundle.getNumAttributes();
    int m = chunk.getNumAttributedNodes();
    out.debugBegin("numAttributes");
    out.writeInt(n);
    out.debugEnd("numAttributes");
    for (int j = 1; j <= n; ++j) {
      PersistentSlotInfo si = (PersistentSlotInfo) bundle.getAttribute(j);
      out.debugBegin("dl name=" + si);
      for (int i = 1; i <= m; ++i) {
        IRNode node = chunk.getAttributedNode(i);
        si.writeChangedSlot(node, out);
      }
      out.debugBegin("sentinel");
      out.writeInt(0);
      out.debugEnd("sentinel");
      out.debugEnd("dl");
    }
    out.debugEnd("body");
  }

  /* Input */

  protected void read(IRInput in) throws IOException {
    in.debugBegin("delta name=" + this);
    readImports(in);
//    if (in.debug()) {
//      System.out.print("Partway through loading ");
//      this.describe(System.out);
//    }
    readChangedAttributes(in);
    in.debugEnd("delta");
  }

  /** Read a table of attribute values. */
  protected void readChangedAttributes(IRInput in) throws IOException {
    in.debugBegin("body");
    Bundle bundle = chunk.getBundle();
    in.debugBegin("numAttributes");
    int n = in.readInt();
    in.debugEnd("numAttributes");
    for (int j = 1; j <= n; ++j) {
      in.debugBegin("dl");
      PersistentSlotInfo si = (PersistentSlotInfo) bundle.getAttribute(j);
      IRNode node;
      in.debugBegin("change");
      while ((node = in.readNode()) != null) {
        si.readChangedSlotValue(node, in);
        in.debugEnd("change");
        in.debugBegin("change");
      }
      in.debugEnd("change");
      in.debugEnd("dl");
    }
    in.debugEnd("body");
  }

  public String toString() {
    return "IRCD(" + getID() + ")";
  }
}