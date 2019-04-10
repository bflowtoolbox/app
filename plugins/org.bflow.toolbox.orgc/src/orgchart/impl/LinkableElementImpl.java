package orgchart.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgchart.LinkableElement;

/**
 * @generated NOT 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-08
 *
 */
public abstract class LinkableElementImpl extends NamedElementImpl implements LinkableElement {
	/**
	 * @generated NOT
	 */
	private String _subdiagram;
	
	/**
	 * @generated NOT
	 */
	@Override
	public String getSubdiagram() {
		return _subdiagram;
	}
	
	protected abstract int getSubdiagramFeatureId();
	
	/**
	 * @generated NOT
	 */
	@Override
	public void setSubdiagram(String value) {
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, getSubdiagramFeatureId(), _subdiagram, value));
		
		_subdiagram = value;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		int subdiagramFeatureId = getSubdiagramFeatureId();
		
		if (featureID == subdiagramFeatureId) {
			return getSubdiagram();
		}
		
		return super.eGet(featureID, resolve, coreType);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		int subdiagramFeatureId = getSubdiagramFeatureId();
		
		if (featureID == subdiagramFeatureId) {
			setSubdiagram((String) newValue);
			return;
		}
		
		super.eSet(featureID, newValue);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID) {
		int subdiagramFeatureId = getSubdiagramFeatureId();
		
		if (featureID == subdiagramFeatureId) {
			return getSubdiagram() != null;
		}
			
		return super.eIsSet(featureID);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		int subdiagramFeatureId = getSubdiagramFeatureId();
		
		if (featureID == subdiagramFeatureId) {
			setSubdiagram((String)null);
			return;
		}
		
		super.eUnset(featureID);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Subdiagram: ");
		result.append(_subdiagram);
		result.append(')');
		return result.toString();
	}
}
