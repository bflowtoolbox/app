package org.bflow.toolbox.hive.templating.dialogs;

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
 * Helps to embed a templatefile in a diagram-editpart for the before insert option.
 * 
 * @author Markus Schnädelbach
 */
public class TemplateEmbedBeforeService extends TemplateEmbedService {

	public TemplateEmbedBeforeService(DiagramDocumentEditor editor, BflowTemplate template, Point location) {
		super(editor, template, location);
	}

	@Override
	protected void connectDiagrams(DiagramEditPart diagramEditPart, IStructuredSelection selection, String id) {
		CompoundCommand creationCommand = new CompoundCommand(id);
		
		ConnectionType templateEndPoint = template.getFirstEndPointType();
		ColoredNodeEditPart shapeSrc = (ColoredNodeEditPart) findIShapeEditPartById(diagramEditPart, template.getFirstEndPointId());
		ColoredNodeEditPart shapeTrg = template.getFirstBaseModelTarget();
		
		if (templateEndPoint.isDummy()) {
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> trgConnection = shapeSrc.getTargetConnections();
			if (!trgConnection.isEmpty()) {
				creationCommand.add(getDeleteCommand(diagramEditPart, shapeSrc));
				shapeSrc = (ColoredNodeEditPart) trgConnection.get(0).getSource();
			}
		}
		if (templateEndPoint.needsConnector()) {
			
			boolean notForceConnector = isConnectorWithTwoOrMoreEntries(shapeSrc);

			ConnectorDialog dialog = new ConnectorDialog(editor.getSite().getShell(), notForceConnector);
			if (dialog.open() == Window.CANCEL) {
				return;
			}
			
			EditPart existingShape = null;
			@SuppressWarnings("unchecked")
			List<ColoredConnectionEditPart> trgConnections = shapeTrg.getTargetConnections();
			if (!trgConnections.isEmpty()) {
				ColoredConnectionEditPart oldEdge = trgConnections.get(0);
				existingShape = oldEdge.getSource();
				creationCommand.add(getDeleteCommand(diagramEditPart, oldEdge));
			}
			
			ConnectorType connectorType = dialog.getConnectortype();
			if (connectorType != ConnectorType.NOTHING) {
				CreateViewRequest connectorRequest = createConnector(diagramEditPart, connectorType, id);
				EObject connectorEObject = resolveCreatedEObject(connectorRequest);
				EditPart connectorEditPart = findEditPart(diagramEditPart, connectorEObject);
				
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, connectorEditPart,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, connectorEditPart, shapeTrg,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, existingShape, connectorEditPart,id));
			}else {
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, existingShape, shapeSrc,id));
				creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, shapeTrg,id));
			}	    
		}else {
			creationCommand.add(getEdgeCreateCommand(diagramEditPart, EpcElementTypes.Arc_4001, shapeSrc, shapeTrg,id));
		}
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(creationCommand);
	}
	
	private boolean isConnectorWithTwoOrMoreEntries(ColoredNodeEditPart shapeSrc) {
		if (shapeSrc instanceof OREditPart || shapeSrc instanceof XOREditPart || shapeSrc instanceof ANDEditPart) {
			List targetConnections = shapeSrc.getTargetConnections();
			if (targetConnections.size() > 1) {
				return true;
			}
		}
		return false;
	}


}
