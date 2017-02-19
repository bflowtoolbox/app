/**
 * 
 */
package edu.toronto.cs.openome.evaluation.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.swt.graphics.RGB;

import edu.toronto.cs.openome.evaluation.commands.HighlightIntentionOutlinesCommand;
import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.InteractiveQualReasoner;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoning;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.commands.HighlightIntentionsCommand;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * @author jenhork
 *
 */
public class UnhighlightAllHander extends ReasonerHandler{
	
	ModelImpl model;
	CommandStack cs;

	public UnhighlightAllHander() {
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

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		model = getModelImpl();
		cs = getCommandStack();
		
		unHighlightAll();
		
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
	
	private void unHighlightAll() {
		HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(getEditParts(), model.getAllIntentions(), "");
		HighlightIntentionOutlinesCommand highlightOutlines = new HighlightIntentionOutlinesCommand(
				getEditParts(), model.getAllIntentions(), new RGB(0,0,0));
		cs.execute(highlight);
		cs.execute(highlightOutlines);
	}
}
