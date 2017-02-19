/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** The intermediate representation node for the FLUID project
 * prototype software development environment.  Each node
 * has an arbitrary and dynamic number of named slots. 
 * Some of these may be stored in the node, others may be
 * stored in external tables, or computed on demand. 
 * </p>
 *
 * @see SlotInfo
 * @see PlainIRNode
 */
public interface IRNode {
  /** The "identity" of the node.  For plain IRNode's,
   * the identity is itself.  Used for equality testing.
   */
  public Object identity();

  /** Get the slot's value for a particular node.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @precondition nonNull(si)
   * @return the value of the slot associated with this node.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @exception SlotUndefinedException
   * If the slot is not initialized with a value.
   */
  public Object getSlotValue(SlotInfo si) throws SlotUndefinedException;

  /** Change the value stored in the slot.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @param newValue value to store in the slot.
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   *   <dt>capabilities<dd> store
   * </dl>
   */
  public void setSlotValue(SlotInfo si, Object newValue)
    throws SlotImmutableException;

  // shortcuts:
  public int getIntSlotValue(SlotInfo si) throws SlotUndefinedException;
  public void setSlotValue(SlotInfo si, int newValue)
    throws SlotImmutableException;

  /** Check if a value is defined for a particular node.
   * @typeparam Value
   * @param si Description of slot to be accessed.
   * <dl purpose=fluid>
   *   <dt>type<dd> SlotInfo[Value]
   * </dl>
   * @precondition nonNull(si)
   * @return true if getSlotValue would return a value, false otherwise
   */
  public boolean valueExists(SlotInfo si);
}