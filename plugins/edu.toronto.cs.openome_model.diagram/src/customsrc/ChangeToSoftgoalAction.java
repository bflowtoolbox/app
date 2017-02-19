package customsrc;

import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;

public class ChangeToSoftgoalAction extends SetIntentionTypeAction {
	
	private String privateID = "ChangeToSoftgoalAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Softgoal";
	private String imageFile = "softgoal.gif";
	
	protected ChangeToSoftgoalAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage, privateCommandLabelText);
		
		this.ID = privateID; //$NON-NLS-1$
		this.commandName = privateCommandLabelText;
		init();
	}
	
	
	
	public void init() {
		super.init();
		setId(ID);
		setText(privateCommandLabelText);
		setImageDescriptor(Openome_modelDiagramEditorPlugin.getBundledImageDescriptor("../openome_model/icons/" + imageFile));
		refresh();
	}
	
	
}