package org.bflow.toolbox.hive.eclipse.integration;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

/**
 * Provides methods to integrate the Hive plug-in suite into eclipse.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.07.2015
 * @version 29.04.2016 Fixed NPE during onInitialize()
 * 			14.05.2017 Added logging
 *
 */
public class EclipseIntegrator {

	/**
	 * Static constructor to register the workbench listener
	 */
	static {
		onInitialized();
	}
	
	static private ArrayList<PlatformEditorInfo> fPlatformEditors;
	static private Log logWriter = LogFactory.getLog(EclipseIntegrator.class);
	
	/**
	 * Is called by the static constructor.
	 */
	static private void onInitialized() {		
		// Register part listener
		// Note: getActiveWorkbenchWindow() may return NULL if it's not running on the UI thread
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(new IntegratorWorkbenchPartListener());
				} catch (Exception ex) {
					logWriter.error("Error on adding view part listener", ex);
				}
			}
		});
			
		// Select all installed editors
		fPlatformEditors = new ArrayList<>();
		
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.ui.editors");
		for (IConfigurationElement configurationElement : elements) {
			String idAttribute = configurationElement.getAttribute("id");
			String classAttribute = configurationElement.getAttribute("class");
			
			fPlatformEditors.add(new PlatformEditorInfo(idAttribute, classAttribute));
		}
	}

	/**
	 * Initializes the integration feature. This method can be called multiple times, but it's 
	 * only activated once.
	 */
	static public void init() {
		// Just ensure to call the static constructor
		
		// TODO Remove me
		activateFor("*.bpmn");
		activateFor("*.epc");		
		activateFor("*.oepc");
	}
	
	static private HashMap<String, String> fRegisteredEditors = new HashMap<>();
	static private HashMap<String, String> fActiveEditorProxy = new HashMap<>();
	
	/**
	 * Activates the bflow* toolbox hive features for all editors that are
	 * applicable for the given file name (extension).
	 * 
	 * @param fileName
	 *            File name with extension
	 */
	static public void activateFor(String fileName) {
		IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
		IEditorDescriptor editorDescriptor = editorRegistry.getDefaultEditor(fileName);
		String editorId = editorDescriptor.getId();
		
		String fileNameExtension = FilenameUtils.getExtension(fileName);
		
		fRegisteredEditors.put(fileNameExtension, editorId); // TODO Fix cyclic dependency
		
		// Is there a proxy?
		String editorProxyId = DiagramEditorProxyRegistry.getEditorProxyId(editorId, fileName);
		if (editorProxyId == null) return;
		
		// Map origin editor to proxy
		fActiveEditorProxy.put(editorId, editorProxyId);
	}
	
	/**
	 * Returns TRUE if the bflow* toolbox hive features are activated for all
	 * editors that applicable for the given file name (extension).
	 * 
	 * @param fileName
	 *            File name with extension
	 * @return TRUE or FALSE
	 */
	static public boolean isActivatedFor(String fileName) {
		String fileNameExtension = FilenameUtils.getExtension(fileName);
		String originEditorId = fRegisteredEditors.get(fileNameExtension);
		if (originEditorId == null) return false;
		boolean isActive = (fActiveEditorProxy.get(originEditorId) != null);
		return isActive;
	}
	
	/**
	 * Returns the editor id of the origin editor that is applicable for the
	 * given file name. This method returns NULL when the hive features aren't
	 * activated for the origin editor.
	 * 
	 * @param fileName
	 *            File name with extension
	 * @return Origin editor id or NULL
	 */
	static public String getOriginDefaultEditorIdFor(String fileName) {
//		if (!isActivatedFor(fileName)) return null;
		String fileNameExtension = FilenameUtils.getExtension(fileName);
		String originEditorId = fRegisteredEditors.get(fileNameExtension);
		return originEditorId;
	}
	
	/**
	 * Returns a newly created instance of the origin editor part that is
	 * applicable for the given file name. This method returns NULL when the
	 * hive features aren't activated for the origin editor.
	 * 
	 * @param fileName
	 *            File name with extension
	 * @return Newly created instance or NULL
	 */
	static public IEditorPart getOriginDefaultEditorInstance(String fileName) {
		String originEditorId = getOriginDefaultEditorIdFor(fileName);
		if (originEditorId == null) return null;
		
		PlatformEditorInfo editorInfo = null;
		for (PlatformEditorInfo info : fPlatformEditors) {
			if (!info.EditorId().equalsIgnoreCase(originEditorId)) continue;
			editorInfo = info;
			break;
		}
		
		if (editorInfo == null) return null;
		String editorClassName = editorInfo.EditorClassName();
		try {
			@SuppressWarnings("unchecked")
			Class<IEditorPart> editorPartClass = (Class<IEditorPart>) Class.forName(editorClassName);
			IEditorPart editorPart = editorPartClass.newInstance();
			return editorPart;
		} catch (Exception ex) {
			logWriter.error("Error on creating new instance of "+editorClassName, ex);
			return null;
		}
	}

	/**
	 * Returns TRUE if the given editor can be proxied.
	 * 
	 * @param originEditorId
	 *            Editor id to check
	 * @param fileName
	 *            File name to check
	 * @return TRUE or FALSE
	 */
	public static boolean isProxySupported(String originEditorId, String fileName) {
		String editorProxyId = fActiveEditorProxy.get(originEditorId);
		boolean isProxySupported = editorProxyId != null;
		return isProxySupported;
	}
	
	/**
	 * Returns TRUE if the given editor is an proxy.
	 * 
	 * @param editorId
	 *            Editor id to check
	 * @param fileName
	 *            File name to check
	 * @return TRUE or FALSE
	 */
	public static boolean isProxy(String editorId, String fileName) {
		for (String activeProxyId : fActiveEditorProxy.values()) {
			if (editorId.equalsIgnoreCase(activeProxyId)) return true;
		}
		return false;
	}
	
	/**
	 * Provides an implementation of {@link IPartListener2} to provide the integration support. 
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 12.07.2015
	 *
	 */
	static class IntegratorWorkbenchPartListener implements IPartListener2 {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partActivated(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partClosed(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partOpened(IWorkbenchPartReference partRef) {
			IWorkbenchPart workbenchPart = partRef.getPart(false);
			if (!(workbenchPart instanceof IEditorPart)) return;
			
			IEditorPart editorPart = (IEditorPart) workbenchPart;
			IEditorInput editorInput = editorPart.getEditorInput();
			String fileName = editorInput.getName();
			
			boolean isSupported = EclipseIntegrator.isActivatedFor(fileName);
			if (!isSupported) {
				// TODO Show dialog
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partHidden(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partVisible(IWorkbenchPartReference partRef) { }

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) { }
	}
	
	/**
	 * Describes an editor that is known to the platform.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 12.07.2015
	 *
	 */
	static class PlatformEditorInfo {
		private String fEditorId;
		private String fEditorClassName;
		
		/**
		 * Creates a new instance.
		 * 
		 * @param editorId
		 *            Editor id
		 * @param editorClassName
		 *            Editor class name
		 */
		public PlatformEditorInfo(String editorId, String editorClassName) {
			super();
			fEditorId = editorId;
			fEditorClassName = editorClassName;
		}
		
		/**
		 * Returns the editor id.
		 * 
		 * @return Editor id
		 */
		public String EditorId() {
			return fEditorId;
		}
		
		/**
		 * Returns the editor class name
		 * 
		 * @return Editor class name
		 */
		public String EditorClassName() {
			return fEditorClassName;
		}
	}
}
