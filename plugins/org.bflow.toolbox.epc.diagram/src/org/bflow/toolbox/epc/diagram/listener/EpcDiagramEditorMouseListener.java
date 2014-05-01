package org.bflow.toolbox.epc.diagram.listener;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;

/**
 * Implements {@link MouseListener} and {@link MouseMoveListener} for the EPC diagram editor. 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26/11/09
 * @version 31/07/13
 *
 */
public class EpcDiagramEditorMouseListener implements MouseListener, MouseMoveListener 
{
	private Point iMouseLocation = new Point(0,0);

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
	 */
	@Override
	public void mouseDoubleClick(MouseEvent e) 	{
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		setMouseLocation(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
	 */
	@Override
	public void mouseUp(MouseEvent e) {
	}
	
	/**
	 * Returns the mouse location.
	 *
	 * @return the mouse location
	 */
	public Point getMouseLocation() {
		return iMouseLocation;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseMoveListener#mouseMove(org.eclipse.swt.events.MouseEvent)
	 */
	@Override
	public void mouseMove(MouseEvent e) {
		setMouseLocation(e);
	}
	
	/**
	 * Sets the mouse location.
	 *
	 * @param e the new mouse location
	 */
	private void setMouseLocation(MouseEvent e) {
		iMouseLocation.x = e.x;
		iMouseLocation.y = e.y;
	}

}
