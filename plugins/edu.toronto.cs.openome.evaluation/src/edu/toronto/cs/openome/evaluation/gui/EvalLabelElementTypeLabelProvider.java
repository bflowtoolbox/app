package edu.toronto.cs.openome.evaluation.gui;


import org.eclipse.swt.graphics.Image;
import edu.toronto.cs.openome_model.EvaluationLabel;


public class EvalLabelElementTypeLabelProvider extends
		EvaluationElementTypeLabelProvider {
	
	/**
	 * Retrieves the image for <code>IElementType</code> objects using
	 * the <code>IconService</code>.
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object object) {
		if (object instanceof EvaluationLabel) {
			return getEvalImage((EvaluationLabel) object);  
		} else {
			return null;
		}
		
	}

	/**
	 * Uses <code>IElementType.getDisplayName()</code> for the text.
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		if (object instanceof EvaluationLabel) {
			
			EvaluationLabel l = (EvaluationLabel) object;
			return l.getName();			
		} else {
			return object.toString();
		}
	}

}
