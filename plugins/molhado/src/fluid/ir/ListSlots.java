package fluid.ir;

import fluid.util.AssocList;

/** Storage of slots in an association list.
 * @typeparam Key key (either node or slot info) for slot
 */
public class ListSlots extends AssocList implements Slots {
  public ListSlots() {
    super();
  }
  public Slot getSlot(Object key) {
    return (Slot) get(key);
  }
  public Slot setSlot(Object key, Slot slot) {
    return (Slot) put(key, slot);
  }
}