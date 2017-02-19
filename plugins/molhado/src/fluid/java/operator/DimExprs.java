// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\DimExprs.op.  Do *NOT* edit!
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
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class DimExprs extends JavaOperator { 
  protected DimExprs() {}

  public static final DimExprs prototype = new DimExprs();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return Expression.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return Expression.prototype;
  }

  public Operator variableOperator() {
    return Expression.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] size) {
    return new JavaNode(prototype,size);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] size) {
    return new JavaNode(tree, prototype,size);
  }

  public static IRNode getSize(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    return getSize(tree, node, i);
  }

  public Expression getSize(int i) {
    return getSize(tree, i);
  }

  public static Enumeration getSizeEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    return getSizeEnumeration(tree, node);
  }

  public Enumeration getSizeEnumeration() {
    return getSizeEnumeration(tree);
  }

  public static IRNode getSize(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    return tree.getChild(node,i);
  }

  public Expression getSize(SyntaxTreeInterface tree, int i) {
    return (Expression)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getSizeEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getSizeEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("[");
  private static Token littoken2 = new Delim("]");

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    Enumeration e = tree.children(node);
    while (e.hasMoreElements()) {
      littoken1.emit(unparser,node);
      unparser.getStyle().getNONE().emit(unparser,node);
      unparser.unparse((IRNode)e.nextElement());
      unparser.getStyle().getNONE().emit(unparser,node);
      littoken2.emit(unparser,node);
      unparser.getStyle().getNONE().emit(unparser,node);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    if (JavaNode.tree.numChildren(node) > 0) return true;
    else return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof DimExprs)) {
      throw new IllegalArgumentException("node not DimExprs: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    while (e.hasMoreElements()) {
      TokenList[i].add(littoken1);
      e.nextElement();
      i++;
      TokenList[i].add(littoken2);
    }
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    return new SequenceComponent(node);
  }
}
