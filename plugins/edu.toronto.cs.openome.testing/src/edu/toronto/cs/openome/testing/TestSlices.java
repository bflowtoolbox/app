package edu.toronto.cs.openome.testing;

import static org.junit.Assert.*;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import edu.toronto.cs.openome_model.Intention;

public class TestSlices extends CreateExampleModel {
	/**
	 * Tests correctness of forward slice for KHP model
	 */
	@Test
	public void testForwardSlice() {
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
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room 2")) {
				UseCyber = i;
			}	
			if (i.getName().equals("Use Text Messaging 2")) {
				UseText2 = i;
			}
		}
		
		forwardSlice = ProvideOnline.getForwardSlice();
		System.out.println("Slice for Provide Online");
		for (Intention i : forwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardSlice.size() == 10);
		assertTrue(forwardSlice.contains(HelpAsMany));
		assertTrue(forwardSlice.contains(AvoidBurnout));
		assertTrue(forwardSlice.contains(Happiness));
		assertTrue(forwardSlice.contains(HighQuality));
		assertTrue(forwardSlice.contains(AvoidBurnout));
		assertTrue(forwardSlice.contains(Funds));
			
		assertTrue(!forwardSlice.contains(ProvideOnline));
		assertTrue(!forwardSlice.contains(Listen));
		assertTrue(!forwardSlice.contains(Immediacy));
		assertTrue(!forwardSlice.contains(UseCyber));
		
		forwardSlice = UseText2.getForwardSlice();
		System.out.println("Slice for Use Text 2");
		for (Intention i : forwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardSlice.size() == 8);
		assertTrue(forwardSlice.contains(Immediacy));
		assertTrue(forwardSlice.contains(Funds));
		
		forwardSlice = UseText.getForwardSlice();
		System.out.println("Slice for Use Text");
		for (Intention i : forwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardSlice.size() == 18);
		assertTrue(forwardSlice.contains(ProvideOnline));
		assertTrue(forwardSlice.contains(Listen));
		assertTrue(forwardSlice.contains(HighQuality));
		assertTrue(forwardSlice.contains(Happiness));
		assertTrue(forwardSlice.contains(HelpAsMany));
		assertTrue(forwardSlice.contains(AvoidBurnout));
		assertTrue(forwardSlice.contains(Immediacy));
		assertTrue(forwardSlice.contains(Funds));
		assertTrue(!forwardSlice.contains(UseCyber));
		
	}
	
	/**
	 * Tests correctness of backward slice for KHP model
	 */
	@Test
	public void testBackwardSlice() {
		initializeKHPTestModel();
		
		EList<Intention> intentions = model.getAllIntentions();
		EList<Intention> backwardSlice;
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
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room 2")) {
				UseCyber = i;
			}	
			if (i.getName().equals("Use Text Messaging 2")) {
				UseText2 = i;
			}
		}
		
		backwardSlice = Happiness.getBackwardSlice();
		System.out.println("Slice for Happiness");
		for (Intention i : backwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(backwardSlice.size() == 8);
		assertTrue(backwardSlice.contains(HelpAsMany));
		assertTrue(backwardSlice.contains(AvoidBurnout));
		assertTrue(backwardSlice.contains(Happiness));
		assertTrue(backwardSlice.contains(HighQuality));			
		assertTrue(backwardSlice.contains(ProvideOnline));
		assertTrue(backwardSlice.contains(Listen));
		assertTrue(backwardSlice.contains(Happiness));
		assertTrue(!backwardSlice.contains(Immediacy));
		assertTrue(!backwardSlice.contains(UseCyber));
		
		backwardSlice = UseText2.getBackwardSlice();
		System.out.println("Slice for Use Text 2");
		for (Intention i : backwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(backwardSlice.size() == 2);
		assertTrue(backwardSlice.contains(UseText));
		assertTrue(!backwardSlice.contains(ProvideOnline));
				
		backwardSlice = Funds.getBackwardSlice();
		System.out.println("Slice for Funds");
		for (Intention i : backwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(backwardSlice.size() == 17);
		assertTrue(backwardSlice.contains(ProvideOnline));
		assertTrue(backwardSlice.contains(Listen));
		assertTrue(backwardSlice.contains(HighQuality));
		assertTrue(backwardSlice.contains(Happiness));
		assertTrue(backwardSlice.contains(HelpAsMany));
		assertTrue(backwardSlice.contains(AvoidBurnout));
		assertTrue(backwardSlice.contains(Immediacy));
		assertTrue(!backwardSlice.contains(Funds));
		assertTrue(backwardSlice.contains(UseCyber));
		
	}

	/**
	 * Tests correctness of forward and backward slice for KHP model
	 */
	@Test
	public void testAllConnected() {
		initializeKHPTestModel();
		
		EList<Intention> intentions = model.getAllIntentions();
		EList<Intention> forwardbackwardSlice;
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
			if (i.getName().equals("Use Cyber Cafe/Portal/Chat Room 2")) {
				UseCyber = i;
			}	
			if (i.getName().equals("Use Text Messaging 2")) {
				UseText2 = i;
			}
		}
		
		forwardbackwardSlice = Happiness.getAllConnected();
		System.out.println("Slice for Happiness");
		for (Intention i : forwardbackwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardbackwardSlice.size() == 22);
		assertTrue(forwardbackwardSlice.contains(HelpAsMany));
		assertTrue(forwardbackwardSlice.contains(AvoidBurnout));
		assertTrue(forwardbackwardSlice.contains(Happiness));
		assertTrue(forwardbackwardSlice.contains(HighQuality));			
		assertTrue(forwardbackwardSlice.contains(ProvideOnline));
		assertTrue(forwardbackwardSlice.contains(Listen));
		assertTrue(forwardbackwardSlice.contains(Happiness));
		assertTrue(forwardbackwardSlice.contains(Immediacy));
		assertTrue(forwardbackwardSlice.contains(UseCyber));
		assertTrue(forwardbackwardSlice.contains(UseText));
		
		forwardbackwardSlice = UseText2.getAllConnected();
		System.out.println("Slice for Use Text 2");
		for (Intention i : forwardbackwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardbackwardSlice.size() == 22);
		assertTrue(forwardbackwardSlice.contains(UseText));
		assertTrue(forwardbackwardSlice.contains(ProvideOnline));
		assertTrue(forwardbackwardSlice.contains(Immediacy));
		assertTrue(forwardbackwardSlice.contains(Funds));
				
		forwardbackwardSlice = Funds.getAllConnected();
		System.out.println("Slice for Funds");
		for (Intention i : forwardbackwardSlice) {
			System.out.println(i.getName());
		}
		assertTrue(forwardbackwardSlice.size() == 22);
		assertTrue(forwardbackwardSlice.contains(ProvideOnline));
		assertTrue(forwardbackwardSlice.contains(Listen));
		assertTrue(forwardbackwardSlice.contains(HighQuality));
		assertTrue(forwardbackwardSlice.contains(Happiness));
		assertTrue(forwardbackwardSlice.contains(HelpAsMany));
		assertTrue(forwardbackwardSlice.contains(AvoidBurnout));
		assertTrue(forwardbackwardSlice.contains(Immediacy));
		assertTrue(forwardbackwardSlice.contains(Funds));
		assertTrue(forwardbackwardSlice.contains(UseCyber));
		
	}
}
