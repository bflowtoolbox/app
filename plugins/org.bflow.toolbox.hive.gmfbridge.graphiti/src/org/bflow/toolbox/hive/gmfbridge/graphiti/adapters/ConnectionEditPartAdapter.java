package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Provides an adapter for {@link Connection}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
@SuppressWarnings({"restriction", "unused"})
public class ConnectionEditPartAdapter extends ConnectionNodeEditPart {
	
	private EObject eObject;
	private Connection fConnectionModel;
	
	private EditPart fOriginEditPart;
	private org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart fConnectionEditPart;
	
	/**
	 * Creates a new instance based on the given edit part.
	 * 
	 * @param originEditPart
	 *            Origin edit part
	 */
	public ConnectionEditPartAdapter(EditPart originEditPart) {
		this(new ViewImpl() {});
		
		eObject = (EObject) originEditPart.getModel();
		fConnectionModel = (Connection)eObject;
		
		fOriginEditPart = originEditPart;
		fConnectionEditPart = (org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart) originEditPart;
		
		EObject underlyingModelObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(fConnectionModel);
		eObject = underlyingModelObject;
		
		getPrimaryView().setElement(eObject);
	}
	
	/**
	 * Protected constructor to serve base constructor.
	 * @param view 
	 */
	ConnectionEditPartAdapter(View view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#createConnectionFigure()
	 */
	@Override
	protected org.eclipse.draw2d.Connection createConnectionFigure() {
		throw new RuntimeException("#AS Not implemented yet");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getFigure()
	 */
	@Override
	public IFigure getFigure() {
		IFigure figure = fConnectionEditPart.getFigure();
		return figure;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getSource()
	 */
	@Override
	public EditPart getSource() {
		EditPart sourceEditPart = fConnectionEditPart.getSource();
		return new ShapeEditPartAdapter(sourceEditPart);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getTarget()
	 */
	@Override
	public EditPart getTarget() {
		EditPart targetEditPart = fConnectionEditPart.getTarget();
		return new ShapeEditPartAdapter(targetEditPart);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {
		return eObject;
	}
}