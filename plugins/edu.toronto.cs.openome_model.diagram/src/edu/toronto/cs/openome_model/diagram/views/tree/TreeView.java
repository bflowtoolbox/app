package edu.toronto.cs.openome_model.diagram.views.tree;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.*;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class TreeView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "edu.toronto.cs.openome_model.diagram.views.tabular.TabularView";

	/**
	 * Needs to be static to support the singleton model.
	 */
	private static TreeViewer viewer;
	
	/* Action variables */
	private Action refreshAction;
	private Action expandAll;
	private Action collapseAll;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);
	
		viewer.setContentProvider(new ViewContentProvider(this));
		viewer.setLabelProvider(new LabelProvider());
		viewer.setSorter(new TreeViewSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "edu.toronto.cs.openome_model.diagram.viewer");
		makeActions();
		hookContextMenu();
//		hookDoubleClickAction();
		contributeToActionBars();
		
		TreeViewerColumn c = new TreeViewerColumn(viewer, SWT.NONE);
		c.getColumn().setWidth(108);
		c.getColumn().setText("Name");
		c.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((Node) element).getName();
			}
			@Override
			public Image getImage(Object element) {
				return ((Node) element).getImage();
			}
		});
		
		c = new TreeViewerColumn(viewer, SWT.NONE);
		c.getColumn().setWidth(50);
		c.getColumn().setText("Link");
		c.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((Node) element).getLink();
			}
			@Override
			public Font getFont(Object element) {
				Display display = getSite().getShell().getDisplay();
				FontData fd[] = display.getSystemFont().getFontData();
				fd[0].setStyle(SWT.BOLD);
				return new Font(null, fd);
				// TODO Auto-generated method stub
			}
			
		});
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Node node = (Node) ((TreeSelection) event.getSelection()).getFirstElement();
				if (node != null) {
					node.select();
				}
			}
		});
		
		c = new TreeViewerColumn(viewer, SWT.NONE);
		c.getColumn().setWidth(20);
		c.getColumn().setText("#");
		c.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((Node) element).getUsages();
			}
		});
		
		/* ISelectionListener will notify the view about every time the user changes/selects a model tab */
		ISelectionListener selectionChangeListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
//	        	clearView();
	        	//refreshView();
	        }
	    };
	    
		final TreeView me = this;
	    
	    IPerspectiveListener perspectiveListener = new IPerspectiveListener() {
	    	@Override
			public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
				clearView();
	        	refreshView();
			}

			@Override
			public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
				// This fixes the problem where Alternatives/Human Judgments
				// were shown even after all the editor tabs were closed.
				
				if(changeId.equals("editorClose")) {
					// check that no editor tabs are open
					if(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().getActiveEditor() == null) {
						// replace the contents with new empty ones
						//viewer.setContentProvider(new ViewContentProvider(me));
					}
				}
			}
	    };
	    
	    // add listeners
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionChangeListener);
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(perspectiveListener);

	    clearView();
    	refreshView();
	}
	
	/**
	 * Clears all content from the Alternatives view
	 */
	private void clearView() {
		
		/* Get the viewer's content provider */ 
		ViewContentProvider contentProvider = (ViewContentProvider) viewer.getContentProvider();
		
		/* Remove all nodes from the content provider */
		//contentProvider.removeAllNodes();
		
	}
	
	/**
	 * Refresh the view
	 * @author aftabs
	 */
	public void refreshView() {
		((ViewContentProvider) viewer.getContentProvider()).refresh();
		viewer.refresh();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				TreeView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(expandAll);
		manager.add(collapseAll);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(expandAll);
		manager.add(collapseAll);
//		manager.add(new Separator());
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
		manager.add(expandAll);
		manager.add(collapseAll);
	}

	private void makeActions() {
		refreshAction = new Action() {
			public void run() {
				refreshView();
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh the view");
		refreshAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		
		expandAll = new Action() {
			public void run() {
				viewer.expandAll();
			}
		};
		expandAll.setText("Expand All");
		expandAll.setToolTipText("Un-collapse all the nodes in the tree");
		expandAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		collapseAll = new Action() {
			public void run() {
				viewer.collapseAll();
			}
		};
		collapseAll.setText("Collapse All");
		collapseAll.setToolTipText("Collapse all the nodes in the tree");
		collapseAll.setImageDescriptor(PlatformUI.getWorkbench().
				getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		
	}

//	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
//			}
//		});
//	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Tabular View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}