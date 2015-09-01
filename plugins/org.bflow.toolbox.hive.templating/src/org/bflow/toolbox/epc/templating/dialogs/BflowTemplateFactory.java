package org.bflow.toolbox.epc.templating.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Factory for Bflowtemplates.
 * If the insert option is before a new BflowTemplateBefore will be initialed.
 * If the insert option is after a new BflowTemplateAfter will be initialed.
 * If the insert option is replace a new BflowTemplateReplace will be initialed.
 * 
 * @author Markus Schnaedelbach
 */
public class BflowTemplateFactory {
	
	private  ArrayList<ColoredNodeEditPart>  baseModelSources = new ArrayList<>();
	private  ArrayList<ColoredNodeEditPart>  baseModelTargets = new ArrayList<>();

	public BflowTemplate createNewTemplate(TemplateAction action, boolean local, File file, String filename, IStructuredSelection selection) {
		switch (action) {
		case before:
			baseModelSources.add((ColoredNodeEditPart) selection.getFirstElement());
			baseModelTargets.add((ColoredNodeEditPart) selection.getFirstElement());
			return new BflowTemplateBefore(local, file, filename, baseModelSources, baseModelTargets);
		case insert:
			if (baseModelSources.isEmpty() && baseModelTargets.isEmpty()) {
				findBaseModelInsertPoints(selection, baseModelSources, baseModelTargets);	
			}
			return new BflowTemplateReplace(local, file, filename, baseModelSources, baseModelTargets, selection);
		case after:
			baseModelSources.add((ColoredNodeEditPart) selection.getFirstElement());
			baseModelTargets.add((ColoredNodeEditPart) selection.getFirstElement());
			return new BflowTemplateAfter(local, file, filename, baseModelSources, baseModelTargets);
		default:
			break;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void findBaseModelInsertPoints(IStructuredSelection selection, ArrayList<ColoredNodeEditPart> baseModelSources,
			ArrayList<ColoredNodeEditPart> baseModelTargets) {
		
		if (selection.size() == 1) {
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof ArcEditPart) {
				ArcEditPart arc = (ArcEditPart) firstElement;
				baseModelSources.add((ColoredNodeEditPart) arc.getSource());
				baseModelTargets.add((ColoredNodeEditPart) arc.getTarget());
				return;
			}
		}
				
		for (Object element : selection.toArray()) {
			if (element instanceof ColoredNodeEditPart) {
				ColoredNodeEditPart node = (ColoredNodeEditPart) element;
				List<ColoredConnectionEditPart> nodeSrcs = node.getSourceConnections();// ausgehende
				List<ColoredConnectionEditPart> nodeTrgs = node.getTargetConnections();// eingehende
				for (ColoredConnectionEditPart con : nodeTrgs) {
					if (con instanceof ArcEditPart) {
						ColoredNodeEditPart source = (ColoredNodeEditPart) con.getSource();
						if (!selectionContainsNode(source, selection)) {
							baseModelSources.add(source);
						}
					}
				}
				for (ColoredConnectionEditPart con : nodeSrcs) {
					if (con instanceof ArcEditPart) {
						ColoredNodeEditPart target = (ColoredNodeEditPart) con.getTarget();
						if (!selectionContainsNode(target, selection)) {
							baseModelTargets.add(target);
						}
					}
				}
			}
		}
	}
	
	private boolean selectionContainsNode(ColoredNodeEditPart node, IStructuredSelection selection) {
		for (Object element : selection.toArray()) {
			if (node.equals(element)) {
				return true;
			}
		}
		return false;
	}
}
