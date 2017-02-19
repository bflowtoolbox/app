/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/AddLabel.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** When flow goes through this node, a label is added onto
 * what we already have.
 * @author John Tang Boyland
 * @see LabelTest
 * @see PendingLabelStrip
 */

public class AddLabel extends Flow {
  protected ControlLabel label;
  public AddLabel(ControlLabel label) {
    this.label = label;
  }
  public ControlLabel getLabel() {
    return label;
  }
}

