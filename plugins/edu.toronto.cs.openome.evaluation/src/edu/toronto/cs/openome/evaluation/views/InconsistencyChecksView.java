package edu.toronto.cs.openome.evaluation.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import edu.toronto.cs.openome.evaluation.commands.AddHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.BackwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.ForwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.RemoveHumanJudgmentCommand;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

public class InconsistencyChecksView extends ViewPart {

	/* ID of Judgments view */
	public static final String ID = "edu.toronto.cs.openome.evaluation.views.InconsistencyChecksView";

	/* Singleton TreeViewer */
	public static TreeViewer viewer;

	private DrillDownAdapter drillDownAdapter;

	/* Action variables */
	private Action clickAction;
	private Action refreshAction;
	private Action collapseAllAction;
	private Action expandAllAction;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);

		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider(this));
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new ViewerSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
				"edu.toronto.cs.openome.evaluation.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		// General Label provider
		final ViewLabelProvider v = new ViewLabelProvider();

		// Label provider for "Inconsistencies" column
		TreeViewerColumn c = new TreeViewerColumn(viewer, SWT.NONE);
		c.getColumn().setWidth(350);
		c.getColumn().setText("Inconsistencies");
		c.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				// Label
				return ((TreeNode) element).getName();
			}

			@Override
			public Image getImage(Object element) {
				return v.getImage(element);
			}
		});

		// Label provider for "Inconsistent with" column
		c = new TreeViewerColumn(viewer, SWT.NONE);
		c.getColumn().setWidth(350);
		c.getColumn().setText("Inconsistent with");
		c.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (((TreeNode) element).getInconsistentWith().equals(
						TreeNode.MODEL)) {
					return "Model";
				} else if (((TreeNode) element).getInconsistentWith().equals(
						TreeNode.JUDGMENT)) {
					return "Judgment History";
				}
				return ""; // Return empty string otherwise
			}
		});

		/*
		 * ISelectionListener will notify the view about every time the user
		 * changes/selects a model tab
		 */
		ISelectionListener selectionChangeListener = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart sourcepart,
					ISelection selection) {
				clearView();
				loadIntentions();
			}
		};

		final InconsistencyChecksView me = this;

		IPerspectiveListener perspectiveListener = new IPerspectiveListener() {
			@Override
			public void perspectiveActivated(IWorkbenchPage page,
					IPerspectiveDescriptor perspective) {
				clearView();
				loadIntentions();
				refreshView();
			}

			@Override
			public void perspectiveChanged(IWorkbenchPage page,
					IPerspectiveDescriptor perspective, String changeId) {
				// This fixes the problem where Alternatives/Human Judgments
				// were shown even after all the editor tabs were closed.

				if (changeId.equals("editorClose")) {
					// check that no editor tabs are open
					if (PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().getActiveEditor() == null) {
						// replace the contents with new empty ones
						viewer.setContentProvider(new ViewContentProvider(me));
					}
				}
			}
		};

		// add listeners
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().addSelectionListener(
						selectionChangeListener);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.addPerspectiveListener(perspectiveListener);

		clearView();
		loadIntentions();
		refreshView();
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * Initialize the action buttons
	 * 
	 * @param manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(collapseAllAction);
		manager.add(expandAllAction);
	}

	/**
	 * Initialize the right-click drown down menu
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				InconsistencyChecksView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Initialize the actions
	 */
	private void makeActions() {
		/**
		 * Expand All Action - expands all nodes in the view
		 */
		expandAllAction = new Action() {
			public void run() {
				expandAll();

			}
		};
		expandAllAction.setText("Expand All");
		expandAllAction.setToolTipText("Expand All");
		expandAllAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_ELCL_COLLAPSEALL_DISABLED));

		/**
		 * Collapse All Action - collapses all nodes in the view
		 */
		collapseAllAction = new Action() {
			public void run() {
				collapseAll();

			}
		};
		collapseAllAction.setText("Collapse All");
		collapseAllAction.setToolTipText("Collapse All");
		collapseAllAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_ELCL_COLLAPSEALL));

		/**
		 * Refresh Action - refreshes the view
		 */
		refreshAction = new Action() {
			public void run() {
				clearView();
				loadIntentions();
			}
		};

		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh");
		refreshAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_TOOL_UNDO));

		/**
		 * Double-Click Action - determines what should be done when item is
		 * double-clicked
		 */
		clickAction = new Action() {
			@SuppressWarnings("unchecked")
			public void run() {

				// Reload the intentions here???
			}
		};

	}

	/**
	 * Assigns the double-click action for the viewer
	 */
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				clickAction.run();
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			// @Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				clickAction.run();
			}
		});
	}

	/**
	 * Clears all content from the Alternatives view
	 */
	void clearView() {
		/* Get the viewer's content provider */
		ViewContentProvider contentProvider = (ViewContentProvider) viewer
				.getContentProvider();

		/* Remove all nodes from the content provider */
		contentProvider.removeAllNodes();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Refresh the view
	 * 
	 */
	public void refreshView() {
		viewer.refresh();
	}

	/**
	 * Collapses all nodes in the view
	 */
	private void collapseAll() {
		viewer.collapseAll();
	}

	/**
	 * Expands all nodes in the view
	 */
	private void expandAll() {
		viewer.expandAll();
	}

	/**
	 * Loads Intentions from the model into the view
	 */
	public void loadIntentions() {

		// Get the active model
		ModelImpl mi = ModelInstance.getModelImpl();

		if (mi != null) {
			// Get a list of all the Intentions
			EList<Intention> intens = mi.getAllIntentions();
			EList<Alternative> alts = mi.getAlternatives();

			for (Intention i : intens) {
				// Check for inconsistencies
				checkInconsistency(i, alts);
			}
		}

		refreshView();
	}

	/**
	 * 
	 * @param a
	 *            The <code>Intention</code> being checked for inconsistency
	 *            with model
	 */
	public void checkInconsistency(Intention i, EList<Alternative> alts) {

		TreeNode node = null;

		// The label of intention has been decided via HJ -
		// Therefore, check for inconsistency
		if (!i.getHumanJudgments().isEmpty()) {

			// Get the label bag
			LabelBag bag = i.getLabelBag();
			// Get the result
			EvaluationLabel result = i.getQualitativeReasoningCombinedLabel();

			/** DETERMINE INCONSISTENCY **/
			boolean modelInconsistent = isModelInconsistent(bag, result);
			boolean historyInconsistent = false;

			if (modelInconsistent || historyInconsistent) {
				// Get the content provider
				ViewContentProvider contentProvider = (ViewContentProvider) viewer
						.getContentProvider();

				if (modelInconsistent) {
					node = contentProvider.addNode(i, null);
					contentProvider.addJudgment(node, i, alts, TreeNode.MODEL);
				} else if (historyInconsistent) {
					node = contentProvider.addNode(i, null);
					contentProvider.addJudgment(node, i, alts, TreeNode.JUDGMENT);
				}
				

			}

		}

	}

	/**
	 * 
	 * @param bag
	 *            The combination of labels or <code>LabelBag</code>
	 * @param l
	 *            The result
	 * @return True if the result is inconsistent with model, False otherwise
	 */
	public boolean isModelInconsistent(LabelBag bag, EvaluationLabel l) {

		if (!bag.isHasUnknown() && l.equals(EvaluationLabel.UNKNOWN)) {
			//System.out
			//		.println("Conflict: " + bag.toString() + " | " + l.name());
			return true;
		} else if (bag.isAllPositive()
				&& (l.equals(EvaluationLabel.PARTIALLY_DENIED) || l
						.equals(EvaluationLabel.DENIED))) {
			//System.out
			//		.println("Conflict: " + bag.toString() + " | " + l.name());
			return true;
		} else if (bag.isAllNegative()
				&& (l.equals(EvaluationLabel.PARTIALLY_SATISFIED) || l
						.equals(EvaluationLabel.SATISFIED))) {
			//System.out
			//		.println("Conflict: " + bag.toString() + " | " + l.name());
			return true;
		} else if ((bag.isAllNegative() || bag.isAllPositive())
				&& l.equals(EvaluationLabel.CONFLICT)) {
			//System.out
			//		.println("Conflict: " + bag.toString() + " | " + l.name());
			return true;
		} else {
			return false;
		}
	}

	public boolean isJudgHistInconsistent() {
		return false;
	}

}
