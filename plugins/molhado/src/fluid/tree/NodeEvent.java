/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/NodeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** Events for digraphs where the node is the locus.
 * @see NewNodeEvent
 * @see ChildEvent
 * @see ParentEvent
 * @see NodeEdgeEvent
 * @see RemoveChildrenEvent
 * @see RemoveParentsEvent
 */
public class NodeEvent extends DigraphEvent {
  public NodeEvent(DigraphInterface dig, IRNode n) {
    super(dig,n);
  }
  public IRNode getNode() { return getLocus(); }
}
