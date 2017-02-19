package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeToCoversAssociationAction extends SetLineTypeAction {
	
	private String privateID = "ChangeToCoversAssociationAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Covers";

	
	protected ChangeToCoversAssociationAction(IWorkbenchPage workbenchPage) {
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