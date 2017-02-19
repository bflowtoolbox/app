// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\LabeledStatement.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.LabelTest;
import fluid.control.Merge;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.java.control.NamedBreakLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class LabeledStatement extends Statement { 
  protected LabeledStatement() {}

  public static final LabeledStatement prototype = new LabeledStatement();

  public Operator superOperator() {
    return Statement.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Statement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 1;
  }

  public static JavaNode createNode(String label,
                                    IRNode stmt) {
    return createNode(tree, label, stmt);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, String label,
                                    IRNode stmt) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{stmt});
    JavaNode.setInfo(_result,label);
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

  public static String getLabel(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledStatement)) {
      throw new IllegalArgumentException("node not LabeledStatement: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getLabel() {
    return JavaNode.getInfo(baseNode);
  }

  public static IRNode getStmt(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledStatement)) {
      throw new IllegalArgumentException("node not LabeledStatement: "+op);
    }
    return getStmt(tree, node);
  }

  public Statement getStmt() {
    return getStmt(tree);
  }

  public static IRNode getStmt(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledStatement)) {
      throw new IllegalArgumentException("node not LabeledStatement: "+op);
    }
    return tree.getChild(node,0);
  }

  public Statement getStmt(SyntaxTreeInterface tree) {
    return (Statement)instantiate(tree.getChild(baseNode,0));
  }

  private static Token littoken1 = new Delim(":");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledStatement)) {
      throw new IllegalArgumentException("node not LabeledStatement: "+op);
    }
    JavaNode.unparseInfo(node,unparser);
    littoken1.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof LabeledStatement)) {
      throw new IllegalArgumentException("node not LabeledStatement: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    TokenList[0].add(littoken1);
    tree.getChild(node,0);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  /** Get the label for an arbitrary statement by looking to
   * see if the parent node is one of these kinds of nodes.
   * @return the label (if any) or null
   */
  public static String getStatementLabel(IRNode stmt) {
    IRNode p = tree.getParent(stmt);
    if (tree.getOperator(p) instanceof LabeledStatement) {
      return (String)getLabel(p);
    } else {
      return null;
    }
  }
  
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,0),1,1,1);

    ControlNode testBreak = new LabelTest(comp,new NamedBreakLabel(node));
    ControlNode endMerge = new Merge();

    ControlEdge.connect(comp.getEntryPort(),sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),endMerge);
    ControlEdge.connect(endMerge,comp.getNormalExitPort());
    ControlEdge.connect(sub.getAbruptExitPort(),testBreak);
    ControlEdge.connect(testBreak,endMerge);
    ControlEdge.connect(testBreak,comp.getAbruptExitPort());

    return comp;
  }
}
