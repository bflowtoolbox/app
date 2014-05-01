/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see oepc.OepcPackage
 * @generated
 */
public interface OepcFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OepcFactory eINSTANCE = oepc.impl.OepcFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>OEPC</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OEPC</em>'.
	 * @generated
	 */
	OEPC createOEPC();

	/**
	 * Returns a new object of class '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event</em>'.
	 * @generated
	 */
	Event createEvent();

	/**
	 * Returns a new object of class '<em>Business Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Object</em>'.
	 * @generated
	 */
	BusinessObject createBusinessObject();

	/**
	 * Returns a new object of class '<em>IT System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IT System</em>'.
	 * @generated
	 */
	ITSystem createITSystem();

	/**
	 * Returns a new object of class '<em>Organisation Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Organisation Unit</em>'.
	 * @generated
	 */
	OrganisationUnit createOrganisationUnit();

	/**
	 * Returns a new object of class '<em>XOR Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XOR Connector</em>'.
	 * @generated
	 */
	XORConnector createXORConnector();

	/**
	 * Returns a new object of class '<em>AND Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AND Connector</em>'.
	 * @generated
	 */
	ANDConnector createANDConnector();

	/**
	 * Returns a new object of class '<em>OR Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OR Connector</em>'.
	 * @generated
	 */
	ORConnector createORConnector();

	/**
	 * Returns a new object of class '<em>Control Flow Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Flow Edge</em>'.
	 * @generated
	 */
	ControlFlowEdge createControlFlowEdge();

	/**
	 * Returns a new object of class '<em>Information Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Information Edge</em>'.
	 * @generated
	 */
	InformationEdge createInformationEdge();

	/**
	 * Returns a new object of class '<em>Business Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Attribute</em>'.
	 * @generated
	 */
	BusinessAttribute createBusinessAttribute();

	/**
	 * Returns a new object of class '<em>Business Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Business Method</em>'.
	 * @generated
	 */
	BusinessMethod createBusinessMethod();

	/**
	 * Returns a new object of class '<em>Document</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document</em>'.
	 * @generated
	 */
	Document createDocument();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OepcPackage getOepcPackage();

} //OepcFactory
