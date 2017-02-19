/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/TemplateSpawnedListener.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.util.EventListener;

/**
 * This interface is implemented by classes that wish to be
 * notified whenever a template spawns a subsidiary template.
 */
public interface TemplateSpawnedListener
extends EventListener
{
  /**
   * Called when a template has spawned a subsidiary template.
   * @param r The template event
   */
  public void templateSpawned( TemplateSpawnedEvent e );
}
