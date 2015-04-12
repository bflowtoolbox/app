package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * Provides an adapter for
 * {@link org.eclipse.graphiti.ui.internal.parts.DiagramEditPart}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
@SuppressWarnings("restriction")
class DiagramEditPartAdapter extends DiagramEditPart {

	private org.eclipse.graphiti.ui.internal.parts.DiagramEditPart fGraphitiDiagramEditPart;
	private GraphicalViewer fDiagramGraphicalViewer;
	
	/**
	 * Creates a new instance based on the given instances.
	 * 
	 * @param graphitiDiagramEditPart
	 * @param diagramGraphicalViewer
	 */
	public DiagramEditPartAdapter(org.eclipse.graphiti.ui.internal.parts.DiagramEditPart graphitiDiagramEditPart, GraphicalViewer diagramGraphicalViewer) {
		super(null);
		fGraphitiDiagramEditPart = graphitiDiagramEditPart;
		fDiagramGraphicalViewer = diagramGraphicalViewer;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#getConnections()
	 */
	@Override
	public List<?> getConnections() {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		List<Connection> connectionModels = diagram.getConnections();
		List<EditPart> connectionEditParts = new ArrayList<>(connectionModels.size());
		for (Connection connectionModel : connectionModels) {
			EditPart connectionEditPart = (EditPart) fDiagramGraphicalViewer.getEditPartRegistry().get(connectionModel);
			connectionEditParts.add(new ConnectionEditPartAdapter(connectionEditPart));
		}
		return connectionEditParts;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getChildren()
	 */
	@Override
	public List<?> getChildren() {
//		List<PictogramElement> modelChildren = fGraphitiDiagramEditPart.getModelChildren();
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		List<Shape> shapeModels = diagram.getChildren();
		List<EditPart> shapeEditParts = new ArrayList<>(shapeModels.size());
		for (Shape shapeModel : shapeModels) {
			EditPart shapeEditPart = (EditPart) fDiagramGraphicalViewer.getEditPartRegistry().get(shapeModel);
			shapeEditParts.add(new ShapeEditPartAdapter(shapeEditPart));
		}
		return shapeEditParts;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {
		Object model = fGraphitiDiagramEditPart.getModel();
		EObject eModel = (EObject)model;
		return eModel;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getModel()
	 */
	@Override
	public Object getModel() {
		return resolveSemanticElement();
	}
}