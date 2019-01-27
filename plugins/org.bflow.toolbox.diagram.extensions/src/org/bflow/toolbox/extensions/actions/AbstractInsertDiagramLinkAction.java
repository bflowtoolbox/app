package org.bflow.toolbox.extensions.actions;

import org.bflow.toolbox.extensions.GenericElementChooserDialog;
import org.bflow.toolbox.extensions.GenericElementChooserDialog.Context;
import org.bflow.toolbox.extensions.NLSupport;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;

/**
 * Diagram link action base class to insert a diagram link from one model
 * element to another diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 * @param <TSelectionData> Selection data class
 */
public abstract class AbstractInsertDiagramLinkAction<TSelectionData> extends AbstractDiagramLinkAction<TSelectionData, String> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected String getModificationValue(TSelectionData selectionData) {
		View view = getViewForSelection(selectionData);
		if (view == null) return null;
		
		GenericElementChooserDialog elementChooser = new GenericElementChooserDialog(Shell(), view, new ChooserContext(this));
		if (elementChooser.open() != Dialog.OK) return null;
		
		URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
		String path = selectedModelElementURI.toPlatformString(true);
		return path;
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
	
	/** Return a {@link View} for the specified {@code selectionData}. */
	protected abstract View getViewForSelection(TSelectionData selectionData);
	
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
