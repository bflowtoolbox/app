package oepc.diagram.views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.ViewPart;

import oepc.diagram.part.OepcDiagramEditor;
import oepc.diagram.views.Association.Type;

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
	
	private static Log log;

	private boolean isEnabled = true;
	private boolean sortByElement = true;
	private boolean sortAsc = true;
	private boolean copyFiles = true;
	private boolean showAll = true;
	
	private IWorkbenchPage page;
	private DiagramEditor diagramEditor;
	private IGraphicalEditPart selectedDiagramElement;
	
	private File currentFolder;
	private File currentAssociationsFile;
	private Associations associations;
	
	private Action actionSetAssociationCopy;
	private Action actionSetViewExtended;
	private Action actionSetShowAllAssociations;
	
	private Label selectedElementName;

	private Table associationTable;
	private TableViewer viewer;
	private TableViewerColumn elementColumn;
	private TableViewerColumn urlColumn;
	
	private Button btnAdd;
	private Button btnDel;
	private Button btnDelAll;
	
	static {
		log = LogFactory.getLog(OepcAssetsViewPart.class);
	}
	
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		
		page = site.getPage();
		page.addSelectionListener(this);
		
		IEditorPart activeEditor = page.getActiveEditor();
		isEnabled = isEditorOepcDiagramEditor(activeEditor);

		if (isEnabled) updateState((DiagramEditor) activeEditor);
	}

	@Override
	public void dispose() {
		super.dispose();
		page.removeSelectionListener(this);
	}
	
	@Override
	public void createPartControl(Composite container) {
		createViewActions();
		
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
				File chosenFile = getFileFromFileDialog();
				if (chosenFile == null) return;
				
				try {
					createForFileAndAddToAssociations(chosenFile);
					AssociationPersistence.writeAssociationsToFile(associations, currentAssociationsFile);
					
					setViewerElements(associations, showAll);
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
										
					boolean success = association.type != Type.FILE || deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
				}

				AssociationPersistence.writeAssociationsToFile(associations, currentAssociationsFile);
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
					
					boolean success = association.type != Type.FILE || deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
				}

				AssociationPersistence.writeAssociationsToFile(associations, currentAssociationsFile);
			}
		});
		
		selectedElementName = new Label(controlPane, SWT.NONE);
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
					switch (association.type) {
					case URL: Desktop.getDesktop().browse(new URI(association.associatedURL)); break;
					case SYMLINK:
					case FILE: Desktop.getDesktop().open((new File(association.associatedURL)));
					}
				} catch (IOException ioe) {
					log.error(ioe.getMessage(), ioe);
				} catch (URISyntaxException urie) {
					log.error(urie.getMessage(), urie);
				}
			}
		});
		
		viewer.addDropSupport(DND.DROP_COPY | DND.DROP_LINK, 
				new Transfer[] { FileTransfer.getInstance(), URLTransfer.getInstance() }, 
				new DropTargetAdapter() {
			private final URLTransfer urlTransfer = URLTransfer.getInstance();
			private final FileTransfer fileTransfer = FileTransfer.getInstance();			
						
			@Override
			public void drop(DropTargetEvent event) {
				if (urlTransfer.isSupportedType(event.currentDataType))
					associateURL(event.currentDataType);
				else if (fileTransfer.isSupportedType(event.currentDataType)) 
					associateFiles(event.currentDataType);

				AssociationPersistence.writeAssociationsToFile(associations, currentAssociationsFile);
			}
			
			private void associateFiles(TransferData data) {
				Object o = fileTransfer.nativeToJava(data);
				String[] paths = (String[]) o;
				
				File[] files = Arrays.stream(paths).map(path -> new File(path)).toArray(File[]::new);
				Arrays.stream(files).forEach(file -> {
					try {
						createForFileAndAddToAssociations(file);
						setViewerElements(associations, showAll);
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}
				});
			}
			
			private void associateURL(TransferData data) {
				Object o = urlTransfer.nativeToJava(data);
				String url = (String) o;
				
				String elementId = GraphicalEditPartUtil.getElementId(selectedDiagramElement);
				Association association = new Association(elementId, url, Association.Type.URL);
				associations.add(association);
				
				setViewerElements(associations, showAll);
			}
			
			@Override
			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SCROLL;
				if (event.item != null)  event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
				else event.feedback |= DND.FEEDBACK_SELECT;
			}
			
			@Override
			public void dragEnter(DropTargetEvent event) {
				TransferData data = event.currentDataType;
				
				if (urlTransfer.isSupportedType(data))
					event.detail = DND.DROP_LINK;
				else if (fileTransfer.isSupportedType(data) && hasNoFolder(data))
					event.detail = DND.DROP_COPY;
			}
			
			private boolean hasNoFolder(TransferData data) {
				Object o = fileTransfer.nativeToJava(data);
				String[] paths = (String[]) o;
				
				File[] files = Arrays.stream(paths).map(path -> new File(path)).toArray(File[]::new);
				return Arrays.stream(files).allMatch(file -> !file.isDirectory());
			}
		});

		urlColumn = new TableViewerColumn(viewer, SWT.NONE);
		urlColumn.getColumn().setText("Assoziierte Datei");
		urlColumn.getColumn().setWidth(180);
		urlColumn.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_TWO));
		urlColumn.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean columnChanged = sortByElement;
				
				sortAsc = columnChanged ? true : !sortAsc;
				sortByElement = false;
				
				updateViewer();
			}
		});

		addElementColumn();
		
		associationTable = viewer.getTable();
		associationTable.setLinesVisible(true);
		associationTable.setHeaderVisible(true);
		associationTable.setLayoutData(gridData);
		associationTable.addKeyListener(new TableViewerKeyListener());
		
		parent.layout();
		parent.pack();
		
		sc.setContent(parent);
		sc.setMinSize(parent.getSize());
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		if (!isEnabled) disableView();
		setViewerElements(associations, showAll);
	}
	
	@Override
	public void setFocus() {
		viewer.getControl().forceFocus();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart activeEditor = part.getSite().getPage().getActiveEditor();
		boolean isOepc = isEditorOepcDiagramEditor(activeEditor);
		
		if (!isOepc && !isEnabled)
			return;
		else if (!isOepc) {
			disableView();
			return;
		} else if (!isEnabled)
			enableView();
		
		updateState((DiagramEditor) activeEditor);
		updateSelectedDiagramElementName(selectedDiagramElement);
		setViewerElements(associations, showAll);
	}
	
	
	private void createViewActions() {
		actionSetAssociationCopy = new Action() {
			@Override
			public ImageDescriptor getImageDescriptor() {
				InputStream is = OepcAssetsViewPart.class.getResourceAsStream("/icons/Document-Move-16.png");
				if (is == null) return null;
				
				Image img = new Image(Display.getCurrent(), is);
				return ImageDescriptor.createFromImage(img);
			}
			
			@Override
			public int getStyle() {
				return Action.AS_CHECK_BOX;
			}
			
			@Override
			public void run() {
				copyFiles = isChecked();
			}
			
			@Override
			public String getToolTipText() {
				return "Dateien kopieren anstatt zu verlinken";
			}
			
		};
		actionSetViewExtended = new Action() {
			@Override
			public ImageDescriptor getImageDescriptor() {
				InputStream is = OepcAssetsViewPart.class.getResourceAsStream("/icons/Remove-16.png");
				if (is == null) return null;
				
				Image img = new Image(Display.getCurrent(), is);
				return ImageDescriptor.createFromImage(img);
			}
			
			@Override
			public int getStyle() {
				return Action.AS_CHECK_BOX;
			}
			
			@Override
			public void run() {
				if (isChecked()) {
					addElementColumn();
					if (isEnabled) updateViewer();
				} else removeElementColumn();
			}
			
			@Override
			public String getToolTipText() {
				return "Spalte mit Diagrammelementnamen anzeigen";
			}
		};
		actionSetShowAllAssociations = new Action() {
			public ImageDescriptor getImageDescriptor() {
				InputStream is = OepcAssetsViewPart.class.getResourceAsStream("/icons/Remove-16.png");
				if (is == null) return null;
				
				Image img = new Image(Display.getCurrent(), is);
				return ImageDescriptor.createFromImage(img);
			}
			
			@Override
			public int getStyle() {
				return Action.AS_CHECK_BOX;
			}
			
			@Override
			public void run() {
				showAll = isChecked();
				updateViewer();
			}
			
			@Override
			public String getToolTipText() {
				return "Alle Assoziationen anzeigen";
			}
		
		};
		
		actionSetAssociationCopy.setChecked(true);
		actionSetViewExtended.setChecked(true);
		actionSetShowAllAssociations.setChecked(true);
		
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		
		toolBar.add(actionSetAssociationCopy);
		toolBar.add(actionSetViewExtended);
		toolBar.add(actionSetShowAllAssociations);
	}

	private void addElementColumn() {
		elementColumn = new TableViewerColumn(viewer, SWT.NONE);
		elementColumn.getColumn().setText("Diagrammelement");
		elementColumn.getColumn().setWidth(120);
		elementColumn.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_ONE));
		elementColumn.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean columnChanged = !sortByElement;
				
				sortAsc = columnChanged ? true : !sortAsc;
				sortByElement = true;
				
				updateViewer();
			}
		});
	}
	
	private void removeElementColumn() {
		elementColumn.getColumn().dispose();
	}
	
	/**
	 * Takes a {@code IGraphicalEditPart} as argument and sets the selectedElementName's
	 * value to the name of the argument. If the argument does not have a name, a default
	 * value is displayed instead.
	 * @param selectedElement
	 */
	private void updateSelectedDiagramElementName(IGraphicalEditPart selectedElement) {
		String name = GraphicalEditPartUtil.getElementName(selectedElement);
		String text = ELEMENT_LABEL_PREFIX + (!name.equals("") ? name : ELEMENT_LABEL_NO_SELECTION);
		selectedElementName.setText(text.replaceAll("[\r]\n", " "));
		selectedElementName.requestLayout();
	}
	
	/**
	 * Updates all instance variables that are relevant to represent to current state.
	 */
	private void updateState(DiagramEditor editor) {
		diagramEditor = editor;
		currentFolder = aquireFolderForDiagram(diagramEditor);
		currentAssociationsFile = AssociationPersistence.aquireAssociationsFile(currentFolder);
		associations = AssociationPersistence.readAssociationsFromFile(currentAssociationsFile);
		selectedDiagramElement = getSelectedElement(page.getSelection());
	}
	
	private void updateViewer() {
		associationTable.setRedraw(false);
		viewer.setItemCount(0);
		setViewerElements(associations, showAll);
		associationTable.setRedraw(true);
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
		associationTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
		btnDelAll.setEnabled(value);
	}
	
	/**
	 * Displays all associations if {@code showAll} is {@code true} on the table viewer
	 * and packs all columns.
	 * Otherwise only the associations for the currently selected element are shown.
	 * @param associations
	 */
	private void setViewerElements(Associations associations, boolean showAll) {
		viewer.setItemCount(0);
		if (associations == null) return;
		
		if (showAll) viewer.add(associations.toArray());
		else {
			String elementId = GraphicalEditPartUtil.getElementId(selectedDiagramElement);
			Association[] filteredAssociations = associations.getAssociationsForElementId(elementId);
			viewer.add(filteredAssociations);
		}
		
		for (TableColumn col : associationTable.getColumns())
			col.pack();
	}

	/**
	 * Creates an association for a given file and adds it to the the {@code associations}
	 * instance variable. Based on the {@code copyFiles} instance variable, the association
	 * is created based on the original file or on a copy. 
	 * @param file 			The file that is to be associated
	 * @throws IOException  If the copy of the file could for some reason not be created
	 */
	private void createForFileAndAddToAssociations(File file) throws IOException {
		String elementId = GraphicalEditPartUtil.getElementId(selectedDiagramElement);
		File associatedFile = copyFiles ? copyFileToFolder(currentFolder, file) : file;
		Type type = copyFiles ? Type.FILE : Type.SYMLINK;
		
		Association association = new Association(elementId, associatedFile, type);
		associations.add(association);
	}
	
	private File getFileFromFileDialog() {
		FileDialog fd = new FileDialog(this.getViewSite().getShell(), SWT.OPEN);
		fd.setText("Datei assoziieren");
		
		String path = fd.open();
		if (path == null) return null;
		
		File file = new File(path);
		return file.exists() ? file : null;
	}	
	
	/**
	 * Checks whether the supplied {@code IEditorPart} contains a {@code OepcDiagramEditor}
	 * as its currently active page.
	 * @param editorPart
	 * @return {@code true} if active page is an instance of {@code OepcDiagramEditor},
	 * {@code false} otherwise.
	 */
	private static boolean isEditorOepcDiagramEditor(IEditorPart editorPart) {
		if (editorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorPart;
			editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		return editorPart instanceof OepcDiagramEditor; 
	}
	
	/**
	 * Tries to delete the supplied file and returns {@code true} if successful.
	 * Note that supplying a file that doesn't exist will deemed a successful deletion.
	 * @param file
	 * @return {@code true} if successful, {@code false} otherwise
	 */
	private static boolean deleteFileAndCatchException(File file) {
		boolean success = !file.exists();
		if (success) return success;
		
		try {
			success = file.delete();
		} catch (SecurityException e) {
			String message = e.getLocalizedMessage();
			MessageDialog.openError(null, "Datei kann nicht gelöscht werden", message);
		}
		
		return success;
	}
	
	/**
	 * Gets or creates the folder in which all associations, associated and copied files
	 * will be stored to, based on the name of the currently opened diagram.
	 * @param editor
	 * @return The folder corresponding to the diagram, or {@code null} if no diagram is open.
	 */
	private static File aquireFolderForDiagram(DiagramEditor editor) {
		IFile currentlyOpenedFile = getCurrentlyOpenedDiagram(editor);
		if (currentlyOpenedFile == null) return null;
		
		IPath path = currentlyOpenedFile.getRawLocation();
		
		String folderName = "." + path.removeFileExtension().lastSegment();
		String pathString = path.removeLastSegments(1).append(folderName).toOSString();
		
		File folder = new File(pathString);
		if (!folder.exists()) folder.mkdirs();
		
		return folder;
	}
	
	private static IGraphicalEditPart getSelectedElement(ISelection selection) {
		if (selection == null || selection.isEmpty() || !(selection instanceof StructuredSelection)) return null;
		StructuredSelection structuredSelection = (StructuredSelection) selection;
		
		Object firstElement = structuredSelection.getFirstElement();
		if (!(firstElement instanceof IGraphicalEditPart)) return null;
		return (IGraphicalEditPart) firstElement;
	}
	
	private static IFile getCurrentlyOpenedDiagram(IWorkbenchPart part) {
		if (!(part instanceof DiagramDocumentEditor)) return null;
		IEditorInput input = ((DiagramDocumentEditor) part).getEditorInput();
			
		if (!(input instanceof IFileEditorInput)) return null;
		IFile file = ((IFileEditorInput) input).getFile();

		return file;
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
			Association a1 = (Association) o1;
			Association a2 = (Association) o2;
			
			if (sortByElement) {
				IGraphicalEditPart element1 = GraphicalEditPartUtil.getViewPart(diagramEditor, a1.elementId);
				IGraphicalEditPart element2 = GraphicalEditPartUtil.getViewPart(diagramEditor, a2.elementId);
				String name1 = GraphicalEditPartUtil.getElementName(element1);
				String name2 = GraphicalEditPartUtil.getElementName(element2);
				
				if (!sortAsc)
					return name1.compareToIgnoreCase(name2);
				else
					return name2.compareToIgnoreCase(name1);
			} else {
				String url1 = a1.associatedURL;
				String url2 = a2.associatedURL;
				
				if (!sortAsc)
					return url1.compareToIgnoreCase(url2);
				else
					return url2.compareToIgnoreCase(url1);
			}
		}
	}
	
	private class TableViewerKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode != SWT.DEL) return;
			
			int selectionIndex = associationTable.getSelectionIndex();
			
			btnDel.notifyListeners(SWT.Selection, null);
			
			if (associationTable.getItemCount() > selectionIndex)
				associationTable.setSelection(selectionIndex);
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
			IGraphicalEditPart element = GraphicalEditPartUtil.getViewPart(diagramEditor, association.elementId);
			
			return column == COLUMN_ONE ? 
					GraphicalEditPartUtil.getElementName(element) : 
					association.associatedURL;
		}		
		
		@Override
		public Image getImage(Object object) {
			if (column != COLUMN_TWO) return null;
			
			Association association = (Association) object;
			String extension = null;
			
			switch (association.type) {
			case URL: extension = IconProvider.getExtension(IconProvider.BROWSER_EXTENSION); break;
			case SYMLINK:
			case FILE: extension = IconProvider.getExtension(association.associatedURL);
			}
			
			return IconProvider.getIcon(extension);
		}
	}
}
