// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\Initialization.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class Initialization extends OptInitialization { 
  protected Initialization() {}

  public static final Initialization prototype = new Initialization();

  public Operator superOperator() {
    return OptInitialization.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Initializer.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(IRNode value) {
    return createNode(tree, value);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode value) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{value});
    return _result;
  }

  public static IRNode getValue(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Initialization)) {
      throw new IllegalArgumentException("node not Initialization: "+op);
    }
    return getValue(tree, node);
  }

  public Initializer getValue() {
    return getValue(tree);
  }

  public static IRNode getValue(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof Initialization)) {
      throw new IllegalArgumentException("node not Initialization: "+op);
    }
    return tree.getChild(node,0);
  }

  public Initializer getValue(SyntaxTreeInterface tree) {
    return (Initializer)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim("=");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof Initialization)) {
      throw new IllegalArgumentException("node not Initialization: "+op);
    }
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof Initialization)) {
      throw new IllegalArgumentException("node not Initialization: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    return new SimpleComponent(node);
  }
}
