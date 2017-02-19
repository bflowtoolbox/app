package sc.document;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRNodeType;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentReferenceType;
import fluid.ir.IRStringType;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.util.UniqueID;
import fluid.version.VersionedSlotFactory;
import fluid.version.VersionedUnitSlotInfo;
/**
 * @author Tien
 *
 */
public class HypertextAttrBundle {
  
  private static Bundle htattr = null;
  
  /** Need some VUSI to watch changes in hypertextAttr */  
  public  static HypertextChanged htchanged;

  /** This attribute "nodeAttr" keeps a pointer from a node in a hypertext 
   * graph to an IR Node in a document tree 
   */
  public static SlotInfo nodeattr_si;

  /** Contain the document that an anchor points to */
  public static SlotInfo destdoc_si;
  
  /** The name of anchors or links */
  public static SlotInfo name_si;
  
  /** A VUSI to watch for changes in nodeattr_si and destdoc_si */
  public static VersionedUnitSlotInfo htrefchanged;
   
  static {
    try {   
      nodeattr_si = VersionedSlotFactory.prototype.makeSlotInfo("schypertext.nodeattr",
               IRNodeType.prototype);
      
      destdoc_si = VersionedSlotFactory.prototype.makeSlotInfo("schypertext.destdoc",
               IRPersistentReferenceType.prototype);
      
      name_si = VersionedSlotFactory.prototype.makeSlotInfo("schypertext.nodename",
               IRStringType.prototype);
      
      htchanged = 
          new HypertextChanged("schypertext.htchanged", HypertextGraphBundle.graph);
      
      htrefchanged = new VersionedUnitSlotInfo("schypertext.htrefchanged");      
      nodeattr_si.addDefineObserver(htrefchanged);
      destdoc_si.addDefineObserver(htrefchanged);
       
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidError("slot already registered exception : " + e);
    }
  } 
     
  public static Bundle getBundle() {
    if (htattr == null) {
      try {
        htattr =
          Bundle.loadBundle(
            UniqueID.parseUniqueID("htattr"),
            IRPersistent.fluidFileLocator);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error in loading \"htattr\" bundle !");
      }
    }
    return htattr;
  }
  
  public static void main(String[] args) {    
    
    try {
      htattr = new Bundle();
      htattr.saveAttribute(nodeattr_si);
      htattr.saveAttribute(destdoc_si);
      htattr.saveAttribute(name_si);
      htattr.saveAttribute(htchanged);
			htattr.saveAttribute(htrefchanged);

      htattr.store(IRPersistent.fluidFileLocator);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (htattr != null)
      htattr.describe(System.out);
    
    /* 
    htattr = getBundle();
    System.out.println("Getting from disk ...");
    if (htattr != null) htattr.describe(System.out);    
    */
  }
}
