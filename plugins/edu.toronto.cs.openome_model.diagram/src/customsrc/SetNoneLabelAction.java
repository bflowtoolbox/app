package customsrc;

import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;

public class SetNoneLabelAction extends SetEvaluationLabelAction {
	
	private EvaluationLabel privateEvalLabel = EvaluationLabel.NONE;
	private String privateID = "SetNoneLabelAction";
	private String privateEvalLabelText = "None";
	private String imageFile = "noneEval.png";
	
	protected SetNoneLabelAction(IWorkbenchPage workbenchPage) {
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