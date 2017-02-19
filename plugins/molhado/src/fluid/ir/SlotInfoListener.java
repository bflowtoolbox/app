/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotInfoListener.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.EventListener;

/** A class of event observers that are notified when a
 * slot for a particular attribute gets a value.
 * @see SlotInfoEvent
 */
public interface SlotInfoListener extends EventListener {
  /** Called when a slot for a particular attribute and node is set. */
  public void handleSlotInfoEvent(SlotInfoEvent e);
}
