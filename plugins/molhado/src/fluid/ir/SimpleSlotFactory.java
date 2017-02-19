/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SimpleSlotFactory.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.Enumeration;

/** The family of simple (mutable and unversioned) slots. */
public final class SimpleSlotFactory implements SlotFactory {
  private SimpleSlotFactory() {}
  public static final SimpleSlotFactory prototype = new SimpleSlotFactory();
  static {
    IRPersistent.registerSlotFactory(prototype,'S');
  }
  public Slot undefinedSlot() {
    return UndefinedSimpleSlot.prototype;
  }
  public Slot predefinedSlot(Object value) {
    return new PredefinedSimpleSlot(value);
  }
  public IRSequence newSequence(int size) {
    if (size < 0) {
      return new IRList(this,~size);
    } else if (size == 0) {
      return EmptyIRSequence.prototype;
    } else {
      return new IRArray(size,this);
    }
  }
  public Enumeration newEnumeration(Enumeration e) {
    return e;
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
    return new SimpleSlotInfo();
  }
  public static SlotInfo makeSlotInfo(String name, IRType type) 
       throws SlotAlreadyRegisteredException
  {
    return new SimpleSlotInfo(name,type);
  }
  public static SlotInfo makeSlotInfo(Object defaultValue) {
    return new SimpleSlotInfo(defaultValue);
  }
  public static SlotInfo makeSlotInfo(String name, IRType type, Object defaultValue)
       throws SlotAlreadyRegisteredException
  {
    return new SimpleSlotInfo(name,type,defaultValue);
  }

  public SlotFactory getOldFactory() {
    return this; // no distinction since no consistency required
  }
}
