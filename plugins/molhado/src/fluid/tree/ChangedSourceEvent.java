/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedSourceEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

/** A class for events where the locus is an edge and
 * the source of the edge is changed.
 */
public class ChangedSourceEvent extends SourceEvent {
  private final IRNode oldSource;
  public ChangedSourceEvent(DigraphInterface dig, IRNode e, IRNode n0, IRNode n1) {
    super(dig,e,n1);
    oldSource = n0;
  }
  public IRNode getOldSource() {
    return oldSource;
  }
}
