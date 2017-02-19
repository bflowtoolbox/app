package sc.document;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRPersistent;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.SymmetricEdgeDigraph;
import fluid.util.UniqueID;
import fluid.version.VersionedSlotFactory;

public class GoalModelGraphBundle {

	  public static final SymmetricEdgeDigraph graph;
    
	  static {
	    try {   
	      graph = new SymmetricEdgeDigraph("GoalModel", VersionedSlotFactory.prototype);
	    } catch (SlotAlreadyRegisteredException e) {
	      throw new FluidError("slot already registered exception : " + e);
	    }
	  }
	  
	  private static Bundle goalmodelgraphbundle = null;
	  public static Bundle getBundle() {
	    if (goalmodelgraphbundle == null) {
	      try {
	        goalmodelgraphbundle =
	          Bundle.loadBundle(
	            UniqueID.parseUniqueID("gmgraph"),
	            IRPersistent.fluidFileLocator);
	      } catch (Throwable t) {
	        System.out.println("Error in loading \"gmgraph\" bundle !");
	      }
	    }
	    return goalmodelgraphbundle;
	  }
	  
	  public static void main(String[] args) {
	    /*
	    Bundle b = new Bundle();
	    graph.saveAttributes(b);
	    
	    try {
	      b.store(IRPersistent.fluidFileLocator);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    if (b != null)
	      b.describe(System.out);
	    */
	    
		
	    Bundle b = getBundle();
	    System.out.println("Getting from disk ...");
	    if (b != null)
	      b.describe(System.out);
	    
	  }
}
