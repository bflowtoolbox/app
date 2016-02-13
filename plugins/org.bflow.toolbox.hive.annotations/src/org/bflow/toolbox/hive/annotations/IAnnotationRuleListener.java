package org.bflow.toolbox.hive.annotations;

/**
 * Implement this interface if you want a class to react on changes in the
 * AnnotationRule.xml
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 17.06.2015
 *
 */
public interface IAnnotationRuleListener {
	/**
	 * Notice the listener that the Attribute File has changed.
	 * 
	 * @param event
	 *            event
	 */
	public void noticeAnnotationFileChange(AnnotationRuleEvent event);
}
