// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\StatementExpressionList.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Never;
import fluid.control.SubcomponentAbruptExitPort;
import fluid.control.SubcomponentEntryPort;
import fluid.control.SubcomponentFlow;
import fluid.control.SubcomponentNormalExitPort;
import fluid.control.VariableComponent;
import fluid.control.VariableSubcomponent;
import fluid.control.VariableSubcomponentControlEdge;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
/** Comma separated expressions used in for loops.
 * It permits an empty list.
 */
public class StatementExpressionList extends JavaOperator implements ForInitInterface { 
  protected StatementExpressionList() {}

  public static final StatementExpressionList prototype = new StatementExpressionList();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return StatementExpression.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return StatementExpression.prototype;
  }

  public Operator variableOperator() {
    return StatementExpression.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] expr) {
    return new JavaNode(prototype,expr);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] expr) {
    return new JavaNode(tree, prototype,expr);
  }

  public static IRNode getExpr(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    return getExpr(tree, node, i);
  }

  public StatementExpressionInterface getExpr(int i) {
    return getExpr(tree, i);
  }

  public static Enumeration getExprEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    return getExprEnumeration(tree, node);
  }

  public Enumeration getExprEnumeration() {
    return getExprEnumeration(tree);
  }

  public static IRNode getExpr(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    return tree.getChild(node,i);
  }

  public StatementExpressionInterface getExpr(SyntaxTreeInterface tree, int i) {
    return (StatementExpressionInterface)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getExprEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getExprEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim(",");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    Enumeration e = tree.children(node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      if (!e.hasMoreElements()) break;
      unparser.getStyle().getNONE().emit(unparser,node);
      littoken1.emit(unparser,node);
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof StatementExpressionList)) {
      throw new IllegalArgumentException("node not StatementExpressionList: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    while (e.hasMoreElements()) {
      e.nextElement();
      i++;
      if (!e.hasMoreElements()) break;
      TokenList[i].add(littoken1);
    }
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  public Component createComponent(IRNode node) {
    /** execute each child in turn and discard its value. */
    return new StatementExpressionListComponent(node);
  }
}

class StatementExpressionListComponent extends VariableComponent {
  public StatementExpressionListComponent(IRNode node) {
    super(node,2,1,1,1);
    ControlNode never = new Never();

    ControlEdge startExec =
      new VariableSubcomponentControlEdge(variable,0,false,entryPort);
    ControlEdge startAbrupt =
      new VariableSubcomponentControlEdge(variable,1,false,never);
    
    ControlEdge endExec =
      new VariableSubcomponentControlEdge(variable,0,true,normalExitPort);
    ControlEdge endAbrupt =
      new VariableSubcomponentControlEdge(variable,1,true,abruptExitPort);
  }
  
  protected VariableSubcomponent createVariableSubcomponent(IRLocation loc) {
    return new StatementExpressionListSubcomponent(this,loc);
  }
}

class StatementExpressionListSubcomponent extends VariableSubcomponent {
  public StatementExpressionListSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,2);
    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentNormalExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);
    ControlNode merge = new Merge();
    ControlNode discard = new SubcomponentFlow(this,null);
    
    ControlEdge.connect(abruptExitPort,merge);
    ControlEdge.connect(normalExitPort,discard);

    ControlEdge normalEntry = 
      new VariableSubcomponentControlEdge(this,0,true,entryPort);
    ControlEdge abruptEntry =
      new VariableSubcomponentControlEdge(this,1,true,merge);
    ControlEdge normalExit =
      new VariableSubcomponentControlEdge(this,0,false,discard);
    ControlEdge abruptExit =
      new VariableSubcomponentControlEdge(this,1,false,merge);
  }
}

