/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ParentEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Class where event concerns parent of locus.
 * @see NewParentEvent
 * @see ChangedParentEvent
 * @see RemoveParentEvent
 */
public class ParentEvent extends NodeEvent {
  private final IRLocation location;
  private final IRNode parentNode;
  public ParentEvent(DigraphInterface dig, IRNode ch, IRLocation loc, IRNode parent) {
    super(dig,ch);
    location = loc;
    parentNode = parent;
  }
  public IRLocation getLocation() { return location; }
  public IRNode getChild() { return getNode(); }
  public IRNode getParent() { return parentNode; }
}
