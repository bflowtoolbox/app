/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/RemoveParentsEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** Parents of node have all been removed.
 */
public class RemoveParentsEvent extends NodeEvent {
  public RemoveParentsEvent(DigraphInterface dig, IRNode parent) {
    super(dig,parent);
  }
  public IRNode getParent() { return getNode(); }
}
