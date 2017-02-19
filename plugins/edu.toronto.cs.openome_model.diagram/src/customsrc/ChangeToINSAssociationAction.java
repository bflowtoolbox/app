package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeToINSAssociationAction extends SetLineTypeAction {
	
	private String privateID = "ChangeToINSAssociationAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "INS";;

	
	protected ChangeToINSAssociationAction(IWorkbenchPage workbenchPage) {
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