/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionCursor.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Observable;
import java.util.Observer;

/** A pointer to a version which tracks any changes to it.
 * If the version associated with a cursor get a new child,
 * the cursor moves to this new version.  A cursor is active
 * if its associated version is the current one, the one
 * that gets any slot changes.
 * <p> A version cursor can have observers, which are notified
 * if the tracked version changes.
 * <p> <b></b>
 * @deprecated use <tt>VersionTracker</tt> instead.
 */
public class VersionCursor extends VersionTracker {
  private Version tracking;

  /** Create a version cursor for the current version.
   * @deprecated use {@link VersionTracker} instances instead,
   * and explicitly update them when transaction is complete.
   */
  public VersionCursor() {
    this(Version.getVersionLocal());
  }

  /** Create a version cursor for the specified version
   * @deprecated use {@link VersionTracker} instances instead,
   * and explicitly update them when transaction is complete.
   */
  public VersionCursor(Version v) {
    tracking = v;
    v.addCursor(this);
  }

  public void notifyObservers() {
    setChanged();
    super.notifyObservers();
  }

  /** Tracked version gets a new child.
   * Called only from class <tt>Version</tt>
   */
  synchronized void moveCursor( final Version v ) {
    final Version old = tracking;
    tracking = v;
    notifyObservers();
    fireVersionTrackerEvent( old, v );
  }

  public synchronized Version getVersion() {
    tracking.freeze();
    return tracking;
  }

  public synchronized void setVersion( final Version v ) {
    tracking.removeCursor(this);
    final Version old = tracking;
    tracking = v;
    v.addCursor(this);
    notifyObservers();
    fireVersionTrackerEvent( old, v );
  }

  /** Make this cursor's tracked version the current version.
   * @deprecated use Version.setVersion(this.getVersion())
   */
  public void makeActive() {
    Version.setVersion(tracking);
  }

  /** Return true if the current version is being tracked by this cursor. */
  public boolean isActive() {
    return tracking.isCurrent();
  }

  /** Attach an observable to a versioned subject.
   * The observer will only be notified when
   * the cursor is active, that is if the change is in
   * the tracked version.
   * @deprecated attach yourself to a version tracker instead,
   * and then ask the subject when the version tracker updates.
   */
  public void attachObserver(Observable subject, final Observer watcher) {
    subject.addObserver(new Observer() {
      public void update(Observable s, Object arg) {
	if (isActive()) watcher.update(s,arg);
      }
    });
  }
}
