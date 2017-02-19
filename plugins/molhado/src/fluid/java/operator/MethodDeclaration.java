// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\MethodDeclaration.op.  Do *NOT* edit!
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
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class MethodDeclaration extends ClassBodyDeclaration 
    implements FlowUnit, DripOperator 
    { 
  protected MethodDeclaration() {}

  public static final MethodDeclaration prototype = new MethodDeclaration();

  public Operator superOperator() {
    return ClassBodyDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return ReturnType.prototype;
    case 1: return Parameters.prototype;
    case 2: return Throws.prototype;
    case 3: return OptMethodBody.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 4;
  }

  public static JavaNode createNode(int modifiers,
                                    IRNode returnType,
                                    String id,
                                    IRNode params,
                                    int dims,
                                    IRNode exceptions,
                                    IRNode body) {
    return createNode(tree, modifiers, returnType, id, params, dims, exceptions, body);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int modifiers,
                                    IRNode returnType,
                                    String id,
                                    IRNode params,
                                    int dims,
                                    IRNode exceptions,
                                    IRNode body) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{returnType,params,exceptions,body});
    JavaNode.setModifiers(_result,modifiers);
    JavaNode.setInfo(_result,id);
    JavaNode.setDimInfo(_result,dims);
    return _result;
  }

  public boolean isComplete(IRNode node) {
    if (!super.isComplete(node)) return false;
    try {
      JavaNode.getModifiers(node);
      JavaNode.getInfo(node);
      JavaNode.getDimInfo(node);
    } catch (SlotUndefinedException ex) {
      return false;
    }
    return true;
  }

  public IRNode copyTree(IRNode node) {
    IRNode _result = super.copyTree(node);
    JavaNode.setModifiers(_result,JavaNode.getModifiers(node));
    JavaNode.setInfo(_result,JavaNode.getInfo(node));
    JavaNode.setDimInfo(_result,JavaNode.getDimInfo(node));
    return _result;
  }

  public boolean isEquivalentNode(IRNode n1, IRNode n2) {
    return super.isEquivalentNode(n1, n2) &&
           (JavaNode.getModifiers(n1) == JavaNode.getModifiers(n2)) &&
           (JavaNode.getInfo(n1) == JavaNode.getInfo(n2)) &&
           (JavaNode.getDimInfo(n1) == JavaNode.getDimInfo(n2)) &&
    true;
  }

  public static int getModifiers(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getModifiers() {
    return JavaNode.getModifiers(baseNode);
  }

  public static IRNode getReturnType(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return getReturnType(tree, node);
  }

  public ReturnType getReturnType() {
    return getReturnType(tree);
  }

  public static IRNode getReturnType(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public ReturnType getReturnType(SyntaxTreeInterface tree) {
    return (ReturnType)instantiate(tree.getChild(baseNode,0));
  }

  public static String getId(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getId() {
    return JavaNode.getInfo(baseNode);
  }

  public static IRNode getParams(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return getParams(tree, node);
  }

  public Parameters getParams() {
    return getParams(tree);
  }

  public static IRNode getParams(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return tree.getChild(node,1);
  }

  public Parameters getParams(SyntaxTreeInterface tree) {
    return (Parameters)instantiate(tree.getChild(baseNode,1));
  }

  public static int getDims(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return JavaNode.getDimInfo(node);
  }

  public int getDims() {
    return JavaNode.getDimInfo(baseNode);
  }

  public static IRNode getExceptions(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return getExceptions(tree, node);
  }

  public Throws getExceptions() {
    return getExceptions(tree);
  }

  public static IRNode getExceptions(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return tree.getChild(node,2);
  }

  public Throws getExceptions(SyntaxTreeInterface tree) {
    return (Throws)instantiate(tree.getChild(baseNode,2));
  }

  public static IRNode getBody(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return getBody(tree, node);
  }

  public OptMethodBody getBody() {
    return getBody(tree);
  }

  public static IRNode getBody(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    return tree.getChild(node,3);
  }

  public OptMethodBody getBody(SyntaxTreeInterface tree) {
    return (OptMethodBody)instantiate(tree.getChild(baseNode,3));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
    unparser.unparse(tree.getChild(node,0));
    JavaNode.unparseInfo(node,unparser);
    unparser.getStyle().getNONE().emit(unparser,node);
    unparser.unparse(tree.getChild(node,1));
    JavaNode.unparseDimInfo(node,unparser);
    unparser.unparse(tree.getChild(node,2));
    unparser.unparse(tree.getChild(node,3));
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof MethodDeclaration)) {
      throw new IllegalArgumentException("node not MethodDeclaration: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Token[] tl = JavaNode.getModiferTokens(node);
    if (tl != null && tl.length > 0)
      for (int j = 0; j < tl.length; j++)
        TokenList[0].add(tl[j]);
    tree.getChild(node,0);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[1].add(id);
    tree.getChild(node,1);
    Token tok = JavaNode.getDimToken(node);
    if (tok != null)
      TokenList[2].add(tok);
    tree.getChild(node,2);
    tree.getChild(node,3);
    return TokenList;
  }


  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,0);
    ControlEdge.connect(comp.getEntryPort(),comp.getNormalExitPort());

    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,3),1,1,1);
    ControlNode startNode = new ComponentSource(comp,null);
    ControlNode returnNode = new ComponentSink(comp,Boolean.TRUE);
    ControlNode abruptNode = new ComponentSink(comp,Boolean.FALSE);

    ControlEdge.connect(startNode,sub.getEntryPort());
    ControlEdge.connect(sub.getNormalExitPort(),returnNode);
    ControlEdge.connect(sub.getAbruptExitPort(),abruptNode);
    return comp;
  }

  public Source getSource(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,3));
    ControlEdge e = ((SubcomponentEntryPort)sub.getEntryPort()).getInput();
    return (Source)e.getSource();
  }
  public Sink getNormalSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,3));
    ControlEdge e = ((SubcomponentNormalExitPort)sub.getNormalExitPort()).getOutput();
    return (Sink)(e.getSink());
  }
  public Sink getAbruptSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,3));
    ControlEdge e = ((SubcomponentAbruptExitPort)sub.getAbruptExitPort()).getOutput();
    return (Sink)(e.getSink());
  }
}
