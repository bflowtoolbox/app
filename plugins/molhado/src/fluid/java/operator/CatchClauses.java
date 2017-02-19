// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\CatchClauses.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Merge;
import fluid.control.Never;
import fluid.control.SubcomponentAbruptExitPort;
import fluid.control.SubcomponentBooleanExitPort;
import fluid.control.SubcomponentEntryPort;
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
public class CatchClauses extends JavaOperator { 
  protected CatchClauses() {}

  public static final CatchClauses prototype = new CatchClauses();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return CatchClause.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return CatchClause.prototype;
  }

  public Operator variableOperator() {
    return CatchClause.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] catchClause) {
    return new JavaNode(prototype,catchClause);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] catchClause) {
    return new JavaNode(tree, prototype,catchClause);
  }

  public static IRNode getCatchClause(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
    }
    return getCatchClause(tree, node, i);
  }

  public CatchClause getCatchClause(int i) {
    return getCatchClause(tree, i);
  }

  public static Enumeration getCatchClauseEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
    }
    return getCatchClauseEnumeration(tree, node);
  }

  public Enumeration getCatchClauseEnumeration() {
    return getCatchClauseEnumeration(tree);
  }

  public static IRNode getCatchClause(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
    }
    return tree.getChild(node,i);
  }

  public CatchClause getCatchClause(SyntaxTreeInterface tree, int i) {
    return (CatchClause)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getCatchClauseEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getCatchClauseEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  public void unparseWrapper(IRNode node, JavaUnparser unparser) {
    if (unparser.getTree().numChildren(node) > 0) super.unparseWrapper(node,unparser);
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
    }
    Enumeration e = tree.children(node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
    }
  }

  public boolean isMissingTokens(IRNode node)  {
    if (JavaNode.tree.numChildren(node) > 0) return true;
    else return false;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof CatchClauses)) {
      throw new IllegalArgumentException("node not CatchClauses: "+op);
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
    }
    return TokenList;
  }

  /** Create the control-flow graph component for a (possibly empty)
   * series of catch clauses.  Each catch clause is wrapped in a variable
   * subcomponent that gives each catch clause a chance at the given
   * exception.  We need three edges between variable subcomponents: <ul>
   * <li> 0 - the current exception is tested here
   * <li> 1 - after an exception is caught, it is passed through here.
   * <li> 2 - abrupt completion of a catch clause goes here. </ul>
   */
  public Component createComponent(IRNode node) {
    return new CatchClausesComponent(node);
  }
}

class CatchClausesComponent extends VariableComponent {
  CatchClausesComponent(IRNode node) {
    super(node,3,1,1,1);

    ControlNode never1 = new Never();
    ControlNode never2 = new Never();
    ControlNode abruptMerge = new Merge();

    ControlEdge startCatch =
      new VariableSubcomponentControlEdge(variable,0,false,entryPort);
    ControlEdge execIn =
      new VariableSubcomponentControlEdge(variable,1,false,never1);
    ControlEdge abruptIn =
      new VariableSubcomponentControlEdge(variable,2,false,never2);

    ControlEdge endCatch =
      new VariableSubcomponentControlEdge(variable,0,true,abruptMerge);
    ControlEdge execOut =
      new VariableSubcomponentControlEdge(variable,1,true,normalExitPort);
    ControlEdge abruptOut =
      new VariableSubcomponentControlEdge(variable,2,true,abruptMerge);

    ControlEdge.connect(abruptMerge,abruptExitPort);
  }

  protected VariableSubcomponent createVariableSubcomponent(IRLocation loc) {
    return new CatchClauseSubcomponent(this,loc);
  }
}

class CatchClauseSubcomponent extends VariableSubcomponent {
  public CatchClauseSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,3);

    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentBooleanExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);

    ControlNode execMerge = new Merge();
    ControlNode abruptMerge = new Merge();

    ControlEdge findCatch =
      new VariableSubcomponentControlEdge(this,0,true,entryPort);
    ControlEdge inExec =
      new VariableSubcomponentControlEdge(this,1,true,execMerge);
    ControlEdge inAbrupt =
      new VariableSubcomponentControlEdge(this,2,true,abruptMerge);

    ControlEdge.connect(normalExitPort,execMerge);
    ControlEdge.connect(abruptExitPort,abruptMerge);
    
    ControlEdge notCaught =
      new VariableSubcomponentControlEdge(this,0,false,normalExitPort);
    ControlEdge outExec =
      new VariableSubcomponentControlEdge(this,1,false,execMerge);
    ControlEdge outAbrupt =
      new VariableSubcomponentControlEdge(this,2,false,abruptMerge);
  }
}
