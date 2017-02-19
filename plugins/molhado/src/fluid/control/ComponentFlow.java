/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ComponentFlow.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** A control-node with Component-specific non-flow actions.
 * ComponentFlow are used to represent uses of constants and variables,
 * @author John Tang Boyland
 * @see ComponentChoice
 */

public class ComponentFlow extends Flow {
  Component comp;
  Object value;
  public ComponentFlow(Component c, Object v) {
    comp = c;
    value = v;
  }
  public Component getComponent() {
    return comp;
  }
  public Object getValue() {
    return value;
  }
  public Object getInfo() {
    return value;
  }
}

