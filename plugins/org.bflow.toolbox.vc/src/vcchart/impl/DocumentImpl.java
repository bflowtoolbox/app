/**
 */
package vcchart.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import vcchart.Document;
import vcchart.VcchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DocumentImpl extends NamedElementImpl implements Document {
	
	/**
	 * @generated NOT
	 */
	private String _subdiagram;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VcchartPackage.Literals.DOCUMENT;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String getSubdiagram() {
		return _subdiagram;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void setSubdiagram(String value) {
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VcchartPackage.DOCUMENT__SUBDIAGRAM, _subdiagram, value));
		
		_subdiagram = value;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case VcchartPackage.DOCUMENT__SUBDIAGRAM:
			{
				return getSubdiagram();
			}
		}
		
		return super.eGet(featureID, resolve, coreType);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case VcchartPackage.DOCUMENT__SUBDIAGRAM:
			{
				setSubdiagram((String) newValue);
				return;
			}
		}
		
		super.eSet(featureID, newValue);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case VcchartPackage.DOCUMENT__SUBDIAGRAM:
			{
				return getSubdiagram() != null;
			}
		}
		
		return super.eIsSet(featureID);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) 
		{
			case VcchartPackage.DOCUMENT__SUBDIAGRAM:
			{
				setSubdiagram((String)null);
				return;
			}
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

} //DocumentImpl
