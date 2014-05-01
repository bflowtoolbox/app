package org.bflow.toolbox.epc.extensions.actions;


import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.bflow.impl.BflowFactoryImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.impl.NotationFactoryImpl;

/**
 * Provides an filter for epc model changes.
 * Also implements the filtering manual to test new values.
 * @author Jörg
 *
 */
class EpcNotificationMatcher {

	
	/**
	 * Create me.
	 */
	EpcNotificationMatcher(){
		
	}
	
	
	/**
	 * Matches the delivered notification to the current filter for 
	 * epc model changes.
	 * Use this method instead of the filter to test other values.
	 * The test looks like:
	 * <code>
	 * for(Notification n : event.getNotifications()){
	 *		if(matcher.matchesModification(n)){
	 *			doValidate = true;
	 *			break;
	 *		}
 	 *	}
	 * </code>
	 * @param n
	 * @return
	 */
	public boolean matchesModification(Notification n){
		boolean matches = false;
		if(n.getFeature() == null){
			return matches;
		}
		
		BflowPackage bflow = BflowFactoryImpl.eINSTANCE.getBflowPackage();
		EAttribute symbolName = bflow.getBflowSymbol_Name();
		EReference from = bflow.getConnection_From();
		EReference to = bflow.getConnection_To();
		
		
		NotationPackage notation = NotationFactoryImpl.eINSTANCE.getNotationPackage();
		EReference nodes = notation.getView_PersistedChildren();
		
		if(n.getFeature().equals(symbolName) 
				|| n.getFeature().equals(from)
				|| n.getFeature().equals(to)
				|| n.getFeature().equals(nodes)){
			matches = true;
		}
		
		return matches;
	}
	
	
	/**
	 * The default filter for epc model changes.
	 * If an notification is thrown, it will validate by this filter.
	 * If the notification passes the filter, 
	 * ResourceSetListener#resourceSetChanged(ResourceSetEvent) will receive it. 
	 * @return
	 */
	public static NotificationFilter buildFilter(){
		NotificationFilter filter;
		
		BflowPackage bflow = BflowFactoryImpl.eINSTANCE.getBflowPackage();
		EAttribute symbolName = bflow.getBflowSymbol_Name();
		EReference from = bflow.getConnection_From();
		EReference to = bflow.getConnection_To();
		
		NotationPackage notation = NotationFactoryImpl.eINSTANCE.getNotationPackage();
		EReference nodes = notation.getView_PersistedChildren();
		
		NotificationFilter f1 = NotificationFilter.createFeatureFilter(symbolName);
		NotificationFilter f2 = NotificationFilter.createFeatureFilter(from);
		NotificationFilter f3 = NotificationFilter.createFeatureFilter(to);
		NotificationFilter f4 = NotificationFilter.createFeatureFilter(nodes);
		
		filter = f1.or(f2).or(f3).or(f4);
		return filter;
	}
}
