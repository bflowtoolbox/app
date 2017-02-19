// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\TypeDeclarations.op.  Do *NOT* edit!
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
public class TypeDeclarations extends JavaOperator implements DripOperator { 
  protected TypeDeclarations() {}

  public static final TypeDeclarations prototype = new TypeDeclarations();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return TypeDeclaration.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return TypeDeclaration.prototype;
  }

  public Operator variableOperator() {
    return TypeDeclaration.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] types) {
    return new JavaNode(prototype,types);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] types) {
    return new JavaNode(tree, prototype,types);
  }

  public static IRNode getTypes(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
    }
    return getTypes(tree, node, i);
  }

  public TypeDeclaration getTypes(int i) {
    return getTypes(tree, i);
  }

  public static Enumeration getTypesEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
    }
    return getTypesEnumeration(tree, node);
  }

  public Enumeration getTypesEnumeration() {
    return getTypesEnumeration(tree);
  }

  public static IRNode getTypes(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
    }
    return tree.getChild(node,i);
  }

  public TypeDeclaration getTypes(SyntaxTreeInterface tree, int i) {
    return (TypeDeclaration)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getTypesEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getTypesEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
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
    if (!(op instanceof TypeDeclarations)) {
      throw new IllegalArgumentException("node not TypeDeclarations: "+op);
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
