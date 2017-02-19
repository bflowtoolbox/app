package fluid.control;

import fluid.ir.IRNode;

public class SubcomponentBooleanExitPort extends DoubleInputPort
    implements SubcomponentPort, NormalExitPort
{
  protected Subcomponent subcomponent;
  
  public SubcomponentBooleanExitPort(Subcomponent subcomp) {
    subcomponent = subcomp;
    subcomp.registerNormalExitPort(this);
  }

  public Subcomponent getSubcomponent() {
    return subcomponent;
  }

  public Port getDual() {
    Component childComp = subcomponent.getComponentInChild();
    if (childComp == null) return null;
    return childComp.getNormalExitPort();
  }  

  public IRNode getSyntax() {
    return subcomponent.getSyntax();
  }

  public Component getComponent() {
    return subcomponent.getComponentInChild();
  }
}

