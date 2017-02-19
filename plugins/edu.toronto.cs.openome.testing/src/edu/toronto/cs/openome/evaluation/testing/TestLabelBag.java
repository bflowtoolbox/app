/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;

import static org.junit.Assert.*;

import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.impl.LabelBagImpl;

/**
 * @author jenhork
 *
 */
public class TestLabelBag extends EvaluationTest{

	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag#addToBag(edu.toronto.cs.openome_model.Intention, edu.toronto.cs.openome_model.EvaluationLabel)}.
	 */
	@Test
	public void testAddToBag() {
		LabelBag lb1 = new LabelBagImpl();
				
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		
		assertTrue(lb1.size() == 1);
		assertTrue(lb1.getLabelBagIntentions().get(0).equals((Intention) elist.get(0)));
		assertTrue(lb1.getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.DENIED);
		
		assertTrue(lb1.size() == 2);
		
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.UNKNOWN);
		
		assertTrue(lb1.size() == 2);
		
		lb1.addToLabelBag(elist.get(2), EvaluationLabel.SATISFIED);
		
		assertTrue(lb1.size() == 3);
		
	}

	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag#diff(edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag)}.
	 */
	@Test
	public void testDiff() {
		LabelBag lb1 = new LabelBagImpl();
		LabelBag lb2 = new LabelBagImpl();
		
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		
		LabelBag d = lb1.bagDiff(lb2);
		assertTrue(d.size() == 1);
		
		assertTrue(d.getLabelBagIntentions().get(0).equals((Intention) elist.get(0)));
		assertTrue(d.getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb1.addToLabelBag(elist.get(2), EvaluationLabel.SATISFIED);
		
		lb2.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(3), EvaluationLabel.CONFLICT);
				
		d = lb1.bagDiff(lb2);
		
		assertTrue(d.size() == 2);
		
		LabelBag lb = new LabelBagImpl();
		lb.addToLabelBag(elist.get(2), EvaluationLabel.SATISFIED);
		lb.addToLabelBag(elist.get(3), EvaluationLabel.CONFLICT);
		for (Intention i: d.getLabelBagIntentions())	{
			System.out.println(i.getName() + " " + d.getLabelBagEvalLabels().get(d.getLabelBagIntentions().indexOf(i)));
		}
		for (Intention i: lb.getLabelBagIntentions())	{
			System.out.println(i.getName() + " " + lb.getLabelBagEvalLabels().get(lb.getLabelBagIntentions().indexOf(i)));
		}
		assertTrue(d.equals(lb));
		
		LabelBag lb3 = new LabelBagImpl();
		LabelBag lb4 = new LabelBagImpl();
		
							
		lb3.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb3.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb3.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		lb4.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb4.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb4.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		d = lb3.bagDiff(lb4);
		
		assertTrue(d.size() == 0);
		
		lb4.addToLabelBag(elist.get(3), EvaluationLabel.PARTIALLY_SATISFIED);
		
		d = lb3.bagDiff(lb4);
		
		assertTrue(d.size() == 1);
		
		assertTrue(d.getLabelBagIntentions().get(0).equals((Intention) elist.get(3)));
		assertTrue(d.getLabelBagEvalLabels().get(0).equals(EvaluationLabel.PARTIALLY_SATISFIED));
		
		lb4.addToLabelBag(elist.get(4), EvaluationLabel.PARTIALLY_DENIED);
		
		d = lb3.bagDiff(lb4);
		
		assertTrue(d.size() == 2);
	}

	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag#isPositive()}.
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag#isNegative()}.
	 */
	@Test
	public void testBooleans() {
		LabelBag lb1 = new LabelBagImpl();
		LabelBag lb2 = new LabelBagImpl();
		LabelBag lb3 = new LabelBagImpl();
		LabelBag lb4 = new LabelBagImpl();
		
		
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
					
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		assertTrue(lb1.size() == 1);
		
		assertTrue(lb1.isAllPositive());
		assertTrue(!lb1.isAllNegative());
		assertTrue(!lb1.hasFullPositive());
		assertTrue(!lb1.hasFullNegative());
		assertTrue(!lb1.hasConflict());
		assertTrue(!lb1.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.SATISFIED);
		assertTrue(lb1.size() == 2);
		
		assertTrue(lb1.isAllPositive());
		assertTrue(!lb1.isAllNegative());
		assertTrue(lb1.hasFullPositive());
		assertTrue(!lb1.hasFullNegative());
		assertTrue(!lb1.hasConflict());
		assertTrue(!lb1.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb1.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		assertTrue(lb1.size() == 3);
		
		assertTrue(!lb1.isAllPositive());
		assertTrue(!lb1.isAllNegative());
		assertTrue(lb1.hasFullPositive());
		assertTrue(!lb1.hasFullNegative());
		assertTrue(!lb1.hasConflict());
		assertTrue(!lb1.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb1.addToLabelBag(elist.get(3), EvaluationLabel.UNKNOWN);
		assertTrue(lb1.size() == 4);
		
		assertTrue(!lb1.isAllPositive());
		assertTrue(!lb1.isAllNegative());
		assertTrue(lb1.hasFullPositive());
		assertTrue(!lb1.hasFullNegative());
		assertTrue(!lb1.hasConflict());
		assertTrue(lb1.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
				
		lb2.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_DENIED);
		
		assertTrue(!lb2.isAllPositive());
		assertTrue(lb2.isAllNegative());
		assertTrue(!lb2.hasFullPositive());
		assertTrue(!lb2.hasFullNegative());
		assertTrue(!lb2.hasConflict());
		assertTrue(!lb2.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb2.addToLabelBag(elist.get(1), EvaluationLabel.DENIED);
		
		assertTrue(!lb2.isAllPositive());
		assertTrue(lb2.isAllNegative());
		assertTrue(!lb2.hasFullPositive());
		assertTrue(lb2.hasFullNegative());
		assertTrue(!lb2.hasConflict());
		assertTrue(!lb2.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb2.addToLabelBag(elist.get(2), EvaluationLabel.CONFLICT);
		
		assertTrue(!lb2.isAllPositive());
		assertTrue(!lb2.isAllNegative());
		assertTrue(!lb2.hasFullPositive());
		assertTrue(lb2.hasFullNegative());
		assertTrue(lb2.hasConflict());
		assertTrue(!lb2.hasUnknown());
		assertTrue(!lb1.isAllUnknown());
		assertTrue(!lb1.isAllConflict());
		
		lb3.addToLabelBag(elist.get(0), EvaluationLabel.CONFLICT);
		lb3.addToLabelBag(elist.get(1), EvaluationLabel.CONFLICT);
		
		assertTrue(!lb3.isAllPositive());
		assertTrue(!lb3.isAllNegative());
		assertTrue(!lb3.hasFullPositive());
		assertTrue(!lb3.hasFullNegative());
		assertTrue(lb3.hasConflict());
		assertTrue(!lb3.hasUnknown());
		assertTrue(!lb3.isAllUnknown());
		assertTrue(lb3.isAllConflict());
				
		lb4.addToLabelBag(elist.get(0), EvaluationLabel.UNKNOWN);
		lb4.addToLabelBag(elist.get(1), EvaluationLabel.UNKNOWN);
		lb4.addToLabelBag(elist.get(2), EvaluationLabel.UNKNOWN);
		
		assertTrue(!lb4.isAllPositive());
		assertTrue(!lb4.isAllNegative());
		assertTrue(!lb4.hasFullPositive());
		assertTrue(!lb4.hasFullNegative());
		assertTrue(!lb4.hasConflict());
		assertTrue(lb4.hasUnknown());
		assertTrue(lb4.isAllUnknown());
		assertTrue(!lb4.isAllConflict());
		
	}	

	
	
	/**
	 * Test method for {@link edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag#equals(edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.LabelBag)}.
	 */
	@Test
	public void testEqualsLabelBag() {
		LabelBag lb1 = new LabelBagImpl();
		LabelBag lb2 = new LabelBagImpl();
		
		
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
					
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb1.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb1.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		lb2.addToLabelBag(elist.get(0), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(1), EvaluationLabel.PARTIALLY_SATISFIED);
		lb2.addToLabelBag(elist.get(2), EvaluationLabel.PARTIALLY_DENIED);
		
		assert(lb1.equals(lb2));
		assert(lb2.equals(lb1));
		
		lb1.addToLabelBag(elist.get(0), EvaluationLabel.CONFLICT);
		
		assert(!lb1.equals(lb2));
		assert(!lb2.equals(lb1));
		
		lb2.addToLabelBag(elist.get(0), EvaluationLabel.CONFLICT);
		
		assert(lb1.equals(lb2));
		assert(lb2.equals(lb1));
		
		lb2.addToLabelBag(elist.get(3), EvaluationLabel.UNKNOWN);
		
		assert(!lb1.equals(lb2));
		assert(!lb2.equals(lb1));
	}

}
