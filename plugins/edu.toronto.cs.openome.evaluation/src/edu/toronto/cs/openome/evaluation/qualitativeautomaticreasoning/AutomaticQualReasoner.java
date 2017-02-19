/**
 * 
 */
package edu.toronto.cs.openome.evaluation.qualitativeautomaticreasoning;

import java.util.ListIterator;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;

import edu.toronto.cs.openome.evaluation.reasoning.Reasoner;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.impl.ModelImpl;
/**
 * @author jenhork
 *
 */
public class AutomaticQualReasoner extends Reasoner {

	Model model;
	public AutomaticQualReasoner(ModelImpl m, CommandStack com,
			DiagramCommandStack d) {
		model = m;
//		super(m, com, d);
	}
	
	public void reason() {
//		QueryVariability2 qv = new QueryVariability();
//		qv.init(model);		
	}
	
	protected EvaluationLabel resolveOtherCases(Intention i) {
		
		ListIterator<EvaluationLabel> it = i.getLabelBag().getLabelBagEvalLabels().listIterator();
				
		int FSCount = 0;
		int PSCount = 0;
		int CCount = 0;
		int UCount = 0;
		int PDCount = 0;
		int FDCount = 0;
		
		while (it.hasNext()) {
			EvaluationLabel label =  it.next();
						
			if (label == EvaluationLabel.SATISFIED) {
				FSCount++;
			}
			if (label == EvaluationLabel.PARTIALLY_SATISFIED)  {
				PSCount++;
			}
			if (label == EvaluationLabel.CONFLICT) {
				CCount++;
			}
			if (label == EvaluationLabel.UNKNOWN) {
				UCount++;
			}
			if (label == EvaluationLabel.PARTIALLY_DENIED) {
				PDCount++;
			}
			if (label == EvaluationLabel.DENIED) {
				FDCount++;
			}
		}
		
		double sum = FSCount + PSCount + CCount + UCount + PDCount + FDCount;
		
		double FSPerc = FSCount/sum;
		double PSPerc = PSCount/sum;
		double CPerc = CCount/sum;
		double UPerc = UCount/sum;
		double PDPerc = PDCount/sum;
		double FDPerc = FDCount/sum;
		
		//System.out.println(FSPerc + ", " + PSPerc + ", " + CPerc + ", " + UPerc + ", " + PDPerc + ", " + FDPerc);
		
		//I'm pulling these numbers out of my ass
		if (UPerc > 0.4)
			return EvaluationLabel.UNKNOWN;
		if ((FSPerc + PSPerc) > 0.9)
			return EvaluationLabel.SATISFIED;
		if ((FSPerc + PSPerc) > 0.7)
			return EvaluationLabel.PARTIALLY_SATISFIED;
		if ((FDPerc + PDPerc) > 0.9)
			return EvaluationLabel.DENIED;
		if ((FDPerc + PDPerc) > 0.7)
			return EvaluationLabel.PARTIALLY_DENIED;
		
		return EvaluationLabel.CONFLICT;
	}

}
