package org.bflow.toolbox.hive.addons.core.exceptions;

/**
 * Defines a component exception for an Add-on operation.
 * 
 * @author Arian Storch
 * @since 29/09/09
 * @version 23/09/10
 * 
 */
public class ComponentException extends ProtocolException {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -4202556327327447033L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            detail message
	 */
	public ComponentException(String msg) {
		super(msg);
	}

	/**
	 * Constructor.
	 * <p/>
	 * Wraps a throwable Object or Exception into a ComponentException.
	 * 
	 * @param t
	 *            throwable Object or Exception
	 */
	public ComponentException(Throwable t) {
		super(t);
	}

	/**
	 * Constructor.
	 * <p/>
	 * Wraps a throwable Object or Exception into a ComponentException.
	 * 
	 * @param msg
	 *            detail message
	 * @param t
	 *            throwable Object or Exception
	 */
	public ComponentException(String msg, Throwable t) {
		super(msg, t);
	}

}
