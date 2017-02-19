/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/LabelTest.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** When flow goes through this node, the choice that
 * is made depends on the top label in the node.
 * (And as with all choices, it is possible for analysis
 * to let control flow on both exits).
 */

public class LabelTest extends ComponentChoice {
  private final ControlLabel testLabel;
  /** Create a label test node.
   * @param c the component this node lives in.
   * @param label the label to add for reverse analysis.
   */
  public LabelTest(Component c, ControlLabel label) {
    super(c,label);
    testLabel = label;
  }
  /** Create a label test node.
   * @param c the component this node lives in.
   * @param v some information for analysis
   * @param label the label to add for reverse analysis.
   */
  public LabelTest(Component c, Object v, ControlLabel label) {
    super(c,v);
    testLabel = label;
  }
  public ControlLabel getTestLabel() {
    return testLabel;
  }
}
