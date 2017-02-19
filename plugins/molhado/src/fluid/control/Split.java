/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Split.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Abstract class for control-flow nodes with one input and two outputs
 * @author John Tang Boyland
 * @see Fork
 * @see Choice
 * @see TrackedDemerge
 * @see LabelTest
 */

public abstract class Split extends Entity
     implements ControlNode, OneInput, TwoOutput
{
  protected ControlEdge input, output1, output2;
  public ControlEdge getInput() { return input; }
  public ControlEdge getOutput1() { return output1; }
  public ControlEdge getOutput2() { return output2; }
  public ControlEdgeEnumeration getInputs() {
    return new SingleControlEdgeEnumeration(input);
  }
  public ControlEdgeEnumeration getOutputs() {
    return new PairControlEdgeEnumeration(output1,output2);
  }
  public void setInput(ControlEdge input) 
      throws EdgeLinkageError
  {
    if (this.input != null) throw new EdgeLinkageError("input already set");
    this.input = input;
  }
  public void setOutput1(ControlEdge output1) 
      throws EdgeLinkageError
  {
    if (this.output1 != null) 
	throw new EdgeLinkageError("output #1 already set");
    this.output1 = output1;
  }
  public void setOutput2(ControlEdge output2) 
      throws EdgeLinkageError
  {
    if (this.output2 != null)
	throw new EdgeLinkageError("output #2 already set");
    this.output2 = output2;
  }
}

