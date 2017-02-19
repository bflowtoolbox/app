package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class HelpContributionLinkAxioms extends ContributionLinkAxioms {
	

	public HelpContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Help");
		//super.createForwardClauses();
		findIndexes();
		int sIndex = sourceIndexes.last();
		
		//PS(ei) -> PS(e)
		forwardClauses.addAll(addAndImplication(sIndex +1, tIndex +1));
				
		//PD(ei) -> PD(e)
		forwardClauses.addAll(addAndImplication(sIndex +4, tIndex +4));
		
		//U(e1) -> U(e)
		forwardClauses.addAll(addAndImplication(sIndex+2, tIndex + 2));
		
		//C(e1) -> C(e)
		forwardClauses.addAll(addAndImplication(sIndex+3, tIndex + 3));
		
	}
	
	
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Help");
		//super.createBackwardClauses();
		/*findIndexes();
		int sIndex = sourceIndexes.last();
		
		//PS(ei) -> (PS(e) or S(e))
		VecInt vi = new VecInt();
		vi.push(sIndex);
		vi.push(sIndex + 1);
		backwardClauses.addAll(addOrImplication(tIndex +1, vi));
				
		//PD(ei) -> PD(e)
		vi = new VecInt();
		vi.push(sIndex + 4);
		vi.push(sIndex + 5);
		backwardClauses.addAll(addOrImplication(tIndex +4, vi));
		*/
		
	}

}
