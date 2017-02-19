// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/TemplateConflictResults.java,v 1.1 2006/03/21 23:21:00 dig Exp $ 
package fluid.java.template;

import java.util.Vector;

/**
 * Tag interface for {@link fluid.template.TemplateEvent}s that 
 * indicates the event is carrying effect conflict data.
 */
public interface TemplateConflictResults
{
  /**
   * Get the conflicting effects.
   * @return A Vector of {@link ConflictResult} objects.
   */
  public Vector getConflicts();
}
