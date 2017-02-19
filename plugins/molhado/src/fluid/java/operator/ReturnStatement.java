// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ReturnStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
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
import fluid.java.control.ReturnLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ReturnStatement extends Statement implements DripOperator { 
  protected ReturnStatement() {}

  public static final ReturnStatement prototype = new ReturnStatement();

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

  public static JavaNode createNode(IRNode value) {
    return createNode(tree, value);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode value) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{value});
    return _result;
  }

  public static IRNode getValue(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ReturnStatement)) {
      throw new IllegalArgumentException("node not ReturnStatement: "+op);
    }
    return getValue(tree, node);
  }

  public Expression getValue() {
    return getValue(tree);
  }

  public static IRNode getValue(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ReturnStatement)) {
      throw new IllegalArgumentException("node not ReturnStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getValue(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Keyword("return");
  private static Token littoken2 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ReturnStatement)) {
      throw new IllegalArgumentException("node not ReturnStatement: "+op);
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
    if (!(op instanceof ReturnStatement)) {
      throw new IllegalArgumentException("node not ReturnStatement: "+op);
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
    Component comp = new Component(node,1,0,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    ControlNode assignReturn = new ComponentFlow(comp,this);
    ControlNode addReturn = new AddLabel(ReturnLabel.prototype);
    ControlNode abruptMerge = new Merge();
    ControlEdge.connect(comp.getEntryPort(),sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),assignReturn);
    ControlEdge.connect(assignReturn,addReturn);
    ControlEdge.connect(sub.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(addReturn,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    return comp;
  }
}
