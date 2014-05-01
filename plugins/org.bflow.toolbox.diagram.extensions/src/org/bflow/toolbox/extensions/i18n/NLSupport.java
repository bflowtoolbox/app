package org.bflow.toolbox.extensions.i18n;

import org.eclipse.osgi.util.NLS;

public class NLSupport extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.extensions.i18n.messages"; //$NON-NLS-1$
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
