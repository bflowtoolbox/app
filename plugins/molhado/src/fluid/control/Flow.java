/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Flow.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Abstract class for control-flow nodes with one input and one output
 * @author John Tang Boyland
 * @see NoOperation
 * @see ComponentFlow
 * @see SubcomponentFlow
 * @see AddLabel
 * @see PendingLabelStrip
 */

public abstract class Flow extends Entity
     implements ControlNode, OneInput, OneOutput
{
  protected ControlEdge input, output;
  public ControlEdge getInput() { return input; }
  public ControlEdge getOutput() { return output; }
  public void setInput(ControlEdge input) 
      throws EdgeLinkageError
  {
    if (this.input != null) throw new EdgeLinkageError("input already set");
    this.input = input;
  }
  public void setOutput(ControlEdge output)
    throws EdgeLinkageError
  {
    if (this.output != null) throw new EdgeLinkageError("output already set");
    this.output = output;
  }
  public ControlEdgeEnumeration getInputs() {
    return new SingleControlEdgeEnumeration(input);
  }
  public ControlEdgeEnumeration getOutputs() {
    return new SingleControlEdgeEnumeration(output);
  }
}
