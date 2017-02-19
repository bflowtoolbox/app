/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/ManyAssignedVersionedSlot.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import fluid.ir.IROutput;
import fluid.ir.IRType;
import fluid.ir.SlotUndefinedException;

/**
 * A many assigned versioned slot is one with multiple values at
 * multiple versions.  It handles the full generality of versioned slots
 * unlike special cases {@link UnassignedVersionedSlot} and
 * {@link OnceAssignedVersionedSlot}.
 */
class ManyAssignedVersionedSlot extends DependentVersionedSlot {
  //! For now, we use an inefficient mechanism.
  private Vector valuesLog = new Vector();
  private Vector versionsLog = new Vector();

  /** Create a versioned slot with no values assigned (yet). */
  public ManyAssignedVersionedSlot() {
    super();
  }

  /** Create a versioned slot with a single version/value pair. */
  public ManyAssignedVersionedSlot(
    Object initialValue,
    Version v,
    Object value) {
    super(initialValue);
    setValue(v, value);
  }

  /** Create a versioned slot from an existing one.
   * Not public because this is only legal when the old one is no longer used.
   */
  ManyAssignedVersionedSlot(Vector versions, Vector values) {
    super();
    versionsLog = versions;
    valuesLog = values;
  }

  public synchronized void describe(PrintStream out) {
    super.describe(out);
    int length = versionsLog.size();
    for (int i = 0; i < length; i++) {
      System.out.println(
        "  " + versionsLog.elementAt(i) + ": " + valuesLog.elementAt(i));
    }
  }

  public int size() {
    return versionsLog.size();
  }

  // cache the last effects of get
  private Version lastGetVersion = null;
  private Object lastGetValue = null;

  // could return undefinedValue
  private Object getValueLocal(Version v) {
    if (v == lastGetVersion)
      return lastGetValue;

    int min = -1;
    int max = versionsLog.size() - 1;
    int index;

    Object value;
    findValue : {
      while (min < max) {
        index = (min + max + 1) / 2;
        Version v2 = (Version) versionsLog.elementAt(index);
        if (v.precedes(v2)) {
          max = index - 1;
        } else {
          if (v.equals(v2)) {
            value = valuesLog.elementAt(index);
            break findValue;
          }
          min = index;
        }
      }
      index = max;

      if (index < 0) {
        value = initialValue;
      } else {
        value = valuesLog.elementAt(index);
      }
    }
    lastGetVersion = v;
    lastGetValue = value;

    return value;
  }

  public boolean isValid(Version v) {
    //! need something better than this:
    return (getValueLocal(v) != undefinedValue);
  }

  /** We overwrite the value at this version
   * to be the value given.  This algorithm
   * works even when the version already has children,
   * as can happen when reading in version-value pairs
   * from a file.  <p>
   * "Redundant" version-value pairs are not safe to ignore
   * since we may load an intermediate delta or snapshot.
   * As a result, every snapshot requires a lot of space in-core
   * as well as in the persistent file.
   */
  protected synchronized VersionedSlot setValue(Version v, Object newValue) {
    int min = 0;
    int max = versionsLog.size();
    int index;

    if (lastGetVersion == v)
      lastGetValue = newValue;

    while (min < max) {
      index = (min + max) / 2;
      Version v2 = (Version) versionsLog.elementAt(index);
      if (v.precedes(v2)) {
        max = index;
      } else {
        if (v2.equals(v)) {
          valuesLog.setElementAt(newValue, index);
          return this;
        }
        min = index + 1;
      }
    }
    index = min;

    /*
     * Now do a little check (for debugging purposes)
     * as to whether this change is really necessary.
     */
    if (verboseDebug) {
      Object former_value =
        index > 0 ? valuesLog.elementAt(index - 1) : initialValue;
      if (newValue == former_value
        || (newValue != null && newValue.equals(former_value))) {
        System.out.print(
          "Inserting redundant " + v + " : " + newValue + " into ");
        describe(System.out);
      }
    }

    Version nextInOrder = v.getNextInPreorderNoKids();
    Version nextInLog;
    if (index >= versionsLog.size()) {
      nextInLog = null;
    } else {
      nextInLog = (Version) versionsLog.elementAt(index);
    }

    /** We need to insert another value if the next
     * version after v in the order is not already
     * in the log.  This is accomplished by checking the following
     * version in the log.  If this is the later version (in the order)
     * or if it is a descendant of the current version (in which case
     * we assume the check has already been carried out), then
     * no entry need be inserted.
     */
    if (nextInOrder != null
      && !nextInOrder.equals(nextInLog)
      && (nextInLog == null || !nextInLog.comesFrom(v))) {
      Object former_value =
        index > 0 ? valuesLog.elementAt(index - 1) : initialValue;
      valuesLog.insertElementAt(former_value, index);
      versionsLog.insertElementAt(nextInOrder, index);
    }

    valuesLog.insertElementAt(newValue, index);
    versionsLog.insertElementAt(v, index);

    return this;
  }

  /** Return the version of this slot for a particular version.
   * This code assumes that it is protected against accessing data of
   * unloaded information.
   * @throws SlotUndefinedException if slot
   * explicitly undefined at this version.
   * @see IndependentVersionedSlot
   */
  public synchronized Object getValue(Version v) {
    Object value = getValueLocal(v);

    if (value == undefinedValue)
      throw new SlotUndefinedException("undefined for " + v);

    return value;
  }

  public Version getLatestChange(Version v) {
    int min = -1;
    int max = versionsLog.size() - 1;
    int index;

    findVersion : {
      while (min < max) {
        index = (min + max + 1) / 2;
        Version v2 = (Version) versionsLog.elementAt(index);
        if (v.precedes(v2)) {
          max = index - 1;
        } else {
          if (v.equals(v2)) {
            break findVersion;
          }
          min = index;
        }
      }
      index = max;
    }

    if (index < 0)
      return Version.getInitialVersion();

    Object currentValue = valuesLog.elementAt(index);
    Version possibleChange;
    Object value;

    do {
      possibleChange = (Version) versionsLog.elementAt(index);
      v = possibleChange.parent();

      findParent : {
        min = -1;
        max = index; // can't come after child
        while (min < max) {
          index = (min + max + 1) / 2;
          Version v2 = (Version) versionsLog.elementAt(index);
          if (v.precedes(v2)) {
            max = index - 1;
          } else {
            if (v.equals(v2)) {
              break findParent;
            }
            min = index;
          }
        }
        index = max;
      }

      if (index < 0) {
        value = initialValue;
        break;
      } else {
        value = valuesLog.elementAt(index);
      }
    }
    while (currentValue == value);

    if (index < 0 && currentValue == value)
      return Version.getInitialVersion();
    return possibleChange;
  }

  /* persistence */

  /** Return true if this version slot has a recorded change
   * for the era specified.
   * <strong>Warning</strong>: this does not test whether the versioned
   * slot was changed in the era.
   */
  public synchronized boolean isChanged(Era era) {
    //!! simple for now
    //!! use a more complicate binary search if this turns out to be costly.
    for (int i = 0; i < versionsLog.size(); ++i) {
      if (isDelta(era, i))
        return true;
    }
    return false;
  }

  /** Compute whether the entry in the log at
   * position i reflects an assignment for this era.
   * The version must be in the era,
   * and the value assigned must not simply be a duplicate of the value
   * for a parent.
   */
  private synchronized boolean isDelta(Era era, int i) {
    Version v = (Version) versionsLog.elementAt(i);
    if (!era.contains(v))
      return false;
    Object value = valuesLog.elementAt(i);
    // an undefined value never reflects 
    if (value == undefinedValue)
      return false;
    Object oldValue;
    try {
      oldValue = getValue(v.parent());
    } catch (SlotUndefinedException e) {
      return true;
    }
    /* now compare old and new values:
     * we don't bother using equals because
     * the only time a duplicate is put in place
     * by the system it just uses the same pointer.
     */
    return value != oldValue;
  }

  /* Write the values for this slot out for the given era.
   * <p>
   * <strong>Warning:</strong>
   * This method will write incorrect values
   * if called without ensuring it has all the information
   * loaded in.
   * <p>
   * This method changes the current version.
   */
  public synchronized void writeValues(IRType ty, IROutput out, Era era)
    throws IOException {
    //!! Simple for now.
    //!! If too slow, use a binary search
    for (int i = 0; i < versionsLog.size(); ++i) {
      if (isDelta(era, i)) {
        Version v = (Version) versionsLog.elementAt(i);
        Object val = valuesLog.elementAt(i);
        writeVersionValue(ty, v, val, out);
      }
    }
  }
}
