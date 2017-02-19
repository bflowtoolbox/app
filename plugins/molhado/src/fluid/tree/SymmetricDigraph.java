/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SymmetricDigraph.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;

import fluid.ir.Bundle;
import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceType;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.IRType;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotImmutableException;
import fluid.ir.SlotInfo;
import fluid.util.ThreadGlobal;

/** Directed graphs that can be traversed in either direction.
 * <P> Known bugs:
 * <ul>
 * <li> If the number of parents is the default (~0)
 *      and we are using constant slots, this node cannot have a parent.
 *      (More of a surprising feature than a bug.)
 * <li> The location of parent references cannot be specified,
 *      and even the relative order cannot be guaranteed.
 *	This bug affects @{link #setParent} and delegating syntax trees
 *	that are being mutated backwards.
 *      We try to fix up the parent vector after a change,
 *      but this only works for fixed parent sequences,
 *      and even then 'null' is left behind (even if the slot
 *      was undefined before).
 * <li> The number of parents can specified through a delegating
 *      symmetric directed graph only through a thread global.
 *      In other words, not all the information is going through
 *      the normal channels.  This will cause things to break,
 *      if there are attribute wrappes between that queue requests.
 * <li> See @{link Digraph} as well.
 * </ul>
 */
public class SymmetricDigraph extends Digraph
implements MutableSymmetricDigraphInterface {
  interface Mutator extends Digraph.Mutator {
    public void initNode(IRNode n, int numParents, int numChildren);
    public void setParent(IRNode node, IRLocation loc, IRNode newParent);
    public void addNullParent(IRNode n);
    public void removeNullParent(IRNode n);
  }

  protected Digraph.Mutator createStoredMutator(SlotFactory sf) {
    return new StoredMutator(sf);
  }
  protected Digraph.Mutator createDelegatingMutator() {
    return new DelegatingMutator();
  }
  
  /*final*/ SlotInfo parentsSlotInfo;
  
  public IRSequence getParents(IRNode node) {
    return (IRSequence)node.getSlotValue(parentsSlotInfo);
  }
  private void setParents(IRNode node, IRSequence parents)
      throws SlotImmutableException
  {
    node.setSlotValue(parentsSlotInfo,parents);
  }

  static IRType parentsType = new IRSequenceType(IRNodeType.prototype);

  /** Create a new stored directed graph using
   * the given slot factory for attribute creation.
   */
  public SymmetricDigraph(String name, SlotFactory sf)
       throws SlotAlreadyRegisteredException
  {
    super(name,sf);
    if (name == null)
      parentsSlotInfo = sf.newAttribute();
    else
      parentsSlotInfo =
	sf.newAttribute(name + ".SymmetricDigraph.parents", parentsType);
  }

  /** Create a directed graph that uses the attributes
   * passed in.  This will work only if there is a symmetric graph
   * behind these attributes.
   */
  public SymmetricDigraph(SlotInfo childrenAttribute,
			  SlotInfo parentsAttribute)
  {
    super(childrenAttribute);
    parentsSlotInfo = parentsAttribute;
  }

      
  /** Create a new node in the graph and ready space for
   * parents and children.
   * @param n node to add to graph
   * @param numParents fixed number of parents if >= 0, ~initial
   *        number of variable parents if < 0.
   *        (defaults to -1 if omitted, see @{link #initNode(IRNode,int)})
   * @param numChildren fixed number of children if >= 0, ~initial
   *        number of variable children if < 0.
   * @exception SlotImmutableException
   * 	if node already in graph.
   */
  public void initNode(IRNode n, int numParents, int numChildren) {
    ((Mutator)mutator).initNode(n,numParents,numChildren);
  }

  public boolean hasParents(IRNode node) {
    return getParents(node).hasElements();
  }
  public int numParents(IRNode node) {
    return getParents(node).size();
  }

  public IRLocation parentLocation(IRNode node, int i) {
    return getParents(node).location(i);
  }
  public int parentLocationIndex(IRNode node, IRLocation loc) {
    return getParents(node).locationIndex(loc);
  }

  public IRLocation firstParentLocation(IRNode node) {
    return getParents(node).firstLocation();
  }
  public IRLocation lastParentLocation(IRNode node) {
    return getParents(node).lastLocation();
  }
  public IRLocation nextParentLocation(IRNode node, IRLocation loc) {
    return getParents(node).nextLocation(loc);
  }
  public IRLocation prevParentLocation(IRNode node, IRLocation loc) {
    return getParents(node).prevLocation(loc);
  }

  public int compareParentLocations(IRNode node,
				    IRLocation loc1, IRLocation loc2) {
    return getParents(node).compareLocations(loc1,loc2);
  }

  public IRNode getParent(IRNode node, int i) {
    return (IRNode)(getParents(node).elementAt(i));
  }
  public IRNode getParent(IRNode node, IRLocation loc) {
    return (IRNode)(getParents(node).elementAt(loc));
  }

  public Enumeration parents(IRNode node) {
    return mutator.protect(getParents(node).elements());
  }

  protected IRLocation findParent(IRNode node, IRNode parent)
       throws IllegalChildException
  {
    IRSequence parents = getParents(node);
    for (IRLocation loc = parents.firstLocation(); loc != null;
	 loc=parents.nextLocation(loc)) {
      if (parents.validAt(loc)) {
	IRNode p = (IRNode)parents.elementAt(loc);
	if (p == parent || (p != null && p.equals(parent))) {
	  return loc;
	}
      }
    }
    throw new IllegalChildException("not a parent of node");
  }

  protected IRLocation findUndefinedParent(IRNode node)
       throws IllegalChildException
  {
    IRSequence parents = getParents(node);
    for (IRLocation loc = parents.firstLocation(); loc != null;
	 loc=parents.nextLocation(loc)) {
      if (!parents.validAt(loc)) return loc;
    }
    throw new IllegalChildException("no undefined parent of node");
  }

  /** Check to see if a node can accept another parent. */
  protected boolean isAdoptable(IRNode child) {
    if (child == null) return true;
    IRSequence parents = getParents(child);
    if (parents.isVariable()) return true;
    for (IRLocation loc = parents.firstLocation(); loc != null;
	 loc=parents.nextLocation(loc)) {
      if (!parents.validAt(loc) || parents.elementAt(loc) == null)
	return true;
    }
    return false;
  }

  /** Set the specified parent.
   *  <strong>Warning: if the list of parents is
   *  variable, it may be reordered. </strong>
   * @param i 0-based indicator of parent to change
   * @param newParent new node to be parent, may be null.
   */
  public void setParent(IRNode node, int i, IRNode newParent) {
    setParent(node,getParents(node).location(i),newParent);
  }

  /** Set the specified parent.
   *  <strong>Warning: if the list of parents is
   *  variable, it may be reordered. </strong>
   * @param loc location to change parent.
   * @param newParent new node to be parent, may be null.
   */
  public void setParent(IRNode node, IRLocation loc, IRNode newParent) {
    ((Mutator)mutator).setParent(node,loc,newParent);
  }

  /** Add a new parent to the parents list without disturbing existing
   * parents.  If the parents are fixed in size, we look for
   * a null or undefined parent to replace.
   * If the parents are variable in size, we append to the end.
   * @exception IllegalChildException if there is no space to add
   */
  public void addParent(IRNode node, IRNode newParent)
       throws IllegalChildException
  {
    if (newParent != null) {
      addChild(newParent,node);
    } else {
      ((Mutator)mutator).addNullParent(node);
    }
  }

  /** Remove the link between a parent and a node.
   * Neither may be null.
   * @see #addParent
   * @exception IllegalChildException if parent is not a parent of node
   */
  public void removeParent(IRNode node, IRNode parent)
       throws IllegalChildException
  {
    if (parent != null) {
      removeChild(parent,node);
    } else {
      ((Mutator)mutator).removeNullParent(node);
    }
  } 

  /** Replace the node's oldParent with newParent.
   * @exception IllegalChildException if oldParent is not a parent, or
   *            newParent is not suitable.
   */
  public void replaceParent(IRNode node, IRNode oldParent, IRNode newParent)
       throws IllegalChildException
  {
    removeParent(node,oldParent);
    addParent(node,newParent);
  }

  /** Remove all the parents of a node. */
  public void removeParents(IRNode node) {
    IRSequence parents = getParents(node);
    boolean variable = parents.isVariable();
    IRLocation next;
    for (IRLocation loc = parents.firstLocation();
	 loc != null;
	 loc = next) {
      next = parents.nextLocation(loc);
      if (parents.validAt(loc)) {
	removeParent(node,getParent(node,loc));
      }
    }
  }

  /** Remove a node from the graph. */
  public void removeNode(IRNode node) {
    removeChildren(node);
    removeParents(node);
  }
  
  /** Return all the nodes connected with a root. */
  public Enumeration connectedNodes(IRNode root) {
    return mutator.protect(new ConnectedNodes(this,root));
  }

  public void saveAttributes(Bundle b) {
    mutator.saveAttributes(b);
  }

    /* The following thread global is used to make
     * initNodes for delegatings SD's (and SED's) work.
     * Essentialy it is used to pass a secret parameter,
     */
  private static ThreadGlobal initNumParents = new ThreadGlobal(null);

  protected class StoredMutator extends Digraph.StoredMutator 
				implements Mutator
  {
    public StoredMutator(SlotFactory sf) {
      super(sf);
    }
    public void initNode(IRNode n, int numChildren) {
      Integer p = (Integer)initNumParents.getValue();
      if (p == null) {
	initNode(n,-1,numChildren);
      } else {
	initNode(n,p.intValue(),numChildren);
      }
    }
    public void initNode(IRNode n, int numParents, int numChildren) {
      super.initNode(n,numChildren);
      setParents(n, slotFactory.newSequence(numParents));
    }

    /** Add a parent to the list.
     * @param initial if parent wishes this to be an initial binding
     * @return true if initial and can make this an initial binding
     */
    protected boolean addParent(IRNode child, IRNode parent,
				IRLocation ignored, boolean initial) 
    {
			
      IRSequence parents = getParents(child);
      if (initial) {
				try {
				  parents.setElementAt(parent,findUndefinedParent(child));
					notifyIRObservers(child); //! Called when in an inconsistent state: new					
				  return true;
				} catch (StructureException e) {
				}
      }
	      try {
					parents.setElementAt(parent,findParent(child,null));
					notifyIRObservers(child); //! Called when in an inconsistent state: new					
					return false;
	      } catch (StructureException e) {
	      }
      if (parents.isVariable()) {
				parents.appendElement(parent);
				notifyIRObservers(child); //! Called when in an inconsistent state: new				
				return false;
      }
      IRLocation loc = findUndefinedParent(child);
      parents.setElementAt(null,loc);
      parents.setElementAt(parent,loc);			
			notifyIRObservers(child); //! Called when in an inconsistent state: new
      return false;
    }

    /** Remove a parent from the list. */
    protected void removeParent(IRNode child, IRNode parent, IRLocation loc) {
      IRSequence parents = getParents(child);
      IRLocation ploc = findParent(child,parent);
      if (parents.isVariable()) {
	parents.removeElementAt(ploc);
      } else {
	parents.setElementAt(null,ploc);
      }      
			notifyIRObservers(child); //! Called when in an inconsistent state: new
    }

    /** Called to check if a node is suitable as a new child for
     * a particular parent node and location.
     */
    public void checkNewChild(IRNode parent, IRLocation loc, IRNode child) 
        throws IllegalChildException
    {
      if (!isAdoptable(child))
	throw new IllegalChildException("cannot add a parent to node");
    }
    
    /** Called to check if a node is suitable as an additional child for
     * a particular parent node.
     */
    public void checkNewVariableChild(IRNode parent, IRNode child) 
        throws IllegalChildException
    {
      super.checkNewVariableChild(parent,child);
      if (!isAdoptable(child))
	throw new IllegalChildException("cannot add a parent to node");
    }

    /** Called to check if a node is suitable as a new parent for
     * a particular child node and location.
     */
    public void checkNewParent(IRNode child, IRLocation loc, IRNode parent)
        throws IllegalChildException
    {
      if (!canAdopt(parent))
	throw new IllegalChildException("cannot add a child to node");
    }

    /** Called to check if a node is suitable as an additional parent
     * for a particular child.
     */
    public void checkNewVariableParent(IRNode child, IRNode parent)
         throws IllegalChildException
    {
      if (!getParents(child).isVariable())
	throw new IllegalChildException("cannot add a new parent to node");
      if (!canAdopt(parent))
	throw new IllegalChildException("cannot add a child to node");
    }

    public void setParent(IRNode node, IRLocation loc, IRNode newParent) {
      IRSequence parents = getParents(node);
      IRNode oldParent = null;
      boolean oldParentDefined = false;
      if (parents.validAt(loc)) {
	/** convert into child operations */
	oldParent = (IRNode)parents.elementAt(loc);
	oldParentDefined = true;
	if (oldParent != null) SymmetricDigraph.this.removeChild(oldParent,node);
      } else {
	parents.setElementAt(null,loc);
      }
      if (newParent != null) {
	addChild(newParent,node);
      }
      if (!parents.isVariable()) { // fix up parents vector
	if (parents.elementAt(loc) != newParent) {
	  /* patch things up */
	  IRLocation wloc = findParent(node,newParent);
	  parents.setElementAt(parents.elementAt(loc),wloc);
	  parents.setElementAt(newParent,loc);
	}
      }
			notifyIRObservers(node); //! Called when in an inconsistent state: new
      if (!oldParentDefined && newParent == null && hasListeners()) {
	informDigraphListeners(new NewParentEvent(SymmetricDigraph.this,
						  node,loc,null));
      }
    }

    public void addNullParent(IRNode node)
       throws IllegalChildException
    {
      IRSequence parents = getParents(node);
      IRLocation loc;
      if (parents.isVariable()) {
        loc = parents.appendElement(null);
      } else {
        try {
          loc = findUndefinedParent(node);
          parents.setElementAt(null,loc);
        } catch (StructureException e) {
          loc = findParent(node,null);
        }
      }
			notifyIRObservers(node); // new
      if (hasListeners()) {
        informDigraphListeners(new NewParentEvent(SymmetricDigraph.this,
						  node,loc,null));
      }
    }

    public void removeNullParent(IRNode node)
       throws IllegalChildException
    {
      IRSequence parents = getParents(node);
      if (parents.isVariable()) {
	IRLocation loc = findParent(node,null);
	parents.removeElementAt(loc);
	notifyIRObservers(node); // new
	if (hasListeners()) {
	  informDigraphListeners(new RemoveParentEvent(SymmetricDigraph.this,
						       node,loc,null));
	}
      }
    }

    public void saveAttributes(Bundle b) {
      super.saveAttributes(b);
      b.saveAttribute(parentsSlotInfo);
    }

    final SlotInfo wrappedParentsAttribute = new WrappedParentsSlotInfo();
    
    public SlotInfo getAttribute(String name) {
      if (name.equals("parents")) {
	return wrappedParentsAttribute;
      } else {
	return super.getAttribute(name);
      }
    }

    public class WrappedParentsSlotInfo extends DerivedSlotInfo {
      protected boolean valueExists(IRNode node) {
	return isNode(node);
      }
      protected Object getSlotValue(IRNode node) {
	return new ParentsWrapper(node,getParents(node));
      }
      protected void setSlotValue(IRNode node, Object value) {
	IRSequence seq = (IRSequence)value;
	int numParents = seq.size();
	int initParents = numParents;
	if (seq.isVariable()) initParents = ~initParents;
	initNode(node,initParents,-1);
	IRSequence seqp = getParents(node);
	IRLocation loc, locp;
	for (loc = seq.firstLocation(),locp = seqp.firstLocation();
	     loc != null;
	     loc = seq.nextLocation(loc), locp = seqp.nextLocation(locp)) {
	  if (seq.validAt(loc)) {
	    setParent(node,locp,(IRNode)seq.elementAt(loc));
	  }
	}
      }
    }

    public class ParentsWrapper extends IRSequenceWrapper {
      final IRNode child;
      public ParentsWrapper(IRNode node, IRSequence real) {
	super(real);
	child = node;
      }
      public void setElementAt(IRNode node, IRLocation loc) {
	setParent(child,loc,node);
      }
      public void insertElementAt(IRNode node, InsertionPoint ip) {
	//! must ignore ip
	SymmetricDigraph.this.addParent(child,node);
      }
      public void removeElementAt(IRLocation loc) {
	IRSequence parents = getParents(child);
	if (parents.validAt(loc)) {
	  IRNode parent = (IRNode)parents.elementAt(loc);
	  SymmetricDigraph.this.removeParent(child,parent);
	} else {
	  parents.removeElementAt(loc);
	}
      }
    }
  }

  protected class DelegatingMutator extends Digraph.DelegatingMutator
				    implements Mutator
  {
    public void initNode(IRNode n, int numParents, int numChildren) {
      initNumParents.pushValue(new Integer(numParents));
      try {
	super.initNode(n,numChildren);
      } finally {
	initNumParents.popValue();
      }
    }
    public void setParent(IRNode node, IRLocation loc, IRNode newParent) {
      getParents(node).setElementAt(newParent,loc);
    }
    public void addNullParent(IRNode node) throws IllegalChildException {
      IRSequence parents = getParents(node);
      if (parents.isVariable()) {
	parents.appendElement(null);
      } else {
        try {
          parents.setElementAt(null,findUndefinedParent(node));
        } catch (StructureException e) {
        }
	findParent(node,null); // for exception side-effect
      }
    }
    public void removeNullParent(IRNode node) throws IllegalChildException {
      IRSequence parents = getParents(node);
      if (parents.isVariable()) {
	IRLocation loc = findParent(node,null);
	parents.removeElementAt(loc);
      }
    }
    public SlotInfo getAttribute(String name) {
      if (name == "parents") return parentsSlotInfo;
      else return super.getAttribute(name);
    }
  }
}
