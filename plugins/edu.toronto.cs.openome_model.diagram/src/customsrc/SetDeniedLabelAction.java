package customsrc;

import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;

public class SetDeniedLabelAction extends SetEvaluationLabelAction {
	
	private EvaluationLabel privateEvalLabel = EvaluationLabel.DENIED;
	private String privateID = "SetDeniedLabelAction";
	private String privateEvalLabelText = "Denied";
	private String imageFile = "deniedEval.png";
	
	protected SetDeniedLabelAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
		this.evalField = privateEvalLabel;
		this.ID = privateID; //$NON-NLS-1$
		this.evalLabel = privateEvalLabelText;
		init();
	}
	
	
	
	public void init() {
		super.init();
		setId(ID);
		setText(privateEvalLabelText);
		
		setImageDescriptor(Openome_modelDiagramEditorPlugin.getBundledImageDescriptor("icons/evalLabels/" + imageFile));
		refresh();
	}
	
	
}