/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SimpleSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;

/** A simple container: the value simply is stored as a field.
 * No versioning.
 * @typeparam Value
 */
public class SimpleSlot extends DefaultSlotStorage {
  /** The value that is fetched and modified.
   * @type Value
   */
  private Object value;
  private boolean dirty;

  public SimpleSlot(Object initial) {
    value = initial;
    dirty = true; /// unlike predefined slot
  }

  public Object getValue() {
    return value;
  }

  public Slot setValue(Object newValue) {
    value = newValue;
    dirty = true;
    return this;
  }

  public boolean isValid() {
    return true;
  }

  public boolean isChanged() {
    return dirty;
  }

  public void writeValue(IRType ty, IROutput out) 
     throws IOException
  {
    super.writeValue(ty,out);
    dirty = false;
  }

  public Slot readValue(IRType ty, IRInput in) 
     throws IOException
  {
    value = ty.readValue(in);
    dirty = false;
    return this;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    out.println("    value = " + value);
  }
}
