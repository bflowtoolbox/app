// $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SyntaxTreeInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ 
package fluid.tree;

import fluid.ir.IRNode;

/** Abstract interface for syntax trees (see @{link SyntaxTree} for more
 * details 
 *
 * @author Edwin Chan
 */
public interface SyntaxTreeInterface extends MutableTreeInterface, GraphLabel {
  public boolean opExists(IRNode node);

  /** Create a node for the particular operator.
   * The number of children required is determined from the operator.
   */
  public void initNode(IRNode n, Operator op);
 
  /** Create a node for a particular operator, with minimum number of children
   * NB: no slots are mutated with this code.
   */
  public void initNode(IRNode n, Operator op, int min);

  /** Create a node for a particular operator, with particular children.
   * NB: no slots are mutated with this code.
   */
  public void initNode(IRNode n, Operator op, IRNode[] children);
}

