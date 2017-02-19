// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\FieldDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class FieldDeclaration extends ClassBodyDeclaration 
    implements DripOperator 
    { 
  protected FieldDeclaration() {}

  public static final FieldDeclaration prototype = new FieldDeclaration();

  public Operator superOperator() {
    return ClassBodyDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    case 1: return VariableDeclarators.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(int mods,
                                    IRNode type,
                                    IRNode vars) {
    return createNode(tree, mods, type, vars);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int mods,
                                    IRNode type,
                                    IRNode vars) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type,vars});
    JavaNode.setModifiers(_result,mods);
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

  public static int getMods(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getMods() {
    return JavaNode.getModifiers(baseNode);
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    return getType(tree, node);
  }

  public Type getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getType(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getVars(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    return getVars(tree, node);
  }

  public VariableDeclarators getVars() {
    return getVars(tree);
  }

  public static IRNode getVars(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    return tree.getChild(node,1);
  }

  public VariableDeclarators getVars(SyntaxTreeInterface tree) {
    return (VariableDeclarators)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim(";");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
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
    if (!(op instanceof FieldDeclaration)) {
      throw new IllegalArgumentException("node not FieldDeclaration: "+op);
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
    tree.getChild(node,1);
    TokenList[2].add(littoken1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    return new SimpleComponent(node,new int[]{1});
  }
}
