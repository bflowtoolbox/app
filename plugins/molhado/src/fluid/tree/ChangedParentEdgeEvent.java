/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedParentEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** A node's incoming edge is changed.
 */
public class ChangedParentEdgeEvent extends ParentEdgeEvent {
  private final IRNode oldEdge;
  public ChangedParentEdgeEvent(DigraphInterface d, IRNode n, IRLocation loc,
				IRNode e0, IRNode e1) {
    super(d,n,loc,e1);
    oldEdge = e0;
  }
}
