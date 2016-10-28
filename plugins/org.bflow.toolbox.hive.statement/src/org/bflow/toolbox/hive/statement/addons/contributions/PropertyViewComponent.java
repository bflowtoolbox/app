package org.bflow.toolbox.hive.statement.addons.contributions;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.addons.components.FileAnalysisComponent;
import org.bflow.toolbox.hive.addons.components.ShellAnalysisComponent;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.utils.PropertyViewResultMessage;
import org.bflow.toolbox.hive.statement.views.StatementView;

/**
 * This component implements the {@link IPropertyViewComponent} and can
 * be used to put property result to the PropertyView.
 * <p/>
 * A Vector of PropertyViewResultMessage is expected as input source. If you don't
 * transmit this data structure you will get a <code>ComponentException</code>.
 * <p/>
 * 
 * @author Markus Schnädelbach
 */
public class PropertyViewComponent implements IComponent {
	
	/**
	 * Collection of messages
	 */
	private List<IAddonMessage> messages = new ArrayList<IAddonMessage>();
	
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
		StatementView sv = StatementView.getInstance();
		sv.clearResult();
		
		for (int i = 0; i < messages.size(); i++) {
			IAddonMessage mm = messages.get(i);
			if (mm instanceof PropertyViewResultMessage) {
				PropertyViewResultMessage pvrm = (PropertyViewResultMessage) mm;
				sv.setResult(i, pvrm.getResult());
			}
		}
		finished = true;
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
		return messages;
	}

	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Erzeugt aus ermittelten Rückmeldungen von externen Programmen Nachrichten, die von der"
					+ " Property-View ausgewertet und dargestellt werden.";

			return str;
		}

		String str = "Generates messages out of the informations that has been passed to bflow* by the external programs. "
				+ "These messages will be analyzed and displayed within the property view.";

		return str;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return "Property view display";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ShellAnalysisComponent)
			return true;

		if (component instanceof FileAnalysisComponent)
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
