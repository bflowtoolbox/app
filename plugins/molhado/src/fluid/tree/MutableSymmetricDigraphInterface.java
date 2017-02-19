/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/MutableSymmetricDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.SlotImmutableException;

public interface MutableSymmetricDigraphInterface
extends MutableDigraphInterface, SymmetricDigraphInterface
{
  /** Create a new node in the graph and ready space for
   * parents and children.
   * @param n node to add to graph
   * @param numParents fixed number of parents if >= 0, ~initial
   *        number of variable parents if < 0.
   *        (defaults to -1 if omitted, see {@link #initNode(IRNode,int)})
   * @param numChildren fixed number of children if >= 0, ~initial
   *        number of variable children if < 0.
   * @exception SlotImmutableException
   * 	if node already in graph.
   */
  public void initNode(IRNode n, int numParents, int numChildren);

  /** Set the specified parent.
   *  <strong>Warning: if the list of parents is
   *  variable, it may be reordered. </strong>
   * @param i 0-based indicator of parent to change
   * @param newParent new node to be parent, may be null.
   */
  public void setParent(IRNode node, int i, IRNode newParent);

  /** Set the specified parent.
   *  <strong>Warning: if the list of parents is
   *  variable, it may be reordered. </strong>
   * @param loc location to change parent.
   * @param newParent new node to be parent, may be null.
   */
  public void setParent(IRNode node, IRLocation loc, IRNode newParent);

  /** Add a new parent to the parents list without disturbing existing
   * parents.  If the parents are fixed in size, we look for
   * a null or undefined parent to replace.
   * If the parents are variable in size, we append to the end.
   * @exception IllegalChildException if there is no space to add
   */
  public void addParent(IRNode node, IRNode newParent)
       throws IllegalChildException;

  /** Remove the link between a parent and a node.
   * Neither may be null.
   * @see #addParent
   * @exception IllegalChildException if parent is not a parent of node
   */
  public void removeParent(IRNode node, IRNode parent)
       throws IllegalChildException;

  /** Replace the node's oldParent with newParent.
   * @exception IllegalChildException if oldParent is not a parent, or
   *            newParent is not suitable.
   */
  public void replaceParent(IRNode node, IRNode oldParent, IRNode newParent)
       throws IllegalChildException;

  /** Remove all the parents of a node. */
  public void removeParents(IRNode node);

  /** Remove a node from the graph. */
  public void removeNode(IRNode node);
  
  /** Return all the nodes connected with a root. */
  public Enumeration connectedNodes(IRNode root);
}
