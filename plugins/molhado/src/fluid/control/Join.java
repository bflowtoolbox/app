/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Join.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Abstract class for control-flow nodes with two inputs and one output
 * @author John Tang Boyland
 * @see Merge
 * @see TrackedMerge
 */

public abstract class Join extends Entity
     implements ControlNode, TwoInput, OneOutput
{
  protected ControlEdge input1, input2, output;
  public ControlEdge getInput1() { return input1; }
  public ControlEdge getInput2() { return input2; }
  public ControlEdge getOutput() { return output; }
  public ControlEdgeEnumeration getInputs() {
    return new PairControlEdgeEnumeration(input1,input2);
  }
  public ControlEdgeEnumeration getOutputs() {
    return new SingleControlEdgeEnumeration(output);
  }
  public void setInput1(ControlEdge input1) 
      throws EdgeLinkageError
  {
    if (this.input1 != null) 
	throw new EdgeLinkageError("input #1 already set");
    this.input1 = input1;
  }
  public void setInput2(ControlEdge input2) 
      throws EdgeLinkageError
  {
    if (this.input2 != null)
	throw new EdgeLinkageError("input #2 already set");
    this.input2 = input2;
  }
  public void setOutput(ControlEdge output)
    throws EdgeLinkageError
  {
    if (this.output != null) throw new EdgeLinkageError("output already set");
    this.output = output;
  }
}
