/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/BlankOutputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class BlankOutputPort extends OutputPort implements NoInput {
  public ControlEdgeEnumeration getInputs() {
    return EmptyControlEdgeEnumeration.prototype;
  }
}
