package org.bflow.toolbox.hive.addons.components;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IShellRecordComponent;

/**
 * Implements the {@link IShellRecordComponent} to record the shell output of a program. This does nothing but
 * collecting the stream and put it into a list of strings.
 * @author Arian Storch
 * @since 09/11/10
 *
 */
public class ShellRecordComponent implements IShellRecordComponent {

	private List<String> consoleLines;
	
	private StringBuffer simpleStream;
	
	private boolean finished;
	
	@Override
	public boolean canLinkWith(IComponent component) {
		
		if(component instanceof ToolRunComponent)
			return true;
		
		if(component instanceof PrologRunComponent)
			return true;
		
		return false;
	}

	@Override
	public void finish() {
	}

	@Override
	public String getDescription(String abbreviation) {
		
		if(abbreviation.equalsIgnoreCase("de")) {
			String str = "Zeichnet die Shellausgabe eines Programms auf. Eine Analyse findet nicht statt!";
			
			return str;
		}
		
		String str = "Records the shell output of a program. It doesn't analyse it!";
		
		return str;
	}

	@Override
	public String getDisplayName() {
		return "Shell Record";
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public boolean hasParams() {
		return false;
	}

	@Override
	public void init() {
		finished = false;
	}

	@Override
	public void invoke() throws ComponentException {
		this.consoleLines = new ArrayList<String>();
		
		String str = simpleStream.toString().replace("addon:", "").replace("#FS#", "");
		
		consoleLines.add(str);
		
		finished = true;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void setParams(String param) {
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if(!(inputSource instanceof StringBuffer))
			throw new ComponentException("Input source has not expected format!");
		
		this.simpleStream = (StringBuffer)inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return this.consoleLines;
	}

}
