package oepc.diagram.nls;

import org.eclipse.osgi.util.NLS;

public class NLSSupport extends NLS {
	private static final String BUNDLE_NAME = "oepc.diagram.nls.messages"; //$NON-NLS-1$
	public static String OepcDiagramEditor_ErrorOnOpening;
	public static String OepcDiagramEditor_FileDoesNotExistAnymore;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NLSSupport.class);
	}

	private NLSSupport() {
	}
}
