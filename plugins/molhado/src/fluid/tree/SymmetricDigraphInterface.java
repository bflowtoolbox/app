/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SymmetricDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Directed graphs that can be traversed in either direction.
 * @see SymmetricDigraph
 * @see SymmetricEdgeDigraphInterface
 */
public interface SymmetricDigraphInterface extends DigraphInterface {
  /** Return true if there is at least one parent location. */
  public boolean hasParents(IRNode node);
  
  /** Return the number of parents, defined or undefined, null or nodes. */
  public int numParents(IRNode node);

  /** Return the location for parent #i */
  public IRLocation parentLocation(IRNode node, int i);

  /** Return the numeric location for a location. */
  public int parentLocationIndex(IRNode node, IRLocation loc);

  /** Return the location of the first parent. */
  public IRLocation firstParentLocation(IRNode node);

  /** Return the location of the last parent. */
  public IRLocation lastParentLocation(IRNode node);

  /** Return next parent location or null. */
  public IRLocation nextParentLocation(IRNode node, IRLocation ploc);
  
  /** Return previous parent location or null. */
  public IRLocation prevParentLocation(IRNode node, IRLocation ploc);

  /** Return one of <dl>
   * <dt>&lt; 0<dd> if loc1 precedes loc2,
   * <dt>&gt; 0<dd> if loc1 follows loc2,
   * <dt>= 0<dd> if loc1 equals loc2.</dl>
   * These locations must be valid locations in the parents of the node.
   */
  public int compareParentLocations(IRNode node,
				    IRLocation loc1, IRLocation loc2);

  /** Return the i'th parent of a node. */
  public IRNode getParent(IRNode node, int i);

  /** Return the parent at location loc. */
  public IRNode getParent(IRNode node, IRLocation loc);

  /** Return the parents of a node in order. */
  public Enumeration parents(IRNode node);
}
