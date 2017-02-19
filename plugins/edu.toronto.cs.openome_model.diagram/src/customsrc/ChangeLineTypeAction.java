package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class ChangeLineTypeAction extends SetLineTypeAction {
	
	private String privateID = "ChangeLineTypeAction"; // this is how plugin.xml recognize us
	private String privateCommandLabelText = "Change Type";
	
	protected ChangeLineTypeAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage, "");
		this.ID = privateID; //$NON-NLS-1$
		this.commandName = privateCommandLabelText;
		setText(privateCommandLabelText);
	}
	
	
	
	public void init() {
		super.init();
		setId(ID);
		setText(privateCommandLabelText);
		
		refresh();
	}
	
	
}