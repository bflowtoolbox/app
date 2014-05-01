package org.bflow.toolbox.hive.interchange.mif.core;

import java.io.File;

/**
 * Defines an interface for listening to the interchange process.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 18/07/13
 * @version 23/07/13
 */
public interface IInterchangeProcessListener {
	
	/**
	 * Provides an implementation of {@link IInterchangeProcessListener} that does nothing.
	 */
	public static final IInterchangeProcessListener DeafProcessListener = 
			new IInterchangeProcessListener() {
				
				@Override
				public void beforeProcessing(File sourceFile, File targetFile) {					
				}
				
				@Override
				public void afterProcessing(File sourceFile, File targetFile) {					
				}

				@Override
				public void onInitialize(InterchangeProcessInfo interchangeProcessInfo) {					
				}

				@Override
				public void onFinished(InterchangeProcessInfo interchangeProcessInfo) {					
				}
			};
			
	/**
	 * Will be called just after the interchange process service has been
	 * initialized.
	 * 
	 * @param interchangeProcessInfo
	 *            The interchange process info
	 */
	public void onInitialize(InterchangeProcessInfo interchangeProcessInfo);

	/**
	 * Will be called just before the interchange process will start.
	 * 
	 * @param sourceFile
	 *            Source file
	 * @param targetFile
	 *            Target file
	 */
	public void beforeProcessing(File sourceFile, File targetFile);

	/**
	 * Will be called just after the interchange process has been finished
	 * successfully.
	 * 
	 * @param sourceFile
	 *            Source file
	 * @param targetFile
	 *            Target file
	 */
	public void afterProcessing(File sourceFile, File targetFile);
	
	/**
	 * Will be called just after the interchange process service has been
	 * finished.
	 * 
	 * @param interchangeProcessInfo
	 *            The interchange process info
	 */
	public void onFinished(InterchangeProcessInfo interchangeProcessInfo);

}
