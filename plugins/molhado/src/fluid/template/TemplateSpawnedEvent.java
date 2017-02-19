/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/TemplateSpawnedEvent.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.util.EventObject;

/**
 * An event sent when a template spawns a
 * subsidiary template that is doing work on behalf
 * of the template doing the spawning.
 */
public class TemplateSpawnedEvent
extends EventObject
{
  /**
   * The template being spawned.
   */
  private Template newTemplate;

  /**
   * Create new event.
   * @param source The template doing the spawning.
   * @param newTemplate The template being spawned.
   */
  public TemplateSpawnedEvent( final Template source, final Template newTemplate )
  {
    super( source );
    this.newTemplate = newTemplate;
  }

  /**
   * Get the event source as a Template; this is the template that is
   * doing the spawning.  Returns the same object as <CODE>getSource()</CODE>.
   * @return The source as a <CODE>Template</CODE>.
   */
  public Template getSourceAsTemplate()
  {
    return (Template)getSource();
  }

  /**
   * Get the template that has been spawned.
   * @return the spawned template.
   */
  public Template getSpawnedTemplate()
  {
    return newTemplate;
  }
}
