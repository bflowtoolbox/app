package org.bflow.toolbox.hive.addons.core.exceptions;

/**
 * Defines a basic exception for mitamm.
 * @author Arian Storch
 * @since 30/09/09
 * @version 23/09/10
 *
 */
public class ProtocolException extends Exception {

	/**
	 * generated serial version uid
	 */
	private static final long serialVersionUID = -3793549200361981287L;
	
	/**
	 * constructor
	 */
	public ProtocolException() 
	{
		super();
	}
	
	/**
	 * constructor
	 * @param msg detail message
	 */
	public ProtocolException(String msg)
	{
		super(msg);
	}
	
	/**
	 * Constructor<p/>
	 * Wraps a throwable Object or Exception into a ProtocolException
	 * @param t throwable Object or Exception
	 */
	public ProtocolException(Throwable t)
	{
		super(t);
	}
	
	/**
	 * Constructor<p/>
	 * Wraps a throwable Object or Exception into a ProtocolException
	 * @param msg detail message
	 * @param t throwable Object or Exception
	 */
	public ProtocolException(String msg, Throwable t)
	{
		super(msg, t);
	}

}
