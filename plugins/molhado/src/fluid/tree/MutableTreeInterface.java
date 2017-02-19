/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/MutableTreeInterface.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.InsertionPoint;

public interface MutableTreeInterface
extends MutableSymmetricDigraphInterface, TreeInterface
{
  /** Set the parent of a node to null.
   * If it currently has a parent, the node is first removed
   * from this parent's children.
   */
  public void clearParent(IRNode node);

  /** Return the parent of a tree node (or null if null).
   * If the parent was undefined, it is made null
   * (and null is returned).
   * @see #getParent(IRNode)
   */
  public IRNode getParentOrNull(IRNode node);

  /** Place a subtree as the child of another node.
   * The subtree is first removed from where it currently
   * is stored, if necessary.
   */
  public void setSubtree(IRNode parent, int i, IRNode newChild);

  /** Place a subtree as the child of another node.
   * The subtree is first removed from where it currently
   * is stored, if necessary.
   */
  public void setSubtree(IRNode parent, IRLocation loc, IRNode newChild);

  /** Replace one subtree in the tree by another subtree.
   * Each subtree is represented by its root.  The new subtree
   * is removed from its current location (if any), the old subtree
   * must have a parent.
   * These properties are changed after the routine returns.
   * @precondition nonNull(oldChild)
   * @exception IllegalChildException
   *   If oldChild does currently have a parent or
   *   newChild is not legal in the place of oldChild.
   */
  public void replaceSubtree(IRNode oldChild, IRNode newChild)
      throws IllegalChildException;

  /** Exchange where two subtrees are located.
   * If one does not have a parent, then the operation
   * is identical to a replaceSubtree operation.
   * If both do not have parents, this operation has no effect.
   * @precondition nonNull(node1) && nonNull(node2)
   * @exception IllegalChildException
   *   If either node is not legal in the place of the other.
   */
  public void exchangeSubtree(IRNode node1, IRNode node2)
      throws IllegalChildException;

  /** Insert a subtree at the given point in a node's children sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild) &&
   *               nonNull(ip)
   */
  public IRLocation insertSubtree(IRNode node, IRNode newChild, InsertionPoint ip);

  /** Insert a subtree at the beginning of the child sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild)
   */
  public void insertSubtree(IRNode parent, IRNode newChild);

  /** Insert a subtree at the end of the children sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild)
   */
  public void appendSubtree(IRNode parent, IRNode newChild);

  /** Remove a subtree from a variable size sequence.
   * The same function as {@link #clearParent}.
   * @precondition nonNull(node)
   */
  public void removeSubtree(IRNode node);

  /** Insert a subtree after another in a sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(oldChild) && nonNull(newChild)
   * @param oldChild a subtree in a sequence
   * @param newChild a subtree to be put in the sequence after oldChild.
   */
  public void insertSubtreeAfter(IRNode newChild, IRNode oldChild);

  /** Insert a subtree before another in a sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(oldChild) && nonNull(newChild)
   * @param oldChild a subtree in a sequence
   * @param newChild a subtree to be put in sequence before oldChild.
   */
  public void insertSubtreeBefore(IRNode newChild, IRNode oldChild);

  /** Return an enumeration of nodes from this one to a root.
   */
  public Enumeration rootWalk(final IRNode node);

  /** Compare the ordering of two nodes in a preorder traversal
   * of a tree.
   * @throws IllegalArgumentEception if they are not in the same tree,
   * @throws NullPointerException if either node is null.
   * @return a value <dl>
   * <dt>-2<dd> if node1 precedes node2 in postorder or preorder
   * <dt>-1<dd> if node1 is an ancestor of node2
   * <dt> 0<dd> if node1 equals node2
   * <dt> 1<dd> if node1 is a descendant of node2
   * <dt> 2<dd> if node1 follows node2 in postorder or preorder
   * </dl>
   * In particular, < 0 means precedes in preorder
   */
  public int comparePreorder(IRNode node1, IRNode node2);
}
