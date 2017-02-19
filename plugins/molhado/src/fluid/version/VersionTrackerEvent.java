/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionTrackerEvent.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.EventObject;

public class VersionTrackerEvent
extends EventObject
{
  public static final int VERSION_CHANGED = 1;
 
  private int kind = 0;
  private Version prevVersion = null;
  private Version newVersion = null;

  public VersionTrackerEvent( final VersionTracker vt, final Version pv, final Version nv )
  {
    super( vt );
    kind = VERSION_CHANGED;
    prevVersion = pv;
    newVersion = nv;
  }

  public int getID()
  {
    return kind;
  }

  public Version getPreviousVersion()
  {
    return prevVersion;
  }

  public Version getNewVersion()
  {
    return newVersion;
  }

  public VersionTracker getSourceAsVersionTracker()
  {
    return (VersionTracker)getSource();
  }
}
