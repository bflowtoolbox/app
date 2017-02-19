/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/OneInput.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Interface for control-flow nodes with one input edge
 * @author John Tang Boyland
 * @see Sink
 * @see Flow
 * @see Split
 */
 
public interface OneInput {
  ControlEdge getInput();
  void setInput(ControlEdge input);
}
