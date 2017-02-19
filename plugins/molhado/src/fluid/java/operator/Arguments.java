// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Arguments.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.SequenceComponent;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class Arguments extends JavaOperator implements DripOperator { 
  protected Arguments() {}

  public static final Arguments prototype = new Arguments();

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

  public static JavaNode createNode(IRNode[] arg) {
    return new JavaNode(prototype,arg);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] arg) {
    return new JavaNode(tree, prototype,arg);
  }

  public static IRNode getArg(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
    }
    return getArg(tree, node, i);
  }

  public Expression getArg(int i) {
    return getArg(tree, i);
  }

  public static Enumeration getArgEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
    }
    return getArgEnumeration(tree, node);
  }

  public Enumeration getArgEnumeration() {
    return getArgEnumeration(tree);
  }

  public static IRNode getArg(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
    }
    return tree.getChild(node,i);
  }

  public Expression getArg(SyntaxTreeInterface tree, int i) {
    return (Expression)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getArgEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getArgEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("(");
  private static Token littoken2 = new Delim(",");
  private static Token littoken3 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
    }
    Enumeration e = tree.children(node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      if (!e.hasMoreElements()) break;
      unparser.getStyle().getNONE().emit(unparser,node);
      littoken2.emit(unparser,node);
      unparser.getStyle().getCOMMA().emit(unparser,node);
    }
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken3.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof Arguments)) {
      throw new IllegalArgumentException("node not Arguments: "+op);
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
      TokenList[i].add(littoken2);
    }
    TokenList[i].add(littoken3);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    return new SequenceComponent(node);
  }
}
