// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\ConstructorDeclaration.op.  Do *NOT* edit!
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
import fluid.java.JavaComponentFactory;
import fluid.java.JavaNode;
import fluid.java.JavaUnparser;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Identifier;
import fluid.unparse.Token;
public class ConstructorDeclaration extends ClassBodyDeclaration 
    implements FlowUnit 
    { 
  protected ConstructorDeclaration() {}

  public static final ConstructorDeclaration prototype = new ConstructorDeclaration();

  public Operator superOperator() {
    return ClassBodyDeclaration.prototype;
  }

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    switch (i) {
    case 0: return Parameters.prototype;
    case 1: return Throws.prototype;
    case 2: return OptMethodBody.prototype;
    default: return null;
    }
  }

  public int numChildren() {
    return 3;
  }

  public static JavaNode createNode(int modifiers,
                                    String name,
                                    IRNode params,
                                    IRNode exceptions,
                                    IRNode body) {
    return createNode(tree, modifiers, name, params, exceptions, body);
  }
  public static JavaNode createNode(SyntaxTreeInterface tree, int modifiers,
                                    String name,
                                    IRNode params,
                                    IRNode exceptions,
                                    IRNode body) {
    JavaNode _result = new JavaNode(tree, prototype, new IRNode[]{params,exceptions,body});
    JavaNode.setModifiers(_result,modifiers);
    JavaNode.setInfo(_result,name);
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

  public static int getModifiers(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return JavaNode.getModifiers(node);
  }

  public int getModifiers() {
    return JavaNode.getModifiers(baseNode);
  }

  public static String getName(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return JavaNode.getInfo(node);
  }

  public String getName() {
    return JavaNode.getInfo(baseNode);
  }

  public static IRNode getParams(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return getParams(tree, node);
  }

  public Parameters getParams() {
    return getParams(tree);
  }

  public static IRNode getParams(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return tree.getChild(node,0);
  }

  public Parameters getParams(SyntaxTreeInterface tree) {
    return (Parameters)instantiate(tree.getChild(baseNode,0));
  }

  public static IRNode getExceptions(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return getExceptions(tree, node);
  }

  public Throws getExceptions() {
    return getExceptions(tree);
  }

  public static IRNode getExceptions(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return tree.getChild(node,1);
  }

  public Throws getExceptions(SyntaxTreeInterface tree) {
    return (Throws)instantiate(tree.getChild(baseNode,1));
  }

  public static IRNode getBody(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return getBody(tree, node);
  }

  public OptMethodBody getBody() {
    return getBody(tree);
  }

  public static IRNode getBody(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    return tree.getChild(node,2);
  }

  public OptMethodBody getBody(SyntaxTreeInterface tree) {
    return (OptMethodBody)instantiate(tree.getChild(baseNode,2));
  }

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    JavaNode.unparseModifiers(node,unparser);
    JavaNode.unparseInfo(node,unparser);
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
    if (!(op instanceof ConstructorDeclaration)) {
      throw new IllegalArgumentException("node not ConstructorDeclaration: "+op);
    }
    int numChildren = tree.numChildren(node);
    Vector[] TokenList = new Vector[numChildren+1];
    for (int j = 0; j < numChildren + 1; j++)
       TokenList[j] = new Vector();
    Token[] tl = JavaNode.getModiferTokens(node);
    if (tl != null && tl.length > 0)
      for (int j = 0; j < tl.length; j++)
        TokenList[0].add(tl[j]);
    Identifier id = new Identifier(JavaNode.getInfo(node).toString());
    TokenList[0].add(id);
    tree.getChild(node,0);
    tree.getChild(node,1);
    tree.getChild(node,2);
    return TokenList;
  }



  public Component createComponent(IRNode node) {
    Component comp = new Component(node,1,1,0);
    ControlEdge.connect(comp.getEntryPort(),comp.getNormalExitPort());

    Subcomponent sub = new Subcomponent(comp,tree.childLocation(node,2),1,1,1);
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
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentEntryPort)sub.getEntryPort()).getInput();
    return (Source)e.getSource();
  }
  public Sink getNormalSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentNormalExitPort)sub.getNormalExitPort()).getOutput();
    return (Sink)(e.getSink());
  }
  public Sink getAbruptSink(IRNode node) {
    Component comp = JavaComponentFactory.prototype.getComponent(node);
    Subcomponent sub = comp.getSubcomponent(tree.childLocation(node,2));
    ControlEdge e = ((SubcomponentAbruptExitPort)sub.getAbruptExitPort()).getOutput();
    return (Sink)(e.getSink());
  }
}
