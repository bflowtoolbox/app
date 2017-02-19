package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class MakeContributionLinkAxioms extends ContributionLinkAxioms {
	

	public MakeContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Make");
		//super.createForwardClauses();
		findIndexes();
		int sIndex = sourceIndexes.last();
		
		//Forward:
		//(For all i  and S(ei)) -> S(e)
		forwardClauses.addAll(addAndImplication(sIndex, tIndex));
		
		//PS(ei) -> PS(e)
		forwardClauses.addAll(addAndImplication(sIndex +1, tIndex +1));
				
		//PD(ei) -> PD(e)
		forwardClauses.addAll(addAndImplication(sIndex +4, tIndex +4));
		
		//D(ei) -> D(e)
		forwardClauses.addAll(addAndImplication(sIndex +5, tIndex +5));
		
		//U(e1) -> U(e)
		forwardClauses.addAll(addAndImplication(sIndex+2, tIndex + 2));
		
		//C(e1) -> C(e)
		forwardClauses.addAll(addAndImplication(sIndex+3, tIndex + 3));
	}
	
	
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Make");
		//super.createBackwardClauses();
		/*findIndexes();
		int sIndex = sourceIndexes.last();
		
		//Backward:
		//(For all i  and S(ei)) -> S(e)
		backwardClauses.addAll(addAndImplication(tIndex, sIndex));
		
		//PS(ei) -> PS(e)
		backwardClauses.addAll(addAndImplication(tIndex +1, sIndex +1));
				
		//PD(ei) -> PD(e)
		backwardClauses.addAll(addAndImplication(tIndex +4, sIndex +4));
		
		//D(ei) -> D(e)
		backwardClauses.addAll(addAndImplication(tIndex +5, sIndex +5));
		*/
	}

}
