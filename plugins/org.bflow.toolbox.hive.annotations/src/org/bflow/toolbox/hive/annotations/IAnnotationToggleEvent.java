package org.bflow.toolbox.hive.annotations;

import java.util.EventListener;

/**
 * Implement this event listener to create an event that provide information
 * about the visibility of annotations
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 17.06.2015
 *
 */
public interface IAnnotationToggleEvent extends EventListener {
	
	public boolean getIsVisible() ;
}
