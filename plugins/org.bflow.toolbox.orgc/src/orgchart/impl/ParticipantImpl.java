/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;

import orgchart.OrgchartPackage;
import orgchart.Participant;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated NOT
 */
public class ParticipantImpl extends LinkableElementImpl implements Participant {	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.PARTICIPANT;
	}
	
	/*
	 * (non-Javadoc)
	 * @see orgchart.impl.LinkableElementImpl#getSubdiagramFeatureId()
	 */
	@Override
	protected int getSubdiagramFeatureId() {
		return OrgchartPackage.PARTICIPANT__SUBDIAGRAM;
	}
	
} //ParticipantImpl
