package openome_model.figures;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

/**
 * An extension of BorderItemLocator that handles the positioning of border
 * items on the NORTH_EAST and SOUTH_EAST sides.
 * Modify the locateOnParent() method to add other directions.
 * 
 */
public class OpenOMEBorderItemLocator extends BorderItemLocator {

	public OpenOMEBorderItemLocator(IFigure parentFigure, int preferredSide) {
		super(parentFigure, preferredSide);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
	 */
	public void relocate(IFigure borderItem) {
        Dimension size = getSize(borderItem);

		Point ptNewLocation = locateOnBorder(getPreferredLocation(borderItem),
				getPreferredSideOfParent(), 0, borderItem);
		
        borderItem.setBounds(new Rectangle(ptNewLocation, size));
		setCurrentSideOfParent(findClosestSideOfParent(new Rectangle(ptNewLocation, size), getParentBorder()));
	}

	/**
	 * The preferred side takes precedence.
	 * 
	 * @param suggestedLocation
	 * @param suggestedSide
	 * @param circuitCount
	 *            recursion count to avoid an infinite loop
	 * @return point
	 */
	protected Point locateOnBorder(Point suggestedLocation, int suggestedSide, int circuitCount, IFigure borderItem) {
		Point recommendedLocation = locateOnParent(suggestedLocation,
			suggestedSide, borderItem);

		int vertical_gap = MapModeUtil.getMapMode(getParentFigure()).DPtoLP(8);
		int horizontal_gap = MapModeUtil.getMapMode(getParentFigure())
			.DPtoLP(8);
		Dimension borderItemSize = getSize(borderItem);

		if (circuitCount < 4 && conflicts(recommendedLocation, borderItem)) {
			if (suggestedSide == PositionConstants.WEST) {
				do {
					recommendedLocation.y += borderItemSize.height
						+ vertical_gap;
				} while (conflicts(recommendedLocation, borderItem));
				if (recommendedLocation.y > getParentBorder().getBottomLeft().y
					- borderItemSize.height) { // off the bottom,
					// wrap south
					return locateOnBorder(recommendedLocation,
						PositionConstants.SOUTH, circuitCount + 1, borderItem);
				}
			} else if (suggestedSide == PositionConstants.SOUTH) {
				do {
					recommendedLocation.x += borderItemSize.width
						+ horizontal_gap;
				} while (conflicts(recommendedLocation, borderItem));
				if (recommendedLocation.x > getParentBorder().getBottomRight().x
					- borderItemSize.width) {
					return locateOnBorder(recommendedLocation,
						PositionConstants.EAST, circuitCount + 1, borderItem);
				}
			} else if (suggestedSide == PositionConstants.EAST) {
				// move up the east side
				do {
					recommendedLocation.y -= (borderItemSize.height + vertical_gap);
				} while (conflicts(recommendedLocation, borderItem));
				if (recommendedLocation.y < getParentBorder().getTopRight().y) {
					// east is full, try north.conflicts(recommendedLocation, borderItem)
					return locateOnBorder(recommendedLocation,
						PositionConstants.NORTH, circuitCount + 1, borderItem);
				}
			} else { // NORTH
				do {
					recommendedLocation.x -= (borderItemSize.width + horizontal_gap);
				} while (conflicts(recommendedLocation, borderItem));
				if (recommendedLocation.x < getParentBorder().getTopLeft().x) {
					return locateOnBorder(recommendedLocation,
						PositionConstants.WEST, circuitCount + 1, borderItem);
				}
			}
		}
		return recommendedLocation;
	}
	
	/**
	 * Ensure the suggested location actually lies on the parent boundary. The
	 * side takes precedence.
	 * 
	 * @param suggestedLocation
	 * @param suggestedSide a PositionConstant indicating the suggested side.
	 * @return point the absolute coordinates of where the item will be located.
	 */
	protected Point locateOnParent(Point suggestedLocation, int suggestedSide,
			IFigure borderItem) {
		Rectangle bounds = getParentBorder();
		int parentFigureWidth = bounds.width;
		int parentFigureHeight = bounds.height;
		int parentFigureX = bounds.x;
		int parentFigureY = bounds.y;
		Dimension borderItemSize = getSize(borderItem);
		int newX = suggestedLocation.x;
		int newY = suggestedLocation.y;
		int westX = parentFigureX - borderItemSize.width
				+ getBorderItemOffset().width;
		int eastX = parentFigureX + parentFigureWidth
				- getBorderItemOffset().width;
		int southY = parentFigureY + parentFigureHeight
				- getBorderItemOffset().height;
		int northY = parentFigureY - borderItemSize.height
				+ getBorderItemOffset().height;
		if (suggestedSide == PositionConstants.WEST) {
			if (suggestedLocation.x != westX) {
				newX = westX;
			}
			if (suggestedLocation.y < bounds.getTopLeft().y) {
				newY = northY + borderItemSize.height;
			} else if (suggestedLocation.y > bounds.getBottomLeft().y
					- borderItemSize.height) {
				newY = southY - borderItemSize.height;
			}
		} else if (suggestedSide == PositionConstants.EAST) {
			if (suggestedLocation.x != eastX) {
				newX = eastX;
			}
			if (suggestedLocation.y < bounds.getTopLeft().y) {
				newY = northY + borderItemSize.height;
			} else if (suggestedLocation.y > bounds.getBottomLeft().y
					- borderItemSize.height) {
				newY = southY - borderItemSize.height;
			}
		} else if (suggestedSide == PositionConstants.NORTH_EAST) {
			if (suggestedLocation.x != eastX) {
				newX = eastX;
			}
			if (suggestedLocation.y != northY) {
				newY = northY;
			}
		} else if (suggestedSide == PositionConstants.SOUTH) {
			if (suggestedLocation.y != southY) {
				newY = southY;
			}
			if (suggestedLocation.x < bounds.getBottomLeft().x) {
				newX = westX + borderItemSize.width;
			} else if (suggestedLocation.x > bounds.getBottomRight().x
					- borderItemSize.width) {
				newX = eastX - borderItemSize.width;
			}
		} else if (suggestedSide == PositionConstants.SOUTH_EAST) {
			if (suggestedLocation.x != eastX) {
				newX = eastX;
			}
			if (suggestedLocation.y != southY) {
				newY = southY;
			}
		} else { // NORTH
			if (suggestedLocation.y != northY) {
				newY = northY;
			}
			if (suggestedLocation.x < bounds.getBottomLeft().x) {
				newX = westX + borderItemSize.width;
			} else if (suggestedLocation.x > bounds.getBottomRight().x
					- borderItemSize.width) {
				newX = eastX - borderItemSize.width;
			}
		}
		return new Point(newX, newY);
	}

	/**
	 * Determine if the the given point conflicts with the position of an
	 * existing borderItemFigure.
	 * 
	 * @param recommendedLocation
	 * @return <code>true</code> or <code>false</code>
	 */
	private boolean conflicts(Point recommendedLocation,
			IFigure targetBorderItem) {
		Rectangle recommendedRect = new Rectangle(recommendedLocation,
			getSize(targetBorderItem));
		List borderItems = targetBorderItem.getParent().getChildren();
        
        // Only check those border items that would have already been
        // relocated. See Bugzilla#214799.
        int currentIndex = borderItems.indexOf(targetBorderItem);
        for (int i = 0; i < currentIndex; i++) {
            IFigure borderItem = (IFigure) borderItems.get(i);
			if (borderItem.isVisible()) {
				Rectangle rect = borderItem.getBounds().getCopy();
				if (rect.intersects(recommendedRect)) {
					return true;
				}
			}
		}
		return false;
	}

}
