package edu.toronto.cs.openome.testing;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.UnknownContributionImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class UnknownImplCreateCommand implements Command {
	
	/*
	 * The model we are going to be creating an actor in
	 */
	private ContainerImpl container;
	private ModelImpl model;
	private Intention source;
	private Intention target;
	
	
	/*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	/**
	 * Command to add an actor inside a model
	 * @param model
	 */
	public UnknownImplCreateCommand(ModelImpl m){
		model = m;
	}
	
	/**
	 * Command to add an actor inside a container
	 * @param c - the container
	 */
	public UnknownImplCreateCommand(ContainerImpl c){
		container = c;
	}
		
	/**
	 * Command to add an actor inside a container
	 * @param model
	 */
	public UnknownImplCreateCommand(ModelImpl m, IntentionImpl s, IntentionImpl t){
		model = m;
		source = s;
		target = t;
	}
	
	/**
	 * Command to add an actor inside a container
	 * @param model
	 */
	public UnknownImplCreateCommand(IntentionImpl source, IntentionImpl target, ContainerImpl c){
		container = c;
		
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
		UnknownContributionImpl cont = (UnknownContributionImpl) factory.createUnknownContribution();
		cont.setSource(source);
		cont.setTarget(target);
				
		if (model != null){
			cont.setModel(model);
			model.getContributions().add(cont);
		}
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
