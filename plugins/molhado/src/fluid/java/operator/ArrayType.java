// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArrayType.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Token;
public class ArrayType extends JavaOperator implements ReferenceType { 
  protected ArrayType() {}

  public static final ArrayType prototype = new ArrayType();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode base,
                                    int dims) {
    return createNode(tree, base, dims);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode base,
                                    int dims) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{base});
    JavaNode.setDimInfo(_result,dims);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getDimInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setDimInfo(_result,JavaNode.getDimInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getDimInfo(n1) == JavaNode.getDimInfo(n2)) &&
    true;
  }

  public static IRNode getBase(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayType)) {
      throw new IllegalArgumentException("node not ArrayType: "+op);
    }
    return getBase(tree, node);
  }

  public Type getBase() {
    return getBase(tree);
  }

  public static IRNode getBase(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayType)) {
      throw new IllegalArgumentException("node not ArrayType: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getBase(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayType)) {
      throw new IllegalArgumentException("node not ArrayType: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getDims() {
    return JavaNode.getDimInfo(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayType)) {
      throw new IllegalArgumentException("node not ArrayType: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    JavaNode.unparseDimInfo(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayType)) {
      throw new IllegalArgumentException("node not ArrayType: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[1].add(tok);
    return TokenList;
  }

}
