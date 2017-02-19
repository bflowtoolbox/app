package edu.toronto.cs.openome.testing;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome_model.Intention;

public class TestLeavesAndRoots extends CreateExampleModel {
	/**
	 * Tests getLeaves method on KHP Model
	 */
	@Test
	public void testGetLeaves() {
		initializeKHPTestModel();
		
		EList<Intention> intentions = model.getAllIntentions();
		EList<Intention> forwardSlice;
		Intention ProvideOnline = null;
		Intention HelpAsMany = null;
		Intention AvoidBurnout = null;
		Intention Happiness = null;
		Intention HighQuality = null;
		Intention Listen = null;
		Intention UseText = null;
		Intention Immediacy = null;
		Intention Funds = null;
		Intention UseCyber = null;
		Intention UseCyber2 = null;
		Intention UseText2 = null;
		
		for (Intention i : intentions) {	
			//System.out.println(i.getName());
			if (i.getName().equals("Provide Online Counseling Services")) {
				ProvideOnline = i;
			}
			if (i.getName().equals("Help As Many Kids as Possible")) {
				HelpAsMany = i;
			}
			if (i.getName().equals("Avoid Burnout")) {
				AvoidBurnout = i;
			}
			if (i.getName().equals("Happiness [Counsellors]")) {
				Happiness = i;
			}
			if (i.getName().equals("High Quality Counselling")) {
				HighQuality = i;
			}
			if (i.getName().equals("Use Text Messaging")) {
				UseText = i;
			}
			if (i.getName().equals("Listen for Cues")) {
				Listen = i;
			}
			if (i.getName().equals("Immediacy [Services]")) {
				Immediacy = i;
			}
			if (i.getName().equals("Increase Funds")) {
				Funds = i;
			}
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room")) {
				UseCyber = i;
			}
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room 2")) {
				UseCyber2 = i;
			}	
			if (i.getName().equals("Use Text Messaging 2")) {
				UseText2 = i;
			}
		}
		
		EList<Intention> leaves = model.getLeaves();
		assertTrue(leaves.size() == 2);
		assertTrue(leaves.contains(UseText));
		assertTrue(leaves.contains(UseCyber));
		
	}
	
	/**
	 * Tests getLeaves method on KHP Model
	 */
	@Test
	public void testGetRoots() {
		initializeKHPTestModel();
		
		EList<Intention> intentions = model.getAllIntentions();
		EList<Intention> forwardSlice;
		Intention ProvideOnline = null;
		Intention HelpAsMany = null;
		Intention AvoidBurnout = null;
		Intention Happiness = null;
		Intention HighQuality = null;
		Intention Listen = null;
		Intention UseText = null;
		Intention Immediacy = null;
		Intention Funds = null;
		Intention UseCyber = null;
		Intention UseCyber2 = null;
		Intention UseText2 = null;
		
		for (Intention i : intentions) {	
			//System.out.println(i.getName());
			if (i.getName().equals("Provide Online Counseling Services")) {
				ProvideOnline = i;
			}
			if (i.getName().equals("Help As Many Kids as Possible")) {
				HelpAsMany = i;
			}
			if (i.getName().equals("Avoid Burnout")) {
				AvoidBurnout = i;
			}
			if (i.getName().equals("Happiness [Counsellors]")) {
				Happiness = i;
			}
			if (i.getName().equals("High Quality Counselling")) {
				HighQuality = i;
			}
			if (i.getName().equals("Use Text Messaging")) {
				UseText = i;
			}
			if (i.getName().equals("Listen for Cues")) {
				Listen = i;
			}
			if (i.getName().equals("Immediacy [Services]")) {
				Immediacy = i;
			}
			if (i.getName().equals("Increase Funds")) {
				Funds = i;
			}
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room")) {
				UseCyber = i;
			}
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room 2")) {
				UseCyber2 = i;
			}	
			if (i.getName().equals("Use Text Messaging 2")) {
				UseText2 = i;
			}
		}
		
		EList<Intention> roots = model.getRoots();
		for (Intention i : roots) {
			System.out.println(i.getName());
		}
		assertTrue(roots.size() == 2);
		
	}
}
