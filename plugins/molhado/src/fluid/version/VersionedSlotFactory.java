/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedSlotFactory.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Enumeration;

import fluid.ir.EmptyIRSequence;
import fluid.ir.IRArray;
import fluid.ir.IRList;
import fluid.ir.IRPersistent;
import fluid.ir.IRSequence;
import fluid.ir.IRType;
import fluid.ir.Slot;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotInfo;

/** The family of versioned (partially mutable) slots. */
public class VersionedSlotFactory implements SlotFactory {
  protected VersionedSlotFactory() {}
  public static final VersionedSlotFactory prototype = 
       new VersionedSlotFactory();
  static final SlotFactory dependent =
    new DependentVersionedSlotFactory();

  static {
    IRPersistent.registerSlotFactory(prototype,'V');
    IRPersistent.registerSlotFactory(dependent,'v');
  }

  /** Create a versioned slot with no default
   * that resides within the currently defined versioned structure.
   * If there is no currently defined versioned structure,
   * `null' will be used which may cause problems for persistence.
   * @see VersionedStructureFactory#getVS
   */
  public Slot undefinedSlot() {
    return undefinedSlot(VersionedStructureFactory.getVS());
  }

  /** Create a versioned slot with given default
   * that resides within the currently defined versioned structure.
   * If there is no currently defined versioned structure,
   * `null' will be used which may cause problems for persistence.
   * @see VersionedStructureFactory#getVS
   */
  public Slot predefinedSlot(Object value) {
    return predefinedSlot(value,VersionedStructureFactory.getVS());
  }

  /** Create a versioned slot with no default
   * that resides in the given versioned structure.
   */
  public Slot undefinedSlot(VersionedStructure vs) {
    return new IndependentVersionedSlot(vs);
  }

  /** Create a versioned slot with given default value
   * that resides in the given versioned structure.
   */
  public Slot predefinedSlot(Object def, VersionedStructure vs) {
    return new IndependentVersionedSlot(def, vs);
  }

  public IRSequence newSequence(int size) {
    if (size < 0) {
      return new VersionedSequence(new IRList(dependent,~size));
    } else if (size == 0) {
      return EmptyIRSequence.prototype;
    } else {
      return new VersionedSequence(new IRArray(size,dependent));
    }
  }
  public Enumeration newEnumeration(Enumeration e) {
    if (e instanceof VersionedEnumeration) return e;
    return new VersionedEnumeration(e);
  }

  public SlotInfo newAttribute() {
    return makeSlotInfo();
  }
  public SlotInfo newAttribute(String name, IRType type) 
       throws SlotAlreadyRegisteredException
  {
    return makeSlotInfo(name,type);
  }
  public SlotInfo newAttribute(Object defaultValue) {
    return makeSlotInfo(defaultValue);
  }
  public SlotInfo newAttribute(String name, IRType type, Object defaultValue)  
       throws SlotAlreadyRegisteredException
  {
    return makeSlotInfo(name,type,defaultValue);
  }

  public static SlotInfo makeSlotInfo() {
    return new VersionedSlotInfo();
  }
  public static SlotInfo makeSlotInfo(String name, IRType type) 
       throws SlotAlreadyRegisteredException
  {
    return new VersionedSlotInfo(name,type);
  }
  public static SlotInfo makeSlotInfo(Object defaultValue) {
    return new VersionedSlotInfo(defaultValue);
  }
  public static SlotInfo makeSlotInfo(String name, IRType type, Object defaultValue) 
       throws SlotAlreadyRegisteredException
  {
    return new VersionedSlotInfo(name,type,defaultValue);
  }

  public SlotFactory getOldFactory() {
    return this;
  }
}

/** A slotfactory for structures that are already protected by
 * versioned structures.
 */
class DependentVersionedSlotFactory extends VersionedSlotFactory {
  
  public Slot undefinedSlot() {
    return new UnassignedVersionedSlot();
  }
  public Slot predefinedSlot(Object value) {
    return new UnassignedVersionedSlot(value);
  }

}
