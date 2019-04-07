package oepc.diagram.views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

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
	
	private static final String ELEMENT_LABEL_PREFIX = "Ausgew�hltes Diagrammelement: ";
	private static final String ELEMENT_LABEL_NO_SELECTION = "<Kein Element selektiert>";
	
	private boolean isEnabled = true;
	private boolean sortByElement = true;
	private boolean sortAsc = true;
	
	private IWorkbenchPage page;
	private DiagramEditor diagramEditor;
	private IGraphicalEditPart selectedDiagramElement;
	
	private File currentFolder;
	private File currentAssociationsFile;
	private Associations associations;
	
	private Label selectedDiagramElementName;

	private Table associationTable;
	private TableViewer viewer;
	
	private Button btnAdd;
	private Button btnAddModify;
	private Button btnDel;
	private Button btnDelAll;
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		
		page = site.getPage();
		IEditorPart activeEditor = page.getActiveEditor();
		diagramEditor = activeEditor instanceof DiagramEditor ? (DiagramEditor) activeEditor : null;
		isEnabled = isEditorOepcDiagramEditor(diagramEditor);
		
		page.addSelectionListener(this);
		
		currentFolder = aquireFolderForDiagram(diagramEditor);
		currentAssociationsFile = aquireAssociationsFileForFolder(currentFolder);
		associations = parseAssociationsFromFile(currentAssociationsFile);
		selectedDiagramElement = aquireSelectedElement(page.getSelection());
	}

	@Override
	public void dispose() {
		super.dispose();
		page.removeSelectionListener(this);
	}
	
	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		Composite parent = new Composite(sc, SWT.BORDER);
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		Composite controlPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;

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
		btnAdd.setToolTipText("Datei-Assoziation hinzuf�gen");

		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				File diagramFolder = aquireFolderForDiagram(diagramEditor);
				if (diagramFolder == null) return;
				
				File chosenFile = getFileFromFileDialog();
				if (chosenFile == null) return;
				
				try {
					File associatedFile = copyFileToFolder(diagramFolder, chosenFile);
					Association association = new Association(GraphicalEditPartUtil.getElementId(selectedDiagramElement), associatedFile);
					associations.add(association);
					persistAssociations();
					
					setViewerElements(associations.toArray());
				} catch (IOException e) {
					MessageDialog.openError(null, "Fehler beim assozieren der Datei", e.getMessage());
				}
			}
		});
		
		btnAddModify = new Button(controlPane, SWT.ARROW | SWT.DOWN | SWT.FLAT);
		btnAddModify.setToolTipText("Assoziations-Verhalten festlegen");
		
		btnAddModify.addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> {
			Menu menu = new Menu(this.getViewSite().getShell(), SWT.POP_UP);

            MenuItem item1 = new MenuItem(menu, SWT.PUSH | SWT.CHECK);
            item1.setText("Kopie");
            item1.setSelection(true);
            MenuItem item2 = new MenuItem(menu, SWT.PUSH | SWT.CHECK);
            item2.setText("Verkn�pfung");

            Point pos = btnAddModify.getLocation();
            Rectangle rect = btnAddModify.getBounds();

            Point menuPos = new Point(pos.x - 1, pos.y + rect.height);

            menu.setLocation(controlPane.getDisplay().map(btnAddModify.getParent(), null, menuPos));
            menu.setVisible(true);
		}));

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
					
					boolean success = deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
					
					persistAssociations();
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
					
					boolean success = deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
					
					persistAssociations();
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
					switch (association.type) {
					case URL: Desktop.getDesktop().browse(new URI(association.associatedURL)); break;
					case SYMLINK:
					case FILE: Desktop.getDesktop().open((new File(association.associatedURL)));
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (URISyntaxException urie) {
					urie.printStackTrace();
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
				
				persistAssociations();
			}
			
			private void associateFiles(TransferData data) {
				Object o = fileTransfer.nativeToJava(data);
				String[] paths = (String[]) o;
				
				if (paths == null) return;
				
				File[] files = Arrays.stream(paths).map(path -> new File(path)).toArray(File[]::new);
				File folder = aquireFolderForDiagram(diagramEditor);

				Arrays.stream(files).forEach(file -> {
					try {
						File newFile = copyFileToFolder(folder, file);
						Association association = new Association(GraphicalEditPartUtil.getElementId(selectedDiagramElement), newFile);
						associations.add(association);
						
						setViewerElements(associations.toArray());
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
			
			private void associateURL(TransferData data) {
				Object o = urlTransfer.nativeToJava(data);
				String url = (String) o;
				
				String elementId = GraphicalEditPartUtil.getElementId(selectedDiagramElement);
				Association association = new Association(elementId, url, Association.Type.URL);
				associations.add(association);
				
				setViewerElements(associations.toArray());
			}
			
			@Override
			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SCROLL;
				if (event.item != null)  event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
				else event.feedback |= DND.FEEDBACK_SELECT;
			}
			
			@Override
			public void dragEnter(DropTargetEvent event) {
				if (urlTransfer.isSupportedType(event.currentDataType))
					event.detail = DND.DROP_LINK;
				else if (fileTransfer.isSupportedType(event.currentDataType))
					event.detail = DND.DROP_COPY;
			}
		});

		TableViewerColumn viewColElement = new TableViewerColumn(viewer, SWT.NONE);
		viewColElement.getColumn().setText("Diagrammelement");
		viewColElement.getColumn().setWidth(120);
		viewColElement.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_ONE));
		viewColElement.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean columnChanged = !sortByElement;
				
				sortAsc = columnChanged ? true : !sortAsc;
				sortByElement = true;
				
				updateViewer();
			}
		});

		TableViewerColumn viewColUrl = new TableViewerColumn(viewer, SWT.NONE);
		viewColUrl.getColumn().setText("Assoziierte Datei");
		viewColUrl.getColumn().setWidth(180);
		viewColUrl.setLabelProvider(new AssociationLabelProvider(AssociationLabelProvider.COLUMN_TWO));
		viewColUrl.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean columnChanged = sortByElement;
				
				sortAsc = columnChanged ? true : !sortAsc;
				sortByElement = false;
				
				updateViewer();
			}
		});

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
		if (associations != null) setViewerElements(associations.toArray());
	}
	
	@Override
	public void setFocus() {
		viewer.getControl().forceFocus();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart activeEditorPart = part.getSite().getPage().getActiveEditor();
		boolean isOepc = isEditorOepcDiagramEditor(activeEditorPart);
		
		if (!isOepc && !isEnabled)
			return;
		else if (!isOepc) {
			disableView();
			return;
		} else if (!isEnabled) {
			enableView();
			diagramEditor = (DiagramEditor) activeEditorPart;
		}
		
		diagramEditor = (DiagramEditor) activeEditorPart;
		currentFolder = aquireFolderForDiagram(diagramEditor);
		currentAssociationsFile = aquireAssociationsFileForFolder(currentFolder);
		associations = parseAssociationsFromFile(currentAssociationsFile);
		selectedDiagramElement = aquireSelectedElement(selection);
		updateSelectedDiagramElementName(selectedDiagramElement);
		
		if (associations != null)
			setViewerElements(associations.toArray());
	}
	
	
	private void updateSelectedDiagramElementName(IGraphicalEditPart selectedDiagramElement) {
		String name = GraphicalEditPartUtil.getElementName(selectedDiagramElement);
		String text = ELEMENT_LABEL_PREFIX + (!name.equals("") ? name : ELEMENT_LABEL_NO_SELECTION);
		selectedDiagramElementName.setText(text.replaceAll("[\r]\n", " "));
		selectedDiagramElementName.requestLayout();
	}
	
	private void updateViewer() {
		associationTable.setRedraw(false);
		viewer.setItemCount(0);
		setViewerElements(associations.toArray());
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
	
	private void setViewerElements(Association[] associations) {
		viewer.setItemCount(0);
		viewer.add(associations);

		for (TableColumn col : associationTable.getColumns())
			col.pack();
	}

	private void persistAssociations() {
		TomlWriter tomlWriter = new TomlWriter();
		try {
			tomlWriter.write(associations, currentAssociationsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getFileFromFileDialog() {
		FileDialog fd = new FileDialog(this.getViewSite().getShell(), SWT.OPEN);
		fd.setText("Datei assoziieren");
		
		String path = fd.open();
		if (path == null) return null;
		
		File file = new File(path);
		return file.exists() ? file : null;
	}	
	
	
	private static boolean isEditorOepcDiagramEditor(IEditorPart editorPart) {
		if (editorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorPart;
			editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		return editorPart instanceof OepcDiagramEditor; 
	}
	
	private static boolean deleteFileAndCatchException(File file) {
		boolean success = !file.exists();
		if (success) return success;
		
		try {
			success = file.delete();
		} catch (SecurityException e) {
			String message = e.getLocalizedMessage();
			MessageDialog.openError(null, "Datei kann nicht gel�scht werden", message);
		}
		
		return success;
	}
	
	private static File aquireFolderForDiagram(DiagramEditor editor) {
		IFile currentlyOpenedFile = getCurrentlyOpenedDiagram(editor);
		if (currentlyOpenedFile == null) return null;
		
		IPath path = currentlyOpenedFile.getRawLocation();
		
		String folderName = "." + path.removeFileExtension().lastSegment();
		String pathString = path.removeLastSegments(1).append(folderName).toOSString();
		
		return getOrCreateFolder(pathString);
	}
	
	private static File aquireAssociationsFileForFolder(File folder) {
		if (folder == null) return null;
		
		File associationFile = new File(folder, ".associations");
		
		try {
			if (!associationFile.exists()) associationFile.createNewFile();
			return associationFile;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static Associations parseAssociationsFromFile(File file) {
		if (file == null) return null;
		return new Toml().read(file).to(Associations.class);
	}
	
	private static IGraphicalEditPart aquireSelectedElement(ISelection selection) {
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
	
	private static File getOrCreateFolder(String path) {
		File folder = new File(path);
		if (!folder.exists()) folder.mkdirs();
		
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
