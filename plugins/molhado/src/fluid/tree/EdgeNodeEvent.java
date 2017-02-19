/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/EdgeNodeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** A class for events where the locus is an edge and
 * the source or sink of the  edge is changed.
 */
public class EdgeNodeEvent extends EdgeEvent {
  private IRNode node;
  public EdgeNodeEvent(DigraphInterface dig, IRNode e, IRNode n) {
    super(dig,e);
    node = n;
  }
  public IRNode getNode() {
    return node;
  }
}
