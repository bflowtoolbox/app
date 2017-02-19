// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArithBinopExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
public class ArithBinopExpression extends BinopExpression implements ArithExpression { 
  protected ArithBinopExpression() {}

  public static final ArithBinopExpression prototype = new ArithBinopExpression();

  public Operator superOperator() {
    return BinopExpression.prototype;
  }

}
