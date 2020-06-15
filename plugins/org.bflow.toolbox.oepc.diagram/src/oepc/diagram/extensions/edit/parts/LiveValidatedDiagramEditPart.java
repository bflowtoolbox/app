package oepc.diagram.extensions.edit.parts;

import oepc.diagram.extensions.actions.DiagramLiveValidator;
import oepc.diagram.extensions.actions.LiveValidationAction;
import oepc.diagram.part.OepcDiagramEditorPlugin;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Represents an <code>DiagramEditPart</code> which defines his own 
 * <code>DiagramLiveValidator</code> which can be used to enable or 
 * disable the live validation.
 * To install live validation use this class as super class for your diagram
 * edit part and install the <code>LiveValidationAction</code>.
 * @see LiveValidationAction
 * @author Joerg Hartmann
 *
 */
public abstract class LiveValidatedDiagramEditPart extends BflowDiagramEditPart {
    
	
	/**
	 * Validates after resource changes.
	 */
	private DiagramLiveValidator validator;
	
	
	/**
	 * Creating this edit part by delivering the view.
	 * @param diagramView
	 */
	public LiveValidatedDiagramEditPart(View diagramView) {
		super(diagramView);
		
		validator = new DiagramLiveValidator(this);
		
		OepcDiagramEditorPlugin.getInstance().setBflowDiagramEditPart(this);
		OepcDiagramEditorPlugin.getInstance().setDiagramLiveValidator(validator);
	}
	
	
	/**
	 * Returns the current <code>DiagramLiveValidator</code>.
	 * Creates a new instance if the validator was not been initialized before.
	 * @return
	 */
	public DiagramLiveValidator getValidator(){
		if(validator == null){
			validator = new DiagramLiveValidator(this);
			OepcDiagramEditorPlugin.getInstance().setDiagramLiveValidator(validator);
		}
		return validator;
	}
}
