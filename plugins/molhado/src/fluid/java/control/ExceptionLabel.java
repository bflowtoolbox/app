/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/ExceptionLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.control.ControlLabel;

public class ExceptionLabel implements ControlLabel {
  public static ExceptionLabel prototype = new ExceptionLabel();
  public String toString() { return "exception"; }
}
