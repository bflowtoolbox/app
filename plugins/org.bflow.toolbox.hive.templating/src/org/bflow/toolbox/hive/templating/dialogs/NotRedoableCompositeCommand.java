package org.bflow.toolbox.hive.templating.dialogs;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;

public class NotRedoableCompositeCommand extends CompositeCommand{

	public NotRedoableCompositeCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public boolean canRedo() {
	       
	        return false;
	    }

}
