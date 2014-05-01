package org.bflow.toolbox.contributions.addons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.addons.AddonPlugin;
import org.bflow.toolbox.hive.addons.components.DiagramExportComponent;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.store.ToolStore;

/**
 * 
 * @author Arian Storch
 *@deprecated
 */
public class EPCMetricsRunComponent implements IComponent {
	private String exePath;
	private File outputFolder;
	private File outputFile;
	private File sourceFile;
	private boolean finished = false;

	public void finish() {
		if (this.outputFile != null) {
			this.outputFile.deleteOnExit();
			this.outputFile.getParentFile().deleteOnExit();
		}

		if (this.outputFolder != null)
			this.outputFolder.deleteOnExit();
	}

	public boolean hasFinished() {
		return this.finished;
	}

	public void init() {
		this.exePath = ToolStore.getTool("EPC-Metrics").getPath();
		try {
			this.outputFolder = File.createTempFile("EpcToolsHeuristicsOutput",
					"");
			this.outputFolder.delete();

			this.outputFolder.deleteOnExit();
		} catch (IOException ex) {
			AddonPlugin.getInstance().logError(
					"could not create temp ouput folder", ex);
		}
	}

	public void invoke() throws ComponentException {
		if (this.exePath.isEmpty()) {
			throw new ComponentException(
					"Es ist kein Pfad zum Programm gesetzt!");
		}
		String installPath = FilenameUtils.getFullPath(this.exePath);

		String cpPath = "\"%CLASSPATH%;" + installPath + "lib" + File.separator
				+ "*;" + installPath + "*\"";

		String command = "java -Xmx900m -cp " + cpPath;
		command = command
				+ " \"de.ulpz.ebus.epc.metrics.BatchMetricCalculator\"";
		command = command + " -r -s -i " + this.sourceFile.getAbsolutePath();
		command = command + " -o " + this.outputFolder.getAbsolutePath();
		command = command + " -c " + installPath + "sap.conf";
		try {
			AddonPlugin.getInstance()
					.logInfo("[EpcToolsHeuristics] " + command);

			Process p = Runtime.getRuntime().exec(command);

			BufferedReader br = new BufferedReader(new InputStreamReader(p
					.getInputStream()));

			boolean running = true;

			while (running) {
				try {
					readInputStream(br);

					p.exitValue();

					readInputStream(br);

					running = false;
				} catch (IllegalThreadStateException localIllegalThreadStateException) {
					running = true;
				}
			}
		} catch (IOException ex) {
			throw new ComponentException("Could not run the command: "
					+ command, ex);
		}

		String baseLine = this.outputFolder.getAbsolutePath()
				+ System.getProperty("file.separator")
				+ this.sourceFile.getName();

		if (baseLine.indexOf(".tmp") > -1)
			baseLine = baseLine.replace(".temp", "_result.xml");
		else {
			baseLine = baseLine.replace("epml", "epm_result.xml");
		}

		String help = this.outputFolder.getAbsolutePath() + "/"
				+ this.sourceFile.getName().replace(".tmp", "_result.xml");

		this.outputFile = new File(baseLine);

		if (!(this.outputFile.exists())) {
			this.outputFile = new File(help);
		}

		this.finished = true;
	}

	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null) {
			throw new ComponentException("Quelle ist null");
		}
		if (inputSource.getClass() != File.class) {
			throw new ComponentException(
					"InputSource hasn't format as expected! Expected: File, Received: "
							+ inputSource.getClass());
		}
		this.sourceFile = ((File) inputSource);
	}

	public Object transformOutput() throws ComponentException {
		return this.outputFile;
	}

	private void readInputStream(BufferedReader br) {
		try {
			String s;
			while ((s = br.readLine()) != null) {
				// String s;
				AddonPlugin.getInstance().logInfo(s);
			}
		} catch (IOException e) {
			AddonPlugin.getInstance().logError("", e);
		}
	}

	public String getDescription(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Spezielle Komponente, die zur Ausführung des EPC-Metrik-Programms benötigt wird.";

			return str;
		}

		String str = "Special component needed for invoking the epc metric program.";

		return str;
	}

	public boolean isValid() {
		return ToolStore.getTool("EPC-Metrics").isValid();
	}

	public String getDisplayName() {
		return "EPC metric run";
	}

	public boolean canLinkWith(IComponent component) {
		return (component instanceof DiagramExportComponent);
	}

	public boolean hasParams() {
		return false;
	}

	public void setParams(String param) {
	}
}
