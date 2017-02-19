package customsrc;

import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;

public class ChangeToAndDecompositionAction extends SetLineTypeAction {
	
	
	private String privateID = "ChangeToAndDecompositionAction"; // this is how plugin.xml recognize us
	private static String privateCommandLabelText = "Decomposition";;
	private String imageFile = "anddecomposition.gif";
	
	protected ChangeToAndDecompositionAction(IWorkbenchPage workbenchPage) {
		
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