package org.bflow.toolbox.extensions.edit.parts.policies;

import java.util.HashMap;
import java.util.Stack;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Policy for providing an Attribute-ToolTipInfromation to IGraphicalEditparts 
 * @author Markus Schnaedelbach
 */
public class SelectionFeedbackEditPolicy extends GraphicalEditPolicy {
	public void showTargetFeedback(Request request) 
	{
		IFigure figure = getHostFigure();
		IGraphicalEditPart ep = (IGraphicalEditPart) getHost();
		EObject eObj = getEObject(ep);
		String id = EMFCoreUtil.getProxyID(eObj);
		AttributeFile attrFile = AttributeFileRegistry.getInstance().getActiveAttributeFile();

		HashMap<String, String> map = null;
		if (attrFile != null) {
			map = attrFile.get(id);
		}

		String newline = System.getProperty("line.separator"); // plattformunabhängig
		String tooltipp = "";

		if (map != null) {
			for (String name : map.keySet()) {
				tooltipp = tooltipp + name + "   " + map.get(name) + newline;
			}
			if (tooltipp.endsWith(newline)) {
				int lastNewlineIndex = tooltipp.lastIndexOf(newline);
				tooltipp = tooltipp.substring(0, lastNewlineIndex);
			}
		}

		String name = getLabelText(figure);
		if (name.isEmpty()) {
			if (!tooltipp.isEmpty()) {
				figure.setToolTip(new Label(tooltipp));
			}
		} else {
			if (tooltipp.isEmpty()) {
				figure.setToolTip(new Label(name));
			}else {
				figure.setToolTip(new Label(name + newline + newline + tooltipp));
			}
		}
	}

	private String getLabelText(IFigure figure) {
		 Stack<Object> containedIfigures = new Stack<>();
		containedIfigures.addAll(figure.getChildren());

		while (!containedIfigures.isEmpty()) {
			Object object = containedIfigures.pop();
			if (object instanceof WrappingLabel) {
				WrappingLabel label = (WrappingLabel) object;
				return label.getText();
			}
			if (object instanceof IFigure) {
				IFigure ifigure = (IFigure) object;
				containedIfigures.addAll(ifigure.getChildren());
			}
		}
		return "";
	}

	/**
	 * Returns the EObject defined by the given graphical edit part or null if
	 * none could be found.
	 * @param graphicalEditPart edit part whose EObject shall be found
	 * @return associated EObject or null
	 */
	private EObject getEObject(IGraphicalEditPart graphicalEditPart) {
		if (graphicalEditPart instanceof DiagramEditPart) {
			DiagramEditPart diagramEditPart = (DiagramEditPart) graphicalEditPart;
			return diagramEditPart.getNotationView().getElement();
		}
		if (graphicalEditPart instanceof ShapeNodeEditPart || graphicalEditPart instanceof ConnectionNodeEditPart) {
			return graphicalEditPart.getPrimaryView().getElement();
		}
		return null;
	}
 
}
