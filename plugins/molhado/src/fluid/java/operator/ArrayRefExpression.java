// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArrayRefExpression.op.  Do *NOT* edit!
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
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ArrayRefExpression extends PrimaryExpression { 
  protected ArrayRefExpression() {}

  public static final ArrayRefExpression prototype = new ArrayRefExpression();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    case 1: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode array,
                                    IRNode index) {
    return createNode(tree, array, index);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode array,
                                    IRNode index) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{array,index});
    return _result;
  }

  public static IRNode getArray(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    return getArray(tree, node);
  }

  public Expression getArray() {
    return getArray(tree);
  }

  public static IRNode getArray(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getArray(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getIndex(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    return getIndex(tree, node);
  }

  public Expression getIndex() {
    return getIndex(tree);
  }

  public static IRNode getIndex(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public Expression getIndex(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim("[");
  private static Token littoken2 = new Delim("]");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayRefExpression)) {
      throw new IllegalArgumentException("node not ArrayRefExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    TokenList[2].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent suba = 
      new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    Subcomponent subi = 
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();
    ControlNode abruptMerge3 = new Merge();
    ControlNode testArray = new ComponentChoice(comp,"array");
    ControlNode throwNullPointerException =
      new AddLabel(PrimitiveExceptionLabel.primitiveNullPointerException);
    ControlNode testIndex = new ComponentChoice(comp,"index");
    ControlNode throwIndexOutOfBoundsException =
      new AddLabel(PrimitiveExceptionLabel.primitiveIndexOutOfBoundsException);
    ControlNode doLoad = new ComponentFlow(comp,null);
    
    ControlEdge.connect(comp.getEntryPort(),suba.getEntryPort());
    ControlEdge.connect(suba.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(suba.getNormalExitPort(),subi.getEntryPort());
    ControlEdge.connect(subi.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subi.getNormalExitPort(),testArray);
    ControlEdge.connect(testArray,testIndex);
    ControlEdge.connect(testArray,throwNullPointerException);
    ControlEdge.connect(testIndex,doLoad);
    ControlEdge.connect(testIndex,throwIndexOutOfBoundsException);
    ControlEdge.connect(doLoad,comp.getNormalExitPort());
    ControlEdge.connect(throwNullPointerException,abruptMerge2);
    ControlEdge.connect(throwIndexOutOfBoundsException,abruptMerge2);
    ControlEdge.connect(abruptMerge1,abruptMerge3);
    ControlEdge.connect(abruptMerge2,abruptMerge3);
    ControlEdge.connect(abruptMerge3,comp.getAbruptExitPort());

    return comp;
  }
}
