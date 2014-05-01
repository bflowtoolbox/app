package org.bflow.toolbox.extensions.wizards;

import java.util.Iterator;
import java.util.Vector;

import org.bflow.toolbox.extensions.helpers.Translator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * An wizardpage to select the types of the elements which are to resized.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class ClassSelectorWizardPage extends WizardPage {

	
	/**
	 * Containing all types.
	 */
	private Vector<Class<?>> classes;
	
	
	/**
	 * Stores the classes and the selection.
	 */
	private Vector<ClassSelectorMarker> markers;
	
	
	/**
	 * Creates this page.
	 * @param classes
	 */
	protected ClassSelectorWizardPage(Vector<Class<?>> classes) {
		super("ClassSelectorPage");
		setTitle("Select the element types to auto size");
		setDescription("");
		this.classes = classes;
		this.markers = new Vector<ClassSelectorMarker>();
	}

	
	/**
	 * Creates the layout of the page.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite child = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		child.setLayoutData(new GridData(GridData.FILL_BOTH));
		child.setLayout(gl);
		
		for(Iterator<Class<?>> c = classes.iterator(); c.hasNext();){
			Class<?> singleClass = c.next();
			Button button = new Button(child, SWT.CHECK);
			button.setText(Translator.translate(
					singleClass.getSimpleName().replace("EditPart", "")));
			button.setSelection(true);
			markers.add(new ClassSelectorMarker(singleClass, button));
		}
		
		setControl(child);
	}

	
	/**
	 * Returns all markers.
	 * @return
	 */
	public Vector<ClassSelectorMarker> getMarkers() {
		return markers;
	}
}
