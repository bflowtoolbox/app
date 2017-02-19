// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\InstanceOfExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class InstanceOfExpression extends Expression { 
  protected InstanceOfExpression() {}

  public static final InstanceOfExpression prototype = new InstanceOfExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Type.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode value,
                                    IRNode type) {
    return createNode(tree, value, type);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode value,
                                    IRNode type) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{value,type});
    return _result;
  }

  public static IRNode getValue(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    return getValue(tree, node);
  }

  public Expression getValue() {
    return getValue(tree);
  }

  public static IRNode getValue(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getValue(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    return getType(tree, node);
  }

  public Type getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Type getType(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("instanceof");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof InstanceOfExpression)) {
      throw new IllegalArgumentException("node not InstanceOfExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,2,1);
    Subcomponent obj = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);

    ControlNode doTest = new ComponentChoice(comp,null);

    ControlEdge.connect(comp.getEntryPort(),obj.getEntryPort());
    ControlEdge.connect(obj.getNormalExitPort(),doTest);
    ControlEdge.connect(doTest,comp.getNormalExitPort()); // true part
    ControlEdge.connect(doTest,comp.getNormalExitPort()); // false part
    ControlEdge.connect(obj.getAbruptExitPort(),comp.getAbruptExitPort());

    return comp;
  }
}
