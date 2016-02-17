/*
 * 
 */
package vcchart.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import vcchart.All_Rel_3;
import vcchart.Model;
import vcchart.Relation3;
import vcchart.diagram.edit.policies.VcBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class Relation3ReorientCommand extends EditElementCommand {

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
	public Relation3ReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Relation3) {
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
		if (!(oldEnd instanceof All_Rel_3 && newEnd instanceof All_Rel_3)) {
			return false;
		}
		All_Rel_3 target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Model)) {
			return false;
		}
		Model container = (Model) getLink().eContainer();
		return VcBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistRelation3_4003(container, getLink(), getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof All_Rel_3 && newEnd instanceof All_Rel_3)) {
			return false;
		}
		All_Rel_3 source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Model)) {
			return false;
		}
		Model container = (Model) getLink().eContainer();
		return VcBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistRelation3_4003(container, getLink(), source,
						getNewTarget());
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
	protected Relation3 getLink() {
		return (Relation3) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected All_Rel_3 getOldSource() {
		return (All_Rel_3) oldEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_3 getNewSource() {
		return (All_Rel_3) newEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_3 getOldTarget() {
		return (All_Rel_3) oldEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_3 getNewTarget() {
		return (All_Rel_3) newEnd;
	}
}
