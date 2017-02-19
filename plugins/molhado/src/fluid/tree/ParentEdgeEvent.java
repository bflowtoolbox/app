/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ParentEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Class of events modifying incoming edges of a node.
 * @see NewParentEdgeEvent
 * @see ChangedParentEdgeEvent
 * @see RemoveParentEdgeEvent
 */
public class ParentEdgeEvent extends NodeEdgeEvent {
  public ParentEdgeEvent(DigraphInterface d, IRNode n, IRLocation loc, IRNode e) {
    super(d,n,loc,e);
  }
}
