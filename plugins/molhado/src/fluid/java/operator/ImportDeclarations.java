// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ImportDeclarations.op.  Do *NOT* edit!
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
public class ImportDeclarations extends JavaOperator implements DripOperator { 
  protected ImportDeclarations() {}

  public static final ImportDeclarations prototype = new ImportDeclarations();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return ImportDeclaration.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return ImportDeclaration.prototype;
  }

  public Operator variableOperator() {
    return ImportDeclaration.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] imps) {
    return new JavaNode(prototype,imps);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] imps) {
    return new JavaNode(tree, prototype,imps);
  }

  public static IRNode getImps(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
    }
    return getImps(tree, node, i);
  }

  public ImportDeclaration getImps(int i) {
    return getImps(tree, i);
  }

  public static Enumeration getImpsEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
    }
    return getImpsEnumeration(tree, node);
  }

  public Enumeration getImpsEnumeration() {
    return getImpsEnumeration(tree);
  }

  public static IRNode getImps(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
    }
    return tree.getChild(node,i);
  }

  public ImportDeclaration getImps(SyntaxTreeInterface tree, int i) {
    return (ImportDeclaration)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getImpsEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getImpsEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
    }
    Enumeration e = tree.children(node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      unparser.getStyle().getBR().emit(unparser,node);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    if (JavaNode.tree.numChildren(node) > 0) return true;
    else return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ImportDeclarations)) {
      throw new IllegalArgumentException("node not ImportDeclarations: "+op);
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

}
