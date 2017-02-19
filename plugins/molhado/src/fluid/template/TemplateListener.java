/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/TemplateListener.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.util.EventListener;

/**
 * This interface is implemented by classes that receive the results of 
 * executing a template.
 */
public interface TemplateListener
extends EventListener
{
  /**
   * Called by a template when it is about to being running.
   */

  public void templateIsRunning( TemplateEvent r );

  /**
   * This method is called when a Template has finished executing.
   * @param r The results of the execution.
   */
  public void templateIsDone( TemplateEvent r );
}
