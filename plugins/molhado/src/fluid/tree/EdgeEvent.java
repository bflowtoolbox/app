/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/EdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** Events for digraphs where the edge is the locus.
 * @see NewEdgeEvent
 * @see EdgeNodeEvent
 */
public class EdgeEvent extends DigraphEvent {
  public EdgeEvent(DigraphInterface dig, IRNode n) {
    super(dig,n);
  }
  public IRNode getEdge() { return getLocus(); }
}
