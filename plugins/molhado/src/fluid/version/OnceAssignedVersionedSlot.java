/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/OnceAssignedVersionedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.io.PrintStream;

import fluid.ir.IROutput;
import fluid.ir.IRType;

/** An versioned slot that has only been assigned once.
 * This kind of versioned slot can be more efficiently
 * handled than a full versioned slot because the latter slot
 * requires two vectors for each slot.
 * <p>
 * This slot depends on its context to ensure that it isn't requested
 * for its value for an unloaded version.
 * @see ManyAssignedVersionedSlot
 * @see IndependentVersionedSlot
 */
class OnceAssignedVersionedSlot extends DependentVersionedSlot {
  final protected Version version;
  protected Object value;

  public OnceAssignedVersionedSlot(Version v, Object newValue) {
    super();
    version = v;
    value = newValue;
  }

  public OnceAssignedVersionedSlot(Object i, Version v, Object newValue) {
    super(i);
    version = v;
    value = newValue;
  }

  /** Express this in the general form so we can then add pairs. */
  protected VersionedSlot generalize() {
    VersionedSlot g =
      new ManyAssignedVersionedSlot(initialValue, version, value);
    retire(); // only if new creation worked
    return g;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    Object val;
    synchronized (this) {
      val = value;
    }
    out.print("  " + version + ": ");
    out.println(val);
  }

  public int size() {
    return 1;
  }

  // slot methods:

  public boolean isValid(Version v) {
    if (v.comesFrom(version))
      return true;
    return super.isValid(v);
  }

  public Object getValue(Version v) {
    if (v.comesFrom(version)) {
      synchronized (this) {
        return value;
      }
    } else {
      LOG.debug("Version "+v+" not from version "+version);
      return super.getValue(v);
    }
  }

  public Version getLatestChange(Version v) {
    if (v.comesFrom(version)) {
      return version;
    } else {
      return Version.getInitialVersion();
    }
  }

  public synchronized VersionedSlot setValue(Version v, Object newValue) {
    // System.out.println(this + ".setValue(" + v + "," + newValue + ")");
    if (version.equals(v)) {
      value = newValue;
      return this;
    } else {
      return generalize().setValue(v, newValue);
    }
  }

  /* persistence */

  public boolean isChanged(Era e) {
    return e.contains(version);
  }

  /* Write the change as a version,value sequence.
   * <p>
   * This method changes the current version.
   */
  protected void writeValues(IRType ty, IROutput out, Era e)
    throws IOException {
    if (isChanged(e)) {
      writeVersionValue(ty, version, value, out);
    }
  }
}
