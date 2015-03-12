package org.bflow.toolbox.hive.addons.components;

import java.net.URL;

import org.bflow.toolbox.hive.addons.AddonPlugin;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Implements {@link IComponent} to provide the possibility to open HTML-Files
 * within the Eclipse instance.
 * 
 * @since 12.08.12
 * @version 12.03.15 Moved to hive add-ons package
 */
public class HTMLViewComponent implements IComponent {

	private IWorkbenchBrowserSupport support;
	private IWebBrowser browser;
	private boolean finished;
	private String pathToHtml;

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#init()
	 */
	@Override
	public void init() {
		finished = false;
		support = PlatformUI.getWorkbench().getBrowserSupport();

		try {
			browser = support.createBrowser("viewer");
		} catch (PartInitException e) {
			AddonPlugin.getInstance().logError("Error on creating browser instance", e);
		}

	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#transformInput(java.lang.Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException { }

	@Override
	public void invoke() throws ComponentException {
		IFile source = ((IFile) Standardprotocol.getRunning().getSource());
		pathToHtml = pathToHtml.replace("$project", getWorkspacePath(source));

		// opens the file in browser
		try {
			browser.openURL(new URL("file:///" + pathToHtml));
		} catch (Exception ex) {
			throw new ComponentException(ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		return pathToHtml;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
		finished = true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#getDescription(java.lang.String)
	 */
	@Override
	public String getDescription(String abbreviation) {
		if (abbreviation.startsWith("de")) {
			return "Oeffnet eine HTML Seite in einem Browser.";
		}
		return "Opens a Html file in a browser.";
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "HTML View";
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#setParams(java.lang.String)
	 */
	@Override
	public void setParams(String param) {
		pathToHtml = param;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComponent#canLinkWith(org.bflow.toolbox.hive.addons.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ToolRunComponent)
			return true;

		return false;
	}

	/**
	 * Returns the OS dependent full qualified path of the given file as string.
	 * 
	 * @param file
	 *            File to resolve its path
	 * @return OS dependent full qualified path
	 */
	private String getWorkspacePath(IFile file) {
		return file.getProject().getLocation().toOSString();
	}
}