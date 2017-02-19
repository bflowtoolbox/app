// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\MethodBody.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.LabelTest;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.ReturnLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class MethodBody extends OptMethodBody 
    implements DripOperator 
    { 
  protected MethodBody() {}

  public static final MethodBody prototype = new MethodBody();

  public Operator superOperator() {
    return OptMethodBody.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return BlockStatement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode block) {
    return createNode(tree, block);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode block) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{block});
    return _result;
  }

  public static IRNode getBlock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodBody)) {
      throw new IllegalArgumentException("node not MethodBody: "+op);
    }
    return getBlock(tree, node);
  }

  public BlockStatement getBlock() {
    return getBlock(tree);
  }

  public static IRNode getBlock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodBody)) {
      throw new IllegalArgumentException("node not MethodBody: "+op);
    }
    return tree.getChild(node,0);
  }

  public BlockStatement getBlock(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,0));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodBody)) {
      throw new IllegalArgumentException("node not MethodBody: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodBody)) {
      throw new IllegalArgumentException("node not MethodBody: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    return TokenList;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    ControlNode doInit = new ComponentFlow(comp,comp.getEntryPort());
    ControlNode returnTest = new LabelTest(comp,ReturnLabel.prototype);
    ControlNode returnMerge = new Merge();
    ControlNode doReturn = new ComponentFlow(comp,comp.getNormalExitPort());
    ControlNode doThrow = new ComponentFlow(comp,comp.getAbruptExitPort());

    ControlEdge.connect(comp.getEntryPort(),doInit);
    ControlEdge.connect(doInit,sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),returnMerge);
    ControlEdge.connect(returnMerge,doReturn);
    ControlEdge.connect(doReturn,comp.getNormalExitPort());
    ControlEdge.connect(sub.getAbruptExitPort(),returnTest);
    ControlEdge.connect(returnTest,returnMerge);
    ControlEdge.connect(returnTest,doThrow);
    ControlEdge.connect(doThrow,comp.getAbruptExitPort());
    return comp;
  }
}
