package edu.toronto.cs.openome_model.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class OrContributionReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public OrContributionReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof edu.toronto.cs.openome_model.OrContribution) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof edu.toronto.cs.openome_model.Intention && newEnd instanceof edu.toronto.cs.openome_model.Intention)) {
			return false;
		}
		edu.toronto.cs.openome_model.Intention target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof edu.toronto.cs.openome_model.Model)) {
			return false;
		}
		edu.toronto.cs.openome_model.Model container = (edu.toronto.cs.openome_model.Model) getLink()
				.eContainer();
		return edu.toronto.cs.openome_model.diagram.edit.policies.Openome_modelBaseItemSemanticEditPolicy.LinkConstraints
				.canExistOrContribution_4013(container, getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof edu.toronto.cs.openome_model.Intention && newEnd instanceof edu.toronto.cs.openome_model.Intention)) {
			return false;
		}
		edu.toronto.cs.openome_model.Intention source = getLink().getSource();
		if (!(getLink().eContainer() instanceof edu.toronto.cs.openome_model.Model)) {
			return false;
		}
		edu.toronto.cs.openome_model.Model container = (edu.toronto.cs.openome_model.Model) getLink()
				.eContainer();
		return edu.toronto.cs.openome_model.diagram.edit.policies.Openome_modelBaseItemSemanticEditPolicy.LinkConstraints
				.canExistOrContribution_4013(container, source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected edu.toronto.cs.openome_model.OrContribution getLink() {
		return (edu.toronto.cs.openome_model.OrContribution) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected edu.toronto.cs.openome_model.Intention getOldSource() {
		return (edu.toronto.cs.openome_model.Intention) oldEnd;
	}

	/**
	 * @generated
	 */
	protected edu.toronto.cs.openome_model.Intention getNewSource() {
		return (edu.toronto.cs.openome_model.Intention) newEnd;
	}

	/**
	 * @generated
	 */
	protected edu.toronto.cs.openome_model.Intention getOldTarget() {
		return (edu.toronto.cs.openome_model.Intention) oldEnd;
	}

	/**
	 * @generated
	 */
	protected edu.toronto.cs.openome_model.Intention getNewTarget() {
		return (edu.toronto.cs.openome_model.Intention) newEnd;
	}
}
