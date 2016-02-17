package org.bflow.toolbox.epc.templating.dialogs;

import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.graphics.Point;

/**
 * Factory for the TemplateEmbedService.
 * If the insert option is before a new TemplateEmbedBeforeService will be initialed.
 * If the insert option is after a new TemplateEmbedInsertService will be initialed.
 * If the insert option is replace a new TemplateEmbedAfterService will be initialed.
 * 
 * @author Markus Schnaedelbach
 */
public class TemplateEmbedServiceFactory {
	
	static public TemplateEmbedService createEmbedder(DiagramDocumentEditor editor, BflowTemplate template, Point location) {
		
		switch (template.getAction()) {
		case before:
			return new TemplateEmbedBeforeService(editor, template, location);
		case insert:
			return new TemplateEmbedInsertService(editor, template, location);
		case after:
			return new TemplateEmbedAfterService(editor, template, location);
		default:
			break;
		}
		return null;
	}

}
