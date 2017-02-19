/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.impl.HumanJudgmentImpl;
import edu.toronto.cs.openome_model.impl.LabelBagImpl;

/**
 * @author jenhork
 *
 */
public class TestHumanJudgement extends EvaluationTest {
	
	
	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.HumanJudgement#findOrImplies(edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag)}.
	 */
	@Test
	public void testFindOrImplies() {
		LabelBag lb1 = new LabelBagImpl();
		LabelBag lb2 = new LabelBagImpl();
		LabelBag lb3 = new LabelBagImpl();
		LabelBag lb4 = new LabelBagImpl();
		
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
					
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb1.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		lb2.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		HumanJudgment hj1 = new HumanJudgmentImpl(lb1, EvaluationLabel.SATISFIED);
		
		assertTrue(hj1.findOrImplies(lb2).getResultLabel().equals(EvaluationLabel.SATISFIED));
		
		HumanJudgment hj2 = new HumanJudgmentImpl(lb2, EvaluationLabel.SATISFIED);
		
		assertTrue(hj2.findOrImplies(lb1).getResultLabel().equals(EvaluationLabel.SATISFIED));
		
		lb2.addToLabelBag(elist.get(3), EvaluationLabel.PARTIALLY_SATISFIED);
		
		HumanJudgment hj3 = new HumanJudgmentImpl(lb2, EvaluationLabel.SATISFIED);
		
		assertTrue(hj1.findOrImplies(lb2).getResultLabel().equals(EvaluationLabel.SATISFIED));
		
		//lb1.printBag();
		//lb2.printBag();
		assertTrue(hj3.findOrImplies(lb1) == null);
		
		lb2.addToLabelBag(elist.get(4), EvaluationLabel.PARTIALLY_DENIED);
		
		//lb1.printBag();
		//lb2.printBag();
		
		//if (r == null)
		//	System.out.println("null");
		//else System.out.println(r.getName());
		
		assertTrue(hj1.findOrImplies(lb2) == null);
		
		//because the bag is copied
		/*for (EvaluationLabel l : hj3.getLabelBag().getLabelBagEvalLabels())
			System.out.println(l.getName());
		System.out.println("---");
		for (EvaluationLabel l: lb1.getLabelBagEvalLabels())
			System.out.println(l.getName());
		System.out.println(hj3.findOrImplies(lb1).getResultLabel().getName());*/
		assertTrue(hj3.findOrImplies(lb1) == null);
		
		HumanJudgment hja = new HumanJudgmentImpl(lb2, EvaluationLabel.SATISFIED);
		
		assertTrue(hja.findOrImplies(lb1) == null);
		
		lb3.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_DENIED);
		lb3.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_DENIED);
		lb3.addToLabelBag(elist.get(2), EvaluationLabel.CONFLICT);
		
		lb4.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_DENIED);
		lb4.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_DENIED);
		lb4.addToLabelBag(elist.get(2), EvaluationLabel.CONFLICT);
		
		HumanJudgment hj4 = new HumanJudgmentImpl(lb3, EvaluationLabel.DENIED);
		
		assertTrue(hj4.findOrImplies(lb4).getResultLabel().equals(EvaluationLabel.DENIED));
		
		HumanJudgment hj5 = new HumanJudgmentImpl(lb4, EvaluationLabel.DENIED);
		
		assertTrue(hj5.findOrImplies(lb3).getResultLabel().equals(EvaluationLabel.DENIED));
		
		lb3.addToLabelBag(elist.get(3), EvaluationLabel.DENIED);
		lb3.addToLabelBag(elist.get(4), EvaluationLabel.DENIED);
		
		assertTrue(hj4.findOrImplies(lb3).getResultLabel().equals(EvaluationLabel.DENIED));
		
		HumanJudgment hj6 = new HumanJudgmentImpl(lb3, EvaluationLabel.DENIED);
		
		assertTrue(hj6.findOrImplies(lb4) == null);		
	}
	
	

}
