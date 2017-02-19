package edu.toronto.cs.openome.evaluation.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.ElementTypeLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.dialogs.SelectionDialog;

import edu.toronto.cs.openome.evaluation.gui.EvalLabelElementTypeLabelProvider;
import edu.toronto.cs.openome.evaluation.gui.EvaluationDialog;
import edu.toronto.cs.openome.evaluation.gui.LabelBagElementTypeLabelProvider;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;

public abstract class  HJWindowCommand implements Command {

	protected Shell shell;
	protected Intention intention;
	protected EvaluationLabel result;
	protected boolean cancelled;
	CommandStack  commandStack;
	
	public HJWindowCommand(Shell s, CommandStack cs, Intention i) {
		shell = s;
		intention = i;
		result = EvaluationLabel.NONE;
		cancelled = false;
		commandStack = cs;
	}

	public boolean canExecute() {
	
		return true;
	}

	public boolean canUndo() {
		
		return false;
	}

	public Command chain(Command command) {
	
		return null;
	}
	public void dispose() {
		

	}

	public abstract void execute();
		

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
	
	public EvaluationLabel getEvalResult() {
		return result;
	}
	
	public boolean cancelled() {
		return cancelled;
	}

}
