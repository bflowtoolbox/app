package sc.document;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRPersistent;
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

public class GoalModelAttrBundle {
	  
	  private static Bundle gmattr = null;
	  
	  /** Need some VUSI to watch changes in goalmodelAttr */  
	  public  static GoalModelChanged goalmodelchanged;
	  
	  /** The name attribute of entities in a goal model */
	  public static SlotInfo name_si;
	  
	  /** A VUSI to watch for changes in attributes */
	  public static VersionedUnitSlotInfo goalmodelpropertychanged;
	   
	  static {
	    try {   
	      
	      name_si = VersionedSlotFactory.prototype.makeSlotInfo("goalmodel.nodename",
	               IRStringType.prototype);
	      
	      goalmodelchanged = 
	          new GoalModelChanged("goalmodel.changed", GoalModelGraphBundle.graph);
	      
	      goalmodelpropertychanged = new VersionedUnitSlotInfo("goalmodel.propertychanged");   
	      name_si.addDefineObserver(goalmodelpropertychanged);	      
	       
	    } catch (SlotAlreadyRegisteredException e) {
	      throw new FluidError("slot already registered exception : " + e);
	    }
	  } 
	     
	  public static Bundle getBundle() {
	    if (gmattr == null) {
	      try {
	        gmattr =
	          Bundle.loadBundle(
	            UniqueID.parseUniqueID("gmattr"),
	            IRPersistent.fluidFileLocator);
	      } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error in loading \"gmattr\" bundle !");
	      }
	    }
	    return gmattr;
	  }
	  
	  public static void main(String[] args) {    
	    /*
	    try {
	      gmattr = new Bundle();
	      
	      gmattr.saveAttribute(name_si);
	      gmattr.saveAttribute(goalmodelchanged);
		  gmattr.saveAttribute(goalmodelpropertychanged);
	      gmattr.store(IRPersistent.fluidFileLocator);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    if (gmattr != null)
	      gmattr.describe(System.out);
	    */
	     
	    gmattr = getBundle();
	    System.out.println("Getting from disk ...");
	    if (gmattr != null) gmattr.describe(System.out);    
	    
	  }
}
