/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/RemoveChildEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class RemoveChildEvent extends ChildEvent {
  public RemoveChildEvent(DigraphInterface d, IRNode p, IRLocation l, IRNode c) {
    super(d,p,l,c);
  }
}
