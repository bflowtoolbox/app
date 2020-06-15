package org.bflow.toolbox.extensions.actions;

import org.bflow.toolbox.extensions.IDiagramCreationWizard;
import org.bflow.toolbox.extensions.NLSupport;
import org.bflow.toolbox.hive.libs.aprogu.collections.Array;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

/**
 * Diagram link action base class to create a diagram link from a model element
 * to a new diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 * @param <TSelectionData> Selection data class
 */
public abstract class AbstractCreateDiagramLinkAction<TSelectionData> extends AbstractDiagramLinkAction<TSelectionData, String> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected String getModificationValue(TSelectionData selectionData) {
		IDiagramCreationWizard wizard = getDiagramCreationWizard();
		
		if (wizard == null) return null;
		
		wizard.init(Workbench(), StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(Shell(), wizard);
		if (wizardDialog.open() == WizardDialog.CANCEL) return null;
		
		return wizard.getDiagram().getURI().toPlatformString(true);
	}
	
	/**
	 * Returns the diagram creation wizard that shall be used to create the new
	 * diagram file. If the user cancels the selection, NULL is returned.
	 */
	protected IDiagramCreationWizard getDiagramCreationWizard() {
		IWizardRegistry wizardRegistry = Workbench().getNewWizardRegistry();
		String[] wizardIds = getWizardIds();
		IDiagramCreationWizard[] wizardSet = new IDiagramCreationWizard[wizardIds.length];
		int p = 0;
		for (int i = -1; ++i != wizardIds.length;) {
			String wizardId = wizardIds[i];
			IWizardDescriptor wizardDescriptor = wizardRegistry.findWizard(wizardId);
			if (wizardDescriptor == null) continue;
			IDiagramCreationWizard dcWiz = null;
			try {
				IWorkbenchWizard workbenchWizard = wizardDescriptor.createWizard();
				dcWiz = Cast.as(IDiagramCreationWizard.class, workbenchWizard);
			} catch (Exception ex) {
				Log().error("Error on resolving workbench wizard", ex); //$NON-NLS-1$
			}
			
			if (dcWiz == null) continue;
			
			wizardSet[p++] = dcWiz;
		}
		
		wizardSet = Array.resize(IDiagramCreationWizard.class, wizardSet, p);
		
		WizardSelectionDialog wsd = new WizardSelectionDialog(Shell(), wizardSet);
		if (wsd.open() == WizardSelectionDialog.CANCEL) return null;
		return wsd.getSelectedWizard();		
	}
	
	/** Returns the ids of the wizards to be selectable. */
	protected abstract String[] getWizardIds();
	
	class WizardSelectionDialog extends Dialog {
		private final IDiagramCreationWizard[] _wizards;
		private IDiagramCreationWizard _selectedWizard;
		
		public WizardSelectionDialog(Shell parentShell, IDiagramCreationWizard[] wizards) {
			super(parentShell);
			setShellStyle(getShellStyle() | SWT.RESIZE);
			_wizards = wizards == null 
					? new IDiagramCreationWizard[0]
					: wizards
					;
		}
		
		public IDiagramCreationWizard getSelectedWizard() {
			return _selectedWizard;
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected Control createDialogArea(Composite parent) {
			Composite composite = (Composite) super.createDialogArea(parent);
			getShell().setText(NLSupport.AbstractCreateDiagramLinkAction_SelectionDialogTitle);
			createContentArea(composite);
			return composite;
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected void createButtonsForButtonBar(Composite parent) {
			super.createButtonsForButtonBar(parent);
			getButton(OK).setEnabled(false);
		}
		
		protected void createContentArea(Composite composite) {
			Label lbl = new Label(composite, SWT.NONE);
			lbl.setText(NLSupport.AbstractCreateDiagramLinkAction_SelectionDialogText);
			
			ComboViewer combo = new ComboViewer(composite, SWT.READ_ONLY);
			combo.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			combo.setContentProvider(ArrayContentProvider.getInstance());
			combo.setLabelProvider(new ComboLabelProvider());
			combo.setInput(_wizards);
			combo.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					Object selectedItem = event.getStructuredSelection().getFirstElement();
					_selectedWizard = Cast.as(IDiagramCreationWizard.class, selectedItem);
					getButton(OK).setEnabled(_selectedWizard != null);
				}				
			});
		}
		
		class ComboLabelProvider extends LabelProvider {
			@Override
			public String getText(Object element) {
				IDiagramCreationWizard dcw = (IDiagramCreationWizard) element;
				return dcw.getShortHint();
			}					
		}
	}
}
