// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\MethodCall.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.CallExceptionLabel;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class MethodCall extends PrimaryExpression 
    implements Call, DripOperator 
    { 
  protected MethodCall() {}

  public static final MethodCall prototype = new MethodCall();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Arguments.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode object,
                                    String method,
                                    IRNode args) {
    return createNode(tree, object, method, args);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode object,
                                    String method,
                                    IRNode args) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{object,args});
    JavaNode.setInfo(_result,method);
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

  public static IRNode getObject(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    return getObject(tree, node);
  }

  public Expression getObject() {
    return getObject(tree);
  }

  public static IRNode getObject(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getObject(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static String getMethod(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getMethod() {
    return JavaNode.getInfo(baseNode);
  }

  public static IRNode getArgs(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    return getArgs(tree, node);
  }

  public Arguments getArgs() {
    return getArgs(tree);
  }

  public static IRNode getArgs(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    return tree.getChild(node,1);
  }

  public Arguments getArgs(SyntaxTreeInterface tree) {
    return (Arguments)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim(".");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getNONE().emit(unparser,node);
    JavaNode.unparseInfo(node,unparser);
    unparser.getStyle().getNONE().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodCall)) {
      throw new IllegalArgumentException("node not MethodCall: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[1].add(id);
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
    Subcomponent subobj = 
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subarg = 
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode abruptMerge3 = new Merge();
    ControlNode throwNull = 
      new AddLabel(PrimitiveExceptionLabel.primitiveNullPointerException);
    ControlNode testOK = new ComponentChoice(comp,null);
    ControlNode doCall = new ComponentChoice(comp,prototype);
    ControlNode propagateExceptions = 
      new AddLabel(new CallExceptionLabel(node));

    ControlEdge.connect(comp.getEntryPort(),subobj.getEntryPort());
    ControlEdge.connect(subobj.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subobj.getNormalExitPort(),subarg.getEntryPort());
    ControlEdge.connect(subarg.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subarg.getNormalExitPort(),testOK);
    ControlEdge.connect(testOK,doCall);
    ControlEdge.connect(doCall,comp.getNormalExitPort());

    ControlEdge.connect(testOK,throwNull);
    ControlEdge.connect(throwNull,abruptMerge2);
    ControlEdge.connect(doCall,propagateExceptions);
    ControlEdge.connect(propagateExceptions,abruptMerge2);
    ControlEdge.connect(abruptMerge1,abruptMerge3);
    ControlEdge.connect(abruptMerge2,abruptMerge3);
    ControlEdge.connect(abruptMerge3,comp.getAbruptExitPort());
    return comp;
  }
  
}
