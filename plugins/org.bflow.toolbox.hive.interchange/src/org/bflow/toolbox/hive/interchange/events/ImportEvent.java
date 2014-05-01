package org.bflow.toolbox.hive.interchange.events;

import java.io.File;

import org.bflow.toolbox.hive.interchange.model.XMLBasedImportDescriptor;

/**
 * Defines an import event. That are events occurring during an import process. 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/07/11
 */
@SuppressWarnings("deprecation")
public class ImportEvent {
	
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
	public static int IMPORT_DONE = 2;
	
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
	public XMLBasedImportDescriptor importDescription;
	
	/**
	 * Pointer to the source file.
	 */
	public File sourceFile;
	
	/**
	 * Pointer to the target file.
	 */
	public File targetFile;

}
