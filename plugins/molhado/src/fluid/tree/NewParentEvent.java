/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/NewParentEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class NewParentEvent extends ParentEvent {
  public NewParentEvent(DigraphInterface d, IRNode c, IRLocation l, IRNode p) {
    super(d,c,l,p);
  }
}
