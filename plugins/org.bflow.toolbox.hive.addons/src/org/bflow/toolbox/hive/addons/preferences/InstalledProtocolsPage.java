package org.bflow.toolbox.hive.addons.preferences;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IToolRunComponent;
import org.bflow.toolbox.hive.addons.preferences.dialogs.AddonEditDialog;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.AddonStore;
import org.bflow.toolbox.hive.addons.store.Key;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the preferences page for the installed add-ons.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13.04.10
 * @version 23.08.13
 * 			12.03.15 Added check of ToolStore.hasTool()
 */
public class InstalledProtocolsPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private TableViewer viewer;

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.WRAP);

		composite.setLayout(new GridLayout(1, false));

		Label lblText = new Label(composite, SWT.NONE);
		lblText.setText(NLSupport.InstalledProtocolsPage_LblDescription);

		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayout(new GridLayout(5, false));

		GridData btnGridData = new GridData();
		btnGridData.widthHint = 80;

		Button btnAdd = new Button(panel, SWT.NONE);
		btnAdd.setLayoutData(btnGridData);
		btnAdd.setText(NLSupport.InstalledProtocolsPage_BtnAdd);

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// Create a dummy protocol
				Standardprotocol prot = new Standardprotocol();
				prot.setName("unnamed"); //$NON-NLS-1$
				prot.setDescription(StringUtils.EMPTY); //$NON-NLS-1$

				// Create a dummy descriptor
				ProtocolDescriptor pd = new ProtocolDescriptor(StringUtils.EMPTY, null, true); //$NON-NLS-1$
				pd.setProtocol(prot);
				pd.setName("unnamed"); //$NON-NLS-1$

				// Open the edit dialog in edit mode
				AddonEditDialog dlg = new AddonEditDialog(composite.getShell(), pd, false);

				int dlgResult = dlg.open();

				if (dlgResult == AddonEditDialog.CANCEL) 
					return;

				// Configure the protocol so it can be saved unique
				String name = prot.getName().replaceAll(" ", StringUtils.EMPTY); //$NON-NLS-1$ //$NON-NLS-2$
				pd.setFile(new File(Key.KEY_PROTOCOLSTORE_PATH.getAbsolutePath() + "/" + name + ".xml")); //$NON-NLS-1$ //$NON-NLS-2$
				pd.setId("org.bflow.toolbox.mitamm.protocols.user." + name); //$NON-NLS-1$
				pd.setName(prot.getName());

				pd.addDisplayName("default", prot.getName()); //$NON-NLS-1$
				pd.addDescriptionText("default", prot.getDescription(null)); //$NON-NLS-1$

				// Tell the store that there is a new add-on
				AddonStore.installAddon(pd);

				// Add the new add-on to the viewer
				viewer.add(pd);
			}
		});

		Button btnRemove = new Button(panel, SWT.NONE);
		btnRemove.setLayoutData(btnGridData);
		btnRemove.setText(NLSupport.InstalledProtocolsPage_BtnRemove);

		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = viewer.getTable().getSelectionIndex();

				if (index == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer.getElementAt(index);

				if (!pd.storable)
					return;

				if (MessageDialog.openConfirm(composite.getShell(),
						NLSupport.InstalledProtocolsPage_BtnDialogConfirmTitle, NLSupport.InstalledProtocolsPage_BtnDialogConfirmDescription)) {
					AddonStore.removeAddon(pd);
					viewer.remove(pd);
				}
			}
		});

		Button btnEdit = new Button(panel, SWT.NONE);
		btnEdit.setLayoutData(btnGridData);
		btnEdit.setText(NLSupport.InstalledProtocolsPage_BtnEdit);

		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sel = viewer.getTable().getSelectionIndex();

				if (sel == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer.getTable()
						.getItem(sel).getData();

				/*
				 * - The clone mechanism should be handled by the edit dialog -
				 * Changes made on parameters aren't handled
				 */
				// Create a copy of the component list to revert changes if
				// necessary
				List<IComponent> componentList = pd.getProtocol().getComponents();
				List<IComponent> componentListClone = new ArrayList<IComponent>(componentList);

				if (!pd.storable)
					return;

				AddonEditDialog dlg = new AddonEditDialog(composite.getShell(),	pd, true);

				int dlgResult = dlg.open();

				// Revert any changes made on the component list
				if (dlgResult == AddonEditDialog.CANCEL) {
					componentList.clear();
					componentList.addAll(componentListClone);
				}
			}
		});

		Button btnExport = new Button(panel, SWT.NONE);
		btnExport.setLayoutData(btnGridData);
		btnExport.setText(NLSupport.InstalledProtocolsPage_BtnExport);

		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = viewer.getTable().getSelectionIndex();

				if (index == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer.getElementAt(index);

				FileDialog fd = new FileDialog(composite.getShell(), SWT.SAVE);

				fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFileName(pd.getName());

				String path = null;

				if ((path = fd.open()) != null) {
					try {
						
						// When the add-on is new or modified we have to save it first
						// Maybe it would be better to use a 'dirty' pattern?
						pd.saveDescriptor();
						
						File src = pd.getFile();
						File tgt = new File(path);

						SAXReader reader = new SAXReader();

						Document doc = reader.read(src);

						Element root = doc.getRootElement();

						Standardprotocol sp = (Standardprotocol) pd.getProtocol();

						for (IComponent comp : sp.getComponents()) {
							if (comp instanceof IToolRunComponent) {
								String toolname = sp.getComponentParam(comp);

								ToolDescriptor td = ToolStore.getTool(toolname);

								Element toolhint = root.addElement("toolhint"); //$NON-NLS-1$
								toolhint.addAttribute("name", td.getName()) //$NON-NLS-1$
										.addAttribute("param", td.getParam()); //$NON-NLS-1$
							}
						}

						OutputFormat format = OutputFormat.createPrettyPrint();
						XMLWriter xmlWriter = new XMLWriter(new FileWriter(tgt), format);

						xmlWriter.write(doc);
						xmlWriter.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		Button btnImport = new Button(panel, SWT.NONE);
		btnImport.setLayoutData(btnGridData);
		btnImport.setText(NLSupport.InstalledProtocolsPage_BtnImport);

		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN);

				fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$

				String path = null;

				if ((path = fd.open()) != null) {
					try {
						File src = new File(path);

						SAXReader reader = new SAXReader();

						Document doc = reader.read(src);
						Element root = doc.getRootElement();

						for (Iterator<?> it = root.elementIterator("toolhint"); it //$NON-NLS-1$
								.hasNext();) {
							Element el = (Element) it.next();
							String name = el.attributeValue("name"); //$NON-NLS-1$
							String param = el.attributeValue("param"); //$NON-NLS-1$

							ToolDescriptor td = new ToolDescriptor(name, StringUtils.EMPTY, param);
							if (ToolStore.hasTool(name)) continue;
							ToolStore.installTool(td);

							InstalledToolsPage prefPage = InstalledToolsPage.getInstance();

							if (prefPage != null)
								prefPage.insert(td);

							root.remove(el);
						}

						File tgt = new File(Key.KEY_PROTOCOLSTORE_PATH.getAbsolutePath()
								+ "/" + root.attributeValue("name") + ".xml"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

						OutputFormat format = OutputFormat.createPrettyPrint();
						XMLWriter xmlWriter = new XMLWriter(new FileWriter(tgt), format);

						xmlWriter.write(doc);
						xmlWriter.close();

						ProtocolDescriptor addonDescriptor = new ProtocolDescriptor(
								root.attributeValue("name"), tgt, true); //$NON-NLS-1$

						// Get add-on name
						String addonName = ((Standardprotocol) addonDescriptor.getProtocol()).getName();

						// Check if there is already an add-on with the same name
						if (AddonStore.hasAddon(addonName)) {
							MessageDialog.openError(getShell(), 
									NLSupport.InstalledProtocolsPage_ErrorDialogTitle,
									NLSupport.InstalledProtocolsPage_ErrorDialogMessage);
							return;
						}

						AddonStore.installAddon(addonDescriptor);
						viewer.add(addonDescriptor);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		viewer = new TableViewer(composite, SWT.FULL_SELECTION);

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 200;
		tableData.widthHint = 400;
		table.setLayoutData(tableData);

		TableViewerColumn colToolName = new TableViewerColumn(viewer, SWT.NONE);
		colToolName.getColumn().setText(NLSupport.InstalledProtocolsPage_TableHeaderProtocol);
		colToolName.getColumn().setWidth(400);
		ColumnViewerToolTipSupport.enableFor(colToolName.getViewer());
		colToolName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ProtocolDescriptor desc = (ProtocolDescriptor) element;
				return ((Standardprotocol) desc.getProtocol()).getName();
			}

			@Override
			public String getToolTipText(Object element) {
				ProtocolDescriptor desc = (ProtocolDescriptor) element;

				return desc.getProtocol().getDescription(
						NLUtil.getActiveLanguageAbbreviation());
			}

		});

		for (ProtocolDescriptor descriptor : AddonStore.getInstalledAddons()) {
			viewer.add(descriptor);
		}

		return composite;
	}

	@Override
	protected void performApply() {
		AddonStore.save();
	}

	@Override
	public boolean performOk() {
		performApply();
		return super.performOk();
	}
}