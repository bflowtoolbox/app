/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/CallExceptionLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

/** The exception label for an exception not caught in a called
 * method that is rebroadcast in the callee method.  The call
 * node can be examined to see, for example, if a particular exception
 * could possibly arise from there.
 */
public class CallExceptionLabel extends ExceptionLabel {
  public final IRNode callNode;
  public CallExceptionLabel(IRNode node) {
    callNode = node;
  }
}
