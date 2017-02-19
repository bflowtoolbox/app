/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotFactory.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.Enumeration;

/** An interface to a family of slots.
 * This interface can be used to parameterize complex objects.
 * @see SimpleSlotFactory
 * @see ConstantSlotFactory
 * @see fluid.version.VersionedSlotFactory
 */
public interface SlotFactory {
  /** Return a (possibly shared) undefined slot of the family. */
  public Slot undefinedSlot();
  /** Return a (possibly shared) predefined slot of the family. */
  public Slot predefinedSlot(Object value);
  /** Return a new sequence of the specific size.
   * Three possibilities exist: <dl>
   * <dt>size &lt; 0<dd> a list is returned initialized to have
   *                     ~size elements (-1 means none).
   * <dt>size   =  0<dd> an empty sequence is returned.
   * <dt>size &gt; 0<dd> a fixed size array is returned.</dl>
   */
  public IRSequence newSequence(int size);
  /** Convert an enumeration to be "stable" in some sense.
   * Unless versions are involved, it is an NOP.
   */
  public Enumeration newEnumeration(Enumeration e);

  /** Create a new anonymous attribute. */
  public SlotInfo newAttribute();
  /** Create a new named (possibly persistent) attribute. 
   * @throws SlotAlreadyRegisteredException
   * 	if a slot with this name already exists.
   * @precondition nonNull(name)
   */
  public SlotInfo newAttribute(String name, IRType type) 
       throws SlotAlreadyRegisteredException;
  /** Create a new anonymous attribute with a default value. */
  public SlotInfo newAttribute(Object defaultValue);
  /** Create a new named (possibly persistent) attribute wiith
   * a default value.
   * @throws SlotAlreadyRegisteredException
   * 	if a slot with this name already exists.
   * @precondition nonNull(name)
   */
  public SlotInfo newAttribute(String name, IRType type, Object defaultValue)  
       throws SlotAlreadyRegisteredException;

  /** Return the slot factory to be used for "old" slots. */
  public SlotFactory getOldFactory();
}
