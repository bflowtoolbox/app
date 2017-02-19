// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArrayDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class ArrayDeclaration extends JavaOperator implements ReferenceType { 
  protected ArrayDeclaration() {}

  public static final ArrayDeclaration prototype = new ArrayDeclaration();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return null;
  }

  public int numChildren() {
    return 0;
  }

  public static JavaNode createNode(IRNode base,
                                    int dims) {
    return createNode(tree, base, dims);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode base,
                                    int dims) {
    JavaNode _result = new JavaNode(tree, prototype);
    JavaNode.setConstantNode(_result,base);
    JavaNode.setConstantInt(_result,dims);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getConstantNode(node);
      JavaNode.getConstantInt(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setConstantNode(_result,JavaNode.getConstantNode(node));
    JavaNode.setConstantInt(_result,JavaNode.getConstantInt(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getConstantNode(n1) == JavaNode.getConstantNode(n2)) &&
           (JavaNode.getConstantInt(n1) == JavaNode.getConstantInt(n2)) &&
    true;
  }

  public static IRNode getBase(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayDeclaration)) {
      throw new IllegalArgumentException("node not ArrayDeclaration: "+op);
    }
    return JavaNode.getConstantNode(node);
  }

  public IRNode getBase() {
    return JavaNode.getConstantNode(baseNode);
  }

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayDeclaration)) {
      throw new IllegalArgumentException("node not ArrayDeclaration: "+op);
    }
    return JavaNode.getConstantInt(node);
  }

  public int getDims() {
    return JavaNode.getConstantInt(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayDeclaration)) {
      throw new IllegalArgumentException("node not ArrayDeclaration: "+op);
    }
    JavaNode.unparseConstantNode(node,unparser);
    JavaNode.unparseConstantInt(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayDeclaration)) {
      throw new IllegalArgumentException("node not ArrayDeclaration: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
            return TokenList;
  }

}
