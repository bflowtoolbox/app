/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/IndependentVersionedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.io.PrintStream;

import fluid.ir.IROutput;
import fluid.ir.IRType;
import fluid.ir.Slot;
import fluid.ir.SlotImmutableException;

/** A versioned slot that can handle being in any structure.
 * It carries around its own pointer to versioned structure
 * to be checked and notified on accesses.  It wraps
 * either an immutable slot (that returns a new (unique) slot
 * on a change) or else a unique slot (that it alone changes.)
 */
class IndependentVersionedSlot extends VersionedSlot {
  VersionedStructure container;
  final /* unique OR immutable */ VersionedSlot base;

  /** Wrap an existing versioned slot */
  public IndependentVersionedSlot(/*@ unique OR immutable */ VersionedSlot vs,
				  VersionedStructure c) {
    base = vs;
    container = c;
  }

  /** Create a new independent initially undefined versioned slot.
   */
  public IndependentVersionedSlot(VersionedStructure c) {
    base = new UnassignedVersionedSlot();
    container = c;
  }

  /** Create a new independent versioned slot.
   * @param value initial value (at alpha)
   */
  public IndependentVersionedSlot(Object value, VersionedStructure c) {
    base = new UnassignedVersionedSlot(value);
    container = c;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    base.describe(out);
  }

  public int size() {
    return base.size();
  }

  public boolean isValid(Version v) {
    VersionedStructure c;
    synchronized (this) {
      c = container;
    }
    return (c == null || c.isDefined(v)) && base.isValid(v);
  }

  /** Set the value of the slot for this version.
   * and inform the versioned structure
   * of the change.
   */
  public Slot setValue(Object newValue) {
    VersionedStructure c;
    synchronized (this) {
      c = container;
    }
    Version v = Version.getVersionLocal();
    if (c != null && !c.isDefined(v))
      throw new SlotImmutableException("slot unknown for version " + v);
    Slot result = super.setValue(newValue);
    if (c != null)
      c.noteChange(Version.getVersionLocal()); // may be a new version
    return result;
  }

  /** Change the independent slot at a particular version.
   * Must be protected because it does not inform the versioned structure,
   * and calls the protected version of setValue for the base.
   */
  protected VersionedSlot setValue(Version v, Object newValue) {
    VersionedSlot newb = base.setValue(v, newValue);
    if (base != newb) {
      VersionedSlot newvs = new IndependentVersionedSlot(newb, container);
      if (size() > 0)
        retire();
      return newvs;
    }
    return this;
  }

  public Object getValue(Version v) {
    VersionedStructure c;
    synchronized (this) {
      c = container;
    }
    while (c != null && !c.isDefined(v))
      new SlotUnknownVersionException("not loaded for this version", this, v)
        .handle();
    return base.getValue(v);
  }

  public Version getLatestChange(Version v) {
    return base.getLatestChange(v);
  }

  private void checkEra(Era e) {
    while (e != null && container != null && !container.isDefined(e))
      new SlotUnknownEraException("Slot not loaded at this time", this, e)
        .handle();
  }

  public boolean isChanged(Era e) {
    checkEra(e);
    return base.isChanged(e);
  }

  public void writeValues(IRType ty, IROutput out, Era era)
    throws IOException {
    if (container == null) {
      container = VersionedStructureFactory.getVS();
      // doesn't matter who sets it
    }
    checkEra(era);
    base.writeValues(ty, out, era);
  }
}
