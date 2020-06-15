package org.bflow.toolbox.extensions;

import org.eclipse.osgi.util.NLS;

public class NLSupport extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.extensions.messages"; //$NON-NLS-1$
	public static String AbstractCreateDiagramLinkAction_SelectionDialogText;
	public static String AbstractCreateDiagramLinkAction_SelectionDialogTitle;
	public static String AbstractInsertDiagramLinkAction_DialogTitle;
	public static String AbstractSelectAllShapesWithTextAction_Text;
	public static String AbstractSelectAllShapesWithTextAction_Tooltip;
	public static String BflowMainPreferencesPage_ButtonIntelligentDeleterText;
	public static String BflowMainPreferencesPage_LabelInfoText;
	public static String BflowMainPreferencesPage_LabelIntelligentDeleterText;
	public static String BflowMainPreferencesPage_LabelLanguageText;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NLSupport.class);
	}

	private NLSupport() {
	}
}
