/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/MutableSymmetricEdgeDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.InsertionPoint;

public interface MutableSymmetricEdgeDigraphInterface
extends SymmetricEdgeDigraphInterface, MutableEdgeDigraphInterface
{
  /** Set the source of an edge keeping the structure consistent. */
  public void setSource(IRNode e, IRNode n);

  /** Add a new incoming edge to a node.
   * If the parents are fixed in size, we look for
   * a null or undefined parent to replace.
   * If the parents are variable in size, we append to the end.
   * @exception StructureException if there is no space to add
   */
  public void addParentEdge(IRNode node, IRNode newParentEdge)
       throws StructureException;

  /** Remove the link between an incoming edge and a node.
   * Neither may be null.
   * @see #addParentEdge
   * @exception StructureException
   *            if parentEdge is not an incoming edge of node
   */
  public void removeParentEdge(IRNode node, IRNode parentEdge)
       throws StructureException;

  /** Replace the node's oldParentEdge with newParentEdge.
   * @exception StructureException if oldParentEdge is not an incoming edge, 
   *            or newParentEdge is not suitable.
   */
  public void replaceParentEdge(IRNode node,
				IRNode oldParentEdge,
				IRNode newParentEdge)
       throws StructureException;

  /** Set the parent edge.
   * See caveats on @{link SymmetricDigraph#setParent(IRNode,int,IRNode)}.
   * @param parentEdge incoming edge to use (may be null)
   */
  public void setParentEdge(IRNode node, int i, IRNode parentEdge);

  /** Set the parent edge. See caveats
   * on @{link SymmetricDigraph#setParent(IRNode,IRLocation,IRNode)}.
   * @param parentEdge incoming edge to use (may be null)
   */
  public void setParentEdge(IRNode node, IRLocation loc, IRNode parentEdge);

  /** Insert a parent.
   * <strong>The insertion point is ignored.</strong>
   */
  public IRLocation insertParentEdge(IRNode node, IRNode parentEdge,
				     InsertionPoint ip);

  /** Remove an edge from the graph. */
  public void disconnect(IRNode edge);
}





