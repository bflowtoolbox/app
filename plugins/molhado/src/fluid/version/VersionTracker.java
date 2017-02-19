/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionTracker.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Observable;
import java.util.Vector;

/** Instances with this interface track a version.
 * They may be used as a coordination point between
 * things that want to track the same version.
 * @see VersionMarker
 */
public abstract class VersionTracker extends Observable {
  private Vector listeners = new Vector();

  /** Get the current version being tracked.
   */
  public abstract Version getVersion();

  /** Set the version being tracked to this new version.
   * Notify observers of the change.
   * If the new version is constructed from the old version,
   * one should either <ol>
   * <li> lock the tracker during construction (pessimistic), or
   * <li> use {@link #moveFromVersionToCurrent} instead (optimisitic).
   * </ol>
   */
  public abstract void setVersion(Version v);

  /** Change the version from the current one
   * to the version constructed using it.
   * If the operation is not sucessful (the tracker
   * was changed since the version was initially read,
   * "false" is returned.  This method should be used
   * in a loop such as:
   * <pre>
   *   do {
   *     Version initial = tr.getVersion();
   *     Version.setVersion(initial);
   *     <em>make changes</em>
   *   } while (!tr.moveFromVersionToCurrent(initial));
   * </pre>
   */
  public synchronized boolean moveFromVersionToCurrent(Version initial) {
    if (getVersion() == initial) {
      setVersion(Version.getVersionLocal()); // or ... getVersion() ...
      return true;
    } else {
      return false;
    }
  }

  /** Return true if the current version is the same as
   * the one being tracked.
   */
  public abstract boolean isActive();

  /** Makes the current version equal to the one in the tracker.
   * @deprecated use Version.setVersion(this.getVersion())
   */
  public abstract void makeActive();

  public synchronized void addVersionTrackerListener( final VersionTrackerListener l )
  {
    listeners.add( l );
  }

  public synchronized void removeVersionTrackerListener( final VersionTrackerListener l )
  {
    listeners.remove( l );
  }

  protected void fireVersionTrackerEvent( final Version v1, final Version v2 )
  {
    final VersionTrackerEvent vte = new VersionTrackerEvent( this, v1, v2 );
    Vector l;
    synchronized( this ) {
      l = (Vector)listeners.clone();
    }
    for( int i = 0; i < l.size(); i++ )
    {
      VersionTrackerListener vtl = (VersionTrackerListener)l.elementAt( i );
      vtl.versionChanged( vte );
    }
  }
}
