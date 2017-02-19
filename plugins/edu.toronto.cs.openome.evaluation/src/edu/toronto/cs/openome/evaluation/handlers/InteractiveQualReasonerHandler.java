package edu.toronto.cs.openome.evaluation.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.commands.AddIntentionsToAlternativeCommand;
import edu.toronto.cs.openome.evaluation.commands.SetAlternativeCommand;
import edu.toronto.cs.openome.evaluation.gui.AlternateDialog;
import edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.InteractiveQualReasoner;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoning;
import edu.toronto.cs.openome.evaluation.views.AlternativesView;
import edu.toronto.cs.openome.evaluation.views.HumanJudgmentsView;
import edu.toronto.cs.openome.evaluation.views.InconsistencyChecksView;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.impl.AlternativeImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;



/**
 * @author jenhork
 * This class is used to handle the evaluation menu function.  This one handles the interactive qualitative reasoning menu.
 */
public class InteractiveQualReasonerHandler extends ReasonerHandler {

	public InteractiveQualReasonerHandler() {
		super();
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * Shows a message in a dialog box with an OK button 
	 * @param message
	 */
	private void showMessage(String message, Shell shell) {
		MessageDialog.openInformation(
			shell,
			"Alternatives",
			message);
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell [] ar = PlatformUI.getWorkbench().getDisplay().getShells();
		
		Shell shell = ar[0];
		DiagramCommandStack dcs = null;
		
		try {
			dcs = getDiagramCommandStack();
		} catch (Exception e) {
			
			showMessage("Please select a diagram model", shell);
			return null;
		}

		AlternateDialog ad = new AlternateDialog(shell);
	
		// Open a dialog box for alternative name and description
		ad.open();
		
		// User pressed cancel
		if (ad.getReturnCode() == Window.CANCEL){
			return null;
		}

		openome_modelPackage _openome_modelPackage = openome_modelPackage.eINSTANCE;
		openome_modelFactory _openome_modelFactory = _openome_modelPackage.getopenome_modelFactory();
		
		/* Create an Alternative */
		Alternative alt = _openome_modelFactory.createAlternative();
		
		alt.setName(ad.getName());
		alt.setDescription(ad.getDescription());
		alt.setDirection("forward");

		ModelImpl mi = getModelImpl();
		CommandStack cs = getCommandStack();
		List editParts = getEditParts();
		
		InteractiveQualReasoner iQualReasoner = new InteractiveQualReasoner(mi, cs, dcs, editParts);
		Reasoning reasoning = new Reasoning(iQualReasoner);
		
		//for now, remove later
		clearAllJudgments(mi, cs);
		
		reasoning.reason();

		// Get a list of all intentions currently in the model
		EList<Intention> intentionsList = mi.getAllIntentions();
	
		
		/* Add the intentions to the newly created Alternative */
		Command addIntentionsToAlternative = new AddIntentionsToAlternativeCommand(alt, intentionsList, mi);
		cs.execute(addIntentionsToAlternative);
		
		//alt.setSoftgoalWrappers(iQualReasoner.getSoftgoalWrappers());
		
		/* Create Human Judgments view */
		HumanJudgmentsView hj = null;
		try {
			// open the HJView, if already opened just give the focus to it
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(HumanJudgmentsView.ID);
			// Get the HJ View
			hj = (HumanJudgmentsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HumanJudgmentsView.ID);
		} catch (PartInitException e) {
			// Shouldn't happen...
			System.err.println("Failed to open HumanJudgmentsView");
		}
		
		AlternativesView av = null;
		try {
			// open the AlternativesView, if already opened just give the focus to it
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(AlternativesView.ID);
			// Get the Alternates View
			av = (AlternativesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(AlternativesView.ID);
		} catch (PartInitException e) {
			// Shouldn't happen...
			System.out.println("Failed to open AlternativesView");
		}
		
		//Set the direction in the alternative
		alt.setDirection("forward");
		
		/* Populate the Alternate View with the alternative */
		av.addAlternative(alt);
		
		/* Add the Alternative to the Model */
		Command addAlternnative = new SetAlternativeCommand(alt);
		CommandStack cs1 = getCommandStack();
		cs1.execute(addAlternnative);
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
