package org.bflow.toolbox.hive.attributes;

import java.util.List;

import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
import org.bflow.toolbox.hive.attributes.utils.EMFEditNameCommand;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Provides some useful methods to manipulate a diagram and its elements.
 * 
 * @author Arian Storch
 * @since 08/06/11
 * @version 28/07/12
 * 
 */
public class DiagramEditorUtils {
	
	private static final String[] GRAPHICAL_ATTRIBUTES = {
		"$name", "$fontname", "$fontsize", "$fontstyle", "$location",
		"$color", "$strokewidth"
	};

	/**
	 * Sets the properties of the font used by the edit part.
	 * <p/>
	 * If fontname is null, the previous font is used.<br/>
	 * If size is -1, the previous size is used.<br/>
	 * If style is -1, the previous style is used. Style parameter uses SWT
	 * constants like SWT.NORMAL, SWT.BOLD, SWT.ITALIC
	 * 
	 * @param editpart
	 *            edit part to edit
	 * @param fontName
	 *            system name of the font
	 * @param size
	 *            size of the font
	 * @param style
	 *            style; look at SWT styles
	 */
	public static void setElementFont(ShapeNodeEditPart editpart,
			String fontName, int size, int style) {
		EditPart child = editpart.getPrimaryChildEditPart();

		if (child instanceof ITextAwareEditPart) {

			ITextAwareEditPart g = (ITextAwareEditPart) child;

			Display display = PlatformUI.getWorkbench().getDisplay();

			if (size == -1)
				size = g.getFigure().getFont().getFontData()[0].getHeight();

			if (style == -1)
				style = g.getFigure().getFont().getFontData()[0].getStyle();

			if (fontName == null)
				fontName = g.getFigure().getFont().getFontData()[0].getName();

			Font f = new Font(display, fontName, size, style);

			g.getFigure().setFont(f);
		}
	}

	/**
	 * Sets the location of the element.
	 * 
	 * @param editpart
	 *            edit part to set
	 * @param location
	 *            new location
	 */
	public static void setElementLocation(ShapeNodeEditPart editpart,
			Point location) {
		SetBoundsCommand command = new SetBoundsCommand(editpart
				.getEditingDomain(), "Element relocate", new EObjectAdapter(
				(View) editpart.getModel()), location);

		editpart.getDiagramEditDomain().getDiagramCommandStack().execute(
				new ICommandProxy(command));
	}

	/**
	 * Moves the element along the x and y values.
	 * 
	 * @param editpart
	 *            edit part to move
	 * @param x
	 *            value to move the element along the abscissae
	 * @param y
	 *            value to move the element along the ordinate
	 */
	public static void moveElementLocation(ShapeNodeEditPart editpart, int x,
			int y) {
		IFigure fig = editpart.getFigure();
		BorderedNodeFigure bnf = (BorderedNodeFigure) fig;

		Point old = bnf.getLocation();

		Point location = new Point(old.x + x, old.y + y);

		setElementLocation(editpart, location);
	}
	
	/**
	 * Sets the foreground color of the given edit part defined by the given attribute.
	 * 
	 * @param editPart edit part which will get the new foreground color
	 * @param attribute attribute which contains the color info
	 */
	public static void setElementColor(IGraphicalEditPart editPart, IAttribute attribute) {
		/*
		 * [Arian Storch]
		 * The following lines are taken from http://wiki.eclipse.org/GMF_Newsgroup_Q_and_A#How_do_I_change_the_color_of_a_line_using_an_action.3F
		 * and will work on edit parts that own this property. In other words, if the user can change a graphical
		 * property during runtime by setting something up within the GUI this would work.
		 * But some elements don't have this property so a workaround needs to be done.
		 */
//		ChangePropertyValueRequest req = new ChangePropertyValueRequest(
//				StringStatics.BLANK, Properties.ID_FILLCOLOR, 
//				FigureUtilities.colorToInteger(DiagramColorConstants.red));
//		
//		final Command cmd = editPart.getCommand(req);
//		
//		AbstractEMFOperation operation = new AbstractEMFOperation(((IGraphicalEditPart) editPart).getEditingDomain(),
//				StringStatics.BLANK, null) {
//				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
//					cmd.execute();
//					return Status.OK_STATUS;
//				}
//			};
//			 
//			try {
//				operation.execute(new NullProgressMonitor(), null);
//			} catch (ExecutionException e) {
//				// nothing to do here
//			}
		if(editPart == null) {
			AttributeViewPlugin.logInfo("Trying to set color but edit part is null! Element-Id: "
					+ attribute.getElementID());
			return;
		}
		
		String strColor = attribute.getValue();
		
		if(strColor == null || strColor.isEmpty()) {
			AttributeViewPlugin.logInfo("Trying to set color but the given value was null or empty!");
			return;
		}
		
		String clrParts[] = strColor.split(",");
		
		if(clrParts.length != 3) {
			String values = "";
			for(int i = 0; i < clrParts.length; i++) {
				values += clrParts[i] + ",";
			}
			
			String message = String.format("Trying to set color but at least one given value was invalid! Values: %s ",
					values);
			AttributeViewPlugin.logInfo(message);
			return;
		}
		
		int r = -1, g = -1 , b = -1;
		
		try {
			r = Integer.parseInt(clrParts[0]);
			g = Integer.parseInt(clrParts[1]);
			b = Integer.parseInt(clrParts[2]);
		} catch(NumberFormatException ex) {
			String message = String.format("Trying to set color but at least one given value was invalid! R: %s, G: %s, B: %s ",
					r, g, b);
			AttributeViewPlugin.logInfo(message);
			return;
		}
		
		Color clr = new Color(Display.getCurrent(),r,g,b);
		editPart.getFigure().setForegroundColor(clr);
	}
	
	/**
	 * Sets the stroke width of the given edit part defined by the given attribute.
	 * 
	 * @param editPart edit part which will get the new stroke width
	 * @param attribute attribute which contains the stroke width value
	 */
	public static void setElementLineStroke(IGraphicalEditPart editPart, IAttribute attribute) {
		if(editPart == null) {
			AttributeViewPlugin.logInfo("Trying to set line stroke width but edit part is null! Element-Id: "
					+ attribute.getElementID());
			return;
		}
		
		String wdthValue = attribute.getValue();
		
		if(wdthValue == null || wdthValue.isEmpty()) {
			AttributeViewPlugin.logInfo("Trying to set line stroke width but the given value was null or empty!");
			return;
		}
		
		double strokeWidth = 1;
		
		try {
			strokeWidth = Double.parseDouble(wdthValue);
		} catch(NumberFormatException ex) {
			String message = String.format("Trying to set line stroke width but the given value was invalid! Width: %s ",
					wdthValue);
			AttributeViewPlugin.logInfo(message);
			return;
		}
		
		IFigure figure = editPart.getFigure();
		
		if(!(figure instanceof PolylineConnectionEx)) {
			AttributeViewPlugin.logInfo("Trying to set line stroke width but the target edit part is not a connection!");
			return;
		}
		
		PolylineConnectionEx polyline = (PolylineConnectionEx)figure;
		polyline.setLineWidth((int) strokeWidth);
	}

	/**
	 * Proofs if the given attribute affects graphical aspects of the diagram.
	 * 
	 * @param attribute attribute to check
	 * @return true or false
	 */
	public static boolean isGraphicalAttribute(IAttribute attribute) {
		String name = attribute.getName();
		
		boolean isGraphicalAttribute = false;
		
		for(int i = 0; i < GRAPHICAL_ATTRIBUTES.length; i++) {
			if(GRAPHICAL_ATTRIBUTES[i].equalsIgnoreCase(name)) {
				return true;
			}
		}
		
		return isGraphicalAttribute;
	}
	
	/**
	 * Will perform the necessary changes within the given diagram edit part defined by the given attribute. 
	 * 
	 * @param attribute attribute which defines the desired changes
	 * @param diagramEditPart diagram edit part which will receive the changes
	 */
	public static void handleGraphicalAttribute(IAttribute attribute, DiagramEditPart diagramEditPart) {
		String value = attribute.getValue();
		
		// Attribute $name
		if (attribute.getName().equalsIgnoreCase("$name")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snPart = (ShapeNodeEditPart) o;
				EObject eObj = snPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					EMFEditNameCommand com = new EMFEditNameCommand(eObj, value);

					EMFCommandOperation op = new EMFCommandOperation(
							diagramEditPart.getEditingDomain(), com);
					try {
						op.execute(null, null);
					} catch (ExecutionException e1) {
						AttributeViewPlugin.logError(e1.getMessage(), e1);
					}

					return;
				}
			}

			return;
		}
		
		// Attribute $fontname
		if (attribute.getName().equalsIgnoreCase("$fontname")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					DiagramEditorUtils.setElementFont(snEditPart, attribute
							.getValue(), -1, -1);

					return;
				}
			}

			return;
		}

		// Attribute $fontsize
		if (attribute.getName().equalsIgnoreCase("$fontsize")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					DiagramEditorUtils.setElementFont(snEditPart, null, Integer
							.parseInt(attribute.getValue()), -1);

					return;
				}
			}

			return;
		}

		// Attribute $fontstyle
		if (attribute.getName().equalsIgnoreCase("$fontstyle")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					int style = -1;

					if (attribute.getValue().equalsIgnoreCase("bold"))
						style = SWT.BOLD;

					if (attribute.getValue().equalsIgnoreCase("italic"))
						style = SWT.ITALIC;

					if (attribute.getValue().equalsIgnoreCase("normal"))
						style = SWT.NORMAL;

					DiagramEditorUtils.setElementFont(snEditPart, null, -1,
							style);

					return;
				}
			}

			return;
		}

		// Attribute $location
		if (attribute.getName().equalsIgnoreCase("$location")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					String strLocation = attribute.getValue();

					String strSplit[] = strLocation.split(",");

					if (strSplit.length != 2)
						return;

					if (strSplit[0].startsWith("-")
							|| strSplit[0].startsWith("+")
							|| strSplit[1].startsWith("-")
							|| strSplit[1].startsWith("+")) {

						strSplit[0] = strSplit[0].replace("+", "");
						strSplit[1] = strSplit[1].replace("+", "");

						DiagramEditorUtils.moveElementLocation(snEditPart,
								Integer.parseInt(strSplit[0]), Integer
										.parseInt(strSplit[1]));
					} else {

						Point location = new Point(Integer
								.parseInt(strSplit[0]), Integer
								.parseInt(strSplit[1]));

						DiagramEditorUtils.setElementLocation(snEditPart,
								location);
					}

					return;
				}
			}

			return;
		}
		
		// Attribute color
		if (attribute.getName().equalsIgnoreCase("$color")) {
			
			IGraphicalEditPart editPart = findGraphicalEditPart(diagramEditPart, attribute.getElementID());
			
			if(editPart != null) {
				DiagramEditorUtils.setElementColor(editPart, attribute);
			}
		}
		
		// Attribute stroke width
		if(attribute.getName().equalsIgnoreCase("$strokewidth")) {
			IGraphicalEditPart editPart = findGraphicalEditPart(diagramEditPart, attribute.getElementID());
			
			if(editPart != null) {
				DiagramEditorUtils.setElementLineStroke(editPart, attribute);
			}
		}
	}
	
	/**
	 * Checks the given diagram edit part if it contains an element with the given proxy id.
	 * 
	 * @param diagramEditPart diagram edit part which could contain an element with the given proxy id
	 * @param proxyId proxyId proxy id of the element that is looked for
	 * @return found instance of {@link IGraphicalEditPart} or null
	 */
	private static IGraphicalEditPart findGraphicalEditPart(DiagramEditPart diagramEditPart, String proxyId) {		
		// looking for ShapeNodeEditPart
		IGraphicalEditPart shapeEditPart = findGraphicalEditPartInList(diagramEditPart.getChildren(), proxyId);
		
		if(shapeEditPart != null) {
			return shapeEditPart;
		}

		// looking for ConnectionEditPart
		IGraphicalEditPart connectionEditPart = findGraphicalEditPartInList(diagramEditPart.getConnections(), proxyId);
		
		if(connectionEditPart != null) {
			return connectionEditPart;
		}
		
		return null;		
	}
	
	/**
	 * Checks the given collection of {@link IGraphicalEditPart} if an element exists which
	 * has the given proxy id.
	 * 
	 * @param list list which could contain an element with the given proxy id
	 * @param proxyId proxy id of the element that is looked for
	 * @return found instance of {@link IGraphicalEditPart} or null
	 */
	private static IGraphicalEditPart findGraphicalEditPartInList(List<?> list, String proxyId) {
		for(Object child:list) {
			IGraphicalEditPart editPart = (IGraphicalEditPart)child;
			EObject eObject = editPart.resolveSemanticElement();
			
			if(EMFCoreUtil.getProxyID(eObject).equalsIgnoreCase(proxyId)) {
				return editPart;
			}
		}
		
		return null;
	}
}
