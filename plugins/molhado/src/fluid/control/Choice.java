/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/Choice.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.ir.IRNode;

/** Points where depending on some program condition, control could
 * go in one of two ways.  A constant tag distinguishes various
 * kinds of choice nodes.  The subclass gives a way to attach this
 * choice to a particular syntax node.
 * @author John Tang Boyland
 * @see ComponentChoice
 * @see SubcomponentChoice
 */
public abstract class Choice extends Split {
  private final Object value;
  public Choice(Object info) { value = info; };
  public Object getValue() {
    return value;
  }
  public Object getInfo() {
    return value;
  }
  public abstract IRNode getSyntax();
}
