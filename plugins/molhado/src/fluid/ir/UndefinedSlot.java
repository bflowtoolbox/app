/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/UndefinedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** A class that holds the place for a value,
 * but throws an exception if not replaced before used.
 */
public abstract class UndefinedSlot extends DefaultSlotStorage {
  public Object getValue() {
    throw new SlotUndefinedException();
  }
  public boolean isValid() {
    return false;
  }
}