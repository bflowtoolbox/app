package sc.document;

import java.util.Observable;
import java.util.Observer;

import fluid.ir.IRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.tree.SymmetricEdgeDigraph;
import fluid.version.VersionedUnitSlotInfo;

public class GoalModelChanged extends VersionedUnitSlotInfo implements Observer {

	  private final SymmetricEdgeDigraph graph;

	  public GoalModelChanged(SymmetricEdgeDigraph g)
	  	throws SlotAlreadyRegisteredException 
	  {
	  	super("goalmodelchanged");
	    graph = g;
	    g.addObserver(this);    
	  }

	  public GoalModelChanged(String name, SymmetricEdgeDigraph g) 
	    throws SlotAlreadyRegisteredException
	  {
	    super(name);
	    graph = g;
	    g.addObserver(this);
	    
	  }

	  /** Note a change at a node and thus at its ancestors. */
	  public void noteChange(IRNode node) {
	    super.setChanged(node);
	  }
	  
	  public void update(Observable obs, Object node) {    
	    noteChange((IRNode)node);
	  }
}
