/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/DigraphMixin.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import java.util.Vector;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRObservable;
import fluid.ir.InsertionPoint;

/** A class that provides some common functions
 * for directed graphs.
 * It implements the listerner protocol and
 * also provides some default definitions for functions
 */
abstract public class DigraphMixin extends IRObservable
     implements DigraphInterface 
{
  private final Vector listeners = new Vector();

  public void addDigraphListener(DigraphListener dl) {
    listeners.addElement(dl);
  }
  public void removeDigraphListener(DigraphListener dl) {
    listeners.removeElement(dl);
  }

  protected boolean hasListeners() {
    return listeners.size() > 0;
  }
  
  protected void informDigraphListeners(DigraphEvent de) {
    for (int i=listeners.size(); i > 0; --i) {
      DigraphListener dl = (DigraphListener)listeners.elementAt(i-1);
      if (dl != null) dl.handleDigraphEvent(de);
    }
  }

  /** Add new child as a new child of node at the given insertion point.
   *  @exception StructureException if newChild is not suitable
   *            or the parent cannot accept new children.
   * @return location of new child
   */
  public abstract IRLocation
    insertChild(IRNode node, IRNode newChild, InsertionPoint ip)
    throws StructureException;

  /** Add newChild as a new first child of node.
   * @exception IllegalChildException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void insertChild(IRNode node, IRNode newChild)
       throws IllegalChildException
  {
    insertChild(node,newChild,InsertionPoint.first);
  }

  /** Add newChild as a new last child of node.
   * @exception StructureException if newChild is not suitable
   *            or the parent cannot accept new children.
   */
  public void appendChild(IRNode node, IRNode newChild)
       throws StructureException
  {
    insertChild(node,newChild,InsertionPoint.last);
  }

}
