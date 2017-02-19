package edu.toronto.cs.openome.evaluation.gui;

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.ElementTypeLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import edu.toronto.cs.openome_model.EvaluationLabel;

public class EvaluationElementTypeLabelProvider extends
		ElementTypeLabelProvider {
	
	protected String getFileName(EvaluationLabel l) {
		String image = "satisfiedEval.png";
		
		if (l == EvaluationLabel.PARTIALLY_SATISFIED)  {
			image = "weaklySatisfiedEval.png";
		}
		if (l == EvaluationLabel.CONFLICT)  {
			image = "conflictEval.png";
		}
		if (l == EvaluationLabel.UNKNOWN)  {
			image = "unknownEval.png";
		}
		if (l == EvaluationLabel.PARTIALLY_DENIED) {
			image = "weaklyDeniedEval.png";
		}
		if (l == EvaluationLabel.DENIED) {
			image = "deniedEval.png";
		}
		
		return image;
	}
	
	public Image getEvalImage(EvaluationLabel l)  {
		String image = getFileName(l);
		
		Image icon = null;
		
		try {
			icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle("edu.toronto.cs.openome.evaluation"),
				new Path("icons/evalLabels/" + image), null).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return icon;
	}

}
