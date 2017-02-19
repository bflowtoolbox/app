/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedChildEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class ChangedChildEvent extends ChildEvent {
  private final IRNode oldChild;
  public ChangedChildEvent(DigraphInterface d, IRNode p, IRLocation l,
			   IRNode c0, IRNode c1) {
    super(d,p,l,c1);
    oldChild = c0;
  }
  public IRNode getOldChild() { return oldChild; }
  public IRNode getNewChild() { return getChild(); }
}
