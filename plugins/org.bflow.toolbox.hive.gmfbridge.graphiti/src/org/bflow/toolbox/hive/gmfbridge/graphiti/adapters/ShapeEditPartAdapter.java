package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;

/**
 * Provides an adapter for {@link ContainerShape}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.15
 * 
 */
@SuppressWarnings({"restriction", "unused"})
public class ShapeEditPartAdapter extends ShapeNodeEditPart {
	
	private EObject eObject;
	private ContainerShape fContainerShapeModel;
	
	private EditPart fOriginEditPart;
	private ContainerShapeEditPart fContainerShapeEditPart;

	/**
	 * Creates a new instance based on the given edit part.
	 * 
	 * @param originEditPart
	 *            Origin edit part
	 */
	public ShapeEditPartAdapter(EditPart originEditPart) {
		this((EObject) originEditPart.getModel());
		
		fOriginEditPart = originEditPart;
		fContainerShapeEditPart = (ContainerShapeEditPart) originEditPart;
		
		// TODO Check suspicious cast
		fContainerShapeModel = (ContainerShape) originEditPart.getModel();
		
		PictogramElement el = fContainerShapeEditPart.getPictogramElement();
		if (el.getLink() == null) return;
		if (el.getLink().getBusinessObjects().size() == 0) return;
		
		List<EObject> bos = el.getLink().getBusinessObjects();
		
		EObject realEObject = bos.get(0);
		eObject = realEObject;		
		
		getPrimaryView().setElement(eObject);
	}

	/**
	 * Protected constructor to serve base constructor.
	 * 
	 * @param model
	 *            Semantic model element
	 */
	ShapeEditPartAdapter(EObject model) {
		super(new ViewAdapter());
		eObject = model;
		fContainerShapeModel = (ContainerShape) model;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getChildren()
	 */
	@Override
	public List<?> getChildren() {
		return super.getChildren();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getFigure()
	 */
	@Override
	public IFigure getFigure() {
		IFigure figure = fContainerShapeEditPart.getFigure();
		return figure;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {
		return eObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#createNodeFigure()
	 */
	@Override
	protected NodeFigure createNodeFigure() {
		throw new RuntimeException("#AS Not implemented yet");
	}
	
	/**
	 * Custom implementation of {@link ViewImpl}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 04.04.2015
	 * 
	 */
	static class ViewAdapter extends ViewImpl {
	}
}
