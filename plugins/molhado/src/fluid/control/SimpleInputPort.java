/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/SimpleInputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class SimpleInputPort extends InputPort 
    implements OneOutput
{
  protected ControlEdge output;

  public ControlEdge getOutput() { return output; }

  public void setOutput(ControlEdge e) 
      throws EdgeLinkageError
  { 
    if (output != null) throw new EdgeLinkageError("Output already set.");
    output = e; 
  }

  public ControlEdgeEnumeration getOutputs() {
    return new SingleControlEdgeEnumeration(output);
  }
  
}
