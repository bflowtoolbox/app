package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.sat4j.core.VecInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.Property;

public class DependencyLinkAxioms extends LinkAxioms {

	public DependencyLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @author jenhork
	 * Create the clauses for a Dependeny Link
	 * If a depends on b
	 * b is the source
	 * a is the target
	 * Forward:
	 * S(b) -> S(a)
	 * PS(b) -> PS(a)
	 * U(b) -> U(a)	 
	 * C(b) -> C(a)
	 * PD(b) -> PD(a)	 
	 * D(b) -> D(a)
	 * Backward:
	 * S(a) -> S(b)
	 * PS(a) -> PS(b)
	 * U(a) -> U(b)	 
	 * C(a) -> C(b)
	 * PD(a) -> PD(b)	 
	 * D(a) -> D(b)
	 */
	
	
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Dependency");
		findIndexes();
		
		//System.out.println(tIndex + " " + sourceIndexes.toString());
		
		int sIndex = sourceIndexes.last();
		
		int[] ints;
		
		//Forward:
		//S(b) -> S(a)
		//!S(b) or S(a)
		//PS(b) -> PS(a)
		//!PS(b) or PS(a)
		//U(b) -> U(a)	 
		//!U(b) or U(a)
		//C(b) -> C(a)
		//!C(b) or C(a)
		//PD(b) -> PD(a)	
		//!PD(b) or PD(a)
		//D(b) -> D(a)
		//!D(b) or D(a)
		for (int i = 0; i < 6; i++) {
			
			forwardClauses.addAll(addAndImplication(sIndex + i, tIndex + i));
			
		}
		
	}
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Dependency");		
		findIndexes();
		
		int sIndex = sourceIndexes.last();
		
		int[] ints;
		/*Backward
		* S(a) -> S(b)
		* PS(a) -> PS(b)
		* U(a) -> U(b)	 
		* C(a) -> C(b)
		* PD(a) -> PD(b)	 
		* D(a) -> D(b)
		*/
		for (int i = 0; i < 6; i++) {
			backwardClauses.addAll(addAndImplication(tIndex + i, sIndex + i));
		}
	}

	
	
	

}
