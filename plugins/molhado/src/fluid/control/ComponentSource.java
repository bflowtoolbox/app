/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ComponentSource.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** A control-node source with component specific meaning.
 * ComponentSource nodes are used to represent (for example)
 * method entry nodes.
 * @author John Tang Boyland
 * @see ComponentFlow
 * @see ComponentSink
 */

public class ComponentSource extends Source {
  Component comp;
  Object value;
  public ComponentSource(Component c, Object v) {
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

