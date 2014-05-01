package oepc.diagram.extensions.actions;

import oepc.diagram.part.ValidateAction;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;


/**
 * If the model resource is change, the <code>ResourceSetListener</code> will
 * call me to run validation.
 * 
 * @see OepcResourceListener
 * @see ResourceSetListener
 * @author Joerg Hartmann
 *
 */
public class DiagramLiveValidator {
	
	
	/**
	 * The correspond diagram edit part which provides me.
	 */
	private DiagramEditPart diagramEditPart;
	
	
	/**
	 * Indicates if validation should be started.
	 */
	private boolean doValidate;
	
	
	/**
	 * If <code>doValidate</code> is set to true, this listener will be set
	 * to your resource set.
	 * Otherwise it will be removed.
	 */
	private OepcResourceListener resourceListener;
	
	
	/**
	 * Creating the validator by delivering the diagram edit part.
	 * Also initializes the <code>doValidate</code> flag to false.
	 * @param editPart
	 */
	public DiagramLiveValidator(DiagramEditPart editPart){
		diagramEditPart = editPart;
		doValidate = true;
		doValidate(doValidate);
	}
	
	
	/**
	 * This method will be called if you want to validate the diagram.
	 * Validation will only be started if the <code>doValidate</code> is true
	 * and the <code>diagramEditPart</code> is not null.
	 * @see ValidateAction.runValidation(DiagramEditPart, View)
	 */
	public void runValidation(){
		if(doValidate && diagramEditPart != null){
			ValidateAction.runValidation( diagramEditPart,
					diagramEditPart.getDiagramView());
		}
	}
	
	
	/**
	 * Sets the <code>doValidate</code> flag. If you deliver true, the 
	 * resource listener will be instantiated and set. Otherwise remove the 
	 * listener.
	 * @param doValidate
	 */
	public void doValidate(boolean doValidate){
		if(doValidate){
			if(resourceListener == null){
				resourceListener = new OepcResourceListener(this);
			}
			if(diagramEditPart != null){
				diagramEditPart.getEditingDomain().addResourceSetListener(
						resourceListener);
			}
		}
		else{
			if(resourceListener != null && diagramEditPart != null){
				diagramEditPart.getEditingDomain().removeResourceSetListener(
						resourceListener);
				resourceListener = null;
			}
		}
		this.doValidate = doValidate;
	}
	
	
	/**
	 * Returns the value of <code>doValidate</code>.
	 * @return
	 */
	public boolean isDoValidate(){
		return doValidate;
	}

	
	/**
	 * To access the diagram edit part.
	 * @return <code>diagramEditPart</code>
	 */
	public DiagramEditPart getDiagramEditPart() {
		return diagramEditPart;
	}
}
