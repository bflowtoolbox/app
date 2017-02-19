// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\LogBinopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
public class LogBinopExpression extends BinopExpression 
    implements LogExpression 
    { 
  protected LogBinopExpression() {}

  public static final LogBinopExpression prototype = new LogBinopExpression();

  public Operator superOperator() {
    return BinopExpression.prototype;
  }

}
