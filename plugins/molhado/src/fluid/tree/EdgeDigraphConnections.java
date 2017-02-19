/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/EdgeDigraphConnections.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;

/** An enumeration of connections between two nodes.
 * The first node must be a node in an edge digraph,
 * the second node may be null.
 */
public class EdgeDigraphConnections implements Enumeration {
  private final EdgeDigraphInterface digraph;
  private final IRNode node1, node2;
  private final Enumeration childEdges;

  public EdgeDigraphConnections(EdgeDigraphInterface ed, IRNode n1, IRNode n2) {
    digraph = ed;
    node1 = n1;
    node2 = n2;
    childEdges = digraph.childEdges(n1);
  }

  private boolean nextIsValid = false;
  private Object nextEdge = null;

  public boolean hasMoreElements() {
    if (!nextIsValid) {
      try {	
	nextEdge = nextElement();
      } catch (NoSuchElementException e) {
	nextEdge = null;
	nextIsValid = false;
      }
    }
    nextIsValid = true;
    return nextEdge != null;
  }
  
  public Object nextElement() throws NoSuchElementException {
    if (nextIsValid) {
      if (nextEdge == null)
	throw new NoSuchElementException("no more children");
      nextIsValid = false;
      return nextEdge;
    }
    for (;;) {
      IRNode edge = (IRNode)childEdges.nextElement();
      try {
	IRNode sink = digraph.getSink(edge);
	if (node2 == null ? null == sink : node2.equals(sink)) {
	  return edge;
	}
      } catch (SlotUndefinedException e) {
      }
    }
  }
}
