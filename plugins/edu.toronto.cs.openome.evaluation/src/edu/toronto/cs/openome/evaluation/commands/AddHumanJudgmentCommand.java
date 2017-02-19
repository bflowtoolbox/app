package edu.toronto.cs.openome.evaluation.commands;

import java.util.Collection;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.impl.HumanJudgmentImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

public class AddHumanJudgmentCommand implements Command {
	private Intention intention;
	private EvaluationLabel label;
	private CommandStack cs;
	private HumanJudgment result;
	
	public AddHumanJudgmentCommand(Intention i, EvaluationLabel l, CommandStack commandStack) {
		intention = i;
		label = l;
		cs = commandStack;
		result = null;
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
		//System.out.println("Executing addHumanJudgment, adding " + label.getName() + " to " + intention.getName());
		result = intention.addHumanJudgment(label);
		//System.out.println(result.toUIString());
		Command addReverse = new AddReverseJudgmentCommand(intention, label);
		cs.execute(addReverse);		
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
	
	public HumanJudgment getHumanJudgmentResult() {
		// TODO Auto-generated method stub
		return result;
	}

	public void redo() {
		// TODO Auto-generated method stub

	}

	public void undo() {
		// TODO Auto-generated method stub

	}

}
