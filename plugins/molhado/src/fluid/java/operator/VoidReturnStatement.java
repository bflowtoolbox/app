// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\VoidReturnStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
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
public class VoidReturnStatement extends Statement implements DripOperator { 
  protected VoidReturnStatement() {}

  public static final VoidReturnStatement prototype = new VoidReturnStatement();

  public Operator superOperator() {
    return Statement.prototype;
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

  private static Token littoken1 = new Keyword("return");
  private static Token littoken2 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof VoidReturnStatement)) {
      throw new IllegalArgumentException("node not VoidReturnStatement: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.getStyle().getSEMI().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof VoidReturnStatement)) {
      throw new IllegalArgumentException("node not VoidReturnStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    TokenList[0].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,0,1);
    ControlNode addReturn = new AddLabel(ReturnLabel.prototype);
    ControlEdge.connect(comp.getEntryPort(),addReturn);
    ControlEdge.connect(addReturn,comp.getAbruptExitPort());
    return comp;
  }
}
