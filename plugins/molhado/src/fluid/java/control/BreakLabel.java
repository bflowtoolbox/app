/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/BreakLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.control.ControlLabel;

public class BreakLabel implements ControlLabel {
  public static BreakLabel prototype = new BreakLabel();
  public String toString() { return "break"; }
}
