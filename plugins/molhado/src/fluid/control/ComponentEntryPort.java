package fluid.control;

import fluid.ir.IRNode;

public class ComponentEntryPort extends SimpleInputPort 
    implements ComponentPort, EntryPort
{
  protected Component component;
  
  public ComponentEntryPort(Component comp) {
    component = comp;
    comp.registerEntryPort(this);
  }

  public Component getComponent() {
    return component;
  }

  public Port getDual() {
    Subcomponent sub = component.getSubcomponentInParent();
    if (sub == null) return null;
    return sub.getEntryPort();
  }

  public IRNode getSyntax() {
    return component.getSyntax();
  }

  public Subcomponent getSubcomponent() {
    return component.getSubcomponentInParent();
  }
}
