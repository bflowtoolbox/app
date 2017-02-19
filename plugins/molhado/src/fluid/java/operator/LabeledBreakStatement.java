// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\LabeledBreakStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlLabel;
import fluid.control.ControlNode;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.NamedBreakLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Identifier;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class LabeledBreakStatement extends Statement { 
  protected LabeledBreakStatement() {}

  public static final LabeledBreakStatement prototype = new LabeledBreakStatement();

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

  public static JavaNode createNode(String id) {
    return createNode(tree, id);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, String id) {
    JavaNode _result = new JavaNode(tree, prototype);
    JavaNode.setInfo(_result,id);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
    true;
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledBreakStatement)) {
      throw new IllegalArgumentException("node not LabeledBreakStatement: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  private static Token littoken1 = new Keyword("break");
  private static Token littoken2 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledBreakStatement)) {
      throw new IllegalArgumentException("node not LabeledBreakStatement: "+op);
    }
    littoken1.emit(unparser,node);
    JavaNode.unparseInfo(node,unparser);
    unparser.getStyle().getSEMI().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledBreakStatement)) {
      throw new IllegalArgumentException("node not LabeledBreakStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    TokenList[0].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,0,1);
    ControlLabel label = new NamedBreakLabel(node);
    ControlNode addBreak = new AddLabel(label);
    ControlEdge.connect(comp.getEntryPort(),addBreak);
    ControlEdge.connect(addBreak,comp.getAbruptExitPort());
    return comp;
  }
}
