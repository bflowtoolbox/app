/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc.provider;


import java.util.Collection;
import java.util.List;

import org.bflow.toolbox.bflow.provider.ElementItemProvider;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.EpcFactory;
import org.bflow.toolbox.epc.EpcPackage;
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
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bflow.toolbox.epc.Epc} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EpcItemProvider
	extends ElementItemProvider
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
	public EpcItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(EpcPackage.Literals.EPC__ELEMENTS);
			childrenFeatures.add(EpcPackage.Literals.EPC__CONNECTIONS);
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
	 * This returns Epc.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Epc"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_Epc_type");
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

		switch (notification.getFeatureID(Epc.class)) {
			case EpcPackage.EPC__ELEMENTS:
			case EpcPackage.EPC__CONNECTIONS:
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
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createEvent()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createFunction()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createProcessInterface()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createApplication()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createParticipant()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createAND()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createOR()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createXOR()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createGroup()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createLocation()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createPosition()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createFile()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createCardFile()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createCluster()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createInternalPerson()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createExternalPerson()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createPersonType()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createTechnicalTerm()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createDocument()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createObjective()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__ELEMENTS,
				 EpcFactory.eINSTANCE.createProduct()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__CONNECTIONS,
				 EpcFactory.eINSTANCE.createArc()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__CONNECTIONS,
				 EpcFactory.eINSTANCE.createRelation()));

		newChildDescriptors.add
			(createChildParameter
				(EpcPackage.Literals.EPC__CONNECTIONS,
				 EpcFactory.eINSTANCE.createInformationArc()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return EpcEditPlugin.INSTANCE;
	}

}
