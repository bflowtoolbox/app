/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/TemplateAdapter.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * This interface is implemented by classes that receive the results of 
 * executing a template.
 */
public class TemplateAdapter
implements TemplateListener
{
  public void templateIsRunning( final TemplateEvent r )
  {
  }

  public void templateIsDone( final TemplateEvent r )
  {
  }
}
