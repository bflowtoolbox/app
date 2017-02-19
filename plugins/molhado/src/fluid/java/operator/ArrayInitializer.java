// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ArrayInitializer.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentFlow;
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
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ArrayInitializer extends Initializer 
    implements OptArrayInitializerInterface 
    { 
  protected ArrayInitializer() {}

  public static final ArrayInitializer prototype = new ArrayInitializer();

  public Operator superOperator() {
    return Initializer.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return Initializer.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return Initializer.prototype;
  }

  public Operator variableOperator() {
    return Initializer.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] child0) {
    return new JavaNode(prototype,child0);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] child0) {
    return new JavaNode(tree, prototype,child0);
  }

  public static IRNode getChild0(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    return getChild0(tree, node, i);
  }

  public Initializer getChild0(int i) {
    return getChild0(tree, i);
  }

  public static Enumeration getChild0Enumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    return getChild0Enumeration(tree, node);
  }

  public Enumeration getChild0Enumeration() {
    return getChild0Enumeration(tree);
  }

  public static IRNode getChild0(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    return tree.getChild(node,i);
  }

  public Initializer getChild0(SyntaxTreeInterface tree, int i) {
    return (Initializer)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getChild0Enumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getChild0Enumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("{");
  private static Token littoken2 = new Delim(",");
  private static Token littoken3 = new Delim("}");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    Enumeration e = tree.children(node);
    littoken1.emit(unparser,node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      if (!e.hasMoreElements()) break;
      littoken2.emit(unparser,node);
    }
    littoken3.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ArrayInitializer)) {
      throw new IllegalArgumentException("node not ArrayInitializer: "+op);
    }
    Enumeration e = tree.children(node);
    int i = 0;
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    TokenList[i].add(littoken1);
    while (e.hasMoreElements()) {
      e.nextElement();
      i++;
      if (!e.hasMoreElements()) break;
      TokenList[i].add(littoken2);
    }
    TokenList[i].add(littoken3);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  public Component createComponent(IRNode node) {
    return new ArrayInitializerComponent(node);
  }
}

class ArrayInitializerComponent extends VariableComponent {
  public ArrayInitializerComponent(IRNode node) {
    super(node,2,1,1,1);
    ControlNode maybeAlloc = new ComponentFlow(this,null);
    ControlNode never = new Never();

    ControlEdge.connect(entryPort,maybeAlloc);
    
    ControlEdge startExec =
      new VariableSubcomponentControlEdge(variable,0,false,maybeAlloc);
    ControlEdge startAbrupt =
      new VariableSubcomponentControlEdge(variable,1,false,never);
    
    ControlEdge endExec =
      new VariableSubcomponentControlEdge(variable,0,true,normalExitPort);
    ControlEdge endAbrupt =
      new VariableSubcomponentControlEdge(variable,1,true,abruptExitPort);
  }
  
  protected VariableSubcomponent createVariableSubcomponent(IRLocation loc) {
    return new ArrayInitializerSubcomponent(this,loc);
  }
}

class ArrayInitializerSubcomponent extends VariableSubcomponent {
  public ArrayInitializerSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,2);
    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentNormalExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);
    ControlNode merge = new Merge();
    ControlNode store = new SubcomponentFlow(this,AssignExpression.prototype);
    
    ControlEdge.connect(abruptExitPort,merge);
    ControlEdge.connect(normalExitPort,store);

    ControlEdge normalEntry = 
      new VariableSubcomponentControlEdge(this,0,true,entryPort);
    ControlEdge abruptEntry =
      new VariableSubcomponentControlEdge(this,1,true,merge);
    ControlEdge normalExit =
      new VariableSubcomponentControlEdge(this,0,false,store);
    ControlEdge abruptExit =
      new VariableSubcomponentControlEdge(this,1,false,merge);
  }
}

