/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/PredefinedSimpleSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** A shared container.  When set a copy is introduced.
 * No versioning.
 * @typeparam Value
 * @see SimpleSlot
 * @see UndefinedSimpleSlot
 */
public class PredefinedSimpleSlot extends PredefinedSlot {
  public PredefinedSimpleSlot(Object initial) {
    super(initial);
  }
  public Slot setValue(Object newValue) {
    return new SimpleSlot(newValue);
  }
}
