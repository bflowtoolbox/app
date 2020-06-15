/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;

import orgchart.OrgchartPackage;
import orgchart.PersonType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class PersonTypeImpl extends LinkableElementImpl implements PersonType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.PERSON_TYPE;
	}
	
	/*
	 * (non-Javadoc)
	 * @see orgchart.impl.LinkableElementImpl#getSubdiagramFeatureId()
	 */
	@Override
	protected int getSubdiagramFeatureId() {
		return OrgchartPackage.PERSON_TYPE__SUBDIAGRAM;
	}

} //PersonTypeImpl
