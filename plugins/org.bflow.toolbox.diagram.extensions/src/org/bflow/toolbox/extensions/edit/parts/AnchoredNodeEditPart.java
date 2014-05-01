package org.bflow.toolbox.extensions.edit.parts;


import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;


/**
 * An <code>AnchoredNodeEditPart</code> specifies the connection 
 * anchor for an source and target of an connection.
 * @author Joerg Hartmann
 *
 */
public abstract class AnchoredNodeEditPart extends ShapeNodeEditPart {

	
	/**
	 * Create me.
	 * @param view
	 */
	public AnchoredNodeEditPart(View view) {
		super(view);
	}

	
	/**
	 * Returns an <code>CenteredAnchor</code>.
	 * @param con
	 * @return
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart con) {
		CenteredAnchor fixedAnchor = new CenteredAnchor(getFigure());
		return fixedAnchor;
	}

	
	
	/**
	 * Returns an <code>CenteredAnchor</code>.
	 * @param request
	 * @return
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		CenteredAnchor fixedAnchor = new CenteredAnchor(getFigure());
		return fixedAnchor;
	}

	
	/**
	 * Returns an <code>CenteredAnchor</code>.
	 * @param con
	 * @return
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		CenteredAnchor fixedAnchor = new CenteredAnchor(getFigure());
		return fixedAnchor;
	}

	
	/**
	 * Returns an <code>CenteredAnchor</code>.
	 * @param request
	 * @return
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		CenteredAnchor fixedAnchor = new CenteredAnchor(getFigure());
		return fixedAnchor;
	}
}
