package org.bflow.toolbox.hive.addons.core.utils;

import java.util.Vector;

/**
 * This class defines a MessageCollector which can be used to collect messages during the
 * execution of a protocol.  
 * @author Arian Storch
 * @since 27/10/09
 * @version 23/09/10
 *
 */
public class MessageCollector 
{
	// holds the static instance
	private static MessageCollector instance = new MessageCollector();
	
	// holds the messages
	private Vector<String> messages = new Vector<String>();
	
	/**
	 * Adds a message to the collector.
	 * @param msg Message that shall be added.
	 */
	public void addMessage(String msg)
	{
		this.messages.add(msg);
	}
	
	/**
	 * Returns a vector containing all collected messages.
	 * @return Vector containing all collected messages.
	 */
	public Vector<String> getMessages()
	{
		return this.messages;
	}
	
	/**
	 * Returns the all collected messages as text stream. Messages are divided by "\n".
	 * @return Collected messages as text stream.
	 */
	public String getText()
	{
		String text = "";
		
		for(String msg:messages)
			text += msg+"\n";
		
		return text;
	}
	
	/**
	 * Returns the instance of the MessageCollector.
	 * @return Instance of the MessageCollector.
	 */
	public static MessageCollector getInstance()
	{
		return instance;
	}

}
