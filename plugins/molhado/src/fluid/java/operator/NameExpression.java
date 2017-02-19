// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\NameExpression.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
public class NameExpression extends PrimaryExpression implements IllegalCode 
    { 
  protected NameExpression() {}

  public static final NameExpression prototype = new NameExpression();

  public Operator superOperator() {
    return PrimaryExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return null;
  }

  public int numChildren() {
    return 0;
  }

  public static JavaNode createNode(String name) {
    return createNode(tree, name);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, String name) {
    JavaNode _result = new JavaNode(tree, prototype);
    JavaNode.setInfo(_result,name);
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

  public static String getName(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof NameExpression)) {
      throw new IllegalArgumentException("node not NameExpression: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getName() {
    return JavaNode.getInfo(baseNode);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof NameExpression)) {
      throw new IllegalArgumentException("node not NameExpression: "+op);
    }
    JavaNode.unparseInfo(node,unparser);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof NameExpression)) {
      throw new IllegalArgumentException("node not NameExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    return TokenList;
  }

}
