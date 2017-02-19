// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\AssertStatement.op.  Do *NOT* edit!
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
public class AssertStatement extends Statement implements DripOperator { 
  protected AssertStatement() {}

  public static final AssertStatement prototype = new AssertStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode assertion) {
    return createNode(tree, assertion);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode assertion) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{assertion});
    return _result;
  }

  public static IRNode getAssertion(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertStatement)) {
      throw new IllegalArgumentException("node not AssertStatement: "+op);
    }
    return getAssertion(tree, node);
  }

  public Expression getAssertion() {
    return getAssertion(tree);
  }

  public static IRNode getAssertion(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertStatement)) {
      throw new IllegalArgumentException("node not AssertStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getAssertion(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Keyword("assert");
  private static Token littoken2 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertStatement)) {
      throw new IllegalArgumentException("node not AssertStatement: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof AssertStatement)) {
      throw new IllegalArgumentException("node not AssertStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    TokenList[1].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subcond = 
        new Subcomponent(comp,tree.childLocation(node,0),1,2,1);
    ControlNode checkEnabled = new ComponentChoice(comp,this);
    ControlNode discardTrue = new ComponentFlow(comp,Boolean.TRUE);
    ControlNode discardFalse = new ComponentFlow(comp,Boolean.FALSE);
    ControlNode okMerge = new Merge();
    ControlNode abruptMerge = new Merge();
    ControlNode addError = 
	new AddLabel(PrimitiveExceptionLabel.assertionError);

    // connect up the condition
    ControlEdge.connect(comp.getEntryPort(),checkEnabled);
    ControlEdge.connect(checkEnabled,subcond.getEntryPort());
    // if assertion succeeds, we are done (after ignoring the condition)
    ControlEdge.connect(subcond.getNormalExitPort(),discardTrue);
    ControlEdge.connect(discardTrue,okMerge);
    // if assertion fails, we throw an error
    ControlEdge.connect(subcond.getNormalExitPort(),discardFalse);
    ControlEdge.connect(discardFalse,addError);

    // connect up final merge
    ControlEdge.connect(checkEnabled,okMerge); // if not enabled, terminate
    ControlEdge.connect(okMerge,comp.getNormalExitPort());
    // connect abrupt exits
    ControlEdge.connect(subcond.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(addError,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    return comp;
  }
}
