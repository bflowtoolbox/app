// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ClassInitializer.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Token;
public class ClassInitializer extends ClassBodyDeclaration { 
  protected ClassInitializer() {}

  public static final ClassInitializer prototype = new ClassInitializer();

  public Operator superOperator() {
    return ClassBodyDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return BlockStatement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(int modifiers,
                                    IRNode block) {
    return createNode(tree, modifiers, block);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int modifiers,
                                    IRNode block) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{block});
    JavaNode.setModifiers(_result,modifiers);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getModifiers(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setModifiers(_result,JavaNode.getModifiers(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getModifiers(n1) == JavaNode.getModifiers(n2)) &&
    true;
  }

  public static int getModifiers(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassInitializer)) {
      throw new IllegalArgumentException("node not ClassInitializer: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getModifiers() {
    return JavaNode.getModifiers(baseNode);
  }

  public static IRNode getBlock(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassInitializer)) {
      throw new IllegalArgumentException("node not ClassInitializer: "+op);
    }
    return getBlock(tree, node);
  }

  public BlockStatement getBlock() {
    return getBlock(tree);
  }

  public static IRNode getBlock(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassInitializer)) {
      throw new IllegalArgumentException("node not ClassInitializer: "+op);
    }
    return tree.getChild(node,0);
  }

  public BlockStatement getBlock(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,0));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassInitializer)) {
      throw new IllegalArgumentException("node not ClassInitializer: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassInitializer)) {
      throw new IllegalArgumentException("node not ClassInitializer: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Token[] tl = JavaNode.getModiferTokens(node);
    if (tl != null && tl.length > 0)
      for (int j = 0; j < tl.length; j++)
        TokenList[0].add(tl[j]);
    tree.getChild(node,0);
    return TokenList;
  }

  
  /** Create a component for this node.  Just execute the block.
   */
  public Component createComponent(IRNode node) {
    return new SimpleComponent(node);
  }  
}
