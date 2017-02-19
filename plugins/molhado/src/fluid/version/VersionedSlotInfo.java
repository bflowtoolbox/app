/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.HashedSlots;
import fluid.ir.IRNode;
import fluid.ir.IRType;
import fluid.ir.InfoStoredSlotInfo;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotImmutableException;
import fluid.ir.SlotInfoSlot;
import fluid.ir.SlotUndefinedException;
import fluid.ir.SlotUnknownException;

/** A specialization of InfoStoredSlotInfo for versioned slots.
 * This class takes into account some of the peculiarities of
 * versioned slots: in particular the way
 * they handle {@link #getSlot} when the node is not
 * new.
 */
public class VersionedSlotInfo extends InfoStoredSlotInfo {
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR.persist");

  public VersionedSlotInfo() {
    super(VersionedSlotFactory.dependent, new HashedSlots());
  }
  public VersionedSlotInfo(Object defaultValue) {
    super(VersionedSlotFactory.dependent, defaultValue, new HashedSlots());
  }
  public VersionedSlotInfo(String name, IRType type)
    throws SlotAlreadyRegisteredException {
    super(name, type, VersionedSlotFactory.dependent, new HashedSlots());
  }
  public VersionedSlotInfo(String name, IRType type, Object defaultValue)
    throws SlotAlreadyRegisteredException {
    super(
      name,
      type,
      VersionedSlotFactory.dependent,
      defaultValue,
      new HashedSlots());
  }

  /** Return the versioned structure associated with this
   * node if any is available.
   */
  VersionedStructure getVS(IRNode node) {
    return VersionedStructureFactory.get(node, this);
  }

  /** Check that the versioned structure for the slot with this
   * node is properly defined at this version.
   * @return true if it is properly defined.
   */
  boolean checkVS(IRNode node) {
    VersionedStructure vs = getVS(node);
    return (vs == null || vs.isDefined(Version.getVersionLocal()));
  }

  /** Inform the versioned structure for the slot for this node
   * that a change has just taken place (in the current version).
   */
  void informVS(IRNode node) {
    VersionedStructure vs = getVS(node);
    if (vs != null)
      vs.noteChange(Version.getVersionLocal());
    else
      LOG.warn("Problem: no VS to inform!");
  }

  /** Return the value for this versioned slot of a node.
   * It checks that the version is available by checking
   * the versioned structure.
   * @exception SlotUndefinedException
   * If the slot is not initialized with a value.
   * @exception SlotUnknownException
   * The slot's value may not yet be loaded.
   */
  public Object getSlotValue(IRNode node)
    throws SlotUndefinedException, SlotUnknownException {
    while (!checkVS(node)) {
      Version v = Version.getVersionLocal();
      new SlotUnknownVersionException(
        "slot value not defined for " + v,
        new SlotInfoSlot(this, node),
        v)
        .handle();
    }
    try {
      Object val = super.getSlotValue(node);
      return val;
    } catch (SlotUndefinedException e) {
      LOG.error("Slot undefined on node "+node, e);
      throw e;
    }
  }

  public boolean valueExists(IRNode node) {
    return checkVS(node) && super.valueExists(node);
  }

  /** Set the value for this versioned slot for the node.
   * It informs the versioned structure it is in that
   * a change has happened here.
   */
  public void setSlotValue(IRNode node, Object newValue) {
    if (!checkVS(node)) {
      Version v = Version.getVersionLocal();
      throw new SlotImmutableException("slot value not defined for " + v);
    }
    super.setSlotValue(node, newValue);
    if (newValue instanceof VersionedSequence) {
      // hack until we get VS stuff into IR
      VersionedStructure vs = getVS(node);
      if (vs != null)
         ((VersionedSequence) newValue).setVS(vs);
    }
    informVS(node);
  }
}
