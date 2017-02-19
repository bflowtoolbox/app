/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/SourceEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** A class for events where the locus is an edge and
 * the source of the  edge is changed.
 * @see NewSourceEvent
 * @see ChangedSourceEvent
 */
public class SourceEvent extends EdgeNodeEvent {
  public SourceEvent(DigraphInterface dig, IRNode e, IRNode n) {
    super(dig,e,n);
  }
  public IRNode getSourceNode() { return getNode(); }
}
