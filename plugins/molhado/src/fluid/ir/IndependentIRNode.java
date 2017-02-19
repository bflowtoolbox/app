/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IndependentIRNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import fluid.util.UniqueID;

/** An IRNode which is referenced by a unique ID.
 * It lives in a region by itself.
 */
public class IndependentIRNode extends IRRegion implements IRNode {
  private static final int magic = 0x4949524e; // IIRN

  /* storage kind */

  private static IRPersistentKind kind = new IRPersistentKind() {
    public void writePersistentReference(IRPersistent p, DataOutput out)
      throws IOException {
      ((IndependentIRNode) p).getID().write(out);
    }
    public IRPersistent readPersistentReference(DataInput in)
      throws IOException {
      UniqueID id = UniqueID.read(in);
      IRPersistent p = find(id);
      if (p == null)
        p = new IndependentIRNode(id);
      return p;
    }
  };
  static {
    IRPersistent.registerPersistentKind(kind, 0x49); // 'I'
  }

  public IRPersistentKind getKind() {
    return kind;
  }

  /* instance variables */

  private final IRNode base = new PlainIRNode(null);

  protected IRNode getBase() {
    return base;
  }

  /* constructors */

  public IndependentIRNode() {
    super(magic);
    super.complete(new IRNode[] { this });
  }

  protected IndependentIRNode(UniqueID id) {
    super(magic, id);
    super.complete(new IRNode[] { this });
  }

  /* Object methods */

  public boolean equals(Object other) {
    return base.equals(other);
  }

  public int hashCode() {
    return base.hashCode();
  }

  /* IR node methods */

  public Object identity() {
    return base;
  }

  public Object getSlotValue(SlotInfo si) throws SlotUndefinedException {
    return base.getSlotValue(si);
  }

  public void setSlotValue(SlotInfo si, Object newValue)
    throws SlotImmutableException {
    base.setSlotValue(si, newValue);
  }

  public int getIntSlotValue(SlotInfo si) throws SlotUndefinedException {
    return base.getIntSlotValue(si);
  }

  public void setSlotValue(SlotInfo si, int newValue)
    throws SlotImmutableException {
    base.setSlotValue(si, newValue);
  }

  public boolean valueExists(SlotInfo si) {
    return base.valueExists(si);
  }
}