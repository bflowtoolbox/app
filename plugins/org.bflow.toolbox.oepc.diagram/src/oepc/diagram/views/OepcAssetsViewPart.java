package oepc.diagram.views;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to show/modify assets of an OEPC diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>, 
 * 		   Philippe Barias<philippe.barias@evy-solutions.de>
 * 
 * @since 2019-03-27
 *
 */
public class OepcAssetsViewPart extends ViewPart implements ISelectionListener {
	public static final String VIEW_ID = "org.bflow.toolbox.oepc.diagram.views.assets"; //$NON-NLS-1$
	
	private static final String ELEMENT_LABEL_PREFIX = "Ausgewähltes Element: ";
	private static final String ELEMENT_LABEL_NO_SELECTION = "<Kein Element selektiert>";
	
	private IEditorPart diagramEditor;
	private IGraphicalEditPart selectedDiagramElement;
	
	private Map<IFile, File> directoryMap;
	private Map<IGraphicalEditPart, List<Association>> associationMap;

	private Label selectedDiagramElementName;
	private Table attributeTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnDel;
	private Button btnDelAll;
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		
		diagramEditor = site.getPage().getActiveEditor();
		directoryMap = new HashMap<>();
		associationMap = new HashMap<>();
		
		aquireFolderForDiagram();
		
		site.getPage().addSelectionListener(this);
	}	
	
	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		final Composite parent = new Composite(sc, SWT.BORDER);
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		Composite controlPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 35;

		controlPane.setLayout(gridLayout);
		controlPane.setLayoutData(gridData);

		gridData = new GridData();
		gridData.widthHint = 120;

		btnAdd = new Button(controlPane, SWT.NONE);
		btnAdd.setImage(new Image(controlPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Add-16.png")));
		btnAdd.setToolTipText("Datei-Assoziation hinzufügen");

		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnAdd);
				
				File diagramFolder = aquireFolderForDiagram();
				File associatedFile = createFileWithRandomContent(diagramFolder);
				Association association = new Association(selectedDiagramElement, associatedFile);
				
				addToAssociationMap(association);
				viewer.add(association);
			}
		});

		btnDel = new Button(controlPane, SWT.NONE);
		btnDel.setImage(new Image(controlPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDel.setToolTipText("Datei-Assoziation entfernen");

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnDel);
			}
		});

		btnDelAll = new Button(controlPane, SWT.NONE);
		btnDelAll.setImage(new Image(controlPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDelAll.setToolTipText("Alle Datei-Assoziationen entfernen");
		btnDelAll.setText("Alle");

		btnDelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnDelAll);
			}
		});
		
		selectedDiagramElementName = new Label(controlPane, SWT.NONE);
		updateSelectedDiagramElementName(selectedDiagramElement);

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		viewer.setComparator(new AssociationViewerComparator());
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent e) {
				if(e.getSelection().isEmpty() || !(e.getSelection() instanceof StructuredSelection)) return;
				
				StructuredSelection selection = (StructuredSelection) e.getSelection();
				Association association = (Association) selection.getFirstElement();
				
				MessageDialog.openInformation(attributeTable.getShell(), "Opening File", 
						"Opening file " + association.associatedFile.getName() + " of element " + association.getElementName());
				
				try {
					Desktop.getDesktop().open(association.associatedFile);
				} catch (IOException ioe) {
					// TODO Auto-generated catch block
					ioe.printStackTrace();
				}
			}
		});

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText("Diagrammelement");
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_ONE));
		viewColAttr.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(attributeTable.getShell(), "Selection event on column ONE triggered", 
						e.toString());
			}
		});

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText("Assoziierte Datei");
		viewColVal.getColumn().setWidth(180);
		viewColVal.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_TWO));
		viewColVal.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(attributeTable.getShell(), "Selection event on column TWO triggered", 
						e.toString());
			}
		});

		attributeTable = viewer.getTable();
		attributeTable.setLinesVisible(true);
		attributeTable.setHeaderVisible(true);
		attributeTable.setLayoutData(gridData);
		attributeTable.addKeyListener(new TableViewerKeyListener());
		
		parent.layout();
		parent.pack();
		org.eclipse.swt.graphics.Point parentSize = parent.getSize();
		
		sc.setContent(parent);
		sc.setMinSize(parentSize);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {// Check if the active editor is a diagram editor. If not deactivate the view
		IEditorPart activeEditorPart = part.getSite().getPage().getActiveEditor();
		
		// Handling MultiPageEditorPart
		if (activeEditorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart)activeEditorPart;
			activeEditorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}	
		
		// Ensure that is a graphical editor
		if (!(activeEditorPart instanceof GraphicalEditor)) {
			disableView();
			return;
		} 
		
		// Handling MultiPageEditorPart
		if (part instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart)part;
			part = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		if (!(part instanceof DiagramEditor)) {
			return;
		}
		
		if(selection.isEmpty() || !(selection instanceof StructuredSelection)) return;
		StructuredSelection structuredSelection = (StructuredSelection) selection;
		
		Object firstElement = structuredSelection.getFirstElement();
		if(!(firstElement instanceof IGraphicalEditPart)) return;
		selectedDiagramElement = (IGraphicalEditPart) firstElement;
		
		updateSelectedDiagramElementName(selectedDiagramElement);
	}
	
	private void updateSelectedDiagramElementName(IGraphicalEditPart selectedDiagramElement) {
		String name = getElementName(selectedDiagramElement);
		String text = ELEMENT_LABEL_PREFIX + (!name.equals("") ? name : ELEMENT_LABEL_NO_SELECTION);		
		selectedDiagramElementName.setText(text);
		selectedDiagramElementName.requestLayout();
	}
	
	private void disableView() {
		viewer.setItemCount(0);
		setUpControls(false);
	}
	
	private void setUpControls(boolean value) {
		attributeTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
		btnDelAll.setEnabled(value);
	}
	
	private void addToAssociationMap(Association association) {
		IGraphicalEditPart part = association.diagramElement;
		
		List<Association> associations = associationMap.get(part);
		if(associations == null) associations = new Vector<>();

		associations.add(association);
		
		associationMap.put(selectedDiagramElement, associations);
	}
	
	
	private File aquireFolderForDiagram() {
		IFile currentlyOpenedFile = getCurrentlyOpenedFile(diagramEditor);
		
		if(directoryMap.containsKey(currentlyOpenedFile))
			return directoryMap.get(currentlyOpenedFile);
		
		IPath path = currentlyOpenedFile.getRawLocation();
		
		String folderName = "." + path.removeFileExtension().lastSegment();
		String pathString = path.removeLastSegments(1).append(folderName).toOSString();
		
		File folder = getOrCreateFolder(pathString);
		directoryMap.put(currentlyOpenedFile, folder);
		
		return folder;
	}
	
	
	private IFile getCurrentlyOpenedFile(IWorkbenchPart part) {
		if (!(part instanceof DiagramDocumentEditor)) return null;
		IEditorInput input = ((DiagramDocumentEditor) part).getEditorInput();
			
		if (!(input instanceof IFileEditorInput)) return null;
		IFile file = ((IFileEditorInput) input).getFile();

		return file;
	}
	
	
	private File getOrCreateFolder(String path) {
		File folder = new File(path);
		if(!folder.exists() && !folder.isDirectory()) folder.mkdirs();
		
		return folder;
	}
	
	
	private static void showSuccessOnButtonClick(Button button) {
		MessageDialog.openInformation(button.getShell(), "Button works!", button.getToolTipText() + " button works!");
	}

	
	/**
	 * Returns the unique id of the model element that is wrapped by 
	 * the given {@code editPart}. If {@code editPart} is NULL, 
	 * NULL is returned.
	 */
	private static String getElementId(IGraphicalEditPart editPart) {
		if (editPart == null) return null;
		EObject eobj = editPart.resolveSemanticElement();
		return EMFCoreUtil.getProxyID(eobj);
	}
	
	
	private static String getElementName(IGraphicalEditPart editPart) {
		if (editPart == null) return "";
		EObject eobj = editPart.resolveSemanticElement();
		return EMFCoreUtil.getName(eobj);
	}
	
	
	private static File createFileWithRandomContent(File folder) {
		Random r = new Random();
		
		byte[] nameBytes = new byte[32];
		r.nextBytes(nameBytes);
		String name = Base64.getEncoder().encodeToString(nameBytes) + ".txt";
		
		byte[] contentBytes = new byte[1024];
		r.nextBytes(contentBytes);
		String content = Base64.getEncoder().encodeToString(contentBytes);
		
		//StringBuilder sb = new StringBuilder(content)
		
		File file = new File(folder, name);
		
		try {
			file.createNewFile();
			
			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	
	private class AssociationViewerComparator extends ViewerComparator {
		@Override
		public int compare(Viewer viewer, Object o1, Object o2) {
			return 0;
		}
	}
	
	
	private class TableViewerKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode != SWT.DEL) return;
			
			int selectionIndex = attributeTable.getSelectionIndex();
			
			btnDel.notifyListeners(SWT.Selection, null);
			
			if(attributeTable.getItemCount() > selectionIndex)
				attributeTable.setSelection(selectionIndex);
		}
	}
	
	
	private class AssociationLabelProvider extends ColumnLabelProvider {
		public static final int COLUMN_ONE = 0;
		public static final int COLUMN_TWO = 1;		
		
		private int column;
		
		public AssociationLabelProvider(int column) {
			this.column = column;
		}
		
		@Override
		public String getText(Object object) {
			Association association = (Association) object;
			return column == COLUMN_ONE ? association.getElementName() : association.associatedFile.getAbsolutePath();
		}		
	}
	
	
	private class Association {
		public final IGraphicalEditPart diagramElement;
		public final File associatedFile;
				
		public Association(IGraphicalEditPart diagramElement, File associatedFile) {
			this.diagramElement = diagramElement;
			this.associatedFile = associatedFile;
		}
		
		public String getElementName() {
			return OepcAssetsViewPart.getElementName(diagramElement);
		}
	}
}
