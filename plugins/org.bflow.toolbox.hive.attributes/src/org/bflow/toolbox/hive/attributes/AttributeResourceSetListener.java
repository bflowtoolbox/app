package org.bflow.toolbox.hive.attributes;

import org.bflow.toolbox.hive.attributes.DefaultAttributeProvider.Attribute;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Implements a resource set listener that reacts to changes to the model resource set.
 * @author Arian Storch
 * @since 22/06/11
 *
 */
public class AttributeResourceSetListener implements ResourceSetListener {
	
	private AttributeFile file;
	private String diagramName;
	private String projectName;
	
	/**
	 * Returns the file that this listener modifies.
	 * @return attribute file
	 */
	public AttributeFile getFile() {
		return file;
	}
	
	/**
	 * Sets the file that this listener modifies.
	 * @param file file to edit
	 */
	public void setFile(AttributeFile file) {
		this.file = file;
	}	
	
	/**
	 * Sets the name of the diagram that is being edited.
	 * @param diagramName name
	 */
	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}
	
	/**
	 * Returns the name of the diagram that is being edited.
	 * @return diagram name
	 */
	public String getDiagramName() {
		return diagramName;
	}
	
	/**
	 * Returns the name of the project that is being edited.
	 * @return project name
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Sets the name of the project that is being edited.
	 * @param projectName name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public NotificationFilter getFilter() {
		return null;
	}

	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	@Override
	public boolean isPostcommitOnly() {
		return false;
	}

	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		Assert.isNotNull(file, "Attribute file mustn't be null!");
		Assert.isNotNull(diagramName, "Diagram name mustn't be null!");
		Assert.isNotNull(projectName, "Project name mustn't be null!");

		for(Notification notify:event.getNotifications()) {
			if(notify.getEventType() == Notification.ADD) {
				EObject obj = (EObject) notify.getNewValue();
				EClass eCl = obj.eClass();
				String clName = eCl.getInstanceClassName();
				
				for(Attribute attr: DefaultAttributeProvider.getAttributesByDiagram(diagramName, clName)) {
					file.add(EMFCoreUtil.getProxyID(obj), attr.getName(), attr.getValue());
				}
				
				for(Attribute attr: DefaultAttributeProvider.getAttributesByProject(projectName, clName)) {
					file.add(EMFCoreUtil.getProxyID(obj), attr.getName(), attr.getValue());
				}
			}
		}
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent arg0)
			throws RollbackException {
		return null;
	}

}
