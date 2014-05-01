package org.bflow.toolbox.extensions.wizards;



import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


/**
 * Represents an wizard, to show element types you can select to choose the 
 * elements, which should be auto-sized.
 * @author Joerg Hartmann
 * @since 0.0.7
 */
public class ClassSelectorWizard extends Wizard implements INewWizard{

	
	/**
	 * The classes you can choose.
	 */
	private Vector<Class<?>> classes;
	
	
	/**
	 * An <code>ClassSelectorWizardPage</code>.
	 */
	private ClassSelectorWizardPage wizardPage;
	
	
	/**
	 * Stores the selection state for each class.
	 */
	private Vector<ClassSelectorMarker> markers;
	
	
	/**
	 * Indicates if the wizard completed correctly.
	 */
	private boolean hasFinished = false;
	
	
	/**
	 * Creates this wizard by delivering all classes you can select below.
	 * @param classes
	 */
	public ClassSelectorWizard(Vector<Class<?>> classes){
		super();
		this.classes = classes;
	}
	
	
	/**
	 * Saves the selection state for each class.
	 * @return
	 */
	public boolean performFinish() {
		markers = wizardPage.getMarkers();
		for(Iterator<ClassSelectorMarker> m = markers.iterator(); m.hasNext();){
			ClassSelectorMarker marker = m.next();
			marker.setSelection(marker.getButton().getSelection());
		}
		hasFinished = true;
		return hasFinished;
	}

	
	/**
	 * Inits this wizard.
	 * @param workbench
	 * @param selection
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		wizardPage = new ClassSelectorWizardPage(classes);
	}
	
	
	/**
	 * Adds an <code>ClassSelectorWizardPage</code>.
	 */
	public void addPages(){
		addPage(wizardPage);
	}

	
	/**
	 * Return the markers.
	 * @return
	 */
	public Vector<ClassSelectorMarker> getMarkers() {
		return markers;
	}

	
	/**
	 * Returns the <code>hasFinished</code> flag.
	 * @return
	 */
	public boolean isHasFinished() {
		return hasFinished;
	}
}
