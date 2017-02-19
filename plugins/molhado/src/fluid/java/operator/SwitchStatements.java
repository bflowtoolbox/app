// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SwitchStatements.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.SequenceComponent;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class SwitchStatements extends JavaOperator { 
  protected SwitchStatements() {}

  public static final SwitchStatements prototype = new SwitchStatements();

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

  public static JavaNode createNode(IRNode[] stmts) {
    return new JavaNode(prototype,stmts);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] stmts) {
    return new JavaNode(tree, prototype,stmts);
  }

  public static IRNode getStmts(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    return getStmts(tree, node, i);
  }

  public Statement getStmts(int i) {
    return getStmts(tree, i);
  }

  public static Enumeration getStmtsEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    return getStmtsEnumeration(tree, node);
  }

  public Enumeration getStmtsEnumeration() {
    return getStmtsEnumeration(tree);
  }

  public static IRNode getStmts(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    return tree.getChild(node,i);
  }

  public Statement getStmts(SyntaxTreeInterface tree, int i) {
    return (Statement)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getStmtsEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getStmtsEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    Enumeration e = tree.children(node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    if (JavaNode.tree.numChildren(node) > 0) return true;
    else return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatements)) {
      throw new IllegalArgumentException("node not SwitchStatements: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    while (e.hasMoreElements()) {
      e.nextElement();
      i++;
    }
    return TokenList;
  }

  public Component createComponent(IRNode node) {
    return new SequenceComponent(node);
  }
}
