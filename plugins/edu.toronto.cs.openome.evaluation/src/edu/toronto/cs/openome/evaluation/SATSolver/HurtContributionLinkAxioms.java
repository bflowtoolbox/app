package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class HurtContributionLinkAxioms extends ContributionLinkAxioms {
	

	public HurtContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Hurt");
		//super.createForwardClauses();
		findIndexes();
		int sIndex = sourceIndexes.last();
		
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
		//System.out.println("Creating Backward Clauses for Hurt");
		//super.createBackwardClauses();
		/*findIndexes();
		int sIndex = sourceIndexes.last();
		
		//PS(ei) -> (D(e) or PD(e))
		VecInt vi = new VecInt();
		vi.push(sIndex + 4);
		vi.push(sIndex + 5);
		backwardClauses.addAll(addOrImplication(tIndex + 1, vi));
				
		//PD(ei) -> (PS(e) or S(e))
		vi = new VecInt();
		vi.push(sIndex);
		vi.push(sIndex + 1);
		backwardClauses.addAll(addOrImplication(tIndex + 4, vi));*/
		
	}

}
