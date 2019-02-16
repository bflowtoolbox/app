package org.bflow.toolbox.bpmn.interop;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.bpmn.interop.messages"; //$NON-NLS-1$
	public static String BpmnInteropContributionItemProvider_MenuName;
	public static String ConvertToBpmnDiagramAction_Error_ModelConversion;
	public static String ConvertToBpmnDiagramAction_Error_RefreshWorkspace;
	public static String ConvertToBpmnDiagramAction_MessageDialog_Hint;
	public static String ConvertToBpmnDiagramAction_MessageDialog_Title;
	public static String ConvertToBpmnDiagramAction_Text;
	public static String ConvertToBpmnDiagramAction_ToolTipText;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
