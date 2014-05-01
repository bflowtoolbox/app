package org.bflow.toolbox.hive.interchange.events;

import java.io.File;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;

/**
 * Defines an export event. That are events occurring during an export process.
 * 
 * @author Arian Storch
 * @since 23/06/11
 * @version 03/10/12
 */
public class ExportEvent {

	/**
	 * The export starts now.
	 */
	public static int START = 0;

	/**
	 * An export step is done.
	 */
	public static int STEP_DONE = 1;

	/**
	 * The whole export process has succeeded without errors.
	 */
	public static int EXPORT_DONE = 2;

	/**
	 * An error or exception occurred.
	 */
	public static int BROKEN = 3;

	/**
	 * Flag to perform an insert attribute process.
	 */
	public static int INSERT_ATTRIBUTES = 4;

	/**
	 * Export step count. If the export process starts or ends it is -1.
	 */
	public int step;

	/**
	 * Export event type. Look at class constants.
	 */
	public int type;

	/**
	 * Running export description.
	 */
	public IInterchangeDescriptor exportDescriptor;

	/**
	 * Pointer to the source file.
	 */
	public File sourceFile;

	/**
	 * Pointer to the target file.
	 */
	public File targetFile;

}
