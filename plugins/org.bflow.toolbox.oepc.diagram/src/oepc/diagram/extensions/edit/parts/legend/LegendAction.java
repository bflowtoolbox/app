package oepc.diagram.extensions.edit.parts.legend;


import java.util.Iterator;
import java.util.List;

import oepc.diagram.edit.parts.OEPCEditPart;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


/**
 * This class represents the action, which will be fired by clicking the menu
 * item.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class LegendAction implements IObjectActionDelegate{

	
	/**
	 * The selected <code>OEPCEditPart</code>.
	 */
	private OEPCEditPart selectedElement;
	
	
	/**
	 * Runs the action by clicking.
	 * @param action
	 */
	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		
		if(selectedElement != null){
			if(selectedElement.useLegend() == false){
				CreateViewRequest createRequest = 
					CreateViewRequestFactory.getCreateShapeRequest(
							LegendElementTypes.Legend_01, 
							selectedElement.getDiagramPreferencesHint());
				Point p = 
					selectedElement.getFigure().getBounds().getTopLeft().getCopy();
				createRequest.setLocation(p);
				selectedElement.getCommand(createRequest).execute();
				
				LegendEditPart legend = 
					(LegendEditPart) selectedElement.getViewer()
					.getEditPartRegistry().get(
					((IAdaptable) ((List) createRequest.getNewObject()).get(0))
						.getAdapter(View.class));
				
				selectedElement.setLegendEditPart(legend);
			}	
			else{
				LegendEditPart legend = selectedElement.getLegendEditPart();
				DestroyElementRequest request = 
					new DestroyElementRequest(false);
				request.setElementToDestroy((EObject) legend.getModel());
				DestroyElementCommand command = 
					new DestroyElementCommand(request);
				
				try {
					command.execute(null, null);
					selectedElement.setLegendEditPart(null);
				} catch (ExecutionException e) {
					
				}
			}
		}
	}

	
	/**
	 * You've selected an element.
	 * @param action
	 * @param selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection structuredSelection = 
			(IStructuredSelection) selection;
		if(structuredSelection.getFirstElement() instanceof DiagramEditPart 
				&& structuredSelection.getFirstElement() 
				instanceof OEPCEditPart){
			selectedElement = 
				(OEPCEditPart) structuredSelection.getFirstElement();
		}
	}

	
	/**
	 * Sets the status of the menu item.
	 * @param action
	 * @param targetPart
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		try{
			IEditorPart editorPart = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		
			if(editorPart != null){
			selectedElement = (OEPCEditPart)((IDiagramWorkbenchPart) 
					editorPart).getDiagramEditPart();
			}
		}
		catch(Exception e){
		}
		
		if(selectedElement != null){
			if(getLegend() != null){
				action.setChecked(true);
			}
			else{
				selectedElement.setLegendEditPart(null);
				action.setChecked(false);
			}
		}
	}
	
	
	/**
	 * Returns the current used <code>LegendEditPart</code> or null, if not 
	 * used.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private LegendEditPart getLegend(){
		LegendEditPart legend = null;
		try{
			if(selectedElement != null){
				Iterator<Object> i = selectedElement.getChildren().iterator();
				while(i.hasNext()){
					Object e = i.next();
					if(e instanceof LegendEditPart){
						legend = (LegendEditPart) e;
						selectedElement.setLegendEditPart(legend);
						break;
					}
				}
			}
		}
		catch(Exception e){	
		}
		
		return legend;
	}
}
