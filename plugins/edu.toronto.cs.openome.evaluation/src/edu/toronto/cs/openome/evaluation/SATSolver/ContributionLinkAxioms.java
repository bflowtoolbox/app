package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Collection;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.SomeMinusContribution;
import edu.toronto.cs.openome_model.SomePlusContribution;
import edu.toronto.cs.openome_model.UnknownContribution;

public class ContributionLinkAxioms extends LinkAxioms {
	protected VecInt posSourceIndexes;
	protected VecInt negSourceIndexes;

	public ContributionLinkAxioms(Vector<Intention> sources, Intention targ,
			Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(sources, targ, l, dhm, desc);
		negSourceIndexes = null;
		posSourceIndexes = null;
	}
		
	public void createForwardClauses() {
		//System.out.println("Creating Forward Clauses for Contribution");
		//findIndexes();
		//System.out.println("found indexes");
		
		//if (intentionMap != null) {
		//	intentionMap.print();
		//}
		//else {
		//	System.out.println("map is null");
		//}
		//System.out.println(sourceIndexes.toString());
		
		
		LinkAxioms la = null;
		AxiomsFactory axiomsFactory = new AxiomsFactory();
		
		for (Link l: links) {
			Contribution cont = (Contribution) l;
			//System.out.println("contribution: " + cont.toString());
			
			Vector<Intention> vInt = new Vector<Intention>();
			vInt.add(cont.getSource());
			
			if (cont instanceof MakeContribution) 
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Make", intentionMap, description);
			if (cont instanceof HelpContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Help", intentionMap, description);
			if (cont instanceof SomePlusContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Help", intentionMap, description);
			if (cont instanceof UnknownContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Unknown", intentionMap, description);
			if (cont instanceof SomeMinusContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Hurt", intentionMap, description);
			if (cont instanceof HurtContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Hurt", intentionMap, description);
			if (cont instanceof BreakContribution)
				la = axiomsFactory.createLinkAxiom(vInt, target, links, "Break", intentionMap, description);
			
			if (la == null)  {
				System.out.println("Link type error");
			}
			else {
				la.createForwardClauses();
				forwardClauses.addAll(la.forwardClauses);
			}
		}
		
	}
	
	public void createBackwardClauses() {
		//System.out.println("Creating Backward Clauses for Contribution");
		findIndexes();
		
		findPosNegIndexes();
		
		VecInt posInc;		
		VecInt negInc;
		VecInt combine;
		
		//S(e)->at least one S(ei) for pos links or at least one D(ek) for neg links
		
		posInc = incrementAll(posSourceIndexes, 0);		
		negInc = incrementAll(negSourceIndexes, 5);
		
		combine = new VecInt();
		combine.pushAll(posInc);
		combine.pushAll(negInc);
		
		backwardClauses.addAll(addOrImplication(tIndex, combine));
		
		//PS(e)->at least one PS(ei) for pos links or at least one PD(ek) for neg links
		
		posInc = incrementAll(posSourceIndexes, 1);		
		negInc = incrementAll(negSourceIndexes, 4);
		
		combine = new VecInt();
		combine.pushAll(posInc);
		combine.pushAll(negInc);
		
		backwardClauses.addAll(addOrImplication(tIndex + 1, combine));

		//U(e)-> at least one U(ei)
		VecInt vi = incrementAll(sourceIndexes, 2);
		backwardClauses.addAll(addOrImplication(tIndex + 2, vi));
		
		//* I must have lost my mind when I did this, I think this is doing backward conflict clauses, not unknown!
		//addBackwardUnknownClauses();		
		//I'm going to add it back, but a simplified version, with the right name
		// C(e)-> at least one C(ei) for all links
		VecInt sICon = incrementAll(sourceIndexes, 3);
		backwardClauses.addAll(addOrImplication(tIndex + 3, sICon));
		
		//PD(e)->at least one PD(ei) for pos links or at least one PS(ek) for neg links		
		posInc = incrementAll(posSourceIndexes, 4);		
		negInc = incrementAll(negSourceIndexes, 1);
		
		combine = new VecInt();
		combine.pushAll(posInc);
		combine.pushAll(negInc);
		
		backwardClauses.addAll(addOrImplication(tIndex + 4, combine));
		
		//D(e)->at least one D(ei) for pos links or at least one S(ek) for neg links		
		posInc = incrementAll(posSourceIndexes, 5);		
		negInc = incrementAll(negSourceIndexes, 0);
		
		combine = new VecInt();
		combine.pushAll(posInc);
		combine.pushAll(negInc);
		
		backwardClauses.addAll(addOrImplication(tIndex + 5, combine));
		
		/*!!! Missing C-> */
		
	}
	//* I must have lost my mind when I did this, I think this is doing backward conflict clauses, not unknown!
	private void addBackwardUnknownClauses() {
		//C(e) ->
		//For all ei Or C(ei) 
		//OR one pos link is PS(ei) AND one neg link is PS(ek)
		//OR one pos link is PD(ei) AND one neg link is PD(ek)
		//C(e) -> r+ (p.q) + (x.y)
		// = not C(e) + r + p + x
		// not C(e) + r + p + y
		// not C(e) + r + q + x
		// not C(e) + r + q + y
		VecInt r = new VecInt();
		VecInt p = new VecInt();
		VecInt q = new VecInt();
		VecInt x = new VecInt();
		VecInt y = new VecInt();
		
		r = incrementAll(sourceIndexes, 3);		
		p = incrementAll(posSourceIndexes, 1);
		q = incrementAll(negSourceIndexes, 4);
		x = incrementAll(posSourceIndexes, 4);
		y = incrementAll(negSourceIndexes, 1);
		
		VecInt vi;
		
		if (negSourceIndexes.size() == 0)  {
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(p);
			vi.pushAll(x);
			vi.push(0);
			
			backwardClauses.add(vi);
		}
		else if (posSourceIndexes.size() == 0)  {
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(q);
			vi.pushAll(y);
			vi.push(0);
			
			backwardClauses.add(vi);
		} else {		
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(p);
			vi.pushAll(x);
			vi.push(0);
			
			backwardClauses.add(vi);
			
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(p);
			vi.pushAll(y);
			vi.push(0);
			
			backwardClauses.add(vi);
			
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(q);
			vi.pushAll(x);
			vi.push(0);
			
			backwardClauses.add(vi);
		
			vi = new VecInt();
			vi.push((tIndex + 3) * -1);
			vi.pushAll(r);
			vi.pushAll(q);
			vi.pushAll(y);
			vi.push(0);
			
			backwardClauses.add(vi);
		}
		
	}



	private void findPosNegIndexes() {
		//System.out.println("finding indexes");
		
			//System.out.println("sourceIndexes was null");
			posSourceIndexes = new VecInt();
			negSourceIndexes = new VecInt();
			
			if (intentionMap != null) {
				//System.out.println("intentionMap was not null");
								
				for (Link l : links) {
					Contribution cont = (Contribution) l;
					
					Intention sInt = cont.getSource();
					Integer sourceIndex = (Integer) intentionMap.getInverse(sInt);
					
					if ((cont instanceof MakeContribution) | (cont instanceof HelpContribution) | (cont instanceof SomePlusContribution)) 
						posSourceIndexes.push(sourceIndex);
					else if ((cont instanceof HurtContribution) | (cont instanceof BreakContribution) | (cont instanceof SomeMinusContribution)) 
						negSourceIndexes.push(sourceIndex);
		
				}
			}
			else {
				//System.out.println("intentionMap is  null");
			}
	}
		
		

}
