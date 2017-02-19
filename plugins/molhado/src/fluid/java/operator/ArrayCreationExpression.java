// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArrayCreationExpression.op.  Do *NOT* edit!
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
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.PrimitiveExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ArrayCreationExpression extends AllocationExpression 
    { 
  protected ArrayCreationExpression() {}

  public static final ArrayCreationExpression prototype = new ArrayCreationExpression();

  public Operator superOperator() {
    return AllocationExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    case 1: return DimExprs.prototype;
    case 2: return OptArrayInitializer.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(IRNode base,
                                    IRNode allocated,
                                    int unallocated,
                                    IRNode init) {
    return createNode(tree, base, allocated, unallocated, init);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode base,
                                    IRNode allocated,
                                    int unallocated,
                                    IRNode init) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{base,allocated,init});
    JavaNode.setDimInfo(_result,unallocated);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getDimInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setDimInfo(_result,JavaNode.getDimInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getDimInfo(n1) == JavaNode.getDimInfo(n2)) &&
    true;
  }

  public static IRNode getBase(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return getBase(tree, node);
  }

  public Type getBase() {
    return getBase(tree);
  }

  public static IRNode getBase(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getBase(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getAllocated(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return getAllocated(tree, node);
  }

  public DimExprs getAllocated() {
    return getAllocated(tree);
  }

  public static IRNode getAllocated(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public DimExprs getAllocated(SyntaxTreeInterface tree) {
    return (DimExprs)instantiate(tree.getChild(baseNode,1));
  }

  public static int getUnallocated(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getUnallocated() {
    return JavaNode.getDimInfo(baseNode);
  }

  public static IRNode getInit(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return getInit(tree, node);
  }

  public OptArrayInitializerInterface getInit() {
    return getInit(tree);
  }

  public static IRNode getInit(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    return tree.getChild(node,2);
  }

  public OptArrayInitializerInterface getInit(SyntaxTreeInterface tree) {
    return (OptArrayInitializerInterface)instantiate(tree.getChild(baseNode,2));
  }

  private static Token littoken1 = new Keyword("new");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getNONE().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getNONE().emit(unparser,node);
    JavaNode.unparseDimInfo(node,unparser);
    unparser.getStyle().getNONE().emit(unparser,node);
    unparser.unparse(tree.getChild(node,2));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayCreationExpression)) {
      throw new IllegalArgumentException("node not ArrayCreationExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    tree.getChild(node,1);
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[2].add(tok);
    tree.getChild(node,2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subdims =
      new Subcomponent(comp,tree.childLocation(node,1),1,1,1);
    Subcomponent subinit =
      new Subcomponent(comp,tree.childLocation(node,2),1,1,1);

    ControlNode testDims = new ComponentChoice(comp,null);
    ControlNode throwNegativeArraySizeException =
      new AddLabel(PrimitiveExceptionLabel.primitiveNegativeArraySizeException);
    ControlNode abruptMerge1 = new Merge();
    ControlNode abruptMerge2 = new Merge();

    ControlEdge.connect(comp.getEntryPort(),subdims.getEntryPort());
    ControlEdge.connect(subdims.getNormalExitPort(),testDims);
    ControlEdge.connect(testDims,subinit.getEntryPort());
    ControlEdge.connect(subinit.getNormalExitPort(),comp.getNormalExitPort());

    ControlEdge.connect(subdims.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(subinit.getAbruptExitPort(),abruptMerge1);
    ControlEdge.connect(testDims,throwNegativeArraySizeException);
    ControlEdge.connect(throwNegativeArraySizeException,abruptMerge2);
    ControlEdge.connect(abruptMerge1,abruptMerge2);
    ControlEdge.connect(abruptMerge2,comp.getAbruptExitPort());

    return comp;
  }
}
