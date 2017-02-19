/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChildEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Class of events modifying outgoing edges of a node.
 * @see NewChildEdgeEvent
 * @see ChangedChildEdgeEvent
 * @see RemoveChildEdgeEvent
 */
public class ChildEdgeEvent extends NodeEdgeEvent {
  public ChildEdgeEvent(DigraphInterface d, IRNode n, IRLocation loc, IRNode e) {
    super(d,n,loc,e);
  }
}
