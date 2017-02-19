/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/RemoveChildEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class RemoveChildEdgeEvent extends ChildEdgeEvent {
  public RemoveChildEdgeEvent(DigraphInterface d, IRNode n, IRLocation l, IRNode e) {
    super(d,n,l,e);
  }
}
