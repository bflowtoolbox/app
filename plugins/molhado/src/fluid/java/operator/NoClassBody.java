// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\NoClassBody.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class NoClassBody extends OptClassBody { 
  protected NoClassBody() {}

  public static final NoClassBody prototype = new NoClassBody();

  public Operator superOperator() {
    return OptClassBody.prototype;
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

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof NoClassBody)) {
      throw new IllegalArgumentException("node not NoClassBody: "+op);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof NoClassBody)) {
      throw new IllegalArgumentException("node not NoClassBody: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    return TokenList;
  }

}
