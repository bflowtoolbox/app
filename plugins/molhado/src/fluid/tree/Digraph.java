/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/Digraph.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.io.PrintStream;
import java.util.Enumeration;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.ConstantSlotFactory;
import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceType;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.IRType;
import fluid.ir.InsertionPoint;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotImmutableException;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;

/** A class with functions for creating and traversing directed graphs.
 * The slots of this class keep track of following nodes (children)
 * but not the preceding nodes (parents).
 * <P> Known bugs:
 * <ul>
 * <li> Listeners and observers
 *      are never informed of changes when this directed graph
 *	is built to delegate to (wrapped) attributes.
 *      <p> A fix would require wiring listeners/observers
 *          through the wrapper slots, with some mechanism
 *	    to avoid the need when no one is requesting it.
 *      </p>
 * </ul>
 * @see Tree
 * @see SymmetricDigraph
 */
public class Digraph extends DigraphMixin implements MutableDigraphInterface {
  /**
   * Log4j logger for this class
   */
  protected static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR.tree");

  public static final String CHILDREN = "children";
  
  /** Interface for mutators of directed graphs. 
   */
  protected interface Mutator {
    public void initNode(IRNode n,int numChildren);
    public void setChild(IRNode parent, IRLocation loc, IRNode child);
    public IRLocation insertChild(IRNode parent, IRNode child, InsertionPoint ip);
    public void removeChild(IRNode parent, IRLocation loc);
    public void saveAttributes(Bundle b);
    public SlotInfo getAttribute(String name);
    public Enumeration<IRNode> protect(Enumeration en);
  }

  protected Mutator createStoredMutator(SlotFactory sf) {
    return new StoredMutator(sf);
  }
  protected Mutator createDelegatingMutator() {
    return new DelegatingMutator();
  }

  protected Mutator mutator;


  private /*final*/ SlotInfo childrenSlotInfo;

  private IRSequence getChildren(IRNode node) {
    return (IRSequence)node.getSlotValue(childrenSlotInfo);
  }
  private void setChildren(IRNode parent, IRSequence children)
      throws SlotImmutableException
  {
    parent.setSlotValue(childrenSlotInfo,children);
  }

  static IRType childrenType = new IRSequenceType(IRNodeType.prototype);


  /** Create a new (stored) digraph.
   * @param name If non-null
   * this name is used to form registered
   * slots to store information in.
   * @param sf the kind of slots to use to store the graph information in
   * @exception SlotAlreadyRegisteredException
   * 	if the given name has already been used to create a Digraph
   */ 
  public Digraph(String name, final SlotFactory sf)
       throws SlotAlreadyRegisteredException
  {
    if (name == null)
      childrenSlotInfo = sf.newAttribute();
    else
      childrenSlotInfo =
	sf.newAttribute(name + ".Digraph.children", childrenType);
    // we used to use ConstantSlotFactory,
    // and so for backward compatability (reading in old bundles)
    // we need to ensure that it is registered.
    ConstantSlotFactory.ensureLoaded(); 
    mutator = createStoredMutator(sf);
  }

  /** Create a Digraph that delegates to a Digraph presumed to lie
   * behind the given attribute.
   */
  public Digraph(SlotInfo childrenAttribute) {
    childrenSlotInfo = childrenAttribute;
    mutator = createDelegatingMutator();
  }

  /** Add a node to the directed graph.
   * Notify define observers and inform listeners of this new node.
   * @param n a new node to add to the graph
   * @throws SlotImmutableException if node already in graph
   */    
  public void initNode(IRNode n) {
    initNode(n,-1);
  }

  /** Add a node to the directed graph.
   * Notify define observers and inform listeners of this new node.
   * @param n a new node to add to the graph
   * @param numChildren if &gt;= 0 then the number of children
   *        for a fixed arity node.  If &lt; 0, then <tt>~numChildren</tt>
   *        is the number of initial children for a variable arity node.
   * @throws SlotImmutableException if node already in graph
   */
  public void initNode(IRNode n, int numChildren) {
    mutator.initNode(n,numChildren);
  }

  /** Return true if the node is part of this direction graph. */
  public boolean isNode(IRNode n) {
    return n.valueExists(childrenSlotInfo);
  }

  public boolean hasChildren(IRNode node) {
    return getChildren(node).hasElements();
  }
  public int numChildren(IRNode node) {
    return getChildren(node).size();
  }
  /** Return true if children sequence is variable */
  public boolean childrenIsVariable(IRNode node) {
    return getChildren(node).isVariable();
  }

  public IRLocation childLocation(IRNode node, int i) {
    return getChildren(node).location(i);
  }
  public int childLocationIndex(IRNode node, IRLocation loc) {
    return getChildren(node).locationIndex(loc);
  }

  public IRLocation firstChildLocation(IRNode node) {
    IRSequence children = getChildren(node);
    if (children != null) {
      return children.firstLocation();
    } else {
      if (this instanceof SyntaxTree) {
	System.out.print("op = "+((SyntaxTree)this).getOperator(node)+", ");
      }
      System.out.println("n = "+node);
      
      throw new FluidError("Null pointer");
    }
  }
  public IRLocation lastChildLocation(IRNode node) {
    return getChildren(node).lastLocation();
  }
  public IRLocation nextChildLocation(IRNode node, IRLocation loc) {
    return getChildren(node).nextLocation(loc);
  }
  public IRLocation prevChildLocation(IRNode node, IRLocation loc) {
    return getChildren(node).prevLocation(loc);
  }

  public int compareChildLocations(IRNode node,
				   IRLocation loc1, IRLocation loc2) {
    return getChildren(node).compareLocations(loc1,loc2);
  }       

  public boolean hasChild(IRNode node, int i) {
    return getChildren(node).validAt(i);
  }
  public boolean hasChild(IRNode node, IRLocation loc) {
    return getChildren(node).validAt(loc);
  }
  public IRNode getChild(IRNode node, int i) {
    try {
      return (IRNode)(getChildren(node).elementAt(i));
    }
    catch (SlotUndefinedException e) {
      if (this instanceof SyntaxTree) {
      	SyntaxTree t = (SyntaxTree) this;
	      LOG.error("i = "+i+", op = "+t.getOperator(node)+" for "+node);
      }
      throw e;
    }
  }
  public IRNode getChild(IRNode node, IRLocation loc) {
    try {
      return (IRNode)(getChildren(node).elementAt(loc));
    }
    catch (SlotUndefinedException e) {
      if (this instanceof SyntaxTree) {
      	SyntaxTree t = (SyntaxTree) this;
	      LOG.error("loc = "+loc+", op = "+t.getOperator(node)+" for "+node);
      }
      throw e;
    }
  }

  /** return the location of a child within the children of node.
   * @exception IllegalChildException if the child is not present
   */
  protected IRLocation findChild(IRNode node, IRNode child)
       throws IllegalChildException
  {
    IRSequence children = getChildren(node);
    for (IRLocation loc = children.firstLocation(); loc != null;
	 loc=children.nextLocation(loc)) {
      if (children.validAt(loc)) {
	IRNode ch = (IRNode)children.elementAt(loc);
	if (child == ch || (child != null && child.equals(ch))) {
	  return loc;
	}
      }
    }
    throw new IllegalChildException("not a child of node");
  }

  /** Return the location within the children of a node
   * that is current undefined.
   * @exception IllegalChildException if all are defined.
   */
  protected IRLocation findUndefinedChild(IRNode node)
       throws IllegalChildException
  {
    IRSequence children = getChildren(node);
    for (IRLocation loc = children.firstLocation(); loc != null;
	 loc=children.nextLocation(loc)) {
      if (!children.validAt(loc)) return loc;
    }
    throw new IllegalChildException("no undefined children of node");
  }


  /** Set the i'th child of the node to be newChild.
   * @exception IllegalChildException if the child is not suitable
   */
  public void setChild(IRNode node, int i, IRNode newChild) 
       throws IllegalChildException
  {
    setChild(node,getChildren(node).location(i),newChild);
  }
  /** Set the child at location loc of the node to be newChild.
   * @exception IllegalChildException if the child is not suitable
   */
  public void setChild(IRNode node, IRLocation loc, IRNode newChild) 
       throws IllegalChildException
  {
    mutator.setChild(node,loc,newChild);
  }

  /** Check to see if a node can accept another child.
   * @see #addChild
   */
  protected boolean canAdopt(IRNode node) {
    if (node == null) return true;
    IRSequence children = getChildren(node);
    if (children.isVariable()) return true;
    for (IRLocation loc = children.firstLocation(); loc != null;
	 loc=children.nextLocation(loc)) {
      if (!children.validAt(loc) || children.elementAt(loc) == null)
	return true;
    }
    return false;
  }

  /** Adopt a new child to the children without disturbing existing
   * children.  If the children are fixed in size, we look for
   * an undefined or null child location.
   * If the children are variable in size,
   * we append to the end.
   * @exception IllegalChildException if there is no space to add
   * @see #canAdopt
   */
  public void addChild(IRNode node, IRNode newChild)
       throws IllegalChildException
  {
    if (getChildren(node).isVariable()) {
      appendChild(node,newChild);
    } else {
      try {
	setChild(node,findUndefinedChild(node),newChild);
      } catch (IllegalChildException ex) {
	setChild(node,findChild(node,null),newChild);
      }
    }
  }

  /** Replace the node's oldChild with newChild.
   * @exception IllegalChildException if oldChild is not a child, or
   *            newChild is not suitable.
   */
  public void replaceChild(IRNode node, IRNode oldChild, IRNode newChild)
       throws IllegalChildException
  {
    setChild(node,findChild(node,oldChild),newChild);
  }

  /** Add new child as a new child of node at the given insertion point.
   *  @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   * @return location of new child
   */
  public IRLocation insertChild(IRNode node, IRNode newChild, InsertionPoint ip)
       throws IllegalChildException
  {
    return mutator.insertChild(node,newChild,ip);
  }

  /** Add newChild as a new first child of node.
   * @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void insertChild(IRNode node, IRNode newChild)
       throws IllegalChildException
  {
    insertChild(node,newChild,InsertionPoint.first);
  }

  /** Add newChild as a new last child of node.
   * @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void appendChild(IRNode node, IRNode newChild)
       throws IllegalChildException
  {
    insertChild(node,newChild,InsertionPoint.last);
  }

  /** Add newChild as a new child after the given child of node.
   * @exception IllegalChildException if oldChild is not a child, 
   *		newChild is not suitable, or
   *            the parent cannot accept new children.
   */
  public void insertChildAfter(IRNode node, IRNode newChild, IRNode oldChild)
       throws IllegalChildException
  {
    insertChild(node,newChild,
		InsertionPoint.createAfter(findChild(node,oldChild)));
  }

  /** Add newChild as a new child before the given child of node.
   * @exception IllegalChildException if if oldChild is not a child, 
   *		newChild is not suitable, or
   *            the parent cannot accept new children.
   */
  public void insertChildBefore(IRNode node, IRNode newChild, IRNode oldChild)
       throws IllegalChildException
  {
    insertChild(node,newChild,
		InsertionPoint.createBefore(findChild(node,oldChild)));
  }

  /** Remove oldChild from the sequence of children of a node.
   * If the sequence is variable, we get rid of its location too,
   * otherwise, we substitute null.
   * @see #addChild
   * @exception IllegalChildException if oldChild is not a child,
   */
  public void removeChild(IRNode node, IRNode oldChild)
       throws IllegalChildException
  {
    removeChild(node,findChild(node,oldChild));
  }

  /** Remove the child (if any) at the given location.
   * Replace with null if sequence is fixed.
   */
  public void removeChild(IRNode node, IRLocation loc)
  {
    if (getChildren(node).isVariable()) {
      mutator.removeChild(node,loc);
    } else {
      setChild(node,loc,null);
    }
  }

  /** Remove all the children of a node. */
  public void removeChildren(IRNode node)
  {
    IRSequence children = getChildren(node);
    for (IRLocation loc = firstChildLocation(node); loc != null;) {
      IRLocation next = children.nextLocation(loc);
      removeChild(node,loc);
      loc = next;
    }
  }

  /** Return the children of a node in order. */
  public Enumeration<IRNode> children(IRNode node) {
    return mutator.protect(getChildren(node).elements());
  }

  /** Return an enumeration of the nodes in the graph.
   * First we return the root given and then recursively
   * the enumerations of each of its children.
   */
  public Enumeration<IRNode> depthFirstSearch(IRNode node) {
    return mutator.protect(new DepthFirstSearch(this,node));
  }

  /** Return slot info for given name.
   * Consistency is 
   */
  public SlotInfo getAttribute(String name) {
    return mutator.getAttribute(name);
  }

  /** Add digraph attributes to a bundle.
   */
  public void saveAttributes(Bundle b) {
    mutator.saveAttributes(b);
  }

  /**
   * Describe information about this node for debugging.
   */
  public void describeNode(IRNode n, PrintStream out) {
    childrenSlotInfo.describeSlot(n,out);
    try {
      IRSequence ch = getChildren(n);
      out.print("  children => ");
      ch.describe(out);
    } catch (SlotUndefinedException ex) {
      // discard exception (just debugging)
    }
  }

  protected class StoredMutator implements Mutator {
    protected /*final*/ SlotFactory slotFactory;

    protected StoredMutator(SlotFactory sf) {
      slotFactory = sf;
    }

    public void initNode(IRNode n, int numChildren) {
      setChildren(n, slotFactory.newSequence(numChildren));
      notifyIRObservers(n); // the children attribute is no longer constant.
      if (hasListeners()) {
	informDigraphListeners(new NewNodeEvent(Digraph.this,n));
      }
    }

    /** Called to declare that a node has a new parent.
     * Overridden in subclasses to record the information.
     * @param initial true if this parent->child binding
     * is the first definition for this parent.
     * @return true if initial and this is initial parent for child.
     * @see Tree#addParent
     * @see SymmetricDigraph#addParent
     */
    protected boolean addParent(IRNode child, IRNode parent, IRLocation loc,
				boolean initial)
    {
      return initial;
    }

    /** Called to declare that a node has lost a parent.
     * Overridden in subclasses to record the information.
     * @see Tree#removeParent
     * @see SymmetricDigraph#removeParent
     */
    protected void removeParent(IRNode child, IRNode parent, IRLocation loc)
      {
      }

    /** Called to check if a node is suitable as a new child for
     * a particular parent node and location.
     * @exception IllegalChildException if the child is not suitable
     */
    protected void checkNewChild(IRNode parent, IRLocation loc, IRNode child) 
      throws IllegalChildException
    {
    }
    
    /** Called to check if a node is suitable as an additional child
     * for a particular parent node.
     * @exception IllegalChildException if the child is not suitable
     *            or if the node cannot take a variable number of children.
     */
    protected void checkNewVariableChild(IRNode parent, IRNode child)
      throws IllegalChildException
    {
      if (!getChildren(parent).isVariable()) {
      	throw new IllegalChildException("node cannot accept new children");
      }
    }

    public void setChild(IRNode node, IRLocation loc, IRNode newChild)
      throws IllegalChildException 
    {
      /*
      if (newChild == null) {
        LOG.debug("newChild is null at version "+Version.getVersion());
      }
      */
      IRSequence children = getChildren(node);
      IRNode oldChild;
      boolean oldChildDefined;

      checkNewChild(node, loc, newChild);
      
      if (children.validAt(loc)) {
        oldChild = (IRNode) children.elementAt(loc);
        oldChildDefined = true;
        if (oldChild == newChild
          || (oldChild != null && oldChild.equals(newChild)))
          return;
      }
      else {
        oldChild = null;
        oldChildDefined = false;
      }
      boolean initial = !oldChildDefined;
      if (newChild != null) {
        initial = addParent(newChild, node, loc, initial);
      }
      if (!oldChildDefined && !initial) // force null to be initial binding
        children.setElementAt(null, loc);
        
      children.setElementAt(newChild, loc);
      if (oldChild != null) {
        removeParent(oldChild, node, loc);
      }
      if (!initial)
        notifyIRObservers(node);
      else
        notifyDefineObservers(node); //? Is this a definition still ?
      if (hasListeners()) {
        DigraphEvent e;
        if (!initial)
          e =
            new ChangedChildEvent(Digraph.this, node, loc, oldChild, newChild);
        else
          e = new NewChildEvent(Digraph.this, node, loc, newChild);
        informDigraphListeners(e);
      }
    }

    public IRLocation insertChild(IRNode node, IRNode newChild, InsertionPoint ip)
       throws IllegalChildException
    {
      IRSequence children = getChildren(node);
      checkNewVariableChild(node,newChild);
      IRLocation newloc = ip.insert(children,newChild);
      if (newChild != null) {
	addParent(newChild,node,newloc,false);
      }
      notifyIRObservers(node);
      if (hasListeners()) {
	DigraphEvent ev = new NewChildEvent(Digraph.this,node,newloc,newChild);
	informDigraphListeners(ev);
      }
      return newloc;
    }

    public void removeChild(IRNode node, IRLocation loc)
    {
      IRSequence children = getChildren(node);
      IRNode oldChild = null;
      if (children.validAt(loc)) oldChild = (IRNode)children.elementAt(loc);
      children.removeElementAt(loc);
      if (oldChild != null) removeParent(oldChild,node,loc);
      notifyIRObservers(node);
      if (hasListeners()) {
	informDigraphListeners(new RemoveChildEvent(Digraph.this,node,loc,
						    oldChild));
      }
    }
    public void saveAttributes(Bundle b) {
      b.saveAttribute(childrenSlotInfo);
    }
    public Enumeration protect(Enumeration e) {
      return slotFactory.newEnumeration(e);
    }

    protected final SlotInfo wrappedChildrenAttribute =
      new WrappedChildrenSlotInfo();
    
    public SlotInfo getAttribute(String name) {
      if (name.equals(CHILDREN)) return wrappedChildrenAttribute;
      else return null;
    }

    class WrappedChildrenSlotInfo extends DerivedSlotInfo {
      public IRType type() {
        return childrenType;
      }
      protected boolean valueExists(IRNode node) {
	return isNode(node);
      }
      protected Object getSlotValue(IRNode node) {
	return new ChildrenWrapper(node,getChildren(node));
      }
      protected void setSlotValue(IRNode node, Object value) {
	IRSequence seq = (IRSequence)value;
	int numChildren = seq.size();
	int initChildren = numChildren;
	if (seq.isVariable()) initChildren = ~initChildren;
	initNode(node,initChildren);
	IRSequence seqp = getChildren(node);
	IRLocation loc, locp;
	for (loc = seq.firstLocation(),locp = seqp.firstLocation();
	     loc != null;
	     loc = seq.nextLocation(loc), locp = seqp.nextLocation(locp)) {
	  if (seq.validAt(loc)) {
	    setChild(node,locp,(IRNode)seq.elementAt(loc));
	  }
	}
      }
    }

    protected class ChildrenWrapper extends IRSequenceWrapper {
      final IRNode parent;
      public ChildrenWrapper(IRNode node, IRSequence real) {
	super(real);
	parent = node;
      }
      public void setElementAt(Object node, IRLocation loc) {
	setChild(parent,loc,(IRNode)node);
      }
      public IRLocation insertElementAt(Object obj, InsertionPoint ip) {
	return insertChild(parent,(IRNode)obj,ip);
      }
      public void removeElementAt(IRLocation loc) {
	removeChild(parent,loc);
      }
    }
  }

  protected class DelegatingMutator implements Mutator {
    public void initNode(IRNode node, int numChildren) {
      setChildren(node,SimpleSlotFactory.prototype.newSequence(numChildren));
    }
    public void setChild(IRNode node, IRLocation loc, IRNode child) {
      getChildren(node).setElementAt(child,loc);
    }
    public IRLocation insertChild(IRNode node, IRNode newChild, InsertionPoint ip) {
      return ip.insert(getChildren(node),newChild);
    }
    public void removeChild(IRNode node, IRLocation loc) {
      getChildren(node).removeElementAt(loc);
    }
    public void saveAttributes(Bundle b) {}
    public SlotInfo getAttribute(String name) {
      if (name == CHILDREN) return childrenSlotInfo;
      else return null;
    }
    public Enumeration protect(Enumeration e) {
      return e;
    }
  }
}
