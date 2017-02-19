/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/ThrownExceptionLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

public class ThrownExceptionLabel extends ExceptionLabel {
  public final IRNode throwNode;
  public ThrownExceptionLabel(IRNode t) {
    throwNode = t;
  }
  public String toString() {
    return "thrown exception";
  }
}
