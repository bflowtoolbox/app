/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Sink.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;


/** Abstract class for control-flow nodes with one input and no output
 * @author John Tang Boyland
 * @see Abort
 * @see ComponentSink
 */

public abstract class Sink extends Entity
     implements ControlNode, OneInput, NoOutput
{
  protected ControlEdge input;
  public ControlEdge getInput() { return input; }
  public void setInput(ControlEdge input) 
      throws EdgeLinkageError
  {
    if (this.input != null) throw new EdgeLinkageError("input already set");
    this.input = input;
  }
  public ControlEdgeEnumeration getInputs() {
    return new SingleControlEdgeEnumeration(input);
  }
  public ControlEdgeEnumeration getOutputs() {
    return EmptyControlEdgeEnumeration.prototype;
  }
}
