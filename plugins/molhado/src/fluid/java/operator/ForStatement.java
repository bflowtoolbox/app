// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ForStatement.op.  Do *NOT* edit!
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
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.AnchoredBreakLabel;
import fluid.java.control.AnchoredContinueLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ForStatement extends Statement { 
  protected ForStatement() {}

  public static final ForStatement prototype = new ForStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return ForInit.prototype;
    case 1: return Expression.prototype;
    case 2: return StatementExpressionList.prototype;
    case 3: return Statement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 4;
  }

  public static JavaNode createNode(IRNode init,
                                    IRNode cond,
                                    IRNode update,
                                    IRNode loop) {
    return createNode(tree, init, cond, update, loop);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode init,
                                    IRNode cond,
                                    IRNode update,
                                    IRNode loop) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{init,cond,update,loop});
    return _result;
  }

  public static IRNode getInit(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return getInit(tree, node);
  }

  public ForInitInterface getInit() {
    return getInit(tree);
  }

  public static IRNode getInit(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public ForInitInterface getInit(SyntaxTreeInterface tree) {
    return (ForInitInterface)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getCond(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return getCond(tree, node);
  }

  public Expression getCond() {
    return getCond(tree);
  }

  public static IRNode getCond(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getCond(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getUpdate(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return getUpdate(tree, node);
  }

  public StatementExpressionList getUpdate() {
    return getUpdate(tree);
  }

  public static IRNode getUpdate(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return tree.getChild(node,2);
  }

  public StatementExpressionList getUpdate(SyntaxTreeInterface tree) {
    return (StatementExpressionList)instantiate(tree.getChild(baseNode,2));
  }

  public static IRNode getLoop(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return getLoop(tree, node);
  }

  public Statement getLoop() {
    return getLoop(tree);
  }

  public static IRNode getLoop(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    return tree.getChild(node,3);
  }

  public Statement getLoop(SyntaxTreeInterface tree) {
    return (Statement)instantiate(tree.getChild(baseNode,3));
  }

  private static Token littoken1 = new Keyword("for");
  private static Token littoken2 = new Delim("(");
  private static Token littoken3 = new Delim(";");
  private static Token littoken4 = new Delim(";");
  private static Token littoken5 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getSEMI().emit(unparser,node);
    littoken3.emit(unparser,node);
    unparser.getStyle().getLI().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getSEMI().emit(unparser,node);
    littoken4.emit(unparser,node);
    unparser.getStyle().getLI().emit(unparser,node);
    unparser.unparse(tree.getChild(node,2));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken5.emit(unparser,node);
    unparser.getStyle().getFORLOOP().emit(unparser,node);
    unparser.unparse(tree.getChild(node,3));
    unparser.getStyle().getENDFORLOOP().emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ForStatement)) {
      throw new IllegalArgumentException("node not ForStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    TokenList[0].add(littoken2);
    tree.getChild(node,0);
    TokenList[1].add(littoken3);
    tree.getChild(node,1);
    TokenList[2].add(littoken4);
    tree.getChild(node,2);
    TokenList[3].add(littoken5);
    tree.getChild(node,3);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subinit =
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subcond = 
      new Subcomponent(comp,tree.childLocation(node,1),1,2,1);
    Subcomponent subcont =
      new Subcomponent(comp,tree.childLocation(node,2),1,1,1);
    Subcomponent subloop = 
      new Subcomponent(comp,tree.childLocation(node,3),1,1,1);
    ControlNode discardTrue = new ComponentFlow(comp,Boolean.TRUE);
    ControlNode discardFalse = new ComponentFlow(comp,Boolean.FALSE);
    ControlNode testMerge = new Merge();
    ControlNode contMerge = new Merge();
    ControlNode endMerge = new Merge();
    ControlNode testBreak = new LabelTest(comp,new AnchoredBreakLabel(node));
    ControlNode testCont = new LabelTest(comp,new AnchoredContinueLabel(node));
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode abruptMerge3 = new Merge();

    // connect up the loop (normal execution)
    ControlEdge.connect(comp.getEntryPort(),subinit.getEntryPort());
    ControlEdge.connect(subinit.getNormalExitPort(),testMerge);
    ControlEdge.connect(testMerge,subcond.getEntryPort());
    ControlEdge.connect(subcond.getNormalExitPort(),discardTrue);
    ControlEdge.connect(discardTrue,subloop.getEntryPort());
    ControlEdge.connect(subloop.getNormalExitPort(),contMerge);
    ControlEdge.connect(contMerge,subcont.getEntryPort());
    ControlEdge.connect(subcont.getNormalExitPort(),testMerge);
    ControlEdge.connect(subcond.getNormalExitPort(),discardFalse);
    ControlEdge.connect(discardFalse,endMerge);
    ControlEdge.connect(endMerge,comp.getNormalExitPort());

    // connect up abrupt exits (including continue and break tests)
    ControlEdge.connect(subinit.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subcond.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subloop.getAbruptExitPort(),testCont);
    ControlEdge.connect(testCont,contMerge);
    ControlEdge.connect(testCont,testBreak);
    ControlEdge.connect(testBreak,endMerge);
    ControlEdge.connect(testBreak,abruptMerge2);
    ControlEdge.connect(subcont.getAbruptExitPort(),abruptMerge2);
    ControlEdge.connect(abruptMerge1,abruptMerge3);
    ControlEdge.connect(abruptMerge2,abruptMerge3);
    ControlEdge.connect(abruptMerge3,comp.getAbruptExitPort());
    return comp;
  }
}
