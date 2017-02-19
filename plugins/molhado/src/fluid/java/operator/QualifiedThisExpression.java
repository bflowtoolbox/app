// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\QualifiedThisExpression.op.  Do *NOT* edit!
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
public class QualifiedThisExpression extends PrimaryExpression 
    { 
  protected QualifiedThisExpression() {}

  public static final QualifiedThisExpression prototype = new QualifiedThisExpression();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return TypeExpression.prototype;
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
    if (!(op instanceof QualifiedThisExpression)) {
      throw new IllegalArgumentException("node not QualifiedThisExpression: "+op);
    }
    return getType(tree, node);
  }

  public TypeExpression getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedThisExpression)) {
      throw new IllegalArgumentException("node not QualifiedThisExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public TypeExpression getType(SyntaxTreeInterface tree) {
    return (TypeExpression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim(".");
  private static Token littoken2 = new Keyword("this");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedThisExpression)) {
      throw new IllegalArgumentException("node not QualifiedThisExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedThisExpression)) {
      throw new IllegalArgumentException("node not QualifiedThisExpression: "+op);
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
