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
 * This class represents a a concrete bflow-Template for the after-insert-option. 
 * It provides the specialized connect methods for the after case.
 * 
 * @author Markus Schnaedelbach
 */
public class BflowTemplateAfter extends BflowTemplate {
	
	public BflowTemplateAfter(boolean local, File file, String templateName, ArrayList<ColoredNodeEditPart> baseModelSources, ArrayList<ColoredNodeEditPart> baseModelTargets) {
		super(local, file, templateName, baseModelSources, baseModelTargets);
	}

	/**
	 * Determines and returns the ConnectionType. 
	 * @param flag
	 * @return ConnectionType
	 */
	@SuppressWarnings("unchecked")
	private ConnectionType getConnectionType(String flag) {
		Object selectedElement = null;
		if (!this.baseModelSources.isEmpty()) {
			selectedElement = this.baseModelSources.get(0);
		}
		if (selectedElement instanceof FunctionEditPart && flag.equals(NORMAL_FLAG)) {
			FunctionEditPart fep = (FunctionEditPart) selectedElement;
			if (countArcs(fep.getSourceConnections()) == 0) {
				return ConnectionType.function_0;
			}else {
				return ConnectionType.function_1;
			}
		}
		if (selectedElement instanceof FunctionEditPart && (flag.equals(DUMMY_FLAG))) {
			FunctionEditPart fep = (FunctionEditPart) selectedElement;
			if (countArcs(fep.getSourceConnections()) == 0) {
				return ConnectionType.function_0_dummy;
			}else {
				return ConnectionType.function_1_dummy;
			}
		}
		if (selectedElement instanceof EventEditPart && flag.equals(DUMMY_FLAG)) {
			EventEditPart eep = (EventEditPart) selectedElement;
			if (countArcs(eep.getSourceConnections()) == 0) {
				return ConnectionType.event_0;
			}else {
				return ConnectionType.event_1;
			}
		}
		if (selectedElement instanceof ANDEditPart || selectedElement instanceof OREditPart || selectedElement instanceof XOREditPart) {
			ColoredNodeEditPart connector = (ColoredNodeEditPart) selectedElement;
			List<ColoredConnectionEditPart> sourceConnections = connector.getSourceConnections();//ausgehende
			if (countArcs(sourceConnections) > 1){
				if (flag.equals(NORMAL_FLAG) && isValidStartOrder(connector, true)) {
						return ConnectionType.function_connector_n;					
				}
				if (flag.equals(DUMMY_FLAG) && isValidStartOrder(connector, false)) {
					return ConnectionType.event_connector_n;
				}
			}else {
				if (flag.equals(NORMAL_FLAG) && isValidStartOrder(connector, true)) {
					return ConnectionType.function_connector_1;
				}
				if (flag.equals(DUMMY_FLAG) && isValidStartOrder(connector, false)) {
					return ConnectionType.event_connector_1;
				}
			}	
		}
		return ConnectionType.unknown;
	}
	
	@Override
	protected boolean isValidForAction() {
		if (this.templateEntries.size() == 1) {
			// Schaue ob das Eine unknown ist
			ConnectionType type = getFirstStartPointType();
			if (type != ConnectionType.unknown) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected String initConnectionType(String id, String name, String value) {
		if ("connect".equals(name.toLowerCase())) { //$NON-NLS-1$
			if (possibleTemplateEntries.contains(id)) {
				value = value.toLowerCase();
				if (NORMAL_FLAG.equals(value)) {
					this.templateEntries.put(id, getConnectionType(NORMAL_FLAG));
				}
				if (DUMMY_FLAG.equals(value)) {
					this.templateEntries.put(id, getConnectionType(DUMMY_FLAG));
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
				IShape target = edge.getTarget();
				allNodes.remove(target);
			}
		}
		// Entferne alle Elemente die nicht relevant f?r die Verbindung sind
		removeNotConnectableElements(allNodes);
		
		for (IShape iShape : allNodes) {
			this.possibleTemplateEntries.add(iShape.getId());
		}
	}	
	
	@Override
	protected void initConnectionTypesWithoutFlag() {
		if (templateEntries.isEmpty() && possibleTemplateEntries.size() == 1) {
				String entryId = possibleTemplateEntries.iterator().next();
				templateEntries.put(entryId, getConnectionType(NORMAL_FLAG));
		}
	}
}
