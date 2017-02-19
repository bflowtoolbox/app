// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/ReorderEvent.java,v 1.1 2006/03/21 23:21:00 dig Exp $
package fluid.java.template;

import java.util.Vector;

import fluid.template.Template;
import fluid.template.TemplateEvent;

public abstract class ReorderEvent extends TemplateEvent.TemplateDoneEvent 
  implements TemplateConflictResults 
{
  public ReorderEvent(Template t, boolean status, String msg) {
    super(t, status, msg, new Vector());
  }
  public ReorderEvent(Template t, boolean status, String msg, Vector v) {
    super(t, status, msg, v);
    nodeSets = v;
  }
  public /* final */ Vector nodeSets;
}
