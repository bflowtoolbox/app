package org.bflow.toolbox.epc.diagram.providers;

import java.util.Arrays;
import java.util.Map;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil;
import org.bflow.toolbox.extensions.mitamm.IMitammMarkerProvider;
import org.bflow.toolbox.extensions.mitamm.MitammMarkerService;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.ui.providers.marker.AbstractModelMarkerNavigationProvider;

/**
 * @generated
 * @modyfied by Arian Storch 17/04/10
 */
public class EpcMarkerNavigationProvider extends
		AbstractModelMarkerNavigationProvider implements IMitammMarkerProvider {
	
	/**
	 * @generated
	 */
	public static final String MARKER_TYPE = EpcDiagramEditorPlugin.ID
			+ ".diagnostic"; //$NON-NLS-1$
	
	/**
	 * instance of this class
	 */
	private static EpcMarkerNavigationProvider instance;
	
	/**
	 * Constructor; <br/>
	 * Needed to register it to the toolchain.
	 */
	public EpcMarkerNavigationProvider()	{	
		MitammMarkerService.registerProvider(this);
	}	

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	protected void doGotoMarker(IMarker marker) {
		String elementId = marker
				.getAttribute(
						org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID,
						null);
		if (elementId == null || !(getEditor() instanceof DiagramEditor)) {
			return;
		}
		DiagramEditor editor = (DiagramEditor) getEditor();
		Map editPartRegistry = editor.getDiagramGraphicalViewer()
				.getEditPartRegistry();
		EObject targetView = editor.getDiagram().eResource().getEObject(
				elementId);
		if (targetView == null) {
			return;
		}
		EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);
		if (targetEditPart != null) {
			EpcDiagramEditorUtil.selectElementsInDiagram(editor, Arrays
					.asList(new EditPart[] { targetEditPart }));
		}
	}

	/**
	 * @generated
	 */
	public void deleteMarkers(IResource resource) {
		try {
			resource.deleteMarkers(MARKER_TYPE, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			EpcDiagramEditorPlugin.getInstance().logError(
					"Failed to delete validation markers", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * deletes all markes within the resource
	 * @param resource resource
	 * @param depth IResource.DEPTH_INFINITE, IResource.DEPTH_ZERO, ...
	 */
	public void deleteMarkers(IResource resource, int depth)
	{		
		try {
			resource.deleteMarkers(MARKER_TYPE, true, depth);
		} catch (CoreException e) {
			EpcDiagramEditorPlugin.getInstance().logError(
					"Failed to delete validation markers", e); //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	public IMarker addMarker(IFile file, String elementId,
			String location, String message, int statusSeverity) {
		IMarker marker = null;
		try {
			marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.LOCATION, location);
			marker
					.setAttribute(
							org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID,
							elementId);
			int markerSeverity = IMarker.SEVERITY_INFO;
			if (statusSeverity == IStatus.WARNING) {
				markerSeverity = IMarker.SEVERITY_WARNING;
			} else if (statusSeverity == IStatus.ERROR
					|| statusSeverity == IStatus.CANCEL) {
				markerSeverity = IMarker.SEVERITY_ERROR;
			}
			marker.setAttribute(IMarker.SEVERITY, markerSeverity);
		} catch (CoreException e) {
			EpcDiagramEditorPlugin.getInstance().logError(
					"Failed to create validation marker", e); //$NON-NLS-1$
		}
		return marker;
	}
	
	/**
	 * Returns the instance of the class.
	 * @return instance of this class
	 */
	public static EpcMarkerNavigationProvider getInstance() 
	{
		if(instance == null)
			instance = new EpcMarkerNavigationProvider();
		
		return instance;
	}
}
