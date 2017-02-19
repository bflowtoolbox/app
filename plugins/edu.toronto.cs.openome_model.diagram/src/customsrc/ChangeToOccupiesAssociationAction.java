package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeToOccupiesAssociationAction extends SetLineTypeAction {
	
	private String privateID = "ChangeToOccupiesAssociationAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Occupies";

	
	protected ChangeToOccupiesAssociationAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage, privateCommandLabelText);
		
		this.ID = privateID; //$NON-NLS-1$
		this.commandName = privateCommandLabelText;
		init();
	}
	
	
	
	public void init() {
		super.init();
		setId(ID);
		setText(privateCommandLabelText);
		
		refresh();
	}
	
	
}