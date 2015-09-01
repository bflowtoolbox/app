/**
 * 
 */
package org.bflow.toolbox.epc.templating.dialogs;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;


/**
 * Defines the TemplateWizard
 * 
 * @author Markus Schnaedelbach
 */
public class TemplateWizard extends Wizard {

	private TemplateFileService fileservice;
	private TemplateWizardPage1 page1;

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param editor
	 * @param selection 
	 * @param TemplateActions 
	 */
	public TemplateWizard(DiagramDocumentEditor editor, IStructuredSelection selection, TemplateAction action) {
		this.fileservice = new TemplateFileService(editor, selection, action); //den editor später ma entfernen bestimmt
		setNeedsProgressMonitor(true);
	}
		
	@Override
	  public void addPages() {
		page1 = new TemplateWizardPage1(Messages.TemplateWizard_0,fileservice);
	    addPage(page1);
	}

	@Override
	public boolean performFinish() {
		
		
		if (!fileservice.allNaminvariablesHaveValues()) {
			boolean result = MessageDialog.openQuestion(getShell(), Messages.TemplateFileService_0, Messages.TemplateWizard_1);
			if (!result) {
				return false;
			}
		}
		
		try {
			return fileservice.performEmbedding();
		} catch (InterchangeProcessingException e) {
			e.printStackTrace();
		}
		return false;
	}	
}
