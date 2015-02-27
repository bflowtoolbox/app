package org.bflow.toolbox.hive.addons.providers;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.addons.AddonPlugin;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.events.ProtocolStoreListener;
import org.bflow.toolbox.hive.addons.events.ToolStoreListener;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.AddonStore;
import org.bflow.toolbox.hive.addons.store.ConfigurationStore;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.hive.addons.utils.ProtocolProgressDialog;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

/**
 * Defines a MenuContributionService that adds dynamically all installed and
 * valid protocols to the add-on menu.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/04/10
 * @version 30/12/13
 * 
 */
public class MenuContributionProvider extends ContributionItem implements
		IWorkbenchContribution, ProtocolStoreListener, ToolStoreListener {

	private Menu addonMenu;
	private MenuItem menuContainer;
	
	private InternalPageListener internalPageListener = new InternalPageListener();
	private InternalPartListener internalPartListener = new InternalPartListener();
	
	private Map<ProtocolDescriptor, MenuItem> protocolDescriptor2MenuItemMap = new HashMap<ProtocolDescriptor, MenuItem>();
	
	private IWorkbenchPage activeWorkbenchPage;

	/**
	 * Constructor.
	 */
	public MenuContributionProvider() {		
		super("org.bflow.toolbox.addons.dynamic1"); //$NON-NLS-1$
		AddonStore.addStoreListener(this);
		ToolStore.addStoreListener(this);
	}
	
	@Override
	public boolean isDynamic() {
		return true;
	}
	
	@Override
	public boolean isDirty() {
		return true;
	}

	@Override
	public void initialize(IServiceLocator serviceLocator) {
		IWorkbenchWindow wnd = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		wnd.addPageListener(internalPageListener);
	}
	
	@Override
	public void dispose() {
		AddonStore.removeStoreListener(this);
		ToolStore.removeStoreListener(this);
		IWorkbenchWindow wnd = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		wnd.removePageListener(internalPageListener);
		super.dispose();
	}

	@Override
	public void fill(Menu menu, int index) {	
		MenuItem addonsMenuItem = new MenuItem(menu, SWT.CASCADE, index);
		menuContainer = addonsMenuItem;
		addonsMenuItem.setText("&Add-ons"); //$NON-NLS-1$

		addonsMenuItem.setImage(new Image(menu.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/Addons-16.png"))); //$NON-NLS-1$

		// Menu for the add-ons
		Menu addonsMenu = new Menu(menu); 
		
		// Add menu listener to validate items every time
		// when the menu becomes expanded
		addonsMenu.addMenuListener(new MenuAdapter() {
			@Override
			public void menuShown(MenuEvent e) {
				checkAndApplyMenuState(activeWorkbenchPage);
			}
		});
		
		addonsMenuItem.setMenu(addonsMenu);
		addonMenu = addonsMenu;

		for (ProtocolDescriptor descriptor : AddonStore.getInstalledAddons()) {
			addMenuItem(descriptor);
		}
	}

	/**
	 * Adds the menu item for the given ProtocolDescriptor.
	 * 
	 * @param protocolDescriptor
	 *            the protocol descriptor
	 */
	private void addMenuItem(final ProtocolDescriptor protocolDescriptor) {
		if(addonMenu == null) return;
		MenuItem sItem = new MenuItem(addonMenu, SWT.NONE);
		sItem.setText(((Standardprotocol) protocolDescriptor.getProtocol()).getName());
		sItem.setData(protocolDescriptor);

		boolean isEnabled = protocolDescriptor.isValid();
		sItem.setEnabled(isEnabled);
		
		// Mapping the created item
		protocolDescriptor2MenuItemMap.put(protocolDescriptor, sItem);

		sItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeAddon(protocolDescriptor);
			}
		});
	}

	@Override
	public void protocolAdded(final ProtocolDescriptor pd) {
		if (!canOperate()) return;
		addMenuItem(pd);
	}

	@Override
	public void protocolRemoved(ProtocolDescriptor pd) {
		if (!canOperate()) return;
		for (MenuItem item : addonMenu.getItems()) {
			if (item.getText().equalsIgnoreCase(((Standardprotocol) pd.getProtocol()).getName())) {
				protocolDescriptor2MenuItemMap.remove(pd);
				item.dispose();
				return;
			}
		}
	}

	@Override
	public void storeUpdate() {
		if (!canOperate()) return;
		
		for (MenuItem item : addonMenu.getItems()) {
			if (item.isDisposed()) continue;
			ProtocolDescriptor pd = (ProtocolDescriptor) item.getData();

			item.setEnabled(pd.isValid());
		}
	}
	
	/**
	 * Returns TRUE if this instance is still alive and provides contributions
	 * to main menu. Otherwise it returns FALSE and invokes dispose() before!
	 * 
	 * @return TRUE if this instance is still alive
	 */
	private boolean canOperate() {
		boolean isAlive = !addonMenu.isDisposed();
		if (isAlive) return true;
		dispose();
		return false;
	}
	
	
	/**
	 * Checks and applies the enabled state of the add-on menu. It is only enabled when at least
	 * one instance of {@link DiagramEditor} is opened or active.
	 * 
	 * @param page
	 *            the workbench page
	 */
	private void checkAndApplyMenuState(IWorkbenchPage page) {
		IEditorPart activeEditor = page.getActiveEditor();
		boolean enabled = activeEditor != null && (activeEditor instanceof DiagramEditor);
		
		if(!menuContainer.isDisposed())
			menuContainer.setEnabled(enabled);
		
		// Check menu items separately
		if(enabled) {
			String extension = getDiagramEditorFileExtension(activeEditor);
			
			if(extension != null && !extension.isEmpty()) {
				for(ProtocolDescriptor protocolDescriptor:protocolDescriptor2MenuItemMap.keySet()) {
					Protocol protocol = protocolDescriptor.getProtocol();
					boolean isEnabled = protocol.isValid() && protocol.isApplicableFor(extension);
					protocolDescriptor2MenuItemMap.get(protocolDescriptor).setEnabled(isEnabled);	
				}
			}
		}
	}
	
	/**
	 * Returns the diagram editor file extension.
	 * 
	 * @param diagramEditor
	 *            the diagram editor
	 * @return the diagram editor file extension or null
	 */
	private String getDiagramEditorFileExtension(IEditorPart editorPart) {
		if(editorPart == null || editorPart.getEditorInput() == null) {
			return null;
		}

		String name = editorPart.getEditorInput().getName();
		String splits[] = name.split("\\."); //$NON-NLS-1$
		String extension = splits[splits.length-1].toLowerCase();
		
		return extension;
	}
	
	/**
	 * Executes the add-on defined by the given protocol descriptor.
	 * 
	 * @param protocolDescriptor Descriptor for the add-on to execute
	 */
	private void executeAddon(ProtocolDescriptor protocolDescriptor) {
		// Delete all markers
		DiagramMarkerNavigationProvider.getInstance().deleteMarkers(
				ResourcesPlugin.getWorkspace().getRoot(),
				IResource.DEPTH_INFINITE);

		try {
			Protocol chain = protocolDescriptor.getProtocol();

			IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			
			String strBool = ConfigurationStore.getInstance().get(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS);
			boolean saveAllOpen = (strBool == null ? false : Boolean.parseBoolean(strBool));

			// Save all open diagrams
			if (saveAllOpen) {

				boolean doIt = MessageDialog.openConfirm(activeEditor.getSite().getShell(),
								NLSupport.MenuContributionProvider_ConfirmDialogTitle,
								NLSupport.MenuContributionProvider_ConfirmDialogText);

				if (doIt) {
					activeEditor.getSite().getPage().saveAllEditors(true);
				}
			}

			if (activeEditor != null) {
				if (activeEditor.isDirty())
					if (MessageDialog.openQuestion(activeEditor
							.getSite().getShell(), 
							NLSupport.MenuContributionProvider_QuestionDialogTitle, 
							NLSupport.MenuContributionProvider_QuestionDialogText))
						activeEditor.getSite().getPage().saveEditor(
								activeEditor, false);
					else
						return;

				IEditorInput input = activeEditor.getEditorInput();
				if (input instanceof IFileEditorInput) {
					IFile resource = ((IFileEditorInput) input)
							.getFile();

					chain.setSource(resource.getLocation().toFile());

					AddonPlugin.getProgressMonitorDialog().run(true,
							true, new ProtocolProgressDialog(chain));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Implements {@link IPartListener2} to listen to workbench page changes.
	 * 
	 * @author Arian Storch
	 * @since 06/09/12
	 *
	 */
	private class InternalPageListener implements IPageListener {

		@Override
		public void pageActivated(IWorkbenchPage page) {
			activeWorkbenchPage = page;
		}

		@Override
		public void pageClosed(IWorkbenchPage page) {
			checkAndApplyMenuState(page);
			page.removePartListener(internalPartListener);
		}

		@Override
		public void pageOpened(IWorkbenchPage page) {
			checkAndApplyMenuState(page);
			page.addPartListener(internalPartListener);
		}
		
	}
	
	/**
	 * Implements {@link IPartListener2} to listen to workbench part changes.
	 * 
	 * @author Arian Storch
	 * @since 06/09/12
	 *
	 */
	private class InternalPartListener implements IPartListener2 {

		@Override
		public void partActivated(IWorkbenchPartReference partRef) {

		}

		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			partOpened(partRef);
		}

		@Override
		public void partClosed(IWorkbenchPartReference partRef) {	
			if(partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}

		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) {	
		}

		@Override
		public void partHidden(IWorkbenchPartReference partRef) {			
		}

		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {	
		}

		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
			if(partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}

		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
			if(partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}	
	}
}
