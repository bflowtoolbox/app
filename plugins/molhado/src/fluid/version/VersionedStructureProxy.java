/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedStructureProxy.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.HashSet;
import java.util.Iterator;

import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentObserver;

/** Stand in the place of a versioned structure until
 * the real one is available.
 * Sometime a versioned structure isn't around immediately,
 * and we need to have something available to use when the time comes.
 * This abstract class helps build such proxies.
 * One needs to write a method {@link #computeReplacement()}
 * and arrange for this proxy to be informed when versioned structure
 * may be ready to compute.
 */
public abstract class VersionedStructureProxy
  implements VersionedStructure, IRPersistentObserver {
  protected VersionedStructure replacement = null;
  private HashSet changes = new HashSet();

  /** Figure what versioned structure should replace this one.
   * This will be called with the lock set on this object.
   * @return null if none yet.
   */
  protected abstract VersionedStructure computeReplacement();

  public void updatePersistent(IRPersistent ignore1, Object ignore2) {
    HashSet saved = null;
    synchronized (this) {
      if (replacement == null)
        replacement = computeReplacement();
      if (replacement != null) {
        saved = changes;
        changes = null;
      }
    }
    if (saved != null)
      for (Iterator it = saved.iterator(); it.hasNext();)
        replacement.noteChange((Version) it.next());
  }

  public VersionedStructure replace() {
    if (replacement != null)
      return replacement;
    return this;
  }

  public boolean isDefined(Version v) {
    // We only need to synchronize the unreplaced case,
    // because replacement is only set once.
    synchronized (this) {
      if (replacement == null)
        return true;
    }
    return replacement.isDefined(v);
  }
  public boolean isDefined(Era e) {
    synchronized (this) {
      if (replacement == null)
        return true;
    }
    return replacement.isDefined(e);
  }

  // cache the last note to avoid frequent sets
  private Version changeNoted = null;
  public void noteChange(Version v) {
    if (v == changeNoted)
      return;
    synchronized (this) {
      if (replacement == null) {
        changes.add(v);
        changeNoted = v;
        return;
      }
    }
    replacement.noteChange(v);
  }
}
