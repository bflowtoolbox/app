package edu.toronto.cs.openome.testing;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;
import junit.framework.*;

/**
 * A Test that examines the case of adding Actors, Agents, Roles, and Positions onto a model
 * @author johan
 *
 */
public class EMFTestAddActor {
	
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
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddActor(){
		String name = "New Actor";
		
		// Create the actor
		ActorImpl actor = (ActorImpl) factory.createActor();
		actor.setName(name);
		
		// Add it
		assertFalse(model == null);
		assertTrue(model.getContainers().isEmpty());
		
		model.getContainers().add(actor);
		
		assertFalse(model.getContainers().isEmpty());
		assertTrue(model.getContainers().contains(actor));
		assertTrue(model.getContainers().size() == 1);
		assertTrue(model.getContainers().get(0).getName().equals(name));
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedActorIsEmpty(){
		ActorImpl actor = (ActorImpl) model.getContainers().get(0);
		assertTrue(actor.getIntentions().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedActorIsNotLinked(){
		ActorImpl actor = (ActorImpl) model.getContainers().get(0);
		assertTrue(actor.getAssociationFrom().isEmpty());
		assertTrue(actor.getAssociationTo().isEmpty());
		assertTrue(actor.getDependencyFrom().isEmpty());
		assertTrue(actor.getDependencyTo().isEmpty());
		assertTrue(actor.getIs_a().isEmpty());
		assertTrue(actor.getIs_part_of().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddAgent(){
		String name = "New Agent";
		
		// Create the actor
		AgentImpl agent = (AgentImpl) factory.createAgent();
		agent.setName(name);
		
		// Add it
		assertFalse(model == null);
		
		model.getContainers().clear();
		assertTrue(model.getContainers().isEmpty());
		
		model.getContainers().add(agent);
		
		assertFalse(model.getContainers().isEmpty());
		assertTrue(model.getContainers().contains(agent));
		assertTrue(model.getContainers().size() == 1);
		assertTrue(model.getContainers().get(0).getName().equals(name));
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedAgentIsNotLinked(){
		AgentImpl agent = (AgentImpl) model.getContainers().get(0);
		assertTrue(agent.getAssociationFrom().isEmpty());
		assertTrue(agent.getAssociationTo().isEmpty());
		assertTrue(agent.getDependencyFrom().isEmpty());
		assertTrue(agent.getDependencyTo().isEmpty());
		assertTrue(agent.getOccupies().isEmpty());
		assertTrue(agent.getPlays().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedAgentIsEmpty(){
		AgentImpl agent = (AgentImpl) model.getContainers().get(0);
		assertTrue(agent.getIntentions().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddRole(){
		String name = "New Role";
		
		// Create the actor
		RoleImpl role = (RoleImpl) factory.createRole();
		role.setName(name);
		
		// Add it
		assertFalse(model == null);
		
		model.getContainers().clear();
		assertTrue(model.getContainers().isEmpty());
		
		model.getContainers().add(role);
		
		assertFalse(model.getContainers().isEmpty());
		assertTrue(model.getContainers().contains(role));
		assertTrue(model.getContainers().size() == 1);
		assertTrue(model.getContainers().get(0).getName().equals(name));
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedRoleIsEmpty(){
		RoleImpl role = (RoleImpl) model.getContainers().get(0);
		assertTrue(role.getIntentions().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedRoleIsNotLinked(){
		RoleImpl role = (RoleImpl) model.getContainers().get(0);
		assertTrue(role.getAssociationFrom().isEmpty());
		assertTrue(role.getAssociationTo().isEmpty());
		assertTrue(role.getDependencyFrom().isEmpty());
		assertTrue(role.getDependencyTo().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddPosition(){
		String name = "New Position";
		
		// Create the actor
		PositionImpl position = (PositionImpl) factory.createPosition();
		position.setName(name);
		
		// Add it
		assertFalse(model == null);
		
		model.getContainers().clear();
		assertTrue(model.getContainers().isEmpty());
		
		model.getContainers().add(position);
		
		assertFalse(model.getContainers().isEmpty());
		assertTrue(model.getContainers().contains(position));
		assertTrue(model.getContainers().size() == 1);
		assertTrue(model.getContainers().get(0).getName().equals(name));
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedPositionIsEmpty(){
		PositionImpl position = (PositionImpl) model.getContainers().get(0);
		assertTrue(position.getIntentions().isEmpty());
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testAddedPositionIsNotLinked(){
		PositionImpl position = (PositionImpl) model.getContainers().get(0);
		assertTrue(position.getAssociationFrom().isEmpty());
		assertTrue(position.getAssociationTo().isEmpty());
		assertTrue(position.getDependencyFrom().isEmpty());
		assertTrue(position.getDependencyTo().isEmpty());
	}

	public int countTestCases() {
		// TODO Auto-generated method stub
		return 2;
	}

	public void run(TestResult result) {
		// TODO Auto-generated method stub
		
		
	}

}
