/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/StoredSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;

import fluid.FluidRuntimeException;

/** This abstract class partially specifies the implementation of
 * slots which are stored somewhere and for which there is a default
 * slot.  Subclasses determine where the slots are stored.
 * @typeparam Value
 * @see InfoStoredSlotInfo
 */
public abstract class StoredSlotInfo
  extends SlotInfo
  implements PersistentSlotInfo {
  private final SlotFactory slotFactory;
  private final boolean predefined;
  private final Object defaultValue;
  private final Slot defaultSlot;

  public SlotFactory getSlotFactory() {
    return slotFactory;
  }
  public boolean isPredefined() {
    return predefined;
  }
  public Object getDefaultValue() {
    return defaultValue;
  }

  /** Register a new stored slot description (default undefined)
   * @param name Name under which to register this description
   * @param sf slot factory used to create slots.
   * @precondition nonNull(name) && nonNull(defaultSlot)
   */
  public StoredSlotInfo(String name, IRType type, SlotFactory sf)
    throws SlotAlreadyRegisteredException {
    super(name, type);
    slotFactory = sf;
    predefined = false;
    defaultValue = null;
    defaultSlot = sf.undefinedSlot();
  }

  /** Register a new stored slot description
   * @param name Name under which to register this description
   * @param sf slot factory used to create slots.
   * @param val default value
   * @precondition nonNull(name) && nonNull(defaultSlot)
   */
  public StoredSlotInfo(String name, IRType type, SlotFactory sf, Object val)
    throws SlotAlreadyRegisteredException {
    super(name, type);
    slotFactory = sf;
    predefined = true;
    defaultValue = val;
    defaultSlot = sf.predefinedSlot(val);
  }

  /** Create a new anonymous slot description (default undefined)
   * @precondition nonNull(defaultSlot)
   * @param sf slot factory used to create slots.
   */
  public StoredSlotInfo(SlotFactory sf) {
    super();
    slotFactory = sf;
    predefined = false;
    defaultValue = null;
    defaultSlot = sf.undefinedSlot();
  }

  /** Create a new anonymous slot description
   * @precondition nonNull(defaultSlot)
   * @param sf slot factory used to create slots.
   * @param val default value
   */
  public StoredSlotInfo(SlotFactory sf, Object val) {
    super();
    slotFactory = sf;
    predefined = true;
    defaultValue = val;
    defaultSlot = sf.predefinedSlot(val);
  }

  /** Get a stored slot.
   * @param node the node for which to fetch the slot
   * @return the slot for the node, or null if none exists.
   * <dl purpose=fluid>
   *   <dt>type<dd> Slot[Value]
   * </dl>
   */
  protected abstract Slot getSlot(IRNode node);

  /** Store a slot.
   * @param node the node for which to store the slot
   * @param slot the slot for the node
   * <dl purpose=fluid>
   *   <dt>type<dd> Slot[Value]
   * </dl>
   */
  protected abstract void setSlot(IRNode node, Slot slot);

  /** Get the value of the stored slot (or of the default
   * slot if none is explicitly stored.)
   * @exception SlotUndefinedException
   * If the slot is not initialized with a value.
   */
  public Object getSlotValue(IRNode node) throws SlotUndefinedException {
    Slot slot = getSlot(node);
    if (slot == null)
      slot = defaultSlot;
    return slot.getValue();
  }

  public boolean valueExists(IRNode node) {
    Slot slot = getSlot(node);
    if (slot == null)
      slot = defaultSlot; // same as above
    return slot.isValid(); // FIX hack
  }

  public boolean valueChanged(IRNode node) {
    Slot slot = getSlot(node);
    if (slot == null)
      return false;
    return slot.isChanged();
  }

  /** Set the value in the stored slot (possible storing a slot
   * in the process).
   * @exception SlotImmutableException
   * If the slot is not mutable.  The slot may be constant.
   * Or its value may be derived from other slots.
   */
  public void setSlotValue(IRNode node, Object newValue)
    throws SlotImmutableException {
    if (type() != null && !type().isValid(newValue)) {
      throw new FluidRuntimeException(
        "setSlotValue: " + newValue + " not of type " + type());
    }
    Slot slot = getSlot(node);
    if (slot == null)
      slot = defaultSlot;
    Object oldValue = null;
    boolean defined = false;
    if (hasListeners()) {
      try {
        oldValue = slot.getValue();
        defined = true;
      } catch (SlotUndefinedException e) {
      }
    }
    Slot newSlot = slot.setValue(newValue);
    if (slot != newSlot)
      setSlot(node, newSlot);
    if (hasListeners()) {
      SlotInfoEvent e =
        defined
          ? (SlotInfoEvent) new SlotInfoChangedEvent(this,
            node,
            oldValue,
            newValue)
          : (SlotInfoEvent) new SlotInfoDefinedEvent(this, node, newValue);
      informListeners(e);
    }
    if (defined)
      notifyIRObservers(node);
    else
      notifyDefineObservers(node);
  }

  /* persistence methods */

  public void writeSlot(IRNode node, IROutput out) throws IOException {
    Slot slot = getSlot(node);
    if (slot != null && slot.isValid()) {
      if (out.debug()) {
        IRRegion r = IRRegion.getOwner(node);
        System.out.println(
          "Writing " + name() + " for " + r + " #" + r.getIndex(node));
      }
      out.debugBegin("pair");
      out.writeNode(node);
      slot.writeValue(type(), out);
      out.debugEnd("pair");
    }
  }

  public void writeChangedSlot(IRNode node, IROutput out) throws IOException {
    Slot slot = getSlot(node);
    if (slot == null)
      return;
    boolean changed = slot.isChanged();
    boolean isCompound = type() instanceof IRCompoundType;
    IRCompound comp =
      (isCompound && slot.isValid()) ? (IRCompound) slot.getValue() : null;
    boolean changedContents = comp != null && comp.isChanged();
    if (!changed && !changedContents)
      return; // nothing to do
    out.debugBegin("change");
    out.writeNode(node);
    byte b = '0';
    if (changed)
      b += 1;
    if (changedContents)
      b += 2;
    if (isCompound) {
      out.debugBegin("changeKind");
      out.writeByte(b); // otherwise, assume just changed
      out.debugEnd("changeKind");
    }
    if (changedContents) {
      if (out.debug()) {
        IRRegion r = IRRegion.getOwner(node);
        System.out.println(
          "Writing changed contents of "
            + name()
            + " for "
            + r
            + " #"
            + r.getIndex(node));
      }
      out.debugBegin("changedContents");
      comp.writeChangedContents((IRCompoundType) type(), out);
      out.debugEnd("changedContents");
    }
    if (changed) {
      if (out.debug()) {
        IRRegion r = IRRegion.getOwner(node);
        System.out.println(
          "Writing changed " + name() + " for " + r + " #" + r.getIndex(node));
      }
      slot.writeValue(type(), out);
    }
    out.debugEnd("change");
  }

  public void readSlotValue(IRNode node, IRInput in) throws IOException {
    if (in.debug()) {
      IRRegion r = IRRegion.getOwner(node);
      System.out.println(
        "Reading " + name() + " for " + r + " #" + r.getIndex(node));
    }
    Slot slot = getSlot(node);
    if (slot == null)
      slot = defaultSlot;
    Slot newSlot = slot.readValue(type(), in);
    if (slot != newSlot)
      setSlot(node, newSlot);
    /*?? No event? */
  }

  public void readChangedSlotValue(IRNode node, IRInput in)
    throws IOException {
    if (in.getRevision() < 4) {
      readSlotValue(node, in);
      return;
    }
    boolean isCompound = type() instanceof IRCompoundType;
    boolean changed = false;
    boolean changedContents = false;
    if (isCompound) {
      byte b;
      in.debugBegin("changeKind");
      b = in.readByte();
      if ((b & 01) != 0)
        changed = true;
      if ((b & 02) != 0)
        changedContents = true;
      in.debugEnd("changeKind");
    } else {
      changed = true;
    }
    if (changedContents) {
      Slot slot = getSlot(node);
      Object obj;
      if (slot == null
        || !slot.isValid()
        || !((obj = slot.getValue()) instanceof IRCompound)) {
        throw new SlotUndefinedException("Cannot read changed contents");
      }
      if (in.debug()) {
        IRRegion r = IRRegion.getOwner(node);
        System.out.println(
          "Reading changed contents of "
            + name()
            + " for "
            + r
            + " #"
            + r.getIndex(node));
      }
      in.debugBegin("changedContents");
      ((IRCompound) obj).readChangedContents((IRCompoundType) type(), in);
      in.debugEnd("changedContents");
    }
    if (changed) {
      readSlotValue(node, in);
    }
  }

  /** Print debugging information for this node. */
  public void describeSlot(IRNode n, PrintStream out) {
    Slot s = getSlot(n);
    if (s == null) {
      out.println("<no information for slot of " +
		  getClass().getName() + ">");
    } else {
      s.describe(out);
    }
  }
}
