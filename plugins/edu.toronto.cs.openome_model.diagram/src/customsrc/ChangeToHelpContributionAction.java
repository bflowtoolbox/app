package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeToHelpContributionAction extends SetLineTypeAction {
	
	private String privateID = "ChangeToHelpContributionAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Help";

	
	protected ChangeToHelpContributionAction(IWorkbenchPage workbenchPage) {
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