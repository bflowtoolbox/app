/**
 */
package orgchart.provider;


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

import orgchart.Model;
import orgchart.OrgchartFactory;
import orgchart.OrgchartPackage;

/**
 * This is the item provider adapter for a {@link orgchart.Model} object.
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
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__PARTICIPANTS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__POSITIONS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__INTERNAL_PERSONS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__EXTERNAL_PERSONS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__PERSON_TYPES);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__LOCATIONS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__GROUPS);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__RELATIONS1);
			childrenFeatures.add(OrgchartPackage.Literals.MODEL__RELATIONS2);
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
			case OrgchartPackage.MODEL__PARTICIPANTS:
			case OrgchartPackage.MODEL__POSITIONS:
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
			case OrgchartPackage.MODEL__PERSON_TYPES:
			case OrgchartPackage.MODEL__LOCATIONS:
			case OrgchartPackage.MODEL__GROUPS:
			case OrgchartPackage.MODEL__RELATIONS1:
			case OrgchartPackage.MODEL__RELATIONS2:
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
				(OrgchartPackage.Literals.MODEL__PARTICIPANTS,
				 OrgchartFactory.eINSTANCE.createParticipant()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__POSITIONS,
				 OrgchartFactory.eINSTANCE.createPosition()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__INTERNAL_PERSONS,
				 OrgchartFactory.eINSTANCE.createInternalPerson()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__EXTERNAL_PERSONS,
				 OrgchartFactory.eINSTANCE.createExternalPerson()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__PERSON_TYPES,
				 OrgchartFactory.eINSTANCE.createPersonType()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__LOCATIONS,
				 OrgchartFactory.eINSTANCE.createLocation()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__GROUPS,
				 OrgchartFactory.eINSTANCE.createGroup()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__RELATIONS1,
				 OrgchartFactory.eINSTANCE.createRelation1()));

		newChildDescriptors.add
			(createChildParameter
				(OrgchartPackage.Literals.MODEL__RELATIONS2,
				 OrgchartFactory.eINSTANCE.createRelation2()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return OrgcEditPlugin.INSTANCE;
	}

}
