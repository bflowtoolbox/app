/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/ReturnLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.control.ControlLabel;

public class ReturnLabel implements ControlLabel {
  public static ReturnLabel prototype = new ReturnLabel();
  public String toString() { return "return"; }
}
