// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/HistoryEvent.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import fluid.template.Template;
import fluid.template.TemplateEvent;

public abstract class HistoryEvent extends TemplateEvent.TemplateDoneEvent 
  implements TemplateHistoryEvent
{
  public HistoryEvent(Template t, boolean status, String msg) {
    super(t, status, msg);
  }
}
