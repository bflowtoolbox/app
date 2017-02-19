/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/DoubleInputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class DoubleInputPort extends InputPort
    implements TwoOutput
{
  protected ControlEdge output1, output2;
  
  public ControlEdge getOutput1() { return output1; }
  public ControlEdge getOutput2() { return output2; }

  public void setOutput1(ControlEdge e)
      throws EdgeLinkageError
  {
    if (output1 != null) throw new EdgeLinkageError("Output #1 already set.");
    output1 = e;
  }

  public void setOutput2(ControlEdge e)
      throws EdgeLinkageError
  {
    if (output2 != null) throw new EdgeLinkageError("Output #2 already set.");
    output2 = e;
  }

  public ControlEdgeEnumeration getOutputs() {
    return new PairControlEdgeEnumeration(output1,output2);
  }
}
