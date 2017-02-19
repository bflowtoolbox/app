package openome_model.figures;

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class EvaluationIconProvider {
	private final static String ICON_PACKAGE = "openome_model";
	
	public static Image getEvaluationIcon (String evaluationLabel) {
		Image icon = null;

		if (evaluationLabel.equals("Unknown")) {
			
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/unknownEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (evaluationLabel.equals("Satisfied")) {
			
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/satisfiedEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (evaluationLabel.equals("Denied")) {
			
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/deniedEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (evaluationLabel.equals("PartiallyDenied")) {
			
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/weaklyDeniedEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (evaluationLabel.equals("PartiallySatisfied")) {
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/weaklySatisfiedEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (evaluationLabel.equals("Conflict")) {
			try {
				icon = new Image (Display.getCurrent(), FileLocator.find(Platform.getBundle(ICON_PACKAGE),
						new Path("icons/conflictEval.png"), null).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return icon;
	}
}
