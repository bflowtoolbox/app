/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/EmptyControlEdgeEnumeration.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import java.util.NoSuchElementException;

/** A class for empty enumerations of control-flow edges. */
class EmptyControlEdgeEnumeration extends ControlEdgeEnumeration {
  public static EmptyControlEdgeEnumeration prototype = 
      new EmptyControlEdgeEnumeration();
  public boolean hasMoreElements() {
    return false;
  }
  public ControlEdge nextControlEdge() 
      throws NoSuchElementException
  {
    throw new NoSuchElementException("enumeration is empty");
  }
}
