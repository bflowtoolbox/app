package org.bflow.toolbox.hive.addons.preferences.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.components.DiagramExportComponent;
import org.bflow.toolbox.hive.addons.components.ToolAdapterComponent;
import org.bflow.toolbox.hive.addons.components.ToolRunComponent;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.IComposableComponent;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.AddonStore;
import org.bflow.toolbox.hive.addons.store.ComponentStore;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Provides a dialog for creating or editing add-ons.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03.12.12
 * @version 06.06.14
 * 			15.03.15 Validate each selected component
 * 			04.04.15 Handle issue that viewer has other object references that component list
 * 			03.03.17 Updated style for higher resolutions
 * 
 */
public class AddonEditDialog extends Dialog {

	private static final int ADD_CODE = 13;
	private static final int REMOVE_CODE = 14;

	private TableViewer viewer;
	private Text txtName;
	private Text txtDescription;

	private boolean editMode = false;

	private ProtocolDescriptor addonDescriptor;

	/**
	 * Creates a new dialog.
	 * 
	 * @param parentShell
	 *            parent shell
	 * @param addonDescriptor
	 *            instance of AddonDescriptor
	 * @param editMode
	 *            if true the dialog is in edit mode
	 */
	public AddonEditDialog(Shell parentShell, ProtocolDescriptor addonDescriptor, boolean editMode) {
		super(parentShell);
		setShellStyle(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		setBlockOnOpen(true);

		this.addonDescriptor = addonDescriptor;
		this.editMode = editMode;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		// Name
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText(NLSupport.AddonEditDialog_LblName);

		txtName = new Text(composite, SWT.BORDER);

		GridData gridData = new GridData(SWT.LEAD, SWT.TOP, true, true);
		gridData.widthHint = 650 / 2;
		txtName.setLayoutData(gridData);

		txtName.setText(((Standardprotocol) addonDescriptor.getProtocol()).getName());

		// Description
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(NLSupport.AddonEditDialog_LblDescription);

		txtDescription = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);

		gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
		gridData.heightHint = txtDescription.computeSize(SWT.DEFAULT, SWT.DEFAULT).y * 2;
		gridData.widthHint = 650;
		txtDescription.setLayoutData(gridData);

		String strDescription = addonDescriptor.getDescriptionText(NLSupport.getActiveLanguage());
		if (strDescription == null)
			strDescription = StringUtils.EMPTY;
		
		txtDescription.setText(strDescription);

		// Table viewer
		viewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		Table table = viewer.getTable();

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 400;
		tableData.widthHint = 650;
		table.setLayoutData(tableData);

		TableViewerColumn compNameViewer = new TableViewerColumn(viewer, SWT.NONE);
		compNameViewer.getColumn().setText(NLSupport.AddonEditDialog_TableHeaderComponent);
		compNameViewer.getColumn().setWidth(250);
		compNameViewer.setLabelProvider(new ComponentNameLabelProvider());
		compNameViewer.setEditingSupport(new ComponentNameEditingSupport(viewer));
		ColumnViewerToolTipSupport.enableFor(compNameViewer.getViewer());

		TableViewerColumn compParamsViewer = new TableViewerColumn(viewer, SWT.NONE);
		compParamsViewer.getColumn().setText(NLSupport.AddonEditDialog_TableHeaderParameter);
		compParamsViewer.getColumn().setWidth(400);
		compParamsViewer.setLabelProvider(new ComponentParamsLabelProvider());
		compParamsViewer.setEditingSupport(new ComponentParamEditingSupport(viewer));

		// Fill the table content
		viewer.setInput(addonDescriptor.getProtocol().getComponents());

		// Button pane
		Composite btnPanel = new Composite(composite, SWT.NONE);
		btnPanel.setLayout(new GridLayout(2, true));

		createButton(btnPanel, ADD_CODE, NLSupport.AddonEditDialog_BtnAdd, false);
		createButton(btnPanel, REMOVE_CODE, NLSupport.AddonEditDialog_BtnRemove, false);

		// Separator
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		return container;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#create()
	 */
	@Override
	public void create() {
		super.create();
		this.getShell().setText(NLSupport.AddonEditDialog_DialogTitle);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		((Standardprotocol) addonDescriptor.getProtocol()).setName(txtName.getText());
		addonDescriptor.addDescriptionText(NLSupport.getActiveLanguage(), txtDescription.getText());

		if (isValid()) {
			super.okPressed();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	/**
	 * Will be invoked when the add button has been pressed.
	 */
	private void handleAddPressed() {
		viewer.add(new NullComponent());
	}

	/**
	 * Will be invoked when the remove button has been pressed.
	 */
	private void handleRemovePressed() {		
		int selectedItemIndex = viewer.getTable().getSelectionIndex();
		IComponent selectedComponent = getSelectedComponent();
		
		// Remove table item
		viewer.getTable().remove(selectedItemIndex);
		
		// Remote component and its parameter
		Protocol protocol = addonDescriptor.getProtocol();		
		List<IComponent> components = protocol.getComponents();
		components.remove(selectedComponent);
		protocol.removeComponentParam(selectedComponent);
	}
	
	/**
	 * Returns the selected component from the protocol component collection.
	 * 
	 * @return Component or NULL
	 */
	private IComponent getSelectedComponent() {
		int selectedItemIndex = viewer.getTable().getSelectionIndex();
		if (selectedItemIndex == -1) return null;
		Protocol protocol = addonDescriptor.getProtocol();		
		List<IComponent> components = protocol.getComponents();
		if (components.size() <= selectedItemIndex) return null;
		IComponent component = components.get(selectedItemIndex);
		return component;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == ADD_CODE) {
			handleAddPressed();
			return;
		}

		if (buttonId == REMOVE_CODE) {
			handleRemovePressed();
			return;
		}

		super.buttonPressed(buttonId);
	}

	/**
	 * Proofs if the protocol is well configured.
	 * 
	 * @return true or false
	 */
	private boolean isValid() {
		String addonName = ((Standardprotocol) addonDescriptor.getProtocol()).getName();

		if (addonName == null || addonName.trim().isEmpty()) {
			MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle,	NLSupport.AddonEditDialog_ErrorDialogTextName);
			return false;
		}

		if (AddonStore.hasAddon(addonName) && !editMode) {
			MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle,	NLSupport.AddonEditDialog_ErrorDialogTextAlreadyInstalled);
			return false;
		}

		int viewerSize = viewer.getTable().getItemCount();

		if (viewerSize == 0) {
			MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle,	NLSupport.AddonEditDialog_ErrorDialogTextMissingComponent);
			return false;
		}

		if (viewerSize == 1) {
			IComponent comp = (IComponent) viewer.getElementAt(0);
			if (!isValidComponent(comp)) return false;
		}

		for (int i = -1; ++i < viewerSize;) {
			IComponent comp = (IComponent) viewer.getElementAt(i);
			
			if (!isValidComponent(comp)) return false;
			if (i == 0) continue;
			
			IComponent prev = (IComponent) viewer.getElementAt(i - 1);
			boolean canBeLinked = comp.canLinkWith(prev);
			
			if (prev instanceof IComposableComponent) {
				IComposableComponent composable = (IComposableComponent) prev;
				canBeLinked = composable.supportsSucceeder(comp);
			}

			if (!canBeLinked) {
				String message = String.format(NLSupport.AddonEditDialog_ChainErrorText, comp.getDisplayName(), prev.getDisplayName());
				MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle, message);
				return false;
			}			
		}

		return true;
	}

	/**
	 * Proofs if the given component is well configured.
	 * 
	 * @param component
	 *            component to proof
	 * @return true or false
	 */
	private boolean isValidComponent(IComponent component) {
		if (component.hasParams() || component instanceof DiagramExportComponent) {
			String param = addonDescriptor.getProtocol().getComponentParam(component);

			if (StringUtils.isBlank(param)) {
				String message = String.format(NLSupport.AddonEditDialog_ChainComponentNeedsParams,	component.getDisplayName());
				MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle, message);
				return false;
			}
		}

		if (component instanceof NullComponent) {
			MessageDialog.openError(getShell(), NLSupport.AddonEditDialog_ErrorDialogTitle, NLSupport.AddonEditDialog_ErrorDialogTextMissingComponent);
			return false;
		}

		return true;
	}

	/**
	 * Defines a ColumnLabelProvider for displaying {@link IComponent} names.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 25.09.10
	 */
	protected class ComponentNameLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			IComponent comp = (IComponent) element;
			return comp.getDisplayName();
		}

		@Override
		public String getToolTipText(Object element) {
			return ((IComponent) element).getDescription(NLUtil.getActiveLanguageAbbreviation());
		}
	}

	/**
	 * Defines a ColumnLabelProvider for displaying {@link IComponent} params.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @version 25.09.10
	 * 
	 */
	protected class ComponentParamsLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof NullComponent) return null;

			String param = ((Standardprotocol) addonDescriptor.getProtocol()).getComponentParam(comp);

			if (param == null)
				param = StringUtils.EMPTY; //$NON-NLS-1$

			return param;
		}
	}

	/**
	 * Dummy {@link IComponent} implementation for the table.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 28.09.10
	 */
	private class NullComponent implements IComponent {

		@Override
		public boolean canLinkWith(IComponent component) {
			return false;
		}

		@Override
		public void finish() {
		}

		@Override
		public String getDescription(String abbreviation) {
			return null;
		}

		@Override
		public String getDisplayName() {
			return NLSupport.AddonEditDialog_NullComponentText;
		}

		@Override
		public boolean hasFinished() {
			return false;
		}

		@Override
		public boolean hasParams() {
			return false;
		}

		@Override
		public void init() {
		}

		@Override
		public void invoke() throws ComponentException {
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public void setParams(String param) {

		}

		@Override
		public void transformInput(Object inputSource)
				throws ComponentException {
		}

		@Override
		public Object transformOutput() throws ComponentException {
			return null;
		}

	}

	/**
	 * Implements a editing support for the component name.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 25.09.10
	 */
	protected class ComponentNameEditingSupport extends EditingSupport {

		private CellEditor editor;
		private List<String> cBoxValues;

		public ComponentNameEditingSupport(ColumnViewer viewer) {
			super(viewer);

			cBoxValues = ComponentStore.getInstance().getUserfriendlyComponentNames();
			int size = cBoxValues.size();

			this.editor = new ComboBoxCellEditor(((TableViewer) viewer)
					.getTable(), cBoxValues.toArray(new String[size]),
					SWT.READ_ONLY);
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return this.editor;
		}

		@Override
		protected Object getValue(Object element) {
			IComponent comp = (IComponent) element;

			String name = comp.getDisplayName();
			int index = cBoxValues.indexOf(name);

			return index;
		}

		@Override
		protected void setValue(Object element, Object value) {
			IComponent old = (IComponent) element; // this references to cBoxValues instances
			old = getSelectedComponent(); // this references to the "real" instances

			int protIndex = -1;
			if (old != null) {
				protIndex = addonDescriptor.getProtocol().getComponents().indexOf(old);
			}

			int index = (Integer) value;
			if (index == -1) return;

			String compName = this.cBoxValues.get(index);

			IComponent nu = ComponentStore.getInstance().identify(compName, true);

			if (old != null && nu.getDisplayName().equalsIgnoreCase(old.getDisplayName()))
				return;

			if (protIndex != -1) { // existing component is going to be replaced
				addonDescriptor.getProtocol().getComponents().set(protIndex, nu);
			} else { // new component is created and added
				try {
					nu = nu.getClass().newInstance();
					addonDescriptor.getProtocol().getComponents().add(nu);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			viewer.refresh();
		}
	}

	/**
	 * Implements an editing support for the component value.
	 * 
	 * @author Arian Storch
	 * @since 25/09/10
	 * @version 04/08/12
	 * 
	 */
	protected class ComponentParamEditingSupport extends EditingSupport {

		private CellEditor editorTxt;
		private CellEditor editorBoxExport;
		private CellEditor editorBoxTools;
		private CellEditor editorBoxToolAdapter;

		private List<String> exportNames = new ArrayList<String>();
		private List<String> toolNames = new ArrayList<String>();
		private List<String> toolAdapterKinds = new ArrayList<String>();

		public ComponentParamEditingSupport(ColumnViewer viewer) {
			super(viewer);

			for (IInterchangeDescriptor exd : ExportDescriptorStore.getDepository(true)) {
				exportNames.add(exd.getName());
			}

			int size = exportNames.size();

			this.editorTxt = new TextCellEditor(((TableViewer) viewer)
					.getTable());

			this.editorBoxExport = new ComboBoxCellEditor(
					((TableViewer) viewer).getTable(), exportNames
							.toArray(new String[size]), SWT.READ_ONLY);

			List<ToolDescriptor> tools = ToolStore.getInstalledTools();

			for (ToolDescriptor td : tools)
				toolNames.add(td.getName());

			int size2 = toolNames.size();

			this.editorBoxTools = new ComboBoxCellEditor(((TableViewer) viewer)
					.getTable(), toolNames.toArray(new String[size2]),
					SWT.READ_ONLY);

			toolAdapterKinds = new ArrayList<String>(Arrays
					.asList(new String[] { "equal", "file to shell", //$NON-NLS-1$ //$NON-NLS-2$
							"shell to file" })); //$NON-NLS-1$

			this.editorBoxToolAdapter = new ComboBoxCellEditor(
					((TableViewer) viewer).getTable(), toolAdapterKinds
							.toArray(new String[toolAdapterKinds.size()]),
					SWT.READ_ONLY);
		}

		@Override
		protected boolean canEdit(Object element) {
			return ((IComponent) element).hasParams();
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof DiagramExportComponent)
				return this.editorBoxExport;

			if (comp instanceof ToolRunComponent)
				return this.editorBoxTools;

			if (comp instanceof ToolAdapterComponent)
				return this.editorBoxToolAdapter;

			return this.editorTxt;
		}

		@Override
		protected Object getValue(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof NullComponent)
				return null;

			String value = ((Standardprotocol) addonDescriptor.getProtocol())
					.getComponentParam(comp);

			if (value == null)
				value = ""; //$NON-NLS-1$

			if (comp instanceof DiagramExportComponent)
				return exportNames.indexOf(value);

			if (comp instanceof ToolRunComponent)
				return toolNames.indexOf(value);

			if (comp instanceof ToolAdapterComponent)
				return toolAdapterKinds.indexOf(value);

			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			IComponent comp = (IComponent) element;

			String param = ""; //$NON-NLS-1$

			if (comp instanceof DiagramExportComponent) {
				if ((Integer) value == -1)
					return;

				param = this.exportNames.get((Integer) value);
			} else if (comp instanceof ToolRunComponent) {
				if ((Integer) value == -1)
					return;

				param = this.toolNames.get((Integer) value);
			} else if (comp instanceof ToolAdapterComponent) {
				if ((Integer) value == -1)
					return;

				param = this.toolAdapterKinds.get((Integer) value);
			} else {
				param = (String) value;
			}

			((Standardprotocol) addonDescriptor.getProtocol())
					.setComponentParam(comp, param);

			super.getViewer().update(element, null);
		}
	}
}
