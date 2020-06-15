/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;

import orgchart.InternalPerson;
import orgchart.OrgchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Internal Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class InternalPersonImpl extends LinkableElementImpl implements InternalPerson {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InternalPersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.INTERNAL_PERSON;
	}
	
	/*
	 * (non-Javadoc)
	 * @see orgchart.impl.LinkableElementImpl#getSubdiagramFeatureId()
	 */
	@Override
	protected int getSubdiagramFeatureId() {
		return OrgchartPackage.INTERNAL_PERSON__SUBDIAGRAM;
	}

} //InternalPersonImpl
