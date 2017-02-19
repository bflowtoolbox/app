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
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
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
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

public class HumanJudgmentsView extends ViewPart {

	/* ID of Judgments view */
	public static final String ID = "edu.toronto.cs.openome.evaluation.views.HumanJudgmentsView";

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

		final HumanJudgmentsView me = this;

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
				HumanJudgmentsView.this.fillContextMenu(manager);
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

				ModelImpl mi;
				CommandStack cs;

				ISelection selection = viewer.getSelection();
				// The item that got selected by the user
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();

				// Double check location of this
				mi = ModelInstance.getModelImpl();
				cs = ModelInstance.getCommandStack();

				if (obj instanceof TreeObject) {
					TreeObject to = (TreeObject) obj;
					Object treeObj = to.getObject();

					// The user clicked on a judgment
					if (treeObj instanceof HashMap) {

						Shell[] ar = PlatformUI.getWorkbench().getDisplay()
								.getShells();

						// Retrieve the model command stack
						cs = ModelInstance.getCommandStack();
						
						//The intention being handled 
						Object intenObj = to.getParent().getObject(); 
						Intention inten = (Intention) intenObj; // Cast						
						
						//Extract the alternative from HashMap
						HashMap<HumanJudgment, Alternative> map = (HashMap<HumanJudgment, Alternative>) treeObj;
						Object[] judgmentArray = map.keySet().toArray();
						HumanJudgment judgment = (HumanJudgment) judgmentArray[0]; //The Human Judgment being handled
						
						Alternative alt = map.get(judgment); //Get associated alternative 
													
						// Create a remove command 
						Command removeCommand = new RemoveHumanJudgmentCommand(inten, judgment);
						
						// Open the appropriate dialog
						if (alt.getDirection().equals("forward")) {
							ForwardHJWindowCommand windowCommand = new ForwardHJWindowCommand(
									ar[0], cs, inten);
							cs.execute(windowCommand);
							if (windowCommand.cancelled()) {
								return;
							}

							EvaluationLabel result = windowCommand
									.getEvalResult();
							
							cs.execute(removeCommand); //Remove the HJ from model & view 
							
							//Create a new HJ to replace removed HJ 
							Command addHJ = new AddHumanJudgmentCommand(inten, result, cs);
							cs.execute(addHJ);

							//Flag the affected elements
							alt.setAffectedStatus(true);
							inten.setAffectedStatus(true);
							EList<HumanJudgment> judgments = inten.getHumanJudgments();
							judgments.get(judgments.size() - 1).setAffectedStatus(true);
							
							propagateToAltView();
							

						} else if (alt.getDirection().equals("backward")) {
							BackwardHJWindowCommand windowCommand = new BackwardHJWindowCommand(
									ar[0], cs, inten);
							cs.execute(windowCommand);
							if (windowCommand.cancelled()) {
								return;
							}
							
							EvaluationLabel result = windowCommand.getEvalResult();
							
							cs.execute(removeCommand); //Remove the HJ from model & view 
							
							//Create a new HJ to replace removed HJ 
							Command addHJ = new AddHumanJudgmentCommand(inten, result, cs);
							cs.execute(addHJ);
					
							//Flag the affected elements 
							alt.setAffectedStatus(true);
							inten.setAffectedStatus(true);
							EList<HumanJudgment> judgments = inten.getHumanJudgments();
							judgments.get(judgments.size() - 1).setAffectedStatus(true);
							
							propagateToAltView();

						}
						
						//Update and refresh the view 
						clearView(); 
						loadIntentions(); 
						
					}

				}

			}
		};

	}
	
	/**
	 * Propagates any changes made in <code>HumanJudgmentsView</code> to the model to the <code>AlternativesView</code>
	 */
	private void propagateToAltView() {
		
		// Retrieve the current AlternativesView
		AlternativesView av = null;
		try {
			// Get the Alternates View
			av = (AlternativesView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage().findView(
							AlternativesView.ID);
		}catch (Exception e) {
			// Shouldn't happen...
			System.out
					.println("Failed to propagate to AlternativesView");
		}

		// Update and refresh the view
		av.clearView();
		av.loadAlternatives();

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
			// Get a list of all the Intentions and Alternatives currently in
			// the model
			EList<Intention> ints = mi.getAllIntentions();
			EList<Alternative> alts = mi.getAlternatives();

			// Add each intention decided by human judgment to the view 
			for (Intention i : ints) {
				if (!i.getHumanJudgments().isEmpty()) {
					addIntention(i, alts);
					
				}
			}
		}

		refreshView();
	}
	
	

	/**
	 * Add the Intention to the View, and all its human judgments.
	 */
	public void addIntention(Intention i, EList<Alternative> alts) {

		// Get the content provider
		ViewContentProvider contentProvider = (ViewContentProvider) viewer
				.getContentProvider();

		// Add a node in the viewer tree structure
		TreeNode node = contentProvider.addNode(i, null);

		contentProvider.addJudgment(node, i, alts, null);
		refreshView();
	}
}
