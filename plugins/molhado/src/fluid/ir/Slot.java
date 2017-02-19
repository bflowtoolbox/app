/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/Slot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;

/** An indirect reference to another value.
 * @see UndefinedSlot
 * @see PredefinedSlot
 * @typeparam Value
 */

public interface Slot {
  /** Get the value.
   * @return the value represented indirectly.
   *  <dl purpose=fluid>
   *    <dt>type<dd> Value
   *  </dl>
   * @precondition isValid()
   * @exception SlotUndefinedException
   *  If the slot is not initialized with a value.
   */
  public Object getValue() throws SlotUndefinedException;

  /** Set the value stored here.
   * The result of this method replaces this object.
   * @param newValue the value to store.
   *  <dl purpose=fluid>
   *    <dt>type<dd> Value
   *  </dl>
   * @return slot to replace this one.
   * @exception SlotImmutableException
   *  If the slot is not mutable.
   */
  public Slot setValue(Object newValue) throws SlotImmutableException;

  /** Return true if there is a valid value */
  public boolean isValid();

  /** Has this slot changed since its "previous value" ("initial value") ? */
  public boolean isChanged();

  /** Write the slot's value to the output stream. */
  public void writeValue(IRType ty, IROutput out) throws IOException;

  /** Read a new slot's value from the input stream and return a new slot. */
  public Slot readValue(IRType ty, IRInput in) throws IOException;

  /** Describe information about the slot for debugging **/
  public void describe(PrintStream out);
}
