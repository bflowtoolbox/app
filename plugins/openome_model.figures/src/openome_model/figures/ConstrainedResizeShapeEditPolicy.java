package openome_model.figures;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

/**
 * A subclass of {@link ResizableShapeEditPolicy} that allows only constrained
 * resizing (with regard to the aspect ratio). This class was originally written
 * for another GMF RCP called PART-E: http://sourceforge.net/projects/parte/
 * 
 * @author Elmar Weber
 * 
 */
public class ConstrainedResizeShapeEditPolicy extends ResizableShapeEditPolicy {
	private final GraphicalEditPart editPart;
	
	/**
	 * Whether or not this edit policy is used for an actor.. if true,
	 * then we want to return an empty list of resize points (such that
	 * the user cannot resize the element when the actor is collapsed)
	 * 
	 * By default, it's false unless we explicitly say so by calling the setter
	 */
	private boolean usedForActor = false;

	/**
	 * Constructs a new {@link ConstrainedResizeShapeEditPolicy} for the
	 * specified {@link GraphicalEditPart}.
	 * 
	 * @param editPart
	 *            the {@link GraphicalEditPart} this
	 *            {@link ConstrainedResizeShapeEditPolicy} is for.
	 */
	public ConstrainedResizeShapeEditPolicy(GraphicalEditPart editPart) {
		this.editPart = editPart;
	}
	
	/**
	 * Sets the new value for whether or not this edit policy is used for an actor
	 * @param newValue
	 */
	public void setIsUsedForActor(boolean newValue) {
		usedForActor = newValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List createSelectionHandles() {
		
		List list = super.createSelectionHandles();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object o = (Object) iterator.next();

			if (o instanceof ResizeHandle) {
				iterator.remove();
			}
		}

		// if the figure is an actor/agent/position/role and is collapsed
		if ((editPart.getFigure().getSize().height) <= ContainerSVGFigure.ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD 
		 && (editPart.getFigure().getSize().width <= ContainerSVGFigure.ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD)
		 && usedForActor) {
			// return the empty list with no resizable points
			return list;
		}
		
			
		list.add(createHandle(PositionConstants.NORTH_WEST));
		list.add(createHandle(PositionConstants.NORTH_EAST));
		list.add(createHandle(PositionConstants.SOUTH_WEST));
		list.add(createHandle(PositionConstants.SOUTH_EAST));

		// only add NESW resize points for actors, but not intentions
		if (usedForActor) {
			// if we want to adjust the shape of the figure, we will allow
			// them to resize north, east, south, or west
			list.add(createHandle(PositionConstants.NORTH));
			list.add(createHandle(PositionConstants.EAST));
			list.add(createHandle(PositionConstants.SOUTH));
			list.add(createHandle(PositionConstants.WEST));	
		}
		

		return list;
	}


	private ResizeHandle createHandle(final int direction) {
		return new ResizeHandle(this.editPart, direction) {
			@Override
			protected DragTracker createDragTracker() {
				return new ResizeTracker(getOwner(), direction) {
					@Override
					public void mouseDrag(MouseEvent me, EditPartViewer viewer) {
						// apply the shift mask (to ensure that proportions are retained when resizing)
						// only when we are resizing intentions, and not actors.. actors should
						// be allowed to resize while not retaining their intention, but
						// intentions would look distorted if they didn't retain their proportions
						// so they must retain their proportions
						if (!usedForActor) {
							me.stateMask |= SWT.SHIFT;
						}
						super.mouseDrag(me, viewer);
					}
				};
			}
		};
	}
}