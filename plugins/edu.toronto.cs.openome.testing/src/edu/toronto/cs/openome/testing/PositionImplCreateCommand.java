package edu.toronto.cs.openome.testing;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class PositionImplCreateCommand implements Command {
	
	/*
	 * The model we are going to be creating an actor in
	 */
	private ModelImpl model;
	
	/*
	 * Name of the actor
	 */
	private String actorName = "";
	
	/*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	/**
	 * Command to add an actor inside a model
	 * @param model
	 */
	public PositionImplCreateCommand(ModelImpl m){
		model = m;
	}
	
	/**
	 * Command to add an actor inside a model
	 * @param model
	 */
	public PositionImplCreateCommand(ModelImpl m, String name){
		model = m;
		actorName = name;
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
		PositionImpl pos = (PositionImpl) factory.createPosition();
		pos.setModel(model);
		pos.setName(actorName);
		model.getContainers().add(pos);

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
		return "Create Position model";
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
