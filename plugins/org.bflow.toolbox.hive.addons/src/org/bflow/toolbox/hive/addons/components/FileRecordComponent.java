package org.bflow.toolbox.hive.addons.components;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IFileRecordComponent;

/**
 * Implements the {@link IFileRecordComponent} interface.
 * @author Arian Storch
 * @since 30/10/10
 * @version 09/11/10
 */
public class FileRecordComponent implements IFileRecordComponent {

	private boolean finished;
	
	private File srcFile;
	
	private Vector<String> fileLines;
	
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
			String str = "Zeichnet die Dateiausgabe des externen Programms ohne Analyse auf.";
			
			return str;
		}
		
		String str = "Records the output of an external program without analysis.";
		
		return str;
	}

	@Override
	public String getDisplayName() {
		return "File Record";
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
		fileLines = new Vector<String>();
		
		try {
			for(Object line:FileUtils.readLines(srcFile))
				fileLines.add((String)line);			
		} catch (IOException e) {
			throw new ComponentException(e);
		} finally {
			finished = true;
		}
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
		
		if (!(inputSource instanceof StringBuffer))
			throw new ComponentException("input source has not expected format");

		String line = ((StringBuffer)inputSource).toString();

		int index = line.indexOf("addon:");
		
		if(index == -1)
			throw new ComponentException("no filename for input source set");
		
		String filename = line.substring(index+6);
		
		filename = filename.replace("#FS#", "");
		filename = filename.replace("\n", "").replace("\r", "");

		srcFile = new File(filename);
		
		if(srcFile == null)
			throw new ComponentException("file is null");

		if (!srcFile.exists())
			throw new ComponentException("input source does not exist: "
					+ srcFile);
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return fileLines;
	}

}
