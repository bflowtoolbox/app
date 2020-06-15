/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;

import orgchart.Location;
import orgchart.OrgchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class LocationImpl extends LinkableElementImpl implements Location {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.LOCATION;
	}
	
	/*
	 * (non-Javadoc)
	 * @see orgchart.impl.LinkableElementImpl#getSubdiagramFeatureId()
	 */
	@Override
	protected int getSubdiagramFeatureId() {
		return OrgchartPackage.LOCATION__SUBDIAGRAM;
	}

} //LocationImpl
