package edu.toronto.cs.openome.evaluation.commands;

import java.util.Collection;
import org.eclipse.emf.common.command.Command;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.HumanJudgmentImpl;
import edu.toronto.cs.openome_model.impl.LabelBagImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class AddToLabelBagCommand implements Command {
	private Intention recipient;
	private Intention toAdd;
	private EvaluationLabel label;
	
	public AddToLabelBagCommand(Intention r, Intention ta, EvaluationLabel l) {
		recipient = r;
		toAdd = ta;
		label = l;
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
		if (recipient.getLabelBag() == null)
			recipient.setLabelBag(new LabelBagImpl());
		recipient.getLabelBag().addToLabelBag(toAdd, label);
			
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
