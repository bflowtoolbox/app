/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/ControlEdgeEnumeration.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * Class to represent sequences of control nodes of Java programs.
 * @author John Tang Boyland
 * @see EmptyControlEdgeEnumeration
 * @see SingleControlEdgeEnumeration
 * @see PairControlEdgeEnumeration
 */
public abstract class ControlEdgeEnumeration implements Enumeration {
  public Object nextElement() throws NoSuchElementException {
    return nextControlEdge();
  }
  abstract public boolean hasMoreElements();
  abstract public ControlEdge nextControlEdge() throws NoSuchElementException;
}
