package fluid.control;

import fluid.ir.IRNode;

public class SubcomponentAbruptExitPort extends SimpleInputPort
    implements SubcomponentPort, AbruptExitPort
{
  protected Subcomponent subcomponent;
  
  public SubcomponentAbruptExitPort(Subcomponent subcomp) {
    subcomponent = subcomp;
    subcomp.registerAbruptExitPort(this);
  }

  public Subcomponent getSubcomponent() {
    return subcomponent;
  }

  public Port getDual() {
    Component childComp = subcomponent.getComponentInChild();
    if (childComp == null) return null;
    return childComp.getAbruptExitPort();
  }  

  public IRNode getSyntax() {
    return subcomponent.getSyntax();
  }

  public Component getComponent() {
    return subcomponent.getComponentInChild();
  }
}

