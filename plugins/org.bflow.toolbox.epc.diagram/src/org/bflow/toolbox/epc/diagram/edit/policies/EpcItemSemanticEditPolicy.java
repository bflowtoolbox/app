package org.bflow.toolbox.epc.diagram.edit.policies;

import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.diagram.edit.commands.ANDCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ApplicationCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.CardFileCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ClusterCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.DocumentCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.EventCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ExternalPersonCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.FileCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.FunctionCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.GroupCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.InternalPersonCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.LocationCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ORCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ObjectiveCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ParticipantCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.PersonTypeCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.PositionCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ProcessInterfaceCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ProductCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.TechnicalTermCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.XORCreateCommand;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class EpcItemSemanticEditPolicy extends EpcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) 
	{		
		if (EpcElementTypes.OR_2001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ORCreateCommand(req));
		}
		if (EpcElementTypes.Participant_2002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ParticipantCreateCommand(req));
		}
		if (EpcElementTypes.AND_2003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ANDCreateCommand(req));
		}
		if (EpcElementTypes.Application_2004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ApplicationCreateCommand(req));
		}
		if (EpcElementTypes.ProcessInterface_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ProcessInterfaceCreateCommand(req));
		}
		if (EpcElementTypes.Event_2006 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new EventCreateCommand(req));
		}
		if (EpcElementTypes.Function_2007 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new FunctionCreateCommand(req));
		}
		if (EpcElementTypes.XOR_2008 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new XORCreateCommand(req));
		}
		if (EpcElementTypes.Group_2009 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new GroupCreateCommand(req));
		}
		if (EpcElementTypes.Cluster_2010 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ClusterCreateCommand(req));
		}
		if (EpcElementTypes.ExternalPerson_2011 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ExternalPersonCreateCommand(req));
		}
		if (EpcElementTypes.InternalPerson_2012 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new InternalPersonCreateCommand(req));
		}
		if (EpcElementTypes.Position_2013 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new PositionCreateCommand(req));
		}
		if (EpcElementTypes.Location_2014 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new LocationCreateCommand(req));
		}
		if (EpcElementTypes.PersonType_2015 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new PersonTypeCreateCommand(req));
		}
		if (EpcElementTypes.TechnicalTerm_2016 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new TechnicalTermCreateCommand(req));
		}
		if (EpcElementTypes.CardFile_2017 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new CardFileCreateCommand(req));
		}
		if (EpcElementTypes.Document_2018 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new DocumentCreateCommand(req));
		}
		if (EpcElementTypes.File_2019 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new FileCreateCommand(req));
		}
		if (EpcElementTypes.Objective_2020 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ObjectiveCreateCommand(req));
		}
		if (EpcElementTypes.Product_2021 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EpcPackage.eINSTANCE
						.getEpc_Elements());
			}
			return getGEFWrapper(new ProductCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
