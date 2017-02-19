/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedSinkEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** A class for events where the locus is an edge and
 * the sink of the edge is changed.
 */
public class ChangedSinkEvent extends SinkEvent {
  private final IRNode oldSink;
  public ChangedSinkEvent(DigraphInterface dig, IRNode e, IRNode n0, IRNode n1)
  {
    super(dig,e,n1);
    oldSink = n0;
  }
  public IRNode getOldSink() {
    return oldSink;
  }
}
