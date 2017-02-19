// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SwitchLabel.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.ir.IRNode;
import fluid.java.JavaOperator;
public class SwitchLabel extends JavaOperator { 
  protected SwitchLabel() {}

  public static final SwitchLabel prototype = new SwitchLabel();


  /** Go up the tree and return the switch statement that includes this
   * label.  Returns null or throws UndefinedSlotException if not inside
   * a switch block.
   */
  public static IRNode getSwitchStatement(IRNode node) {
    while (node != null &&
	   !(tree.getOperator(node) instanceof SwitchStatement))
      node = tree.getParent(node);
    return node;
  }
}
