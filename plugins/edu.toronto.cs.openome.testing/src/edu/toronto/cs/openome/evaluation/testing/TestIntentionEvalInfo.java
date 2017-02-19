/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;

import static org.junit.Assert.*;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome.evaluation.commands.AddHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.AddReverseJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.AddToLabelBagCommand;
import edu.toronto.cs.openome.evaluation.commands.BacktrackReverseJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.RemoveHumanJudgmentCommand;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.impl.LabelBagImpl;

/**
 * @author jenhork
 *
 */
public class TestIntentionEvalInfo extends EvaluationTest {

	/**
	 * Test method for AddHumanJudgment
	 */
	@Test
	public void testAddHumanJudgment() {
		LabelBag lb1 = new LabelBagImpl();		
			
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		Intention i = (Intention) elist.get(4);
		
		assertTrue(i.getHumanJudgments().size() == 0);
					
		Command addToLB;
		addToLB = new AddToLabelBagCommand(i, elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		addToLB = new AddToLabelBagCommand(i, elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		addToLB = new AddToLabelBagCommand(i, elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		Command addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED, cs);
		cs.execute(addHJ);
		assertTrue(i.getHumanJudgments().size() == 1);
		
		addToLB = new AddToLabelBagCommand(i, elist.get(3), EvaluationLabel.CONFLICT);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED, cs);
		cs.execute(addHJ);
		assertTrue(i.getHumanJudgments().size() == 2);
		
		addToLB = new AddToLabelBagCommand(i, elist.get(3), EvaluationLabel.UNKNOWN);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.DENIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.getHumanJudgments().size() == 3);		
	}
	
	/**
	 * Test method for RemoveHumanJudgment
	 */
	@Test
	public void testRemoveHumanJudgment() {
		LabelBag lb1 = new LabelBagImpl();		
			
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		Intention i = (Intention) elist.get(4);
		assertTrue(i.getHumanJudgments().size() == 0);
					
		Command addToLB;
		addToLB = new AddToLabelBagCommand(i, elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		addToLB = new AddToLabelBagCommand(i, elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		addToLB = new AddToLabelBagCommand(i, elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		Command addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.getHumanJudgments().size() == 1);
		
		Command removeHJ = new RemoveHumanJudgmentCommand(i, i.getHumanJudgments().get(0));
		cs.execute(removeHJ);
		
		assertTrue(i.getHumanJudgments().size() == 0);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.getHumanJudgments().size() == 1);
		
		addToLB = new AddToLabelBagCommand(i, elist.get(3), EvaluationLabel.DENIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(i, elist.get(4), EvaluationLabel.CONFLICT);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.CONFLICT, cs);
		cs.execute(addHJ);
		
		assertTrue(i.getHumanJudgments().size() == 2);
		
		removeHJ = new RemoveHumanJudgmentCommand(i, i.getHumanJudgments().get(1));
		cs.execute(removeHJ);
		
		assertTrue(i.getHumanJudgments().size() == 1);		
	}
	
	
	
	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.IntQualIntentionWrapper#findExistingResult()}.
	 */
	@Test
	public void testFindExistingResult() {
		LabelBag lb1 = new LabelBagImpl();
						
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		Intention i = (Intention) elist.get(3);
			
		Command addToLB;
		addToLB = new AddToLabelBagCommand(i, elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(i, elist.get(1), EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		assertTrue(i.getLabelBag().size() == 2);
		
		Command addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.PARTIALLY_SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.getLabelBag().size() == 2);
		
		assertTrue(i.findExistingHumanJudgment().equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		//addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED);
		//cs.execute(addHJ);
		//i.addHumanJudgment(EvaluationLabel.SATISFIED);
		
		//assertTrue(i.findExistingHumanJudgment().equals(EvaluationLabel.SATISFIED));
		
		addToLB = new AddToLabelBagCommand(i, elist.get(2), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		
		assertTrue(i.findExistingHumanJudgment() == null);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.findExistingHumanJudgment().equals(EvaluationLabel.SATISFIED));
		
		addToLB = new AddToLabelBagCommand(i, elist.get(3), EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		assertTrue(i.findExistingHumanJudgment() == null);
		
		addHJ = new AddHumanJudgmentCommand(i, EvaluationLabel.DENIED, cs);
		cs.execute(addHJ);
		
		assertTrue(i.findExistingHumanJudgment().equals(EvaluationLabel.DENIED));
		
		addToLB = new AddToLabelBagCommand(i, elist.get(4), EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		assertTrue(i.findExistingHumanJudgment().equals(EvaluationLabel.DENIED));
		
		addToLB = new AddToLabelBagCommand(i, elist.get(5), EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		
		assertTrue(i.findExistingHumanJudgment() == null);
				
	}
	
	/**
	 * Test method for ReverseLabelBag
	 */
	@Test
	public void testReverseLabelBag() {
				
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		Intention a = (Intention) elist.get(0);
		Intention b = (Intention) elist.get(1);
		Intention c = (Intention) elist.get(2);
		Intention d = (Intention) elist.get(3);
		
		Command addToLB;
		addToLB = new AddToLabelBagCommand(a, b, EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(a, c, EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		Command addHJ = new AddHumanJudgmentCommand(a, EvaluationLabel.PARTIALLY_SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 1);
		assertTrue(c.getReverseLabelBag().size() == 1);
		
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		addToLB = new AddToLabelBagCommand(a, d, EvaluationLabel.CONFLICT);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(a, EvaluationLabel.CONFLICT, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 1);
		assertTrue(c.getReverseLabelBag().size() == 1);
		
		addToLB = new AddToLabelBagCommand(d, b, EvaluationLabel.SATISFIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(d, c, EvaluationLabel.DENIED);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(d, EvaluationLabel.CONFLICT, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 2);
		assertTrue(c.getReverseLabelBag().size() == 2);
		
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(1).equals(d));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(1).equals(EvaluationLabel.CONFLICT));
		
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(1).equals(d));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(1).equals(EvaluationLabel.CONFLICT));
		
	}
	
	/**
	 * Test method for BacktrackReverseJudgment
	 */
	@Test
	public void testBacktrackReverseJudgment() {
				
EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		Intention a = (Intention) elist.get(0);
		Intention b = (Intention) elist.get(1);
		Intention c = (Intention) elist.get(2);
		Intention d = (Intention) elist.get(3);
		
		Command addToLB;
		addToLB = new AddToLabelBagCommand(a, b, EvaluationLabel.PARTIALLY_SATISFIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(a, c, EvaluationLabel.PARTIALLY_DENIED);
		cs.execute(addToLB);
		
		Command addHJ = new AddHumanJudgmentCommand(a, EvaluationLabel.PARTIALLY_SATISFIED, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 1);
		assertTrue(c.getReverseLabelBag().size() == 1);
		
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		addToLB = new AddToLabelBagCommand(a, d, EvaluationLabel.CONFLICT);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(a, EvaluationLabel.CONFLICT, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 1);
		assertTrue(c.getReverseLabelBag().size() == 1);
		
		addToLB = new AddToLabelBagCommand(d, b, EvaluationLabel.SATISFIED);
		cs.execute(addToLB);
		
		addToLB = new AddToLabelBagCommand(d, c, EvaluationLabel.DENIED);
		cs.execute(addToLB);
		
		addHJ = new AddHumanJudgmentCommand(d, EvaluationLabel.CONFLICT, cs);
		cs.execute(addHJ);
		
		assertTrue(b.getReverseLabelBag().size() == 2);
		assertTrue(c.getReverseLabelBag().size() == 2);
		
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(1).equals(d));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(1).equals(EvaluationLabel.CONFLICT));
		
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(1).equals(d));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(1).equals(EvaluationLabel.CONFLICT));
		
		Command backtrack = new BacktrackReverseJudgmentCommand(d, d.getHumanJudgments().get(0));
		cs.execute(backtrack);
		
		assertTrue(b.getReverseLabelBag().size() == 1);
		assertTrue(c.getReverseLabelBag().size() == 1);
		
		assertTrue(b.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(b.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		assertTrue(c.getReverseLabelBag().getLabelBagIntentions().get(0).equals(a));
		assertTrue(c.getReverseLabelBag().getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		backtrack = new BacktrackReverseJudgmentCommand(a, a.getHumanJudgments().get(0));
		cs.execute(backtrack);
		
		assertTrue(b.getReverseLabelBag().size() == 0);
		assertTrue(c.getReverseLabelBag().size() == 0);
	}
}
