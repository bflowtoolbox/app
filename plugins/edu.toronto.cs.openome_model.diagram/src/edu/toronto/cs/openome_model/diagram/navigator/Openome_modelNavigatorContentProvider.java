package edu.toronto.cs.openome_model.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class Openome_modelNavigatorContentProvider implements
		ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	public Openome_modelNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						for (Iterator it = myEditingDomain.getResourceSet()
								.getResources().iterator(); it.hasNext();) {
							Resource nextResource = (Resource) it.next();
							nextResource.unload();
						}
						if (myViewer != null) {
							myViewer.getControl().getDisplay().asyncExec(
									myViewerRefreshRunnable);
						}
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		for (Iterator it = myEditingDomain.getResourceSet().getResources()
				.iterator(); it.hasNext();) {
			Resource resource = (Resource) it.next();
			resource.unload();
		}
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			Collection result = new ArrayList();
			result
					.addAll(createNavigatorItems(
							selectViewsByType(
									resource.getContents(),
									edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.MODEL_ID),
							file, false));
			return result.toArray();
		}

		if (parentElement instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup group = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem navigatorItem = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getVisualID(view)) {

		case edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup links = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Model_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			connectedViews = getDiagramLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			links
					.addChildren(createNavigatorItems(connectedViews, links,
							false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Actor_2001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Actor_2001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Agent_2002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Agent_2002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Position_2003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Position_2003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Role_2004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Role_2004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(
					connectedViews,
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_2005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_2005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_2006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_2006_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_2007_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_2007_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_2008_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_2008_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3006_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3007_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3007_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3008_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3008_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3009_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3009_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3010_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3010_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3011_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3011_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3012_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3012_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3013_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Goal_3013_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3014_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Softgoal_3014_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3015_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Resource_3015_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup incominglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3016_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup outgoinglinks = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Task_3016_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Dependency_4001_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_Dependency_4001_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_AndDecomposition_4002_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_AndDecomposition_4002_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OrDecomposition_4003_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OrDecomposition_4003_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_HelpContribution_4005_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_HelpContribution_4005_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_HurtContribution_4006_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_HurtContribution_4006_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_MakeContribution_4007_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_MakeContribution_4007_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_BreakContribution_4008_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_BreakContribution_4008_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_SomePlusContribution_4009_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_SomePlusContribution_4009_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_SomeMinusContribution_4010_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_SomeMinusContribution_4010_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_UnknownContribution_4011_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_UnknownContribution_4011_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_AndContribution_4012_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_AndContribution_4012_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OrContribution_4013_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OrContribution_4013_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_IsAAssociation_4014_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_IsAAssociation_4014_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_CoversAssociation_4015_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_CoversAssociation_4015_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OccupiesAssociation_4016_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_OccupiesAssociation_4016_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_IsPartOfAssociation_4017_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_IsPartOfAssociation_4017_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_PlaysAssociation_4018_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_PlaysAssociation_4018_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID: {
			Collection result = new ArrayList();
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup target = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_INSAssociation_4019_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup source = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorGroup(
					edu.toronto.cs.openome_model.diagram.part.Messages.NavigatorGroupName_INSAssociation_4019_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(
					Collections.singleton(view),
					edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection getLinksSourceByType(Collection edges, String type) {
		Collection result = new ArrayList();
		for (Iterator it = edges.iterator(); it.hasNext();) {
			Edge nextEdge = (Edge) it.next();
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getLinksTargetByType(Collection edges, String type) {
		Collection result = new ArrayList();
		for (Iterator it = edges.iterator(); it.hasNext();) {
			Edge nextEdge = (Edge) it.next();
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getOutgoingLinksByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getIncomingLinksByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getChildrenByType(Collection nodes, String type) {
		Collection result = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			View nextNode = (View) it.next();
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getDiagramLinksByType(Collection diagrams, String type) {
		Collection result = new ArrayList();
		for (Iterator it = diagrams.iterator(); it.hasNext();) {
			Diagram nextDiagram = (Diagram) it.next();
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection selectViewsByType(Collection views, String type) {
		Collection result = new ArrayList();
		for (Iterator it = views.iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart.MODEL_ID
				.equals(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection createNavigatorItems(Collection views, Object parent,
			boolean isLeafs) {
		Collection result = new ArrayList();
		for (Iterator it = views.iterator(); it.hasNext();) {
			result
					.add(new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem(
							(View) it.next(), parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelAbstractNavigatorItem) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelAbstractNavigatorItem abstractNavigatorItem = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
