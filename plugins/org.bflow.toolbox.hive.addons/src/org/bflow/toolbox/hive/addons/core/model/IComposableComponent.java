package org.bflow.toolbox.hive.addons.core.model;

/**
 * Extends {@link IComponent} to indicate that the implementing instance may
 * have special needs when it will be composed with other elements. Implement
 * this interface to request more control about how this component is linked
 * with other components.
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 25.11.2016
 * 
 */
public interface IComposableComponent extends IComponent {

	/**
	 * Returns TRUE if this component may be succeeded by the given one.
	 * 
	 * @param component
	 *            Component to check
	 * @return TRUE or FALSE
	 */
	boolean supportsSucceeder(IComponent component);

}
