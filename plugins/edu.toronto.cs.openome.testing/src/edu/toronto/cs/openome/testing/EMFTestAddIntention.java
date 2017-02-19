package edu.toronto.cs.openome.testing;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

/**
 * Examines the case of adding an intention into the model
 * @author johan
 *
 */
public class EMFTestAddIntention {
	/*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	/*
	 * The main model we are using
	 * It may contains Actors, Dependencies, etc 
	 * and should support operations to add or delete them
	 */
	private static ModelImpl model = (ModelImpl) factory.createModel();
	
	/*
	 * Test the case of adding an intention to a model
	 * e.g. outside of an actor
	 */
	@SuppressWarnings("restriction")
	@Test
	public void testAddGoalToModel(){
		String name = "New Goal";
		
		//Create the Intention
		GoalImpl goal = (GoalImpl) factory.createGoal();
		goal.setName(name);
		
		//Add it
		assertFalse(model == null);
		model.getIntentions().clear();		
		assertTrue(model.getIntentions().isEmpty());
		
		model.getIntentions().add(goal);
		
		assertFalse(model.getIntentions().isEmpty());
		assertTrue(model.getIntentions().contains(goal));
		assertTrue(model.getIntentions().get(0).getName().equals(name));
	}
}