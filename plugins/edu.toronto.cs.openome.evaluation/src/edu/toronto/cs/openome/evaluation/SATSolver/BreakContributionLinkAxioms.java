package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class BreakContributionLinkAxioms extends ContributionLinkAxioms {
	

	public BreakContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Break");
		//super.createForwardClauses();
		findIndexes();
		int sIndex = sourceIndexes.last();
		
		//Forward:
		//S(ei) -> D(e)
		forwardClauses.addAll(addAndImplication(sIndex, tIndex+5));
		
		//PS(ei) -> PD(e)
		forwardClauses.addAll(addAndImplication(sIndex +1, tIndex +4));
				
		//PD(ei) -> PS(e)
		forwardClauses.addAll(addAndImplication(sIndex +4, tIndex +1));
		
		//U(e1) -> U(e)
		forwardClauses.addAll(addAndImplication(sIndex+2, tIndex + 2));
		
		//C(e1) -> C(e)
		forwardClauses.addAll(addAndImplication(sIndex+3, tIndex + 3));
		
	}
	
	
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Break");
		//super.createBackwardClauses();
		/*findIndexes();
		int sIndex = sourceIndexes.last();
		
		//Backward:
		//S(ei) -> (D(e) or PD(e))
		VecInt vi = new VecInt();
		vi.push(sIndex + 4);
		vi.push(sIndex + 5);
		backwardClauses.addAll(addOrImplication(tIndex, vi));
		
		//PS(ei) -> PD(e)
		backwardClauses.addAll(addAndImplication(tIndex +1, sIndex +4));
				
		//PD(ei) -> PS(e)
		backwardClauses.addAll(addAndImplication(tIndex +4, sIndex +1));
		
		//D(ei) -> (S(e) or PS(e))
		vi = new VecInt();
		vi.push(sIndex);
		vi.push(sIndex +1);
		backwardClauses.addAll(addOrImplication(tIndex +5, vi));*/
	}

}
