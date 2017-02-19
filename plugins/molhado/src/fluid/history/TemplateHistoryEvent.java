// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/TemplateHistoryEvent.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import fluid.ir.IRNode;

/**
 * Tag interface for {@link fluid.template.TemplateEvent}s that 
 * indicates the event is carrying history data.
 */
public interface TemplateHistoryEvent
{
  // Q: where does it attach?
  public IRNode getRecord();
}
