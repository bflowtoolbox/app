// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ClassDeclaration.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Vector;

import fluid.control.Component;
import fluid.control.ComponentSink;
import fluid.control.ComponentSource;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.Sink;
import fluid.control.Source;
import fluid.control.Subcomponent;
import fluid.control.SubcomponentAbruptExitPort;
import fluid.control.SubcomponentEntryPort;
import fluid.control.SubcomponentNormalExitPort;
import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.java.DripOperator;
import fluid.java.JavaComponentFactory;
import fluid.java.JavaNode;
import fluid.java.JavaOperator;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
public class ClassDeclaration extends JavaOperator implements TypeDeclInterface, DripOperator, FlowUnit 
    { 
  protected ClassDeclaration() {}

  public static final ClassDeclaration prototype = new ClassDeclaration();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return NamedType.prototype;
    case 1: return Implements.prototype;
    case 2: return ClassBody.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(int mods,
                                    String id,
                                    IRNode extension,
                                    IRNode impls,
                                    IRNode body) {
    return createNode(tree, mods, id, extension, impls, body);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int mods,
                                    String id,
                                    IRNode extension,
                                    IRNode impls,
                                    IRNode body) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{extension,impls,body});
    JavaNode.setModifiers(_result,mods);
    JavaNode.setInfo(_result,id);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getModifiers(node);
      JavaNode.getInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setModifiers(_result,JavaNode.getModifiers(node));
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getModifiers(n1) == JavaNode.getModifiers(n2)) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
    true;
  }

  public static int getMods(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getMods() {
    return JavaNode.getModifiers(baseNode);
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  public static IRNode getExtension(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return getExtension(tree, node);
  }

  public NamedType getExtension() {
    return getExtension(tree);
  }

  public static IRNode getExtension(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public NamedType getExtension(SyntaxTreeInterface tree) {
    return (NamedType)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getImpls(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return getImpls(tree, node);
  }

  public Implements getImpls() {
    return getImpls(tree);
  }

  public static IRNode getImpls(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return tree.getChild(node,1);
  }

  public Implements getImpls(SyntaxTreeInterface tree) {
    return (Implements)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getBody(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return getBody(tree, node);
  }

  public ClassBody getBody() {
    return getBody(tree);
  }

  public static IRNode getBody(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    return tree.getChild(node,2);
  }

  public ClassBody getBody(SyntaxTreeInterface tree) {
    return (ClassBody)instantiate(tree.getChild(baseNode,2));
  }

  private static Token littoken1 = new Keyword("class");
  private static Token littoken2 = new Keyword("extends");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
    littoken1.emit(unparser,node);
    JavaNode.unparseInfo(node,unparser);
    littoken2.emit(unparser,node);
    unparser.unparse(tree.getChild(node,0));
    unparser.unparse(tree.getChild(node,1));
    unparser.unparse(tree.getChild(node,2));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof ClassDeclaration)) {
      throw new IllegalArgumentException("node not ClassDeclaration: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Token[] tl = JavaNode.getModiferTokens(node);
    if (tl != null && tl.length > 0)
      for (int j = 0; j < tl.length; j++)
        TokenList[0].add(tl[j]);
    TokenList[0].add(littoken1);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    TokenList[0].add(littoken2);
    tree.getChild(node,0);
    tree.getChild(node,1);
    tree.getChild(node,2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }


  /** Use the interface to determine what operators are legal:
   */
  public boolean includes(Operator other) {
    return (other instanceof ClassDeclaration) ||
	   (other instanceof NestedClassDeclaration);
  }

  public Component createComponent(IRNode node) {
    Component comp = new Component(node,0,0,0);

    /* the class body executes the static initializer */
    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,2),1,1,1);
    ControlNode startNode = new ComponentSource(comp,null);
    ControlNode endNode = new ComponentSink(comp,Boolean.TRUE);
    ControlNode abruptNode = new ComponentSink(comp,Boolean.FALSE);

    ControlEdge.connect(startNode,sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),endNode);
    ControlEdge.connect(sub.getAbruptExitPort(),abruptNode);

    return comp;
  }

  public Source getSource(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentEntryPort)sub.getEntryPort()).getInput();
    return (Source)e.getSource();
  }
  public Sink getNormalSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentNormalExitPort)sub.getNormalExitPort()).getOutput();
    return (Sink)e.getSink();
  }
  public Sink getAbruptSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentAbruptExitPort)sub.getAbruptExitPort()).getOutput();
    return (Sink)e.getSink();
  }
}
