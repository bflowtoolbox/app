/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SinkEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** A class for events where the locus is an edge and
 * the sink of the  edge is changed.
 * @see NewSinkEvent
 * @see ChangedSinkEvent
 */
public class SinkEvent extends EdgeNodeEvent {
  public SinkEvent(DigraphInterface dig, IRNode e, IRNode n) {
    super(dig,e,n);
  }
  public IRNode getSinkNode() { return getNode(); }
}
