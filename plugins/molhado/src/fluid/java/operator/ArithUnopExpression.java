// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArithUnopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
public class ArithUnopExpression extends UnopExpression 
    implements ArithExpression 
    { 
  protected ArithUnopExpression() {}

  public static final ArithUnopExpression prototype = new ArithUnopExpression();

  public Operator superOperator() {
    return UnopExpression.prototype;
  }

}
