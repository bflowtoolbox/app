/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/DefaultSlotStorage.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;

/** Default implementations for storage methods of a slot.
 */
public abstract class DefaultSlotStorage implements Slot {
  /** Does this slot contain changed information.
   * By default, assume not.
   */
  public boolean isChanged() {
    return false;
  }
  public void writeValue(IRType ty, IROutput out) 
     throws IOException
  {
    ty.writeValue(getValue(),out);
  }
  public Slot readValue(IRType ty, IRInput in) 
     throws IOException
  {
    // System.out.println("Reading slot of type " + ty);
    return setValue(ty.readValue(in));
  }

  public void describe(PrintStream out) {
    out.println(getClass().getName());
  }
}
