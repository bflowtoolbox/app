// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\BinopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.ir.IRNode;
import fluid.tree.Operator;
public class BinopExpression extends Expression { 
  protected BinopExpression() {}

  public static final BinopExpression prototype = new BinopExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }


  public static IRNode getOp1(IRNode node) {
    if (!(tree.getOperator(node) instanceof BinopExpression)) {
      throw new IllegalArgumentException("node not BinopExpression");
    }
    return tree.getChild(node,0);
  }

  public Expression getOp1() {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getOp2(IRNode node) {
    if (!(tree.getOperator(node) instanceof BinopExpression)) {
      throw new IllegalArgumentException("node not BinopExpression");
    }
    return tree.getChild(node,1);
  }

  public Expression getOp2() {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }
}
