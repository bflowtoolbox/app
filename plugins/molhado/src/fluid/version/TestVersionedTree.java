/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/TestVersionedTree.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.IRNode;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.tree.Digraph;
import fluid.tree.SymmetricDigraph;
import fluid.tree.TestTree;
import fluid.tree.Tree;

/** A test case to test how fluid.tree structures work with versions. */
public class TestVersionedTree extends TestTree {

  /** Create a Versioned Tree.  We see if the SlotFactory
   * is simple in which case we substitute the VersionedSlotFactory.
   */
  protected Digraph createStored(SlotFactory sf) {
    if (sf == SimpleSlotFactory.prototype) {
      sf = VersionedSlotFactory.prototype;
    }
    return super.createStored(sf);
  }

  public static void main(String[] args)
  {
    (new TestVersionedTree()).test(args);
  }

  public static void reportError(String s) {
    System.out.println("!!! " + s);
  }

  public void test(String args[]) {
    // generic tests
    super.test(args);

    Tree t = null;
    SymmetricDigraph sd = null;

    try {
      t = new Tree(null,VersionedSlotFactory.prototype);
      sd = new SymmetricDigraph(null,VersionedSlotFactory.prototype);
    } catch (SlotAlreadyRegisteredException  ex) {
      reportError("should not happen");
      ex.printStackTrace();
      System.exit(1);
    }

    IRNode root = new SelfDocumentingIRNode("root");
    IRNode c0 = new SelfDocumentingIRNode("c0");
    IRNode c1 = new SelfDocumentingIRNode("c1");
    IRNode c2 = new SelfDocumentingIRNode("c2");
    IRNode gc1 = new SelfDocumentingIRNode("gc1");
    IRNode gc2 = new SelfDocumentingIRNode("gc2");
    IRNode r2 = new SelfDocumentingIRNode("r2");

    Version v1 = Version.getVersion();
    Version.bumpVersion();
    Version v2 = Version.getVersion();

    if (v1 == v2)
      reportError("bumpVersion didn't");

    t.initNode(root,3);		sd.initNode(root,0,3);
    t.initNode(c0,0);		sd.initNode(c0,1,0);
    t.initNode(c1,~0);		sd.initNode(c1,2,~0);
    t.initNode(c2,~1);		sd.initNode(c2,~1,~1);
    t.initNode(gc1,1);		sd.initNode(gc1,1,1);
    t.initNode(gc2,0);		sd.initNode(gc2,1,0);
    t.initNode(r2,~2);		sd.initNode(r2,0,~2);

    t.setChild(root,0,c0);	sd.setChild(root,0,c0);
    t.setChild(root,1,null);	sd.setChild(root,1,null);
    t.setChild(root,2,c2);	sd.setChild(root,2,c2);
    t.setChild(c2,0,gc2);	sd.setChild(c2,0,gc2);

    t.setChild(r2,0,null);	sd.setChild(r2,0,null);

    sd.setParent(gc1,0,null);
    sd.setParent(c1,0,null);

    Version initialV = Version.getVersion();

    if (initialV == v2)
      reportError("Creating initial tree created no versions");
    if (initialV.parent() != v2)
      reportError("Creating initial tree created extra versions");

    t.insertChild(c2,gc1);	sd.insertChild(c2,gc1);
    t.setChild(root,1,c1);	sd.setChild(root,1,c1);

    Version.setVersion(initialV);

    if (t.getParentOrNull(gc1) != null)
      reportError("Tree: inserting a child sets initial parent!");
    if (t.getParentOrNull(c1) != null)
      reportError("Tree: grafting in child sets initial parent!");
    if (sd.getParent(gc1,0) != null)
      reportError("SD: inserting a child sets initial parent!");
    if (sd.getParent(c1,0) != null)
      reportError("SD: grafting in child sets initial parent!");

    // if we use c1, the previous bug disrupts structure
    t.removeSubtree(c2);	sd.removeParent(c2,root);
    t.setChild(r2,0,c2);	sd.setChild(r2,0,c2);

    Version.setVersion(initialV);

    if (t.getChild(r2,0) != null)
      reportError("Tree: moving a child set initial child!");
    if (sd.getChild(r2,0) != null)
      reportError("Tree: moving a child set initial child!");
 }
}
