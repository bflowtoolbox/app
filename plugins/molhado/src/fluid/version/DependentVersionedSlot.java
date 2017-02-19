/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/DependentVersionedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.PrintStream;

import fluid.ir.SlotImmutableException;
import fluid.ir.SlotUndefinedException;
import fluid.ir.SlotUnknownException;

/** A dependent versioned slot is a slot that relies on
 * external context to ensure the versioned structure has been consulted for
 * the operation.  It assumes that it has all information
 * about a slot. It serves as an abstract class with
 * a few predefined operations, in particular it includes
 * an initial value.
 */
abstract class DependentVersionedSlot extends VersionedSlot {
  protected /*final*/
  Object initialValue = undefinedValue;

  /** Create a versioned slot with no initial value */
  public DependentVersionedSlot() {
    super();
  }

  /** Create a versioned slot with an initial value */
  public DependentVersionedSlot(Object initial) {
    super();
    initialValue = initial;
  }

  public void describe(PrintStream out) {
    super.describe(out);
    if (initialValue == undefinedValue) {
      out.println("  initially undefined");
    } else {
      out.println("  initial: " + initialValue);
    }
  }

  /** The number of version-value pairs in this versioned slot.
   * Used primarily for debugging and statistics.
   */
  public int size() {
    return 0;
  }

  /** Return the value of this slot at a particular version.
   * @throws SlotUndefinedException if the slot does not have
   * a value.
   */
  public Object getValue(Version v)
    throws SlotUndefinedException, SlotUnknownException {
    if (initialValue == undefinedValue)
      throw new SlotUndefinedException("no initial value for versioned slot");
    return initialValue;
  }

  protected VersionedSlot setValue(Version v, Object val) {
    if (v == Version.getInitialVersion()) {
      initialValue = val;
      return this;
    } else {
      throw new SlotImmutableException();
    }
  }

  /** Does the slot have a value at the given version?
   */
  public boolean isValid(Version v) {
    return initialValue != undefinedValue;
  }
}
