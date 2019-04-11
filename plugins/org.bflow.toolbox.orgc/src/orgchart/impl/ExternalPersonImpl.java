/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;

import orgchart.ExternalPerson;
import orgchart.OrgchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ExternalPersonImpl extends LinkableElementImpl implements ExternalPerson {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalPersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.EXTERNAL_PERSON;
	}
	
	/*
	 * (non-Javadoc)
	 * @see orgchart.impl.LinkableElementImpl#getSubdiagramFeatureId()
	 */
	@Override
	protected int getSubdiagramFeatureId() {
		return OrgchartPackage.EXTERNAL_PERSON__SUBDIAGRAM;
	}

} //ExternalPersonImpl
