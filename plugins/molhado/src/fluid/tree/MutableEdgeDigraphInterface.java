/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/MutableEdgeDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRSequenceException;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotUndefinedException;

/** A form of graph in which edges have identity and may be attributed.
 * <P> Known bugs:
 * <ul>
 * <li> See @{link Digraph}.
 * </ul>
 */
public interface MutableEdgeDigraphInterface
extends EdgeDigraphInterface
{
  /** Return a reference to this EdgeDigraph as a Digraph */
  public Digraph getAsDigraph();

  /** Return <code>true</code> if the edge is part of this EdgeDigraph. */
  public boolean isEdge(IRNode n);

  /** Add an Edge to the EdgeDigraph. */
  public void initEdge(IRNode e);
  
  /** Return true if the given edge (which must be an edge in this graph)
   * currently has a sink defined, or not.
   * @exception EdgeDigraphException if e is not initialized as an edge.
   */
  public boolean hasSink(IRNode e);

  /** Return the sink that this edge has.
   * @return null if edge has an empty sink.
   * @exception EdgeDigraphException if e is not initialized as an edge.
   * @exception SlotUndefinedException if this edge has no sink.
   */
  public IRNode getSink(IRNode e);

  /** Set the sink of an edge, and if necessary keep the structure
   * consistent.
   * @throws StructureException if sink unacceptable or if node not a node
   */
  public void setSink(IRNode e, IRNode n) throws StructureException;

  /** Return true if this node has an edge outgoing at
   * the given index.
   * @exception EdgeDigraphException if the node is not an initialized
   *            node of this graph
   * @exception IRSequenceException if the index is out of range
   */
  public boolean hasChildEdge(IRNode node, int i);

  /** Return true if this node has an edge outgoing at
   * the given location.
   * @exception EdgeDigraphException if the node is not an initialized
   *            node of this graph
   * @exception IRSequenceException if the location is out of range
   */
  public boolean hasChildEdge(IRNode node, IRLocation loc);

  /** Set the i'th outgoing edge of the node to be newChildEdge.
   */
  public void setChildEdge(IRNode node, int i, IRNode newChildEdge);

  /** Set the outgoing edge at location loc of the node to be newChildEdge.
   */
  public void setChildEdge(IRNode node, IRLocation loc, IRNode newChildEdge);
  
  /** Adopt a new outgoing edge without disturbing existing
   * edges.  If number of outgoing edges are fixed in size, we look for
   * an undefined child location.
   * If the number of outgoing edges are variable in size,
   * we append to the end.
   * @exception StructureException if there is no space to add
   */
  public void addChildEdge(IRNode node, IRNode newChildEdge)
       throws StructureException;

  /** Replace the node's oldChildEdge with newChildEdge.
   * @exception StructureException if oldChildEdge is not a childEdge, or
   *            newChildEdge is not suitable.
   */
  public void replaceChildEdge(IRNode node,
			       IRNode oldChildEdge,
			       IRNode newChildEdge)
       throws StructureException;    

  /** Add newChildEdge as a new outgoing edge of the node
   * @exception StructureException if newChildEdge is not suitable
   *            or the parent cannot accept new outgoing edges.
   */
  public IRLocation insertChildEdge(IRNode node,
				    IRNode newChildEdge,
				    InsertionPoint ip)
      throws StructureException;

  /** Add newChildEdge as a new first outgoing edge of node.
   * @exception StructureException if newChildEdge is not suitable
   *            or the parent cannot accept new outgoing edges.
   */
  public void insertChildEdge(IRNode node, IRNode newChildEdge)
       throws StructureException;

  /** Add newChildEdge as a new last childEdge of node.
   * @exception StructureException if newChildEdge is not suitable
   *            or the parent cannot accept new outgoing edges.
   */
  public void appendChildEdge(IRNode node, IRNode newChildEdge)
       throws StructureException;

  /** Add newChildEdge as a new childEdge after the given childEdge of node.
   * @exception StructureException if oldChildEdge is not a childEdge, 
   *		newChildEdge is not suitable, or
   *            the parent cannot accept new outgoing edges
   */
  public void insertChildEdgeAfter(IRNode node,
				   IRNode newChildEdge,
				   IRNode oldChildEdge)
       throws StructureException;

  /** Add newChildEdge as a new childEdge before the given childEdge of node.
   * @exception StructureException if if oldChildEdge is not a childEdge, 
   *		newChildEdge is not suitable, or
   *            the parent cannot accept new outgoing edges.
   */
  public void insertChildEdgeBefore(IRNode node,
				    IRNode newChildEdge,
				    IRNode oldChildEdge)
       throws StructureException;

  /** Remove oldChildEdge from the sequence of childEdgeren of a node.
   * If the sequence is variable, we get rid of its location too,
   * otherwise, we substitute null.
   * @see #addChildEdge
   * @exception StructureException if oldChildEdge is not a childEdge,
   */
  public void removeChildEdge(IRNode node, IRNode oldChildEdge)
       throws StructureException;

  /** Add edge as the link between two (non-null) nodes. */
  public void connect(IRNode edge, IRNode parent, IRNode child);
}
