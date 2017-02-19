// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\LogUnopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
public class LogUnopExpression extends UnopExpression 
    implements LogExpression 
    { 
  protected LogUnopExpression() {}

  public static final LogUnopExpression prototype = new LogUnopExpression();

  public Operator superOperator() {
    return UnopExpression.prototype;
  }

}
