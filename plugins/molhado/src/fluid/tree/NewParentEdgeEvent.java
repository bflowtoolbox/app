/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/NewParentEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** A node gets a new incoming edge.
 */
public class NewParentEdgeEvent extends ParentEdgeEvent {
  public NewParentEdgeEvent(DigraphInterface d, IRNode n, IRLocation loc, IRNode e) {
    super(d,n,loc,e);
  }
}
