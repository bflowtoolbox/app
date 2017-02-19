package fluid.java;

import java.util.Hashtable;

import fluid.FluidError;
import fluid.control.Component;
import fluid.control.ComponentFactory;
import fluid.ir.IRNode;
import fluid.tree.SyntaxTree;

public class JavaComponentFactory extends ComponentFactory {
  public static JavaComponentFactory prototype = new JavaComponentFactory();

  /** Each syntax node potentially has a control component.
   * This information is stored in the following table.
   * The structures are transient, and so there is no need
   * to use slots.
   * @type Hashtable[IRNode,Component]
   */
  private static Hashtable components = new Hashtable();
  public static Component lookupComponent(IRNode node) {
    return (Component)(components.get(node));
  }
  public Component getComponent(IRNode node) {
    return getComponent(node,false);
  }
  public static Component getComponent(IRNode node, boolean quiet) {
    Component comp = lookupComponent(node);
    if (comp == null)
      return prototype.createComponent(node,quiet);
    else
      return comp;
  }
  public Component createComponent(IRNode node, boolean quiet) {
    JavaOperator op = (JavaOperator)JavaNode.tree.getOperator(node);
    Component comp = op.createComponent(node);
    if (comp != null) {
      if (!comp.isValid()) {
	System.out.println("Warning: invalid component for");
	JavaNode.dumpTree(System.out,node,1);
      }
      comp.registerFactory(this);
      components.put(node,comp);
    } else if (!quiet) {
      System.out.println("Warning: Null control-flow component for");
      System.out.println(DebugUnparser.toString(node));
      while ((node = JavaNode.tree.getParentOrNull(node)) != null) {
	System.out.println("  child of " +
			   JavaNode.tree.getOperator(node).name() + " node");
      }
      (new FluidError("Just want the trace")).printStackTrace();
    }
    return comp;
  }
  public SyntaxTree tree() {
    return JavaOperator.tree;
  }
}
