package oepc.diagram.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
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
import org.eclipse.swt.widgets.Table;
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
public class OepcAssetsViewPart extends ViewPart {

	public static final String VIEW_ID = "org.bflow.toolbox.oepc.diagram.views.assets"; //$NON-NLS-1$

	private Table attributeTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnDel;
	private Button btnDelAll;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		final Composite parent = new Composite(sc, SWT.BORDER);
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		Composite mainPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 9;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 35;

		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);

		gridData = new GridData();
		gridData.widthHint = 120;

		btnAdd = new Button(mainPane, SWT.NONE);
		btnAdd.setImage(new Image(mainPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Add-16.png")));
		btnAdd.setToolTipText("Datei-Assoziation hinzufügen");

		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnAdd);
			}
		});

		btnDel = new Button(mainPane, SWT.NONE);
		btnDel.setImage(new Image(mainPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDel.setToolTipText("Datei-Assoziation entfernen");

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnDel);
			}
		});

		btnDelAll = new Button(mainPane, SWT.NONE);
		btnDelAll.setImage(new Image(mainPane.getDisplay(), this.getClass().getResourceAsStream("/icons/Remove-16.png")));
		btnDelAll.setToolTipText("Alle Datei-Assoziationen entfernen");
		btnDelAll.setText("Alle");

		btnDelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showSuccessOnButtonClick(btnDelAll);
			}
		});

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		viewer.setComparator(new AssociationViewerComparator());

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText("Diagrammelement");
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new AssociationCellLabelProvider());
		viewColAttr.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText("Assoziierte Datei");
		viewColVal.getColumn().setWidth(180);
		viewColVal.setLabelProvider(new AssociationCellLabelProvider());
		viewColVal.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub		
	}
	
	/**
	 * Returns the unique id of the model element that is wrapped by 
	 * the given {@code editPart}. If {@code editPart} is NULL, 
	 * NULL is returned.
	 */
	private String getElementId(IGraphicalEditPart editPart) {
		if (editPart == null) return null;
		EObject eobj = editPart.resolveSemanticElement();
		return EcoreUtil.getID(eobj);
	}
	
	private static void showSuccessOnButtonClick(Button button) {
		MessageDialog.openInformation(button.getShell(), "Button works!", button.getToolTipText() + " button works!");
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
	
	private class AssociationCellLabelProvider extends CellLabelProvider {

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
