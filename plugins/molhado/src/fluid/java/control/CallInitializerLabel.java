/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/CallInitializerLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.control.ControlLabel;
import fluid.ir.IRNode;
import fluid.java.DebugUnparser;

/** The label for a particular call to a class instance initializer.
 * The label is used to handle multiple uses of
 * the initializer in a class.
 * @see fluid.java.analysis.JavaTransfer#runClassInitializer
 */
public class CallInitializerLabel implements ControlLabel {
  public final IRNode callNode;
  public CallInitializerLabel(IRNode node) {
    callNode = node;
  }

  public boolean equals(Object x) {
    if (x instanceof CallInitializerLabel) {
      return callNode.equals(((CallInitializerLabel)x).callNode);
    }
    return false;
  }

  public String toString() {
    return DebugUnparser.toString(callNode);
  }
}
