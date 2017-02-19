// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SwitchStatement.op.  Do *NOT* edit!
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
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class SwitchStatement extends Statement { 
  protected SwitchStatement() {}

  public static final SwitchStatement prototype = new SwitchStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return SwitchBlock.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode expr,
                                    IRNode block) {
    return createNode(tree, expr, block);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode expr,
                                    IRNode block) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{expr,block});
    return _result;
  }

  public static IRNode getExpr(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
    }
    return getExpr(tree, node);
  }

  public Expression getExpr() {
    return getExpr(tree);
  }

  public static IRNode getExpr(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getExpr(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getBlock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
    }
    return getBlock(tree, node);
  }

  public SwitchBlock getBlock() {
    return getBlock(tree);
  }

  public static IRNode getBlock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public SwitchBlock getBlock(SyntaxTreeInterface tree) {
    return (SwitchBlock)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("switch");
  private static Token littoken2 = new Delim("(");
  private static Token littoken3 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
    }
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken3.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchStatement)) {
      throw new IllegalArgumentException("node not SwitchStatement: "+op);
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
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sube = 
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subb =
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);

    ControlNode assignSwitch = new ComponentFlow(comp,this);
    ControlNode testBreak = new LabelTest(comp,new AnchoredBreakLabel(node));
    ControlNode endMerge = new Merge();
    ControlNode abruptMerge = new Merge();

    // connect normal execution:
    ControlEdge.connect(comp.getEntryPort(),sube.getEntryPort());
    ControlEdge.connect(sube.getNormalExitPort(),assignSwitch);
    ControlEdge.connect(assignSwitch,subb.getEntryPort());
    ControlEdge.connect(subb.getNormalExitPort(),endMerge);
    ControlEdge.connect(endMerge,comp.getNormalExitPort());

    // connect up abrupt termination (including testing for breaks)
    ControlEdge.connect(sube.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(subb.getAbruptExitPort(),testBreak);
    ControlEdge.connect(testBreak,endMerge);
    ControlEdge.connect(testBreak,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());

    return comp;
  }
}
