// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\TypeExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
/** A static reference to a type.
 * This expression is legal in only a few situations:
 * <ul>
 *    <li> As the object for method calls.
 *    <li> As the object for field refs.
 * </ul>
 */
public class TypeExpression extends PrimaryExpression { 
  protected TypeExpression() {}

  public static final TypeExpression prototype = new TypeExpression();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode type) {
    return createNode(tree, type);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode type) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type});
    return _result;
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeExpression)) {
      throw new IllegalArgumentException("node not TypeExpression: "+op);
    }
    return getType(tree, node);
  }

  public Type getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeExpression)) {
      throw new IllegalArgumentException("node not TypeExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getType(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeExpression)) {
      throw new IllegalArgumentException("node not TypeExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeExpression)) {
      throw new IllegalArgumentException("node not TypeExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    return TokenList;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,0);
    
    ControlNode getType = new ComponentFlow(comp,null);
    
    ControlEdge.connect(comp.getEntryPort(),getType);
    ControlEdge.connect(getType,comp.getNormalExitPort());

    return comp;
  }
}
