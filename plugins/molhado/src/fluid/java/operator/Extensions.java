// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Extensions.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
/** Extends clause for interfaces.
 */
public class Extensions extends JavaOperator { 
  protected Extensions() {}

  public static final Extensions prototype = new Extensions();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return Type.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return Type.prototype;
  }

  public Operator variableOperator() {
    return Type.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] superInterface) {
    return new JavaNode(prototype,superInterface);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] superInterface) {
    return new JavaNode(tree, prototype,superInterface);
  }

  public static IRNode getSuperInterface(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    return getSuperInterface(tree, node, i);
  }

  public Type getSuperInterface(int i) {
    return getSuperInterface(tree, i);
  }

  public static Enumeration getSuperInterfaceEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    return getSuperInterfaceEnumeration(tree, node);
  }

  public Enumeration getSuperInterfaceEnumeration() {
    return getSuperInterfaceEnumeration(tree);
  }

  public static IRNode getSuperInterface(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    return tree.getChild(node,i);
  }

  public Type getSuperInterface(SyntaxTreeInterface tree, int i) {
    return (Type)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getSuperInterfaceEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getSuperInterfaceEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Keyword("extends");
  private static Token littoken2 = new Delim(",");

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    Enumeration e = tree.children(node);
    if (e.hasMoreElements()) {
      littoken1.emit(unparser,node);
      while (e.hasMoreElements()) {
        unparser.unparse((IRNode)e.nextElement());
        if (!e.hasMoreElements()) break;
        littoken2.emit(unparser,node);
      }
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    if (JavaNode.tree.numChildren(node) > 0) return true;
    else return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof Extensions)) {
      throw new IllegalArgumentException("node not Extensions: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    if (e.hasMoreElements()) {
      TokenList[i].add(littoken1);
      while (e.hasMoreElements()) {
        e.nextElement();
        i++;
        if (!e.hasMoreElements()) break;
        TokenList[i].add(littoken2);
      }
    }
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

}
