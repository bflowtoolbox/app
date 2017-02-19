/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedChildEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** A node's outgoing edge is changed.
 */
public class ChangedChildEdgeEvent extends ChildEdgeEvent {
  private final IRNode oldEdge;
  public ChangedChildEdgeEvent(DigraphInterface d, IRNode n, IRLocation loc,
			       IRNode e0, IRNode e1) {
    super(d,n,loc,e1);
    oldEdge = e0;
  }
}
