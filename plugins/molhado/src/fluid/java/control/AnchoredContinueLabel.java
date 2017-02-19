/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/AnchoredContinueLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

public class AnchoredContinueLabel extends ContinueLabel {
  public final IRNode stmtNode; // a loop node
  public AnchoredContinueLabel(IRNode node) {
    stmtNode = node;
  }
  public String toString() {
    return "anchored continue";
  }
}
