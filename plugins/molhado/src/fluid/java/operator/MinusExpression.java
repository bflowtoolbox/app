// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\MinusExpression.op.  Do *NOT* edit!
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
public class MinusExpression extends ArithUnopExpression 
    implements DripOperator 
    { 
  protected MinusExpression() {}

  public static final MinusExpression prototype = new MinusExpression();

  public Operator superOperator() {
    return ArithUnopExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode op) {
    return createNode(tree, op);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode op) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{op});
    return _result;
  }

  public static IRNode getOp(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MinusExpression)) {
      throw new IllegalArgumentException("node not MinusExpression: "+op);
    }
    return getOp(tree, node);
  }

  public Expression getOp() {
    return getOp(tree);
  }

  public static IRNode getOp(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MinusExpression)) {
      throw new IllegalArgumentException("node not MinusExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getOp(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim("-");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof MinusExpression)) {
      throw new IllegalArgumentException("node not MinusExpression: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof MinusExpression)) {
      throw new IllegalArgumentException("node not MinusExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
