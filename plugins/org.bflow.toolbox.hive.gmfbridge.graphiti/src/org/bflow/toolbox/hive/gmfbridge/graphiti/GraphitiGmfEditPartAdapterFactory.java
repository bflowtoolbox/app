package org.bflow.toolbox.hive.gmfbridge.graphiti;

import java.util.HashMap;

import org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.ConnectionEditPartAdapter;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.DiagramEditorAdapter;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.ShapeEditPartAdapter;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Provides an implementation of {@link IGmfEditPartAdapterFactory} to adapt
 * graphiti edit parts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
@SuppressWarnings("restriction")
public class GraphitiGmfEditPartAdapterFactory implements IGmfEditPartAdapterFactory {
	
	private final HashMap<IEditorPart, DiagramEditor> fDiagramEditorMap = new HashMap<>();
	private final HashMap<GraphicalEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart> fGraphicalEditPartMap = new HashMap<>();

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#canAdapt(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean canAdapt(IEditorPart editorPart) {
		boolean canAdapt = (org.eclipse.graphiti.ui.editor.DiagramEditor.class.isAssignableFrom(editorPart.getClass()));
		return canAdapt;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#getAdapter(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public DiagramEditor getAdapter(IEditorPart editorPart) {
		DiagramEditor cachedEditor = fDiagramEditorMap.get(editorPart);
		if (cachedEditor == null) {
			cachedEditor = new DiagramEditorAdapter((org.eclipse.graphiti.ui.editor.DiagramEditor) editorPart);
			fDiagramEditorMap.put(editorPart, cachedEditor);
		}
		return cachedEditor;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#canAdapt(org.eclipse.gef.GraphicalEditPart)
	 */
	@Override
	public boolean canAdapt(GraphicalEditPart graphicalEditPart) {
		if (org.eclipse.graphiti.ui.internal.parts.ShapeEditPart.class.isAssignableFrom(graphicalEditPart.getClass())) return true;
		if (org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart.class.isAssignableFrom(graphicalEditPart.getClass())) return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#getAdapter(org.eclipse.gef.GraphicalEditPart)
	 */
	@Override
	public org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart getAdapter(GraphicalEditPart graphicalEditPart) {
		org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart cachedGraphicalEditPart = fGraphicalEditPartMap.get(graphicalEditPart);
		if (cachedGraphicalEditPart == null) {
			if (graphicalEditPart instanceof org.eclipse.graphiti.ui.internal.parts.ShapeEditPart)
				cachedGraphicalEditPart = new ShapeEditPartAdapter(graphicalEditPart);
			
			if (graphicalEditPart instanceof org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart)
				cachedGraphicalEditPart = null; //new ConnectionEditPartAdapter(graphicalEditPart);
			
			fGraphicalEditPartMap.put(graphicalEditPart, cachedGraphicalEditPart);
		}
		return cachedGraphicalEditPart;
	}
}
