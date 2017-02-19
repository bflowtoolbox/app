/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/NamedContinueLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

public class NamedContinueLabel extends ContinueLabel {
  public final IRNode continueNode;
  public NamedContinueLabel(IRNode node) {
    continueNode = node;
  }
  public String toString() {
    return "named continue";
  }
}
