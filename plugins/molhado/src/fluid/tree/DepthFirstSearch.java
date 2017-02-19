/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/DepthFirstSearch.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Stack;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotInfo;

/** An enumeration of the nodes in a directed graph.
 * "Depth-first" means we look at all nodes reachable through
 * one child before looking at nodes reachable through
 * the next child.  The enumeration is <em>not</em> protected
 * from mutation.
 * @see DigraphInterface
 */
public class DepthFirstSearch implements Enumeration {
  protected final DigraphInterface digraph;
  protected final Stack stack = new Stack();
  private IRNode node = null;
  private IRLocation loc = null;
  private final SlotInfo markInfo =
      SimpleSlotFactory.makeSlotInfo(Boolean.FALSE);
  private final boolean bottomUp;

  private Object nextResult;

  public DepthFirstSearch(DigraphInterface dig, IRNode root) {
    digraph = dig;
    bottomUp = false;
    visit(root);
  }

  public DepthFirstSearch(DigraphInterface dig, IRNode root, boolean bu) {
    digraph = dig;
    bottomUp = bu;
    visit(root);
  }

  protected void pushState() {
    stack.push(node);
    stack.push(loc);
  }

  protected void popState() {
    loc = (IRLocation)stack.pop();
    node = (IRNode)stack.pop();
  }
    
  protected void visit(IRNode n) {
    if (bottomUp) {
      nextResult = null;
    } else {
      nextResult = n;
    }
    pushState();
    node = n;
    loc = digraph.firstChildLocation(n);
  }

  protected static final Object noNext = new Object();

  protected Object getNext() {
    while (nextResult == null) {
      if (loc != null) {
	IRNode newNode = digraph.getChild(node,loc);
	loc = digraph.nextChildLocation(node,loc);
	if (mark(newNode)) {
	  if (newNode == null) {
	    System.out.println("DFS IRNode is null");
	  } else {
	    visit(newNode);
	  }
	}
      } else if (node == null) {
	// throw new NoSuchElementException("depth first search is over");
	return noNext;
      } else if (!additionalChildren(node)) {
	if (bottomUp) nextResult = node;
	popState();
      }
    }
    try {
      return nextResult;
    } finally {
      nextResult = null;
    }
  }

  public boolean hasMoreElements() {
    if (nextResult != null) return true;
    if (node == null) return false;
    
    Object rv = getNext();
    if (rv == noNext) {
      return false;
    } 
    nextResult = rv;
    return true;
  }

  public Object nextElement() throws NoSuchElementException {
    Object rv = getNext();
    if (rv == noNext) {
      throw new NoSuchElementException("depth first search is over");
    } 
    return rv;
  }

  /** Mark this node and return true if it was not marked before. */
  protected boolean mark(IRNode node) {
    // System.out.println("IRNode = "+node);
    if (node == null) return false;
    if (((Boolean)node.getSlotValue(markInfo)).booleanValue()) return false;
    node.setSlotValue(markInfo,Boolean.TRUE);
    return true;
  }

  /** Handle additional children.
   * Call visit(...) on additional children.
   * Overridden in some subclasses.
   * @return true if additional children are to be traversed.
   */
  protected boolean additionalChildren(IRNode node) {
    return false;
  }
}
