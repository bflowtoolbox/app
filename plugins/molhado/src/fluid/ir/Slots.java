/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/Slots.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** Interface to slot storage.
 * @typeparam Key key (either node or slot info) for slot
 */
public interface Slots {
  /** Return the slot for this key (or null).
   * @param key key (node or slot info) to use to get slot.
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   */
  public Slot getSlot(Object key);

  /** Change the association for a key.
   * @param key key (node or slot info) to use to get slot.
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   * @param slot slot to be stored.
   * @return the former slot (or null) for this key
   */
  public Slot setSlot(Object key, Slot slot);

  /** Return some indication of how many things are stored. */
  public int size();

  //!! need to add enumeration too
}