package org.bflow.toolbox.hive.mbe;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements {@link IPartListener} to observe a workbench page and if a
 * {@link IWorkbenchPart} is activated. If this part is an instance of
 * {@link DiagramDocumentEditor} additional listeners will be added.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07.03.2015
 *
 */
public class MbeWorkbenchPagePartListener implements IPartListener {
	
	/**
	 * Static instance. Can be used because this implementation is stateless.
	 */
	public static final MbeWorkbenchPagePartListener Default = new MbeWorkbenchPagePartListener();
	
	private DiagramEditPart currentObservedDiagramEditPart;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partActivated(IWorkbenchPart part) { 
		if (!(part instanceof DiagramDocumentEditor)) return;
		
		DiagramDocumentEditor diagramDocumentEditor = (DiagramDocumentEditor) part;
		DiagramEditPart diagramEditPart = diagramDocumentEditor.getDiagramEditPart();
		if (diagramEditPart == null) return;
		
		if (currentObservedDiagramEditPart != null)
			currentObservedDiagramEditPart.removeEditPartListener(ActiveDiagramEditPartListener.Default);
		
		currentObservedDiagramEditPart = diagramEditPart;
		
		if (currentObservedDiagramEditPart != null) {
			currentObservedDiagramEditPart.addEditPartListener(ActiveDiagramEditPartListener.Default);
			ActiveDiagramEditPartListener.Default.addedToDiagramEditPart(diagramEditPart);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPart part) { }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void partClosed(IWorkbenchPart part) { }

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
