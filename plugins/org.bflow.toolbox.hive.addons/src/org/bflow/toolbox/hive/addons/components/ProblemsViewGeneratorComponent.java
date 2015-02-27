package org.bflow.toolbox.hive.addons.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IProblemsViewGeneratorComponent;
import org.bflow.toolbox.hive.addons.services.MitammMarkerService;
import org.bflow.toolbox.hive.addons.utils.ProblemsViewMessage;
import org.eclipse.core.resources.IMarker;

/**
 * This component implements the {@link IProblemsViewGeneratorComponent} and can
 * be used to put some messages to the Eclipse Problems View.
 * <p/>
 * A Vector of ProblemsViewMessage is expected as input source. If you don't
 * transmit this data structure you will get a <code>ComponentException</code>.
 * <p/>
 * As output source you will get a vector containing the created markers. So you
 * can work with it further.
 * 
 * @author Arian Storch
 * @since 12/10/09
 * @version 07/04/13
 */
public class ProblemsViewGeneratorComponent implements IProblemsViewGeneratorComponent {
	
	/**
	 * Collection of messages
	 */
	private List<IAddonMessage> messages = new ArrayList<IAddonMessage>();
	
	/**
	 * Collection of markers
	 */
	private Vector<IMarker> createdMarkers = new Vector<IMarker>();

	/**
	 * finished flag
	 */
	private boolean finished = false;

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
		try {

			for (IAddonMessage mm : messages) {
				if (mm instanceof ProblemsViewMessage) {
					ProblemsViewMessage pvm = (ProblemsViewMessage) mm;

					createdMarkers.add((IMarker) MitammMarkerService.addMarker(
							pvm.getResource(),
							org.bflow.toolbox.hive.addons.utils.IdResolver.getId(pvm
									.getResource(), 
							pvm.getElementID()), 
							pvm.getLocation(), 
							pvm.getDescription(), 
							pvm.getMessageType()));
				}
			}
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Input source is null!");
		
		if (!(inputSource instanceof List)) {
			String listType = List.class.toString();
			String inputType = inputSource.getClass().toString();
			String message = String.format("Input source hasn't expected data structure." +
					" Expected '%s', received: '%s'", listType, inputType);
			
			throw new ComponentException(message);
		}

		List<?> v = (List<?>) inputSource;

		if (v.size() == 0) {
			this.messages = new ArrayList<IAddonMessage>();
			return;
		}

		if (!(v.get(0) instanceof IAddonMessage)) {
			String typeExpected = IAddonMessage.class.toString();
			String typeFound = v.get(0).getClass().toString();
			String message = String.format("Information class type within the input source is not known! " +
					"Expected: '%s', found '%s'", typeExpected, typeFound);
			
			throw new ComponentException(message);
		}

		this.messages = (List<IAddonMessage>) v;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return createdMarkers;
	}

	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Erzeugt aus ermittelten Rückmeldungen von externen Programmen Nachrichten, die in der"
					+ " Problems-View angezeigt werden.";

			return str;
		}

		String str = "Generates messages out of the informations that has been passed to bflow* by the external programs. "
				+ "These messages will be shown within the problems view.";

		return str;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return "Problems view display";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ShellAnalysisComponent)
			return true;

		if (component instanceof FileAnalysisComponent)
			return true;

		if (component instanceof AttributeAdjustComponent)
			return true;

		if (component instanceof EPCMetricsEvaluationComponent)
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
