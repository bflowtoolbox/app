/* Header */
package fluid.control;

import fluid.ir.IRNode;

/** Control-flow decision points in the graph that depend
 * on language-specific conditions.  The component is identified
 * as well as an arbitrary value that may be used to distinguish
 * multiple control points within a component.
 */
public class ComponentChoice extends Choice {
  Component comp;
  public ComponentChoice(Component c, Object v) {
    super(v);
    comp = c;
  }
  public Component getComponent() {
    return comp;
  }
  public IRNode getSyntax() {
    return comp.getSyntax();
  }
}
