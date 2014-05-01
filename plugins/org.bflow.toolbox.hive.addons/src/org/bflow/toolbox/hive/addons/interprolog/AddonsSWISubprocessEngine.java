package org.bflow.toolbox.hive.addons.interprolog;

import java.io.IOException;

import com.declarativa.interprolog.SWISubprocessEngine;

/**
 * Extends {@link SWISubprocessEngine} to fix the bug that the original 
 * implementation could not handle paths with whitespaces.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 22/07/13
 */
public class AddonsSWISubprocessEngine extends SWISubprocessEngine {
		
	/**
	 * Instantiates a new SWI subprocess engine.
	 *
	 * @param prologCommand the prolog command
	 * @param debug the debug
	 */
	public AddonsSWISubprocessEngine(String prologCommand, boolean debug) {
		super(prologCommand, debug);
	}

	/* (non-Javadoc)
	 * @see com.declarativa.interprolog.SubprocessEngine#createProcess(java.lang.String)
	 */
	@Override
	protected Process createProcess(String prologCommand) throws IOException {
		progressMessage("Launching subprocess "+prologCommand);
		ProcessBuilder processBuilder = new ProcessBuilder(prologCommand);
		return processBuilder.start();
	}

}
