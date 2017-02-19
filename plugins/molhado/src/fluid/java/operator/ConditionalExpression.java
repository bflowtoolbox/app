// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConditionalExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ConditionalExpression extends Expression { 
  protected ConditionalExpression() {}

  public static final ConditionalExpression prototype = new ConditionalExpression();

  public Operator superOperator() {
    return Expression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Expression.prototype;
    case 2: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(IRNode cond,
                                    IRNode iftrue,
                                    IRNode iffalse) {
    return createNode(tree, cond, iftrue, iffalse);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode cond,
                                    IRNode iftrue,
                                    IRNode iffalse) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{cond,iftrue,iffalse});
    return _result;
  }

  public static IRNode getCond(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return getCond(tree, node);
  }

  public Expression getCond() {
    return getCond(tree);
  }

  public static IRNode getCond(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getCond(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getIftrue(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return getIftrue(tree, node);
  }

  public Expression getIftrue() {
    return getIftrue(tree);
  }

  public static IRNode getIftrue(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getIftrue(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getIffalse(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return getIffalse(tree, node);
  }

  public Expression getIffalse() {
    return getIffalse(tree);
  }

  public static IRNode getIffalse(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    return tree.getChild(node,2);
  }

  public Expression getIffalse(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,2));
  }

  private static Token littoken1 = new Delim("?");
  private static Token littoken2 = new Delim(":");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    littoken2.emit(unparser,node);
    unparser.unparse(tree.getChild(node,2));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalExpression)) {
      throw new IllegalArgumentException("node not ConditionalExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    TokenList[2].add(littoken2);
    tree.getChild(node,2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    // We can reuse the structure created for if statements:
    return IfElseStatement.prototype.createComponent(node);
  }
}
