package edu.toronto.cs.openome.versioning.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import sc.document.Configuration;
import sc.document.GoalModel;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;

/**
 * This is called when the user clicks commit from the diagram editor
 * @author nernst
 * 		    //TODO add these methods to their own handlers:
		    //checkOutAllVersionsFromRepository(config, model_name);
		    //checkout_last_version(config, model_name);
		    //checkout_version(Configuration, String)
 *
 */
public class DiagramCommitHandler extends MolhadoActionHandler implements IHandler {

	//Openome_modelDiagramEditor gme;
	
	public DiagramCommitHandler() {
		super(); //gets the MolhadoActions instance
	}
	
	/**
	 * Commit the model to the repository
	 * Possible states of the model on call: 
	 * 1) not a valid OpenOME model (ood file)
	 * 2) new model, never committed
	 * 3) existing model, committed before, not dirty
	 * 4) existing model, committed before, dirty
	 * 
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Save the resources to the file system.
		// check in the changes
		try {
			gme = (Openome_modelDiagramEditor) findEditor(); 
			super.setGME(gme);
			//see http://help.eclipse.org/help33/topic/org.eclipse.gmf.doc/reference/api/runtime/org/eclipse/gmf/runtime/diagram/ui/resources/editor/ide/editor/FileDiagramEditor.html
			editingDomain = ((Openome_modelDiagramEditor) gme).getEditingDomain();
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
			// Algorithm 3
		    ma.checkLatestVersion(model_name, project_name, file_name, resourceSet); 
		    
		    Configuration config = ma.configurations.get(project_name);
		    GoalModel the_gm = ma.find_the_gm(model_name, config);
		    
		    // this replicates the behaviour of ma.checkinCheckoutMolhado
			if (the_gm != null) {
				GoalModel.clearCount();
				ma.modify_edited_diagram_goal_model(the_gm, resource);
				ma.checkin_current_version(config, model_name);
				System.out.println("Stats for save: " + GoalModel.getCount());
			} else {
				GoalModel.clearCount();
				ma.checkInGoalModel(file_name, model_name, config, resourceSet);
				ma.update_version(model_name, 1);
				System.out.println("Stats for save: " + GoalModel.getCount());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * nernst - set false to disable checkins in Diagram component
	 */
	public boolean isEnabled() {
		return false;
	}
}