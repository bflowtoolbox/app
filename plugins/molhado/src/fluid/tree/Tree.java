/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/Tree.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import fluid.ir.Bundle;
import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRLocation;
import fluid.ir.IRLocationType;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceException;
import fluid.ir.IRSequenceType;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.IRType;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.util.SingletonEnumeration;

/** This class contains methods for accessing (untyped) tree nodes.
 * Each node has a parent, location and a vector of children.
 * We have basic parent/child commands.
 * A number of XXChild methods in Digraph have be rewritten for trees
 * and called XXSubtree, for example {@link #setChild(IRNode,int,IRNode)}
 * has been supplemented by {@link #setSubtree(IRNode,int,IRNode)}.
 * The subtree version (as opposed to ones inherited from Digraph)
 * first remove the subtree from its former location before attaching
 * it in the new location.  The inherited methods are not changed,
 * except to signal an error if a node would get two or more parents.
 * <P> Known bugs:
 * <ul>
 * <li> As with {@link Digraph}, listeners and observers
 *      are never informed of changes when this tree is a derived Tree.
 *      <p> A fix would require keeping conditional listeners/observers
 *          through the wrapper slots.
 *      </p>
 * </ul>
 */
public class Tree extends Digraph implements MutableTreeInterface {
  public static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("TREE");
  public static final String PARENTS = "parents";
  public static final String LOCATION = "location";

  protected interface Mutator extends Digraph.Mutator {
    public void clearParent(IRNode node);
  }

  protected Digraph.Mutator createStoredMutator(SlotFactory sf) {
    return new StoredMutator(sf);
  }
  protected Digraph.Mutator createDelegatingMutator() {
    return new DelegatingMutator();
  }

  /*final*/
  SlotInfo parentsSlotInfo;

  static IRType parentsType = new IRSequenceType(IRNodeType.prototype);

  /** Return parent of a tree node.
   * @precondition nonNull(node)
   * @throws SlotUndefinedException if no parent defined
   * @throws NullPointerException if node is null
   * @see #getParentOrNull(IRNode)
   */
  public IRNode getParent(IRNode node) {
    final IRSequence seq = getParents(node);
    return (IRNode) getParents(node).elementAt(0);
    // return (IRNode)(node.getSlotValue(parentSlotInfo));
  }

  private IRSequence getParents(IRNode node) {
    return (IRSequence) node.getSlotValue(parentsSlotInfo);
  }

  protected void setParent(IRNode node, IRNode parent) {
    final IRSequence seq = (IRSequence) node.getSlotValue(parentsSlotInfo);
    seq.setElementAt(parent, 0);
    // node.setSlotValue(parentSlotInfo,parent);
  }

  /** Set the parent of a node to null.
   * If it currently has a parent, the node is first removed
   * from this parent's children.
   */
  public void clearParent(IRNode node) {
    ((Mutator) mutator).clearParent(node);
  }

  protected boolean parentExists(IRNode node) {
    return node.valueExists(parentsSlotInfo) && getParents(node).validAt(0);
  }

  /** Return the parent of a tree node (or null if null).
   * If the parent was undefined, it is made null
   * (and null is returned).
   * @see #getParent(IRNode)
   */
  public IRNode getParentOrNull(IRNode node) {
    if (node == null)
      return null;
    else if (parentExists(node)) {
      return getParent(node);
    } else if (isNode(node) && node.valueExists(parentsSlotInfo)) {
      // only if defined and fully in the tree
      // (observers looking at a newly created node before
      // it has been tree-initialized might/do call this function)
      clearParent(node);
    }
    return null;
  }

  /*final*/
  SlotInfo locationSlotInfo;

  /** The location is a value used by an IRSequence
   * to locate an element.  For IRArray, it is an integer.
   * @precondition nonNull(node)
   */
  public IRLocation getLocation(IRNode node) {
    return (IRLocation) node.getSlotValue(locationSlotInfo);
  }
  protected void setLocation(IRNode node, IRLocation loc) {
    node.setSlotValue(locationSlotInfo, loc);
  }

  /** Return the location of a child within the children of a node.
   * In this case, it is easy to compute because of the stored information,
   * (compare with Digraph.findChild).
   * @see Digraph#findChild
   */
  protected IRLocation findChild(IRNode node, IRNode child)
    throws IllegalChildException {
    if (child == null)
      return super.findChild(node, child);
    if (getParentOrNull(child) == node)
      return getLocation(child);
    throw new IllegalChildException("not a child of node");
  }

  public Tree(String name, SlotFactory sf)
    throws SlotAlreadyRegisteredException {
    super(name, sf);
    if (name == null) {
      parentsSlotInfo = sf.newAttribute();
      locationSlotInfo = sf.newAttribute();
    } else {
      parentsSlotInfo = sf.newAttribute(name + ".Tree.parents", parentsType);
      locationSlotInfo =
        sf.newAttribute(name + ".Tree.location", IRLocationType.prototype);
    }
  }

  /** Create a Tree that delegates to a Tree presumed to lie
    * behind the given attributes.
    */
  public Tree(
    SlotInfo childrenAttribute,
    SlotInfo parentAttribute,
    SlotInfo locationAttribute) {
    super(childrenAttribute);
    parentsSlotInfo = parentAttribute;
    locationSlotInfo = locationAttribute;
  }

  public Enumeration<IRNode> connectedNodes(final IRNode node) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void initNode(final IRNode node, final int numP, final int numC) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void removeNode(final IRNode node) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void replaceParent(
    final IRNode node,
    final IRNode oldParent,
    final IRNode newParent) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void removeParent(final IRNode node, final IRNode oldParent) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void removeParents(final IRNode node) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void addParent(final IRNode node, final IRNode newParent) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void setParent(
    final IRNode node,
    final int i,
    final IRNode newParent) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  public void setParent(
    final IRNode node,
    final IRLocation loc,
    final IRNode newParent) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  /** Place a subtree as the child of another node.
   * The subtree is first removed from where it currently
   * is stored, if necessary.
   */
  public void setSubtree(IRNode parent, int i, IRNode newChild) {
    if (parentExists(newChild))
      clearParent(newChild);
    setChild(parent, i, newChild);
  }

  /** Place a subtree as the child of another node.
   * The subtree is first removed from where it currently
   * is stored, if necessary.
   */
  public void setSubtree(IRNode parent, IRLocation loc, IRNode newChild) {
    if (parentExists(newChild))
      clearParent(newChild);
    setChild(parent, loc, newChild);
  }

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
    throws IllegalChildException {
    IRNode parent = getParent(oldChild);
    if (parent == null) {
      throw new IllegalChildException("replaced subtree has no parent");
    }
    removeSubtree(newChild);
    setChild(parent, getLocation(oldChild), newChild);
  }

  /** Exchange where two subtrees are located.
   * If one does not have a parent, then the operation
   * is identical to a replaceSubtree operation.
   * If both do not have parents, this operation has no effect.
   * @precondition nonNull(node1) && nonNull(node2)
   * @exception IllegalChildException
   *   If either node is not legal in the place of the other.
   */
  public void exchangeSubtree(IRNode node1, IRNode node2)
    throws IllegalChildException {
    IRNode parent1 = getParentOrNull(node1);
    IRNode parent2 = getParentOrNull(node2);
    if (parent1 == null) {
      if (parent2 != null) {
        replaceSubtree(node2, node1);
      }
    } else if (parent2 == null) {
      replaceSubtree(node1, node2);
    } else {
      IRLocation location1 = getLocation(node1);
      IRLocation location2 = getLocation(node2);

      // First we unhook one child and then
      // perform two set operations.
      // The first operation is required to avoid a node
      // getting more than one parent.
      setChild(parent1, location1, null);
      setChild(parent2, location2, node1);
      setChild(parent1, location1, node2);
    }
  }

  /** Insert a subtree at the given point in a node's children sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild) &&
   *               nonNull(ip)
   */
  public IRLocation insertSubtree(
    IRNode node,
    IRNode newChild,
    InsertionPoint ip) {
    removeSubtree(newChild);
    return insertChild(node, newChild, ip);
  }

  /** Insert a subtree at the beginning of the child sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild)
   */
  public void insertSubtree(IRNode parent, IRNode newChild) {
    removeSubtree(newChild);
    insertChild(parent, newChild);
  }

  /** Insert a subtree at the end of the children sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(parent) && nonNull(newChild)
   */
  public void appendSubtree(IRNode parent, IRNode newChild) {
    removeSubtree(newChild);
    appendChild(parent, newChild);
  }

  /** Remove a subtree from a variable size sequence.
   * The same function as {@link #clearParent}.
   * @precondition nonNull(node)
   */
  public void removeSubtree(IRNode node) {
    clearParent(node);
  }

  /** Insert a subtree after another in a sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(oldChild) && nonNull(newChild)
   * @param oldChild a subtree in a sequence
   * @param newChild a subtree to be put in the sequence after oldChild.
   */
  public void insertSubtreeAfter(IRNode newChild, IRNode oldChild) {
    removeSubtree(newChild);
    insertChildAfter(getParent(oldChild), newChild, oldChild);
  }

  /** Insert a subtree before another in a sequence.
   * The subtree is first removed from wherever it currently resides.
   * @precondition nonNull(oldChild) && nonNull(newChild)
   * @param oldChild a subtree in a sequence
   * @param newChild a subtree to be put in sequence before oldChild.
   */
  public void insertSubtreeBefore(IRNode newChild, IRNode oldChild) {
    removeSubtree(newChild);
    insertChildBefore(getParent(oldChild), newChild, oldChild);
  }

  /** Return the root of a subtree.
   */
  public IRNode getRoot(IRNode subtree) {
    IRNode parent;
    while (true) {
      parent = getParent(subtree);
      if (parent == null)
        return subtree;
      subtree = parent;
    }
  }

  /** Return an enumeration of nodes in the subtree
   * starting with leaves and working toward the node given.
   * A postorder traversal.
   */
  public Enumeration<IRNode> bottomUp(IRNode subtree) {
    return mutator.protect(new TreeWalkEnumeration(this, subtree, true));
  }
  /** Return an enumeration of nodes in the subtree
   * starting with this node and working toward the leaves.
   * A preorder traversal.
   */
  public Enumeration<IRNode> topDown(IRNode subtree) {
    return mutator.protect(new TreeWalkEnumeration(this, subtree, false));
  }

  /** Return an enumeration of the nodes in a tree.
   * This method overrides the method in Digraph,
   * in the case we are guaranteed not to have cycles
   */
  public Enumeration<IRNode> depthFirstSearch(IRNode root) {
    if (getParentOrNull(root) == null) {
      // safe to ignore marks:
      return topDown(root);
    } else {
      // not safe to ignore marks:
      return super.depthFirstSearch(root);
    }
  }

  /** Return an enumeration of nodes from this one to a root.
   */
  public Enumeration<IRNode> rootWalk(final IRNode node) {
    return mutator.protect(new Enumeration<IRNode>() {
      IRNode n = node;
      public boolean hasMoreElements() {
        return n != null;
      }
      public IRNode nextElement() {
        if (n == null)
          throw new NoSuchElementException("to root");
        try {
          return n;
        } finally {
          n = getParentOrNull(n);
        }
      }
    });
  }

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
  public int comparePreorder(IRNode node1, IRNode node2) {
    IRNode root1 = node1, root2 = node2;
    int d1 = 0, d2 = 0;

    if (node1.equals(node2))
      return 0;

    /* first find root and depth */

    for (;;) {
      IRNode tmp = getParentOrNull(root1);
      if (tmp == null)
        break;
      root1 = tmp;
      ++d1;
    }
    for (;;) {
      IRNode tmp = getParentOrNull(root2);
      if (tmp == null)
        break;
      root2 = tmp;
      ++d2;
    }

    /* now check that they are in the same tree */
    if (!root1.equals(root2))
      throw new IllegalArgumentException("nodes in different trees cannot be compared");

    while (d1 > d2) {
      node1 = getParent(node1);
      --d1;
      if (node1.equals(node2))
        return -1;
    }
    while (d1 < d2) {
      node2 = getParent(node2);
      --d2;
      if (node1.equals(node2))
        return 1;
    }

    for (;;) {
      IRNode p1 = getParent(node1);
      IRNode p2 = getParent(node2);
      if (p1.equals(p2)) {
        int loccomp =
          compareChildLocations(p1, getLocation(node1), getLocation(node2));
        return loccomp < 0 ? -2 : 2;
      }
      node1 = p1;
      node2 = p2;
    }
  }

  // Satisfying SymmetricDigraphInterface:

  public boolean hasParents(IRNode node) {
    return true;
  }
  public int numParents(IRNode node) {
    return 1;
  }

  public IRLocation parentLocation(IRNode n, int i) {
    return getParents(n).location(i);
  }

  public int parentLocationIndex(IRNode n, IRLocation loc) {
    return getParents(n).locationIndex(loc);
  }

  public IRLocation firstParentLocation(IRNode n) {
    return getParents(n).firstLocation();
  }

  public IRLocation lastParentLocation(IRNode n) {
    return getParents(n).lastLocation();
  }

  public IRLocation nextParentLocation(IRNode n, IRLocation loc) {
    return getParents(n).nextLocation(loc);
  }

  public IRLocation prevParentLocation(IRNode n, IRLocation loc) {
    return getParents(n).prevLocation(loc);
  }

  public int compareParentLocations(
    IRNode n,
    IRLocation loc1,
    IRLocation loc2) {
    return getParents(n).compareLocations(loc1, loc2);
  }

  public IRNode getParent(IRNode node, int i) {
    if (i == 0)
      return getParent(node);
    throw new IRSequenceException("only one parent");
  }
  public IRNode getParent(IRNode node, IRLocation loc) {
    if (getParents(node).locationIndex(loc) == 0)
      return getParent(node);
    throw new IRSequenceException("only one parent");
  }

  public Enumeration<IRNode> parents(IRNode node) {
    return new SingletonEnumeration(getParent(node));
  }

  protected class StoredMutator
    extends Digraph.StoredMutator
    implements Mutator {
    protected StoredMutator(SlotFactory sf) {
      super(sf);
    }

    // Override to init the parents attribute to be an
    // IRSequence
    public void initNode(IRNode node, int numChildren) {
      super.initNode(node, numChildren);
      node.setSlotValue(parentsSlotInfo, slotFactory.newSequence(1));
    }

    protected void checkNewChild(IRNode parent, IRLocation loc, IRNode child)
      throws IllegalChildException {
      if (child == null)
        return;
      else if (parentExists(child)) {
        if (getParent(child) != null)
          throw new IllegalChildException("child already has a parent");
      } else {
        /* parent is undefined, hence OK */
      }
      checkChild(parent, loc, child);
    }

    protected void checkNewVariableChild(IRNode parent, IRNode child)
      throws IllegalChildException {
      super.checkNewVariableChild(parent, child);
      if (child == null)
        return;
      else if (parentExists(child)) {
        if (getParent(child) != null)
          throw new IllegalChildException("child already has a parent");
      } else {
        /* parent is undefined, hence OK */
      }
      checkVariableChild(parent, child);
    }

    protected void checkChild(IRNode parent, IRLocation loc, IRNode child)
      throws IllegalChildException {
    }

    protected void checkVariableChild(IRNode parent, IRNode child)
      throws IllegalChildException {
    }

    /** Called when a node gains a parent. */
    protected boolean addParent(
      IRNode child,
      IRNode parent,
      IRLocation loc,
      boolean initial) {
      LOG.debug(
        "addParent("
          + child
          + ","
          + (parent == null ? "null" : parent.toString())
          + ","
          + loc
          + ","
          + initial
          + ")");
      if (initial) {
        if (getParents(child).validAt(0))
          initial = false;
      } else if (!parentExists(child)) {
        setLocation(child, null);
      }
      setParent(child, parent);
      setLocation(child, loc);
      return initial;
    }

    /** Call when a node loses a parent. */
    protected void removeParent(IRNode child, IRNode parent, IRLocation loc) {
      setParent(child, null);
      setLocation(child, null);
    }

    public void saveAttributes(Bundle b) {
      super.saveAttributes(b);
      b.saveAttribute(parentsSlotInfo);
      b.saveAttribute(locationSlotInfo);
    }

    public void clearParent(IRNode node) {
      IRNode parent;
      if (parentExists(node)) {
        parent = getParent(node);
        if (parent != null)
          Tree.this.removeChild(parent, node);
      } else {
        setParent(node, null);
        setLocation(node, null);
        IRLocation ploc = getParents(node).location(0);
        if (hasListeners()) {
          informDigraphListeners(
            new NewParentEvent(Tree.this, node, ploc, null));
        }
      }
      return;
    }

    protected final SlotInfo wrappedParentsAttribute =
      new WrappedParentsSlotInfo();

    protected final SlotInfo wrappedLocationAttribute =
      new WrappedLocationSlotInfo();

    public SlotInfo getAttribute(String name) {
      if (name == PARENTS) {
        return wrappedParentsAttribute;
      } else if (name == LOCATION) {
        return wrappedLocationAttribute;
      } else {
        return super.getAttribute(name);
      }
    }

    // Wrap so that an IRSequence wrapper is supstituted for
    // the actual parents sequence.
    class WrappedParentsSlotInfo extends DerivedSlotInfo {
      public IRType type() {
        return parentsType;
      }
      protected boolean valueExists(IRNode node) {
        return parentExists(node);
      }
      protected Object getSlotValue(IRNode node) {
        return new ParentsWrapper(node, getParents(node));
      }
    }

    // Wrap the parents sequence
    public class ParentsWrapper extends IRSequenceWrapper {
      final IRNode child;
      public ParentsWrapper(IRNode node, IRSequence real) {
        super(real);
        child = node;
      }
      public void setElementAt(IRNode value, IRLocation loc) {
        if (locationIndex(loc) != 0) {
          throw new IRSequenceException(
            "index out of bounds:"
              + " the parent of a tree must be at index 0");
        }
        IRNode parent = (IRNode) value;
        if (parentExists(child) || value == null)
          clearParent(child);
        if (value != null)
          addChild(parent, child);
      }
      public void insertElementAt(IRNode node, InsertionPoint ip) {
        throw new IRSequenceException("arrays are fixed size");
      }
      public void removeElementAt(IRLocation loc) {
        if (locationIndex(loc) != 0) {
          throw new IRSequenceException(
            "index out of bounds:"
              + " the parent of a tree must be at index 0");
        }
        // Only clear parent if there is a non-null parent
        if (validAt(0)) {
          if (elementAt(0) != null)
            clearParent(child);
        }
      }
    }

    class WrappedLocationSlotInfo extends DerivedSlotInfo {
      public IRType type() {
        return IRLocationType.prototype;
      }
      protected boolean valueExists(IRNode node) {
        return node.valueExists(locationSlotInfo);
      }
      protected Object getSlotValue(IRNode node) {
        return getLocation(node);
      }
    }
  }

  protected class DelegatingMutator
    extends Digraph.DelegatingMutator
    implements Mutator {
    public void clearParent(IRNode node) {
      setParent(node, null);
    }
    public SlotInfo getAttribute(String name) {
      if (name.equals(PARENTS))
        return parentsSlotInfo;
      else if (name.equals(LOCATION))
        return locationSlotInfo;
      else
        return super.getAttribute(name);
    }
  }
}

/** Class of tree walk enumerations.
 * This has code for bottom-up and top-down tree walks
 * ending/starting at a particular node.
 * (These traversals are also called postorder and preorder).
 */

class TreeWalkEnumeration extends DepthFirstSearch {
  TreeWalkEnumeration(Tree t, IRNode root, boolean bottomUp) {
    super(t, root, bottomUp);
  }

  protected boolean mark(IRNode node) {
    return true;
  }
}
