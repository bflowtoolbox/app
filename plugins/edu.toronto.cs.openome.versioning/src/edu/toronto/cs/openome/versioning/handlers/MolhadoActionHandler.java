package edu.toronto.cs.openome.versioning.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.versioning.MolhadoActions;

/** 
 * Super class for any Handler for a Molhado related command
 * Manages various administrivia like fetching model names &c.
 * @author nernst
 *
 */
public class MolhadoActionHandler implements IHandler {

	protected MolhadoActions ma;
	protected IEditorPart gme;
	protected EditingDomain editingDomain;
	protected Resource resource;
	protected URI resourceURI;
	protected IFileEditorInput modelFile = null;
	protected String project_name;
	protected String model_name;
	protected String file_name;
	protected ResourceSet resourceSet;
	
	protected MolhadoActionHandler() {
		ma = MolhadoActions.getInstance();
	}

	protected void loadResource() {
		try {
			// Load the resource through the editing domain.
			//
			resourceURI = EditUIUtil.getURI(gme.getEditorInput());
			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
		}
		catch (Exception e) {
			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		}
	}
	
	protected void setResourceSet() {
		resourceSet = editingDomain.getResourceSet();
	}

	/**
	 * Check if the current editor has unsaved changes
	 */
	protected boolean isDirty() {
		return gme.isDirty();
	}
	
	protected void setGME(IEditorPart g) {
		this.gme = g;
	}
	


	/**
	 * hacky way to get the user's editor and thence the resource.
	 * It would be better to register a listener on the model resource itself, e.g with EMF TransactionListener
	 * editor id is edu.toronto.cs.openome_model.presentation.openome_modelEditorID
	 */
	protected IEditorPart findEditor() {
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage(); //assume correct page is showing ... dubious
		IEditorPart iep = iwp.getActiveEditor(); //
		return iep;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isHandled() {
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {}

	public void addHandlerListener(IHandlerListener handlerListener) {}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {	}

	/** 
	 * Override in subclasses
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.err.println("this should be overridden by a subclass of MolhadoActionHandler");
		return null;
	}
	
	public String toString() {
		String s = "Model name: " + model_name + "\n";
		s += "File name: " + file_name + "\n";
		s += "Project name: " + project_name + "\n";
		return s;
	}
	
	/** 
	 * get the current EMF i* model elements for commit<br>
	 * set the various properties, file name &c.
	 * 
	 */
	protected void setModelDetails(Resource res) {
		//input: 'platform:/resource/Examples/q7/aspects/mediashop.oom'
		//can't use gme.getEditorInput() because the model file is the underlying oom file 
		String name = res.getURI().toString();
		name = name.substring(name.indexOf(":") + 10); //remove the 'platform:/resource/' substring
		model_name = name.substring(name.lastIndexOf("/")+1, name.indexOf(".oom")); //eg. 'mediashop'
		//model_name = model_name.substring(0, model_name.indexOf(".oom")); //eg. should be 'mediashop'
		project_name = name.substring(name.indexOf("/")+1, name.length()); //eg. 'Examples'
		project_name = project_name.substring(0,project_name.indexOf("/"));
		file_name = name; // eg. '/Examples/q7/aspects/mediashop.oom'
	}
}