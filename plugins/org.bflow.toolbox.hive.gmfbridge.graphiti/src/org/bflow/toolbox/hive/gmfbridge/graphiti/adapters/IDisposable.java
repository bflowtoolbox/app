package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

/**
 * Indicates that this instance needs to be informed when it's going to be
 * disposed.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 11.04.2015
 * 
 */
public interface IDisposable {
	
	/**
	 * Tells this instance that it is going to be disposed and it can release
	 * all its bound resources.
	 */
	void dispose();

}
