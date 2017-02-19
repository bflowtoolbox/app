// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ClassExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ClassExpression extends Expression 
    { 
  protected ClassExpression() {}

  public static final ClassExpression prototype = new ClassExpression();

  public Operator superOperator() {
    return Expression.prototype;
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

  public static JavaNode createNode(IRNode type) {
    return createNode(tree, type);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode type) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type});
    return _result;
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassExpression)) {
      throw new IllegalArgumentException("node not ClassExpression: "+op);
    }
    return getType(tree, node);
  }

  public Expression getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassExpression)) {
      throw new IllegalArgumentException("node not ClassExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getType(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim(".");
  private static Token littoken2 = new Keyword("class");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassExpression)) {
      throw new IllegalArgumentException("node not ClassExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassExpression)) {
      throw new IllegalArgumentException("node not ClassExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    TokenList[1].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
