package fluid.tree;

import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;

public class TestTree extends TestDigraph {
  public static void main(String[] args) {
    new TestTree().test(args);
  }
  protected Digraph createStored(SlotFactory sf) {
    try {
      return new Tree(null,sf);
    } catch (SlotAlreadyRegisteredException ex) {
      System.out.println("panic: " + ex);
      ex.printStackTrace();
      System.exit(1);
      return null;
    }
  }
  protected Digraph createCopy(Digraph dig) {
    return new Tree(dig.getAttribute(Digraph.CHILDREN),
		    dig.getAttribute(Tree.PARENTS),
		    dig.getAttribute(Tree.LOCATION));
  }
  protected boolean getDagOK() { return false; }
}
