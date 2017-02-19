/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/NewEdgeEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRNode;

public class NewEdgeEvent extends EdgeEvent {
  public NewEdgeEvent(DigraphInterface dig, IRNode e) {
    super(dig,e);
  }
}
