/* Header */
package fluid.control;

import fluid.ir.IRNode;

/** Control-flow decision points in the graph that depend
 * on language-specific conditions.  The subcomponent is identified
 * as well as an arbitrary value that may be used to distinguish
 * multiple control points within a subcomponent.
 * This node is treated as a ComponentFlow during control flow analysis.
 * @see ComponentFlow
 */
public class SubcomponentFlow extends Flow {
  Subcomponent sub;
  Object value;
  public SubcomponentFlow(Subcomponent s, Object v) {
    super();
    sub = s;
    value = v;
  }
  public Subcomponent getSubcomponent() {
    return sub;
  }
  public IRNode getSyntax() {
    return sub.getComponent().getSyntax();
  }
  public Object getValue() {
    return value;
  }
  public Object getInfo() {
    return value;
  }
}
