package edu.toronto.cs.openome.testing;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class GoalImplCreateCommand implements Command {
	
	/*
	 * The model we are going to be creating an actor in
	 */
	private ContainerImpl container;
	private ModelImpl model;
	private GoalImpl goal;
	
	/*
	 * Name of the actor
	 */
	private String intentionName = "";
	
	/*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	/**
	 * Command to add an actor inside a model
	 * @param model
	 */
	public GoalImplCreateCommand(ModelImpl m){
		model = m;
	}
	
	/**
	 * Command to add an actor inside a container
	 * @param c - the container
	 */
	public GoalImplCreateCommand(ContainerImpl c){
		container = c;
	}
	
	/**
	 * Command to add an actor inside a model
	 * @param model
	 */
	public GoalImplCreateCommand(ModelImpl m, String name){
		model = m;
		intentionName = name;
	}
	
	/**
	 * Command to add an actor inside a container
	 * @param model
	 */
	public GoalImplCreateCommand(ContainerImpl c, String name){
		container = c;
		intentionName = name;
	}



	@Override
	public boolean canExecute() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command chain(Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		goal = (GoalImpl) factory.createGoal();
		goal.setName(intentionName);
				
		if(container != null){
			goal.setContainer(container);
			container.getIntentions().add(goal);
		} else if (model != null){
//			goal.setModel(model);
			model.getIntentions().add(goal);
		}
	}
	
	public GoalImpl getGoalImpl() {
		return goal;
	}

	@Override
	public Collection<?> getAffectedObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		return "Create Actor model";
	}

	@Override
	public Collection<?> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
