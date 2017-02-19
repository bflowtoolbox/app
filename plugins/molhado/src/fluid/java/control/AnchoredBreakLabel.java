/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/AnchoredBreakLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

public class AnchoredBreakLabel extends BreakLabel {
  public final IRNode stmtNode; // a loop or switch statement
  public AnchoredBreakLabel(IRNode n) {
    stmtNode = n;
  }
  public String toString() {
    return "anchored break";
  }
}
