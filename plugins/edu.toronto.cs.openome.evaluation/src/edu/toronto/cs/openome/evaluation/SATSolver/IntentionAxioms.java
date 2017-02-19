package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public abstract class IntentionAxioms extends Axioms {
	
	protected Intention target;
	protected Vector<VecInt> clauses;
	protected int tIndex;

	public IntentionAxioms(Intention t, DualHashMap<Integer, Intention> dhm, String desc) {
		super(dhm, desc);
		target = t;
		clauses = new Vector<VecInt>();
	}

	@Override
	abstract public void createAllClauses();
	
	public boolean containsForward(VecInt vi) {
		return false;
	}
	
	public boolean containsBackward(VecInt vi) {
		return false;
	}
	
	public boolean containsLinks(Vector<Link> links) {
		return false;
	}
	
	public boolean contains(VecInt vi) {
		for (VecInt vi2 : clauses) {
			if (equalsVecInt(vi, vi2)) {
				return true;
			}
				
		}
		return false;
	}

	@Override
	protected void findIndexes() {
		Integer intTIndex = (Integer) intentionMap.getInverse(target);
		
		tIndex = intTIndex.intValue();
	}

	@Override
	public Vector<String> getClauses() {
		Vector<String> strClauses = new Vector<String>();
		
		for (VecInt vi : clauses)  {
			String str = "";
			IteratorInt it = vi.iterator();
			while (it.hasNext()) {			
				str += String.valueOf(it.next()) + " ";				
			}
			
			strClauses.add(str);
		}
						
		return strClauses;
	}

	@Override
	public int getNumClauses() {
		if (clauses != null)
			return clauses.size();
		else
			return 0;
	}
	
	
	@Override
	public int getNumVars() {
		return intentionMap.size() * 6;
	}
	
	public VecInt getClauseByIndex(int index) {
		int numClause = getNumClauses();
		
		for (int i = 0; i< numClause; i++) {
			 if ((clauseIndex + i) == index) {
				return clauses.get(i);				
			 }
		}
		
		return null;
	}

	public void createLeafClauses() {
		// TODO Auto-generated method stub
		
	}

}
