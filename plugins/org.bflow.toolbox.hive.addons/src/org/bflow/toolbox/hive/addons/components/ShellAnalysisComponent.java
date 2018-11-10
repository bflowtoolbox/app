package org.bflow.toolbox.hive.addons.components;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter;
import org.bflow.toolbox.hive.addons.interfaces.IShellAnalysisComponent;
import org.bflow.toolbox.hive.addons.utils.MessageFormatterRegistry;
import org.eclipse.core.resources.IFile;

/**
 * <p>
 * Implements the {@link IToolChainComponent} to analyze the console output and
 * generate problems view messages.
 * </p>
 * <p>
 * A vector containing strings is being expected as input source. Only lines
 * beginning with "addon:" are being recognized and processed. A vector of
 * ProblemsViewMessages is going to created by this component.
 * </p>
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 25.04.10
 * @version 10.03.13
 * 			28.10.16 AST - ???
 * 
 */
public class ShellAnalysisComponent implements IShellAnalysisComponent {
	private boolean finished;
	private StringBuffer simpleStream;
	private List<IAddonMessage> messageList;
	private IFile markerResource;
	
	/**
	 * Default constructor.
	 */
	public ShellAnalysisComponent() {
	}

	/**
	 * Constructor.
	 * 
	 * @param markerResource
	 *            resource that gets the markers
	 */
	public ShellAnalysisComponent(IFile markerResource) {
		this.markerResource = markerResource;
	}

	@Override
	public void finish() {
	}

	@Override
	public String getDescription(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Überwacht die Shellausgabe des verwendeten externen Programms und achtet dabei auf Meldungen"
					+ ", die mit bflow*: beginnen. Nur diese werden zur weiteren Verarbeitung erfasst.";

			return str;
		}

		String str = "Monitors the shell output of the used external program and looks for messages beginning with"
				+ "bflow*:. Only these messages are logged for further purposes.";

		return str;
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public void init() {
		finished = false;
	}

	@Override
	public void invoke() throws ComponentException {
		try {
			messageList = new ArrayList<IAddonMessage>();
			
			String lines[] = simpleStream.toString().split("#FS#");

			for (String line : lines) {
				
				int pos = line.indexOf("addon:");
				
				if (pos == -1)
					continue;

				String str = line.substring(pos+6);
				
				str = str.replace("#FS#", "");

				String parts[] = str.split("]");
				String contents[] = new String[parts.length];
				
				for(int i = 0; i < contents.length; i++)
					contents[i] = parts[i].substring(1).replace("#FS#", "");
					
				String type = contents[0];
				IMessageFormatter messageFormatter = MessageFormatterRegistry.Instance.getMessageFormatter(type);
				if (messageFormatter == null) continue; 				
				
				IAddonMessage addonMessage = messageFormatter.format(contents, markerResource);
				
				if (!messageList.contains(addonMessage))
					messageList.add(addonMessage);
			}
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
		}
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null) throw new ComponentException("inputSource is null");
		if (!(inputSource instanceof StringBuffer))	throw new ComponentException("input source is no string");

		simpleStream = (StringBuffer)inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return messageList;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return "Shell Analysis";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ToolRunComponent)
			return true;

		if (component instanceof PrologRunComponent)
			return true;
		return false;
	}

	@Override
	public boolean hasParams() {
		return false;
	}

	@Override
	public void setParams(String param) {
	}

}
