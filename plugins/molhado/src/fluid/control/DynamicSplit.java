/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/DynamicSplit.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** A control flow node that itself can decide whether flow goes
 * to either output.
 */
public abstract class DynamicSplit extends Split {
  public DynamicSplit() { super(); }
  /** Return true if flow can happen between the source
   * and the output indicated.
   * @param flag true if first output, false if second output.
   */
  public abstract boolean test(boolean flag);
}
