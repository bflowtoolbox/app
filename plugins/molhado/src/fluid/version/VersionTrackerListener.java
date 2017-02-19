/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionTrackerListener.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.EventListener;

public interface VersionTrackerListener extends EventListener
{
  public void versionChanged( VersionTrackerEvent e );
}
