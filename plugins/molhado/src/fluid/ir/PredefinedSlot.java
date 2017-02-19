/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/PredefinedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.PrintStream;

/** A class that holds a default value until it is set.
 * @see UndefinedSlot
 * @typeparam Value
 */
public abstract class PredefinedSlot extends DefaultSlotStorage {
  /** Initial value of the slot.
   * @type Value
   */
  protected Object startValue;

  /** Create a new predefined slot.
   * @param value initial value
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  public PredefinedSlot(Object value) {
    startValue = value;
  }

  /** Get the value of a predefined slot.
   * @return initial value
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  public Object getValue() {
    return startValue;
  }

  public boolean isValid() {
    return true;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    out.println("   value = " + startValue);
  }
}
