package fluid.control;

import fluid.ir.IRNode;

public class ComponentAbruptExitPort extends SimpleOutputPort
    implements ComponentPort, AbruptExitPort
{
  protected Component component;
  
  public ComponentAbruptExitPort(Component comp) {
    component = comp;
    comp.registerAbruptExitPort(this);
  }

  public Component getComponent() {
    return component;
  }

  public Port getDual() {
    Subcomponent sub = component.getSubcomponentInParent();
    if (sub == null) return null;
    return sub.getAbruptExitPort();
  }  

  public IRNode getSyntax() {
    return component.getSyntax();
  }

  public Subcomponent getSubcomponent() {
    return component.getSubcomponentInParent();
  }
}
