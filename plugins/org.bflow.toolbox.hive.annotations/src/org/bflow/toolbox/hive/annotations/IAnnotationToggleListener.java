package org.bflow.toolbox.hive.annotations;


/**
 * Implement this if you want a class to react on changes of the Annotation
 * Toggle Button.
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 17.06.2015
 *
 */
public interface IAnnotationToggleListener {
	
	/**
	 * Notice the listener that the annotation toggle button has changed.
	 * 
	 * @param event
	 *            event
	 */
	public void noticeToggleChange(boolean showAnnotation);

}
