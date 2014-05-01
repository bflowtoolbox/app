/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc.provider;


import java.util.Collection;
import java.util.List;

import oepc.OEPC;
import oepc.OepcFactory;
import oepc.OepcPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link oepc.OEPC} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OEPCItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OEPCItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OepcPackage.Literals.OEPC__ELEMENTS);
			childrenFeatures.add(OepcPackage.Literals.OEPC__CONNECTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns OEPC.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/OEPC"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_OEPC_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(OEPC.class)) {
			case OepcPackage.OEPC__ELEMENTS:
			case OepcPackage.OEPC__CONNECTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createEvent()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createBusinessObject()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createITSystem()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createOrganisationUnit()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createXORConnector()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createANDConnector()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createORConnector()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__ELEMENTS,
				 OepcFactory.eINSTANCE.createDocument()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__CONNECTIONS,
				 OepcFactory.eINSTANCE.createControlFlowEdge()));

		newChildDescriptors.add
			(createChildParameter
				(OepcPackage.Literals.OEPC__CONNECTIONS,
				 OepcFactory.eINSTANCE.createInformationEdge()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return OepcEditPlugin.INSTANCE;
	}

}
