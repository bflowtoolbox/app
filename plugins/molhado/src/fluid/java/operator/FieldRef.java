// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\FieldRef.op.  Do *NOT* edit!
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
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class FieldRef extends PrimaryExpression implements DripOperator { 
  protected FieldRef() {}

  public static final FieldRef prototype = new FieldRef();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Expression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode object,
                                    String id) {
    return createNode(tree, object, id);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode object,
                                    String id) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{object});
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

  public static IRNode getObject(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldRef)) {
      throw new IllegalArgumentException("node not FieldRef: "+op);
    }
    return getObject(tree, node);
  }

  public Expression getObject() {
    return getObject(tree);
  }

  public static IRNode getObject(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldRef)) {
      throw new IllegalArgumentException("node not FieldRef: "+op);
    }
    return tree.getChild(node,0);
  }

  public Expression getObject(SyntaxTreeInterface tree) {
    return (Expression)instantiate(tree.getChild(baseNode,0));
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldRef)) {
      throw new IllegalArgumentException("node not FieldRef: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  private static Token littoken1 = new Delim(".");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldRef)) {
      throw new IllegalArgumentException("node not FieldRef: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getNONE().emit(unparser,node);
    JavaNode.unparseInfo(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldRef)) {
      throw new IllegalArgumentException("node not FieldRef: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[1].add(id);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    ControlNode abruptMerge = new Merge();
    ControlNode throwNull = 
      new AddLabel(PrimitiveExceptionLabel.primitiveNullPointerException);
    ControlNode testOK = new ComponentChoice(comp,null);
    ControlNode useField = new ComponentFlow(comp,null);

    ControlEdge.connect(comp.getEntryPort(),sub.getEntryPort());
    ControlEdge.connect(sub.getAbruptExitPort(),abruptMerge);
    ControlEdge.connect(sub.getNormalExitPort(),testOK);
    ControlEdge.connect(testOK,useField);
    ControlEdge.connect(useField,comp.getNormalExitPort());
    ControlEdge.connect(testOK,throwNull);
    ControlEdge.connect(throwNull,abruptMerge);
    ControlEdge.connect(abruptMerge,comp.getAbruptExitPort());
    return comp;
  }
}
