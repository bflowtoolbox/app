/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SymmetricEdgeDigraph.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import fluid.ir.DerivedSlotInfo;
import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceException;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.InsertionPoint;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;

/** Graphs with explicit edges which can be traversed in either direction.
 * <P> Known bugs:
 * <ul>
 * <li> The insertion point for parents is ignored.
 * <li> See @{link SymmetricDigraph} for additional bugs.
 * </ul>
 */
public class SymmetricEdgeDigraph extends EdgeDigraph
implements MutableSymmetricEdgeDigraphInterface
{
  protected interface Mutator extends EdgeDigraph.Mutator {
    public void initNode(IRNode n, int numParents, int numChildren);
  }

  protected EdgeDigraph.Mutator createStoredMutator(SlotFactory sf) {
    return new StoredMutator(sf);
  }
  protected EdgeDigraph.Mutator createDelegatingMutator() {
    return new DelegatingMutator();
  }
  
  /*final*/ protected SymmetricDigraph underlyingNodes;
  /*final*/ protected SymmetricDigraph underlyingEdges;
  
  public SymmetricEdgeDigraph(String name, SlotFactory sf) 
       throws SlotAlreadyRegisteredException
  {
    super(name,sf, new SymmetricDigraph(name,sf));
    underlyingNodes = (SymmetricDigraph)super.underlyingNodes;
    underlyingEdges = (SymmetricDigraph)super.underlyingEdges;
  }
  
  public SymmetricEdgeDigraph(SlotInfo childEdgesAttribute,
			      SlotInfo parentEdgesAttribute,
                     	      SlotInfo sinksAttribute,
			      SlotInfo sourcesAttribute,
			      SlotInfo isEdgeAttribute)
  {
    super(new SymmetricDigraph(childEdgesAttribute,parentEdgesAttribute),
	  new SymmetricDigraph(sinksAttribute,sourcesAttribute),
	  isEdgeAttribute);
    underlyingNodes = (SymmetricDigraph)super.underlyingNodes;
    underlyingEdges = (SymmetricDigraph)super.underlyingEdges;
  }

  public void initNode(IRNode n, int numParents, int numChildren) {
    ((Mutator)mutator).initNode(n,numParents,numChildren);
  }
  
  protected DigraphEvent transformNodeEvent(IRNode n, DigraphEvent ev) {
    if (ev instanceof ParentEvent) {
      IRLocation loc = ((ParentEvent)ev).getLocation();
      IRNode parent = ((ParentEvent)ev).getParent();
      if (ev instanceof NewParentEvent) {
	return new NewParentEdgeEvent(this,n,loc,parent);
      } else if (ev instanceof RemoveParentEvent) {
	return new RemoveParentEdgeEvent(this,n,loc,parent);
      } else if (ev instanceof ChangedParentEvent) { /* won't happen */
	IRNode oldParent = ((ChangedParentEvent)ev).getOldParent();
	return new ChangedParentEdgeEvent(this,n,loc,oldParent,parent);
      }
    } 
    return super.transformNodeEvent(n,ev);
  }

  protected DigraphEvent transformEdgeEvent(IRNode e, DigraphEvent ev) {
    if (ev instanceof ParentEvent) {
      IRLocation loc = ((ParentEvent)ev).getLocation();
      IRNode source = ((ParentEvent)ev).getParent();
      if (ev instanceof NewParentEvent) {
	return new NewSourceEvent(this,e,source);
      } else if (ev instanceof ChangedParentEvent) { /* won't happen */
	IRNode oldSource = ((ChangedParentEvent)ev).getOldParent();
	return new ChangedSourceEvent(this,e,oldSource,source);
      }
    } 
    return super.transformEdgeEvent(e,ev);
  }

  public IRNode getSource(IRNode e) {
    assertEdge(e);
    return underlyingEdges.getParent(e,0);
  }

  // NB: we use a different technique for handling mutations than EdgeDigraph:
  // we go ahead and perform the (partially redundant) checks.

  /** Set the source of an edge keeping the structure consistent. */
  public void setSource(IRNode e, IRNode n)
       throws StructureException
  {
    assertEdge(e);
    if (n != null) assertNode(n);
    underlyingEdges.setParent(e,0,n);
  }

  /** Return the i'th edge arriving at a node. */
  public IRNode getParentEdge(IRNode node, int i) {
    assertNode(node);
    return underlyingNodes.getParent(node,i);
  }

  /** Return the ingoing edge at location loc. */
  public IRNode getParentEdge(IRNode node, IRLocation loc) {
    assertNode(node);
    return underlyingNodes.getParent(node,loc);
  }

  /** Add a new incoming edge to a node.
   * If the parents are fixed in size, we look for
   * a null or undefined parent to replace.
   * If the parents are variable in size, we append to the end.
   * @exception StructureException if there is no space to add
   */
  public void addParentEdge(IRNode node, IRNode newParentEdge)
       throws StructureException
  {
    assertNode(node);
    if (newParentEdge != null) assertEdge(newParentEdge);
    underlyingNodes.addParent(node,newParentEdge);
  }

  /** Remove the link between an incoming edge and a node.
   * Neither may be null.
   * @see #addParentEdge
   * @exception StructureException
   *            if parentEdge is not an incoming edge of node
   */
  public void removeParentEdge(IRNode node, IRNode parentEdge)
       throws StructureException
  {
    assertNode(node);
    if (parentEdge != null) assertEdge(parentEdge);
    underlyingNodes.removeParent(node,parentEdge);
  }

  /** Replace the node's oldParentEdge with newParentEdge.
   * @exception StructureException if oldParentEdge is not an incoming edge, 
   *            or newParentEdge is not suitable.
   */
  public void replaceParentEdge(IRNode node,
				IRNode oldParentEdge,
				IRNode newParentEdge)
       throws StructureException
  {
    assertNode(node);
    if (oldParentEdge != null) assertEdge(oldParentEdge);
    if (newParentEdge != null) assertEdge(newParentEdge);
    underlyingNodes.replaceParent(node,oldParentEdge,newParentEdge);
  }

  /** Set the parent edge.
   * See caveats on @{link SymmetricDigraph#setParent(IRNode,int,IRNode)}.
   * @param parentEdge incoming edge to use (may be null)
   */
  public void setParentEdge(IRNode node, int i, IRNode parentEdge) {
    assertNode(node);
    if (parentEdge != null) assertEdge(parentEdge);
    underlyingNodes.setParent(node,i,parentEdge);
  }

  /** Set the parent edge. See caveats
   * on @{link SymmetricDigraph#setParent(IRNode,IRLocation,IRNode)}.
   * @param parentEdge incoming edge to use (may be null)
   */
  public void setParentEdge(IRNode node, IRLocation loc, IRNode parentEdge) {
    assertNode(node);
    if (parentEdge != null) assertEdge(parentEdge);
    underlyingNodes.setParent(node,loc,parentEdge);
  }

  /** Insert a parent.
   * <strong>The insertion point is ignored.</strong>
   */
  public IRLocation insertParentEdge(IRNode node, IRNode parentEdge,
				     InsertionPoint ip)
  {
    assertNode(node);
    if (parentEdge != null) assertEdge(parentEdge);
    underlyingNodes.addParent(node,parentEdge);
    return underlyingNodes.findParent(node,parentEdge);
  }

  /** Return an enumeration of the incoming edges to a node. */
  public Enumeration parentEdges(IRNode node) {
    assertNode(node);
    return underlyingNodes.parents(node);
  }

  /** Remove an edge from the graph. */
  public void disconnect(IRNode edge) {
    setSource(edge,null);
    setSink(edge,null);
  }

  /** Remove the children of a node and the connecting edge. */
  public void removeChildren(IRNode node) {
    Enumeration e = childEdges(node);
    while (e.hasMoreElements()) {
      try {
	IRNode edge = (IRNode)e.nextElement();
	disconnect(edge);
      } catch (SlotUndefinedException unused) {
      }
    }
  }

  /** Remove the parents of a node and the connecting edge. */
  public void removeParents(IRNode node) {
    Enumeration e = parentEdges(node);
    while (e.hasMoreElements()) {
      try {
	IRNode edge = (IRNode)e.nextElement();
	disconnect(edge);
      } catch (SlotUndefinedException unused) {
      }
    }
  }

  /** Remove a node from the graph and all connecting edges. */
  public void removeNode(IRNode node) {
    assertNode(node);
    removeChildren(node);
    removeParents(node);
  }

  // routines to satisfy SymmetricDigraphInterface

  public boolean hasParents(IRNode node) {
    return underlyingNodes.hasParents(node);
  }
  public int numParents(IRNode node) {
    return underlyingNodes.numParents(node);
  }

  public IRLocation parentLocation(IRNode node, int i) {
    return underlyingNodes.parentLocation(node,i);
  }
  public int parentLocationIndex(IRNode node, IRLocation loc) {
    return underlyingNodes.parentLocationIndex(node,loc);
  }

  public IRLocation firstParentLocation(IRNode node) {
    return underlyingNodes.firstParentLocation(node);
  }
  public IRLocation lastParentLocation(IRNode node) {
    return underlyingNodes.firstParentLocation(node);
  }
  public IRLocation nextParentLocation(IRNode node, IRLocation loc) {
    return underlyingNodes.nextParentLocation(node,loc);
  }
  public IRLocation prevParentLocation(IRNode node, IRLocation loc) {
    return underlyingNodes.prevParentLocation(node,loc);
  }

  public int compareParentLocations(IRNode node,
				    IRLocation loc1, IRLocation loc2) {
    return underlyingNodes.compareParentLocations(node,loc1,loc2);
  }

  public IRNode getParent(IRNode node, int i) {
    return getSource(getParentEdge(node,i));
  }
  public IRNode getParent(IRNode node, IRLocation loc) {
    return getSource(getParentEdge(node,loc));
  }

  public Enumeration parents(IRNode node) {
    return mutator.protect(new ParentEnumeration(this,node));
  }

  private final SlotInfo wrappedParentsAttribute =
    new WrappedParentsSlotInfo();
  private SlotInfo underlyingParentsAttribute = null;

  public SlotInfo getAttribute(String name) {
    if (name.equals("parents")) return wrappedParentsAttribute;
    if (name.equals("children")) return super.getAttribute(name);
    else return mutator.getAttribute(name);
  }

  class WrappedParentsSlotInfo extends DerivedSlotInfo {
    protected boolean valueExists(IRNode node) {
      return isNode(node);
    }
    protected Object getSlotValue(IRNode node) {
      // make sure initialized
      if (underlyingParentsAttribute == null)
	underlyingParentsAttribute = underlyingNodes.getAttribute("parents");
      assertNode(node);
      IRSequence parentEdges =
        (IRSequence)node.getSlotValue(underlyingParentsAttribute);
      return new ParentsWrapper(parentEdges);
    }
  }

  class ParentsWrapper extends IRSequenceWrapper {
    ParentsWrapper(IRSequence seq) {
      super(seq);
    }
    public void setElementAt(Object parent, IRLocation loc) {
      setSource((IRNode)super.elementAt(loc), (IRNode)parent);
    }
    public IRLocation insertElementAt(Object parent, InsertionPoint ip) {
      throw new IRSequenceException("Cannot insert a parent without parent edge");
    }
    public void removeElementAt(IRLocation loc) {
      throw new IRSequenceException("Cannot remove a parent without parent edge");
    }
    public Object elementAt(IRLocation loc) {
      return getSource((IRNode)super.elementAt(loc));
    }
    public Enumeration elements() {
      return new Enumeration() {
        final Enumeration edges = ParentsWrapper.super.elements();
        public boolean hasMoreElements() {
          return edges.hasMoreElements();
        }
        public Object nextElement() {
          return getSource((IRNode)edges.nextElement());
        }
      };
    }
  }

  class StoredMutator extends EdgeDigraph.StoredMutator implements Mutator {
    public StoredMutator(SlotFactory sf) {
      super(sf);
    }

    public void initNode(IRNode n, int numParents, int numChildren) {
      initBareNode(n);
      underlyingNodes.initNode(n,numParents,numChildren);
    }
    public void initEdge(IRNode e) {
      initBareEdge(e);
      underlyingEdges.initNode(e,1,1);
    }

    private final SlotInfo wrappedParentEdgesAttribute =
      new WrappedParentEdgesSlotInfo();
    private final SlotInfo wrappedSourcesAttribute =
      new WrappedSourcesSlotInfo();

    public SlotInfo getAttribute(String name) {
      if (name.equals("parentEdges")) return wrappedParentEdgesAttribute;
      else if (name.equals("sources")) return wrappedSourcesAttribute;
      else return super.getAttribute(name);
    }

    class WrappedParentEdgesSlotInfo extends DerivedSlotInfo {
      private SlotInfo underlyingParentEdgesAttribute;
      protected boolean valueExists(IRNode node) {
        return isNode(node);
      }
      protected Object getSlotValue(IRNode node) {
	if (underlyingParentEdgesAttribute == null)
	  underlyingParentEdgesAttribute =
	    underlyingNodes.getAttribute("parents");
        assertNode(node);
        IRSequence parentEdges =
          (IRSequence)node.getSlotValue(underlyingParentEdgesAttribute);
        return new ParentEdgesWrapper(node,parentEdges);
      }
      protected void setSlotValue(IRNode node, Object value) {
	if (underlyingParentEdgesAttribute == null)
	  underlyingParentEdgesAttribute =
	    underlyingNodes.getAttribute("parents");
        IRSequence seq = (IRSequence)value; // force exception early
        initBareNode(node);
        node.setSlotValue(underlyingParentEdgesAttribute,seq);
      }
    }

    class ParentEdgesWrapper extends IRSequenceWrapper {
      final IRNode child;
      public ParentEdgesWrapper(IRNode c, IRSequence realedges) {
        super(realedges);
        child = c;
      }
      public void setElementAt(Object o, IRLocation loc) {
        IRNode parent = (IRNode)o;
        setParentEdge(child,loc,parent);
      }
      public IRLocation insertElementAt(Object o, InsertionPoint ip) {
        IRNode parent = (IRNode)o;
        return insertParentEdge(child,parent,ip);
      }
      public void removeElementAt(IRLocation loc) {
        // no need to override since it does not affect node/edge relations
        super.removeElementAt(loc);
      }
    }

    class WrappedSourcesSlotInfo extends DerivedSlotInfo {
      private SlotInfo underlyingSourcesAttribute;

      protected boolean valueExists(IRNode edge) {
        return isEdge(edge);
      }
      protected Object getSlotValue(IRNode edge) {
	if (underlyingSourcesAttribute == null)
	  underlyingSourcesAttribute =
	    underlyingEdges.getAttribute("parents");
        assertEdge(edge);
        IRSequence sources =
          (IRSequence)edge.getSlotValue(underlyingSourcesAttribute);
        return new SourcesWrapper(edge,sources);
      }
      protected void setSlotValue(IRNode edge, Object value) {
	if (underlyingSourcesAttribute == null)
	  underlyingSourcesAttribute =
	    underlyingEdges.getAttribute("parents");
        IRSequence seq = (IRSequence)value; // force exception early
        if (seq.isVariable() || seq.size() != 1)
          throw new StructureException("sources must be a fixed sequence of one");
        initBareEdge(edge);
        edge.setSlotValue(underlyingSourcesAttribute,seq);
      }
    }

    class SourcesWrapper extends IRSequenceWrapper {
      final IRNode edge;
      public SourcesWrapper(IRNode e, IRSequence realedges) {
        super(realedges);
        edge = e;
      }
      public void setElementAt(Object o, IRLocation loc) {
        IRNode child = (IRNode)o;
        if (locationIndex(loc) != 0)
          throw new IRSequenceException("out of range");
        setSource(edge,child);
      }
      public IRLocation insertElementAt(Object o, InsertionPoint ip) {
        throw new IRSequenceException("not variable");
      }
      public void removeElementAt(IRLocation loc) {
        throw new IRSequenceException("not variable");
      }
    }
  }

  class DelegatingMutator extends EdgeDigraph.DelegatingMutator implements Mutator {
    public void initNode(IRNode n, int numParents, int numChildren) {
      underlyingNodes.initNode(n,numParents,numChildren);
    }

    public SlotInfo getAttribute(String name) {
     if (name.equals("parentEdges")) return underlyingNodes.getAttribute("parents");
     else if (name.equals("sinks")) return underlyingEdges.getAttribute("parents");
     else return super.getAttribute(name);
   }
  }
}

class ParentEnumeration implements Enumeration {
  private final SymmetricDigraphInterface digraph;
  private final IRNode parent;
  private IRLocation next = null;

  ParentEnumeration(SymmetricEdgeDigraph ed, IRNode node) {
    digraph = ed;
    parent = node;
    try {
      next = digraph.firstParentLocation(parent);
    } catch (IRSequenceException e) {
      next = null;
    }
  }

  public boolean hasMoreElements() {
    return next != null;
  }

  public Object nextElement() throws NoSuchElementException {
    if (next == null) throw new NoSuchElementException("no more children");
    try {
      return digraph.getParent(parent,next);
    } finally {
      try {
        next = digraph.nextParentLocation(parent,next);
      } catch (IRSequenceException e) {
        next = null;
      }
    }
  }
}





