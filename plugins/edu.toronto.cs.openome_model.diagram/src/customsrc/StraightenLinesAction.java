package customsrc;

import org.eclipse.ui.IWorkbenchPage;

public class StraightenLinesAction extends SetLineConnectionAction {
	
	private String privateID = "StraightenLinesAction"; // this is how plugin.xml recognize us
	private String privateCommandLabelText = "Straighten Line";
	
	protected StraightenLinesAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
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