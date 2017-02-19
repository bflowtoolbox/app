// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\NoInitialization.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class NoInitialization extends OptInitialization 
    implements DripOperator 
    { 
  protected NoInitialization() {}

  public static final NoInitialization prototype = new NoInitialization();

  public Operator superOperator() {
    return OptInitialization.prototype;
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
    if (!(op instanceof NoInitialization)) {
      throw new IllegalArgumentException("node not NoInitialization: "+op);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof NoInitialization)) {
      throw new IllegalArgumentException("node not NoInitialization: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    return TokenList;
  }


  public Component createComponent(IRNode node) {
    return new SimpleComponent(node, new ComponentFlow(null,this));
  }
}
