/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/GraphLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** Classes implementing this interface label their nodes
 * with Operators which determine allowable parents and children.
 * These labels are immutable.
 * Classes implementing this interface usually also implement
 * <tt>DigraphInterface</tt>.
 * @see Operator
 * @see DigraphInterface
 * @see SyntaxTree
 */
public interface GraphLabel {
  /** Return operator of a tree node.
   * @precondition nonNull(node)
   */
  public Operator getOperator(IRNode node);
}
  
