package sc.document;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRPersistent;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.SyntaxTree;
import fluid.util.UniqueID;
import fluid.version.TreeChanged;
import fluid.version.VersionedSlotFactory;

/**
 * @author Tien N. Nguyen
 */
public class DocTreeBundle {
  
  public static final SyntaxTree  tree;
  public static final TreeChanged tc;
  
  static {
    try {   
      tree = new SyntaxTree("SCDocument", VersionedSlotFactory.prototype);
      tc   = new TreeChanged("SCDocument.treeChanged", tree);
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidError("slot already registered exception : " + e);
    }
  }
  
  private static Bundle doctreebundle = null;
  public static Bundle getBundle() {
    if (doctreebundle == null) {
      try {
        doctreebundle =
          Bundle.loadBundle(
            UniqueID.parseUniqueID("doctree"),
            IRPersistent.fluidFileLocator);
      } catch (Throwable t) {
        System.out.println("Error in loading \"doctree\" bundle !");
      }
    }
    return doctreebundle;
  }
  
  public static void main(String[] args) {
    Bundle b = new Bundle();
    tree.saveAttributes(b);
    b.saveAttribute(tc);
    try {
      b.store(IRPersistent.fluidFileLocator);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (b != null)
      b.describe(System.out);    
    /*
    Bundle b = getBundle();
    System.out.println("Getting from disk ...");
    if (b != null)
      b.describe(System.out);
    */
  }
}
