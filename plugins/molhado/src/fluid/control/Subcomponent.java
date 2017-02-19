package fluid.control;

import fluid.FluidRuntimeException;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.tree.SyntaxTree;

/** A region of control nodes in the graph corresponding to the
 * child of a syntactic entity.  This structure serves as a wrapper
 * around the region of the child node itself (a Component).
 * We do not refer directly to the component, leaving that to
 * be computed on demand.
 * <P>  This structure maintains a set of ports that are used as
 * proxies of the ports on the child's component.  The ports
 * are all of type SubcomponentPort. </P>
 * @see Port
 * @see Component
 * @see VariableSubcomponent
 */

public class Subcomponent {
  /** The component this subcomponent is nested in. */
  protected Component component;
  
  /** The location within the component that this subcomponent
   * occupies.
   */
  protected IRLocation location;

  /** The three ports: one for entry, one for normal exit
   * and one for exceptions (abrupt exit).
   */ 
  protected SubcomponentPort entryPort, normalExitPort, abruptExitPort;

  /** The syntax tree for control-flow graph subcomponent nodes.
   * <b>This should be a parameter, not a constant.</b>
   */
  protected static final SyntaxTree tree = fluid.parse.JJNode.tree;

  public Subcomponent(Component comp, IRLocation loc) {
    component = comp;
    location = loc;
    component.registerSubcomponent(loc,this);
  }

  public Subcomponent(Component comp, IRLocation loc, 
  		      int inputs, int outputs, int abrupts) 
  {
    this(comp,loc);
    if (inputs == 1) {
      entryPort = new SubcomponentEntryPort(this); // redundant assignment
    } else {
      throw new FluidRuntimeException("bad number of entry ports");
    }
    if (outputs == 2) {
      normalExitPort = new SubcomponentBooleanExitPort(this);
    } else if (outputs == 1) {
      normalExitPort = new SubcomponentNormalExitPort(this);
    } else {
      throw new FluidRuntimeException("bad number of normal exit ports");
    }
    if (abrupts == 1) {
      abruptExitPort = new SubcomponentAbruptExitPort(this);
    } else {
      throw new FluidRuntimeException("bad number of abrupt exit ports");
    }
  }

  void registerEntryPort(SubcomponentPort entry) {
    entryPort = entry;
  }

  void registerNormalExitPort(SubcomponentPort normalExit) {
    normalExitPort = normalExit;
  }

  void registerAbruptExitPort(SubcomponentPort abruptExit) {
    abruptExitPort = abruptExit;
  }

  /** Return the component context. */
  public Component getComponent() {
    return component;
  }
  
  /** Return the location of the child for this subcomponent */
  public IRLocation getLocation() {
    return location;
  }
  
  /** Return the component which is the dual to this. */
  public Component getComponentInChild() {
    IRNode child = getSyntax();
    if (child == null) return null;
    return component.getComponent(child);
  }

  /** Return the node this subcomponent wraps. */
  public IRNode getSyntax() {
    IRNode node = component.getSyntax();
    IRNode child = tree.getChild(component.getSyntax(),location);
    return child;
  }

  /** Return the start port. */
  public SubcomponentPort getEntryPort() {
    return entryPort;
  }

  /** Return the normal exit port. */
  public SubcomponentPort getNormalExitPort() {
    return normalExitPort;
  }

  /** Return the abrupt exit port */
  public SubcomponentPort getAbruptExitPort() {
    return abruptExitPort;
  }

  /** Return the entering or exiting variable edge for the following index.
   * (Overridden in VariableSubcomponent.)
   * @param isEntry
   * If true, then return the entry edge for the index,
   * otherwise return the exit edge for the index.
   */
  public VariableSubcomponentControlEdge 
      getVariableEdge(int index, boolean isEntry) 
  {
    return null;
  }
}




