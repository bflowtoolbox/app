/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Abort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Control should never reach this point.
 * @author John Tang Boyland
 */

public class Abort extends Sink {
  public Abort() {};
  public Abort(ControlEdge prev) { setInput(prev); }
}
