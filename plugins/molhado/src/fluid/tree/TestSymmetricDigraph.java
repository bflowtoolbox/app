package fluid.tree;

import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;

class TestSymmetricDigraph extends TestDigraph {
  public static void main(String[] args) {
    new TestSymmetricDigraph().test(args);
  }
  Digraph createStored(SlotFactory sf) {
    try {
      return new SymmetricDigraph(null,sf);
    } catch (SlotAlreadyRegisteredException ex) {
      System.out.println("panic: " + ex);
      ex.printStackTrace();
      System.exit(1);
      return null;
    }
  }
  Digraph createCopy(Digraph dig) {
    return new SymmetricDigraph(dig.getAttribute("children"),
				dig.getAttribute("parents"));
  }
  Digraph createBackwardCopy(Digraph dig) {
    return new SymmetricDigraph(dig.getAttribute("parents"),
				dig.getAttribute("children"));
  }

  public void test(String[] args) {
    verbose = args.length != 0;
    // immutable SymmetricDigraphs are basically useless.
    Digraph dig = createStored(SimpleSlotFactory.prototype);
    test("mutable",dig,createCopy(dig),true,getDagOK());
    Digraph backward = createBackwardCopy(dig);
    test("backward",backward,backward,true,getDagOK());
  }
}
