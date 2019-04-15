package oepc.diagram.views;

import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

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
public class OepcAssetsViewPart extends ViewPart implements PropertyChangeListener {
	public static final String VIEW_ID = "org.bflow.toolbox.oepc.diagram.views.assets"; //$NON-NLS-1$
	
	private static final String ELEMENT_LABEL_PREFIX = "Ausgewähltes Diagrammelement: ";
	private static final String ELEMENT_LABEL_NO_SELECTION = "<Kein Element selektiert>";
	
	private static Log log;

	private boolean sortByElement = true;
	private boolean sortAsc = true;
	private boolean showAll = true;
	
	private WorkbenchModel workbenchModel;
	private OepcAssestsViewModel viewModel;
	
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
		
		this.workbenchModel = new WorkbenchModel();
		this.viewModel = new OepcAssestsViewModel(workbenchModel);
		
		IGraphicalEditPart element = WorkbenchModel.getSelectedElement(site.getPage().getSelection());
		viewModel.setSelectedElementId(GraphicalEditPartUtil.getElementId(element));

		IEditorPart activeEditor = site.getPage().getActiveEditor();
		if(WorkbenchModel.isEditorOepcDiagramEditor(activeEditor)) viewModel.setDiagramEditor((DiagramEditor) activeEditor);
		
		IFile diagram = WorkbenchModel.getOpenedDiagramForWorkbenchPart(activeEditor);
		File folder = WorkbenchModel.aquireFolderForDiagram(diagram);
		viewModel.setFolder(folder);
		
		File associationsFile = AssociationPersistence.aquireAssociationsFile(folder);
		viewModel.setAssociationsFile(associationsFile);
		viewModel.setAssociations(AssociationPersistence.readAssociationsFromFile(associationsFile));
		
		site.getPage().addSelectionListener(workbenchModel);
	}

	@Override
	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(workbenchModel);
		viewModel.removePropertyChangeListener(this);
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
					Associations associations = viewModel.getAssociations();
					Association newAssociation = createAssociationForFile(chosenFile);
					
					associations.add(newAssociation);
					AssociationPersistence.writeAssociationsToFile(associations, viewModel.getAssociationsFile());
					
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
				
				Associations associations = viewModel.getAssociations();
				for (Object o : structuredSelection.toArray()) {
					if (!(o instanceof Association)) continue;
					Association association = (Association) o;
										
					boolean success = association.type != Type.FILE || deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
				}

				AssociationPersistence.writeAssociationsToFile(associations, viewModel.getAssociationsFile());
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
				Associations associations = viewModel.getAssociations();
				
				for (TableItem item : items) {
					if (!(item.getData() instanceof Association)) continue;
					Association association = (Association) item.getData();
					
					boolean success = association.type != Type.FILE || deleteFileAndCatchException(new File(association.associatedURL));
					if (!success) continue;
					
					associations.remove(association);
					viewer.remove(association);
				}

				AssociationPersistence.writeAssociationsToFile(associations, viewModel.getAssociationsFile());
			}
		});
		
		selectedElementName = new Label(controlPane, SWT.NONE);
		updateSelectedDiagramElementName(GraphicalEditPartUtil.getViewPart(viewModel.getDiagramEditor(), viewModel.getSelectedElementId()));

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

				AssociationPersistence.writeAssociationsToFile(viewModel.getAssociations(), viewModel.getAssociationsFile());
			}
			
			private void associateFiles(TransferData data) {
				Object o = fileTransfer.nativeToJava(data);
				String[] paths = (String[]) o;
				
				File[] files = Arrays.stream(paths).map(path -> new File(path)).toArray(File[]::new);
				Arrays.stream(files).forEach(file -> {
					try {
						Associations associations = viewModel.getAssociations();
						Association newAssociation = createAssociationForFile(file);
						
						associations.add(newAssociation);
						
						setViewerElements(associations, showAll);
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}
				});
			}
			
			private void associateURL(TransferData data) {
				Object o = urlTransfer.nativeToJava(data);
				String url = (String) o;

				Associations associations = viewModel.getAssociations();
				Association association = new Association(viewModel.getSelectedElementId(), url, Association.Type.URL);
				
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
		
		setUpControls(viewModel.isEnabled());
		setViewerElements(viewModel.getAssociations(), showAll);
		viewModel.addPropertyChangeListener(this);
	}
	
	@Override
	public void setFocus() {
		viewer.getControl().forceFocus();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propName = evt.getPropertyName();
		boolean isEnabled = viewModel.isEnabled();
		
		if ("isEnabled".equals(propName)) {
			isEnabled = (Boolean) evt.getNewValue();
			setUpControls(isEnabled);
		} else if ("selectedElementId".equals(propName) && isEnabled) {
			DiagramEditor editor = viewModel.getDiagramEditor();
			String id = (String) evt.getNewValue();
			
			IGraphicalEditPart element = GraphicalEditPartUtil.getViewPart(editor, id);
			updateSelectedDiagramElementName(element);
			
			if (!showAll) updateViewer();
		} else if ("associations".equals(propName) && isEnabled) {
			updateViewer();
		}
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
				viewModel.setCopy(isChecked());
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
					if (viewModel.isEnabled()) updateViewer();
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
		if (selectedElementName == null) return;
		
		String name = GraphicalEditPartUtil.getElementName(selectedElement);
		String text = ELEMENT_LABEL_PREFIX + (!name.equals("") ? name : ELEMENT_LABEL_NO_SELECTION);
		selectedElementName.setText(text.replaceAll("[\r]\n", " "));
		selectedElementName.requestLayout();
	}
	
	private void updateViewer() {
		associationTable.setRedraw(false);
		setViewerElements(viewModel.getAssociations(), showAll);
		associationTable.setRedraw(true);
	}
	
	/**
	 * Sets up all controls within this ViewPart.
	 * @param isEnabled
	 */
	private void setUpControls(boolean isEnabled) {
		associationTable.setEnabled(isEnabled);
		btnAdd.setEnabled(isEnabled);
		btnDel.setEnabled(isEnabled);
		btnDelAll.setEnabled(isEnabled);
		
		if (!isEnabled) viewer.setItemCount(0);
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
			String elementId = viewModel.getSelectedElementId();
			Association[] filteredAssociations = associations.getAssociationsForElementId(elementId);
			viewer.add(filteredAssociations);
		}
		
		for (TableColumn col : associationTable.getColumns())
			col.pack();
	}

	/**
	 * Creates an association for a given file. Based on the {@code copyFiles} instance variable,
	 * the association is created based on the original file or on a copy. If the file is to be
	 * copied, this method also performs its copying.
	 * @param file 			The file that is to be associated
	 * @throws IOException  If the copy of the file could for some reason not be created
	 */
	private Association createAssociationForFile(File file) throws IOException {
		String elementId = viewModel.getSelectedElementId();
		File associatedFile = viewModel.isCopy() ? copyFileToFolder(viewModel.getFolder(), file) : file;
		Type type = viewModel.isCopy() ? Type.FILE : Type.SYMLINK;
		
		return new Association(elementId, associatedFile, type);
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
				DiagramEditor diagramEditor = viewModel.getDiagramEditor();
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
			DiagramEditor diagramEditor = viewModel.getDiagramEditor();
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
