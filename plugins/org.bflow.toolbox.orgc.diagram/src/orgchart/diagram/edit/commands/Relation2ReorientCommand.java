/*
 * 
 */
package orgchart.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import orgchart.All_Rel_2;
import orgchart.Model;
import orgchart.Relation2;
import orgchart.diagram.edit.policies.OrgcBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class Relation2ReorientCommand extends EditElementCommand {

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
	public Relation2ReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Relation2) {
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
		if (!(oldEnd instanceof All_Rel_2 && newEnd instanceof All_Rel_2)) {
			return false;
		}
		All_Rel_2 target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Model)) {
			return false;
		}
		Model container = (Model) getLink().eContainer();
		return OrgcBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistRelation2_4002(container, getLink(), getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof All_Rel_2 && newEnd instanceof All_Rel_2)) {
			return false;
		}
		All_Rel_2 source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Model)) {
			return false;
		}
		Model container = (Model) getLink().eContainer();
		return OrgcBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistRelation2_4002(container, getLink(), source,
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
	protected Relation2 getLink() {
		return (Relation2) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected All_Rel_2 getOldSource() {
		return (All_Rel_2) oldEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_2 getNewSource() {
		return (All_Rel_2) newEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_2 getOldTarget() {
		return (All_Rel_2) oldEnd;
	}

	/**
	 * @generated
	 */
	protected All_Rel_2 getNewTarget() {
		return (All_Rel_2) newEnd;
	}
}
