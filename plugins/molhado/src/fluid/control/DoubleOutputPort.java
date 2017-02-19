/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/DoubleOutputPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public abstract class DoubleOutputPort extends OutputPort
    implements TwoInput
{
  protected ControlEdge input1, input2;
  
  public ControlEdge getInput1() { return input1; }
  public ControlEdge getInput2() { return input2; }

  public void setInput1(ControlEdge e)
      throws EdgeLinkageError
  {
    if (input1 != null) throw new EdgeLinkageError("Input #1 already set.");
    input1 = e;
  }

  public void setInput2(ControlEdge e)
      throws EdgeLinkageError
  {
    if (input2 != null) throw new EdgeLinkageError("Input #2 already set.");
    input2 = e;
  }

  public ControlEdgeEnumeration getInputs() {
    return new PairControlEdgeEnumeration(input1,input2);
  }
}
