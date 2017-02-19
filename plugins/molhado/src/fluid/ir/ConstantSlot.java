/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/ConstantSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** A slot that cannot change value.  May be shared or may not be.
 * @typeparam Value
 */
public class ConstantSlot extends PredefinedSlot implements Slot {
  public ConstantSlot(Object initial) {
    super(initial);
  }

  public Slot setValue(Object newValue) throws SlotImmutableException {
    throw new SlotImmutableException("constant slots are immutable");
  }
}