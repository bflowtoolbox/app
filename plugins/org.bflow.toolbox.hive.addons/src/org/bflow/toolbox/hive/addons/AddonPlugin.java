package org.bflow.toolbox.hive.addons;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.store.AddonStore;
import org.bflow.toolbox.hive.addons.store.ComponentStore;
import org.bflow.toolbox.hive.addons.store.PrologAdditionStore;
import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.hive.addons.validation.ValidationService;
import org.bflow.toolbox.hive.addons.workbench.AddonsWorkbenchListener;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 06/03/11
 * @version 17/12/13
 */
public class AddonPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.addons";

	// The shared instance
	private static AddonPlugin addonPlugin;

	// holds all created markers
	private HashMap<String, Vector<IMarker>> createdMarkers = new HashMap<String, Vector<IMarker>>();

	// holds the console
	private IOConsole console;

	// holds an instance of a progress monitor dialog
	private static ProgressMonitorDialog prgrMonitorDlg;
	
	// holds an instance of the preference store
	private Preferences preferencesStore;

	/**
	 * @generated
	 */
	public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint(
			PLUGIN_ID);

	/**
	 * Constructor.
	 */
	public AddonPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		PreferencesHint.registerPreferenceStore(DIAGRAM_PREFERENCES_HINT, this
				.getPreferenceStore());
		
		preferencesStore = InstanceScope.INSTANCE.getNode(PLUGIN_ID);

		addonPlugin = this;

		createConsole();
		AttributeViewPart.getInstance();
		AddonsWorkbenchListener.register();

		try {
			generateComponentRegistry();
		} catch (CoreException ex) {
			ex.printStackTrace();
		}

		try {
			generatePrologAdditionRegistry();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		try {
			generateProtocolRegistry();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		ValidationService.getInstance();
		
		// try to move all hive preference pages under the node of the diagram application 
		tryEmbeddingPreferencePages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		addonPlugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AddonPlugin getInstance() {
		return addonPlugin;
	}
	
	/**
	 * Returns the preferences store.
	 * @return preferences store
	 */
	public Preferences getPreferencesStore() {
		return preferencesStore;
	}

	/**
	 * Use this method to log to the plugin, that markers has been created.
	 * 
	 * @param id
	 *            unique marker type id
	 * @param markers
	 *            vector containing markers
	 */
	public void addMarker(String id, Vector<IMarker> markers) {
		if (createdMarkers.get(id) == null)
			createdMarkers.put(id, markers);
		else
			createdMarkers.get(id).addAll(markers);
	}

	/**
	 * Use this method to delete logged markers. Deleting markers by using this
	 * method won't affect other markers. This is recommended.
	 * 
	 * @param id
	 *            unique marker type id
	 * @param resource
	 *            resource that is connected with the markers
	 */
	public void deleteMarker(String id, IFile resource) {
		Vector<IMarker> indizes = new Vector<IMarker>();

		if (createdMarkers.get(id) == null)
			return;

		for (IMarker marker : createdMarkers.get(id))
			try {
				if (marker.getResource().getLocation().toString().equals(
						resource.getLocation().toString())) {
					marker.delete();
					indizes.add(marker);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}

		for (IMarker marker : indizes)
			createdMarkers.get(id).remove(marker);
	}

	/**
	 * Returns a ProgressMonitorDialog that can be used for showing that
	 * something is in progress.
	 * 
	 * @return ProgressMonitorDialog
	 */
	public static ProgressMonitorDialog getProgressMonitorDialog() {
		if (prgrMonitorDlg == null) {
			prgrMonitorDlg = new ProgressMonitorDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell());
			
			prgrMonitorDlg.setCancelable(false);
		}		

		return prgrMonitorDlg;
	}

	/**
	 * Creates the add-on console.
	 */
	private void createConsole() {
		this.console = new IOConsole("Add-on Console", null);

		ConsolePlugin.getDefault().getConsoleManager().addConsoles(
				new IConsole[] { this.console });
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(
				this.console);
	}

	/**
	 * Returns the add-on console.
	 * 
	 * @return add-on console
	 */
	public IOConsole getAddonConsole() {
		return this.console;
	}

	/**
	 * Requests to focus the mitamm console.
	 */
	public void requestConsoleFocus() {
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(
				this.console);
	}

	/**
	 * @generated
	 */
	public void logError(String error) {
		logError(error, null);
	}

	/**
	 * @generated
	 */
	public void logError(String error, Throwable throwable) {
		if (error == null && throwable != null) {
			error = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.ERROR, AddonPlugin.PLUGIN_ID, IStatus.OK,
						error, throwable));
		debug(error, throwable);
	}

	/**
	 * @generated
	 */
	public void logInfo(String message) {
		logInfo(message, null);
	}

	/**
	 * @generated
	 */
	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.INFO, AddonPlugin.PLUGIN_ID, IStatus.OK,
						message, throwable));
		debug(message, throwable);
	}

	/**
	 * @generated
	 */
	private void debug(String message, Throwable throwable) {
		if (!isDebugging()) {
			return;
		}
		if (message != null) {
			System.err.println(message);
		}
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}

	

	/**
	 * Registers all components to the component store that are registered by
	 * the defining extension point.
	 * 
	 * @throws CoreException
	 */
	private void generateComponentRegistry() throws CoreException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_COMPONENT);

		for (IConfigurationElement element : config) {
			IComponent component = (IComponent) element
					.createExecutableExtension("Class");
			ComponentStore.register(component);
		}
	}

	/**
	 * Registers all prolog additions to the prolog addition store that are
	 * registered by the defining extension point.
	 * 
	 * @throws IOException
	 */
	private void generatePrologAdditionRegistry() throws NullPointerException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_PROLOGADDITION);

		for (IConfigurationElement element : config) {
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			String file = element.getAttribute("File");
			String id = element.getAttribute("ID");
			String name = element.getAttribute("Name");
			URL url = bundle.getEntry(file);

			if (url == null)
				throw new NullPointerException("file url is null");

			PrologAdditionStore.register(id, name, url);
		}
	}

	/**
	 * Registers all protocols to the protocol store that are
	 * registered by the defining extension point.
	 * 
	 * @throws IOException
	 */
	private void generateProtocolRegistry() throws IOException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_PROTOCOL);

		for (IConfigurationElement element : config) {
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			String file = element.getAttribute("File");
			String ID = element.getAttribute("ID");
			URL url = bundle.getEntry(file);
			
			File tmpFile = File.createTempFile("protocol", "dyn");
			tmpFile.deleteOnExit();
			
			FileUtils.copyURLToFile(url, tmpFile);
			
			ProtocolDescriptor prtDesc = new ProtocolDescriptor(ID, tmpFile, false);
			
			AddonStore.installAddon(prtDesc);
		}
	}
	
	/**
	 * Checks if there is a extension contribution which embeds the add-ons preference pages 
	 * within another one. If there isn't any contribution, all pages which will be available 
	 * under the default 'Modeling Toolbox' node.
	 */
	private void tryEmbeddingPreferencePages() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_PREFERENCEPAGECONTAINER);
		
		if(config == null || config.length == 0) return;
		IConfigurationElement contribution = config[0];
		String pageId = contribution.getAttribute("pageId");
		if(StringUtils.isBlank(pageId)) return;
		
		PreferenceManager mgr = PlatformUI.getWorkbench().getPreferenceManager();
		
		IPreferenceNode appNode = mgr.find(pageId);
		if(appNode == null) return;
		
		// Remove the hive node
		IPreferenceNode hiveNode = mgr.find("org.bflow.toolbox.addons.preferences.MainPage");
		mgr.remove(hiveNode);
		
		// Add the hive children to the app node
		for(IPreferenceNode hiveChild:hiveNode.getSubNodes()) {
			appNode.add(hiveChild);
		}
	}

	/**
	 * Extension point id for add-ons components.
	 */
	public static final String EXTENSION_ID_ADDON_COMPONENT = "org.bflow.toolbox.addons.component";

	/**
	 * Extension point id for add-ons prolog additions.
	 */
	public static final String EXTENSION_ID_ADDON_PROLOGADDITION = "org.bflow.toolbox.addons.prologaddition";

	/**
	 * Extension point id for a protocol.
	 */
	public static final String EXTENSION_ID_ADDON_PROTOCOL = "org.bflow.toolbox.addons.protocol";
	
	/**
	 * Extension point id for a rule.
	 */
	public static final String EXTENSION_ID_ADDON_RULE = "org.bflow.toolbox.addons.rule";
	
	/**
	 * Extension point id for the preference page container.
	 */
	public static final String EXTENSION_ID_ADDON_PREFERENCEPAGECONTAINER = "org.bflow.toolbox.hive.addons.preferencePageContainer";

	
}
