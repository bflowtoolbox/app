// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\PrimaryExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
public class PrimaryExpression extends Expression 
    implements StatementExpressionInterface 
    { 
  protected PrimaryExpression() {}

  public static final PrimaryExpression prototype = new PrimaryExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }

}
