// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\VariableDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.tree.Operator;
public class VariableDeclaration extends Declaration { 
  protected VariableDeclaration() {}

  public static final VariableDeclaration prototype = new VariableDeclaration();

  public Operator superOperator() {
    return Declaration.prototype;
  }


  public static String getId(IRNode node) {
    if (tree.getOperator(node) instanceof VariableDeclaration) {
      return JavaNode.getInfo(node);
    } else {
      throw new IllegalArgumentException("Not a VariableDeclaration " +
					 tree.getOperator(node));
    }
  }
}
