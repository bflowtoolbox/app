// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\OpAssignExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class OpAssignExpression extends BinopExpression 
    implements Assignment 
    { 
  protected OpAssignExpression() {}

  public static final OpAssignExpression prototype = new OpAssignExpression();

  public Operator superOperator() {
    return BinopExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode lhs,
                                    JavaOperator op,
                                    IRNode rhs) {
    return createNode(tree, lhs, op, rhs);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode lhs,
                                    JavaOperator op,
                                    IRNode rhs) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{lhs,rhs});
    JavaNode.setOp(_result,op);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getOp(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setOp(_result,JavaNode.getOp(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getOp(n1) == JavaNode.getOp(n2)) &&
    true;
  }

  public static IRNode getLhs(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    return getLhs(tree, node);
  }

  public Expression getLhs() {
    return getLhs(tree);
  }

  public static IRNode getLhs(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getLhs(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static JavaOperator getOp(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    return JavaNode.getOp(node);
  }

  public JavaOperator getOp() {
    return JavaNode.getOp(baseNode);
  }

  public static IRNode getRhs(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    return getRhs(tree, node);
  }

  public Expression getRhs() {
    return getRhs(tree);
  }

  public static IRNode getRhs(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getRhs(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim("=");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    JavaNode.unparseOp(node,unparser);
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof OpAssignExpression)) {
      throw new IllegalArgumentException("node not OpAssignExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(JavaNode.getOp(node).asToken());
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public IRNode getSource(IRNode node) {
    return node; // that is, compute while ignoring assignment aspect
  }
  public IRNode getTarget(IRNode node) {
    return getLhs(node);
  }

  /** Create the control-flow graph component for
   *  an op assignment expression.
   */
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sublhs = 
        new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subrhs = 
        new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    ControlNode doOperation = new ComponentChoice(comp,Boolean.TRUE);
    ControlNode throwArithmeticException =
      new AddLabel(PrimitiveExceptionLabel.primitiveArithmeticException);
    ControlNode doAssign = new ComponentChoice(comp,null); // must be null
    ControlNode throwArrayStoreException =
      new AddLabel(PrimitiveExceptionLabel.primitiveArrayStoreException);
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode abruptMerge3 = new Merge();

    // normal control flow:
    ControlEdge.connect(comp.getEntryPort(),sublhs.getEntryPort());
    ControlEdge.connect(sublhs.getNormalExitPort(),subrhs.getEntryPort());
    ControlEdge.connect(subrhs.getNormalExitPort(),doOperation);
    ControlEdge.connect(doOperation,doAssign);
    ControlEdge.connect(doAssign,comp.getNormalExitPort());

    // connect up all possible abrupt edges.
    ControlEdge.connect(sublhs.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subrhs.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(doOperation,throwArithmeticException);
    ControlEdge.connect(throwArithmeticException,abruptMerge2);
    ControlEdge.connect(doAssign,throwArrayStoreException);
    ControlEdge.connect(throwArrayStoreException,abruptMerge2);
    ControlEdge.connect(abruptMerge1,abruptMerge3);
    ControlEdge.connect(abruptMerge2,abruptMerge3);
    ControlEdge.connect(abruptMerge3,comp.getAbruptExitPort());
    
    return comp;
  }  
}
