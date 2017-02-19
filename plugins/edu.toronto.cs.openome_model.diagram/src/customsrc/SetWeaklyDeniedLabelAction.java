package customsrc;

import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;

public class SetWeaklyDeniedLabelAction extends SetEvaluationLabelAction {
	
	private EvaluationLabel privateEvalLabel = EvaluationLabel.PARTIALLY_DENIED;
	private String privateID = "SetWeaklyDeniedLabelAction";
	private String privateEvalLabelText = "Partially Denied";
	private String imageFile = "weaklyDeniedEval.png";
	
	protected SetWeaklyDeniedLabelAction(IWorkbenchPage workbenchPage) {
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