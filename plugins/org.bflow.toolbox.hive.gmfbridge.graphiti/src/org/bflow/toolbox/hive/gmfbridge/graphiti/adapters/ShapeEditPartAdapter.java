package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.gmfbridge.HiveGmfBridge;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

/**
 * Provides an adapter for {@link ContainerShape}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.15
 * 
 */
public class ShapeEditPartAdapter extends ShapeNodeEditPart {
	
	private EObject fUnderlyingModelObject;
	private FlowNode fUnderlyingFlowNode;
	
	@SuppressWarnings("unused")
	private EditPart fOriginEditPart; // ContainerShapeEditPart
	private EObject fOriginEditPartModel; // ContainerShape

	/**
	 * Creates a new instance based on the given edit part.
	 * 
	 * @param originEditPart
	 *            Origin edit part
	 */
	public ShapeEditPartAdapter(EditPart originEditPart) {
		this((EObject) originEditPart.getModel());
		
		// Origin references
		fOriginEditPart = originEditPart;
		fOriginEditPartModel = (EObject) originEditPart.getModel();	
		
		// Underlying references
		PictogramElement shapeModel = (PictogramElement) fOriginEditPartModel;
		fUnderlyingModelObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(shapeModel);		
		fUnderlyingFlowNode = (FlowNode) fUnderlyingModelObject;
		
		getPrimaryView().setElement(fUnderlyingModelObject);
	}

	/**
	 * Protected constructor to serve base constructor.
	 * 
	 * @param model
	 *            Semantic model element
	 */
	ShapeEditPartAdapter(EObject model) {
		super(new ViewImpl() {});
		fUnderlyingModelObject = model;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getChildren()
	 */
	@Override
	public List<?> getChildren() {
		return super.getChildren();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getSourceConnections()
	 */
	@Override
	public List<?> getSourceConnections() {
		DiagramEditor diagramEditor = ModelUtil.getEditor(fOriginEditPartModel);
		DiagramEditPart diagramEditPart = HiveGmfBridge.adapt(diagramEditor).getDiagramEditPart();
		@SuppressWarnings("unchecked")
		List<IGraphicalEditPart> diagramConnections = diagramEditPart.getConnections();
		
		List<SequenceFlow> incomings = fUnderlyingFlowNode.getOutgoing();
		List<EditPart> sourceConnections = new ArrayList<>();
		for (SequenceFlow seqFlow : incomings) {
			for (IGraphicalEditPart edge : diagramConnections) {
				EObject edgeModelObject = edge.resolveSemanticElement();
				if (edgeModelObject != seqFlow) continue;
				sourceConnections.add(edge);
				break;
			}
		}
		
		return sourceConnections;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getTargetConnections()
	 */
	@Override
	public List<?> getTargetConnections() {
		DiagramEditor diagramEditor = ModelUtil.getEditor(fOriginEditPartModel);
		DiagramEditPart diagramEditPart = HiveGmfBridge.adapt(diagramEditor).getDiagramEditPart();
		@SuppressWarnings("unchecked")
		List<IGraphicalEditPart> diagramConnections = diagramEditPart.getConnections();
		
		List<SequenceFlow> outgoings = fUnderlyingFlowNode.getIncoming();
		List<EditPart> targetConnections = new ArrayList<>();
		for (SequenceFlow seqFlow : outgoings) {
			for (IGraphicalEditPart edge : diagramConnections) {
				EObject eObj = edge.resolveSemanticElement();
				if (eObj != seqFlow) continue;
				targetConnections.add(edge);
				break;
			}
		}
		return targetConnections;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getFigure()
	 */
	@Override
	public IFigure getFigure() { 
		IFigure figure = ((IGraphicalEditPart)fOriginEditPartModel).getFigure();
		return figure;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {
		return fUnderlyingModelObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#createNodeFigure()
	 */
	@Override
	protected NodeFigure createNodeFigure() {
		throw new RuntimeException("#AS Not implemented yet");
	}
}
