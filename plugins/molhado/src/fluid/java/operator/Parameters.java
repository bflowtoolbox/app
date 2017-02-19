// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Parameters.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

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
public class Parameters extends JavaOperator implements DripOperator { 
  protected Parameters() {}

  public static final Parameters prototype = new Parameters();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return ParameterDeclaration.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return ParameterDeclaration.prototype;
  }

  public Operator variableOperator() {
    return ParameterDeclaration.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] formal) {
    return new JavaNode(prototype,formal);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] formal) {
    return new JavaNode(tree, prototype,formal);
  }

  public static IRNode getFormal(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
    }
    return getFormal(tree, node, i);
  }

  public ParameterDeclaration getFormal(int i) {
    return getFormal(tree, i);
  }

  public static Enumeration getFormalEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
    }
    return getFormalEnumeration(tree, node);
  }

  public Enumeration getFormalEnumeration() {
    return getFormalEnumeration(tree);
  }

  public static IRNode getFormal(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
    }
    return tree.getChild(node,i);
  }

  public ParameterDeclaration getFormal(SyntaxTreeInterface tree, int i) {
    return (ParameterDeclaration)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getFormalEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getFormalEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("(");
  private static Token littoken2 = new Delim(",");
  private static Token littoken3 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
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
    if (!(op instanceof Parameters)) {
      throw new IllegalArgumentException("node not Parameters: "+op);
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

}
