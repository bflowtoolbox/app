package edu.toronto.cs.openome.evaluation.views;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * This class makes it easier to get a hold of the current Model and CommandStack
 * @author Aftab
 *
 */
public class ModelInstance {
	
	
	private static Openome_modelDiagramEditor mDE;
	
	/**
	 * Returns the active Model
	 * @return
	 */
	public static ModelImpl getModelImpl() {
		ModelImpl model = null;
		
		try {
		getModelDiagramEditor();
		
		EditingDomain editingDomain = mDE.getEditingDomain();
				
		ResourceSet resourceSet = editingDomain.getResourceSet();
		
		
		
		XMIResourceImpl xmires = null;
		
		for(Resource tmp: resourceSet.getResources()) {
			//System.out.println(tmp.toString());
			if (tmp instanceof XMIResourceImpl) {
				xmires = (XMIResourceImpl) tmp;
			}
			
		}

						
		for(EObject tmp2: xmires.getContents()){ 
			if (tmp2 instanceof ModelImpl) 
				model = (ModelImpl) tmp2; 
		}
		} catch (Exception e) {
			return null;
		}
		
		return model;
		
	}
	
	/**
	 * Returns the active CommandStack
	 * @return
	 */
	public static CommandStack getCommandStack() {
		CommandStack cs = null;
		try {
			getModelDiagramEditor();
			EditingDomain editingDomain = mDE.getEditingDomain();

			cs = editingDomain.getCommandStack();
		} catch (Exception e) {
			return null;
		}

		return cs;
	}
	
	
	/**
	 * This is an important somethingorrather that is needed to get both the modelImplementation and the command stack.
	 */
	private static void getModelDiagramEditor() {
		
		try {
			IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			
			IWorkbenchPage iwp = iww.getActivePage(); //assume correct page is showing ... dubious
			
			IEditorPart iep= iwp.getActiveEditor(); //
			
			mDE = (Openome_modelDiagramEditor) iep; //
					}
		catch (Exception e) {
			System.out.println("Exception getting modelEditor");
		}
		 
	}
	

}
