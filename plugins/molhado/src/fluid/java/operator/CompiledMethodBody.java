// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CompiledMethodBody.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
public class CompiledMethodBody extends OptMethodBody { 
  protected CompiledMethodBody() {}

  public static final CompiledMethodBody prototype = new CompiledMethodBody();

  public Operator superOperator() {
    return OptMethodBody.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return null;
  }

  public int numChildren() {
    return 0;
  }

  public static JavaNode createNode(Object code) {
    return createNode(tree, code);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, Object code) {
    JavaNode _result = new JavaNode(tree, prototype);
    JavaNode.setCode(_result,code);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getCode(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setCode(_result,JavaNode.getCode(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getCode(n1) == JavaNode.getCode(n2)) &&
    true;
  }

  public static Object getCode(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompiledMethodBody)) {
      throw new IllegalArgumentException("node not CompiledMethodBody: "+op);
    }
    return JavaNode.getCode(node);
  }

  public Object getCode() {
    return JavaNode.getCode(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompiledMethodBody)) {
      throw new IllegalArgumentException("node not CompiledMethodBody: "+op);
    }
    JavaNode.unparseCode(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof CompiledMethodBody)) {
      throw new IllegalArgumentException("node not CompiledMethodBody: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(new Keyword("<compiled>"));
    return TokenList;
  }

}
