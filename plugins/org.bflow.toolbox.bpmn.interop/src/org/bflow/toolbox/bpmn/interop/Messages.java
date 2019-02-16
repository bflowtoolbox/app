package org.bflow.toolbox.bpmn.interop;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.bflow.toolbox.bpmn.interop.messages"; //$NON-NLS-1$
	public static String AbstractConvertDiagramAction_DialogTitle;
	public static String AbstractConvertDiagramAction_OpenModelDialog_Text;
	public static String BpmnInteropContributionItemProvider_MenuName;
	public static String AbstractConvertDiagramAction_Error_ModelConversion;
	public static String AbstractConvertDiagramAction_Error_RefreshWorkspace;
	public static String AbstractConvertDiagramAction_ExistingModelDialog_Text;
	public static String ConvertToBpmnDiagramAction_Text;
	public static String ConvertToBpmnDiagramAction_ToolTipText;
	public static String ConvertToEpcDiagramAction_Text;
	public static String ConvertToEpcDiagramAction_ToolTipText;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
