package fluid.control;

import fluid.ir.IRNode;
import fluid.tree.SyntaxTree;

/** A component factory creates a component for a node.
 * It should be subclassed with something that knows how to
 * create a component for a given IRNode.  Each Component
 * is assigned the factory that created it.
 */
public abstract class ComponentFactory {
  /** Return the component for this node, creating (and registering it)
   * as necessary.  The component should be created at most once.
   */
  public abstract Component getComponent(IRNode node);
  /** Return the syntax tree instance for the nodes here. */
  public abstract SyntaxTree tree();
}
