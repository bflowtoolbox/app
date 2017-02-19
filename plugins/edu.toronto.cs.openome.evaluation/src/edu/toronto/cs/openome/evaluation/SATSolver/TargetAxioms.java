package edu.toronto.cs.openome.evaluation.SATSolver;

import org.sat4j.core.VecInt;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;

public class TargetAxioms extends IntentionAxioms {

	public TargetAxioms(Intention t, DualHashMap<Integer, Intention> dhm, String desc) {
		super(t, dhm, desc);
	}

	@Override
	public void createAllClauses() {
		findIndexes();
		
		VecInt vi = new VecInt();
		
		EvaluationLabel label = target.getQualitativeReasoningCombinedLabel();
		if (label == EvaluationLabel.SATISFIED) {				
			vi.push(tIndex);
		}
			
		if (label == EvaluationLabel.PARTIALLY_SATISFIED) {
			
			vi.push(tIndex+1);
		}
		if (label == EvaluationLabel.UNKNOWN) {
			vi.push(tIndex+2);
		}
			
		if (label == EvaluationLabel.CONFLICT) {
			vi.push(tIndex+3);
		}
		if (label == EvaluationLabel.PARTIALLY_DENIED) {
			vi.push(tIndex+4);
		}
		if (label == EvaluationLabel.DENIED) {
			vi.push(tIndex+5);
		}
		
		vi.push(0);			
		clauses.add(vi);	

	}
	
	public void createBackwardClauses() {
		findIndexes();
		
		VecInt vi = new VecInt();
		
		EvaluationLabel label = target.getQualitativeReasoningCombinedLabel();
		if (label == EvaluationLabel.SATISFIED) {				
			int [] indexes = {tIndex, -(tIndex + 2), -(tIndex + 3), -(tIndex + 4)};
			addClauses(indexes);
			//vi.push(tIndex);
		}
			
		if (label == EvaluationLabel.PARTIALLY_SATISFIED) {
			int [] indexes = {tIndex+1, -(tIndex + 2), -(tIndex + 3), -(tIndex + 4)};
			addClauses(indexes);
			//vi.push(tIndex+1);
		}
		if (label == EvaluationLabel.UNKNOWN) {
			int [] indexes = {tIndex +2, -(tIndex + 1), -(tIndex + 3), -(tIndex + 4)};
			addClauses(indexes);
		}
			
		if (label == EvaluationLabel.CONFLICT) {
			int [] indexes = {tIndex +3, -(tIndex + 1), -(tIndex + 2), -(tIndex + 4)};
			addClauses(indexes);
		}
		if (label == EvaluationLabel.PARTIALLY_DENIED) {
			int [] indexes = {tIndex +4, -(tIndex + 1), -(tIndex + 2), -(tIndex + 3)};
			addClauses(indexes);
		}
		if (label == EvaluationLabel.DENIED) {
			int [] indexes = {tIndex +5, -(tIndex + 1), -(tIndex + 2), -(tIndex + 3)};
			addClauses(indexes);
		}
	}

	private void addClauses(int[] indexes) {
		VecInt vi;
		for (int i: indexes) {
			vi = new VecInt();
			vi.push(i);
			vi.push(0);			
			clauses.add(vi);
		}
	}

}
