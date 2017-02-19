// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\UnopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.ir.IRNode;
import fluid.tree.Operator;
public class UnopExpression extends Expression { 
  protected UnopExpression() {}

  public static final UnopExpression prototype = new UnopExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }


  public static IRNode getOp(IRNode node) {
    if (!(tree.getOperator(node) instanceof UnopExpression)) {
      throw new IllegalArgumentException("node not UnopExpression");
    }
    return tree.getChild(node,0);
  }

  public Expression getOp() {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }
  public boolean isPrefix() {
    return true;
  }
  public int childPrecedence(int i) {
    return unopPrec;
  }
}
