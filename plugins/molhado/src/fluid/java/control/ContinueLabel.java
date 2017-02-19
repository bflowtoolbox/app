/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/ContinueLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.control.ControlLabel;

public class ContinueLabel implements ControlLabel {
  public static ContinueLabel prototype = new ContinueLabel();
  public String toString() { return "continue"; }
}
