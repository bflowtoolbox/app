package org.bflow.toolbox.epc.templating.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * This class represents a a concrete bflow-Template for the replace-option. 
 * It provides the specialized connect methods for the replace case.
 * 
 * @author Markus Schnaedelbach
 */
public class BflowTemplateReplace extends BflowTemplate {
	
	private IStructuredSelection selection;
	

	public BflowTemplateReplace(boolean local, File file, String templateName, ArrayList<ColoredNodeEditPart> baseModelSources, ArrayList<ColoredNodeEditPart> baseModelTargets, IStructuredSelection selection) {
		super(local, file, templateName, baseModelSources, baseModelTargets);
		this.selection = selection;		
	}
	
	/**
	 * Determines and returns the ConnectionType of the template start note. 
	 * @param flag
	 * @return ConnectionType
	 */
	private ConnectionType getStartConnectionType(String flag) {
		Object selectedElement = null;
		if (!this.baseModelSources.isEmpty()) {
			selectedElement = this.baseModelSources.get(0);
		}else{
			return ConnectionType.empty;
		}
			
		if (selectedElement instanceof FunctionEditPart && flag.equals(NORMAL_FLAG)) {
				return ConnectionType.function_0;
		}
		if (selectedElement instanceof EventEditPart && flag.equals(DUMMY_FLAG)) {
				return ConnectionType.event_0;
		}
		if (selectedElement instanceof ANDEditPart || selectedElement instanceof OREditPart || selectedElement instanceof XOREditPart) {
			ColoredNodeEditPart connector = (ColoredNodeEditPart) selectedElement;
				if (flag.equals(NORMAL_FLAG) && isValidStartOrder(connector, true)) {
						return ConnectionType.function_connector_n;					
				}
				if (flag.equals(DUMMY_FLAG) && isValidStartOrder(connector, false)) {
					return ConnectionType.event_connector_n;
				}
		}
		return ConnectionType.unknown;
	}
	
	/**
	 * Determines and returns the ConnectionType of the template end note. 
	 * @param flag
	 * @return ConnectionType
	 */
	private ConnectionType getEndConnectionType(String flag) {
		Object selectedElement = null;
		if (!this.baseModelTargets.isEmpty()) {
			selectedElement = this.baseModelTargets.get(0);
		}else{
			return ConnectionType.empty;
		}
		if (selectedElement instanceof FunctionEditPart && flag.equals(NORMAL_FLAG)) {
				return ConnectionType.function_0;
		}
		if (selectedElement instanceof EventEditPart && flag.equals(DUMMY_FLAG)) {
				return ConnectionType.event_0;
		}
		if (selectedElement instanceof ANDEditPart || selectedElement instanceof OREditPart || selectedElement instanceof XOREditPart) {
			ColoredNodeEditPart connector = (ColoredNodeEditPart) selectedElement;
				if (flag.equals(NORMAL_FLAG) && isValidEndOrder(connector, true)) {
						return ConnectionType.function_connector_n;					
				}
				if (flag.equals(DUMMY_FLAG) && isValidEndOrder(connector, false)) {
					return ConnectionType.event_connector_n;
				}
		}
		return ConnectionType.unknown;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected boolean isValidForAction() {
		//test ob Auswahl ein zusammenhängender Graph
		ArrayList<BflowNodeEditPart> allEventsandFunctions = new ArrayList<BflowNodeEditPart>();
		for (Object element : selection.toList()) {
			if (element instanceof FunctionEditPart || element instanceof EventEditPart) {
				allEventsandFunctions.add((BflowNodeEditPart) element);
			}
		}
		
		if (!allEventsandFunctions.isEmpty()) {
			
			HashSet<BflowNodeEditPart> relevantNodes = new HashSet<BflowNodeEditPart>(allEventsandFunctions);
			Stack<BflowNodeEditPart> stack = new Stack<BflowNodeEditPart>();
			BflowNodeEditPart startNode = allEventsandFunctions.get(0);
			relevantNodes.remove(startNode);
			stack.push(startNode);
			
			while (!stack.isEmpty()) {
				List<ColoredConnectionEditPart> targets = stack.pop().getTargetConnections();// eingehende
				
				for (ColoredConnectionEditPart con : targets) {
					if (con instanceof ArcEditPart) {
						ColoredNodeEditPart source = (ColoredNodeEditPart) con.getSource();
						if (allEventsandFunctions.contains(source)) {
							if (relevantNodes.contains(source)) {//wenn ja dann noch nicht besucht
								relevantNodes.remove(source);
								stack.push((BflowNodeEditPart) source);
							}
						}
					}
				}
			}
			
//			relevantNodes = new HashSet<BflowNodeEditPart>(allEventsandFunctions);
			stack.push(startNode);
			while (!stack.isEmpty()) {
				List<ColoredConnectionEditPart> sources = stack.pop().getSourceConnections();// ausgehende
				
				for (ColoredConnectionEditPart con : sources) {
					if (con instanceof ArcEditPart) {
						ColoredNodeEditPart target = (ColoredNodeEditPart) con.getTarget();
						if (allEventsandFunctions.contains(target)) {
							if (relevantNodes.contains(target)) {//wenn ja dann noch nicht besucht
								relevantNodes.remove(target);
								stack.push((BflowNodeEditPart) target);
							}
						}
					}
				}
			}
			
			if (!relevantNodes.isEmpty()) {
				return false; // nicht alle Knoten konnten besucht werden, Auswahl ist nicht zusammenhängend
			}
		}
		
		
		if ((this.baseModelTargets.isEmpty() || this.baseModelSources.isEmpty())) {
			if (this.selection.size() == 1 && this.selection.getFirstElement() instanceof EpcEditPart) {
				return true; // einfügen ohne Verknüpfung
			}
			if (baseModelSources.isEmpty() && baseModelTargets.size()==1 && templateExits.size() == 1 && getFirstEndPointType() != ConnectionType.unknown) {
				return true;
			}
			if (baseModelTargets.isEmpty() && baseModelSources.size() == 1 && templateEntries.size() == 1 && getFirstStartPointType() != ConnectionType.unknown) {
				return true;
			}

		}

		if (getFirstEndPointType() == ConnectionType.unknown || getFirstStartPointType() == ConnectionType.unknown) {
			return false;
		}

		
		if (this.selection.size() == 1 && this.selection.getFirstElement() instanceof ArcEditPart) {
			return true;
		}
		if (baseModelTargets.size()==1 && templateExits.size() == 1 && baseModelSources.size() == 1 && templateEntries.size() == 1) {
			return true;
		}

		return false;
	}

	@Override
	protected String initConnectionType(String id, String name, String value) {
		if ("connect".equals(name.toLowerCase())) { //$NON-NLS-1$
			if (possibleTemplateEntries.contains(id)) {
				value = value.toLowerCase();
				if (NORMAL_FLAG.equals(value)) {
					this.templateEntries.put(id, getStartConnectionType(NORMAL_FLAG));
				}
				if (DUMMY_FLAG.equals(value)) {
					this.templateEntries.put(id, getStartConnectionType(DUMMY_FLAG));
					this.dummyIds.add(id);
				}
			}
		}

		if ("connect".equals(name.toLowerCase())) { //$NON-NLS-1$
			if (possibleTemplateExits.contains(id)) {
				value = value.toLowerCase();
				if (NORMAL_FLAG.equals(value)) {
					this.templateExits.put(id, getEndConnectionType(NORMAL_FLAG));
				}
				if (DUMMY_FLAG.equals(value)) {
					this.templateExits.put(id, getEndConnectionType(DUMMY_FLAG));
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
		ArrayList<IShape> allNodesExits = new ArrayList<>(tplShapes);
		ArrayList<IShape> allNodesEntries = new ArrayList<>(tplShapes);
		for (IEdge edge : tplEdges) {
			if ("org.bflow.toolbox.epc.Arc".equals(edge.getType().toString())) { //$NON-NLS-1$
				IShape source = edge.getSource();
				IShape target = edge.getTarget();
				allNodesExits.remove(source);
				allNodesEntries.remove(target);
			}
		}
		// Entferne alle Elemente die nicht relevant f?r die Verbindung sind
		removeNotConnectableElements(allNodesExits);
		removeNotConnectableElements(allNodesEntries);
		
		for (IShape iShape : allNodesExits) {
			this.possibleTemplateExits.add(iShape.getId());
		}
		for (IShape iShape : allNodesEntries) {
			this.possibleTemplateEntries.add(iShape.getId());
		}
	}
	
	@Override
	protected void initConnectionTypesWithoutFlag() {

		if (templateEntries.isEmpty() || templateExits.isEmpty()) {
			if (templateEntries.isEmpty() && possibleTemplateEntries.size() == 1) {
				String entryId = possibleTemplateEntries.iterator().next();
				templateEntries.put(entryId, getStartConnectionType(NORMAL_FLAG));
			}
			if (templateExits.isEmpty() && possibleTemplateExits.size() == 1) {
				String entryId = possibleTemplateExits.iterator().next();
				templateExits.put(entryId, getEndConnectionType(NORMAL_FLAG));
			}
		}
	}
}
