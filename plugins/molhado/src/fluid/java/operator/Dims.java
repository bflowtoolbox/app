// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Dims.op.  Do *NOT* edit!
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
public class Dims extends JavaOperator { 
  protected Dims() {}

  public static final Dims prototype = new Dims();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return null;
  }

  public int numChildren() {
    return 0;
  }

  public static JavaNode createNode(int dims) {
    return createNode(tree, dims);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int dims) {
    JavaNode _result = new JavaNode(tree, prototype);
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

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Dims)) {
      throw new IllegalArgumentException("node not Dims: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getDims() {
    return JavaNode.getDimInfo(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Dims)) {
      throw new IllegalArgumentException("node not Dims: "+op);
    }
    JavaNode.unparseDimInfo(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof Dims)) {
      throw new IllegalArgumentException("node not Dims: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[0].add(tok);
    return TokenList;
  }

}
