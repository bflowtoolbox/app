package org.bflow.toolbox.hive.addons.providers;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.addons.AddonsPlugin;
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
 * @since 2010-04-17
 * @version 2013-12-30
 * 			2015-02-27 Added more reliable dispose checks
 * 			2018-10-03 Added NULL check in dispose method 			
 * 
 */
public class MenuContributionProvider extends ContributionItem implements IWorkbenchContribution, ProtocolStoreListener, ToolStoreListener {

	private Menu _addonMenu;
	private MenuItem _menuContainer;
	
	private InternalPageListener _pageListener = new InternalPageListener();
	private InternalPartListener _partListener = new InternalPartListener();
	
	private Map<ProtocolDescriptor, MenuItem> _pd2miMap = new HashMap<ProtocolDescriptor, MenuItem>();
	
	private IWorkbenchPage _activeWorkbenchPage;

	/**
	 * Constructor.
	 */
	public MenuContributionProvider() {		
		super("org.bflow.toolbox.addons.dynamic1"); //$NON-NLS-1$
		AddonStore.addStoreListener(this);
		ToolStore.addStoreListener(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#isDynamic()
	 */
	@Override
	public boolean isDynamic() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.menus.IWorkbenchContribution#initialize(org.eclipse.ui.services.IServiceLocator)
	 */
	@Override
	public void initialize(IServiceLocator serviceLocator) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		window.addPageListener(_pageListener);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	@Override
	public void dispose() {
		AddonStore.removeStoreListener(this);
		ToolStore.removeStoreListener(this);
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			window.removePageListener(_pageListener);
		}			
		
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
	 */
	@Override
	public void fill(Menu menu, int index) {	
		MenuItem addonsMenuItem = new MenuItem(menu, SWT.CASCADE, index);
		_menuContainer = addonsMenuItem;
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
				checkAndApplyMenuState(_activeWorkbenchPage);
			}
		});
		
		addonsMenuItem.setMenu(addonsMenu);
		_addonMenu = addonsMenu;

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
		if(_addonMenu == null) return;
		
		MenuItem menuItem = new MenuItem(_addonMenu, SWT.NONE);
		menuItem.setText(((Standardprotocol) protocolDescriptor.getProtocol()).getName());
		menuItem.setData(protocolDescriptor);

		boolean isEnabled = protocolDescriptor.isValid();
		menuItem.setEnabled(isEnabled);
		
		// Mapping the created item
		_pd2miMap.put(protocolDescriptor, menuItem);

		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeAddon(protocolDescriptor);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.events.ProtocolStoreListener#protocolAdded(org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor)
	 */
	@Override
	public void protocolAdded(final ProtocolDescriptor pd) {
		if (!canOperate()) return;
		addMenuItem(pd);
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.events.ProtocolStoreListener#protocolRemoved(org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor)
	 */
	@Override
	public void protocolRemoved(ProtocolDescriptor pd) {
		if (!canOperate()) return;
		for (MenuItem item : _addonMenu.getItems()) {
			if (item.getText().equalsIgnoreCase(((Standardprotocol) pd.getProtocol()).getName())) {
				_pd2miMap.remove(pd);
				item.dispose();
				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.events.ToolStoreListener#storeUpdate()
	 */
	@Override
	public void storeUpdate() {
		if (!canOperate()) return;
		
		for (MenuItem item : _addonMenu.getItems()) {
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
		boolean isAlive = !_addonMenu.isDisposed();
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
		
		if (!_menuContainer.isDisposed())
			_menuContainer.setEnabled(enabled);
		
		// Check menu items separately
		if (enabled) {
			String extension = getDiagramEditorFileExtension(activeEditor);
			
			if (extension != null && !extension.isEmpty()) {
				for (ProtocolDescriptor protocolDescriptor:_pd2miMap.keySet()) {
					Protocol protocol = protocolDescriptor.getProtocol();
					boolean isEnabled = protocol.isValid() && protocol.isApplicableFor(extension);
					_pd2miMap.get(protocolDescriptor).setEnabled(isEnabled);	
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
		if (editorPart == null || editorPart.getEditorInput() == null) return null;

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
		DiagramMarkerNavigationProvider.getInstance().deleteMarkers(ResourcesPlugin.getWorkspace().getRoot(), IResource.DEPTH_INFINITE);

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
					if (MessageDialog.openQuestion(activeEditor.getSite().getShell(), 
							NLSupport.MenuContributionProvider_QuestionDialogTitle, 
							NLSupport.MenuContributionProvider_QuestionDialogText))
						activeEditor.getSite().getPage().saveEditor(activeEditor, false);
					else
						return;

				IEditorInput input = activeEditor.getEditorInput();
				if (input instanceof IFileEditorInput) {
					IFile resource = ((IFileEditorInput) input).getFile();
					chain.setSource(resource.getLocation().toFile());
					AddonsPlugin.getProgressMonitorDialog().run(true, true, new ProtocolProgressDialog(chain));
				}
			}

		} catch (Exception ex) {
			AddonsPlugin.getInstance().logError("Error on executing add-on", ex);
		}
	}
	
	/**
	 * Implements {@link IPartListener2} to listen to workbench page changes.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 06.09.12
	 *
	 */
	private class InternalPageListener implements IPageListener {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageActivated(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageActivated(IWorkbenchPage page) {
			_activeWorkbenchPage = page;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageClosed(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageClosed(IWorkbenchPage page) {
			checkAndApplyMenuState(page);
			page.removePartListener(_partListener);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageOpened(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageOpened(IWorkbenchPage page) {
			checkAndApplyMenuState(page);
			page.addPartListener(_partListener);
		}
		
	}
	
	/**
	 * Implements {@link IPartListener2} to listen to workbench part changes.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 06.09.12
	 *
	 */
	private class InternalPartListener implements IPartListener2 {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partActivated(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			partOpened(partRef);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partClosed(IWorkbenchPartReference partRef) {	
			if (partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partHidden(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {	}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
			if (partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
			if (partRef.getPart(false) instanceof DiagramEditor) {
				checkAndApplyMenuState(partRef.getPage());
			}
		}	
	}
}
