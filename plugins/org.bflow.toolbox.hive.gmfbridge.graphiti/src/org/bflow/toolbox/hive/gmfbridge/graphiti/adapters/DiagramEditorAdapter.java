package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;

/**
 * Implements an adapter for
 * {@link org.eclipse.graphiti.ui.editor.DiagramEditor}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public class DiagramEditorAdapter extends DiagramEditor {
	
	org.eclipse.graphiti.ui.editor.DiagramEditor fGraphitiDiagramEditor;
	
	/**
	 * Creates a new instance based on the given graphiti editor.
	 * 
	 * @param graphitiEditor
	 */
	public DiagramEditorAdapter(org.eclipse.graphiti.ui.editor.DiagramEditor graphitiEditor) {
		fGraphitiDiagramEditor = graphitiEditor;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		throw new RuntimeException("#AS not implemented yet");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getDiagramEditPart()
	 */
	@Override
	public DiagramEditPart getDiagramEditPart() {
		DiagramBehavior diagramBehaviour = fGraphitiDiagramEditor.getDiagramBehavior();
		EditPart editPart = diagramBehaviour.getContentEditPart();
		
		@SuppressWarnings("restriction")
		org.eclipse.graphiti.ui.internal.parts.DiagramEditPart graphitiDiagramEditPart = (org.eclipse.graphiti.ui.internal.parts.DiagramEditPart) editPart;
		return new DiagramEditPartAdapter(graphitiDiagramEditPart, fGraphitiDiagramEditor.getGraphicalViewer());
	}
}