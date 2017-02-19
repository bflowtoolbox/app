/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedUnitSlotInfo.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import fluid.ir.DefaultSlotStorage;
import fluid.ir.HashedSlots;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.IRType;
import fluid.ir.IRUnitType;
import fluid.ir.InfoStoredSlotInfo;
import fluid.ir.Slot;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.ir.SlotInfoSlot;

/** An attribute that contains no information, except changes.
 * It keeps track of times the attribute is given a new value,
 * and it has a method to determine if two versions differ
 * for some node.
 */
public class VersionedUnitSlotInfo extends InfoStoredSlotInfo 
  implements Observer {
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("IR.persist");

  /** Register a new versioned unit slot descriptor.
   * @param name Name under which to register this description
   * @precondition nonNull(name);
   */
  public VersionedUnitSlotInfo(String name)
    throws SlotAlreadyRegisteredException {
    super(
      name,
      IRUnitType.prototype,
      VersionedUnitSlotFactory.prototype,
      null,
      new HashedSlots());
  }

  /** Create a new anonymous versioned unit slot descriptor. */
  public VersionedUnitSlotInfo() {
    super(VersionedUnitSlotFactory.prototype, null, new HashedSlots());
  }

  /** Perform a set slot if necessary and return true if performed.
   */
  public boolean setChanged(IRNode node) {
    Version v = Version.getVersionLocal();
    // Version.bumpVersion();
    if (changed(node, v, v.parent())) {
      return false;
    } else {
      super.setSlotValue(node, null);
      informVS(node);
      return true;
    }
  }

  /** Return the versioned structure associated with this
   * node if any is available.
   */
  VersionedStructure getVS(IRNode node) {
    return VersionedStructureFactory.get(node, this);
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

  // checking the VS is tricky:
  // Snapshots are useless, because they don't save change information.
  // (Perhaps this should change?)
  //
  // To be valid, we need every era along the path from
  // one version to the other, except for the lca to be
  // defined.
  // (to be continued)

  /** Ensure that the versioned structure in this era is defined. */
  void checkVS(VersionedStructure vs, Era e, IRNode node) {
    // System.out.println("Checking " + e);
    while (!vs.isDefined(e)) {
      new SlotUnknownEraException(
        "intervening diffs not loaded",
        new SlotInfoSlot(this, node),
        e)
        .handle();
    }
  }

  // (continued from above)
  // Starting with one of the two versions, we work backwards to
  // the lca.  This is complicated by the fact that we do a
  // whole era at a time.
  //
  // We use the following facts:
  // versions except alpha with eras always have parent versions with eras.
  // If the limit is not assigned to an era, everything is local (OK).
  // Versions without eras can be ignored (local).

  /** Ensure that the versioned structure in eras for all versions between
   * v and limit (inclusive v, exclusive of limit).
   */
  void checkVS(VersionedStructure vs, Version v, Version limit, IRNode node) {
    Era elimit = limit.getEra();
    if (elimit == null)
      return;
    Era e = null;
    while ((e = v.getEra()) == null && v != limit)
      v = v.parent();
    while (e != elimit) {
      checkVS(vs, e, node);
      v = e.getRoot();
      e = v.getEra();
    }
    // if not back to limit, we need to check VS at this era too.
    if (v != limit)
      checkVS(vs, e, node);
  }

  /** Return true if a change has been noted for the node
   * between the current version and the given version.
   */
  public boolean changed(IRNode node, Version other) {
    return changed(node, other, Version.getVersionLocal());
  }
  /** Return true if a change has been noted for the node
   * between two versions.
   */
  public boolean changed(IRNode node, Version v1, Version v2) {
    if (v1.equals(v2))
      return false;
    VersionedStructure vs = getVS(node);
    if (vs != null) {
      Version v0 = Version.latestCommonAncestor(v1, v2);
      checkVS(vs, v1, v0, node);
      checkVS(vs, v2, v0, node);
    }
    VersionedUnitSlot slot = (VersionedUnitSlot) super.getSlot(node);
    if (slot == null)
      return false;
    return slot.changed(v1, v2);
  }
  
  /** Inform a change at a particular IRNode.
   * @param node a tree node
   */
  public void update(Observable obs, Object node) {
    setChanged((IRNode)node);
  }
}

class VersionedUnitSlotFactory extends VersionedSlotFactory {
  private VersionedUnitSlotFactory() {
    super();
  }
  public static VersionedUnitSlotFactory prototype =
    new VersionedUnitSlotFactory();
  static {
    IRPersistent.registerSlotFactory(prototype, 'U');
  }

  public Slot predefinedSlot(Object value) {
    return NoVersions.prototype;
  }

  public SlotInfo newAttribute() {
    return makeSlotInfo();
  }
  public SlotInfo newAttribute(String name, IRType type)
    throws SlotAlreadyRegisteredException {
    return makeSlotInfo(name, type);
  }
  public SlotInfo newAttribute(Object defaultValue) {
    return makeSlotInfo(defaultValue);
  }
  public SlotInfo newAttribute(String name, IRType type, Object defaultValue)
    throws SlotAlreadyRegisteredException {
    return makeSlotInfo(name, type, defaultValue);
  }

  public static SlotInfo makeSlotInfo() {
    return new InfoStoredSlotInfo(prototype, new HashedSlots());
  }
  public static SlotInfo makeSlotInfo(String name, IRType type)
    throws SlotAlreadyRegisteredException {
    return new InfoStoredSlotInfo(name, type, prototype, new HashedSlots());
  }
  public static SlotInfo makeSlotInfo(Object defaultValue) {
    return new InfoStoredSlotInfo(prototype, defaultValue, new HashedSlots());
  }
  public static SlotInfo makeSlotInfo(
    String name,
    IRType type,
    Object defaultValue)
    throws SlotAlreadyRegisteredException {
    return new InfoStoredSlotInfo(
      name,
      type,
      prototype,
      defaultValue,
      new HashedSlots());
  }
}

abstract class VersionedUnitSlot extends DefaultSlotStorage {
  public Object getValue() {
    return null;
  }
  public boolean isValid() {
    return true;
  }

  public abstract boolean changed(Version v1, Version v2);
}

class NoVersions extends VersionedUnitSlot {
  public static NoVersions prototype = new NoVersions();

  public Slot setValue(Object newValue) {
    return new OneVersion(Version.getVersionLocal());
  }

  public boolean changed(Version v1, Version v2) {
    return false;
  }

  public Slot readValue(IRType ty, IRInput in) throws IOException {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      int count = in.readUnsignedShort();
      if (count == 1) {
        Version v = Version.read(in, era);
        if (in.debug())
          System.out.println("  " + v);
        return new OneVersion(v);
      } else if (count > 0) {
        Vector vlog = new Vector();
        for (int i = 0; i < count; ++i) {
          Version v = Version.read(in, era);
          if (in.debug())
            System.out.println("  " + v);
          vlog.addElement(v);
        }
        return new Versions(vlog);
      } else {
        System.out.println("!! Warning: read empty versioned unit record!");
      }
    }
    return this;
  }
}

class OneVersion extends VersionedUnitSlot {
  private Version version;

  public OneVersion(Version v) {
    version = v;
  }

  public Slot setValue(Object newValue) {
    Version current = Version.getVersionLocal();
    if (version == current)
      return this;
    return new Versions(version).setValue(newValue);
  }

  public boolean changed(Version v1, Version v2) {
    return version.isBetween(v1, v2);
  }

  public boolean isChanged() {
    Era era = VersionedSlot.getEra();
    if (era != null)
      return era.contains(version);
    return false;
  }

  public void writeValue(IRType ty, IROutput out) throws IOException {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      if (out.debug()) {
        System.out.println("changed: " + version);
      }
      out.writeShort(1);
      version.write(out);
    }
  }

  public Slot readValue(IRType ty, IRInput in) throws IOException {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      Versions vs = new Versions(version);
      return vs.readValue(ty, in);
    }
    return this;
  }
}

class Versions extends VersionedUnitSlot {
  private final Vector versionLog;

  public Versions(Version v) {
    versionLog = new Vector();
    versionLog.addElement(v);
  }
  /** Create a set of versions all from the same era,
   * already in sorted order.
   */
  Versions(Vector vlog) {
    versionLog = vlog;
  }

  protected final int findVersion(Version version) {
    int min = 0;
    int max = versionLog.size();
    int index;

    // look for version in log:
    while (min < max) {
      index = (min + max) / 2;
      Version v = (Version) versionLog.elementAt(index);
      if (version.equals(v))
        return index;
      if (version.precedes(v)) {
        max = index;
      } else {
        min = index + 1;
      }
    }
    return min;
  }

  public Slot setValue(Object newValue) {
    Version.getVersionLocal();
    addVersion(Version.getVersionLocal());
    return this;
  }

  void addVersion(Version current) {
    int index = findVersion(current);

    if (index < versionLog.size()
      && // I wish I didn't have to test again...
    versionLog.elementAt(
      index).equals(
        current))
      return;

    versionLog.insertElementAt(current, index);
    /* System.out.print("Log: ");
     * for (int i=0; i < versionLog.size(); ++i)
     *   System.out.print(versionLog.elementAt(i)+ " ");
     * System.out.println();
     */
  }

  public boolean changed(Version v1, Version v2) {
    Version lca = Version.latestCommonAncestor(v1, v2);
    Version end = Version.precedes(v1, v2) ? v2 : v1;

    int lcaindex = findVersion(lca);
    int endindex = findVersion(end);

    /* we have to special case the final case
     * or else turn the following for loop to test
     *   i <= endindex
     * and then first ensure endindex in range.
     */
    if (endindex >= lcaindex
      && endindex < versionLog.size()
      && !v1.equals(v2)
      && versionLog.elementAt(endindex).equals(end))
      return true;

    for (int i = lcaindex; i < endindex; ++i) {
      Version v = (Version) versionLog.elementAt(i);
      if (v.isBetween(v1, v2))
        return true;
    }

    return false;
  }

  public boolean isChanged() {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      //!! slow for now
      for (int i = 0; i < versionLog.size(); ++i) {
        Version v = (Version) versionLog.elementAt(i);
        if (era.contains(v))
          return true;
      }
    }
    return false;
  }

  public void writeValue(IRType ty, IROutput out) throws IOException {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      Vector inEra = new Vector();
      //!! slow for now
      for (int i = 0; i < versionLog.size(); ++i) {
        Version v = (Version) versionLog.elementAt(i);
        if (era.contains(v))
          inEra.addElement(v);
      }
      out.writeShort(inEra.size());
      for (int i = 0; i < inEra.size(); ++i) {
        Version v = (Version) inEra.elementAt(i);
        v.write(out);
      }
    }
  }

  public Slot readValue(IRType ty, IRInput in) throws IOException {
    Era era = VersionedSlot.getEra();
    if (era != null) {
      int count = in.readUnsignedShort();
      for (int i = 0; i < count; ++i) {
        Version v = Version.read(in, era);
        if (in.debug())
          System.out.println("  " + v);
        addVersion(v);
      }
    }
    return this;
  }
}