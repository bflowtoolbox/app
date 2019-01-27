package org.bflow.toolbox.extensions.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.extensions.GenericElementChooserDialog;
import org.bflow.toolbox.extensions.NLSupport;
import org.bflow.toolbox.extensions.GenericElementChooserDialog.Context;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;

/**
 * Base class action to insert a diagram link from one model element to another
 * diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 * @param <TSelectionData> Selection data class
 */
public abstract class AbstractInsertDiagramLinkAction<TSelectionData> implements IObjectActionDelegate {
	private final Log _log;
	
	private Shell _shell;
	private IWorkbench _workbench;
	private TSelectionData _selectionData;
	
	protected AbstractInsertDiagramLinkAction() {
		_log = LogFactory.getLog(getClass());
	}
	
	/** Log instance */
	protected Log Log() { return _log; }
	
	/** Shell */
	protected Shell Shell() { return _shell; }
	
	/** Workbench */
	protected IWorkbench Workbench() { return _workbench; }
	
	/** Selection data */
	protected TSelectionData SelectionData() { return _selectionData; }
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		_shell = targetPart.getSite().getShell();
		_workbench = targetPart.getSite().getWorkbenchWindow().getWorkbench();		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		_selectionData = getSelectionData(selection);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		View view = getViewForSelection(_selectionData);
		if (view == null) return;
		
		GenericElementChooserDialog elementChooser = new GenericElementChooserDialog(_shell, view, new ChooserContext(this));
		if (elementChooser.open() != Dialog.OK) return;
		
		URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
		String path = selectedModelElementURI.toPlatformString(true);
		
		try {
			performInsert(_selectionData, path);
		} catch (Exception ex) {
			_log.error("Error on inserting diagram link", ex); //$NON-NLS-1$
		}
	}

	/**
	 * Returns the preferences hint that is used to store the selection. 
	 * Clients may override. The default implementation returns NULL.
	 */
	protected PreferencesHint getPreferencesHint() {
		return null;
	}
	
	/** Return the {@link AdapterFactory} that is used by the element chooser dialog. */
	protected abstract AdapterFactory getAdapterFactory();
	
	/** Return the set of file extensions for the selectable files. */
	protected abstract String[] getFileExtensions();
			
	/** Return the selection data that is used for the insert operation. */
	protected abstract TSelectionData getSelectionData(ISelection selection);
	
	/** Return a {@link View} for the specified {@code selectionData}. */
	protected abstract View getViewForSelection(TSelectionData selectionData);
	
	/** Perform the insert operation with the given arguments. */
	protected abstract void performInsert(TSelectionData selectionData, String path) throws Exception;
	
	class ChooserContext implements Context {
		
		private AbstractInsertDiagramLinkAction<TSelectionData> _action;
		
		public ChooserContext(AbstractInsertDiagramLinkAction<TSelectionData> action) {
			_action = action;
		}

		@Override
		public boolean allowMultiSelection() {
			return false;
		}

		@Override
		public AdapterFactory getAdapterFactory() {
			return _action.getAdapterFactory();
		}

		@Override
		public String getDialogTitle() {
			return NLSupport.AbstractInsertDiagramLinkAction_DialogTitle;
		}

		@Override
		public String[] getFileExtesions() {
			return _action.getFileExtensions();
		}

		@Override
		public PreferencesHint getPreferenceHint() {
			return _action.getPreferencesHint();
		}

		@Override
		public ITreeContentProvider getTreeContentProvider() {
			return new BaseWorkbenchContentProvider();
		}		
	}
}
