// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SynchronizedStatement.op.  Do *NOT* edit!
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
public class SynchronizedStatement extends Statement { 
  protected SynchronizedStatement() {}

  public static final SynchronizedStatement prototype = new SynchronizedStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return BlockStatement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode lock,
                                    IRNode block) {
    return createNode(tree, lock, block);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode lock,
                                    IRNode block) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{lock,block});
    return _result;
  }

  public static IRNode getLock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    return getLock(tree, node);
  }

  public Expression getLock() {
    return getLock(tree);
  }

  public static IRNode getLock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getLock(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getBlock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    return getBlock(tree, node);
  }

  public BlockStatement getBlock() {
    return getBlock(tree);
  }

  public static IRNode getBlock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public BlockStatement getBlock(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("synchronized");
  private static Token littoken2 = new Delim("(");
  private static Token littoken3 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken3.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof SynchronizedStatement)) {
      throw new IllegalArgumentException("node not SynchronizedStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    TokenList[0].add(littoken2);
    tree.getChild(node,0);
    TokenList[1].add(littoken3);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
