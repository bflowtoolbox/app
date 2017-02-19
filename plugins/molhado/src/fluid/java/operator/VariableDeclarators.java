// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\VariableDeclarators.op.  Do *NOT* edit!
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
public class VariableDeclarators extends JavaOperator implements DripOperator { 
  protected VariableDeclarators() {}

  public static final VariableDeclarators prototype = new VariableDeclarators();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return VariableDeclarator.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return VariableDeclarator.prototype;
  }

  public Operator variableOperator() {
    return VariableDeclarator.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] var) {
    return new JavaNode(prototype,var);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] var) {
    return new JavaNode(tree, prototype,var);
  }

  public static IRNode getVar(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    return getVar(tree, node, i);
  }

  public VariableDeclarator getVar(int i) {
    return getVar(tree, i);
  }

  public static Enumeration getVarEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    return getVarEnumeration(tree, node);
  }

  public Enumeration getVarEnumeration() {
    return getVarEnumeration(tree);
  }

  public static IRNode getVar(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    return tree.getChild(node,i);
  }

  public VariableDeclarator getVar(SyntaxTreeInterface tree, int i) {
    return (VariableDeclarator)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getVarEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getVarEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim(",");
  private static Token littoken2 = new Delim("<missing>");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    Enumeration e = tree.children(node);
    if (e.hasMoreElements()) {
      while (e.hasMoreElements()) {
        unparser.unparse((IRNode)e.nextElement());
        if (!e.hasMoreElements()) break;
        unparser.getStyle().getNONE().emit(unparser,node);
        littoken1.emit(unparser,node);
      }
    } else {
      littoken2.emit(unparser,node);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarators)) {
      throw new IllegalArgumentException("node not VariableDeclarators: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    if (e.hasMoreElements()) {
      while (e.hasMoreElements()) {
        e.nextElement();
        i++;
        if (!e.hasMoreElements()) break;
        TokenList[i].add(littoken1);
      }
    } else {
      TokenList[i].add(littoken2);
    }
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  /** Create a component for this node.
   * Execute the declarators in succession.
   */
  public Component createComponent(IRNode node) {
    return new SequenceComponent(node);
  }
}
