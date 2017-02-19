package fluid.ir;

import java.util.Hashtable;

/** Storage of slots in a hashtable
 * @typeparam Key key (either node or slot info) for slot
 */
public class HashedSlots extends Hashtable implements Slots {
  public HashedSlots() {
    super();
  }
  public Slot getSlot(Object key) {
    return (Slot) get(key);
  }
  public Slot setSlot(Object key, Slot slot) {
    return (Slot) put(key, slot);
  }
}