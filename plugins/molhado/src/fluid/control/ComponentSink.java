/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ComponentSink.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** A control-node sink with component specific meaning.
 * ComponentSink nodes are used to represent (for example)
 * method exit nodes (not returns).
 * @author John Tang Boyland
 * @see ComponentFlow
 * @see ComponentSource
 */

public class ComponentSink extends Sink {
  Component comp;
  Object value;
  public ComponentSink(Component c, Object v) {
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

