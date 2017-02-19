/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/DigraphEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.EventObject;

import fluid.ir.IRNode;

/** Abstract class for events for directed graphs.
 * @see NodeEvent
 * @see EdgeEvent
 */
public abstract class DigraphEvent extends EventObject {
  private final DigraphInterface digraph;
  private final IRNode locus;
  public DigraphEvent(DigraphInterface dig, IRNode n) {
    super(dig);
    digraph = dig;
    locus = n;
  }
  public DigraphInterface getDigraph() { return digraph; }
  public IRNode getLocus() { return locus; }
}
