/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/PairControlEdgeEnumeration.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

import java.util.NoSuchElementException;

/** Class to represent an enumeration that has up to two nodes.
 * @author John Tang Boyland
 */
class PairControlEdgeEnumeration extends ControlEdgeEnumeration {
  private ControlEdge next1, next2;
  private int returned = 0;
  /** Create an enumeration of control nodes.
   * @param elem1 the node to be returned first
   * @param elem2 the node to be returned next
   */
  PairControlEdgeEnumeration (ControlEdge elem1, ControlEdge elem2) {
    this.next1 = elem1;
    this.next2 = elem2;
  }
  public boolean hasMoreElements() { 
    return returned < 2;
  }
  public ControlEdge nextControlEdge() throws NoSuchElementException {
    switch (returned) {
    case 0:
      returned = 1;
      return next1;
    case 1:
      returned = 2;
      return next2;
    default:
      throw new NoSuchElementException();
    }
  }
}
