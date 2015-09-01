package org.bflow.toolbox.epc.templating.dialogs;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.extensions.actions.DiagramLiveValidator;
import org.bflow.toolbox.epc.extensions.utils.EpcDiagramEditUtil;
import org.bflow.toolbox.epc.templating.dialogs.BflowTemplate.NamingVariable;
import org.bflow.toolbox.hive.interchange.commons.CommonInterchangeUtil;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Defines a TemplateImportService. Represents the controller for the
 * TemplateWizardDialog. Provides needed methods for the btpl-Import
 * 
 * @author Markus Schnaedelbach, Christian Soward
 */
public class TemplateFileService {

	public static final String TEMPLATE_PATH_NAME = ".templates/"; //$NON-NLS-1$
	private List<BflowTemplate> localTemplates;
	private List<BflowTemplate> globalTemplates;
	private EpcDiagramEditor editor;
	private BflowTemplate currentBflowTemplate; // the current template as epc
	private IStructuredSelection selection;
	private Point location;
	
	public TemplateFileService(DiagramDocumentEditor editor,IStructuredSelection selection, TemplateAction templateAction) {
		this.editor = (EpcDiagramEditor) editor;
		Point loc = this.editor.getMouseLocation();
		location = new Point(loc.x, loc.y);
		this.selection = selection;
		
		initTemplateHashMaps(templateAction, selection);
	}

	protected void setCurrentBflowTemplate(BflowTemplate template) {
		this.currentBflowTemplate = template;
	}


	
	/**
	 * Get a sorted ArrayList with available local and/or global Templates
	 * @param local
	 * @param global
	 * @return
	 */
	protected ArrayList<BflowTemplate> getTemplates(boolean local, boolean global) {
		ArrayList<BflowTemplate> templates = new ArrayList<BflowTemplate>();

		if (local) {
			templates.addAll(localTemplates);
		}
		if (global) {
			templates.addAll(globalTemplates);
		}
		Collections.sort(templates);
		return templates;
	}

	/**
	 * Inits the Template-ArrayList.
	 * If no templates exists, the List is empty initiates.
	 * @param selection 
	 * @param templateAction 
	 */
	private void initTemplateHashMaps(TemplateAction action, IStructuredSelection selection) {
		
		this.localTemplates = new ArrayList<BflowTemplate>();
		// find local Templates
		IEditorInput input = editor.getEditorInput();
		IFile currentLocation = ((IFileEditorInput) input).getFile();
		IPath workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		
		IPath localTemplatesPath = getLocalTemplatePath(currentLocation, workspaceRoot);
		if (localTemplatesPath != null) {
			this.localTemplates.addAll(getAvailableTemplates(localTemplatesPath ,action, true,selection));
		}
		
		//init globalsHM
		this.globalTemplates = new ArrayList<BflowTemplate>();
		IPath globalTemplatesPath = workspaceRoot.append(TEMPLATE_PATH_NAME);
		this.globalTemplates.addAll(getAvailableTemplates(globalTemplatesPath, action, false,selection));
	}
	
	static public IPath getLocalTemplatePath(IResource currentLocation, IPath workspaceRoot){
		IPath localTemplatesPath = currentLocation.getLocation();
		localTemplatesPath = localTemplatesPath.removeLastSegments(1);
		while (!localTemplatesPath.equals(workspaceRoot)) {
			localTemplatesPath = localTemplatesPath.append(TEMPLATE_PATH_NAME);
			if (localTemplatesPath.toFile().exists()) {
				return localTemplatesPath;
			}
			localTemplatesPath = localTemplatesPath.removeLastSegments(2);
		}
		return null;
	}
	
	/**
	 * Returns a ArrayList with available BflowTemplates of the given Path
	 * @param templatesPath
	 * @param action 
	 * @param selection 
	 * @return
	 */
	private ArrayList<BflowTemplate> getAvailableTemplates(IPath templatesPath, TemplateAction action, boolean local, IStructuredSelection selection) {
				
		ArrayList<BflowTemplate> templates = new ArrayList<BflowTemplate>();
		File[] epcfiles = getTemplatesAsFileArray(templatesPath);
		BflowTemplateFactory templateFactory = new BflowTemplateFactory();
		if (epcfiles != null) {
			for (File epc : epcfiles) {
				String filename = epc.getName();
				filename = filename.substring(0, filename.length()-4);				
				templates.add(templateFactory.createNewTemplate(action, local, epc, filename, selection));
			}
		}
		return templates;
	}

	/**
	 * Returns an array of the containing .epc files in the given IPath
	 * 
	 * @return The File[] array or an empty array or null
	 */
	private File[] getTemplatesAsFileArray(IPath path) {
		Path templatesPath = (Path) path;
		File templatesDir = templatesPath.toFile();

		File files[];
		if (templatesDir.exists()) {
			files = templatesDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File arg0) {

					if (arg0.isDirectory())
						return false;

					String filename = arg0.getName();

					if (filename.endsWith(".epc")) //$NON-NLS-1$
						return true;
					return false;
				}
			});
		} else {
			files = new File[0];
		}
		return files;
	}

	/**
	 * Returns the raw image preview data of the currently selected template.
	 * 
	 * @return The raw image data.
	 * @throws CoreException 
	 */
	protected byte[] getImageByteArrayFromCurrentTemplate() throws CoreException {
		
		DiagramEditPart offscreenEditPart = null;
		Shell myShell = new Shell();
		File tempFile = currentBflowTemplate.getTmpProjectRessourceIPath().toFile();
		//Manipulate DummyElements
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();	
			
			Document document = builder.parse(tempFile);
			NodeList styles = document.getElementsByTagName("styles"); //$NON-NLS-1$
						
			for (int i = 0; i < styles.getLength(); i++) {
				Node node = styles.item(i);
				NamedNodeMap parentattributes = node.getParentNode().getAttributes();
				if (currentBflowTemplate.isDummyId(parentattributes.getNamedItem("element").getTextContent())) { //$NON-NLS-1$
					NamedNodeMap attributes = node.getAttributes();
					Node type = attributes.getNamedItem("xmi:type"); //$NON-NLS-1$
					if (type.getNodeValue().equals("notation:ShapeStyle")) { //$NON-NLS-1$
						 Node fillcolor = attributes.getNamedItem("fillColor"); //$NON-NLS-1$
						 fillcolor.setNodeValue("16777215");//weiße Farbe //$NON-NLS-1$
					}
				}
			}
			
			NodeList elements = document.getElementsByTagName("elements"); //$NON-NLS-1$
			for (int j = 0; j < elements.getLength(); j++) {
				Node node = elements.item(j);
				NamedNodeMap attributes = node.getAttributes();
				Node id = attributes.getNamedItem("xmi:id"); //$NON-NLS-1$
				if (currentBflowTemplate.isConnectNode(id.getNodeValue())) {
					Node name = attributes.getNamedItem("name"); //$NON-NLS-1$
					
					if (name == null) {
						Attr att = document.createAttribute("name"); //$NON-NLS-1$
						att.setValue(Messages.TemplateFileService_12);	
						attributes.setNamedItem(att);
					}else {
						name.setNodeValue(Messages.TemplateFileService_12 + name.getNodeValue());
					}
				}
			}
			
			//Zurück schreiben
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(tempFile);
			transformer.transform(source, result);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IFile templateProjectIFile = CommonInterchangeUtil.toIFile(tempFile);
		offscreenEditPart = CommonInterchangeUtil.getOffscreenDiagramEditPart(myShell, templateProjectIFile );
		DiagramEditPart diagramEditPart = (offscreenEditPart == null ? CommonInterchangeUtil.getOnscreenDiagramEditPart(templateProjectIFile) : offscreenEditPart);
		CopyToImageUtil imageUtil = new CopyToImageUtil();
		currentBflowTemplate.removeTmpProjectRessource();
		return imageUtil.copyToImageByteArray(diagramEditPart, null, -1, -1, ImageFileFormat.PNG, new NullProgressMonitor(), false);
	}

	public Collection<NamingVariable> getCurrentNamingVariables() {
		return currentBflowTemplate.getNamingVariables();
	}

	protected void updateNamingVarEntry(String id, String value) {
		currentBflowTemplate.updateNamingVarEntry(id,value);
	}

	protected String getCurrentDescription() {
		return currentBflowTemplate.getDescription();
	}

	protected boolean isValidSelection() {
		return currentBflowTemplate.isValidForAction();
	}
	
	/**
	 * Starts the embedding. TemplateEmbedService must initiates.
	 * @return 
	 * @throws InterchangeProcessingException
	 */
	protected boolean performEmbedding() throws InterchangeProcessingException {
		
		boolean withConnect = true;
		
		if (!isValidSelection()) {
			
			boolean result = MessageDialog.openQuestion(editor.getSite().getShell(), Messages.TemplateFileService_0, Messages.TemplateFileService_2);
			if (result) {
				withConnect = false;
			}else {
				return false;
			}
		}
		TemplateEmbedService embedder = TemplateEmbedServiceFactory.createEmbedder(editor, currentBflowTemplate, location);
		DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
			
		// Deactivate validation and set Zoom to 100%
		DiagramLiveValidator diagramLiveValidator = EpcDiagramEditorPlugin.getInstance().getDiagramLiveValidator();
		boolean validationStatusBefore = diagramLiveValidator.isEnabled();
		diagramLiveValidator.setEnabled(false);
		DiagramRootEditPart root = (DiagramRootEditPart) diagramEditPart.getRoot();
		ZoomManager zoomManager = root.getZoomManager();
		double currentZoomLevel = zoomManager.getZoom();
		root.getZoomManager().setZoom(1.0);
		
		//id for identify all commands of that insertion
		final String id = UUID.randomUUID().toString();
		
		// Erstelle Shapes des Templates
		embedder.insertShapesInDiagram(diagramEditPart, id);

		// Erstelle Kanten
		embedder.insertEdgesToDiagram(diagramEditPart, id);

		if (withConnect) {
			// Verbinde beide Diagramme
			embedder.connectDiagrams(diagramEditPart, selection, id);
		}
		
		StructuredSelection newSelection = new StructuredSelection(embedder.getInsertedEditParts(diagramEditPart));
		editor.getSite().getSelectionProvider().setSelection(newSelection);
		
		
		// Activate validation and reset the old Zoom level
		zoomManager.setZoom(currentZoomLevel);
		if (validationStatusBefore) {
			diagramLiveValidator.setEnabled(true);
		}
		
//		manipulate undo history
		DefaultOperationHistory history=(DefaultOperationHistory) OperationHistoryFactory.getOperationHistory();
				
		AbstractOperation aoend = EpcDiagramEditUtil.getCollectedUndoRedoCommand(id, Messages.TemplateFileService_13);
		aoend.addContext(IOperationHistory.GLOBAL_UNDO_CONTEXT);
		history.add(aoend);
		return true;
	}



	public boolean allNaminvariablesHaveValues() {
		return currentBflowTemplate.allNamingVariablesHaveValue();
	}
}
