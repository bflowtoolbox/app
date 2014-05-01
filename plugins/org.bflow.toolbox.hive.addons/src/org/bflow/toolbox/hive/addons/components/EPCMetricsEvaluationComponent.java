package org.bflow.toolbox.hive.addons.components;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IEPCMetricsEvaluationComponent;
import org.bflow.toolbox.hive.addons.utils.ProblemsViewMessage;
import org.eclipse.core.resources.IFile;

/**
 * Implements the {@link IEPCMetricsEvaluationComponent} to evaluate the results
 * of the result file created by the external "EPC metric" program.
 * <p/>
 * It's recommended to set a resource to connect the evaluation informations to
 * a file. There you should use the
 * <code>EPCMetricsEvaluationComponent(IFile resource)</code> as default.
 * <p/>
 * A file object of the result file is expected as input source. If it isn't you
 * will get a <code>ComponentException</code>.<br/>
 * During evaluation the informations are read out of the file and
 * ProblemsViewMessages are constructed and collected. <br/>
 * A vector containing the messages is returned as output by the
 * <code>transformOutput()</code> method.
 * 
 * @author Arian Storch
 * @since 24/10/09
 * @version 07/10/10
 * 
 */
public class EPCMetricsEvaluationComponent implements
		IEPCMetricsEvaluationComponent {
	private File sourceFile;

	private IFile activeResource;

	private boolean finished = false;

	private Vector<ProblemsViewMessage> messages = new Vector<ProblemsViewMessage>();
	
	/**
	 * Default constructor.
	 */
	public EPCMetricsEvaluationComponent() {
	}

	/**
	 * Use this as default constructor.
	 * 
	 * @param resource
	 *            Resource that shall connected with the collected informations.
	 */
	public EPCMetricsEvaluationComponent(IFile resource) {
		setActiveResource(resource);
	}

	@Override
	public void finish() {
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public void init() {
	}

	@Override
	public void invoke() throws ComponentException {
		String fileStream = "";

		try {
			fileStream = FileUtils.readFileToString(sourceFile);
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

		int refValue = 2 ^ n - 1;

		if (notClean != 0)
			messages.add(new ProblemsViewMessage(ProblemsViewMessage.TYPE_INFO,
					"", "not clean", activeResource, activeResource
							.getLocation().toFile().getName(), activeResource
							.getLocation().toFile().getParent()));

		if (notStrongSound == refValue)
			messages.add(new ProblemsViewMessage(ProblemsViewMessage.TYPE_INFO,
					"", "not strong sound", activeResource, activeResource
							.getLocation().toFile().getName(), activeResource
							.getLocation().toFile().getParent()));

		if (notWeakSound == refValue)
			messages.add(new ProblemsViewMessage(ProblemsViewMessage.TYPE_INFO,
					"", "NotWeakSound: " + notWeakSound, activeResource,
					activeResource.getLocation().toFile().getName(),
					activeResource.getLocation().toFile().getParent()));

		finished = true;
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (inputSource.getClass() != File.class)
			throw new ComponentException(
					"Input Source hasn't expected data structure");

		this.sourceFile = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return messages;
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

	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Erzeugt aus den Meldungen des EPC-Metrik-Programms Nachrichten für die Problems-View.";

			return str;
		}

		String str = "Generates messages for the Problems View out of the informations created by the epc metric program.";

		return str;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return "EPC metric info analysis";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof EPCMetricsRunComponent)
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
