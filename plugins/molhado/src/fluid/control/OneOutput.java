/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/OneOutput.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Interface for control-flow nodes with one output edge
 * @author John Tang Boyland
 * @see Source
 * @see Flow
 * @see Join
 */
 
public interface OneOutput {
  ControlEdge getOutput();
  void setOutput(ControlEdge output);
}

