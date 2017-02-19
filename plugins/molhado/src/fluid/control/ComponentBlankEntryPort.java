/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ComponentBlankEntryPort.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import fluid.ir.IRNode;

public class ComponentBlankEntryPort extends BlankInputPort
    implements ComponentPort, EntryPort
{
  protected Component component;
  
  public ComponentBlankEntryPort(Component comp) {
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
