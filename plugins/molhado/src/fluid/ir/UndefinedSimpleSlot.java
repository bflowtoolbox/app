/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/UndefinedSimpleSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** A class that holds the place for a value,
 * but throws an exception if not set before used.
 * When set, it turns into a SimpleSlot
 * @see SimpleSlot
 */
public class UndefinedSimpleSlot extends UndefinedSlot {
  public static UndefinedSimpleSlot prototype = new UndefinedSimpleSlot();
  public Slot setValue(Object newValue) {
    return new SimpleSlot(newValue);
  }
}