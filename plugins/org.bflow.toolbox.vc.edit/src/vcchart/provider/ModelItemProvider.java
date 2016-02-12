/**
 */
package vcchart.provider;


import java.util.Collection;
import java.util.List;

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

import vcchart.Model;
import vcchart.VcchartFactory;
import vcchart.VcchartPackage;

/**
 * This is the item provider adapter for a {@link vcchart.Model} object.
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
			childrenFeatures.add(VcchartPackage.Literals.MODEL__ACTIVITYS1);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__ACTIVITYS2);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__CLUSTERS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__PRODUCTS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__TECHNICAL_TERMS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__OBJECTIVES);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__PARTICIPANTS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__APPLICATIONS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__DOCUMENTS);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__RELATIONS1);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__RELATIONS2);
			childrenFeatures.add(VcchartPackage.Literals.MODEL__RELATIONS3);
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
		return getString("_UI_Model_type");
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
			case VcchartPackage.MODEL__ACTIVITYS1:
			case VcchartPackage.MODEL__ACTIVITYS2:
			case VcchartPackage.MODEL__CLUSTERS:
			case VcchartPackage.MODEL__PRODUCTS:
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
			case VcchartPackage.MODEL__OBJECTIVES:
			case VcchartPackage.MODEL__PARTICIPANTS:
			case VcchartPackage.MODEL__APPLICATIONS:
			case VcchartPackage.MODEL__DOCUMENTS:
			case VcchartPackage.MODEL__RELATIONS1:
			case VcchartPackage.MODEL__RELATIONS2:
			case VcchartPackage.MODEL__RELATIONS3:
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
				(VcchartPackage.Literals.MODEL__ACTIVITYS1,
				 VcchartFactory.eINSTANCE.createActivity1()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__ACTIVITYS2,
				 VcchartFactory.eINSTANCE.createActivity2()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__CLUSTERS,
				 VcchartFactory.eINSTANCE.createCluster()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__PRODUCTS,
				 VcchartFactory.eINSTANCE.createProduct()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__TECHNICAL_TERMS,
				 VcchartFactory.eINSTANCE.createTechnicalTerm()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__OBJECTIVES,
				 VcchartFactory.eINSTANCE.createObjective()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__PARTICIPANTS,
				 VcchartFactory.eINSTANCE.createParticipant()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__APPLICATIONS,
				 VcchartFactory.eINSTANCE.createApplication()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__DOCUMENTS,
				 VcchartFactory.eINSTANCE.createDocument()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__RELATIONS1,
				 VcchartFactory.eINSTANCE.createRelation1()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__RELATIONS2,
				 VcchartFactory.eINSTANCE.createRelation2()));

		newChildDescriptors.add
			(createChildParameter
				(VcchartPackage.Literals.MODEL__RELATIONS3,
				 VcchartFactory.eINSTANCE.createRelation3()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return VcEditPlugin.INSTANCE;
	}

}
