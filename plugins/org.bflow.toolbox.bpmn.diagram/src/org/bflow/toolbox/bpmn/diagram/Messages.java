package org.bflow.toolbox.bpmn.diagram;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.bpmn.diagram.messages"; //$NON-NLS-1$
	public static String BpmnCreationWizard_ShortHint;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
