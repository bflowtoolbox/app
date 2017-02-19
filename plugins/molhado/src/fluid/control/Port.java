/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Port.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Ports into and out of syntactic constructs.
 * Ports are the only access to nodes inside a construct (region).
 * Ports come in several orthogonal varieties:
 * <ul>
 *   <li><dl>
 *         <dt>ComponentPort<dd> port on the outside of a component.
 *	   <dt>SubcomponentPort<dd> port inside a component around the
 *				    component for a child.
 *	 </dl>
 *   <li><dl>
 *         <dt>EntryPort<dd> port on control entering region.
 *         <dt>ExitPort<dd> port on control leaving a region.
 *           <dl>
 *             <dt>NormalExitPort<dd> port for normal termination.
 *             <dt>AbruptExitPort<dd> port for abrupt termination.
 *           </dl>
 *       </dl>
 *   <li><dl>
 *         <dt>InputPort<dd> port with local outputs.
 *           <dl>
 *             <dt>BlankInputPort<dd> unconnected port (unused).
 *             <dt>SimpleInputPort<dd> port with one local output.
 *             <dt>DoubleInputPort<dd> port with two local outputs.
 *           </dl>
 *         <dt>OutputPort<dd> port with local inputs.
 *           <dl>
 *             <dt>BlankOutputPort<dd> unconnected port (unused).
 *             <dt>SimpleOutputPort<dd> port with one local input.
 *             <dt>DoubleOutputPort<dd> port with two local inputs.
 *           </dl>
 *       </dl>
 * </ul>
 * @author John Tang Boyland
 * @see Component
 * @see Subcomponent
 *
 * @see ComponentPort
 * @see SubcomponentPort
 * @see EntryPort
 * @see ExitPort
 * @see InputPort
 * @see OutputPort
 */

import fluid.ir.IRNode;

public interface Port extends ControlNode {
  /** Return the port that this port is connected with.
   * The dual of the dual is this port.
   */
  public abstract Port getDual();
  /** Return the syntax associated with the sub/component
   * this port is associated with.
   */
  public abstract IRNode getSyntax();
  /** Return the component associated with this port.
   * (If a port of a subcomponent, then get the
   *  corresponding child's component).
   */
  public abstract Component getComponent();
  /** Return the subcomponent associated with this port.
   * (If a port of a component, then get the subcomponent
   * from the parent.)
   */
  public abstract Subcomponent getSubcomponent();    
}

