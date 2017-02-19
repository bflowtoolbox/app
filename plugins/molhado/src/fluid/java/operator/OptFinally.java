// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\OptFinally.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.control.Component;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.JavaOperator;
public class OptFinally extends JavaOperator { 
  protected OptFinally() {}

  public static final OptFinally prototype = new OptFinally();


  public Component createComponent(IRNode node) {
    return new SimpleComponent(node);
  }
}
