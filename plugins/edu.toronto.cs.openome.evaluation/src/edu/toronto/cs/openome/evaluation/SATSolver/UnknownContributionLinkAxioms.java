package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class UnknownContributionLinkAxioms extends ContributionLinkAxioms {
	

	public UnknownContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Unknown");
		findIndexes();
		
		int sIndex = sourceIndexes.last();

		//anything(e1) -> U(e)
		for (int i = 0; i<6; i++) {
			forwardClauses.addAll(addAndImplication(sIndex + i, tIndex + 2));
		}
		
	}
	
	
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Unknown");
		/*findIndexes();
		
		int sIndex = sourceIndexes.last();

		//U(e) -> anything(e1)
		VecInt vi = new VecInt();
		for (int i = 0; i<6; i++) {
			vi.push(sIndex + i);
		}
		
		backwardClauses.addAll(addOrImplication(tIndex + 2, vi));*/
		
	}

}
