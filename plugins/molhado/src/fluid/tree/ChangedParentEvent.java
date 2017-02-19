/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChangedParentEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

public class ChangedParentEvent extends ParentEvent {
  private final IRNode oldParent;
  public ChangedParentEvent(DigraphInterface d, IRNode c, IRLocation l,
			    IRNode p0, IRNode p1) {
    super(d,c,l,p1);
    oldParent = p0;
  }
  public IRNode getOldParent() { return oldParent; }
  public IRNode getNewParent() { return getParent(); }
}
