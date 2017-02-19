/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/TreeChanged.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.util.Observable;
import java.util.Observer;

import fluid.ir.IRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.Tree;

/** Change bits attached to tree nodes.  If a change is noted at
 * a node, it is propagated toward the root.  At any point,
 * a client can request if a change has happened at a node
 * (which of course includes any node in its subtree)
 * with respect to any prior version.
 */
public class TreeChanged extends VersionedUnitSlotInfo implements Observer {
  private final Tree tree;
  public TreeChanged(Tree t) {
    tree = t;
    t.addObserver(this);
  }

  public TreeChanged(String name, Tree t) throws SlotAlreadyRegisteredException
  {
    super(name);
    tree = t;
    t.addObserver(this);
  }

  /** A separate object holder observers of roots. */
  private final Observable rootObservable = new Observable() {
    public void notifyObservers(Object o) {
      super.setChanged();
      super.notifyObservers(o);
    }
  };

  public void addRootObserver(Observer o) {
    rootObservable.addObserver(o); 
  }
  public void deleteRootObserver(Observer o) {
    rootObservable.deleteObserver(o); 
  }
  
  /** Note a change at a node and thus at its ancestors. */
  public void noteChange(IRNode node) {
    while (node != null && super.setChanged(node)) {
      // System.out.println("Setting tree changed for " + node);
      IRNode parent = tree.getParentOrNull(node);
      if (parent == null) {
	rootObservable.notifyObservers(node);
      }
      node = parent;
    }
  }

  /** Inform a change at a particular IRNode.
   * @param node a tree node
   */
  public void update(Observable obs, Object node) {
    noteChange((IRNode)node);
  }
}
