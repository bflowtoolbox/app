// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\BlockStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class BlockStatement extends Statement implements DripOperator { 
  protected BlockStatement() {}

  public static final BlockStatement prototype = new BlockStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return Statement.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return Statement.prototype;
  }

  public Operator variableOperator() {
    return Statement.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] stmt) {
    return new JavaNode(prototype,stmt);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] stmt) {
    return new JavaNode(tree, prototype,stmt);
  }

  public static IRNode getStmt(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    return getStmt(tree, node, i);
  }

  public Statement getStmt(int i) {
    return getStmt(tree, i);
  }

  public static Enumeration getStmtEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    return getStmtEnumeration(tree, node);
  }

  public Enumeration getStmtEnumeration() {
    return getStmtEnumeration(tree);
  }

  public static IRNode getStmt(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    return tree.getChild(node,i);
  }

  public Statement getStmt(SyntaxTreeInterface tree, int i) {
    return (Statement)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getStmtEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getStmtEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("{");
  private static Token littoken2 = new Delim("}");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    Enumeration e = tree.children(node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getBLOCK().emit(unparser,node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      if (!e.hasMoreElements()) break;
      unparser.getStyle().getLI().emit(unparser,node);
    }
    unparser.getStyle().getENDBLOCK().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof BlockStatement)) {
      throw new IllegalArgumentException("node not BlockStatement: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[i].add(littoken1);
    while (e.hasMoreElements()) {
      e.nextElement();
      i++;
      if (!e.hasMoreElements()) break;
    }
    TokenList[i].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
