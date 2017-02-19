// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ExprStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ExprStatement extends Statement implements DripOperator { 
  protected ExprStatement() {}

  public static final ExprStatement prototype = new ExprStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return StatementExpression.prototype;
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
    if (!(op instanceof ExprStatement)) {
      throw new IllegalArgumentException("node not ExprStatement: "+op);
    }
    return getExpr(tree, node);
  }

  public StatementExpressionInterface getExpr() {
    return getExpr(tree);
  }

  public static IRNode getExpr(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ExprStatement)) {
      throw new IllegalArgumentException("node not ExprStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public StatementExpressionInterface getExpr(SyntaxTreeInterface tree) {
    return (StatementExpressionInterface)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ExprStatement)) {
      throw new IllegalArgumentException("node not ExprStatement: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ExprStatement)) {
      throw new IllegalArgumentException("node not ExprStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    return new SimpleComponent(node,new ComponentFlow(null,this));
  }
}
