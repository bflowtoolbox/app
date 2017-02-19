/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/OutputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.ir.AbstractProxyNode;
import fluid.ir.IRNode;

/** An output port is a port with input edges coming from the interior
 * of a component.  It includes exit ports of components and
 * entry ports of subcomponents.  It is also a node
 * which is identical to the corresponding (dual) input port.
 * @see BlankOutputPort
 * @see SimpleOutputPort
 * @see DoubleOutputPort
 */
public abstract class OutputPort extends AbstractProxyNode implements Port {
  public IRNode getIRNode() {
    return getDual();
  }
  public ControlEdgeEnumeration getOutputs() {
    Port dual = getDual();
    if (dual == null) {
      return EmptyControlEdgeEnumeration.prototype;
    } else {
      return dual.getOutputs();
    }
  }
}
