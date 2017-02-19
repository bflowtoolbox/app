// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CatchClause.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlLabel;
import fluid.control.ControlNode;
import fluid.control.LabelTest;
import fluid.control.Subcomponent;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.java.control.CaughtExceptionLabel;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class CatchClause extends JavaOperator { 
  protected CatchClause() {}

  public static final CatchClause prototype = new CatchClause();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return ParameterDeclaration.prototype;
    case 1: return BlockStatement.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 2;
  }

  public static JavaNode createNode(IRNode param,
                                    IRNode body) {
    return createNode(tree, param, body);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode param,
                                    IRNode body) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{param,body});
    return _result;
  }

  public static IRNode getParam(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    return getParam(tree, node);
  }

  public ParameterDeclaration getParam() {
    return getParam(tree);
  }

  public static IRNode getParam(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    return tree.getChild(node,0);
  }

  public ParameterDeclaration getParam(SyntaxTreeInterface tree) {
    return (ParameterDeclaration)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getBody(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    return getBody(tree, node);
  }

  public BlockStatement getBody() {
    return getBody(tree);
  }

  public static IRNode getBody(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    return tree.getChild(node,1);
  }

  public BlockStatement getBody(SyntaxTreeInterface tree) {
    return (BlockStatement)instantiate(tree.getChild(baseNode,1));
  }

  private static Token littoken1 = new Keyword("catch");
  private static Token littoken2 = new Delim("(");
  private static Token littoken3 = new Delim(")");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    littoken1.emit(unparser,node);
    littoken2.emit(unparser,node);
    unparser.getStyle().getPAREN().emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.getStyle().getENDPAREN().emit(unparser,node);
    littoken3.emit(unparser,node);
    unparser.getStyle().getCATCH().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    unparser.getStyle().getENDCATCH().emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClause)) {
      throw new IllegalArgumentException("node not CatchClause: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[0].add(littoken1);
    TokenList[0].add(littoken2);
    tree.getChild(node,0);
    TokenList[1].add(littoken3);
    tree.getChild(node,1);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  /** Create the control-flow graph component for a catch clause.
   * We have two possible normal exits: <ul>
   * <li> the exception is caught (and the block completes normally)
   * <li> the exception is not caught</ul>
   * If the block completes abruptly, its exception <em>cannot</em>
   * be caught in another catch clause of the same try statement.
   */
  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,2,1);
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,1),1,1,1);

    ControlLabel lab = new CaughtExceptionLabel(node);
    ControlNode test = new LabelTest(comp,lab);

    ControlEdge.connect(comp.getEntryPort(),test);
    ControlEdge.connect(test,sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),comp.getNormalExitPort());
    ControlEdge.connect(test,comp.getNormalExitPort());
    ControlEdge.connect(sub.getAbruptExitPort(),comp.getAbruptExitPort());
    return comp;
  }
}
