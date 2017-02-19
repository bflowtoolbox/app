/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.EventObject;

/** An event that a mutable attribute of a node has received a new value.
 * @see SlotInfo
 * @see SlotInfoDefinedEvent
 * @see SlotInfoChangedEvent
 */
public abstract class SlotInfoEvent extends EventObject {
  private final SlotInfo attribute;
  private final IRNode node;
  private final Object newValue;
  
  public SlotInfoEvent(SlotInfo attr, IRNode n, Object newVal) {
    super(attr);
    attribute = attr;
    node = n;
    newValue = newVal;
  }

  public SlotInfo getSlotInfo() {
    return attribute;
  }
  public IRNode getNode() {
    return node;
  }
  public Object getNewValue() {
    return newValue;
  }
}
