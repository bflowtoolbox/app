package edu.toronto.cs.openome.evaluation.views;

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
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import edu.toronto.cs.openome.evaluation.commands.AddHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.BackwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.DeleteAlternativeCommand;
import edu.toronto.cs.openome.evaluation.commands.ForwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.RemoveHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.ModelImpl;


/**
 * This class creates a view model on the fly,
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

public class AlternativesView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "edu.toronto.cs.openome.evaluation.views.AlternativesView";

	// need this to be a static
	// to support the singleton model
	public static TreeViewer viewer;

	private DrillDownAdapter drillDownAdapter;
	
	/* Action variables */
	private Action refreshAction;
	private Action deleteAction;
	private Action evaluateAction;
	private Action clickAction;
	private Action collapseAllAction;
	private Action expandAllAction;
	private Action clearHighlightsAction; 

	/**
	 * This is a call back that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		// Set up columns in the view 
		TreeColumn column = new TreeColumn(viewer.getTree(), SWT.NONE);
		column.setWidth(1000);
		column.setText("Alternatives Column");
		
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider(this));
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new ViewerSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "edu.toronto.cs.openome.evaluation.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		/* ISelectionListener will notify the view about every time the user changes/selects a model tab */
		ISelectionListener selectionChangeListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
	        	clearView();
	        	loadAlternatives();
	        }
	    };
	    
	    final AlternativesView me = this;
	    
	    IPerspectiveListener perspectiveListener = new IPerspectiveListener() {
	    	@Override
			public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
				clearView();
	        	loadAlternatives();
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
						viewer.setContentProvider(new ViewContentProvider(me));
					}
				}
			}
	    };
	    
	    // add listeners
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionChangeListener);
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(perspectiveListener);

	    clearView();
    	loadAlternatives();
    	refreshView();
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	/**
	 * Initialize the action buttons
	 * @param manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(collapseAllAction);
		manager.add(expandAllAction);
	}

	/**
	 *  Initialize the right-click drown down menu 
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				
				/* Evaluate and Delete actions only if the selected item is an Alternative */
				if (obj != null) {
					if (obj instanceof TreeNode){
						if (((TreeNode) obj).isAlternative()){
							manager.add(evaluateAction);
							manager.add(deleteAction);
							}
						}
					
				}
				
				/* Add to the Alternatives View */
				AlternativesView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(clearHighlightsAction); 
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
		 *  Expand All Action - expands all nodes in the view
		 */
		expandAllAction = new Action() {
			public void run() {
				expandAll();
				
			}
		};
		expandAllAction.setText("Expand All");
		expandAllAction.setToolTipText("Expand All");
		expandAllAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL_DISABLED));
		
		
		/**
		 *  Collapse All Action - collapses all nodes in the view
		 */
		collapseAllAction = new Action() {
			public void run() {
				collapseAll();
				
			}
		};
		collapseAllAction.setText("Collapse All");
		collapseAllAction.setToolTipText("Collapse All");
		collapseAllAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		
		/**
		 *  Refresh Action - refreshes the view
		 */
		refreshAction = new Action() {
			public void run() {								
				clearView();
				loadAlternatives();
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh");
		refreshAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		
		/**
		 *  Re-evaluate Action - re-evaluates the model with the selected Alternative
		 */
		
		evaluateAction = new Action() {
			public void run() {
				clearView();
				loadAlternatives();
			}
		};
		evaluateAction.setText("Evaluate");
		evaluateAction.setToolTipText("Evaluate");
		evaluateAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		/**
		 *  Delete Action - deletes the selected Alternative
		 */
		deleteAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				deleteAlternative(obj);
				propagateToHJView();
			}
		};
		deleteAction.setText("Delete");
		deleteAction.setToolTipText("Delete");
		deleteAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));
		
		/**
		 * Clear Highlights Action - clears all highlights done to Alternatives View 
		 */
		clearHighlightsAction = new Action(){
			public void run(){
				clearAllNodeStatus(); 
				clearView();
				loadAlternatives();
			}
		}; 
		clearHighlightsAction.setText("Clear Highlights");
		clearHighlightsAction.setToolTipText("Clear Highlights");
		clearHighlightsAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		/**
		 *  Double-Click Action - sets the labels from the selected Alternative on the model
		 */
		
		clickAction = new Action() {
			public void run() {
				ModelImpl mi;
				CommandStack cs;
				
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();

				// Remap all the alternatives if user double clicks on an
				// alternative
				if (obj instanceof TreeNode) {
					if (((TreeNode) obj).getObject() instanceof Alternative){
						Alternative altToSetLabels = (Alternative) ((TreeNode) obj).getObject();
						
						/* Get all the intention to label map  from the Alternative */
						HashMap<Intention, EvaluationLabel> intentionLabels = altToSetLabels.getIntentionLabels();
						Command setLabels;
						mi = ModelInstance.getModelImpl();
						cs = ModelInstance.getCommandStack();
						
						/* Set each label to the intention in the model */
						for (Intention i : intentionLabels.keySet() ) {
							setLabels = new SetQualitativeEvaluationLabelCommand(i, intentionLabels.get(i));
							if (cs != null)
								cs.execute(setLabels);
						}
					}
					
					// expand the node
					//viewer.expandToLevel(obj, 1);
				}
				
				/*
				 * Human Judgment code
				 */
			
				// check if user clicked on a human judgment
				// if so, change the label of the intention to this judgment
				// and propagate the change
				if (obj instanceof TreeObject){
					TreeObject to = (TreeObject) obj;
					if (to.getObject() instanceof HumanJudgment){
						HumanJudgment judgement = (HumanJudgment) to.getObject();
						// get the parent, which is the Intention this label is associated with
						// and change the label
						if (to.getParent().getObject() instanceof Intention){
							Intention inten = (Intention) to.getParent().getObject();
							// TODO: How do you change the label???
							// TODO: How do you propagate the change??????
//							inten.setQualitativeReasoningCombinedLabel(label);
							// DEBUG
							// get the alternative
							TreeObject altNode = to.getParent().getParent();
							if(altNode != null){
								//Mike - commented WIP code
								if (altNode.getObject() instanceof Alternative){
									Alternative alt = (Alternative)altNode.getObject();
									Shell [] ar = PlatformUI.getWorkbench().getDisplay().getShells();

									//Remove the old judgment
									cs = ModelInstance.getCommandStack();
									
									/* Create a Remove command */
									Command removeCommand = new RemoveHumanJudgmentCommand(inten, judgement);
									

									//Open the appropriate dialog
									if (alt.getDirection().equals("forward")) {
										ForwardHJWindowCommand windowCommand = new ForwardHJWindowCommand(ar[0], cs, inten);
										cs.execute(windowCommand);
										if (windowCommand.cancelled()) {
											return;
										}

										//Remove the HJ from both the model and the AlternativesView tree.
										cs.execute(removeCommand);
										//to.getParent().removeChild(to);

										EvaluationLabel result = windowCommand.getEvalResult();	

										Command addHJ = new AddHumanJudgmentCommand(inten, result, cs);
										cs.execute(addHJ);
										
										//Flag the Alternative as altered 
										alt.setAffectedStatus(true);

									} else if (alt.getDirection().equals("backward")) {
										BackwardHJWindowCommand windowCommand = new BackwardHJWindowCommand(ar[0], cs, inten);
										cs.execute(windowCommand);
										if (windowCommand.cancelled()) {
											return;
										}

										//Remove the HJ from both the model and the AlternativesView tree.
										cs.execute(removeCommand);
										//to.getParent().removeChild(to);

										EvaluationLabel result = windowCommand.getEvalResult();	
										//System.out.println("Window result: " + result.getName());

										Command addHJ = new AddHumanJudgmentCommand(inten, result, cs);
										cs.execute(addHJ);
										
										//Flag the Alternative as altered 
										alt.setAffectedStatus(true);
									}
									
									//Update and refresh the view
									clearView();
									loadAlternatives();
									
									//Propagate changes to HJ view 
									propagateToHJView(); 
									
									// update the intention with the new judgment

									/*showMessage("Humanjudgement: " + judgement 
											+ "\nIntention: " + inten.getName()
											+ "\nAlternative: " + to.getParent().getParent().getName());*/
								}
							}
							return;
						}
					}
				}



			}
		};
	}
	
	/**
	 * Propagates any changes made in <code>AlternativesView</code> to the model to the <code>HumanJudgmentsView</code>
	 */
	private void propagateToHJView() {
		
		// Retrieve the current AlternativesView
		HumanJudgmentsView hjv = null;
		try {
			// Get the Alternates View
			hjv = (HumanJudgmentsView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage().findView(
							HumanJudgmentsView.ID);
		}catch (Exception e) {
			// Shouldn't happen...
			System.out
					.println("Failed to propagate to AlternativesView");
		}

		// Update and refresh the view
		hjv.clearView();
		hjv.loadIntentions();

	}

	/**
	 *  Deletes an Alternative from the model 
	 */
	protected void deleteAlternative(Object obj) {
		if (obj instanceof TreeNode) {
			ModelImpl mi = ModelInstance.getModelImpl();
			CommandStack cs = ModelInstance.getCommandStack();
			if (cs != null && mi != null) {

				/* Get the Alternative from the node object passed in */
				Alternative altToDelete = (Alternative) ((TreeNode) obj)
						.getAlternative();

				/* Show the confirmation dialog box */
				boolean confirm = showConfirm("Do you want to delete Alternate: "
						+ altToDelete.getName());

				if (confirm) {
					
					/* Create a new delete command */
					Command deleteCommand = new DeleteAlternativeCommand(
							altToDelete, mi);

					cs.execute(deleteCommand);
					
					/* Update and refresh view */
					clearView();
					loadAlternatives();
				}
			}
		}

	}

	/**
	 * Clears all content from the Alternatives view
	 */
	public void clearView() {
		
		/* Get the viewer's content provider */ 
		ViewContentProvider contentProvider = (ViewContentProvider) viewer
		.getContentProvider();
		
		/* Remove all nodes from the content provider */
		contentProvider.removeAllNodes();
		
	}
	
	/**
	 * Resets the status of all affected elements in the model
	 * RECALL: The status of an Alternative is true if it has been affected 
	 * by some change in the human judgments view and false otherwise. 
	 */
	private void clearAllNodeStatus(){
		
		//Get the active model 
		ModelImpl mi = ModelInstance.getModelImpl();
		
		if (mi!=null) {
			// Get a list of all the Alternatives currently in the model
			EList<Alternative> alts = mi.getAlternatives();
			EList<Intention> intens = mi.getAllIntentions();
			
			// Reset the status of each Alternative 
			for (Alternative alt : alts) {
				alt.setAffectedStatus(false);
			}
			
			// Reset the status of each Intention
			for (Intention i : intens){
				i.setAffectedStatus(false); 
				EList<HumanJudgment> judgments = i.getHumanJudgments();
				//Reset the status of each Human Judgment 
				if (!judgments.isEmpty()){
					for (HumanJudgment hj : judgments){
						hj.setAffectedStatus(false);
					}
				}
			}
		}
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
	 * Shows a message in a dialog box with an OK button 
	 * @param message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Alternatives",
			message);
	}
	
	/**
	 * Shows a message in a confirmation dialog box
	 * @param message
	 * @return
	 */
	private boolean showConfirm (String message) {
		return MessageDialog.openConfirm(
				viewer.getControl().getShell(),
				"Delete Alternative?",
				message);
		
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	/**
	 * Add and show the specified Alternative in the view.
	 * @author aftabs
	 * @param alt
	 */
	public void addAlternative(Alternative alt) {
		
		//trying to avoid null pointer exception, not sure why alternative is null
		if (alt == null)
			return; 
		
		// Get the content provider
		ViewContentProvider contentProvider = (ViewContentProvider) viewer
				.getContentProvider();
		
 		TreeNode node = contentProvider.addNode(alt);
		node.setAlternateStatus(true);
		
		// Add all the intentions and human judgments if there are any
		// in the parent alternative object
		//contentProvider.addChildren(alt.getIntentions(), alt.getSoftgoalWrappers(), node);
		contentProvider.addChildren(alt.getIntentionLabels(), node);
		
		refreshView();
	}
	
	/**
	 * Refresh the view
	 * @author aftabs
	 */
	public void refreshView() {
		viewer.refresh();
		}
	
	/**
	 * Loads Alternatives from the model into the view
	 */
	public void loadAlternatives() {
		
		/* Get the active model */
		ModelImpl mi = ModelInstance.getModelImpl();
		
		if (mi!=null) {
			/* Get a list of all the Alternatives currently in the model */
			EList<Alternative> alts = mi.getAlternatives();
			
			System.out.println(alts.toString());
			
			/* Add each Alternative to the view */
			for (Alternative alt : alts) {
				if (alt.getDirection() == null) {
					System.out.println("alt direction is null");
					alt.setDirection("unknown");
				}
				
				System.out.println(alt.toString());
				
				//it was throwing a null pointer exception for some null alternatives.  Not sure why some alternatives are null.
				if (alt != null)
					addAlternative(alt);
			}
		}
		refreshView();
		
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
}