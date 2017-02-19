/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ComponentBlankNormalExitPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.ir.IRNode;

public class ComponentBlankNormalExitPort extends BlankOutputPort
    implements ComponentPort, NormalExitPort
{
  protected Component component;
  
  public ComponentBlankNormalExitPort(Component comp) {
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
