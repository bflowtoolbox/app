package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.Link;

public class HumanJudgmentLinkAxioms extends LinkAxioms {
	//private Intention intention;
	private LabelBag lb;

	public HumanJudgmentLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		
	}
	
	public void findIndexes() {
		//System.out.println("finding indexes");
		sourceIndexes = new VecInt();
		
		if (intentionMap != null) {
			//System.out.println("getting index");
			tIndex = (Integer) intentionMap.getInverse(target);
			//System.out.println("got index");
			if (lb.getLabelBagIntentions() == null) {
				System.out.println("label bag intentions null in find indexes");
			}
			else {
				for (Intention intn: lb.getLabelBagIntentions()) {
					Integer sourceIndex = (Integer) intentionMap.getInverse(intn);
					sourceIndexes.push(sourceIndex.intValue() + getLabelOffset(lb.getLabelBagEvalLabels().get(lb.getLabelBagIntentions().indexOf(intn))));	
				}
			}
			
		}
		else {
			System.out.println("intentionMap is null");
		}
	}
	
	public void createForwardClauses() {	
		//System.out.println("Creating forward clauses for HJ");
		
		findIndexes();
		
		int targetOffset = 0;
		/*for (HumanJudgement hj : wrapper.getHumanJudgements()) {
			if (hj.isEnabled()) {
				findIndexes(hj);
						
				EvaluationLabel targetLabel = hj.getJudgement();
		
				targetOffset = getLabelOffset(targetLabel);
		
				//AND of all judgements -> Target(e)
				forwardClauses.addAll(addAndImplication(sourceIndexes, tIndex + targetOffset));		
			}
		}*/
		
		EvaluationLabel targetLabel = target.getInitialEvalLabel();
		
		targetOffset = getLabelOffset(targetLabel);
		
		//AND of all judgements -> Target(e)
		forwardClauses.addAll(addAndImplication(sourceIndexes, tIndex + targetOffset));		
		
		addTargetRestrictions(targetOffset, forwardClauses);
		
		//addSourceRestrictions(lb, forwardClauses);
		
	}
	
	
	
	private void addTargetRestrictions(int i, Vector<VecInt> clauses) {
		VecInt vi; 
		switch (i) {
			case(0): {
				vi = new VecInt();
				vi.push(-(tIndex+2)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+3)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+4)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
			case(1): {
				vi = new VecInt();			
				vi.push(-(tIndex+2)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+3)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+4)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
			case(2): {
				vi = new VecInt();			
				vi.push(-(tIndex+1)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+3)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+4)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
			case(3): {
				vi = new VecInt();			
				vi.push(-(tIndex+1)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+2)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+4)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
			case(4): {
				vi = new VecInt();			
				vi.push(-(tIndex+1)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+2)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+3)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
			case(5): {
				vi = new VecInt();			
				vi.push(-(tIndex+1)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+2)); 
				vi.push(0); 
				clauses.add(vi);
				vi = new VecInt();
				vi.push(-(tIndex+3)); 
				vi.push(0); 
				clauses.add(vi); 
				break;
			}
		}
		
	}

	public void createBackwardClauses() {
		
		findIndexes();
		
		int targetOffset = 0;
		//System.out.println("Creating backward clauses for HJ");
		/*for (HumanJudgement hj : wrapper.getHumanJudgements()) {
			if (hj.isEnabled()) {			
				findIndexes(hj);
				
				EvaluationLabel targetLabel = wrapper.getInitialEvaluationLable();
			
				targetOffset = getLabelOffset(targetLabel);
			
				//Target(e)-> AND of all judgments
				backwardClauses.addAll(addAndImplication(tIndex + targetOffset, sourceIndexes));
				
				if (targetOffset == 0) {
					backwardClauses.addAll(addAndImplication(tIndex + 1, sourceIndexes));
				}
				
				if (targetOffset == 5) {
					backwardClauses.addAll(addAndImplication(tIndex + 4, sourceIndexes));
				}
			}
		}	*/	
		EvaluationLabel targetLabel = target.getInitialEvalLabel();
		
		targetOffset = getLabelOffset(targetLabel);
		
		//Target(e)-> AND of all judgments
		backwardClauses.addAll(addAndImplication(tIndex + targetOffset, sourceIndexes));
		
		if (targetOffset == 0) {
			backwardClauses.addAll(addAndImplication(tIndex + 1, sourceIndexes));
		}
		
		if (targetOffset == 5) {
			backwardClauses.addAll(addAndImplication(tIndex + 4, sourceIndexes));
		}
		
		addTargetRestrictions(targetOffset, backwardClauses);
		
		//addSourceRestrictions(lb, backwardClauses);
		
	}
	
	private void addSourceRestrictions(LabelBag lb2, Vector<VecInt> clauses) {
		VecInt vi;
		ListIterator<Intention> it = lb2.getLabelBagIntentions().listIterator();
		IteratorInt itr = sourceIndexes.iterator();
		//which itr to use??
		while (itr.hasNext()) {
			vi = new VecInt();
			vi.push(itr.next());
			vi.push(0);
			clauses.add(vi);
		}
	}

	private int getLabelOffset(EvaluationLabel targetLabel) {
		if (targetLabel == EvaluationLabel.SATISFIED)
			return 0;
		if (targetLabel == EvaluationLabel.PARTIALLY_SATISFIED)
			return 1;
		if (targetLabel == EvaluationLabel.UNKNOWN)
			return 2;
		if (targetLabel == EvaluationLabel.CONFLICT)
			return 3;
		if (targetLabel == EvaluationLabel.PARTIALLY_DENIED)
			return 4;
		if (targetLabel == EvaluationLabel.DENIED)
			return 5;
		return 0;
	}

	public void addLabelBag(LabelBag bag) {
		lb = bag;		
	}

}
