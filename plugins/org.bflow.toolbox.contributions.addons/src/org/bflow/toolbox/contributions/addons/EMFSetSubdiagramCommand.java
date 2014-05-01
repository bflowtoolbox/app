package org.bflow.toolbox.contributions.addons;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;

/**
 * Implements {@link AbstractCommand} to set the subdiagram value of 
 * an EPC Function or Process Interface.
 * 
 * @author Arian Storch
 * @since 10/02/13
 * @version 28/02/13
 */
public class EMFSetSubdiagramCommand extends AbstractCommand {
	
	private EObject eObject;
	private String value;
	private String oldValue = null;
	
	/**
	 * Constructor.
	 * @param eObj Function to edit
	 * @param value new subdiagram value
	 */
	public EMFSetSubdiagramCommand(EObject eObj, String value) {
		super("Set Subdiagram value");
		this.eObject = eObj;
		this.value = value;
	}

	@Override
	public void execute() {
		// Handling Process Interface
		if(eObject instanceof ProcessInterface) {
			ProcessInterface processInterface = (ProcessInterface)eObject;
			oldValue = processInterface.getSubdiagram();
			processInterface.setSubdiagram(value);
			return;
		}		
		
		// Handling Function
		Function function = (Function)eObject;
		
		if(function.getSubdiagram().size() > 0) {
			oldValue = function.getSubdiagram().get(0);
		}
		
		function.getSubdiagram().clear(); // TODO Check for better strategy
		function.getSubdiagram().add(value);
	}
	
	@Override
	public void undo() {
		// Handling Process Interface
		if(eObject instanceof ProcessInterface) {
			ProcessInterface processInterface = (ProcessInterface)eObject;
			processInterface.setSubdiagram(oldValue);
		}
		
		// Handling Function
		if(oldValue != null) {
			Function function = (Function)eObject;
			function.getSubdiagram().clear(); // TODO Check for better strategy
			function.getSubdiagram().add(oldValue);
		}
	}

	@Override
	public void redo() {
		execute();
	}

}
