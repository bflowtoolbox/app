package org.bflow.toolbox.hive.addons.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.interfaces.IToolRunComponent;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.TemporaryFileServer;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.eclipse.core.resources.IFile;

/**
 * <p>
 * Defines a tool run component which implements {@link IToolRunComponent} to
 * run a tool within the protocol.
 * </p>
 * <p>
 * The tool runs inside an external process and finishes when the process exits.
 * <br/>
 * If there are parameters for the tool, they are replace before the tool is
 * being started.
 * </p>
 * <p>
 * Look at the documentation for further details like the mapped parameters.
 * </p>
 * <p>
 * A file object containing the source file for the tool is being expected and
 * used to replace the "$file" parameter.
 * </p>
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.04.10
 * @version 25.05.13
 * 
 */
public class ToolRunComponent implements IToolRunComponent {
	
	/** The tool. */
	private ToolDescriptor tool;

	/** The source file. */
	private File srcFile;

	/** The target file. */
	private File tgtFile;

	/** The finished flag. */
	private boolean finished = false;
	
	/** The collecting console messages flag. */
	private boolean collectingConsoleMessages = true;

	/** The temp path. */
	private File tmpPath;

	/** The shell stream. */
	private StringBuffer shellStream;

	/**
	 * Constructor.
	 */
	public ToolRunComponent() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#getDescription(java.lang.String)
	 */
	@Override
	public String getDescription(String abbreviation) {
		if (abbreviation.startsWith("de")) {
			String str = "Startet ein externes Programm und überwacht dessen Ausgabe nach Meldungen, die für bflow* bestimmt sind.";
			return str;
		}

		String str = "Starts an external program and logs its output.";
		return str;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#init()
	 */
	@Override
	public void init() {
		finished = false;

		String tmpDir = System.getProperty("java.io.tmpdir");
		String stmpPath = tmpDir + "/bflowtemp/";

		tmpPath = new File(stmpPath);
		tmpPath.mkdir();

		shellStream = new StringBuffer(1024);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		try {
			List<String> processArgs = new ArrayList<String>();
			
			// If starting a jar, there shall be "java -jar" at first
			if (tool.getPath().endsWith(".jar")) {
				processArgs.add("java");
				processArgs.add("-jar");
			}
			
			// If starting a batch program, there must be "cmd /c" at first
			if(tool.getPath().endsWith(".bat")) {
				processArgs.add("cmd");
				processArgs.add("/c");
			}

			// Adding path to the tool
			processArgs.add(tool.getPath());
		    
		    IFile source = ((IFile) Standardprotocol.getRunning().getSource());
			
			// Parameters
			String params = tool.getParam();
			String processedParams = adjustFileCommands(params);
			
			// Split in to parts
			List<String> splitParams = splitToParams(processedParams);
			
			// Adjusting parameters
			for (int i = 0; i < splitParams.size(); i++) {
				String splitParam = splitParams.get(i);
				
				splitParam = replaceParameter(splitParam, "$source", srcFile.getAbsolutePath());
				splitParam = replaceParameter(splitParam, "$wsSource", getWSSource(source));
				splitParam = replaceParameter(splitParam, "$project",	getWorkspacePath(source));
				splitParam = replaceParameter(splitParam, "$addon_temp", tmpPath.getAbsolutePath());
				splitParam = replaceParameter(splitParam, "$install_dir", FilenameUtils.getFullPath(tool.getPath()), false);
				splitParam = replaceParameter(splitParam, "$name", FilenameUtils.getName(srcFile.getAbsolutePath()));
				
				if (!splitParam.trim().isEmpty()) {
					processArgs.add(splitParam);
				}
			}

			try {			
				String[] processArgsArray = processArgs.toArray(new String[0]);
				ProcessBuilder processBuilder = new ProcessBuilder(processArgsArray);
				processBuilder.directory(new File(tool.getPath()).getParentFile());
				processBuilder.redirectOutput(Redirect.PIPE);
				processBuilder.redirectError(Redirect.PIPE);
				Process process = processBuilder.start();
				process.waitFor();				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

				BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				// The protocol must wait until the process has been terminated
				boolean running = true;

				while (running) {
					try {
						readInputStream(br); // dummy, needed so the process
												// will terminate
						int exitValue = process.exitValue(); // terminated?

						if (exitValue < 0) {
							readInputStream(errReader);
							running = false;
							throw new ComponentException("Process did not end properly! " +
									"Look into the log file for futher informations.");
						}

						readInputStream(br);
						running = false;
					} catch (IllegalThreadStateException ex) {
						continue;
					} finally {
						readInputStream(br);
					}
				}
			} catch (IOException ex) {
				throw new ComponentException("Could not execute tool!", ex);
			}
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
			Standardprotocol.getLogger().info(shellStream.toString());
		}
	}

	/**
	 * Reads the input stream of the executed process.
	 * 
	 * @param br
	 *            buffered reader
	 */
	private void readInputStream(BufferedReader br) {
		// CharBuffer charBuffer = CharBuffer.allocate(1024);

		int i = -1;

		try {
			while (br.ready() && (i = br.read()) != -1)
				shellStream.append((char) i);
		} catch (IOException e) {
			Standardprotocol.getLogger().error("read input stream failed", e);
		}
	}

	/**
	 * Adjust file commands.
	 * 
	 * @param command
	 *            the command
	 * @return the string
	 */
	private String adjustFileCommands(String command) {
		int pos = -1;
		int v = 1;

		while ((pos = command.indexOf("$file" + v)) != -1) {
			String param = command.substring(pos, pos + 6);

			File f = TemporaryFileServer.getFileReference(param);

			command = command.replace(param, (f == null ? StringUtils.EMPTY : f.getAbsolutePath()));
			v++;
		}

		return command;
	}
	
	/**
	 * Splits a string using whitespace that are not surrounded by
	 * quotes.
	 * 
	 * @param subjectString String to split
	 * @return Collection split string parts
	 */
	private List<String> splitToParams(String subjectString) {
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(subjectString);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		}
		
		return matchList;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#transformInput(java.lang.Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null) throw new ComponentException("inputSource is NULL");
		if (inputSource.getClass() != File.class) throw new ComponentException("inputSource has not expected format!");

		srcFile = (File) inputSource;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		if (collectingConsoleMessages) return shellStream;
		return tgtFile;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IToolRunComponent#setProtocol(org.bflow.toolbox.addons.core.model.Protocol)
	 */
	@Override
	public void setProtocol(Protocol protocol) {
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IToolRunComponent#setToolDescriptor(org.bflow.toolbox.addons.utils.ToolDescriptor)
	 */
	@Override
	public void setToolDescriptor(ToolDescriptor td) {
		this.tool = td;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		if (tool != null && tool.getPath() != null && !tool.getPath().isEmpty()) {
			String path = tool.getPath();
			
			File file = new File(path);			
			if (file.exists()) return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Tool Run";
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#canLinkWith(org.bflow.toolbox.addons.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof DiagramExportComponent) return true;
		if (component instanceof ToolAdapterComponent) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#setParams(java.lang.String)
	 */
	@Override
	public void setParams(String param) {
		ToolDescriptor td = ToolStore.getTool(param);
		this.tool = td;
	}


	/**
	 * Returns the workspace path of the given file.
	 * 
	 * @param file
	 *            the file
	 * @return the workspace path
	 */
	private String getWSSource(IFile file) {
		return file.getLocation().toOSString();
	}

	/**
	 * Returns the workspace path.
	 * 
	 * @param file
	 *            the file
	 * @return the workspace path
	 */
	private String getWorkspacePath(IFile file) {
		return file.getProject().getLocation().toOSString();
	}

	/**
	 * Replaces within the given line the given parameter with the given value.
	 * If you set <code>quote</code> true, the parameter will be quoted.
	 * 
	 * @param line
	 *            the original line
	 * @param param
	 *            the parameter that will be replaced
	 * @param value
	 *            the value that will be inserted
	 * @param quote
	 *            the quote when true the value will be quoted
	 * @return the new line
	 */
	private String replaceParameter(String line, String param, String value, boolean quote) {
		String repl = String.format((quote ? ParamLineQuote : ParamLineUnquote), value);
		return line.replace(param, repl);
	}

	/**
	 * Replaces within the given line the given parameter with the given value.
	 * The new value will be quoted.
	 * 
	 * @param line
	 *            the original line
	 * @param param
	 *            the parameter that will be replaced
	 * @param value
	 *            the value that will be inserted
	 * @return the new line
	 */
	private String replaceParameter(String line, String param, String value) {
		return replaceParameter(line, param, value, true);
	}

	/** The Constant ParamLineUnquote. */
	private static final String ParamLineUnquote = "%s";
	
	/** The Constant ParamLineQuote. */
	private static final String ParamLineQuote = "\"%s\"";
}
