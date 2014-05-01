package oepc.diagram.extensions.actions;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;


/**
 * This resource set listener will react on changes in your model resource.
 * These changes will be delivered as an <code>ResourceSetChangeEvent</code>
 * which are filtered by an <code>NotificationFilter</code>.
 * The default filter is <code>NotificationFilter.NO_TOUCH</code>.
 * #the installed filter is OepcNotificationMatcher.buildFilter().
 * @see NotificationFilter
 * @see OepcNotificationMatcher
 * @author Joerg Hartmann
 *
 */
public class OepcResourceListener extends ResourceSetListenerImpl {

	
	/**
	 * To validate the model.
	 */
	private DiagramLiveValidator validator;
	
	
	/**
	 * Matches notifications which represents model changes.
	 * Use it to test new filter modifications.
	 */
	@SuppressWarnings("unused")
	private OepcNotificationMatcher matcher;
	
	
	/**
	 * Creating this listener by delivering the current 
	 * <code>DiagramLiveValidator</code>.
	 * @param validator
	 */
	public OepcResourceListener(DiagramLiveValidator validator){ 
		super(OepcNotificationMatcher.buildFilter());
		this.validator = validator;
		this.matcher = new OepcNotificationMatcher();
	}
	
	
	/**
	 * This method will be called if changes on the resource are done which
	 * passes the <code>NotificationFilter</code>.
	 * Calls the validator to validate your model.
	 * @param event
	 */
	public void resourceSetChanged(ResourceSetChangeEvent event){
		validator.runValidation();
	}
}
