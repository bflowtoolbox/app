/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/DerivedSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** This class partially specifies slots which are computed on demand.
 * @typeparam Value The type of values stored in this slot.
 * <dl purpose=fluid>
 *  <dt>capabilities<dd> store
 * </dl>
 */
public abstract class DerivedSlotInfo extends SlotInfo {
  /** Allocate a new anonymous and temporary derived slot description. */
  public DerivedSlotInfo() {
  }

  /** Allocate and register a new slot description.
   * @param name The name under which to register the slots.
   * @exception SlotAlreadyRegisteredException
   * If slots have already been registered under this name.
   * @precondition nonNull(name)
   * @capabilities store, read, write
   */
  public DerivedSlotInfo(String name, IRType type)
    throws SlotAlreadyRegisteredException {
    super(name, type);
  }

  /** Get the slot's value for a particular node.
   * @param node IR node for which the slot is to be returned.
   * @precondition nonNull(node)
   * @return the value of the slot associated with this node.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  protected abstract Object getSlotValue(IRNode node);
  protected abstract boolean valueExists(IRNode node);

  protected void setSlotValue(IRNode node, Object newValue)
    throws SlotImmutableException {
    throw new SlotImmutableException("Slot is constant");
  }

  public void writeSlot(IRNode node, IROutput out) {
  }
  public void readSlotValue(IRNode node, IRInput in) throws IOException {
    throw new IOException("derived slots are not stored");
  }
}