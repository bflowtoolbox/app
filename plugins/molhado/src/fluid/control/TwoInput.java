/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/TwoInput.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Interface for control-flow nodes with two Input edges
 * @author John Tang Boyland
 * @see Join
 */
 
public interface TwoInput {
  ControlEdge getInput1();
  ControlEdge getInput2();
  void setInput1(ControlEdge input1);
  void setInput2(ControlEdge input2);
}
