package org.bflow.toolbox.bpmn.diagram.wizards;

import org.bflow.toolbox.bpmn.diagram.Messages;
import org.bflow.toolbox.bpmn.diagram.handlers.CreateBpmnDiagramHandler;
import org.bflow.toolbox.extensions.IDiagramCreationWizard;
import org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizard;
import org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizardPage2;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * Extends {@link BPMN2DiagramWizard} to patch a size error when the 
 * dialog is being presented via {@link CreateBpmnDiagramHandler}. Additionally, 
 * this wizard implements {@link IDiagramCreationWizard} to support some Bflow* features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-04
 * @version 2019-01-27 AST Added implementation of {@link IDiagramCreationWizard}
 *
 */
public class BpmnCreationWizard extends BPMN2DiagramWizard implements IDiagramCreationWizard {
	private Resource _resource;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		Point sz = pageContainer.getSize();
		
		/* 2018-10-04 AST:
		 * The BPMN2DiagramWizardPage1 calculates the width of its labels via the 
		 * pageContainer size. When the page is not being displayed by the Eclipse 
		 * New Wizard, the size is initially 0. So we set up a default size here 
		 * to avoid the error.
		 */			
		if (sz.x == 0) {
			pageContainer.setSize(600, 300);
		}
		
		super.createPageControls(pageContainer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		BPMN2DiagramWizardPage2 page2 = (BPMN2DiagramWizardPage2) getPages()[1];
		IResource fileContainer = page2.getDiagramContainer();
		String filename = page2.getFileName();
		IPath fullPath = fileContainer.getFullPath().append(filename);
		URI fileUri = URI.createPlatformResourceURI(fullPath.toString(), false);
		
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		try {
			_resource = editingDomain.getResourceSet().createResource(fileUri);
		} finally {
			editingDomain.dispose(); 
		}		
		
		return super.performFinish();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.IDiagramCreationWizard#getShortHint()
	 */
	@Override
	public String getShortHint() {
		return Messages.BpmnCreationWizard_ShortHint;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.IDiagramCreationWizard#getDiagram()
	 */
	@Override
	public Resource getDiagram() {
		return _resource;
	}
}
