package org.bflow.toolbox.hive.addons.components;

import java.util.List;

import org.bflow.toolbox.hive.addons.AddonsPlugin;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IConsoleViewGeneratorComponent;
import org.eclipse.ui.console.IOConsoleOutputStream;

/**
 * Implements the {@link IConsoleViewGeneratorComponent} to display messages on
 * the Add-on console.
 * <p/>
 * The input source must be a vector of strings. The ouput source is null.
 * 
 * @author Arian Storch
 * @since 29/09/10
 * @version 07/06/11
 * 
 */
public class ConsoleDisplayComponent implements IConsoleViewGeneratorComponent {

	private boolean finished;

	private List<?> consoleLines;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#canLinkWith(org.bflow.
	 * toolbox.mitamm.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ShellRecordComponent)
			return true;
		
		if( component instanceof FileRecordComponent)
			return true;

		if (component instanceof FileAnalysisComponent)
			return true;

		if (component instanceof ShellAnalysisComponent)
			return true;

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
		// Nothing to do here
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#getDescription(java.lang
	 * .String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Gibt die gesammelten Meldungen aus einem Programm auf der Konsole aus.";

			return str;
		}

		String str = "Displays the collected messages of a program on the console.";

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Console Display";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#init()
	 */
	@Override
	public void init() {
		finished = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		if (consoleLines.size() == 0) return;

		try {
			AddonsPlugin addonPlugin = AddonsPlugin.getInstance();
			addonPlugin.requestConsoleFocus();

			IOConsoleOutputStream stream = addonPlugin.getAddonConsole().newOutputStream();
			stream.setActivateOnWrite(true);

			Object o = consoleLines.get(0);

			if (o instanceof String) {
				for (Object s : consoleLines) {
					String str = (String) s;
					stream.write(str.replace("addon:", "") + "\n\r");
				}
			}
			
			if (o instanceof IAddonMessage) {
				IAddonMessage mm = (IAddonMessage)o;
				stream.write(mm.toString());
			}
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#setParams(java.lang.String
	 * )
	 */
	@Override
	public void setParams(String param) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#transformInput(java.lang
	 * .Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null) throw new ComponentException("Quelle ist null");
		if (!(inputSource instanceof List<?>)) throw new ComponentException("Input source has not expected format!");

		this.consoleLines = (List<?>) inputSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		return this.consoleLines;
	}

}
