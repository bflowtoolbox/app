package org.bflow.toolbox.hive.modelnavigator;

import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.graphics.Image;

/**
 * Extends {@link IIconProvider} to support the derivation of an image based on
 * the associated edit part itself and not only by its element type.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.04.2015
 * 
 */
public interface IExtendedIconProvider extends IIconProvider {
	/**
	 * Returns the icon of the given graphical edit part.
	 * 
	 * @param graphicalEditPart
	 *            Graphical edit part to process
	 * @return Image or NULL
	 */
	Image getIcon(IGraphicalEditPart graphicalEditPart);
}
