package oepc.diagram.views;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

public class GraphicalEditPartUtil {

	/**
	 * Returns the GraphicalEditPart that is a child of {@code editor}
	 * and has the unique {@code elementId}. Returns NULL if it wasn't found.
	 * @param editor    The editor that is supposed to contain the element
	 * @param elementId The unique id of the element
	 */
	public static IGraphicalEditPart getViewPart(DiagramEditor editor, String elementId) {
		DiagramEditPart diagram = editor.getDiagramEditPart();
		List<?> children = diagram.getChildren();

		for(Object obj : children) {
			if (!(obj instanceof IGraphicalEditPart)) continue;
			IGraphicalEditPart child = (IGraphicalEditPart) obj;
			
			if(elementId.equals(getElementId(child))) return child;
		}
		
		return null;
	}
		
	/**
	 * Returns the unique id of the model element that is wrapped by 
	 * the given {@code editPart}. If {@code editPart} is NULL, 
	 * NULL is returned.
	 */
	public static String getElementId(IGraphicalEditPart editPart) {
		if (editPart == null) return null;
		EObject eobj = editPart.resolveSemanticElement();
		return EMFCoreUtil.getProxyID(eobj);
	}
	
	/**
	 * Returns the name of the model element that is wrapped by 
	 * the given {@code editPart}. If {@code editPart} is NULL, 
	 * an empty String is returned.
	 */
	public static String getElementName(IGraphicalEditPart editPart) {
		if (editPart == null) return "";
		EObject eobj = editPart.resolveSemanticElement();
		return EMFCoreUtil.getName(eobj);
	}
}
