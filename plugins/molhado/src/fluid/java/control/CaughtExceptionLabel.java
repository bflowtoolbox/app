/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/CaughtExceptionLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

/** A label used for reverse control-flow analysis from a catch clause. */
public class CaughtExceptionLabel extends ExceptionLabel {
  public final IRNode catchNode;
  public CaughtExceptionLabel(IRNode c) {
    catchNode = c;
  }
  public String toString() {
    return "catch exception";
  }
}
