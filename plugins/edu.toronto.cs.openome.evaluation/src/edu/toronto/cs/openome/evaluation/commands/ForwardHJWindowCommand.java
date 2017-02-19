package edu.toronto.cs.openome.evaluation.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.ElementTypeLabelProvider;
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

public class ForwardHJWindowCommand extends HJWindowCommand {
	
	public ForwardHJWindowCommand(Shell s, CommandStack cs, Intention i) {
		
		super(s, cs, i);	
		
	}

	@SuppressWarnings("restriction")
	public void execute() {
		//System.out.println("executign ForwardHJWindowCommand");
		
		
		//InputDialog d = new InputDialog(shell, "a", "b", "c", null);
		
		//EvaluationDialog d = new EvaluationDialog(shell);
		//SelectionDialog d = new SelectionDialog(shell);
		
		EvaluationDialog ld = new EvaluationDialog(shell);
		
		List<EvaluationLabel> labellist = new ArrayList<EvaluationLabel>();
		
		//System.out.println("created dialog and labellist");
		
//		list.add(Openome_modelElementTypes.Goal_1005);
//		list.add(Openome_modelElementTypes.Dependency_3001);
//		list.add(Openome_modelElementTypes.BreakContribution_3007);
		
		labellist.add(EvaluationLabel.SATISFIED);
		labellist.add(EvaluationLabel.PARTIALLY_SATISFIED);
		labellist.add(EvaluationLabel.CONFLICT);
		labellist.add(EvaluationLabel.UNKNOWN);
		labellist.add(EvaluationLabel.PARTIALLY_DENIED);
		labellist.add(EvaluationLabel.DENIED);
	
		//System.out.println("added to labellist");
		
		//ld.setAddCancelButton(true);  
		ld.setEvalLabelContentProvider(new ArrayContentProvider());
		ld.setLabelBagContentProvider(new ArrayContentProvider());
		
		//System.out.println("set content provider");
		
		ld.setEvalLabelLabelProvider(new EvalLabelElementTypeLabelProvider());
		ld.setLabelBagLabelProvider(new LabelBagElementTypeLabelProvider());
		
		//System.out.println("set provider");
	
		ld.setEvalLabelInput(labellist);
		
		//System.out.println("set label list");
		
		if (intention != null) {
			Object array = intention.getLabelBag().toArray();
			ld.setLabelBagInput(array);
		}
		else
			System.out.println("Intention is null");
		
		//System.out.println("set label bag");		
		
		String mess = intention.getName();
		if (intention.getContainer() != null)
			mess += " in " + intention.getContainer().getName();
	
		ld.setTitle("Softgoal Resolution for " + mess);
		ld.setMessage(mess + " has received the following labels.  Please select a resulting label.");

		//System.out.println("set title and message");	
		
		ld.open();
		
		if (ld.getReturnCode() == Window.CANCEL) {
			result = EvaluationLabel.NONE;
			//System.out.println("cancelled");
			cancelled = true;
			return;
		}
		
		for (Object ob: ld.getResult()) {
			//System.out.println("Dialog Result: " + ob.toString());
			result = EvaluationLabel.getByName(ob.toString());
			//System.out.println("Dialog Result: " + result.getName());
		}
	}	

}
