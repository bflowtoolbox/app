package org.bflow.toolbox.hive.gmfbridge.graphiti;

import java.util.HashMap;

import org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.ConnectionEditPartAdapter;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.DiagramEditPartAdapter;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.DiagramEditorAdapter;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.IDisposable;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.ShapeEditPartAdapter;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;

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
	private final HashMap<GraphicalEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart> fGraphicalEditPartMap = new HashMap<>();
	
	/**
	 * Default constructor.
	 */
	public GraphitiGmfEditPartAdapterFactory() {
		// Register listener for cache clean up
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(new _PartListener());
	}

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
	public org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart getAdapter(GraphicalEditPart graphicalEditPart) {
		org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart cachedGraphicalEditPart = fGraphicalEditPartMap.get(graphicalEditPart);
		if (cachedGraphicalEditPart == null) {
			if (graphicalEditPart instanceof org.eclipse.graphiti.ui.internal.parts.DiagramEditPart) 
				cachedGraphicalEditPart = new DiagramEditPartAdapter((org.eclipse.graphiti.ui.internal.parts.DiagramEditPart) graphicalEditPart);
			else if (graphicalEditPart instanceof org.eclipse.graphiti.ui.internal.parts.ShapeEditPart)
				cachedGraphicalEditPart = new ShapeEditPartAdapter(graphicalEditPart);
			else if (graphicalEditPart instanceof org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart)
				cachedGraphicalEditPart = new ConnectionEditPartAdapter(graphicalEditPart);
			
			fGraphicalEditPartMap.put(graphicalEditPart, cachedGraphicalEditPart);
		}
		return cachedGraphicalEditPart;
	}
	
	/**
	 * Implements {@link IPartListener}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 11.04.2015
	 * 
	 */
	class _PartListener implements IPartListener {
		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
		 */
		@Override
		public void partActivated(IWorkbenchPart part) { }

		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
		 */
		@Override
		public void partBroughtToTop(IWorkbenchPart part) {	}

		@Override
		public void partClosed(IWorkbenchPart part) {
			if (!(part instanceof IEditorPart)) return;
			IEditorPart editorPart = (IEditorPart)part;
			
			// Handling multi page editor
			if (editorPart instanceof MultiPageEditorPart) {
				MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorPart;
				editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
			}
			
			DiagramEditor cachedDiagramEditor = fDiagramEditorMap.get(editorPart);
			if (cachedDiagramEditor == null) return;
			
			for (GraphicalEditPart graphicalEditPart : fGraphicalEditPartMap.values()) {
				if (!(graphicalEditPart instanceof IDisposable)) continue;
				IDisposable disposableObject = (IDisposable)graphicalEditPart;
				disposableObject.dispose();
			}				
			
			// TODO Don't clear all up - some other editors might still be open
			fDiagramEditorMap.clear();
			fGraphicalEditPartMap.clear();				
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
		 */
		@Override
		public void partDeactivated(IWorkbenchPart part) { }

		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
		 */
		@Override
		public void partOpened(IWorkbenchPart part) { }
	}
}
