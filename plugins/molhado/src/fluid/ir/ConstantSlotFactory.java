/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/ConstantSlotFactory.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.util.Enumeration;

/** The family of constant (immutable) slots. */
public class ConstantSlotFactory implements SlotFactory {
  protected ConstantSlotFactory() {}
  public static final ConstantSlotFactory prototype = 
       new ConstantSlotFactory();
  static {
    IRPersistent.registerSlotFactory(prototype,'C');
  }

  public Slot undefinedSlot() {
    return UndefinedConstantSlot.prototype;
  }
  public Slot predefinedSlot(Object value) {
    return new ConstantSlot(value);
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
    return new ConstantSlotInfo();
  }
  public static SlotInfo makeSlotInfo(String name, IRType type) 
       throws SlotAlreadyRegisteredException
  {
    return new ConstantSlotInfo(name,type);
  }
  public static SlotInfo makeSlotInfo(Object defaultValue) {
    return new ConstantSlotInfo(defaultValue);
  }
  public static SlotInfo makeSlotInfo(String name, IRType type, Object defaultValue)
       throws SlotAlreadyRegisteredException
  {
    return new ConstantSlotInfo(name,type,defaultValue);
  }

  public SlotFactory getOldFactory() {
    return OldConstantSlotFactory.prototype;
  }

  public static void ensureLoaded() {}
}

class OldConstantSlotFactory extends ConstantSlotFactory {
  public static SlotFactory prototype = new OldConstantSlotFactory();
  public Slot undefinedSlot() {
    return UndefinableConstantSlot.prototype;
  }
}

/** A constant slot created in another JVM: can only be set using
 * persistence, never by "setSlotValue".
 */
class UndefinableConstantSlot extends UndefinedSlot {
  public static final UndefinedSlot prototype = new UndefinableConstantSlot();

  public Object getValue() throws SlotUnknownException {
    throw new SlotUnknownException("Slot value not loaded.",this);
  }

  public Slot setValue(Object newValue) throws SlotImmutableException {
    throw new SlotImmutableException("Slot cannot be defined");
  }

  public Slot readValue(IRType ty, IRInput in) 
     throws IOException
  {
    return new ConstantSlot(ty.readValue(in));
  }
}
