package fluid.control;

import fluid.ir.IRNode;

public class ComponentNormalExitPort extends SimpleOutputPort
    implements ComponentPort, NormalExitPort
{
  protected Component component;
  
  public ComponentNormalExitPort(Component comp) {
    component = comp;
    comp.registerNormalExitPort(this);
  }

  public Component getComponent() {
    return component;
  }

  public Port getDual() {
    Subcomponent sub = component.getSubcomponentInParent();
    if (sub == null) return null;
    return sub.getNormalExitPort();
  }  

  public IRNode getSyntax() {
    return component.getSyntax();
  }

  public Subcomponent getSubcomponent() {
    return component.getSubcomponentInParent();
  }
}
