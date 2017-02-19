/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/CachedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.FluidError;
import fluid.ir.DefaultSlotStorage;
import fluid.ir.Slot;
import fluid.ir.SlotUndefinedException;

/** A class to cache the result of some computation until the version changes.
 * Then it must be recomputed.
 * @see VersionedSlot
 */

public abstract class CachedSlot extends DefaultSlotStorage {
  protected Object value;
  protected Version version;

  public CachedSlot() {
    this(null,null);
  }
  public CachedSlot(Object value) {
    this(value,Version.getVersionLocal());
  }
  public CachedSlot(Object value, Version version) {
    this.value = value;
    this.version = version;
  }

  public Object getValue() {
    if (!isCached()) {
      if (!recomputeValue()) {
	throw new SlotUndefinedException("slot not set for version");
      }
      if (!isCached()) {
	throw new FluidError("recomputation didn't work");
      }
    }
    return value;
  }

  public Slot setValue(Object newValue) {
    value = newValue;
    version = Version.getVersionLocal();
    return this;
  }

  public final boolean isCached() {
    return version != null && version.equals(Version.getVersionLocal());
  }

  public boolean isValid() {
    return isCached() || recomputeValue();
  }

  /** Recompute this value and possible others for current version.
   * @param node IR node for which the slot is being requested.
   * @postcondition isValid()
   * @return true if successful, false if undefined
   */
  public abstract boolean recomputeValue();
}
