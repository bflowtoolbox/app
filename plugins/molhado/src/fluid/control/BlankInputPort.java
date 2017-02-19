/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/BlankInputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class BlankInputPort extends InputPort implements NoOutput {
  public ControlEdgeEnumeration getOutputs() {
    return EmptyControlEdgeEnumeration.prototype;
  }
}
