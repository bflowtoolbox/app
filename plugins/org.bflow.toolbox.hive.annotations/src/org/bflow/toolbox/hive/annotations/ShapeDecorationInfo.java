package org.bflow.toolbox.hive.annotations;

import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;

/**
 * Helper class to encapsulate information about the resource name of a
 * decoration and the position where it should be placed over a model element.
 * 
 * @author Felix Hoess
 * @since 05.05.2015
 *
 */
public class ShapeDecorationInfo {

	private Direction position = null;
	private String iconResourceName = null;
			
	public ShapeDecorationInfo(Direction position, String iconResourceName) {
		this.position = position;
		this.setIconResourceName(iconResourceName);

	}

	public Direction getPosition() {
		return position;
	}

	public String getIconResourceName() {
		return iconResourceName;
	}

	private void setIconResourceName(String iconResourceName) {
		this.iconResourceName = iconResourceName;
	}
			
}
