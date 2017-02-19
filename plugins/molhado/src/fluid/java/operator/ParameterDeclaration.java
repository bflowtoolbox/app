// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ParameterDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class ParameterDeclaration extends VariableDeclaration 
    implements DripOperator 
    { 
  protected ParameterDeclaration() {}

  public static final ParameterDeclaration prototype = new ParameterDeclaration();

  public Operator superOperator() {
    return VariableDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Type.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(int mods,
                                    IRNode type,
                                    String id,
                                    int dims) {
    return createNode(tree, mods, type, id, dims);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int mods,
                                    IRNode type,
                                    String id,
                                    int dims) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type});
    JavaNode.setModifiers(_result,mods);
    JavaNode.setInfo(_result,id);
    JavaNode.setDimInfo(_result,dims);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getModifiers(node);
      JavaNode.getInfo(node);
      JavaNode.getDimInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setModifiers(_result,JavaNode.getModifiers(node));
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    JavaNode.setDimInfo(_result,JavaNode.getDimInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getModifiers(n1) == JavaNode.getModifiers(n2)) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
           (JavaNode.getDimInfo(n1) == JavaNode.getDimInfo(n2)) &&
    true;
  }

  public static int getMods(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getMods() {
    return JavaNode.getModifiers(baseNode);
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    return getType(tree, node);
  }

  public Type getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public Type getType(SyntaxTreeInterface tree) {
    return (Type)instantiate(tree.getChild(baseNode,0));
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getDims() {
    return JavaNode.getDimInfo(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
    unparser.unparse(tree.getChild(node,0));
    JavaNode.unparseInfo(node,unparser);
    JavaNode.unparseDimInfo(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ParameterDeclaration)) {
      throw new IllegalArgumentException("node not ParameterDeclaration: "+op);
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
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[1].add(id);
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[1].add(tok);
    return TokenList;
  }

}
