// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\TypeDeclarationStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class TypeDeclarationStatement extends Statement { 
  protected TypeDeclarationStatement() {}

  public static final TypeDeclarationStatement prototype = new TypeDeclarationStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return TypeDeclaration.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode typedec) {
    return createNode(tree, typedec);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode typedec) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{typedec});
    return _result;
  }

  public static IRNode getTypedec(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarationStatement)) {
      throw new IllegalArgumentException("node not TypeDeclarationStatement: "+op);
    }
    return getTypedec(tree, node);
  }

  public TypeDeclaration getTypedec() {
    return getTypedec(tree);
  }

  public static IRNode getTypedec(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarationStatement)) {
      throw new IllegalArgumentException("node not TypeDeclarationStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public TypeDeclaration getTypedec(SyntaxTreeInterface tree) {
    return (TypeDeclaration)instantiate(tree.getChild(baseNode,0));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarationStatement)) {
      throw new IllegalArgumentException("node not TypeDeclarationStatement: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof TypeDeclarationStatement)) {
      throw new IllegalArgumentException("node not TypeDeclarationStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    return TokenList;
  }

}
