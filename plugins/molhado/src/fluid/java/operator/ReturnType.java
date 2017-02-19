// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ReturnType.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.java.JavaOperator;
import fluid.tree.Operator;
public class ReturnType extends JavaOperator implements ReturnTypeInterface { 
  protected ReturnType() {}

  public static final ReturnType prototype = new ReturnType();


  /** Use the interface to determine what operators are legal:
   */
  public boolean includes(Operator other) {
    return (other instanceof ReturnTypeInterface);
  }
}
