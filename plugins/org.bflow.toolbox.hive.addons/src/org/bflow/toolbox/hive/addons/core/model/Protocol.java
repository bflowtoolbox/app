package org.bflow.toolbox.hive.addons.core.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.exceptions.ProtocolException;
import org.bflow.toolbox.hive.addons.core.utils.MessageCollector;

/**
 * Defines a protocol that allows to call and run various types of classes,
 * tools or external programs. You can handle appearing exceptions, modify input
 * and output sources and decide on each step what happens next.
 * <p/>
 * To run a protocol create some components using the <code>IComponent</code>
 * interface and add them to the protocol using the <code>addComponent()</code>
 * method. Afterwards you have to decide if you want to run the protocol as
 * thread or not. The default state is NOT. You can change this by using the
 * <code>setThread()</code> method. To finally start the protocol by calling
 * <code>start()</code>.
 * <p/>
 * When the protocol starts <code>init()</code> is called first. Subclasses have
 * to implement this method and can use this to do basic initial stuff if
 * necessary.<br/>
 * Next, within every component <code>init()</code>,
 * <code>transformInput()</code>, <code>invoke()</code>,
 * <code>transformOutput()</code> and <code>finish()</code> are called in order.
 * You can use the transform methods to manipulate the in- and output sources or
 * check if they have the expected data format.
 * <p/>
 * The components are called in order they have been added.
 * <p/>
 * After every component has been invoked it is checked by the implementation of
 * <code>returnToSenderAfterFinish()</code> if last output source is handled as
 * new input source for the initiator of the protocol. If so,
 * <code>handleReturn()</code> is called. You can use this for evaluating the
 * return of the last component.
 * <p/>
 * Appearing exceptions will basically wrapped in <code>ProtocolException</code>.
 * <p/>
 * If an exception appears you can decide for every component if the chain shall
 * continue or break. For the decision the implementation of
 * <code>continueThoughException()</code> is called. Within this you can
 * individually decide how to react to this. You can even continue the chain by
 * returning <code>true</code>.
 * <p/>
 * The protocol does the whole stuff within the <code>run()</code> method. You
 * can subclass it, but it's not intended.
 * <p/>
 * After the protocol is done and all components have been processed,
 * <code>finish()</code> is called. You can use this method to do some cleaning
 * up if desired.
 * <p/>
 * During the execution of the protocol you can use a logger instance (apache
 * log4j) to log any information you want. Because the logger instance is static
 * you can get it by calling <code>Protocol.getLogger()</code>. You can even use
 * your own implementation of logger, so set it by invoking
 * <code>Protocol.setLogger()</code>. It's recommended to do this first, e.g.
 * during the init phase.
 * <p/>
 * An alternative way to log or collect some messages during the execution you
 * can use the {@link MessageCollector}. It's a static instance to collect any
 * information you want. Call it by <code>Protocol.getMessageCollector()</code>.
 * 
 * @author Arian Storch
 * @since 29/09/09
 * @version 28/04/13
 */
public abstract class Protocol implements Runnable {
	/**
	 * flag that shows if the protocol is running
	 */
	private boolean running = false;

	/**
	 * flag that shows if the protocol is in progress
	 */
	private boolean finished = false;

	/**
	 * flag that sets the protocol as thread or not
	 */
	private boolean thread = false;

	/**
	 * holds the instance of the thread
	 */
	private Thread instance;

	/**
	 * source object for the chain respectively input source of the first
	 * component
	 */
	private Object source;

	/**
	 * parameter string for execution of the protocol.
	 */
	private String parameter;

	/**
	 * Data structure that holds components in order
	 */
	private List<IComponent> components = new ArrayList<IComponent>();

	/**
	 * description of the protocol
	 */
	private String description;

	/**
	 * holds component parameters
	 */
	private HashMap<IComponent, String> paramList = new HashMap<IComponent, String>();

	/**
	 * Instance of a MessageCollector
	 */
	private static MessageCollector messageCollector = new MessageCollector();

	/**
	 * Instance of a Logger
	 */
	private static Logger logger = Logger.getLogger(Protocol.class);

	/**
	 * Is called after the protocol has been started. Use this method to do
	 * basic initial stuff.
	 */
	protected abstract void init();

	/**
	 * Is called after the protocol has been finished. Use this method to do
	 * some cleaning up if desired.
	 */
	protected abstract void finish();

	/**
	 * If you wish to return the output of the last component to the initiator
	 * eg. for evaluating, you must return <code>true</code> otherwise
	 * <code>false</code>.
	 * <p/>
	 * <i>Note: </i>If you return true you have to implement
	 * <code>handleReturn()</code> wisely. It is invoked afterwards.
	 * 
	 * @return true or false
	 */
	protected abstract boolean ReturnToSenderAfterFinish();

	/**
	 * This method is only invoked when <code>returnToSenderAfterFinish()</code>
	 * returns true. So you can use this method to evaluate the output of the
	 * last component.
	 * 
	 * @param object
	 *            output of the last invoked tool chain link
	 */
	protected abstract void handleReturn(Object object);

	/**
	 * If an exception appears this method is called. Within this you can decide
	 * how to react to the exception and also enforce an continuing of the
	 * protocol if desired.
	 * <p/>
	 * For continuing return true else false.
	 * 
	 * @param component
	 *            component, where the exception appeared
	 * @param protex
	 *            exception
	 * @return true if you want to enforce the continuing of the chain otherwise
	 *         false
	 */
	protected abstract boolean continueThoughException(IComponent component,
			ProtocolException protex);

	/**
	 * Proofs if the protocol is valid. An invalid protocol shouldn't be
	 * processed!
	 * 
	 * @return true or false
	 */
	public abstract boolean isValid();

	/**
	 * Returns true if the protocol can be applied to the diagram editor with
	 * the given editor file extension.
	 * 
	 * @param diagramEditorFileExtension file extension of a diagram editor
	 * @return true or false
	 */
	public abstract boolean isApplicableFor(String diagramEditorFileExtension);

	/**
	 * This is the core method of the protocol. Within this every component is
	 * initiated, invoked, finished and also the transforming methods are called.
	 * <p/>
	 * <i>Note: </i>You can subclass this but it's not intended.
	 */
	public void run() {
		this.finished = false;

		Object output = this.source;

		for (int i = 0; i < components.size(); i++) {
			IComponent link = components.get(i);
			
			try {
				link.init();
			} catch (IOException e) {
				logger.error("Error during initialization of the program.", e);
			}

			try {
				link.transformInput(output);

				link.invoke();

				output = link.transformOutput();

				if (output == null)
					throw new ProtocolException(
							"output source must not be null");
			} catch (ProtocolException e1) {
				Protocol.getLogger().error("", e1);

				if (continueThoughException(link, e1)) {
					link.finish();
					continue;
				} else {
					break;
				}
			}

			link.finish();
		}

		if (this.ReturnToSenderAfterFinish())
			this.handleReturn(output);

		this.finished = true;
	}

	/**
	 * Returns true if the protocol has been finished.
	 * 
	 * @return true if the protocol has been finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Sets the protocol as finished.
	 * <p/>
	 * Don't call this manually!
	 * 
	 * @param finished
	 *            true or false
	 */
	protected void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * Returns the parameter string.
	 * 
	 * @return parameter string
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Sets the parameter string.
	 * 
	 * @param parameter
	 *            string
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * Sets the param string of a component.
	 * 
	 * @param component
	 *            component
	 * @param param
	 *            value
	 */
	public void setComponentParam(IComponent component, String param) {
		paramList.put(component, param);
	}

	/**
	 * Returns the param string of a component.
	 * 
	 * @param component
	 *            component
	 * @return value string
	 */
	public String getComponentParam(IComponent component) {
		return paramList.get(component);
	}

	/**
	 * Removes the parameter associated with the component.
	 * 
	 * @param component
	 *            component
	 * @return the remove parameter string
	 */
	public String removeComponentParam(IComponent component) {
		return paramList.remove(component);
	}

	/**
	 * Call this method to start the protocol.
	 * <p/>
	 * If you want to run this as a Thread use <code>setThread()</code>.
	 * 
	 * @throws ProtocolException
	 */
	public void start() throws ProtocolException {
		init();

		if (components.size() == 0) throw new ComponentException("First link hasn't been set.");
		if (source == null) throw new ProtocolException("Protocol source is null");

		if (thread) {
			this.instance = new Thread(this);
			this.instance.setDaemon(true);
			this.instance.start();
		} else
			run();

		finish();
	}

	/**
	 * Adds a new component to the protocol.
	 * <p/>
	 * The order links are added, is the order they are called.
	 * 
	 * @param link
	 */
	public void addComponent(IComponent component) {
		components.add(component);
	}

	/**
	 * Returns the installed components.
	 * 
	 * @return installed components
	 */
	public List<IComponent> getComponents() {
		return components;
	}

	/**
	 * Returns true if the protocol runs as thread else false.
	 * 
	 * @return true of the protocol runs as thread.
	 */
	public boolean isThread() {
		return thread;
	}

	/**
	 * Sets if the protocol shall run as thread or not
	 * <p/>
	 * <i>Note: </i>If the protocol is already running, you can't changed the
	 * state. So nothing will happen.
	 * 
	 * @param b
	 *            true or false
	 */
	public void setThread(boolean b) {
		if (!running)
			this.thread = b;
	}

	/**
	 * Returns the source that is used as input for the first component.
	 * 
	 * @return source that is used as input for the first component
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Sets the source for the first component.
	 * 
	 * @param source
	 *            source for the first component
	 */
	public void setSource(Object source) {
		this.source = source;
	}

	/**
	 * Returns the logger instance used by this protocol.
	 * 
	 * @return logger instance used by this protocol.
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Sets the logger instance used by this protocol.
	 * 
	 * @param log
	 *            Logger instance used by this protocol.
	 */
	public static void setLogger(Logger log) {
		logger = log;
	}

	/**
	 * Returns the {@link MessageCollector} for this protocol.
	 * 
	 * @return MessageCollector for this protcol.
	 */
	public static MessageCollector getMessageCollector() {
		return messageCollector;
	}

	/**
	 * Returns a description of this protocol.
	 * 
	 * @param abbreviation
	 *            language abbreviation of the description
	 * @return
	 */
	public String getDescription(String abbreviation) {
		return description;
	}

	/**
	 * Sets the description of the protocol.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
