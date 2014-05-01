package org.bflow.toolbox.contributions.addons;

import java.net.URL;

import org.bflow.toolbox.hive.addons.components.ToolRunComponent;
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
 * @since 12/08/12
 * 
 */
public class HTMLViewComponent implements IComponent {

	private IWorkbenchBrowserSupport support;
	private IWebBrowser browser;
	private boolean finished;
	private String pathToHtml;

	@Override
	public void init() {
		finished = false;
		support = PlatformUI.getWorkbench().getBrowserSupport();

		try {
			browser = support.createBrowser("viewer");
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {

	}

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

	@Override
	public Object transformOutput() throws ComponentException {
		return pathToHtml;
	}

	@Override
	public void finish() {
		finished = true;
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public String getDescription(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Oeffnet eine HTML Seite in einem Browser.";

			return str;
		}

		String str = "Opens a Html file in a browser.";

		return str;
	}

	@Override
	public String getDisplayName() {
		return "HTML View";
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean hasParams() {
		return true;
	}

	@Override
	public void setParams(String param) {
		pathToHtml = param;
	}

	@Override
	public boolean canLinkWith(IComponent component) {

		if (component instanceof ToolRunComponent)
			return true;

		return false;
	}

	private String getWorkspacePath(IFile file) {
		return file.getProject().getLocation().toOSString();
	}

}
