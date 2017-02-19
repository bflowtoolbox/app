/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionMarker.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

/** A version tracker that does <em>not</em> move
 * except when told to using setVersion.
 * It accepts only frozen versions.
 * Essentially this class is just a container for a version.
 * @see VersionCursor
 */
public class VersionMarker extends VersionTracker {
  private Version tracking;

  public VersionMarker() {
    tracking = Version.getVersion();
  }

  public VersionMarker(Version v) {
    tracking = v;
    tracking.freeze();
  }
  
  /** Get the current version being tracked.
   */
  public synchronized Version getVersion() {
    return tracking;
  }

  /** Set the version being tracked to this new version.
   */
  public synchronized void setVersion( final Version v ) {
    v.freeze();

    final Version old = tracking;
    tracking = v;
    setChanged();
    notifyObservers(old);
    fireVersionTrackerEvent( old, v );
  }

  /** Return true if the current version is the same as
   * the one being tracked.
   */
  public synchronized boolean isActive() {
    return tracking.isCurrent();
  }

  /** Make the tracked version current.
   * @deprecated use Version.setVersion(this.getVersion())
   */
  public void makeActive() {
    Version.setVersion(tracking);
  }
}
