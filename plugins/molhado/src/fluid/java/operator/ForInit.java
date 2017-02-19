// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ForInit.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.java.JavaOperator;
import fluid.tree.Operator;
public class ForInit extends JavaOperator implements ForInitInterface { 
  protected ForInit() {}

  public static final ForInit prototype = new ForInit();


  public boolean includes(Operator op) {
    return op instanceof ForInitInterface;
  }
}
