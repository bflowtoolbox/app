// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\NewExpression.op.  Do *NOT* edit!
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
import fluid.java.control.CallExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class NewExpression extends AllocationExpression 
    implements Call, DripOperator 
    { 
  protected NewExpression() {}

  public static final NewExpression prototype = new NewExpression();

  public Operator superOperator() {
    return AllocationExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return NamedType.prototype;
    case 1: return Arguments.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode type,
                                    IRNode args) {
    return createNode(tree, type, args);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode type,
                                    IRNode args) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type,args});
    return _result;
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    return getType(tree, node);
  }

  public NamedType getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public NamedType getType(SyntaxTreeInterface tree) {
    return (NamedType)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getArgs(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    return getArgs(tree, node);
  }

  public Arguments getArgs() {
    return getArgs(tree);
  }

  public static IRNode getArgs(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Arguments getArgs(SyntaxTreeInterface tree) {
    return (Arguments)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("new");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof NewExpression)) {
      throw new IllegalArgumentException("node not NewExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }


  public IRNode getActuals(IRNode node) {
    return getArgs(node);
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,1),1,1,1);

    ControlNode doAlloc = new ComponentFlow(comp,prototype);
    ControlNode doCall = new ComponentChoice(comp,prototype);
    ControlNode propagateExceptions = 
      new AddLabel(new CallExceptionLabel(node));
    ControlNode abruptMerge = new Merge();

    ControlEdge.connect(comp.getEntryPort(),doAlloc);
    ControlEdge.connect(doAlloc,sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),doCall);
    ControlEdge.connect(doCall,comp.getNormalExitPort());
    ControlEdge.connect(sub.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(doCall,propagateExceptions);
    ControlEdge.connect(propagateExceptions,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());

    return comp;
  }
}
