// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConstructorCall.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Abort;
import fluid.control.AddLabel;
import fluid.control.Component;
import fluid.control.ComponentChoice;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.CallExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
/** A very special form of call: legal only as the first statement
 * of a constructor.  The object must be "this" or "super".
 */
public class ConstructorCall extends Statement implements Call 
    { 
  protected ConstructorCall() {}

  public static final ConstructorCall prototype = new ConstructorCall();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return ConstructionObject.prototype;
    case 1: return Arguments.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode object,
                                    IRNode args) {
    return createNode(tree, object, args);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode object,
                                    IRNode args) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{object,args});
    return _result;
  }

  public static IRNode getObject(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    return getObject(tree, node);
  }

  public ConstructionObject getObject() {
    return getObject(tree);
  }

  public static IRNode getObject(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    return tree.getChild(node,0);
  }

  public ConstructionObject getObject(SyntaxTreeInterface tree) {
    return (ConstructionObject)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getArgs(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    return getArgs(tree, node);
  }

  public Arguments getArgs() {
    return getArgs(tree);
  }

  public static IRNode getArgs(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    return tree.getChild(node,1);
  }

  public Arguments getArgs(SyntaxTreeInterface tree) {
    return (Arguments)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorCall)) {
      throw new IllegalArgumentException("node not ConstructorCall: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    tree.getChild(node,1);
    TokenList[2].add(littoken1);
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
    Subcomponent rec = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,1),1,1,1);

    ControlNode impossible = new Abort();
    ControlNode doCall = new ComponentChoice(comp,prototype);
    ControlNode propagateExceptions = 
      new AddLabel(new CallExceptionLabel(node));
    ControlNode abruptMerge = new Merge();

    ControlEdge.connect(comp.getEntryPort(),rec.getEntryPort());
    ControlEdge.connect(rec.getNormalExitPort(),sub.getEntryPort());
    ControlEdge.connect(rec.getAbruptExitPort(),impossible);
    ControlEdge.connect(sub.getNormalExitPort(),doCall);
    ControlEdge.connect(doCall,comp.getNormalExitPort());
    ControlEdge.connect(sub.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(doCall,propagateExceptions);
    ControlEdge.connect(propagateExceptions,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());

    return comp;
  }  
}
