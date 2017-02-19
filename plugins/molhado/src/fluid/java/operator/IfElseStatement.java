// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\IfElseStatement.op.  Do *NOT* edit!
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
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class IfElseStatement extends Statement implements DripOperator { 
  protected IfElseStatement() {}

  public static final IfElseStatement prototype = new IfElseStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Statement.prototype;
    case 2: return Statement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(IRNode cond,
                                    IRNode thenpart,
                                    IRNode elsepart) {
    return createNode(tree, cond, thenpart, elsepart);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode cond,
                                    IRNode thenpart,
                                    IRNode elsepart) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{cond,thenpart,elsepart});
    return _result;
  }

  public static IRNode getCond(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return getCond(tree, node);
  }

  public Expression getCond() {
    return getCond(tree);
  }

  public static IRNode getCond(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getCond(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getThenpart(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return getThenpart(tree, node);
  }

  public Statement getThenpart() {
    return getThenpart(tree);
  }

  public static IRNode getThenpart(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public Statement getThenpart(SyntaxTreeInterface tree) {
    return (Statement)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getElsepart(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return getElsepart(tree, node);
  }

  public Statement getElsepart() {
    return getElsepart(tree);
  }

  public static IRNode getElsepart(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    return tree.getChild(node,2);
  }

  public Statement getElsepart(SyntaxTreeInterface tree) {
    return (Statement)instantiate(tree.getChild(baseNode,2));
  }

  private static Token littoken1 = new Keyword("if");
  private static Token littoken2 = new Delim("(");
  private static Token littoken3 = new Delim(")");
  private static Token littoken4 = new Keyword("else");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
    }
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken3.emit(unparser,node);
    unparser.getStyle().getTHEN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getENDTHEN().emit(unparser,node);
    littoken4.emit(unparser,node);
    unparser.getStyle().getELSE().emit(unparser,node);
    unparser.unparse(tree.getChild(node,2));
    unparser.getStyle().getENDELSE().emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof IfElseStatement)) {
      throw new IllegalArgumentException("node not IfElseStatement: "+op);
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
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subcond = 
        new Subcomponent(comp,tree.childLocation(node,0),1,2,1);
    Subcomponent subthen = 
        new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    Subcomponent subelse = 
        new Subcomponent(comp,tree.childLocation(node,2),1,1,1);
    ControlNode discardTrue = new ComponentFlow(comp,Boolean.TRUE);
    ControlNode discardFalse = new ComponentFlow(comp,Boolean.FALSE);
    ControlNode endMerge = new Merge();
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    // connect up the condition
    ControlEdge.connect(comp.getEntryPort(),subcond.getEntryPort());
    ControlEdge.connect(subcond.getNormalExitPort(),discardTrue);
    ControlEdge.connect(discardTrue,subthen.getEntryPort());
    ControlEdge.connect(subcond.getNormalExitPort(),discardFalse);
    ControlEdge.connect(discardFalse,subelse.getEntryPort());
    // connect up final merge
    ControlEdge.connect(subthen.getNormalExitPort(),endMerge);
    ControlEdge.connect(subelse.getNormalExitPort(),endMerge);
    ControlEdge.connect(endMerge,comp.getNormalExitPort());
    // connect abrupt exits
    ControlEdge.connect(subcond.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subthen.getAbruptExitPort(),abruptMerge2);
    ControlEdge.connect(subelse.getAbruptExitPort(),abruptMerge2);
    ControlEdge.connect(abruptMerge2,abruptMerge1);
    ControlEdge.connect(abruptMerge1,comp.getAbruptExitPort());
    return comp;
  }
}
