// Generated from C:\eclipse\workspace\fluid\code\fluid\java\operator\SwitchBlock.op.  Do *NOT* edit!
package fluid.java.operator;

import java.util.Enumeration;
import java.util.Vector;

import fluid.control.Component;
import fluid.control.ControlEdge;
import fluid.control.ControlNode;
import fluid.control.DynamicSplit;
import fluid.control.Merge;
import fluid.control.Never;
import fluid.control.NoOperation;
import fluid.control.SubcomponentAbruptExitPort;
import fluid.control.SubcomponentChoice;
import fluid.control.SubcomponentEntryPort;
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
public class SwitchBlock extends JavaOperator { 
  protected SwitchBlock() {}

  public static final SwitchBlock prototype = new SwitchBlock();

  public boolean isProduction() {
    return true;
  }

  public Operator childOperator(int i) {
    return SwitchElement.prototype;
  }

  public Operator childOperator(IRLocation loc) {
    return SwitchElement.prototype;
  }

  public Operator variableOperator() {
    return SwitchElement.prototype;
  }

  public int numChildren() {
    return -1;
  }

  public static JavaNode createNode(IRNode[] element) {
    return new JavaNode(prototype,element);
  }

  public static JavaNode createNode(SyntaxTreeInterface tree, IRNode[] element) {
    return new JavaNode(tree, prototype,element);
  }

  public static IRNode getElement(IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
    }
    return getElement(tree, node, i);
  }

  public SwitchElement getElement(int i) {
    return getElement(tree, i);
  }

  public static Enumeration getElementEnumeration(IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
    }
    return getElementEnumeration(tree, node);
  }

  public Enumeration getElementEnumeration() {
    return getElementEnumeration(tree);
  }

  public static IRNode getElement(SyntaxTreeInterface tree, IRNode node, int i) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
    }
    return tree.getChild(node,i);
  }

  public SwitchElement getElement(SyntaxTreeInterface tree, int i) {
    return (SwitchElement)instantiate(tree.getChild(baseNode,i));
  }

  public static Enumeration getElementEnumeration(SyntaxTreeInterface tree, IRNode node) {
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
    }
    return tree.children(node);
  }

  public Enumeration getElementEnumeration(SyntaxTreeInterface tree) {
    return instantiate(tree.children(baseNode));
  }

  private static Token littoken1 = new Delim("{");
  private static Token littoken2 = new Delim("}");

  public void unparse(IRNode node, JavaUnparser unparser) {
SyntaxTreeInterface tree = unparser.getTree();
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
    }
    Enumeration e = tree.children(node);
    littoken1.emit(unparser,node);
    while (e.hasMoreElements()) {
      unparser.unparse((IRNode)e.nextElement());
    }
    littoken2.emit(unparser,node);
  }

  public boolean isMissingTokens(IRNode node)  {
    return true;
  }

  public Vector[] missingTokens(IRNode node) {
    SyntaxTreeInterface tree = JavaNode.tree;
    Operator op = tree.getOperator(node);
    if (!(op instanceof SwitchBlock)) {
      throw new IllegalArgumentException("node not SwitchBlock: "+op);
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
    }
    TokenList[i].add(littoken2);
    return TokenList;
  }

  public Token asToken() {
    return littoken1;
  }

  /** Create the control-flow graph component for a (possibly empty)
   * series of switch block elements.  Each switch element is wrapped
   * in a variable subcomponent that gives each switch element a chance
   * to execute under specific conditions.  We have four edges between
   * variable subcomponents: <ul>
   * <li> 0 - execution before a label is found.  Looking for an exact match
   * <li> 1 - execution before default is found.  
   *          Looking for default, if it exists
   * <li> 2 - execution of regular statements in the sequence.
   * <li> 3 - collection of abrupt termination.</ul>
   */
  public Component createComponent(IRNode node) {
    return new SwitchBlockComponent(node);
  }
}

class SwitchBlockComponent extends VariableComponent {
  public SwitchBlockComponent(IRNode node) {
    super(node,4,1,1,1);

    ControlNode endMerge = new Merge();
    ControlNode never2 = new Never();
    ControlNode never3 = new Never();
    ControlNode nop = new NoOperation();

    ControlEdge startSearch =
      new VariableSubcomponentControlEdge(variable,0,false,entryPort);
    ControlEdge findDefault =
      new VariableSubcomponentControlEdge(variable,1,false,nop);
    ControlEdge execIn =
      new VariableSubcomponentControlEdge(variable,2,false,never2);
    ControlEdge abruptIn =
      new VariableSubcomponentControlEdge(variable,3,false,never3);

    ControlEdge endSearch =
      new VariableSubcomponentControlEdge(variable,0,true,nop);
    ControlEdge noDefault =
      new VariableSubcomponentControlEdge(variable,1,true,endMerge);
    ControlEdge endStmts =
      new VariableSubcomponentControlEdge(variable,2,true,endMerge);
    ControlEdge abruptOut =
      new VariableSubcomponentControlEdge(variable,3,true,abruptExitPort);

    ControlEdge.connect(endMerge,normalExitPort);
  }

  public VariableSubcomponent createVariableSubcomponent(IRLocation loc) {
    return new SwitchSubcomponent(this,loc);
  }
}

/** The wrapper in a switch statement around each element.
 * There are four edges into each instance: <ul>
 * <li> 0 - execution before a label is found.  Looking for an exact match
 * <li> 1 - execution before default is found.  
 *          Looking for default, if it exists
 * <li> 2 - execution of regular statements in the sequence.
 * <li> 3 - collection of abrupt termination.</ul>
 */
class SwitchSubcomponent extends VariableSubcomponent {
  public SwitchSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,4);
    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentNormalExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);
    
    ControlNode checkConstant = new DynamicSplit() {
      public boolean test(boolean flag) {
	IRNode label = SwitchElement.getLabel(getSyntax());
	return flag == (JavaNode.tree.getOperator(label) == ConstantLabel.prototype);
      }
    };

    ControlNode checkDefault = new DynamicSplit() {
      public boolean test(boolean flag) {
	IRNode label = SwitchElement.getLabel(getSyntax());
	return flag == (JavaNode.tree.getOperator(label) == DefaultLabel.prototype);
      }
    };

    ControlNode testConstant =
      new SubcomponentChoice(this,ConstantLabel.prototype);

    ControlNode foundMerge = new Merge();
    ControlNode execMerge = new Merge();
    ControlNode notFoundMerge = new Merge();
    ControlNode abruptMerge = new Merge();

    ControlEdge findLabel =
      new VariableSubcomponentControlEdge(this,0,true,checkConstant);
    ControlEdge findDefault =
      new VariableSubcomponentControlEdge(this,1,true,checkDefault);
    ControlEdge startExec =
      new VariableSubcomponentControlEdge(this,2,true,execMerge);
    ControlEdge abruptEntry =
      new VariableSubcomponentControlEdge(this,3,true,abruptMerge);

    ControlEdge.connect(checkConstant,testConstant);
    ControlEdge.connect(checkConstant,notFoundMerge);
    ControlEdge.connect(testConstant,foundMerge);
    ControlEdge.connect(testConstant,notFoundMerge);
    ControlEdge.connect(checkDefault,foundMerge);
    ControlEdge.connect(foundMerge,execMerge);
    ControlEdge.connect(execMerge,entryPort);
    ControlEdge.connect(abruptExitPort,abruptMerge);

    ControlEdge notLabel =
      new VariableSubcomponentControlEdge(this,0,false,notFoundMerge);
    ControlEdge notDefault =
      new VariableSubcomponentControlEdge(this,1,false,checkDefault);
    ControlEdge stopExec =
      new VariableSubcomponentControlEdge(this,2,false,normalExitPort);
    ControlEdge abruptExit =
      new VariableSubcomponentControlEdge(this,3,false,abruptMerge);
  }
}
