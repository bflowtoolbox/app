package edu.toronto.cs.openome.evaluation.commands;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;

import edu.toronto.cs.openome.evaluation.views.ModelInstance;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class AddIntentionsToAlternativeCommand implements Command {
	private Alternative alternative;
	private EList<Intention> intentions;
	private ModelImpl mi;
	
	/*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	public AddIntentionsToAlternativeCommand(Alternative alt, EList<Intention> ints, ModelImpl m) {
		alternative = alt;
		intentions = ints;
		mi = m;
	}

	public boolean canExecute() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

	public Command chain(Command arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void execute() {
		
		alternative.addIntention(intentions, mi);
	}

	public Collection<?> getAffectedObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return "a label";
	}

	public Collection<?> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public void redo() {
		// TODO Auto-generated method stub

	}

	public void undo() {
		// TODO Auto-generated method stub

	}

}
