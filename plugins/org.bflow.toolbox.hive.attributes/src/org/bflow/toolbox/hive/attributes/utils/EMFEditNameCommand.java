package org.bflow.toolbox.hive.attributes.utils;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Implements {@link AbstractCommand} to edit the name of an EMF element.
 * 
 * @author Arian Storch
 * @since 27/07/12
 *
 */
public class EMFEditNameCommand extends AbstractCommand {

	EObject eObject;
	String value;
	String oldValue;

	/**
	 * Constructor.
	 * 
	 * @param eObject Instance to edit
	 * @param value new value for name
	 */
	public EMFEditNameCommand(EObject eObject, String value) {
		super("SetNameCommend", "Sets the name of an eobject during an "
				+ "attribute adjust component is running.");
		this.eObject = eObject;
		this.value = value;
	}

	@Override
	public void execute() {
		oldValue = EMFCoreUtil.getName(eObject);
		EMFCoreUtil.setName(eObject, value);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		EMFCoreUtil.setName(eObject, oldValue);
	}
}
