package org.bflow.toolbox.extensions;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Describes a wizard that creates a Bflow*-related diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public interface IDiagramCreationWizard extends IWizard {
	/**
	 * Notifies the instance to initialize itself.
	 * 
	 * @param workbench Workbench
	 * @param selection Selection
	 */
	void init(IWorkbench workbench, IStructuredSelection selection);
	
	/** Returns a short hint that describes the diagram the wizard creates. */
	String getShortHint();

	/** Returns the created diagram */
	Resource getDiagram();
}