// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConstantLabel.op.  Do *NOT* edit!
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
public class ConstantLabel extends SwitchLabel { 
  protected ConstantLabel() {}

  public static final ConstantLabel prototype = new ConstantLabel();

  public Operator superOperator() {
    return SwitchLabel.prototype;
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

  public static JavaNode createNode(IRNode expr) {
    return createNode(tree, expr);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode expr) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{expr});
    return _result;
  }

  public static IRNode getExpr(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstantLabel)) {
      throw new IllegalArgumentException("node not ConstantLabel: "+op);
    }
    return getExpr(tree, node);
  }

  public Expression getExpr() {
    return getExpr(tree);
  }

  public static IRNode getExpr(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstantLabel)) {
      throw new IllegalArgumentException("node not ConstantLabel: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getExpr(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Keyword("case");
  private static Token littoken2 = new Delim(":");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstantLabel)) {
      throw new IllegalArgumentException("node not ConstantLabel: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstantLabel)) {
      throw new IllegalArgumentException("node not ConstantLabel: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    TokenList[1].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
