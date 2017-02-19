/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/TwoOutput.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Interface for control-flow nodes with two output edges
 * @author John Tang Boyland
 * @see Split
 */
 
public interface TwoOutput {
  ControlEdge getOutput1();
  ControlEdge getOutput2();
  void setOutput1(ControlEdge output1);
  void setOutput2(ControlEdge output2);
}

