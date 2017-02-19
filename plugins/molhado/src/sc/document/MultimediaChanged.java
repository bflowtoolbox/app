package sc.document;

import java.util.Observable;
import java.util.Observer;

import fluid.ir.IRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.Tree;
import fluid.version.VersionedUnitSlotInfo;

public class MultimediaChanged
  extends VersionedUnitSlotInfo implements Observer {
  
  private final Tree tree;
  public MultimediaChanged(Tree t) {
    tree = t;
    
  }

  public MultimediaChanged(String name, Tree t) throws SlotAlreadyRegisteredException
  {
    super(name);
    tree = t;
  }

  /** Note a change at a node and thus at its ancestors. */
  public void noteChange(IRNode node) {
    while (node != null && super.setChanged(node)) {
      IRNode parent = tree.getParentOrNull(node);
      node = parent;
    }
  }
  
  public void update(Observable obs, Object node) {
    noteChange((IRNode)node);
  }
}
