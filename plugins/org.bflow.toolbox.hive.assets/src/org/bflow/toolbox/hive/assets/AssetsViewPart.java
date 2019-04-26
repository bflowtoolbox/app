package org.bflow.toolbox.hive.assets;

import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to show/modify assets of a GMF diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class AssetsViewPart extends ViewPart {
	/** Extension view id */
	public static final String VIEW_ID = "org.bflow.toolbox.hive.assets.view"; //$NON-NLS-1$
	
	private final AssetLinkCollection _assetLinkCollection = new AssetLinkCollection();
	private final WorkbenchPartListener _workbenchPartListener = new WorkbenchPartListener(_assetLinkCollection);
	private final WorkbenchSelectionListener _workbenchSelectionListener = new WorkbenchSelectionListener(_assetLinkCollection);
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		
		site.getPage().addPartListener(_workbenchPartListener);
		site.getPage().addSelectionListener(_workbenchSelectionListener);
		
		_assetLinkCollection.onActiveEditorPartChanged(site.getPage().getActiveEditor());		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		_assetLinkCollection.dispose();
		_workbenchPartListener.dispose();
		
		getSite().getPage().removePartListener(_workbenchPartListener);
		getSite().getPage().removeSelectionListener(_workbenchSelectionListener);
		
		super.dispose();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite container) {		
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		Composite parent = new Composite(sc, SWT.BORDER);
		parent.setLayout(new GridLayout(1, false));
		
		createButtonPanel(parent);
		createTableViewer(parent);
		
		sc.setContent(parent);
		sc.setMinSize(parent.getSize());
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
			
		ICompatibleEditorListener editorListener = isCompatible -> {
			Control[] children = parent.getChildren();
			for (int i = -1; ++i != children.length;) {
				Control child = children[i];
				child.setEnabled(isCompatible);
			}
		};
		
		_workbenchPartListener.addCompatibleEditorListener(editorListener);
		
		// Disable view initially
		editorListener.onEditorChanged(false);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// Nothing to do here
	}
	
	/** Creates the button panel as child of the given {@code parent}. */
	private void createButtonPanel(Composite parent) {
		Composite controlPane = new Composite(parent, SWT.BORDER);
		controlPane.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
	}
	
	/** Creates the table viewer as child of the given {@code parent}. */
	private void createTableViewer(Composite parent) {
		TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		viewer.setComparator(new ViewerComparator());
		viewer.addDoubleClickListener(new TableViewerDoubleClickHandler());
		viewer.addDropSupport(DND.DROP_COPY | DND.DROP_LINK, 
				new Transfer[] {URLTransfer.getInstance(), FileTransfer.getInstance()}, 
				new TableViewerDropTargetListener(FileTransfer.getInstance(), URLTransfer.getInstance(), _assetLinkCollection)
				);
				
		TableViewerColumn linkColumn = new TableViewerColumn(viewer, SWT.NONE);
		linkColumn.getColumn().setText("Assets");
		linkColumn.getColumn().setWidth(320);
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		viewer.getTable().addKeyListener(new TableViewerKeyListener(() -> {
			Object selectedObj = viewer.getStructuredSelection().getFirstElement();
			AssetLink assetLink = Cast.as(AssetLink.class, selectedObj);
			_assetLinkCollection.removeLink(assetLink);
		}));
		
		viewer.setLabelProvider(new TableViewerLabelProvider());
		viewer.setContentProvider(new TableViewerContentProvider());
		viewer.setInput(_assetLinkCollection);
		
		// Always update the viewer the asset link collection changes
		_assetLinkCollection.addCollectionChangedListener(() -> viewer.refresh());
	}
}
