package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Interface of model elements that own a property which identifies them which a
 * human readable name.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-21
 */
public interface INameable {
	/**
	 * Returns the name of the model element.
	 * 
	 * @return Name of the model element.
	 */
	String getName();
}
