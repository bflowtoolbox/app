package org.bflow.toolbox.hive.addons.providers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.addons.AddonsPlugin;
import org.bflow.toolbox.hive.addons.services.IMitammMarkerProvider;
import org.bflow.toolbox.hive.addons.services.MitammMarkerService;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.ui.services.marker.AbstractMarkerNavigationProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;

/**
 * Implements a default marker navigation provider for the add-ons plug-in.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 06/03/11
 * @version 19/07/13
 * 
 */
public class DiagramMarkerNavigationProvider extends
		AbstractMarkerNavigationProvider implements IMitammMarkerProvider {

	/**
	 * Id of the marker
	 */
	public static final String MARKER_TYPE = "org.bflow.toolbox.mitamm.marker";

	/**
	 * instance of this class
	 */
	private static DiagramMarkerNavigationProvider instance;

	/**
	 * Constructor; <br/>
	 * Needed to register it to the mitamm marker service.
	 */
	public DiagramMarkerNavigationProvider() {
		MitammMarkerService.registerProvider(this);
	}

	@Override
	protected void doGotoMarker(IMarker marker) {
		String elementId = marker
				.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,	null);
		
		if (elementId == null || !(getEditor() instanceof DiagramEditor))
			return;
		
		DiagramEditor editor = (DiagramEditor) getEditor();
		Map<?,?> editPartRegistry = editor.getDiagramGraphicalViewer().getEditPartRegistry();
		EObject targetView = editor.getDiagram().eResource().getEObject(elementId);
		
		if (targetView == null) 
			return;
		
		EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);
		if (targetEditPart != null) {
			revealSelectedItem(editor, Arrays.asList(new EditPart[] { targetEditPart }));

			/*
			 * EpcDiagramEditorUtil.selectElementsInDiagram(editor, Arrays
			 * .asList(new EditPart[] { targetEditPart }));
			 */
		}
	}
	
	/**
	 * Scrolls the given collections of edit parts into the view and selects them 
	 * if possible.
	 * 
	 * @param diagramPart Diagram edit part
	 * @param editParts Collection of edit parts to select
	 */
	private void revealSelectedItem(IDiagramWorkbenchPart diagramPart, List<EditPart> editParts) {
		// This code is copied from EpcDiagramEditorUtil.selectElementsInDiagram(...)
		diagramPart.getDiagramGraphicalViewer().deselectAll();

		EditPart firstPrimary = null;
		for (Iterator<EditPart> it = editParts.iterator(); it.hasNext();) {
			EditPart nextPart = (EditPart) it.next();
			diagramPart.getDiagramGraphicalViewer().appendSelection(nextPart);
			if (firstPrimary == null && nextPart instanceof IPrimaryEditPart) {
				firstPrimary = nextPart;
			}
		}

		if (!editParts.isEmpty()) {
			diagramPart.getDiagramGraphicalViewer().reveal(
					firstPrimary != null ? firstPrimary : (EditPart) editParts
							.get(0));
		}
	}

	/**
	 * @generated
	 */
	public void deleteMarkers(IResource resource) {
		deleteMarkers(resource, IResource.DEPTH_ZERO);
	}

	/**
	 * Deletes all markers within the resource
	 * 
	 * @param resource
	 *            resource
	 * @param depth
	 *            IResource.DEPTH_INFINITE, IResource.DEPTH_ZERO, ...
	 */
	public void deleteMarkers(IResource resource, int depth) {
		try {
			resource.deleteMarkers(MARKER_TYPE, true, depth);
			resource.deleteMarkers(IMarker.PROBLEM, true, depth);
		} catch (CoreException e) {
			AddonsPlugin.getInstance().logError(
					"Failed to delete validation markers", e); //$NON-NLS-1$
		}
	}

	@Override
	public IMarker addMarker(IFile file, String elementId, String location, String message, int statusSeverity) {
		IMarker marker = null;

		try {
			marker = file.createMarker(MARKER_TYPE);						

			marker.setAttribute(IMarker.MESSAGE, message);			
			marker.setAttribute(IMarker.LOCATION, location);
			marker.setAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID,	elementId);


			int markerSeverity = IMarker.SEVERITY_INFO;
			if (statusSeverity == IStatus.WARNING) {
				markerSeverity = IMarker.SEVERITY_WARNING;
			} else if (statusSeverity == IStatus.ERROR || statusSeverity == IStatus.CANCEL) {
				markerSeverity = IMarker.SEVERITY_ERROR;
			}

			marker.setAttribute(IMarker.SEVERITY, markerSeverity);			
		} catch (Exception e) {
			AddonsPlugin.getInstance().logError("Failed to create validation marker", e); //$NON-NLS-1$
		}
		
		return marker;
	}

	/**
	 * Returns the instance of this marker navigation service.
	 * 
	 * @return instance
	 */
	public static DiagramMarkerNavigationProvider getInstance() {
		if (instance == null)
			instance = new DiagramMarkerNavigationProvider();

		return instance;
	}

}
