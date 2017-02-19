/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome_model.Intention;

/**
 * @author jenhork
 *
 */
public class TestIntentionImpl extends EvaluationTest{

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		EList<Intention> elist = model.getIntentions();
		
		assertTrue(!elist.isEmpty());
		
		assertTrue(elist.get(0).equals(elist.get(0)));
		assertTrue(elist.get(1).equals(elist.get(1)));
		assertTrue(elist.get(2).equals(elist.get(2)));
	}

}
