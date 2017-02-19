/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/ChildEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** Class for events where children of locus are modified.
 * @see NewChildEvent
 * @see ChangedChildEvent
 * @see RemoveChildEvent
 */
public class ChildEvent extends NodeEvent {
  private final IRLocation location;
  private final IRNode childNode;
  public ChildEvent(DigraphInterface dig, IRNode parent, IRLocation loc, IRNode child) {
    super(dig,parent);
    location = loc;
    childNode = child;
  }
  public IRLocation getLocation() { return location; }
  public IRNode getParent() { return getNode(); }
  public IRNode getChild() { return childNode; }
}
