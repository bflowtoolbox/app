package org.bflow.toolbox.epc.templating.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;

/**
 * This class represents a a concrete bflow-Template for the before-insert-option. 
 * It provides the specialized connect methods for the before case.
 * 
 * @author Markus Schnaedelbach
 */
public class BflowTemplateBefore extends BflowTemplate {
	
	public BflowTemplateBefore(boolean local, File file, String templateName, ArrayList<ColoredNodeEditPart> baseModelSources, ArrayList<ColoredNodeEditPart> baseModelTargets) {
		super(local, file, templateName, baseModelSources,baseModelTargets);
	}

	/**
	 * Determines and returns the ConnectionType. 
	 * @param flag
	 * @return ConnectionType
	 */
	@SuppressWarnings("unchecked")
	private ConnectionType getConnectionType(String flag) {
		Object selectedElement = null;
		if (!this.baseModelTargets.isEmpty()) {
			selectedElement = this.baseModelTargets.get(0);
		}
		if (selectedElement instanceof FunctionEditPart && flag.equals(NORMAL_FLAG)) {
			FunctionEditPart fep = (FunctionEditPart) selectedElement;
			if (countArcs(fep.getTargetConnections()) == 0) {
				return ConnectionType.function_0;
			}else {
				return ConnectionType.function_1;
			}
		}
		if (selectedElement instanceof EventEditPart && flag.equals(DUMMY_FLAG)) {
			EventEditPart eep = (EventEditPart) selectedElement;
			if (countArcs(eep.getTargetConnections()) == 0) {
				return ConnectionType.event_0;
			}else {
				return ConnectionType.event_1;
			}
		}
		if (selectedElement instanceof ANDEditPart || selectedElement instanceof OREditPart || selectedElement instanceof XOREditPart) {
			ColoredNodeEditPart connector = (ColoredNodeEditPart) selectedElement;
			List<ColoredConnectionEditPart> targetConnections = connector.getTargetConnections();//eingehende
			if (countArcs(targetConnections) > 1){
				if (flag.equals(NORMAL_FLAG) && isValidEndOrder(connector, true)) {
						return ConnectionType.function_connector_n;					
				}
				if (flag.equals(DUMMY_FLAG) && isValidEndOrder(connector, false)) {
					return ConnectionType.event_connector_n;
				}
			}else {
				if (flag.equals(NORMAL_FLAG) && isValidEndOrder(connector, true)) {
					return ConnectionType.function_connector_1;
				}
				if (flag.equals(DUMMY_FLAG) && isValidEndOrder(connector, false)) {
					return ConnectionType.event_connector_1;
				}
			}	
		}
		return ConnectionType.unknown;
	}
	
	@Override
	protected boolean isValidForAction() {
		if (this.templateExits.size() == 1) {
			// Schaue ob das Eine unknown ist
			ConnectionType type = getFirstEndPointType();
			if (type != ConnectionType.unknown) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected String initConnectionType(String id, String name, String value) {
		if ("connect".equals(name.toLowerCase())) { //$NON-NLS-1$
			if (possibleTemplateExits.contains(id)) {
				value = value.toLowerCase();
				if (NORMAL_FLAG.equals(value)) {
					this.templateExits.put(id, getConnectionType(NORMAL_FLAG));
				}
				if (DUMMY_FLAG.equals(value)) {
					this.templateExits.put(id, getConnectionType(DUMMY_FLAG));
					this.dummyIds.add(id);
				}
			}
		}
		return value;
	}

	@Override
	protected void findPossibleConnectionPoints() {
		if (tplShapes == null || tplEdges == null) {
			loadModelData();
		}
		ArrayList<IShape> allNodes = new ArrayList<>(tplShapes);
		for (IEdge edge : tplEdges) {
			if ("org.bflow.toolbox.epc.Arc".equals(edge.getType().toString())) { //$NON-NLS-1$
				IShape source = edge.getSource();
				allNodes.remove(source);
			}
		}
		// Entferne alle Elemente die nicht relevant f?r die Verbindung sind
		removeNotConnectableElements(allNodes);
		
		for (IShape iShape : allNodes) {
			this.possibleTemplateExits.add(iShape.getId());
		}
	}

	@Override
	protected void initConnectionTypesWithoutFlag() {
		if (templateExits.isEmpty() && possibleTemplateExits.size() == 1) {
			String entryId = possibleTemplateExits.iterator().next();
			templateExits.put(entryId, getConnectionType(NORMAL_FLAG));
		}
	}
}
