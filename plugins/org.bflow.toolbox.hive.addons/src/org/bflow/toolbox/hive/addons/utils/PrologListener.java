package org.bflow.toolbox.hive.addons.utils;

import org.apache.commons.lang3.StringUtils;
import com.declarativa.interprolog.PrologOutputListener;
import com.declarativa.interprolog.util.OutputListener;

/**
 * Defines a listener for the prolog output stream.
 * 
 * @author Arian Storch <arian.storch@bflow.org>
 * @since 2010-06-24
 * @version 2013-02-09
 * 			2020-06-12 Updated to latest message format (SWI prolog 8.2.0)
 */
public class PrologListener implements OutputListener, PrologOutputListener {	
	private StringBuilder _stringBuilder = new StringBuilder(1024);
	private boolean _hasTerminatedByException = false;
	private boolean _streamFinished = false;
	private boolean _hasNoStartCommand = false;
	
	private String _endCommand;
	private StringBuffer _stream;	
	
	/**
	 * Constructor.
	 * 
	 * @param stream      StringBuffer that records the input stream
	 * @param _endCommand prolog end stream command
	 */
	public PrologListener(StringBuffer stream, String endCommand) {
		super();
		_stream = stream;
		_endCommand = endCommand;
	}
	
	/**
	 * Resets the the listener to its initial state.
	 */
	public void reset() {
		_hasTerminatedByException = false;
		_streamFinished = false;
		_hasNoStartCommand = false;
		_stream.setLength(0);
	}
	
	/**
	 * Returns true if the program did not run because the missing start command.
	 * 
	 * @return true if the program did not run
	 */
	public boolean hasNoStartCommand() {
		return _hasNoStartCommand;
	}
	
	/**
	 * Returns true if the prolog output stream finished.
	 * 
	 * @return true or false
	 */
	public boolean isStreamFinished() {
		return _streamFinished;
	}
	
	/**
	 * Returns true if the program was terminated by an exception.
	 * 
	 * @return true or false
	 */
	public boolean hasTerminatedByException() {
		return _hasTerminatedByException;
	}

	/*
	 * (non-Javadoc)
	 * @see com.declarativa.interprolog.util.OutputListener#analyseBytes(byte[], int)
	 */
	@Override
	public void analyseBytes(byte[] buffer, int bufSz) {
		_stringBuilder.setLength(0);
				
		for (int i = -1; ++i != bufSz;) {
			char c = (char) buffer[i];
			if (c == '\'') continue;
			_stringBuilder.append(c);
		}
		
		String prologMessage = _stringBuilder.toString();
		
		// Sanitize
		prologMessage = prologMessage.replace("?-", StringUtils.EMPTY);

		// Something went wrong
		if (prologMessage.contains("^  Exception")) {
			_hasTerminatedByException = true;
			_streamFinished = true;
		}
			
		if (prologMessage.contains("halt(1)")) {
			_hasNoStartCommand = true;
			_streamFinished = true;
		}
			
		// Normal termination
		if (prologMessage.contains(_endCommand)) {
			prologMessage = prologMessage.replace(_endCommand, StringUtils.EMPTY);
			_streamFinished = true;
		}
		
		_stream.append(prologMessage);					
	}

	/*
	 * (non-Javadoc)
	 * @see com.declarativa.interprolog.util.OutputListener#streamEnded()
	 */
	@Override
	public void streamEnded() {
//		System.err.println("STREAM ENDED");
	}

	/*
	 * (non-Javadoc)
	 * @see com.declarativa.interprolog.PrologOutputListener#print(java.lang.String)
	 */
	@Override
	public void print(String arg0) 	{
//		System.out.println("--->"+arg0);
	}

}
