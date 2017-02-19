// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConstructionObject.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.tree.Operator;
/** The class of expressions legal to start a constructor call.
 * The only legal things are "this" or "super".
 */
public class ConstructionObject extends PrimaryExpression { 
  protected ConstructionObject() {}

  public static final ConstructionObject prototype = new ConstructionObject();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

}
