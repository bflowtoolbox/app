package org.bflow.toolbox.epc.templating.dialogs;

import java.util.List;

import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;

/**
 * Helps to embed a templatefile in a diagram-editpart for the after insert option.
 * 
 * @author Markus Schnädelbach
 */
public class TemplateEmbedAfterService extends TemplateEmbedService {

	public TemplateEmbedAfterService(DiagramDocumentEditor editor, BflowTemplate template, Point location) {
		super(editor, template, location);
	}

	@Override
	protected void connectDiagrams(DiagramEditPart diagramEditPart, IStructuredSelection selection, String id) {
		CompoundCommand creationCommand = new CompoundCommand(id);
		
		ConnectionType templateStartPoint = template.getFirstStartPointType();
		
		ColoredNodeEditPart shapeSrc = template.getFirstBaseModelSource();
		ColoredNodeEditPart shapeTrg = (ColoredNodeEditPart) findIShapeEditPartById(diagramEditPart, template.getFirstStartPointId());
		if (templateStartPoint.isDummy()) {
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> sourceConnection = shapeTrg.getSourceConnections(); //ausgehende
			if (!sourceConnection.isEmpty()) {
				creationCommand.add(getDeleteCommand(diagramEditPart, shapeTrg));
				shapeTrg = (ColoredNodeEditPart) sourceConnection.get(0).getTarget();
			}
		}
		if (templateStartPoint.needsConnector()) {
			boolean notForceConnector = isConnectorWithTwoOrMoreExits(shapeTrg);
			ConnectorDialog dialog = new ConnectorDialog(editor.getSite().getShell(), notForceConnector);
			if (dialog.open() == Window.CANCEL) {
				return;
			}	
			EditPart existingShape = null;
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> srcConnections = shapeSrc.getSourceConnections();
			if (!srcConnections.isEmpty()) {
				ColoredConnectionEditPart oldEdge = srcConnections.get(0);
				existingShape = oldEdge.getTarget();
				creationCommand.add(getDeleteCommand(diagramEditPart, oldEdge));
			}
			ConnectorType connectorType = dialog.getConnectortype();
			if (connectorType != ConnectorType.NOTHING) {
				CreateViewRequest connectorRequest = createConnector(diagramEditPart, connectorType, id);
				EObject connectorEObject = resolveCreatedEObject(connectorRequest);
				EditPart connectorEditPart = findEditPart(diagramEditPart, connectorEObject);
				
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, connectorEditPart,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, connectorEditPart, shapeTrg,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, connectorEditPart, existingShape,id));
			}else {
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeTrg, existingShape,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, shapeTrg,id));
			}	    
		}else {
			creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, shapeTrg,id));
		}
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(creationCommand);
	}

	private boolean isConnectorWithTwoOrMoreExits(ColoredNodeEditPart shape) {
		if (shape instanceof OREditPart || shape instanceof XOREditPart || shape instanceof ANDEditPart) {
			List<?> sourceConnections = shape.getSourceConnections();
			if (sourceConnections.size() > 1) {
				return true;
			}
		}
		return false;
	}

}
