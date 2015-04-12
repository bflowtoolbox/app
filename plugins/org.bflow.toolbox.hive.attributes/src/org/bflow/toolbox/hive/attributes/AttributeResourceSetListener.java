package org.bflow.toolbox.hive.attributes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 22.06.2011
 * @version 27.09.2014
 * 			01.03.2015 Added copy and paste support for attributes
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

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#getFilter()
	 */
	@Override
	public NotificationFilter getFilter() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isAggregatePrecommitListener()
	 */
	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isPostcommitOnly()
	 */
	@Override
	public boolean isPostcommitOnly() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isPrecommitOnly()
	 */
	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		Assert.isNotNull(file, "Attribute file mustn't be null!");
		Assert.isNotNull(diagramName, "Diagram name mustn't be null!");
		Assert.isNotNull(projectName, "Project name mustn't be null!");

		for (Notification notify:event.getNotifications()) {			
			
			if (notify.getEventType() == Notification.ADD) {
				Object newValue = notify.getNewValue();
				if (!(newValue instanceof EObject)) continue;
				
				EObject obj = (EObject) newValue;
				String proxyId = EMFCoreUtil.getProxyID(obj);
				String clName = getInstanceClassName(notify);
				
				for (Attribute attr: DefaultAttributeProvider.getAttributesByDiagram(diagramName, clName)) {
					file.add(proxyId, attr.getName(), attr.getValue());
				}
				
				for (Attribute attr: DefaultAttributeProvider.getAttributesByProject(projectName, clName)) {
					file.add(proxyId, attr.getName(), attr.getValue());
				}
				
				if (AttributeClipboard.Instance.isCopy(obj)) {
					Map<String, String> attributes = AttributeClipboard.Instance.getOriginAttributes(obj);
					for (Iterator<Entry<String, String>> it = attributes.entrySet().iterator(); it.hasNext();) {
						Entry<String, String> entry = it.next();
						file.add(proxyId, entry.getKey(), entry.getValue());
					}
				}
			}
			
			if (notify.getEventType() == Notification.REMOVE) {
				Object oldValue = notify.getOldValue();
				if (!(oldValue instanceof EObject)) continue;
				
				EObject obj = (EObject) oldValue;
				String proxyId = iAffectedObjectMap.get(obj); // We need to get the id from here (see comments later)
				if (proxyId != null && !proxyId.equalsIgnoreCase("//"))
					file.removeAll(proxyId);
			}
		}
		
		iAffectedObjectMap.clear();
	}
	
	private HashMap<EObject, String> iAffectedObjectMap = new HashMap<EObject, String>();
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		/*
		 * At this point we have to resolve the proxy id of each affected element because 
		 * if an element will be deleted we cannot resolve it anymore.
		 */
		for (Notification notify:event.getNotifications()) {
			Object newValue = notify.getNewValue();
			Object oldValue = notify.getOldValue();
			
			addToAffectedItemList((newValue instanceof EObject), newValue);
			addToAffectedItemList((oldValue instanceof EObject), oldValue);
		}
		return null;
	}
	
	/**
	 * Adds the given affected object to a cache with its current (proxy) id if
	 * it's applicable.
	 * 
	 * @param applicable
	 *            If false nothing will happen
	 * @param affectedObject
	 *            Affected object that will be casted to EObject
	 */
	private void addToAffectedItemList(boolean applicable, Object affectedObject) {
		if (!applicable) return;
		EObject eObject = (EObject) affectedObject;
		String id = EMFCoreUtil.getProxyID(eObject);
		if (!id.equalsIgnoreCase("//")) // Means id is not set
			iAffectedObjectMap.put(eObject, id);
	}
	
	/**
	 * Resolves the instance class name that is affected by the given
	 * notification.
	 * 
	 * @param notify
	 *            Notification that announces the affected item
	 * @return Instance class name of the affected item
	 */
	private String getInstanceClassName(Notification notify) {
		EObject obj = (EObject) notify.getNewValue();
		EClass eCl = obj.eClass();
		String clName = eCl.getInstanceClassName();
		return clName;
	}
}