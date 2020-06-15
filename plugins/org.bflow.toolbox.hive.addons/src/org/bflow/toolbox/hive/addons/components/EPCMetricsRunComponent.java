package org.bflow.toolbox.hive.addons.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IEPCMetricsRunComponent;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.ToolStore;

/**
 * Implements the {@link IEPCMetricsRunComponent] to run the external tool called "epcToolsHeuristic".<p/>
 * The tool is started by invoking a new process.<br/>
 * The output stream of this process is beeing displayed on console.<br/>
 * The protocol is blocked until the process is terminated.<p/>
 * A file object of an epml diagram is expected as input source. If it isn't you will get a
 * <code>ComponentException</code>.<br/>
 * After the process has been done a xml result file is created in the local os temp folder. Though
 * this will be deleted on exit you get a file object to this as output by <code>transformOutput()</code>
 * method.
 * @author Arian Storch
 * @since 12/10/09
 * @version 07/10/10
 */
public class EPCMetricsRunComponent implements IEPCMetricsRunComponent {
	private String exePath;

	private File outputFolder;
	private File outputFile;
	private File sourceFile;

	private boolean finished = false;

	@Override
	public void finish() {
		if (outputFile != null) {
			outputFile.deleteOnExit();
			outputFile.getParentFile().deleteOnExit();
		}

		if (outputFolder != null)
			outputFolder.deleteOnExit();
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public void init() {
		this.exePath = ToolStore.getTool("EPC-Metrics").getPath();
		
		try {
			outputFolder = File.createTempFile("EpcToolsHeuristicsOutput", "");
			outputFolder.delete();
			// outputFolder.mkdir();
			outputFolder.deleteOnExit();
		} catch (IOException ex) {
			Standardprotocol.getLogger().error(
					"could not create temp ouput folder", ex);
		}
	}

	@Override
	public void invoke() throws ComponentException {
		if (exePath.isEmpty())
			throw new ComponentException(
					"Es ist kein Pfad zum Programm gesetzt!");

		String installPath = FilenameUtils.getFullPath(exePath);

		String cpPath = "\"%CLASSPATH%;" + installPath + "lib" + File.separator
				+ "*;" + installPath + "*\"";

		String command = "java -Xmx900m -cp " + cpPath;
		command += " \"de.ulpz.ebus.epc.metrics.BatchMetricCalculator\"";
		command += " -r -s -i " + sourceFile.getAbsolutePath();
		command += " -o " + outputFolder.getAbsolutePath();
		command += " -c " + installPath + "sap.conf";

		try {
			Standardprotocol.getLogger().info("[EpcToolsHeuristics] " + command);

			Process p = Runtime.getRuntime().exec(command);

			BufferedReader br = new BufferedReader(new InputStreamReader(p
					.getInputStream()));

			/*
			 * the chain must wait until the process has been terminated
			 */

			boolean running = true;

			while (running) {
				try {
					/*
					 * collecting text send by the stream
					 */

					readInputStream(br);

					p.exitValue(); // terminated?

					readInputStream(br);

					running = false;

					/*
					 * try { Thread.sleep(1000); } catch (InterruptedException
					 * e) { EpcToolsHeuristicProtocol.getLogger().error("", e);
					 * }
					 */
				} catch (IllegalThreadStateException ex) {
					running = true;
					continue;
				}
			}

		} catch (IOException ex) {
			throw new ComponentException("Could not run the command: "
					+ command, ex);
		}

		String baseLine = outputFolder.getAbsolutePath()
				+ System.getProperty("file.separator") + sourceFile.getName();

		if (baseLine.indexOf(".tmp") > -1)
			baseLine = baseLine.replace(".temp", "_result.xml");
		else
			baseLine = baseLine.replace("epml", "epm_result.xml");

		// System.err.println(baseLine);

		String help = outputFolder.getAbsolutePath() + "/"
				+ sourceFile.getName().replace(".tmp", "_result.xml");

		// System.out.println(help);

		outputFile = new File(baseLine);

		if (!outputFile.exists())
			outputFile = new File(help);

		// MessageDialog.openInformation(null,
		// MessageProvider.getMessage("ETH#msg1"),
		// MessageProvider.getMessage("ETH#msg4"));

		finished = true;
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (inputSource.getClass() != File.class)
			throw new ComponentException(
					"InputSource hasn't format as expected! Expected: File, Received: "
							+ inputSource.getClass());

		sourceFile = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return outputFile;
	}

	private void readInputStream(BufferedReader br) {
		String s;

		try {
			while ((s = br.readLine()) != null) {
				// System.out.println("[EpcToolsHeuristics] "+s);
				Standardprotocol.getLogger().info(s);
			}
		} catch (IOException e) {
			Standardprotocol.getLogger().error("", e);
		}
	}

	@Override
	public String getDescription(String abbreviation) {
		if(abbreviation.equalsIgnoreCase("de")) {
			String str = "Spezielle Komponente, die zur Ausführung des EPC-Metrik-Programms benötigt wird.";
			
			return str;
		}
		
		String str = "Special component needed for invoking the epc metric program.";
		
		return str;
	}

	@Override
	public boolean isValid() {
		return ToolStore.getTool("EPC-Metrics").isValid();
	}

	@Override
	public String getDisplayName() {
		return "EPC Metric Run";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if(component instanceof DiagramExportComponent)
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
