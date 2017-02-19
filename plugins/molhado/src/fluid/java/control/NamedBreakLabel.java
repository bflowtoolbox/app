/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/NamedBreakLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;

import fluid.ir.IRNode;

/** Break labels for names break statements and for labeled statements.
 */
public class NamedBreakLabel extends BreakLabel {
  public final IRNode breakNode;
  public NamedBreakLabel(IRNode bn) {
    breakNode = bn;
  }
  public String toString() {
    return "named break";
  }
}
