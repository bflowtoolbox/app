// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Initializer.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.SequenceComponent;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.JavaOperator;
/** The class of variable initializers (including all expressions).
 * By default, any subexpressions are executed in order.
 * @see Expression
 */
public class Initializer 
    extends JavaOperator { 
  protected Initializer() {}

  public static final Initializer prototype = new Initializer();


  /** Create a component for this node.  By default we just
   * execute the children in succession and then have some
   * operator specific action:
   */
  public Component createComponent(IRNode node) {
    if (variableOperator() != null) {
      return new SequenceComponent(node, new ComponentFlow(null,this));
    } else {
      return new SimpleComponent(node, new ComponentFlow(null,this));
    }
  }
}
