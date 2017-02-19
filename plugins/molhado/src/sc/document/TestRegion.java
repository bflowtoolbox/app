package sc.document;

import java.util.Enumeration;

import fluid.ir.IRNode;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.Tree;
import fluid.version.Era;
import fluid.version.OverlappingEraException;
import fluid.version.Version;
import fluid.version.VersionedRegion;
import fluid.version.VersionedSlotFactory;

/**
 */
public class TestRegion {
  private static Tree t;
  static {
    try {
      t = new Tree("TestRegion.syntax", VersionedSlotFactory.prototype);
    } catch (SlotAlreadyRegisteredException e) {
    }
  }
  
  private static Era era1;
  private static Era era2;
  
  private static VersionedRegion region;
  
  public static void main(String[] args) {
    region = new VersionedRegion();
    
    IRNode
      root = new PlainIRNode(region),
      c1   = new PlainIRNode(region),
      c2   = new PlainIRNode(region),
      c3   = new PlainIRNode(region),
      l1   = new PlainIRNode(region),
      l2   = new PlainIRNode(region),
      l3   = new PlainIRNode(region);
    
    t.initNode(root,~4);
    t.removeSubtree(root);
    {
      t.initNode(c1,0);
      t.setChild(root,0,c1);
      t.initNode(c2,~2);
      t.setChild(root,1,c2);
      {
        t.initNode(l1,0);
        t.setChild(c2,0,l1);
        t.initNode(l2,~1);
        t.setChild(c2,1,l2);
        t.initNode(l3,0);
      }
      t.initNode(c3,1);
      t.setChild(root,2,c3);
      {
        t.setChild(c3,0,l3);
      }
    }
    
    Version v1 = Version.getVersion();
    
    try {
      era1 = new Era(Version.getInitialVersion(), new Version[]{v1});
    } catch (OverlappingEraException e) {
      e.printStackTrace();
    }
    
    Version.bumpVersion();
    IRNode c4 = new PlainIRNode(region);
    t.initNode(c4,0);
    t.setChild(root,3,c4);
    
    Version v2 = Version.getVersion();
    Version.bumpVersion();
    
    IRNode d1 = new PlainIRNode(region);
    t.initNode(d1,0);
    t.setChild(l2,0,d1);
    
    Version v3 = Version.getVersion();
    
    System.out.println("BEFORE NEW AN ERA");
    Enumeration en = region.allNodes(v3);
    int i = 0;
    while (en.hasMoreElements()) {
      IRNode n = (IRNode) en.nextElement();
      System.out.println(i + 1 + ". ( " + n + " ).");
      i++;
    }
    
    // Create a new era
    try {
      era2 = new Era(v1, new Version[]{v3});
    } catch (OverlappingEraException e) {
      e.printStackTrace();
    }
    
    System.out.println("AFTER NEW AN ERA");
    i = 0;
    en = region.allNodes(v3);
    while (en.hasMoreElements()) {
      IRNode n = (IRNode) en.nextElement();
      System.out.println(i + 1 + ". ( " + n + " ).");
      i++;
    }
  }
}
