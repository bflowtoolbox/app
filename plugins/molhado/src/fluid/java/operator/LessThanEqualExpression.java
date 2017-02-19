// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\LessThanEqualExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class LessThanEqualExpression extends CompareExpression 
    implements DripOperator 
    { 
  protected LessThanEqualExpression() {}

  public static final LessThanEqualExpression prototype = new LessThanEqualExpression();

  public Operator superOperator() {
    return CompareExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode expr1,
                                    IRNode expr2) {
    return createNode(tree, expr1, expr2);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode expr1,
                                    IRNode expr2) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{expr1,expr2});
    return _result;
  }

  public static IRNode getExpr1(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
    }
    return getExpr1(tree, node);
  }

  public Expression getExpr1() {
    return getExpr1(tree);
  }

  public static IRNode getExpr1(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getExpr1(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getExpr2(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
    }
    return getExpr2(tree, node);
  }

  public Expression getExpr2() {
    return getExpr2(tree);
  }

  public static IRNode getExpr2(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getExpr2(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim("<=");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
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
    if (!(op instanceof LessThanEqualExpression)) {
      throw new IllegalArgumentException("node not LessThanEqualExpression: "+op);
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

}
