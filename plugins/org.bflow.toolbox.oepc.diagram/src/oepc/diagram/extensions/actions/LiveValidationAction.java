package oepc.diagram.extensions.actions;



import oepc.diagram.edit.parts.OEPCEditPart;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * An <code>IObjectActionDelegate</code> that will be called if you clicked
 * on the correspond menu item or the sequence you entered in the correspond
 * binding. Both are created in the plugin.xml.
 * @author Joerg Hartmann
 *
 */
public class LiveValidationAction implements IObjectActionDelegate{

	
	/**
	 * The diagram edit part.
	 */
	private OEPCEditPart diagramEditPart;
	
	
	/**
	 * The diagram validator.
	 */
	private DiagramLiveValidator validator;
	
	
	/**
	 * Flag indicates if live validation should be started or not. 
	 */
	private boolean enableValidation = false;
	
	
	/**
	 * Called by pressing the item or by the bindings sequence.
	 */
	public void run(IAction action) {
		setCurrentEditor();
		
		if(validator != null){
			enableValidation = validator.isDoValidate();
			enableValidation = enableValidation ? false : true;
			
			validator.doValidate(enableValidation);
			if(enableValidation){
				validator.runValidation();
			}
		}
	}
	

	/**
	 * The selection was changes. Sets the edit part and the validator.
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		setCurrentEditor();
	}

	
	/**
	 * Is called before the menu open's. Sets the check of the item.
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		setCurrentEditor();
		if(validator != null && action != null){
			action.setChecked(validator.isDoValidate());
		}
	}
	
	
	/**
	 * Access the diagram edit part and the validator.
	 */
	private void setCurrentEditor(){
		try{
			IEditorPart editorPart = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
			
			if(editorPart != null){
				diagramEditPart = (OEPCEditPart)((IDiagramWorkbenchPart) 
						editorPart).getDiagramEditPart();
			}
			if(diagramEditPart != null){
				validator = diagramEditPart.getValidator();
			}
		}
		catch(NullPointerException npe){
			
		}
		catch(ClassCastException cce){
			
		}
	}
}
