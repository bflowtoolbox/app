/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.provider;


import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
 * This is the item provider adapter for a {@link edu.toronto.cs.openome_model.LabelBag} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LabelBagItemProvider
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
	public LabelBagItemProvider(AdapterFactory adapterFactory) {
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

			addLabelBagIntentionsPropertyDescriptor(object);
			addLabelBagEvalLabelsPropertyDescriptor(object);
			addToResolvePropertyDescriptor(object);
			addAllPositivePropertyDescriptor(object);
			addAllNegativePropertyDescriptor(object);
			addHasFullPositivePropertyDescriptor(object);
			addHasFullNegativePropertyDescriptor(object);
			addHasUnknownPropertyDescriptor(object);
			addHasConflictPropertyDescriptor(object);
			addAllUnknownPropertyDescriptor(object);
			addAllConflictPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Label Bag Intentions feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelBagIntentionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_labelBagIntentions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_labelBagIntentions_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__LABEL_BAG_INTENTIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Label Bag Eval Labels feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelBagEvalLabelsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_labelBagEvalLabels_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_labelBagEvalLabels_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__LABEL_BAG_EVAL_LABELS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the To Resolve feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addToResolvePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_toResolve_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_toResolve_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__TO_RESOLVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the All Positive feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllPositivePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_allPositive_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_allPositive_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__ALL_POSITIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the All Negative feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllNegativePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_allNegative_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_allNegative_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__ALL_NEGATIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Full Positive feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHasFullPositivePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_hasFullPositive_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_hasFullPositive_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__HAS_FULL_POSITIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Full Negative feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHasFullNegativePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_hasFullNegative_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_hasFullNegative_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__HAS_FULL_NEGATIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Unknown feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHasUnknownPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_hasUnknown_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_hasUnknown_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__HAS_UNKNOWN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Conflict feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHasConflictPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_hasConflict_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_hasConflict_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__HAS_CONFLICT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the All Unknown feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllUnknownPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_allUnknown_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_allUnknown_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__ALL_UNKNOWN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the All Conflict feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllConflictPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LabelBag_allConflict_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LabelBag_allConflict_feature", "_UI_LabelBag_type"),
				 openome_modelPackage.Literals.LABEL_BAG__ALL_CONFLICT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns LabelBag.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LabelBag"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		LabelBag labelBag = (LabelBag)object;
		return getString("_UI_LabelBag_type") + " " + labelBag.isToResolve();
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

		switch (notification.getFeatureID(LabelBag.class)) {
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS:
			case openome_modelPackage.LABEL_BAG__TO_RESOLVE:
			case openome_modelPackage.LABEL_BAG__ALL_POSITIVE:
			case openome_modelPackage.LABEL_BAG__ALL_NEGATIVE:
			case openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE:
			case openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE:
			case openome_modelPackage.LABEL_BAG__HAS_UNKNOWN:
			case openome_modelPackage.LABEL_BAG__HAS_CONFLICT:
			case openome_modelPackage.LABEL_BAG__ALL_UNKNOWN:
			case openome_modelPackage.LABEL_BAG__ALL_CONFLICT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
