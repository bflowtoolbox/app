package org.bflow.toolbox.hive.interchange.mif.core;

import org.bflow.toolbox.hive.interchange.mif.impl.AditusInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.impl.VelocityInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.impl.XSLTInterchangeExportProcessor;
import org.bflow.toolbox.hive.interchange.mif.impl.XSLTInterchangeImportProcessor;
import org.bflow.toolbox.hive.interchange.model.XMLBasedExportDescriptor;
import org.bflow.toolbox.hive.interchange.model.XMLBasedImportDescriptor;

/**
 * Provides a store for different implementations of
 * {@link IInterchangeProcessor}.
 * 
 * @author Arian Storch
 * @since 03/10/12
 * @version 18/07/13
 */
@SuppressWarnings("deprecation")
public class InterchangeProcessorStore {

	/** The Constant XSLTExportProcessor. */
	private static final IInterchangeProcessor XSLTExportProcessor = new XSLTInterchangeExportProcessor();
	
	/** The Constant XSLTImportProcessor. */
	private static final IInterchangeProcessor XSLTImportProcessor = new XSLTInterchangeImportProcessor();

	/** The Constant templateProcessor. */
	private static final IInterchangeProcessor templateProcessor = new VelocityInterchangeProcessor();
	
	/** The Constant aditusProcessor. */
	private static final IInterchangeProcessor aditusProcessor = new AditusInterchangeProcessor();

	/**
	 * Returns the processor for the given type of {@link IInterchangeDescriptor} which will 
	 * be used for export processes.
	 * 
	 * @param exportDescriptor
	 *            the export descriptor
	 * @return the processor for the given descriptor
	 */
	public static IInterchangeProcessor getExportProcessorFor(IInterchangeDescriptor exportDescriptor) {
		if (exportDescriptor instanceof XMLBasedExportDescriptor)
			return XSLTExportProcessor;

		return templateProcessor;
	}
	
	/**
	 * Returns the processor for the given type of {@link IInterchangeProcessor}
	 * which will be used for the import processes.
	 * 
	 * @param importDescriptor
	 *            The import descriptor
	 * @return The processor for the given descriptor
	 */
	public static IInterchangeProcessor getImportProcessorFor(IInterchangeDescriptor importDescriptor) {
		if(importDescriptor instanceof XMLBasedImportDescriptor)
			return XSLTImportProcessor;
		
		return aditusProcessor;
	}

}
