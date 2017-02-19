// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\DivRemExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
public class DivRemExpression extends ArithBinopExpression { 
  protected DivRemExpression() {}

  public static final DivRemExpression prototype = new DivRemExpression();

  public Operator superOperator() {
    return ArithBinopExpression.prototype;
  }


  public static IRNode getDivisor(IRNode node) {
    if (!prototype.includes(tree.getOperator(node))) {
      throw new IllegalArgumentException("node not a Div or Rem Expression");
    }
    return tree.getChild(node,1);
  }
  public Expression getDivisor() {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub1 =
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent sub2 =
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);

    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode testDivisor = new ComponentChoice(comp,null);
    ControlNode throwArithmeticException =
      new AddLabel(PrimitiveExceptionLabel.primitiveArithmeticException);

    ControlEdge.connect(comp.getEntryPort(),sub1.getEntryPort());
    ControlEdge.connect(sub1.getNormalExitPort(),sub2.getEntryPort());
    ControlEdge.connect(sub2.getNormalExitPort(),testDivisor);
    ControlEdge.connect(testDivisor,comp.getNormalExitPort());
    ControlEdge.connect(sub1.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(sub2.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(abruptMerge1,abruptMerge2);
    ControlEdge.connect(testDivisor,abruptMerge2);
    ControlEdge.connect(abruptMerge2,comp.getAbruptExitPort());

    return comp;
  }
}
