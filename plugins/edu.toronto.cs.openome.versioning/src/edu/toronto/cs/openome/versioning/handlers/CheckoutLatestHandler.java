package edu.toronto.cs.openome.versioning.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IFileEditorInput;

import sc.document.Configuration;
import edu.toronto.cs.openome_model.presentation.openome_modelEditor;
import fluid.ir.IRPersistent;
import fluid.util.FileLocator;
import fluid.version.Version;

/**
 * Checkout the latest version of this model from the repository
 * @author nernst
 *
 */
public class CheckoutLatestHandler extends MolhadoActionHandler {

	openome_modelEditor gme;
	
	public CheckoutLatestHandler() {
		super();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final FileLocator floc = IRPersistent.fluidFileLocator;     
		
		// Load the configuration   
		Configuration.ensureLoaded(); //necessary?

		gme = (openome_modelEditor) findEditor();
		editingDomain = gme.getEditingDomain();
		setResourceSet();
		setModelDetails();  //i.e., file_name, editor instance, etc
	    String property = ma.getMolhadoProperty();
	    Configuration config = ma.loadConfig(project_name, property);
	    if(config == null) {
	    	MessageDialog.openError(null, "Versioning error", "Could not find a repo to check out");
	    	throw new ExecutionException("No existing repo");
		}
	    int versionNumber = ma.getVersion(project_name, model_name, config);
	    ma.configurations.put(project_name, config);
		if (versionNumber == 0) {
			MessageDialog.openError(null, "Versioning error", "Could not find an existing version in the repo to check out");
			throw new ExecutionException("No existing versions");
		}
		ma.checkout_last_version(config, model_name, versionNumber);
		Version v1 = Version.getVersion();
		Version.setVersion(v1); //confused... why this behaviour? seems redundant.
		String vName = config.getVersionName(v1);
		//System.out.println(vName);
		ma.unparse_checkout_into_emf(config, vName);
		return null; 
	}

	/** 
	 * get the current EMF i* model elements for commit<br>
	 * set the various properties, file name &c.
	 * 
	 */
	private void setModelDetails() {
		modelFile = (IFileEditorInput) gme.getEditorInput(); 
		//is the editor dirty, that is, modified?
		project_name = modelFile.getFile().getProject().getName(); //is empty?
		model_name = modelFile.getFile().getName().toString();
		model_name = model_name.substring(0, model_name.indexOf(".oom"));
		file_name = modelFile.getFile().getFullPath().toString();
	}

	@Override
	public boolean isEnabled() {
		//return repoExists && versionExists; //TODO needs to poll somehow
		return true;
	}
}
