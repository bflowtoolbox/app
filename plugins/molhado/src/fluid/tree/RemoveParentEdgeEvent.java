/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/RemoveParentEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class RemoveParentEdgeEvent extends ParentEdgeEvent {
  public RemoveParentEdgeEvent(DigraphInterface d, IRNode n, IRLocation l, IRNode e) {
    super(d,n,l,e);
  }
}
