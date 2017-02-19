/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/UndefinedConstantSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** A class that holds the place for a constant value,
 * but throws an exception if not set before used.
 * When set, it turns into a ConstantSlot.
 * @see ConstantSlot
 */
public class UndefinedConstantSlot extends UndefinedSlot {
  public static UndefinedConstantSlot prototype = new UndefinedConstantSlot();
  public Slot setValue(Object newValue) {
    return new ConstantSlot(newValue);
  }
}