// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CastExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class CastExpression extends Expression { 
  protected CastExpression() {}

  public static final CastExpression prototype = new CastExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    case 1: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode type,
                                    IRNode expr) {
    return createNode(tree, type, expr);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode type,
                                    IRNode expr) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type,expr});
    return _result;
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    return getType(tree, node);
  }

  public Type getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getType(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getExpr(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    return getExpr(tree, node);
  }

  public Expression getExpr() {
    return getExpr(tree);
  }

  public static IRNode getExpr(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getExpr(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim("(");
  private static Token littoken2 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof CastExpression)) {
      throw new IllegalArgumentException("node not CastExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    TokenList[1].add(littoken2);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    ControlNode abruptMerge = new Merge();
    ControlNode throwClassCastException = 
      new AddLabel(PrimitiveExceptionLabel.primitiveClassCastException);
    ControlNode testOK = new ComponentChoice(comp,null);

    ControlEdge.connect(comp.getEntryPort(),sub.getEntryPort());
    ControlEdge.connect(sub.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(sub.getNormalExitPort(),testOK);
    ControlEdge.connect(testOK,comp.getNormalExitPort());
    ControlEdge.connect(testOK,throwClassCastException);
    ControlEdge.connect(throwClassCastException,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    
    return comp;
  }
}
