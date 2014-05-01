package org.bflow.toolbox.hive.addons.utils;

import java.util.Vector;

import com.declarativa.interprolog.PrologOutputListener;
import com.declarativa.interprolog.util.OutputListener;

/**
 * Defines a listener for the prolog output stream.
 * 
 * @author Arian Storch
 * @since 24/06/10
 * @version 09/02/13
 */
public class PrologListener implements OutputListener, PrologOutputListener 
{	
	private boolean hasTerminatedByException = false;
	private boolean streamFinished = false;
	private boolean hasNoStartCommand = false;
	
	private String endCommand;
	private StringBuffer wholeStream;

	/**
	 * Default constructor
	 * @param storage message storage
	 * @deprecated
	 */
	public PrologListener(Vector<String> storage, String endCommand) {
		super();
		this.endCommand = endCommand;
	}
	
	/**
	 * Constructor.
	 * @param stream StringBuffer that records the input stream
	 * @param endCommand prolog end stream command
	 */
	public PrologListener(StringBuffer stream, String endCommand) {
		super();
		this.wholeStream = stream;
		this.endCommand = endCommand;
	}
	
	/**
	 * Resets the the listener to its initial state.
	 */
	public void reset() {
		hasTerminatedByException = false;
		streamFinished = false;
		hasNoStartCommand = false;
		wholeStream.setLength(0);
	}
	
	/**
	 * Returns true if the program did not run because the missing start command.
	 * @return true if the program did not run
	 */
	public boolean hasNoStartCommand() {
		return hasNoStartCommand;
	}
	
	/**
	 * Returns true if the prolog output stream finished.
	 * @return true or false
	 */
	public boolean isStreamFinished() {
		return streamFinished;
	}
	
	/**
	 * Returns true if the program was terminated by an exception.
	 * @return true or false
	 */
	public boolean hasTerminatedByException() {
		return hasTerminatedByException;
	}

	@Override
	public void analyseBytes(byte[] arg0, int arg1) {
		String stream = "";
		
		for(int i = 0; i < arg1; i++)
				stream += (char)arg0[i];
				
		//System.err.println(arg1);
		//System.out.println("("+(i++)+"): "+arg1+" byte");
		//System.err.println(stream);
		
		//if(stream.startsWith("bflow*:"))
		//{
			if(stream.contains("?-"))
				stream = stream.replace("?-", "");
			
			//stream = stream.replace("\n", "").replace("\r", "");
			
			// Something went wrong
			if(stream.contains("^  Exception")) {
				hasTerminatedByException = true;
				streamFinished = true;
			}
			
			if(stream.contains("halt(1)")) {
				hasNoStartCommand = true;
				streamFinished = true;
			}
			
			// Normal termination
			if(stream.contains(endCommand)) {
				stream = stream.replace(endCommand, "");
				streamFinished = true;
			}
			
			wholeStream.append(stream);
			
			/*String lines[] = stream.split("#FS#");
								
			for(String line:lines)
			{
				storage.add(line);
				
				//System.err.println(line);
			}
		//}*/
		
	}

	@Override
	public void streamEnded() {
//		System.err.println("STREAM ENDED");
	}

	@Override
	public void print(String arg0) 	{
//		System.out.println("--->"+arg0);
	}

}
