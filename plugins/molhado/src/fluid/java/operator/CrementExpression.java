// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CrementExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.tree.Operator;
/** Prefix and Postfix Increment and Decrement expressions.
 */
public class CrementExpression extends ArithUnopExpression 
    implements Assignment 
    { 
  protected CrementExpression() {}

  public static final CrementExpression prototype = new CrementExpression();

  public Operator superOperator() {
    return ArithUnopExpression.prototype;
  }


  /** Return the variable being modified. */
  public IRNode getTarget(IRNode node) {
    return tree.getChild(node,0);
  }

  /** Return the value to assign to it.
   * <bf>This does not mean quite what it should for post*crement!</bf>
   */
  public IRNode getSource(IRNode node) {
    return node; // i.e. without side-effects
  }

  /** Return whether this is a post-inc/dec-rement or not. */
  public boolean isPost() { return !isPrefix(); }

  /** Return basic operator (a pre operation) */
  public Operator baseOp() { return this; }
  
  /** Create the control-flow graph component for
   *  a pre/post-inc/dec-rement expression.
   * Evaluate argument (while saving LHS subvalues for later assignment),
   * then increment or decrement,
   * then assign back to variable,
   * then (for post-inc/dec-rement) decrement or increment
   */
  public Component createComponent(IRNode node) {
    Operator op = tree.getOperator(node);
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = 
        new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    ControlNode doOperation = new ComponentFlow(comp,baseOp());
    ControlNode doAssign = new ComponentFlow(comp,null); // must be null
    ControlNode doAdjust;
    if (isPost()) {
      Operator revOp = (op instanceof PostIncrementExpression) ?
	(Operator)PreDecrementExpression.prototype :
	(Operator)PreIncrementExpression.prototype;
      doAdjust = new ComponentFlow(comp,revOp);
    } else {
      doAdjust = null;
    }

    // normal control flow:
    ControlEdge.connect(comp.getEntryPort(),sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),doOperation);
    ControlEdge.connect(doOperation,doAssign);
    if (isPost()) {
      ControlEdge.connect(doAssign,doAdjust);
      ControlEdge.connect(doAdjust,comp.getNormalExitPort());
    } else {
      ControlEdge.connect(doAssign,comp.getNormalExitPort());
    }

    // abrupt flow:
    // no exceptions unless the first use dies
    ControlEdge.connect(sub.getAbruptExitPort(),comp.getAbruptExitPort());
    
    return comp;
  }  
}
