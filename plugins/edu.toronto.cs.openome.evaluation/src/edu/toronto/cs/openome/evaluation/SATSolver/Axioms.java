package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.UnknownContribution;

public abstract class Axioms {
	
	protected DualHashMap<Integer, Intention> intentionMap;
	protected String description;
	protected boolean enabled;
	protected int clauseIndex;
	
	public Axioms(DualHashMap<Integer, Intention> dhm, String desc) {
			intentionMap = dhm;			
			description = desc;
			enabled = true;
			clauseIndex = -1;
	}	
	
	abstract public  void  createAllClauses();
	
	abstract protected void findIndexes();
	
	abstract public int getNumClauses();
	
	abstract public int getNumVars();
	
	abstract public boolean contains(VecInt vi);
	
	abstract public boolean containsForward(VecInt vi);
	
	abstract public boolean containsBackward(VecInt vi);
	
	abstract public Vector<String> getClauses();
	
	abstract public boolean containsLinks(Vector<Link> links);
	
	protected Vector<VecInt> addAndImplication(VecInt ind1, int ind2) {
		VecInt output = new VecInt(ind1.size() + 2);
		IteratorInt itr = ind1.iterator();
		while (itr.hasNext()) {
			output.push(itr.next() * -1);
		}		
		output.push(ind2);
		output.push(0);
		Vector<VecInt> v = new Vector<VecInt>();
		v.add(output);
		
		return v;
	}
	
	protected Vector<VecInt> addAndImplication(int ind1, VecInt ind2) {
		Vector<VecInt> v = new Vector<VecInt>();
		VecInt output;
		IteratorInt itr = ind2.iterator();
		while (itr.hasNext()) {
			output = new VecInt(2);
			output.push(ind1 * -1);
			output.push(itr.next());
			output.push(0);
			v.add(output);
		}
		
		return simplify(v);
	}
	
	protected Vector<VecInt> addAndImplication(int ind1, int ind2) {
		VecInt output = new VecInt(3);
		output.push(ind1 * -1);	
		output.push(ind2);
		output.push(0);
		Vector<VecInt> v = new Vector<VecInt>();
		v.add(output);
		return v;
	}
	
	protected Vector<VecInt> addOrImplication(int ind, VecInt ind2) {
		Vector<VecInt> v = new Vector<VecInt>();
		VecInt vi = new VecInt();
		vi.push(ind * -1);
		IteratorInt it = ind2.iterator();
		while (it.hasNext()) {
			vi.push(it.next());
		}
		vi.push(0);
		v.add(vi);
		return v;
	}
	
	protected Vector<VecInt> addOrImplication(VecInt ind, int ind2) {
		Vector<VecInt> v = new Vector<VecInt>();
		IteratorInt it = ind.iterator();
		while (it.hasNext()) {
			v.addAll(addAndImplication(it.next(), ind2));
		}
		return simplify(v);
	}
	
	protected VecInt incrementAll(VecInt input, int inc) {
		VecInt output = new VecInt(input.size());
		IteratorInt itr = input.iterator();
		while (itr.hasNext()) {
			output.push(itr.next() + inc);
		}
		return output;
	}	
	
	//ignores order
	public boolean equalsVecInt(VecInt vi1, VecInt vi2) {
		//System.out.println(vi1.toString() + " " + vi2.toString());
		boolean answer = false;
		if (vi1.size() == vi2.size()) {
			for (int i = 0; i<vi1.size();i++) {
				for (int j = 0; j< vi2.size(); j++) {
					if (vi1.get(i) == vi2.get(j))
						answer = true;
				}				
				if (!answer) {
					//System.out.println("false");
					return answer;
				}
			}
		}
		//System.out.println(answer);
		return answer;	
		
	}	
	
	public Vector<VecInt> simplify(Vector<VecInt> input) {
		return input;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void enable() {
		enabled = true;
		//System.out.println("Axiom enabled: " + description);
	}
	
	public void disable() {
		enabled = false;
		//System.out.println("Axiom disabled: " + description);
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setClauseIndex(int count) {
		clauseIndex = count;		
	}
	
	public int getClauseIndex() {
		return clauseIndex;
	}

	public abstract VecInt getClauseByIndex(int index);


	

	
}
