package org.bflow.toolbox.extensions;

/**
 * Provides information about a link that is related with a modeling element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public interface ILinkContext {
	/** Returns TRUE if there is a link to show. */
	boolean showLink();
	
	/** Returns the tooltip text for the link. */
	String getToolTipText();
	
	/** Is invoked when the link is double clicked. */
	void onLinkDoubleClick();
}
