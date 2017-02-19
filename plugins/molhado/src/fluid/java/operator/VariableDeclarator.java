// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\VariableDeclarator.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
/** A variable or field declaration.
 * We do not handle the case of [] appended to the end of the name.
 */
public class VariableDeclarator extends VariableDeclaration 
    implements DripOperator 
    { 
  protected VariableDeclarator() {}

  public static final VariableDeclarator prototype = new VariableDeclarator();

  public Operator superOperator() {
    return VariableDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return OptInitialization.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(String id,
                                    int dims,
                                    IRNode init) {
    return createNode(tree, id, dims, init);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, String id,
                                    int dims,
                                    IRNode init) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{init});
    JavaNode.setInfo(_result,id);
    JavaNode.setDimInfo(_result,dims);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getInfo(node);
      JavaNode.getDimInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    JavaNode.setDimInfo(_result,JavaNode.getDimInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
           (JavaNode.getDimInfo(n1) == JavaNode.getDimInfo(n2)) &&
    true;
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getDims() {
    return JavaNode.getDimInfo(baseNode);
  }

  public static IRNode getInit(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    return getInit(tree, node);
  }

  public OptInitialization getInit() {
    return getInit(tree);
  }

  public static IRNode getInit(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    return tree.getChild(node,0);
  }

  public OptInitialization getInit(SyntaxTreeInterface tree) {
    return (OptInitialization)instantiate(tree.getChild(baseNode,0));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    JavaNode.unparseInfo(node,unparser);
    JavaNode.unparseDimInfo(node,unparser);
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof VariableDeclarator)) {
      throw new IllegalArgumentException("node not VariableDeclarator: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[0].add(tok);
    tree.getChild(node,0);
    return TokenList;
  }

  
  public static IRNode getType(IRNode node) {
    if (tree.getOperator(node) instanceof VariableDeclarator) {
      return tree.getChild(tree.getParent(tree.getParent(node)),0);
    } else {
      throw new IllegalArgumentException("Not a VariableDeclarator " +
					 tree.getOperator(node));
    }
  }

  /** Create the control-flow component for a variable declaration.
   * Execute the initialization (child #0), and then
   * perform an initialization flow.
   */
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent subinit = 
        new Subcomponent(comp,tree.childLocation(node,0),1,1,1);
    ControlNode performInit = new ComponentFlow(comp,null);
    
    ControlEdge.connect(comp.getEntryPort(),subinit.getEntryPort());
    ControlEdge.connect(subinit.getNormalExitPort(),performInit);
    ControlEdge.connect(performInit,comp.getNormalExitPort());
    ControlEdge.connect(subinit.getAbruptExitPort(),comp.getAbruptExitPort());
    return comp;
  }
}
