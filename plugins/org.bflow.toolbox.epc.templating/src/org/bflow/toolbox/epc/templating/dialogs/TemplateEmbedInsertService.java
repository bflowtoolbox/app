package org.bflow.toolbox.epc.templating.dialogs;

import java.util.List;

import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Point;

/**
 * Helps to embed a templatefile in a diagram-editpart for the replace insert option.
 * 
 * @author Markus Schnädelbach
 */
public class TemplateEmbedInsertService extends TemplateEmbedService {

	public TemplateEmbedInsertService(DiagramDocumentEditor editor, BflowTemplate template, Point location) {
		super(editor, template, location);
	}

	@Override
	protected void connectDiagrams(DiagramEditPart diagramEditPart, IStructuredSelection selection, String id) {
		
		CompoundCommand removeShapesCommand = new CompoundCommand(id);
		
		ConnectionType templateStartPoint = template.getFirstStartPointType();
		ConnectionType templateEndPoint = template.getFirstEndPointType();
		ColoredNodeEditPart shapeStartSrc = template.getFirstBaseModelSource();
		ColoredNodeEditPart shapeStartTrg = (ColoredNodeEditPart) findIShapeEditPartById(diagramEditPart, template.getFirstStartPointId());
		ColoredNodeEditPart shapeEndSrc = (ColoredNodeEditPart) findIShapeEditPartById(diagramEditPart, template.getFirstEndPointId());
		ColoredNodeEditPart shapeEndTrg = template.getFirstBaseModelTarget();

		addRemoveSelectionCommand(selection, removeShapesCommand, diagramEditPart);
		if (templateStartPoint.isDummy()) {
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> sourceConnection = shapeStartTrg.getSourceConnections(); // ausgehende
			if (!sourceConnection.isEmpty()) {
				removeShapesCommand.add(getDeleteCommand(diagramEditPart, shapeStartTrg));
				shapeStartTrg = (ColoredNodeEditPart) sourceConnection.get(0).getTarget();
			}
		}

		if (templateEndPoint.isDummy()) {
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> trgConnection = shapeEndSrc.getTargetConnections();
			if (!trgConnection.isEmpty()) {
				removeShapesCommand.add(getDeleteCommand(diagramEditPart, shapeEndSrc));
				shapeEndSrc = (ColoredNodeEditPart) trgConnection.get(0).getSource();
			}
		}
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(removeShapesCommand);
		
		CompoundCommand connectCommand = new CompoundCommand(id);
		if (shapeStartSrc != null) {
			connectCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeStartSrc, shapeStartTrg, id));
		}
		if (shapeEndTrg != null) {
			connectCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeEndSrc, shapeEndTrg, id));
		}
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(connectCommand);
	}

	private void addRemoveSelectionCommand(IStructuredSelection selection, CompoundCommand removeCommand, DiagramEditPart diagramEditPart) {
		if (isOneNode(selection)) {
			BflowNodeEditPart  node = (BflowNodeEditPart) selection.toArray()[0];
			List source_edges = node.getSourceConnections();
			List target_edges = node.getTargetConnections();
			for (Object object : target_edges) {
				if (object instanceof ArcEditPart) {
					ArcEditPart edge = (ArcEditPart) object;
					removeCommand.add(getDeleteCommand(diagramEditPart, edge));
				}
			}
			
			for (Object object : source_edges) {
				if (object instanceof ArcEditPart) {
					ArcEditPart edge = (ArcEditPart) object;
					removeCommand.add(getDeleteCommand(diagramEditPart, edge));
				}
			}
			removeCommand.add(getDeleteCommand(diagramEditPart, node));
			return;
		}
		
		for (Object element : selection.toArray()) {
			if (element instanceof INodeEditPart && element instanceof IGraphicalEditPart) {
				IGraphicalEditPart ep =  (IGraphicalEditPart) element;
				removeCommand.add(getDeleteCommand(diagramEditPart, ep));
			}
		}
	}

	private boolean isOneNode(IStructuredSelection selection) {
		int count = 0;
		for (Object element : selection.toArray()) {
			if (count > 1) {
				return false;
			}
			if (element instanceof FunctionEditPart || element instanceof EventEditPart) {
				count++;
			}
		}
		if (count == 1 && selection.toArray().length == 1) {
			return true;
		}
		
		return false;
	}


	
}
