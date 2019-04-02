package oepc.diagram.views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.ViewPart;

import oepc.diagram.part.OepcDiagramEditor;

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
	
	private static final String ELEMENT_LABEL_PREFIX = "Ausgewähltes Diagrammelement: ";
	private static final String ELEMENT_LABEL_NO_SELECTION = "<Kein Element selektiert>";
	
	private boolean isEnabled = true;
	
	private IEditorPart diagramEditor;
	private IGraphicalEditPart selectedDiagramElement;
	
	private Map<IFile, File> directoryMap;
	private Map<String, List<Association>> associationMap;

	private Label selectedDiagramElementName;
	private Table attributeTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnDel;
	private Button btnDelAll;
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		
		isEnabled = isEditorOepcDiagramEditor(site.getPage().getActiveEditor());
		
		diagramEditor = site.getPage().getActiveEditor();
		directoryMap = new HashMap<>();
		associationMap = new HashMap<>();
		
		site.getPage().addSelectionListener(this);
		
		aquireFolderForDiagram();
		updateSelectedElement(site.getPage().getSelection());
	}
	

	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		Composite parent = new Composite(sc, SWT.BORDER);
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
			public void widgetSelected(SelectionEvent event) {
				File diagramFolder = aquireFolderForDiagram();
				File chosenFile = getFileFromFileDialog();
				
				if (chosenFile == null) return;
				
				try {
					File associatedFile = copyFileToFolder(diagramFolder, chosenFile);
					Association association = new Association(selectedDiagramElement, associatedFile);
					
					addToAssociationMap(association);
					viewer.add(association);
				} catch (IOException e) {
					MessageDialog.openError(null, "Fehler beim assozieren der Datei", e.getMessage());
				}
			}
		});

		btnDel = new Button(controlPane, SWT.NONE);
		btnDel.setImage(new Image(controlPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDel.setToolTipText("Datei-Assoziation entfernen");

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISelection selection = viewer.getSelection();
				if (selection.isEmpty() || !(selection instanceof StructuredSelection)) return;
				StructuredSelection structuredSelection = (StructuredSelection) selection;
				
				for (Object o : structuredSelection.toArray()) {
					if (!(o instanceof Association)) continue;
					Association association = (Association) o;
					
					boolean success = deleteFileAndCatchException(association.associatedFile);
					if (!success) continue;
					
					removeFromAssociationMap(association);
					viewer.remove(association);
				}
			}
		});

		btnDelAll = new Button(controlPane, SWT.NONE);
		btnDelAll.setImage(new Image(controlPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDelAll.setToolTipText("Alle Datei-Assoziationen entfernen");
		btnDelAll.setText("Alle");

		btnDelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = viewer.getTable().getItems();
				
				for (TableItem item : items) {
					if (!(item.getData() instanceof Association)) continue;
					Association association = (Association) item.getData();
					
					boolean success = deleteFileAndCatchException(association.associatedFile);
					if (!success) continue;
					
					removeFromAssociationMap(association);
					viewer.remove(association);
				}
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
				if (e.getSelection().isEmpty() || !(e.getSelection() instanceof StructuredSelection)) return;
				
				StructuredSelection selection = (StructuredSelection) e.getSelection();
				Association association = (Association) selection.getFirstElement();
				
				try {
					Desktop.getDesktop().open(association.associatedFile);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		
		viewer.addDropSupport(DND.DROP_COPY | DND.DROP_LINK, 
				new Transfer[] { FileTransfer.getInstance(), URLTransfer.getInstance() }, 
				new DropTargetListener() {
			private final URLTransfer urlTransfer = URLTransfer.getInstance();
			private final FileTransfer fileTransfer = FileTransfer.getInstance();			
			
			@Override
			public void dropAccept(DropTargetEvent event) {
				System.out.println("Drop accepting: " + event.toString());
			}
			
			@Override
			public void drop(DropTargetEvent event) {
				if (!fileTransfer.isSupportedType(event.currentDataType)) return;
				
				Object o = fileTransfer.nativeToJava(event.currentDataType);
				String[] paths = (String[]) o;
				
				if (paths == null) return;
				
				File[] files = Arrays.stream(paths).map(path -> new File(path)).toArray(File[]::new);
				File folder = aquireFolderForDiagram();

				Arrays.stream(files).forEach(file -> {
					try {
						File newFile = copyFileToFolder(folder, file);
						Association association = new Association(selectedDiagramElement, newFile);
						addToAssociationMap(association);
						viewer.add(association);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
			
			@Override
			public void dragOver(DropTargetEvent event) {
				System.out.println("Drag over " + event.toString());
				
				event.feedback = DND.FEEDBACK_SCROLL;
				if (event.item != null) event.feedback |= DND.FEEDBACK_INSERT_AFTER;
				else 				   event.feedback |= DND.FEEDBACK_SELECT;
			}
			
			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				System.out.println("Drag operation changed " + event.toString());
			}
			
			@Override
			public void dragLeave(DropTargetEvent event) {
			}
			
			@Override
			public void dragEnter(DropTargetEvent event) {
				if (urlTransfer.isSupportedType(event.currentDataType))
					event.detail = DND.DROP_LINK;
				if (fileTransfer.isSupportedType(event.currentDataType))
					event.detail = DND.DROP_COPY;
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
		
		if (!isEnabled) disableView();
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart activeEditorPart = part.getSite().getPage().getActiveEditor();
		boolean isOepc = isEditorOepcDiagramEditor(activeEditorPart);
		
		if (!isOepc && !isEnabled)
			return;
		else if (!isOepc)
			disableView();
		else if (!isEnabled)
			enableView();

		if (!isEnabled)
			return;
		
		aquireFolderForDiagram();
		updateSelectedElement(selection);
		updateSelectedDiagramElementName(selectedDiagramElement);
	}
	
	private void updateSelectedElement(ISelection selection) {
		if (selection == null || selection.isEmpty() || !(selection instanceof StructuredSelection)) return;
		StructuredSelection structuredSelection = (StructuredSelection) selection;
		
		Object firstElement = structuredSelection.getFirstElement();
		if (!(firstElement instanceof IGraphicalEditPart)) return;
		selectedDiagramElement = (IGraphicalEditPart) firstElement;
	}
	
	private void updateSelectedDiagramElementName(IGraphicalEditPart selectedDiagramElement) {
		String name = getElementName(selectedDiagramElement);
		String text = ELEMENT_LABEL_PREFIX + (!name.equals("") ? name : ELEMENT_LABEL_NO_SELECTION);		
		selectedDiagramElementName.setText(text);
		selectedDiagramElementName.requestLayout();
	}
	
	private void enableView() {
		isEnabled = true;
		
		setUpControls(isEnabled);
	}
	
	private void disableView() {
		isEnabled = false;
		
		viewer.setItemCount(0);
		setUpControls(isEnabled);
	}
	
	private void setUpControls(boolean value) {
		attributeTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
		btnDelAll.setEnabled(value);
	}
	
	private void addToAssociationMap(Association association) {
		IGraphicalEditPart part = association.diagramElement;
		String elementId = getElementId(part);
		
		List<Association> associations = associationMap.get(elementId);
		if (associations == null) associations = new Vector<>();
		associations.add(association);
		
		associationMap.put(elementId, associations);
	}
	
	private boolean removeFromAssociationMap(Association association) {
		IGraphicalEditPart part = association.diagramElement;
		String elementId = getElementId(part);
		
		List<Association> associations = associationMap.get(elementId);
		boolean success = associations.remove(association);
		
		return success;
	}
	
	private File getFileFromFileDialog() {
		FileDialog fd = new FileDialog(this.getViewSite().getShell(), SWT.OPEN);
		fd.setText("Datei assoziieren");
		
		String path = fd.open();
		if (path == null) return null;
		
		File file = new File(path);
		return file.exists() ? file : null;
	}
	
	private File aquireFolderForDiagram() {
		IFile currentlyOpenedFile = getCurrentlyOpenedDiagram(diagramEditor);
		if (currentlyOpenedFile == null) return null;
		
		if (directoryMap.containsKey(currentlyOpenedFile))
			return directoryMap.get(currentlyOpenedFile);
		
		IPath path = currentlyOpenedFile.getRawLocation();
		
		String folderName = "." + path.removeFileExtension().lastSegment();
		String pathString = path.removeLastSegments(1).append(folderName).toOSString();
		
		File folder = getOrCreateFolder(pathString);
		directoryMap.put(currentlyOpenedFile, folder);
		
		return folder;
	}
	
	private static boolean isEditorOepcDiagramEditor(IEditorPart editorPart) {
		if (editorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorPart;
			editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		return editorPart instanceof OepcDiagramEditor; 
	}
	
	private static boolean deleteFileAndCatchException(File file) {
		boolean success = false;
		
		try {
			success = file.delete();
		} catch (SecurityException e) {
			String message = e.getLocalizedMessage();
			MessageDialog.openError(null, "Datei kann nicht gelöscht werden", message);
		}
		
		return success;
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
	
	private static IFile getCurrentlyOpenedDiagram(IWorkbenchPart part) {
		if (!(part instanceof DiagramDocumentEditor)) return null;
		IEditorInput input = ((DiagramDocumentEditor) part).getEditorInput();
			
		if (!(input instanceof IFileEditorInput)) return null;
		IFile file = ((IFileEditorInput) input).getFile();

		return file;
	}
	
	private static File getOrCreateFolder(String path) {
		File folder = new File(path);
		if (!folder.exists() && !folder.isDirectory()) folder.mkdirs();
		
		return folder;
	}
	
	private static File copyFileToFolder(File folder, File file) throws IOException {
		if (!folder.isDirectory()) throw new IllegalArgumentException("The first argument has to be a folder!");
		if (!file.isFile()) throw new IllegalArgumentException("The second argument has to be a file!");
		
		String fileName = file.getName();
		File targetFile = new File(folder, fileName);

		Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		return targetFile;
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
			
			if (attributeTable.getItemCount() > selectionIndex)
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
