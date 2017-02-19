/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/InputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** An input port is a port with output edges leading into the interior
 * of a component.  It includes entry ports to components and
 * exit ports of subcomponents.
 * @see BlankInputPort
 * @see SimpleInputPort
 * @see DoubleInputPort
 */
public abstract class InputPort extends Entity implements Port {
  public ControlEdgeEnumeration getInputs() {
    Port dual = getDual();
    if (dual == null) {
      return EmptyControlEdgeEnumeration.prototype;
    } else {
      return dual.getInputs();
    }
  }
}
