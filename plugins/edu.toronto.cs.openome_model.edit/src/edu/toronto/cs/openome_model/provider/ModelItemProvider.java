/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.provider;


import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link edu.toronto.cs.openome_model.Model} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			addCorrelationsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Model_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Model_name_feature", "_UI_Model_type"),
				 openome_modelPackage.Literals.MODEL__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Correlations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCorrelationsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Model_correlations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Model_correlations_feature", "_UI_Model_type"),
				 openome_modelPackage.Literals.MODEL__CORRELATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
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
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__INTENTIONS);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__DEPENDENCIES);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__DECOMPOSITIONS);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__CONTAINERS);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__ASSOCIATIONS);
			childrenFeatures.add(openome_modelPackage.Literals.MODEL__ALTERNATIVES);
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
	 * This returns Model.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Model"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Model)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Model_type") :
			getString("_UI_Model_type") + " " + label;
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

		switch (notification.getFeatureID(Model.class)) {
			case openome_modelPackage.MODEL__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case openome_modelPackage.MODEL__INTENTIONS:
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
			case openome_modelPackage.MODEL__DEPENDENCIES:
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
			case openome_modelPackage.MODEL__CONTAINERS:
			case openome_modelPackage.MODEL__ASSOCIATIONS:
			case openome_modelPackage.MODEL__ALTERNATIVES:
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
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createIntention()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createGoal()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createResource()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createSoftgoal()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createTask()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__INTENTIONS,
				 openome_modelFactory.eINSTANCE.createBelief()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createHelpContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createHurtContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createMakeContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createBreakContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createSomePlusContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createSomeMinusContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createUnknownContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createAndContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTRIBUTIONS,
				 openome_modelFactory.eINSTANCE.createOrContribution()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__DEPENDENCIES,
				 openome_modelFactory.eINSTANCE.createDependency()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__DECOMPOSITIONS,
				 openome_modelFactory.eINSTANCE.createDecomposition()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__DECOMPOSITIONS,
				 openome_modelFactory.eINSTANCE.createAndDecomposition()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__DECOMPOSITIONS,
				 openome_modelFactory.eINSTANCE.createOrDecomposition()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTAINERS,
				 openome_modelFactory.eINSTANCE.createActor()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTAINERS,
				 openome_modelFactory.eINSTANCE.createAgent()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTAINERS,
				 openome_modelFactory.eINSTANCE.createPosition()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__CONTAINERS,
				 openome_modelFactory.eINSTANCE.createRole()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createIsAAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createCoversAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createIsPartOfAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createOccupiesAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createPlaysAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ASSOCIATIONS,
				 openome_modelFactory.eINSTANCE.createINSAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(openome_modelPackage.Literals.MODEL__ALTERNATIVES,
				 openome_modelFactory.eINSTANCE.createAlternative()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return openome_modelEditPlugin.INSTANCE;
	}

}
