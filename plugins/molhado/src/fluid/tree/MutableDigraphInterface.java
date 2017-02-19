// $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/MutableDigraphInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotImmutableException;

public interface MutableDigraphInterface
extends DigraphInterface
{
  /** Add a node to the directed graph.
   * Notify define observers and inform listeners of this new node.
   * @param n a new node to add to the graph
   * @throws SlotImmutableException if node already in graph
   */    
  public void initNode(IRNode n);

  /** Add a node to the directed graph.
   * Notify define observers and inform listeners of this new node.
   * @param n a new node to add to the graph
   * @param numChildren if &gt;= 0 then the number of children
   *        for a fixed arity node.  If &lt; 0, then <tt>~numChildren</tt>
   *        is the number of initial children for a variable arity node.
   * @throws SlotImmutableException if node already in graph
   */
  public void initNode(IRNode n, int numChildren);

  /** Return true if the node is part of this direction graph. */
  public boolean isNode(IRNode n);

  /** Set the i'th child of the node to be newChild.
   * @exception IllegalChildException if the child is not suitable
   */
  public void setChild(IRNode node, int i, IRNode newChild) 
       throws IllegalChildException;

  /** Set the child at location loc of the node to be newChild.
   * @exception IllegalChildException if the child is not suitable
   */
  public void setChild(IRNode node, IRLocation loc, IRNode newChild) 
       throws IllegalChildException;    

  /** Adopt a new child to the children without disturbing existing
   * children.  If the children are fixed in size, we look for
   * an undefined or null child location.
   * If the children are variable in size,
   * we append to the end.
   * @exception IllegalChildException if there is no space to add
   */
  public void addChild(IRNode node, IRNode newChild)
       throws IllegalChildException;

  /** Replace the node's oldChild with newChild.
   * @exception IllegalChildException if oldChild is not a child, or
   *            newChild is not suitable.
   */
  public void replaceChild(IRNode node, IRNode oldChild, IRNode newChild)
       throws IllegalChildException;

  /** Add new child as a new child of node at the given insertion point.
   *  @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   * @return location of new child
   */
  public IRLocation insertChild(IRNode node, IRNode newChild, InsertionPoint ip)
       throws IllegalChildException;    

  /** Add newChild as a new first child of node.
   * @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void insertChild(IRNode node, IRNode newChild)
       throws IllegalChildException;

  /** Add newChild as a new last child of node.
   * @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void appendChild(IRNode node, IRNode newChild)
       throws IllegalChildException;

  /** Add newChild as a new child after the given child of node.
   * @exception IllegalChildException if oldChild is not a child, 
   *		newChild is not suitable, or
   *            the parent cannot accept new children.
   */
  public void insertChildAfter(IRNode node, IRNode newChild, IRNode oldChild)
       throws IllegalChildException;

  /** Add newChild as a new child before the given child of node.
   * @exception IllegalChildException if if oldChild is not a child, 
   *		newChild is not suitable, or
   *            the parent cannot accept new children.
   */
  public void insertChildBefore(IRNode node, IRNode newChild, IRNode oldChild)
       throws IllegalChildException;

  /** Remove oldChild from the sequence of children of a node.
   * If the sequence is variable, we get rid of its location too,
   * otherwise, we substitute null.
   * @see #addChild
   * @exception IllegalChildException if oldChild is not a child,
   */
  public void removeChild(IRNode node, IRNode oldChild)
       throws IllegalChildException;

  /** Remove the child (if any) at the given location.
   * Replace with null if sequence is fixed.
   */
  public void removeChild(IRNode node, IRLocation loc);

  /** Remove all the children of a node. */
  public void removeChildren(IRNode node);

  /** Return an enumeration of the nodes in the graph.
   * First we return the root given and then recursively
   * the enumerations of each of its children.
   */
  public Enumeration depthFirstSearch(IRNode node);
}
