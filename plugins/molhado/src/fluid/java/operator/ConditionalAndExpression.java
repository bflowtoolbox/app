// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConditionalAndExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ConditionalAndExpression extends BinopExpression 
    implements DripOperator 
    { 
  protected ConditionalAndExpression() {}

  public static final ConditionalAndExpression prototype = new ConditionalAndExpression();

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

  public static JavaNode createNode(IRNode cond1,
                                    IRNode cond2) {
    return createNode(tree, cond1, cond2);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode cond1,
                                    IRNode cond2) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{cond1,cond2});
    return _result;
  }

  public static IRNode getCond1(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    return getCond1(tree, node);
  }

  public Expression getCond1() {
    return getCond1(tree);
  }

  public static IRNode getCond1(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getCond1(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getCond2(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    return getCond2(tree, node);
  }

  public Expression getCond2() {
    return getCond2(tree);
  }

  public static IRNode getCond2(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getCond2(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim("&&");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConditionalAndExpression)) {
      throw new IllegalArgumentException("node not ConditionalAndExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,2,1);
    Subcomponent sub0 = 
        new Subcomponent(comp,tree.childLocation(node,0),1,2,1);
    Subcomponent sub1 = 
        new Subcomponent(comp,tree.childLocation(node,1),1,2,1);
    ControlNode falseMerge = new Merge();
    ControlNode abruptMerge = new Merge();
    ControlNode discardTrue = new ComponentFlow(comp,this);
    // connect through (first edge on a boolean port is the true edge).
    ControlEdge.connect(comp.getEntryPort(),sub0.getEntryPort());
    ControlEdge.connect(sub0.getNormalExitPort(),discardTrue);
    ControlEdge.connect(discardTrue,sub1.getEntryPort());
    ControlEdge.connect(sub1.getNormalExitPort(),comp.getNormalExitPort());
    // connect false fall throughs
    ControlEdge.connect(sub0.getNormalExitPort(),falseMerge);
    ControlEdge.connect(sub1.getNormalExitPort(),falseMerge);
    ControlEdge.connect(falseMerge,comp.getNormalExitPort());
    // connect abrupt exits
    ControlEdge.connect(sub0.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(sub1.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    return comp;
  }
}
