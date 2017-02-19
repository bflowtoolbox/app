/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Source.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Abstract class for control-flow nodes with no input and one output
 * @author John Tang Boyland
 * @see Never
 * @see ComponentSource
 */

public abstract class Source extends Entity
     implements ControlNode, NoInput, OneOutput
{
  protected ControlEdge output;
  public ControlEdge getOutput() { return output; }
  public void setOutput(ControlEdge output)
    throws EdgeLinkageError
  {
    if (this.output != null) throw new EdgeLinkageError("output already set");
    this.output = output;
  }
  public ControlEdgeEnumeration getInputs() {
    return EmptyControlEdgeEnumeration.prototype;
  }
  public ControlEdgeEnumeration getOutputs() {
    return new SingleControlEdgeEnumeration(output);
  }
}
