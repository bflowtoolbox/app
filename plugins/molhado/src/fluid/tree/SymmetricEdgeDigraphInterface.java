/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SymmetricEdgeDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Classes implementing this interface enable traversal
 * of graphs with explicit edges in either direction.
 */
public interface SymmetricEdgeDigraphInterface 
     extends EdgeDigraphInterface, SymmetricDigraphInterface
{
  /** Return the source of an edge. */
  public IRNode getSource(IRNode edge);

  /** Return the i'th edge arriving at a node. */
  public IRNode getParentEdge(IRNode node, int i);
  /** Return the ingoing edge at location loc. */
  public IRNode getParentEdge(IRNode node, IRLocation loc);

  /** Return an enumeration of the ingoing edges. */
  public Enumeration parentEdges(IRNode node);  
}
