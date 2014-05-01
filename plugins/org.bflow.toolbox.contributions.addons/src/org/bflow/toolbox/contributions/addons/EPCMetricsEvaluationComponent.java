package org.bflow.toolbox.contributions.addons;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.utils.ProblemsViewMessage;
import org.eclipse.core.resources.IFile;

/**
 * 
 * @author Arian Storch
 * @deprecated
 */
public class EPCMetricsEvaluationComponent implements IComponent {
	private File sourceFile;
	private IFile activeResource;
	private boolean finished = false;

	private Vector<ProblemsViewMessage> messages = new Vector<ProblemsViewMessage>();

	public EPCMetricsEvaluationComponent() {
	}

	public EPCMetricsEvaluationComponent(IFile resource) {
		setActiveResource(resource);
	}

	public void finish() {
	}

	public boolean hasFinished() {
		return this.finished;
	}

	public void init() {
	}

	public void invoke() throws ComponentException {
		String fileStream = "";
		try {
			fileStream = FileUtils.readFileToString(this.sourceFile);
		} catch (IOException ex) {
			throw new ComponentException(ex);
		}

		int notClean = Integer.parseInt(getValue(fileStream, "notclean"));
		int notStrongSound = Integer.parseInt(getValue(fileStream,
				"notstrongsound"));
		int notWeakSound = Integer
				.parseInt(getValue(fileStream, "notweaksound"));
		String startEvents = getValue(fileStream, "startevents");

		int n = Integer.parseInt(startEvents);

		int refValue = 0x2 ^ n - 1;

		if (notClean != 0) {
			this.messages.add(new ProblemsViewMessage(1, "", "not clean",
					this.activeResource, this.activeResource.getLocation()
							.toFile().getName(), this.activeResource
							.getLocation().toFile().getParent()));
		}
		if (notStrongSound == refValue) {
			this.messages.add(new ProblemsViewMessage(1, "",
					"not strong sound", this.activeResource,
					this.activeResource.getLocation().toFile().getName(),
					this.activeResource.getLocation().toFile().getParent()));
		}
		if (notWeakSound == refValue) {
			this.messages.add(new ProblemsViewMessage(1, "", "NotWeakSound: "
					+ notWeakSound, this.activeResource, this.activeResource
					.getLocation().toFile().getName(), this.activeResource
					.getLocation().toFile().getParent()));
		}
		this.finished = true;
	}

	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null) {
			throw new ComponentException("Quelle ist null");
		}
		if (inputSource.getClass() != File.class) {
			throw new ComponentException(
					"Input Source hasn't expected data structure");
		}
		this.sourceFile = ((File) inputSource);
	}

	public Object transformOutput() throws ComponentException {
		return this.messages;
	}

	private String getValue(String fileStream, String attribute) {
		int index = fileStream.indexOf(attribute + " value=\"")
				+ attribute.length() + 8;
		int indexEnd = fileStream.indexOf("\"", index);

		return fileStream.substring(index, indexEnd);
	}

	public void setActiveResource(IFile activeResource) {
		this.activeResource = activeResource;
	}

	public String getDescription(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Erzeugt aus den Meldungen des EPC-Metrik-Programms Nachrichten für die Problems-View.";

			return str;
		}

		String str = "Generates messages for the Problems View out of the informations created by the epc metric program.";

		return str;
	}

	public boolean isValid() {
		return true;
	}

	public String getDisplayName() {
		return "EPC metric info analysis";
	}

	public boolean canLinkWith(IComponent component) {
		return (component instanceof EPCMetricsRunComponent);
	}

	public boolean hasParams() {
		return false;
	}

	public void setParams(String param) {
	}
}
