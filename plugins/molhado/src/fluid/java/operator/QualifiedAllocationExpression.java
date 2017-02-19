// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\QualifiedAllocationExpression.op.  Do *NOT* edit!
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
public class QualifiedAllocationExpression extends AllocationExpression 
    { 
  protected QualifiedAllocationExpression() {}

  public static final QualifiedAllocationExpression prototype = new QualifiedAllocationExpression();

  public Operator superOperator() {
    return AllocationExpression.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return PrimaryExpression.prototype;
    case 1: return AllocationExpression.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode type,
                                    IRNode alloc) {
    return createNode(tree, type, alloc);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode type,
                                    IRNode alloc) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{type,alloc});
    return _result;
  }

  public static IRNode getType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    return getType(tree, node);
  }

  public PrimaryExpression getType() {
    return getType(tree);
  }

  public static IRNode getType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    return tree.getChild(node,0);
  }

  public PrimaryExpression getType(SyntaxTreeInterface tree) {
    return (PrimaryExpression)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getAlloc(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    return getAlloc(tree, node);
  }

  public AllocationExpression getAlloc() {
    return getAlloc(tree);
  }

  public static IRNode getAlloc(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    return tree.getChild(node,1);
  }

  public AllocationExpression getAlloc(SyntaxTreeInterface tree) {
    return (AllocationExpression)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Delim(".");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof QualifiedAllocationExpression)) {
      throw new IllegalArgumentException("node not QualifiedAllocationExpression: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    TokenList[1].add(littoken1);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    return new SimpleComponent(node);
  }
}
