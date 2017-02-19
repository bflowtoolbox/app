package edu.toronto.cs.openome.versioning.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.dialogs.MessageDialog;

import sc.document.Configuration;
import sc.document.GoalModel;
import edu.toronto.cs.openome_model.presentation.openome_modelEditor;
import fluid.version.Version;

public class EditorCommitHandler extends MolhadoActionHandler implements IHandler {

	openome_modelEditor gme; //override
	
	public EditorCommitHandler() {
		super(); //gets the MolhadoActions instance
	}
	
	/**
	 * Commit the model to the repository
	 * Possible states of the model on call: 
	 * 1) not a valid OpenOME model (oom file)
	 * 2) new model, never committed
	 * 3) existing model, committed before, not dirty
	 * 4) existing model, committed before, dirty <br/>
	 * the molhado terminology: a config is assigned per folder (project). 
	 * Inside each config there is a set of files with each file holding a set of 'versions'. 
	 * So first we see what the latest version is, which  
	 * a) creates a new config with the project_name, and sets V=0 or; b) finds the latest version
	 * 
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			gme = (openome_modelEditor) findEditor();
			editingDomain = gme.getEditingDomain();
			setResourceSet();
		    List<Resource> resources = resourceSet.getResources();
		    for(Resource nextResource: resources) {
		    //the GMF diagram editor has a list of resources: 
		    //1, the diagram file (OOD), 2, the model file (OOM). We operate on the model file.
		    	if (nextResource instanceof XMIResource) {
		    		resource = nextResource;				
		    	}
		    }
		    if (resource == null) throw new Exception("No XMI Resource found");

			super.setModelDetails(resource);  //i.e., file_name, editor instance, etc
			super.setGME(gme);

		    //warn of unsaved changes... but we don't force it.
		    if(isDirty())
		    	MessageDialog.openWarning(null, "Versioning warning", "Current editor has unsaved changes that will be lost");
		    
		    Configuration.ensureLoaded();
		    ma.checkLatestVersion(model_name, project_name, file_name, resourceSet);
		    Configuration config = ma.configurations.get(project_name);  
		    //after this step we have a goal model stored and versioned.
			ma.checkout_last_version(config, model_name, 0); 
			// Tien: important after check out if one wants to modify the mirror 
			Version.bumpVersion(); 
			GoalModel the_gm = ma.find_the_gm(file_name, config);
			//null the_gm implies ... no existing version of this model
			if (the_gm != null) {
				ma.updateIndex(the_gm, the_gm.getRoot());
				ma.modify_edited_goal_model(the_gm, gme); //algorithm 4
			}
			//GoalModel.clearCount();
			ma.checkin_current_version(config, model_name); 
			//Version 2 because we first store a blank goalmodel
			//System.out.println("Stats for save: " + GoalModel.getCount());
			MessageDialog.openConfirm(null, "Versioning successful", "Current model was stored as version: " + ma.getVersion(project_name, model_name, config)
										+ "\nStats: " + GoalModel.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * get the current EMF i* model elements for commit<br>
	 * set the various properties, file name &c.
	 * 
	 
	private void setModelDetails() {
		modelFile = (IFileEditorInput) gme.getEditorInput(); 
		//is the editor dirty, that is, modified?
		project_name = modelFile.getFile().getProject().getName(); //is empty?
		model_name = modelFile.getFile().getName().toString();
		model_name = model_name.substring(0, model_name.indexOf(".oom"));
		file_name = modelFile.getFile().getFullPath().toString();
	}*/

}