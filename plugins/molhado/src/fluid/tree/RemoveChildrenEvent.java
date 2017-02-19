/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/RemoveChildrenEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** Children of node have all been removed.
 */
public class RemoveChildrenEvent extends NodeEvent {
  public RemoveChildrenEvent(DigraphInterface dig, IRNode parent) {
    super(dig,parent);
  }
  public IRNode getParent() { return getNode(); }
}
