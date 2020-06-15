package org.bflow.toolbox.hive.addons.components;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IAttributeAdjustComponent;
import org.bflow.toolbox.hive.addons.utils.AttributeAdjustMessage;
import org.bflow.toolbox.hive.addons.utils.ImplementationProvider;
import org.bflow.toolbox.hive.attributes.IAttribute;

/**
 * Implements the {@link IAttributeAdjustComponent} interface.
 * @author Arian Storch
 * @since 08/10/10
 * @version 15/10/10
 */
public class AttributeAdjustComponent implements IAttributeAdjustComponent {

	private boolean finished;

	private List<IAddonMessage> consoleLines;

	private Protocol protocol;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#canLinkWith(org.bflow.
	 * toolbox.mitamm.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof ShellAnalysisComponent)
			return true;

		if (component instanceof FileAnalysisComponent)
			return true;

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#getDescription(java.lang
	 * .String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Ändert, erzeugt oder löscht Attribute des Modells, falls das Programm entsprechende Meldungen erzeugt hat.";

			return str;
		}

		String str = "Edits, creates or deletes attributes of the model if the program has been created corresponding messages.";

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Attribute Adjust";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#init()
	 */
	@Override
	public void init() {
		finished = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		try {

			List<IAttribute> list = new ArrayList<IAttribute>();

			for (IAddonMessage mm : consoleLines) {
				if (mm instanceof AttributeAdjustMessage) {
					AttributeAdjustMessage aam = (AttributeAdjustMessage) mm;

					list.add(aam.getAttribute());
				}
			}

			ImplementationProvider.setAttributes(this.protocol, list);
			
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#setParams(java.lang.String
	 * )
	 */
	@Override
	public void setParams(String param) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#transformInput(java.lang
	 * .Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");

		if (!(inputSource instanceof List))
			throw new ComponentException(
					"Input source has not expected format!");

		List<?> l = (List<?>) inputSource;

		if (!l.isEmpty()) {
			Object o = l.get(0);

			if (!(o instanceof IAddonMessage))
				throw new ComponentException("information class is not known!");
		}

		this.consoleLines = (List<IAddonMessage>) l;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		return this.consoleLines;
	}

	/**
	 * Sets the protocol.
	 * 
	 * @param protocol
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

}
