// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\TryStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.PendingLabelStrip;
import fluid.control.Subcomponent;
import fluid.control.TrackedDemerge;
import fluid.control.TrackedMerge;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class TryStatement extends Statement { 
  protected TryStatement() {}

  public static final TryStatement prototype = new TryStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return BlockStatement.prototype;
    case 1: return CatchClauses.prototype;
    case 2: return OptFinally.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(IRNode block,
                                    IRNode catchPart,
                                    IRNode finallyPart) {
    return createNode(tree, block, catchPart, finallyPart);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode block,
                                    IRNode catchPart,
                                    IRNode finallyPart) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{block,catchPart,finallyPart});
    return _result;
  }

  public static IRNode getBlock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return getBlock(tree, node);
  }

  public BlockStatement getBlock() {
    return getBlock(tree);
  }

  public static IRNode getBlock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public BlockStatement getBlock(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getCatchPart(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return getCatchPart(tree, node);
  }

  public CatchClauses getCatchPart() {
    return getCatchPart(tree);
  }

  public static IRNode getCatchPart(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return tree.getChild(node,1);
  }

  public CatchClauses getCatchPart(SyntaxTreeInterface tree) {
    return (CatchClauses)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getFinallyPart(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return getFinallyPart(tree, node);
  }

  public OptFinally getFinallyPart() {
    return getFinallyPart(tree);
  }

  public static IRNode getFinallyPart(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    return tree.getChild(node,2);
  }

  public OptFinally getFinallyPart(SyntaxTreeInterface tree) {
    return (OptFinally)instantiate(tree.getChild(baseNode,2));
  }

  private static Token littoken1 = new Keyword("try");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
    unparser.unparse(tree.getChild(node,2));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof TryStatement)) {
      throw new IllegalArgumentException("node not TryStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    tree.getChild(node,1);
    tree.getChild(node,2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subblock = 
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subcatch = 
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    Subcomponent subfinal = 
      new Subcomponent(comp,tree.childLocation(node,2),1,1,1);

    ControlNode endMerge = new Merge();
    ControlNode addTrack = new TrackedMerge();
    ControlNode checkTrack = new TrackedDemerge();
    ControlNode stripLabel = new PendingLabelStrip();
    ControlNode abruptMerge = new Merge();
    
    // Connect up normal execution
    ControlEdge.connect(comp.getEntryPort(),subblock.getEntryPort());
    ControlEdge.connect(subblock.getNormalExitPort(),endMerge);
    ControlEdge.connect(endMerge,addTrack);
    ControlEdge.connect(addTrack,subfinal.getEntryPort());
    ControlEdge.connect(subfinal.getNormalExitPort(),checkTrack);
    ControlEdge.connect(checkTrack,comp.getNormalExitPort());

    // Connect catch clauses
    ControlEdge.connect(subblock.getAbruptExitPort(),subcatch.getEntryPort());
    ControlEdge.connect(subcatch.getNormalExitPort(),endMerge);
    ControlEdge.connect(subcatch.getAbruptExitPort(),addTrack);

    // Get labels right for abrupt termination of try statement
    ControlEdge.connect(checkTrack,abruptMerge);
    ControlEdge.connect(subfinal.getAbruptExitPort(),stripLabel);
    ControlEdge.connect(stripLabel,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    
    return comp;
  }
}
