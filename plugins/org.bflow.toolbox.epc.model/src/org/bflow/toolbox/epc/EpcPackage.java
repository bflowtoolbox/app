/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc;

import org.bflow.toolbox.bflow.BflowPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bflow.toolbox.epc.EpcFactory
 * @model kind="package"
 * @generated
 */
public interface EpcPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "epc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.bflow.toolbox.epc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "epc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EpcPackage eINSTANCE = org.bflow.toolbox.epc.impl.EpcPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.EventImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.FunctionImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The feature id for the '<em><b>Subdiagram</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SUBDIAGRAM = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ProcessInterfaceImpl <em>Process Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ProcessInterfaceImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getProcessInterface()
	 * @generated
	 */
	int PROCESS_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The feature id for the '<em><b>Subdiagram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE__SUBDIAGRAM = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_INTERFACE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ApplicationImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ParticipantImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ANDImpl <em>AND</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ANDImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getAND()
	 * @generated
	 */
	int AND = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>AND</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ORImpl <em>OR</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ORImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getOR()
	 * @generated
	 */
	int OR = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>OR</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.XORImpl <em>XOR</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.XORImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getXOR()
	 * @generated
	 */
	int XOR = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>XOR</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ArcImpl <em>Arc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ArcImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getArc()
	 * @generated
	 */
	int ARC = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Arc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARC_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.RelationImpl <em>Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.RelationImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getRelation()
	 * @generated
	 */
	int RELATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.GroupImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.LocationImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.PositionImpl <em>Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.PositionImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getPosition()
	 * @generated
	 */
	int POSITION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.FileImpl <em>File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.FileImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getFile()
	 * @generated
	 */
	int FILE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.CardFileImpl <em>Card File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.CardFileImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getCardFile()
	 * @generated
	 */
	int CARD_FILE = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Card File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_FILE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ClusterImpl <em>Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ClusterImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getCluster()
	 * @generated
	 */
	int CLUSTER = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.InternalPersonImpl <em>Internal Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.InternalPersonImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getInternalPerson()
	 * @generated
	 */
	int INTERNAL_PERSON = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Internal Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ExternalPersonImpl <em>External Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ExternalPersonImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getExternalPerson()
	 * @generated
	 */
	int EXTERNAL_PERSON = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>External Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.PersonTypeImpl <em>Person Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.PersonTypeImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getPersonType()
	 * @generated
	 */
	int PERSON_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Person Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.TechnicalTermImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getTechnicalTerm()
	 * @generated
	 */
	int TECHNICAL_TERM = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Technical Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.DocumentImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ObjectiveImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getObjective()
	 * @generated
	 */
	int OBJECTIVE = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.ProductImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.InformationArcImpl <em>Information Arc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.InformationArcImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getInformationArc()
	 * @generated
	 */
	int INFORMATION_ARC = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Information Arc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_ARC_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.bflow.toolbox.epc.impl.EpcImpl <em>Epc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.epc.impl.EpcImpl
	 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getEpc()
	 * @generated
	 */
	int EPC = 24;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPC__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPC__CONNECTIONS = 1;

	/**
	 * The number of structural features of the '<em>Epc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPC_FEATURE_COUNT = 2;
	
	
	/**
	 * The number of structural features for'<em>Epc Nodes</em>' class.
	 */
	int EpcNode = 25;


	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see org.bflow.toolbox.epc.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.bflow.toolbox.epc.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the attribute list '{@link org.bflow.toolbox.epc.Function#getSubdiagram <em>Subdiagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Subdiagram</em>'.
	 * @see org.bflow.toolbox.epc.Function#getSubdiagram()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Subdiagram();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.ProcessInterface <em>Process Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Interface</em>'.
	 * @see org.bflow.toolbox.epc.ProcessInterface
	 * @generated
	 */
	EClass getProcessInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.epc.ProcessInterface#getSubdiagram <em>Subdiagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subdiagram</em>'.
	 * @see org.bflow.toolbox.epc.ProcessInterface#getSubdiagram()
	 * @see #getProcessInterface()
	 * @generated
	 */
	EAttribute getProcessInterface_Subdiagram();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see org.bflow.toolbox.epc.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see org.bflow.toolbox.epc.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.AND <em>AND</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AND</em>'.
	 * @see org.bflow.toolbox.epc.AND
	 * @generated
	 */
	EClass getAND();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.OR <em>OR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OR</em>'.
	 * @see org.bflow.toolbox.epc.OR
	 * @generated
	 */
	EClass getOR();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.XOR <em>XOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XOR</em>'.
	 * @see org.bflow.toolbox.epc.XOR
	 * @generated
	 */
	EClass getXOR();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Arc <em>Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arc</em>'.
	 * @see org.bflow.toolbox.epc.Arc
	 * @generated
	 */
	EClass getArc();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation</em>'.
	 * @see org.bflow.toolbox.epc.Relation
	 * @generated
	 */
	EClass getRelation();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see org.bflow.toolbox.epc.Group
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see org.bflow.toolbox.epc.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Position <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position</em>'.
	 * @see org.bflow.toolbox.epc.Position
	 * @generated
	 */
	EClass getPosition();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.File <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File</em>'.
	 * @see org.bflow.toolbox.epc.File
	 * @generated
	 */
	EClass getFile();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.CardFile <em>Card File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Card File</em>'.
	 * @see org.bflow.toolbox.epc.CardFile
	 * @generated
	 */
	EClass getCardFile();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Cluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster</em>'.
	 * @see org.bflow.toolbox.epc.Cluster
	 * @generated
	 */
	EClass getCluster();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.InternalPerson <em>Internal Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Person</em>'.
	 * @see org.bflow.toolbox.epc.InternalPerson
	 * @generated
	 */
	EClass getInternalPerson();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.ExternalPerson <em>External Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Person</em>'.
	 * @see org.bflow.toolbox.epc.ExternalPerson
	 * @generated
	 */
	EClass getExternalPerson();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.PersonType <em>Person Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person Type</em>'.
	 * @see org.bflow.toolbox.epc.PersonType
	 * @generated
	 */
	EClass getPersonType();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.TechnicalTerm <em>Technical Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Term</em>'.
	 * @see org.bflow.toolbox.epc.TechnicalTerm
	 * @generated
	 */
	EClass getTechnicalTerm();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see org.bflow.toolbox.epc.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Objective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see org.bflow.toolbox.epc.Objective
	 * @generated
	 */
	EClass getObjective();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.bflow.toolbox.epc.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.InformationArc <em>Information Arc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Information Arc</em>'.
	 * @see org.bflow.toolbox.epc.InformationArc
	 * @generated
	 */
	EClass getInformationArc();
	
	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.EpcNode <em>Epc Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see org.bflow.toolbox.epc.Objective
	 * @generated
	 */
	EClass getEpcNode();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.epc.Epc <em>Epc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Epc</em>'.
	 * @see org.bflow.toolbox.epc.Epc
	 * @generated
	 */
	EClass getEpc();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.epc.Epc#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.bflow.toolbox.epc.Epc#getElements()
	 * @see #getEpc()
	 * @generated
	 */
	EReference getEpc_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.epc.Epc#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.bflow.toolbox.epc.Epc#getConnections()
	 * @see #getEpc()
	 * @generated
	 */
	EReference getEpc_Connections();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EpcFactory getEpcFactory();
	
	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.EventImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.FunctionImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Subdiagram</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__SUBDIAGRAM = eINSTANCE.getFunction_Subdiagram();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ProcessInterfaceImpl <em>Process Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ProcessInterfaceImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getProcessInterface()
		 * @generated
		 */
		EClass PROCESS_INTERFACE = eINSTANCE.getProcessInterface();

		/**
		 * The meta object literal for the '<em><b>Subdiagram</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_INTERFACE__SUBDIAGRAM = eINSTANCE.getProcessInterface_Subdiagram();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ApplicationImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ParticipantImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ANDImpl <em>AND</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ANDImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getAND()
		 * @generated
		 */
		EClass AND = eINSTANCE.getAND();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ORImpl <em>OR</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ORImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getOR()
		 * @generated
		 */
		EClass OR = eINSTANCE.getOR();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.XORImpl <em>XOR</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.XORImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getXOR()
		 * @generated
		 */
		EClass XOR = eINSTANCE.getXOR();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ArcImpl <em>Arc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ArcImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getArc()
		 * @generated
		 */
		EClass ARC = eINSTANCE.getArc();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.RelationImpl <em>Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.RelationImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getRelation()
		 * @generated
		 */
		EClass RELATION = eINSTANCE.getRelation();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.GroupImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.LocationImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.PositionImpl <em>Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.PositionImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getPosition()
		 * @generated
		 */
		EClass POSITION = eINSTANCE.getPosition();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.FileImpl <em>File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.FileImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getFile()
		 * @generated
		 */
		EClass FILE = eINSTANCE.getFile();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.CardFileImpl <em>Card File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.CardFileImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getCardFile()
		 * @generated
		 */
		EClass CARD_FILE = eINSTANCE.getCardFile();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ClusterImpl <em>Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ClusterImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getCluster()
		 * @generated
		 */
		EClass CLUSTER = eINSTANCE.getCluster();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.InternalPersonImpl <em>Internal Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.InternalPersonImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getInternalPerson()
		 * @generated
		 */
		EClass INTERNAL_PERSON = eINSTANCE.getInternalPerson();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ExternalPersonImpl <em>External Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ExternalPersonImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getExternalPerson()
		 * @generated
		 */
		EClass EXTERNAL_PERSON = eINSTANCE.getExternalPerson();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.PersonTypeImpl <em>Person Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.PersonTypeImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getPersonType()
		 * @generated
		 */
		EClass PERSON_TYPE = eINSTANCE.getPersonType();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.TechnicalTermImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getTechnicalTerm()
		 * @generated
		 */
		EClass TECHNICAL_TERM = eINSTANCE.getTechnicalTerm();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.DocumentImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ObjectiveImpl <em>Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ObjectiveImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getObjective()
		 * @generated
		 */
		EClass OBJECTIVE = eINSTANCE.getObjective();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.ProductImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.EpcNodeImpl <em>Epc Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.NodeEpcImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getNodeEpc()
		 * @generated NOT
		 */
		EClass INFORMATION_ARC = eINSTANCE.getInformationArc();
		
		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.EpcNodeImpl <em>Epc Node</em>}' class.
		 */
		EClass EPC_NODE = eINSTANCE.getEpcNode();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.epc.impl.EpcImpl <em>Epc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.epc.impl.EpcImpl
		 * @see org.bflow.toolbox.epc.impl.EpcPackageImpl#getEpc()
		 * @generated
		 */
		EClass EPC = eINSTANCE.getEpc();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPC__ELEMENTS = eINSTANCE.getEpc_Elements();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPC__CONNECTIONS = eINSTANCE.getEpc_Connections();
		

	}

} //EpcPackage
