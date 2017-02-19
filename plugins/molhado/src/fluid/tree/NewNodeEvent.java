/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/NewNodeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

public class NewNodeEvent extends NodeEvent {
  public NewNodeEvent(DigraphInterface dig, IRNode n) {
    super(dig,n);
  }
}
