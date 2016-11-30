package org.bflow.toolbox.hive.addons.core.model;

import java.io.IOException;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.exceptions.ProtocolException;

/**
 * Defines an interface for an add-on component.
 * 
 * @author Arian Storch
 * @since 29/09/09
 * @version 12/08/12
 */
public interface IComponent {
	
	/**
	 * This method is called first when a new component will be processed. Use
	 * this to do basic initial stuff.
	 * @throws IOException 
	 */
	public void init() throws ProtocolException;

	/**
	 * You can use this method to manipulate the input source of the component
	 * or to simple get a pointer to the source. It will be called after
	 * <code>init()</code>.
	 * 
	 * @param inputSource
	 *            input source
	 * @throws ComponentException
	 */
	public void transformInput(Object inputSource) throws ComponentException;

	/**
	 * After calling <code>transformInput()</code> the component will invoked by
	 * this. Implement all necessary things that fulfills the task of the
	 * component here. This can also be a call for an external program.
	 * 
	 * @throws ComponentException
	 */
	public void invoke() throws ComponentException;

	/**
	 * After invoking the component this method is called. You can use this to
	 * evaluate or manipulate data for the next component.
	 * <p/>
	 * <i>Note: </i> The return object is the input source for the next
	 * component. It mustn't be null otherwise you will get an exception!
	 * 
	 * @return object used as input for the next component
	 * @throws ComponentException
	 */
	public Object transformOutput() throws ComponentException;

	/**
	 * This method is called after <code>transformOutput()</code> and can be
	 * used for some cleaning up.
	 */
	public void finish();

	/**
	 * Returns true if the component has been finished or processed.
	 * 
	 * @return true if the component has been finished or processed.
	 */
	public boolean hasFinished();

	/**
	 * Returns a description of this component.
	 * 
	 * @param abbreviation
	 *            language abbreviation of the description
	 * @return description of this component
	 */
	public String getDescription(String abbreviation);

	/**
	 * Returns an application user friendly name to display and identify.
	 * 
	 * @return user friendly name to display
	 */
	public String getDisplayName();

	/**
	 * Returns true if the component is valid and can be used.
	 * 
	 * @return true or false
	 */
	public boolean isValid();

	/**
	 * Returns true if the component needs parameters for execution.
	 * 
	 * @return true or false
	 */
	public boolean hasParams();

	/**
	 * Sets the param for this component.
	 * 
	 * @param param
	 *            param string
	 */
	public void setParams(String param);

	/**
	 * Returns true if the component can linked with another component.
	 * 
	 * @param component
	 *            component
	 * @return true or false
	 */
	public boolean canLinkWith(IComponent component);
}
