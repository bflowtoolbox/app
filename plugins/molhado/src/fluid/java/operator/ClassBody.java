// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ClassBody.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentSink;
import fluid.control.ComponentSource;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.DynamicSplit;
import fluid.control.Merge;
import fluid.control.Never;
import fluid.control.Sink;
import fluid.control.Source;
import fluid.control.Subcomponent;
import fluid.control.SubcomponentAbruptExitPort;
import fluid.control.SubcomponentEntryPort;
import fluid.control.SubcomponentNormalExitPort;
import fluid.control.VariableComponent;
import fluid.control.VariableSubcomponent;
import fluid.control.VariableSubcomponentControlEdge;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.java.DripOperator;
import fluid.java.JavaComponentFactory;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Delim;
import fluid.unparse.Token;
public class ClassBody extends OptClassBody implements DripOperator, FlowUnit 
    { 
  protected ClassBody() {}

  public static final ClassBody prototype = new ClassBody();

  public Operator superOperator() {
    return OptClassBody.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return ClassBodyDeclaration.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return ClassBodyDeclaration.prototype;
  }

  public Operator variableOperator() {
    return ClassBodyDeclaration.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] decl) {
    return new JavaNode(prototype,decl);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] decl) {
    return new JavaNode(tree, prototype,decl);
  }

  public static IRNode getDecl(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
    }
    return getDecl(tree, node, i);
  }

  public ClassBodyDeclaration getDecl(int i) {
    return getDecl(tree, i);
  }

  public static Enumeration getDeclEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
    }
    return getDeclEnumeration(tree, node);
  }

  public Enumeration getDeclEnumeration() {
    return getDeclEnumeration(tree);
  }

  public static IRNode getDecl(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
    }
    return tree.getChild(node,i);
  }

  public ClassBodyDeclaration getDecl(SyntaxTreeInterface tree, int i) {
    return (ClassBodyDeclaration)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getDeclEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getDeclEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("{");
  private static Token littoken2 = new Delim("}");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
    }
    Enumeration e = tree.children(node);
    littoken1.emit(unparser,node);
    unparser.getStyle().getBLOCK().emit(unparser,node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
      if (!e.hasMoreElements()) break;
      unparser.getStyle().getLI().emit(unparser,node);
    }
    unparser.getStyle().getENDBLOCK().emit(unparser,node);
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassBody)) {
      throw new IllegalArgumentException("node not ClassBody: "+op);
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
    }
    TokenList[i].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  
  /** Create a control-flow graph for the body of the class.
   * This control-flow graph includes two subgraphs,
   * one for the instance initializer and one for the static initializer.
   * This means we have four control-flow edges flowing between
   * components: <ul>
   * <li> 0 - normal execution of static initializer
   * <li> 1 - abrupt termination of static initializer
   * <li> 2 - normal execution of instance initializer
   * <li> 3 - abrupt termination of instance initialize.</ul>
   * <p>
   * The instance initializer is called after the call to the super
   * constructor during analysis.
   * <br><bf>!! The static initializer is ignored in analysis.</bf>
   * @see fluid.java.analysis.JavaTransfer#runClassInitializer
   * </p>
   */
  public Component createComponent(IRNode node) {
    return new ClassBodyComponent(node);
  }

  /* A ClassBody is a flow unit for the instance initializer.
   */

  public Source getSource(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    VariableSubcomponent sub = (VariableSubcomponent)
      comp.getVariableSubcomponent();
    ControlEdge e = sub.getVariableEdge(2,false);
    return (Source)e.getSource();
  }
  public Sink getNormalSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    VariableSubcomponent sub = (VariableSubcomponent)
      comp.getVariableSubcomponent();
    ControlEdge e = sub.getVariableEdge(2,true);
    return (Sink)e.getSink();
  }
  public Sink getAbruptSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    VariableSubcomponent sub = (VariableSubcomponent)
      comp.getVariableSubcomponent();
    ControlEdge e = sub.getVariableEdge(3,true);
    return (Sink)e.getSink();
  }
}

class ClassBodyComponent extends VariableComponent {
  public ClassBodyComponent(IRNode node) {
    super(node,4,1,1,1);
    
    ControlNode staticNever = new Never();

    ControlNode instanceStartNode = new ComponentSource(this,null);
    ControlNode instanceEndNode = new ComponentSink(this,Boolean.TRUE);
    ControlNode instanceAbruptNode = new ComponentSink(this,Boolean.FALSE);
    ControlNode instanceNever = new Never();

    ControlEdge startStatic =
      new VariableSubcomponentControlEdge(variable,0,false,getEntryPort());
    ControlEdge startStaticAbrupt =
      new VariableSubcomponentControlEdge(variable,1,false,staticNever);
    ControlEdge startInstance =
      new VariableSubcomponentControlEdge(variable,2,false,instanceStartNode);
    ControlEdge startInstanceAbrupt =
      new VariableSubcomponentControlEdge(variable,3,false,instanceNever);

    ControlEdge endStatic =
      new VariableSubcomponentControlEdge(variable,0,true,getNormalExitPort());
    ControlEdge endStaticAbrupt =
      new VariableSubcomponentControlEdge(variable,1,true,getAbruptExitPort());
    ControlEdge endInstance =
      new VariableSubcomponentControlEdge(variable,2,true,instanceEndNode);
    ControlEdge endInstanceAbrupt =
      new VariableSubcomponentControlEdge(variable,3,true,instanceAbruptNode);
  } 
  public VariableSubcomponent createVariableSubcomponent(IRLocation loc) {
    return new ClassSubcomponent(this,loc);
  }
}

/** The wrapper around each class body declaration. */
class ClassSubcomponent extends VariableSubcomponent {
  public ClassSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,4);

    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentNormalExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);

    ControlNode checkStatic = new CheckStatic(this);
    ControlNode checkInstance = new CheckStatic(this); // wired in reverse
    ControlNode splitNormal = new CheckStatic(this);
    ControlNode splitAbrupt = new CheckStatic(this);
    ControlNode startMerge = new Merge();
    ControlNode staticNormalMerge = new Merge();
    ControlNode staticAbruptMerge = new Merge();
    ControlNode instanceNormalMerge = new Merge();
    ControlNode instanceAbruptMerge = new Merge();

    ControlEdge staticEntry =
      new VariableSubcomponentControlEdge(this,0,true,checkStatic);
    ControlEdge abruptStaticEntry =
      new VariableSubcomponentControlEdge(this,1,true,staticAbruptMerge);
    ControlEdge instanceEntry =
      new VariableSubcomponentControlEdge(this,2,true,checkInstance);
    ControlEdge abruptInstanceEntry =
      new VariableSubcomponentControlEdge(this,3,true,instanceAbruptMerge);

    ControlEdge.connect(checkStatic,startMerge);
    ControlEdge.connect(checkInstance,instanceNormalMerge);
    ControlEdge.connect(startMerge,entryPort);
    ControlEdge.connect(normalExitPort,splitNormal);
    ControlEdge.connect(splitNormal,staticNormalMerge);
    ControlEdge.connect(splitNormal,instanceNormalMerge);
    ControlEdge.connect(checkStatic,staticNormalMerge);
    ControlEdge.connect(checkInstance,startMerge);

    ControlEdge.connect(abruptExitPort,splitAbrupt);
    ControlEdge.connect(splitAbrupt,staticAbruptMerge);
    ControlEdge.connect(splitAbrupt,instanceAbruptMerge);

    ControlEdge staticExit =
      new VariableSubcomponentControlEdge(this,0,false,staticNormalMerge);
    ControlEdge abruptStaticExit =
      new VariableSubcomponentControlEdge(this,1,false,staticAbruptMerge);
    ControlEdge instanceExit =
      new VariableSubcomponentControlEdge(this,2,false,instanceNormalMerge);
    ControlEdge abruptInstanceExit =
      new VariableSubcomponentControlEdge(this,3,false,instanceAbruptMerge);
  }
}

class CheckStatic extends DynamicSplit {
  private final Subcomponent subcomponent;
  public CheckStatic(Subcomponent sub) {
    subcomponent = sub;
  }
  public boolean test(boolean flag) {
    return flag == JavaNode.getModifier(subcomponent.getSyntax(),
					JavaNode.STATIC);
  }
}
