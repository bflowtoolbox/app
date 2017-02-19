/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ControlNode.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.ir.IRNode;

/** Control nodes for Java programs.
 * @author John Tang Boyland
 * @see Source
 * @see Sink
 * @see Flow
 * @see Join
 * @see Split
 */
public interface ControlNode extends IRNode {
  abstract public ControlEdgeEnumeration getInputs();
  abstract public ControlEdgeEnumeration getOutputs();
}

