package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeToPlaysAssociationAction extends SetLineTypeAction {
	
	private String privateID = "ChangeToPlaysAssociationAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Plays";;

	
	protected ChangeToPlaysAssociationAction(IWorkbenchPage workbenchPage) {
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