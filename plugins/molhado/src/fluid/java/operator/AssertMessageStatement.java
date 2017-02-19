// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\AssertMessageStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class AssertMessageStatement extends Statement implements DripOperator { 
  protected AssertMessageStatement() {}

  public static final AssertMessageStatement prototype = new AssertMessageStatement();

  public Operator superOperator() {
    return Statement.prototype;
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

  public static JavaNode createNode(IRNode assertion,
                                    IRNode message) {
    return createNode(tree, assertion, message);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode assertion,
                                    IRNode message) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{assertion,message});
    return _result;
  }

  public static IRNode getAssertion(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    return getAssertion(tree, node);
  }

  public Expression getAssertion() {
    return getAssertion(tree);
  }

  public static IRNode getAssertion(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getAssertion(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getMessage(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    return getMessage(tree, node);
  }

  public Expression getMessage() {
    return getMessage(tree);
  }

  public static IRNode getMessage(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getMessage(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("assert");
  private static Token littoken2 = new Delim(":");
  private static Token littoken3 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    littoken2.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken3.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertMessageStatement)) {
      throw new IllegalArgumentException("node not AssertMessageStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    TokenList[1].add(littoken2);
    tree.getChild(node,1);
    TokenList[2].add(littoken3);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subcond = 
        new Subcomponent(comp,tree.childLocation(node,0),1,2,1);
    Subcomponent submesg = 
        new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    ControlNode checkEnabled = new ComponentChoice(comp,this);
    ControlNode discardTrue = new ComponentFlow(comp,Boolean.TRUE);
    ControlNode discardFalse = new ComponentFlow(comp,Boolean.FALSE);
    ControlNode okMerge = new Merge();
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode addError = 
	new AddLabel(PrimitiveExceptionLabel.assertionError);

    // connect up the condition
    ControlEdge.connect(comp.getEntryPort(),checkEnabled);
    ControlEdge.connect(checkEnabled,subcond.getEntryPort());
    // if assertion succeeds, we are done (after ignoring the condition)
    ControlEdge.connect(subcond.getNormalExitPort(),discardTrue);
    ControlEdge.connect(discardTrue,okMerge);
    // if assertion fails, we evaluate detail message
    ControlEdge.connect(subcond.getNormalExitPort(),discardFalse);
    ControlEdge.connect(discardFalse,submesg.getEntryPort());
    ControlEdge.connect(submesg.getNormalExitPort(),addError);

    // connect up final merge
    ControlEdge.connect(checkEnabled,okMerge); // if not enabled, terminate
    ControlEdge.connect(okMerge,comp.getNormalExitPort());
    // connect abrupt exits
    ControlEdge.connect(subcond.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(submesg.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(abruptMerge1,abruptMerge2);
    ControlEdge.connect(addError,abruptMerge2);
    ControlEdge.connect(abruptMerge2,comp.getAbruptExitPort());
    return comp;
  }
}
