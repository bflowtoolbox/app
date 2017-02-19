// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SwitchElement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.SimpleComponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
public class SwitchElement extends JavaOperator { 
  protected SwitchElement() {}

  public static final SwitchElement prototype = new SwitchElement();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return SwitchLabel.prototype;
    case 1: return SwitchStatements.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode label,
                                    IRNode stmts) {
    return createNode(tree, label, stmts);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode label,
                                    IRNode stmts) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{label,stmts});
    return _result;
  }

  public static IRNode getLabel(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    return getLabel(tree, node);
  }

  public SwitchLabel getLabel() {
    return getLabel(tree);
  }

  public static IRNode getLabel(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    return tree.getChild(node,0);
  }

  public SwitchLabel getLabel(SyntaxTreeInterface tree) {
    return (SwitchLabel)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getStmts(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    return getStmts(tree, node);
  }

  public SwitchStatements getStmts() {
    return getStmts(tree);
  }

  public static IRNode getStmts(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    return tree.getChild(node,1);
  }

  public SwitchStatements getStmts(SyntaxTreeInterface tree) {
    return (SwitchStatements)instantiate(tree.getChild(baseNode,1));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchElement)) {
      throw new IllegalArgumentException("node not SwitchElement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    tree.getChild(node,0);
    tree.getChild(node,1);
    return TokenList;
  }

  /** Create the control-flow graph component for a label and optional
   * following statements.  Control only enters the component
   * if the statements will be executed.  Testing the various labels
   * is done in the SwitchBlockSubcomponent.
   * Currently, we don't execute the labels themselves.
   */
  public Component createComponent(IRNode node) {
    return new SimpleComponent(node,new int[]{1});
  }
}
