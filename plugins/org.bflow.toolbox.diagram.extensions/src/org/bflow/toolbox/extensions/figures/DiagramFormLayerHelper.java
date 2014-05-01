package org.bflow.toolbox.extensions.figures;

import java.util.List;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.IExpandableFigure;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;


/**
 * An <code>DiagramFormLayerHelper</code> is registered on an 
 * <code>DiagramFormLayer</code> to manage the calculation for the size
 * of the diagram figure.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
@SuppressWarnings( { "restriction" })
public class DiagramFormLayerHelper implements FreeformListener {

	private FreeformFigure host;
	private Rectangle freeformExtent;
	private Rectangle minimumSize;
	private FigureListener figureListener;
	
	private final static Insets insets = new Insets(20, 20, 20, 20);

	public DiagramFormLayerHelper(FreeformFigure host) {
		this.host = host;

		minimumSize = new Rectangle(0, 0, 500, 500);
		figureListener = new ChildTracker();
	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#reset()
	 */
	public void reset() {
		freeformExtent = null;
	}

	
	/**
	 * Calculates and sets the current diagram size and stores it into
	 * an <code>DiagramStyle</code>.
	 * @return
	 */
	public Rectangle getFreeformExtent() {
		Rectangle r;
		List<?> children = host.getChildren();
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				r = ((FreeformFigure) child).getFreeformExtent();
			else if (child instanceof IExpandableFigure)
				r = ((IExpandableFigure) child).getExtendedBounds();
			else
				r = child.getBounds();
			if (freeformExtent == null)
				freeformExtent = r.getCopy();
			else
				freeformExtent.union(r);
		}
		
		if (freeformExtent == null)
			freeformExtent = new Rectangle(0, 0, insets.getWidth(), insets
					.getHeight());
		else {
			freeformExtent.expand(insets);
			minimumSize.x = Math.min(minimumSize.x, freeformExtent.x);
			minimumSize.y = Math.min(minimumSize.y, freeformExtent.y);
			minimumSize.width = Math.max(
					freeformExtent.width, minimumSize.width);
			minimumSize.height = Math.max(
					freeformExtent.height, minimumSize.height);
			host.translateToParent(freeformExtent);
		}
		try {
			AbstractTransactionalCommand cmd = new AbstractTransactionalCommand(
					BflowDiagramEditPart.getCurrentViewer().getEditingDomain(),
					null, null) {

				@Override
				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {

					DiagramStyle pageStyle = (DiagramStyle) BflowDiagramEditPart
							.getCurrentViewer().getDiagramView().getStyle(
									NotationPackage.Literals.DIAGRAM_STYLE);

					if (pageStyle != null) {
						pageStyle.setPageX(minimumSize.x);
						pageStyle.setPageY(minimumSize.y);
						pageStyle.setPageWidth(minimumSize.width);
						pageStyle.setPageHeight(minimumSize.height);
					}

					return CommandResult.newOKCommandResult();
				}

			};

			try {
				cmd.execute(null, null);
			} catch (ExecutionException e) {
			}
		} catch (NullPointerException n) {

		}

		return minimumSize;

	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#hookChild(IFigure)
	 */
	public void hookChild(IFigure child) {
		invalidate();
		if (child instanceof FreeformFigure)
			((FreeformFigure) child).addFreeformListener(this);
		else
			child.addFigureListener(figureListener);
	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#invalidate()
	 */
	void invalidate() {
		freeformExtent = null;
		host.fireExtentChanged();
		if (host.getParent() != null)
			host.getParent().revalidate();
		else
			host.revalidate();
	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#notifyFreeformExtentChanged()
	 */
	public void notifyFreeformExtentChanged() {
		invalidate();
	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#setFreeformBounds(Rectangle)
	 */
	public void setFreeformBounds(Rectangle bounds) {
		host.setBounds(this.minimumSize);
		bounds = this.minimumSize.getCopy();
		host.translateFromParent(bounds);
		List<?> children = host.getChildren();
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				((FreeformFigure) child).setFreeformBounds(bounds);
		}
	}

	
	/*
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.
	 * BorderItemsAwareFreeFormLayer.BorderItemAwareFreeFormHelper#unhookChild(IFigure)
	 */
	public void unhookChild(IFigure child) {
		invalidate();
		if (child instanceof FreeformFigure)
			((FreeformFigure) child).removeFreeformListener(this);
		else
			child.removeFigureListener(figureListener);
	}

	
	/**
	 * Sets the current minimum size for the diagram.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setMinimumSize(int x, int y, int width, int height) {
		this.minimumSize = new Rectangle(x, y, width, height);
		invalidate();
	}
	
	
	/**
	 * Setting up minimum size by calculating the new width and
	 * height from the delivered parameters.
	 * @param width
	 * @param height
	 */
	public void setMinimumSize(int width,int height){
		this.minimumSize = new Rectangle(
				this.minimumSize.x, 
				this.minimumSize.y, 
				this.minimumSize.x + width, 
				this.minimumSize.y + height);
		invalidate();
	}
	
	
	/**
	 * Calculates the minimum bounds around all elements by adding an 
	 * insets and stores this size.
	 */
	public void pack(){
		Rectangle r;
		List<?> children = host.getChildren();
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				r = ((FreeformFigure) child).getFreeformExtent();
			else if (child instanceof IExpandableFigure)
				r = ((IExpandableFigure) child).getExtendedBounds();
			else
				r = child.getBounds();
			if (freeformExtent == null)
				freeformExtent = r.getCopy();
			else
				freeformExtent.union(r);
		}
		if (freeformExtent == null)
			freeformExtent = new Rectangle(0, 0, insets.getWidth(), insets
					.getHeight());
		else {
			freeformExtent.expand(insets);
			host.translateToParent(freeformExtent);
		}
		
		host.setBounds(freeformExtent);
		host.translateFromParent(freeformExtent);
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				((FreeformFigure) child).setFreeformBounds(freeformExtent);
		}
		
		minimumSize = freeformExtent;
		invalidate();
	}
	
	
	/**
	 * An simple listener which invalidates if an figure was moved.
	 *
	 */
	class ChildTracker implements FigureListener {
		public void figureMoved(IFigure source) {
			invalidate();
		}
	}
}
