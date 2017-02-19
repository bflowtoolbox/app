package edu.toronto.cs.openome.evaluation.SATSolver;

import edu.toronto.cs.openome_model.Intention;

public class InvariantAxioms extends IntentionAxioms {

	public InvariantAxioms(Intention t, DualHashMap<Integer, Intention> dhm, String desc) {
		super(t, dhm, desc);
	}

	@Override
	public void createAllClauses() {
		findIndexes();
		
		//S(ei) -> PS(e)
		clauses.addAll(addAndImplication(tIndex, tIndex +1));
		
		//D(ei) -> PD(e)
		clauses.addAll(addAndImplication(tIndex+5, tIndex +4));

	}

}
