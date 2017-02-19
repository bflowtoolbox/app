/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Component.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import fluid.FluidRuntimeException;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.tree.SyntaxTree;

/** A region of control nodes in the graph corresponding to a 
 * syntactic entity.  Regions are strictly nested and control cannot
 * enter or leave the region except through special edges
 * identified by Ports.
 * @see Port
 * @see Subcomponent
 */

public class Component {
  /** The syntax node for this component. */
  protected IRNode syntax;

  ComponentFactory factory;

  /** The syntax tree for control-flow graph component nodes.
   * <b>This should be a parameter, not a constant.</b>
   */
  protected static final SyntaxTree tree = fluid.parse.JJNode.tree;

  public void registerFactory(ComponentFactory cf) {
    factory = cf;
  }

  public Component getComponent(IRNode node) {
    return factory.getComponent(node);
  }

  /** The three ports: one for entry, one for normal exit
   * and one for exceptions (abrupt exit).
   */ 
  protected ComponentPort entryPort, normalExitPort, abruptExitPort;

  public Component(IRNode node) {
    syntax = node;
  }
  
  public Component(IRNode node, int inputs, int outputs, int abrupts) {
    this(node);
    if (inputs == 0) {
      entryPort = new ComponentBlankEntryPort(this); // redundant assignment
    } else if (inputs == 1) {
      entryPort = new ComponentEntryPort(this);
    } else {
      throw new FluidRuntimeException("bad number of entry ports");
    }
    if (outputs == 2) {
      normalExitPort = new ComponentBooleanExitPort(this);
    } else if (outputs == 1) {
      normalExitPort = new ComponentNormalExitPort(this);
    } else if (outputs == 0) {
      normalExitPort = new ComponentBlankNormalExitPort(this);
    } else {
      throw new FluidRuntimeException("bad number of normal exit ports");
    }
    if (abrupts == 0) {
      abruptExitPort = new ComponentBlankAbruptExitPort(this);
    } else if (abrupts == 1) {
      abruptExitPort = new ComponentAbruptExitPort(this);
    } else {
      throw new FluidRuntimeException("bad number of abrupt exit ports");
    }
  }
  
  void registerEntryPort(ComponentPort entry) {
    entryPort = entry;
  }  

  void registerNormalExitPort(ComponentPort normalExit) {
    normalExitPort = normalExit;
  }  

  void registerAbruptExitPort(ComponentPort abruptExit) {
    abruptExitPort = abruptExit;
  }  

  /** Subcomponents within the region.
   * Each subcomponent represents a wrapper.
   * @type Hashtable[IRLocation,Subcomponent]
   */
  protected Hashtable subcomponents;
  
  void registerSubcomponent(IRLocation loc, Subcomponent sub) {
    if (subcomponents == null) subcomponents = new Hashtable();
    if (loc == null) return; // the variable subcomponent
    subcomponents.put(loc,sub);
  }

  /** Return the subcomponent associated with the given location.
   * For variable arity nodes, this method should be overridden
   * in language specific subclasses.
   * @param loc
   * The location associated with this subcomponent.
   */
  public Subcomponent getSubcomponent(IRLocation loc) {
    Subcomponent sub = (Subcomponent)subcomponents.get(loc);
    return sub;
  }
  
  /** Return the subcomponent associated with the start
   * and end of the sequence in a variable-arity node.
   * (Override in language specific, node specific component classes.)
   */
  public Subcomponent getVariableSubcomponent() {
    return null;
  }
  
  /** Return the subcomponent in the component of our parent. */
  public Subcomponent getSubcomponentInParent() {
    IRNode parent = tree.getParent(syntax);
    if (parent == null) return null;
    return getComponent(parent).getSubcomponent(tree.getLocation(syntax));
  }

  /** Return the node associated with this component */
  public IRNode getSyntax() {
    return syntax;
  }
  
  /** Return the start port. */
  public ComponentPort getEntryPort() {
    return entryPort;
  }

  /** Return the normal exit port. */
  public ComponentPort getNormalExitPort() {
    return normalExitPort;
  }

  /** Return the abrupt exit port */
  public ComponentPort getAbruptExitPort() {
    return abruptExitPort;
  }

  /** Verify a component that all pieces are put together correctly.
   * Currently we check simply that no edges are null.
   * We traverse forward from input ports and backward from output ports.
   */
  public boolean isValid() {
    Stack s = new Stack();
    Vector visited = new Vector(); // cannot be hashtable because of proxies
    s.push(getEntryPort());
    s.push(getNormalExitPort());
    s.push(getAbruptExitPort());
    outer : while (!s.empty()) {
      ControlNode node = (ControlNode)s.pop();
      if (node == null) return false;
      /** cannot use contains because of proxies: **/
      for (int i=visited.size(); i > 0; --i) {
	if (visited.elementAt(i-1) == node) continue outer;
      }
      visited.addElement(node);
      if (!(node instanceof InputPort)) {
	ControlEdgeEnumeration more = node.getInputs();
	while (more.hasMoreElements()) {
	  ControlEdge e = more.nextControlEdge();
	  if (e == null) return false;
	  s.push(e.getSource());
	}
      }
      if (!(node instanceof OutputPort)) {
	ControlEdgeEnumeration more = node.getOutputs();
	while (more.hasMoreElements()) {
	  ControlEdge e = more.nextControlEdge();
	  if (e == null) return false;
	  s.push(e.getSink());
	}
      }
    }
    return true;
  }
}





