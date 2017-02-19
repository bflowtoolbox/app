/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/InfoStoredSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** Slots described by instances of this class are stored outside of the node.
 * @typeparam Value
 */
public class InfoStoredSlotInfo extends StoredSlotInfo {
  /** The storage for the slots.
   * @type Slots[IRNode,Slot[Value]]
   * @structure
   */
  private Slots slots;

  /** Register a new stored slot description
   * @param name Name under which to register this description
   * @param sf slot factory used to create slots.
   * @param slots storage for created slots.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slots[IRnode,Slot[Value]]
   *   <dt>capabilities<dd> store, read, write
   * </dl>
   * @precondition nonNull(name) && nonNull(defaultSlot) && 
   *     nonNull(slots) && unique(slots)
   */
  public InfoStoredSlotInfo(
    String name,
    IRType type,
    SlotFactory sf,
    Slots slots)
    throws SlotAlreadyRegisteredException {
    super(name, type, sf);
    this.slots = slots;
  }

  /** Register a new stored slot description
   * @param name Name under which to register this description
   * @param sf slot factory used to create slots.
   * @param val default value for slots.
   * @param slots storage for created slots.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slots[IRnode,Slot[Value]]
   *   <dt>capabilities<dd> store, read, write
   * </dl>
   * @precondition nonNull(name) && nonNull(defaultSlot) && 
   *     nonNull(slots) && unique(slots)
   */
  public InfoStoredSlotInfo(
    String name,
    IRType type,
    SlotFactory sf,
    Object val,
    Slots slots)
    throws SlotAlreadyRegisteredException {
    super(name, type, sf, val);
    this.slots = slots;
  }

  /** Create a new anonymous slot description.
   * @param sf slot factory used to create slots.
   * @param slots storage for created slots.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slots[IRnode,Slot[Value]]
   *   <dt>capabilities<dd> store, read, write
   * </dl>
   * @precondition nonNull(defaultSlot) && nonNull(slots) && unique(slots)
   */
  public InfoStoredSlotInfo(SlotFactory sf, Slots slots) {
    super(sf);
    this.slots = slots;
  }

  /** Create a new anonymous slot description.
   * @param sf slot factory used to create slots.
   * @param val default value for slots.
   * @param slots storage for created slots.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slots[IRnode,Slot[Value]]
   *   <dt>capabilities<dd> store, read, write
   * </dl>
   * @precondition nonNull(defaultSlot) && nonNull(slots) && unique(slots)
   */
  public InfoStoredSlotInfo(SlotFactory sf, Object val, Slots slots) {
    super(sf, val);
    this.slots = slots;
  }

  /** Get a stored slot.
   * @param node the node for which to fetch the slot
   * @return the slot for the node, or null if none exists.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slot[Value]
   * </dl>
   * @capabilities store
   */
  protected Slot getSlot(IRNode node) {
    return slots.getSlot(node);
  }

  /** Store a slot.
   * @param node the node for which to store the slot
   * @param slot the slot for the node
   * <dl purpose=fluid>
   *   <dt>type<dd> Slot[Value]
   * </dl>
   */
  protected void setSlot(IRNode node, Slot slot) {
    slots.setSlot(node, slot);
  }
}