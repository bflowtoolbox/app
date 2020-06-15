/*
 * 
 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/part/Messages.java
package vcchart.diagram.part;
=======
package vcchart.diagram;
>>>>>>> development:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/Messages.java

import org.eclipse.osgi.util.NLS;

/**
 * @generated
 */
public class Messages extends NLS {

<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/part/Messages.java
=======
	private static final String BUNDLE_NAME = "vcchart.diagram.messages"; //$NON-NLS-1$
	
>>>>>>> development:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/Messages.java
	/**
	 * @generated
	 */
	static {
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/part/Messages.java
		NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
=======
		NLS.initializeMessages(BUNDLE_NAME, Messages.class); //$NON-NLS-1$
>>>>>>> development:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/Messages.java
	}

	/**
	 * @generated
	 */
	private Messages() {
	}

	/**
	 * @generated
	 */
	public static String VcCreationWizardTitle;

	/**
	 * @generated
	 */
	public static String VcCreationWizard_DiagramModelFilePageTitle;

	/**
	 * @generated
	 */
	public static String VcCreationWizard_DiagramModelFilePageDescription;

	/**
	 * @generated
	 */
	public static String VcCreationWizardOpenEditorError;

	/**
	 * @generated
	 */
	public static String VcCreationWizardCreationError;

	/**
	 * @generated
	 */
	public static String VcCreationWizardPageExtensionError;

	/**
	 * @generated
	 */
	public static String VcDiagramEditorUtil_OpenModelResourceErrorDialogTitle;

	/**
	 * @generated
	 */
	public static String VcDiagramEditorUtil_OpenModelResourceErrorDialogMessage;

	/**
	 * @generated
	 */
	public static String VcDiagramEditorUtil_CreateDiagramProgressTask;

	/**
	 * @generated
	 */
	public static String VcDiagramEditorUtil_CreateDiagramCommandLabel;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_isModifiable;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_handleElementContentChanged;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_IncorrectInputError;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_NoDiagramInResourceError;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_DiagramLoadingError;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_UnsynchronizedFileSaveError;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_SaveDiagramTask;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_SaveNextResourceTask;

	/**
	 * @generated
	 */
	public static String VcDocumentProvider_SaveAsOperation;

	/**
	 * @generated
	 */
	public static String InitDiagramFile_ResourceErrorDialogTitle;

	/**
	 * @generated
	 */
	public static String InitDiagramFile_ResourceErrorDialogMessage;

	/**
	 * @generated
	 */
	public static String InitDiagramFile_WizardTitle;

	/**
	 * @generated
	 */
	public static String InitDiagramFile_OpenModelFileDialogTitle;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_CreationPageName;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_CreationPageTitle;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_CreationPageDescription;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageName;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageTitle;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageDescription;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageSelectionTitle;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageNoSelectionMessage;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_InitDiagramCommand;

	/**
	 * @generated
	 */
	public static String VcNewDiagramFileWizard_IncorrectRootError;

	/**
	 * @generated
	 */
	public static String VcDiagramEditor_SavingDeletedFile;

	/**
	 * @generated
	 */
	public static String VcDiagramEditor_SaveAsErrorTitle;

	/**
	 * @generated
	 */
	public static String VcDiagramEditor_SaveAsErrorMessage;

	/**
	 * @generated
	 */
	public static String VcDiagramEditor_SaveErrorTitle;

	/**
	 * @generated
	 */
	public static String VcDiagramEditor_SaveErrorMessage;

	/**
	 * @generated
	 */
	public static String VcElementChooserDialog_SelectModelElementTitle;

	/**
	 * @generated
	 */
	public static String ModelElementSelectionPageMessage;

	/**
	 * @generated
	 */
	public static String ValidateActionMessage;

	/**
	 * @generated
	 */
	public static String Objects1Group_title;

	/**
	 * @generated
	 */
	public static String Connections2Group_title;

	/**
	 * @generated
	 */
	public static String Activity11CreationTool_title;

	/**
	 * @generated
	 */
	public static String Activity11CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Activity22CreationTool_title;

	/**
	 * @generated
	 */
	public static String Activity22CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Objective3CreationTool_title;

	/**
	 * @generated
	 */
	public static String Objective3CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Product4CreationTool_title;

	/**
	 * @generated
	 */
	public static String Product4CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Application5CreationTool_title;

	/**
	 * @generated
	 */
	public static String Application5CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Cluster6CreationTool_title;

	/**
	 * @generated
	 */
	public static String Cluster6CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Document7CreationTool_title;

	/**
	 * @generated
	 */
	public static String Document7CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Participant8CreationTool_title;

	/**
	 * @generated
	 */
	public static String Participant8CreationTool_desc;

	/**
	 * @generated
	 */
	public static String TechnicalTerm9CreationTool_title;

	/**
	 * @generated
	 */
	public static String TechnicalTerm9CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Relation11CreationTool_title;

	/**
	 * @generated
	 */
	public static String Relation11CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Relation22CreationTool_title;

	/**
	 * @generated
	 */
	public static String Relation22CreationTool_desc;

	/**
	 * @generated
	 */
	public static String Relation33CreationTool_title;

	/**
	 * @generated
	 */
	public static String Relation33CreationTool_desc;

	/**
	 * @generated
	 */
	public static String CommandName_OpenDiagram;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Model_1000_links;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Product_2001_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Product_2001_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Objective_2002_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Objective_2002_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Activity1_2003_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Activity1_2003_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Activity2_2004_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Activity2_2004_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Cluster_2005_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Cluster_2005_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_TechnicalTerm_2006_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_TechnicalTerm_2006_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Participant_2007_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Participant_2007_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Application_2008_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Application_2008_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Document_2009_incominglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Document_2009_outgoinglinks;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation1_4001_target;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation1_4001_source;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation2_4002_target;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation2_4002_source;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation3_4003_target;

	/**
	 * @generated
	 */
	public static String NavigatorGroupName_Relation3_4003_source;

	/**
	 * @generated
	 */
	public static String NavigatorActionProvider_OpenDiagramActionName;

	/**
	 * @generated
	 */
	public static String MessageFormatParser_InvalidInputError;

	/**
	 * @generated
	 */
	public static String VcModelingAssistantProviderTitle;

	/**
	 * @generated
	 */
	public static String VcModelingAssistantProviderMessage;

	//TODO: put accessor fields manually	
}
