/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.bflow;

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
 * @see org.bflow.toolbox.bflow.BflowFactory
 * @model kind="package"
 * @generated
 */
public interface BflowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bflow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.bflow.toolbox.bflow";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bflow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BflowPackage eINSTANCE = org.bflow.toolbox.bflow.impl.BflowPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.impl.BflowSymbolImpl <em>Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.impl.BflowSymbolImpl
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getBflowSymbol()
	 * @generated
	 */
	int BFLOW_SYMBOL = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW_SYMBOL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW_SYMBOL__ID = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW_SYMBOL__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW_SYMBOL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.impl.ConnectionImpl
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__NAME = BFLOW_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = BFLOW_SYMBOL__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__DESCRIPTION = BFLOW_SYMBOL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__FROM = BFLOW_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TO = BFLOW_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = BFLOW_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.impl.ElementImpl
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__NAME = BFLOW_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__ID = BFLOW_SYMBOL__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__DESCRIPTION = BFLOW_SYMBOL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__IN = BFLOW_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__OUT = BFLOW_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = BFLOW_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.IConnector <em>IConnector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.IConnector
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIConnector()
	 * @generated
	 */
	int ICONNECTOR = 3;

	/**
	 * The number of structural features of the '<em>IConnector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONNECTOR_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.IBflowElement <em>IBflow Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.IBflowElement
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIBflowElement()
	 * @generated
	 */
	int IBFLOW_ELEMENT = 4;

	/**
	 * The number of structural features of the '<em>IBflow Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBFLOW_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.IEBflowElement <em>IE Bflow Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.IEBflowElement
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIEBflowElement()
	 * @generated
	 */
	int IE_BFLOW_ELEMENT = 5;

	/**
	 * The number of structural features of the '<em>IE Bflow Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IE_BFLOW_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.bflow.impl.BflowImpl <em>Bflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.bflow.impl.BflowImpl
	 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getBflow()
	 * @generated
	 */
	int BFLOW = 6;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW__CONNECTIONS = 1;

	/**
	 * The number of structural features of the '<em>Bflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BFLOW_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.BflowSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Symbol</em>'.
	 * @see org.bflow.toolbox.bflow.BflowSymbol
	 * @generated
	 */
	EClass getBflowSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.bflow.BflowSymbol#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.bflow.toolbox.bflow.BflowSymbol#getName()
	 * @see #getBflowSymbol()
	 * @generated
	 */
	EAttribute getBflowSymbol_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.bflow.BflowSymbol#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.bflow.toolbox.bflow.BflowSymbol#getId()
	 * @see #getBflowSymbol()
	 * @generated
	 */
	EAttribute getBflowSymbol_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.bflow.BflowSymbol#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.bflow.toolbox.bflow.BflowSymbol#getDescription()
	 * @see #getBflowSymbol()
	 * @generated
	 */
	EAttribute getBflowSymbol_Description();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.bflow.toolbox.bflow.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.bflow.toolbox.bflow.Connection#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.bflow.toolbox.bflow.Connection#getFrom()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_From();

	/**
	 * Returns the meta object for the reference '{@link org.bflow.toolbox.bflow.Connection#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.bflow.toolbox.bflow.Connection#getTo()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_To();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.bflow.toolbox.bflow.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the reference list '{@link org.bflow.toolbox.bflow.Element#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In</em>'.
	 * @see org.bflow.toolbox.bflow.Element#getIn()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_In();

	/**
	 * Returns the meta object for the reference list '{@link org.bflow.toolbox.bflow.Element#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out</em>'.
	 * @see org.bflow.toolbox.bflow.Element#getOut()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_Out();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.IConnector <em>IConnector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConnector</em>'.
	 * @see org.bflow.toolbox.bflow.IConnector
	 * @generated
	 */
	EClass getIConnector();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.IBflowElement <em>IBflow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBflow Element</em>'.
	 * @see org.bflow.toolbox.bflow.IBflowElement
	 * @generated
	 */
	EClass getIBflowElement();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.IEBflowElement <em>IE Bflow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IE Bflow Element</em>'.
	 * @see org.bflow.toolbox.bflow.IEBflowElement
	 * @generated
	 */
	EClass getIEBflowElement();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.bflow.Bflow <em>Bflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bflow</em>'.
	 * @see org.bflow.toolbox.bflow.Bflow
	 * @generated
	 */
	EClass getBflow();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.bflow.Bflow#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.bflow.toolbox.bflow.Bflow#getElements()
	 * @see #getBflow()
	 * @generated
	 */
	EReference getBflow_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.bflow.Bflow#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.bflow.toolbox.bflow.Bflow#getConnections()
	 * @see #getBflow()
	 * @generated
	 */
	EReference getBflow_Connections();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BflowFactory getBflowFactory();

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
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.impl.BflowSymbolImpl <em>Symbol</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.impl.BflowSymbolImpl
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getBflowSymbol()
		 * @generated
		 */
		EClass BFLOW_SYMBOL = eINSTANCE.getBflowSymbol();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BFLOW_SYMBOL__NAME = eINSTANCE.getBflowSymbol_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BFLOW_SYMBOL__ID = eINSTANCE.getBflowSymbol_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BFLOW_SYMBOL__DESCRIPTION = eINSTANCE.getBflowSymbol_Description();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.impl.ConnectionImpl
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__FROM = eINSTANCE.getConnection_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__TO = eINSTANCE.getConnection_To();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.impl.ElementImpl
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>In</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__IN = eINSTANCE.getElement_In();

		/**
		 * The meta object literal for the '<em><b>Out</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__OUT = eINSTANCE.getElement_Out();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.IConnector <em>IConnector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.IConnector
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIConnector()
		 * @generated
		 */
		EClass ICONNECTOR = eINSTANCE.getIConnector();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.IBflowElement <em>IBflow Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.IBflowElement
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIBflowElement()
		 * @generated
		 */
		EClass IBFLOW_ELEMENT = eINSTANCE.getIBflowElement();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.IEBflowElement <em>IE Bflow Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.IEBflowElement
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getIEBflowElement()
		 * @generated
		 */
		EClass IE_BFLOW_ELEMENT = eINSTANCE.getIEBflowElement();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.bflow.impl.BflowImpl <em>Bflow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.bflow.impl.BflowImpl
		 * @see org.bflow.toolbox.bflow.impl.BflowPackageImpl#getBflow()
		 * @generated
		 */
		EClass BFLOW = eINSTANCE.getBflow();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BFLOW__ELEMENTS = eINSTANCE.getBflow_Elements();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BFLOW__CONNECTIONS = eINSTANCE.getBflow_Connections();

	}

} //BflowPackage
