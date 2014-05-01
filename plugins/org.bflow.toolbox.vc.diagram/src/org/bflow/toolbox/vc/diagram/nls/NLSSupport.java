package org.bflow.toolbox.vc.diagram.nls;

import org.eclipse.osgi.util.NLS;

public class NLSSupport extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.vc.diagram.nls.messages"; //$NON-NLS-1$
	public static String VcDiagramEditor_ErrorOnOpening;
	public static String VcDiagramEditor_FileDoesNotExistAnymore;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NLSSupport.class);
	}

	private NLSSupport() {
	}
}
