// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Finally.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class Finally extends OptFinally { 
  protected Finally() {}

  public static final Finally prototype = new Finally();

  public Operator superOperator() {
    return OptFinally.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return BlockStatement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode body) {
    return createNode(tree, body);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode body) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{body});
    return _result;
  }

  public static IRNode getBody(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Finally)) {
      throw new IllegalArgumentException("node not Finally: "+op);
    }
    return getBody(tree, node);
  }

  public BlockStatement getBody() {
    return getBody(tree);
  }

  public static IRNode getBody(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Finally)) {
      throw new IllegalArgumentException("node not Finally: "+op);
    }
    return tree.getChild(node,0);
  }

  public BlockStatement getBody(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Keyword("finally");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Finally)) {
      throw new IllegalArgumentException("node not Finally: "+op);
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
    if (!(op instanceof Finally)) {
      throw new IllegalArgumentException("node not Finally: "+op);
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
