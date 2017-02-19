/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/SimpleOutputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class SimpleOutputPort extends OutputPort 
    implements OneInput
{
  protected ControlEdge input;

  public ControlEdge getInput() { return input; }

  public void setInput(ControlEdge e) 
      throws EdgeLinkageError
  { 
    if (input != null) throw new EdgeLinkageError("Input already set.");
    input = e; 
  }

  public ControlEdgeEnumeration getInputs() {
    return new SingleControlEdgeEnumeration(input);
  }
  
}
