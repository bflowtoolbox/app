package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Provides an adapter for {@link Connection}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
@SuppressWarnings({"restriction"})
public class ConnectionEditPartAdapter extends ConnectionNodeEditPart {
	
	private EObject fUnderlyingModelObject;
	private SequenceFlow fUnderlyingSequenceFlow;
	
	@SuppressWarnings("unused")
	private EditPart fOriginEditPart;
	private EObject fOriginEditPartModel;
	private org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart fOriginConnectionEditPart;
	
	/**
	 * Creates a new instance based on the given edit part.
	 * 
	 * @param originEditPart
	 *            Origin edit part
	 */
	public ConnectionEditPartAdapter(EditPart originEditPart) {
		this(new ViewImpl() {});
		
		// Origin references
		fOriginEditPart = originEditPart;
		fOriginEditPartModel = (EObject) originEditPart.getModel();
		fOriginConnectionEditPart = (org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart) originEditPart;
		
		// Underlying references
		PictogramElement connectionModel = (PictogramElement) fOriginEditPartModel;
		fUnderlyingModelObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(connectionModel);
		fUnderlyingSequenceFlow = (SequenceFlow)fUnderlyingModelObject;
		
		getPrimaryView().setElement(fUnderlyingModelObject);
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
		IFigure figure = fOriginConnectionEditPart.getFigure();
		return figure;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getSource()
	 */
	@Override
	public EditPart getSource() {
		FlowNode sourceNode = fUnderlyingSequenceFlow.getSourceRef();
		ShapeEditPartAdapter shapeEditPart = AdapterFactory.getShapeEditPartAdapter(sourceNode);
		return shapeEditPart;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getTarget()
	 */
	@Override
	public EditPart getTarget() {		
		FlowNode targetNode = fUnderlyingSequenceFlow.getTargetRef();
		ShapeEditPartAdapter shapeEditPart = AdapterFactory.getShapeEditPartAdapter(targetNode);
		return shapeEditPart;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {
		return fUnderlyingModelObject;
	}
}