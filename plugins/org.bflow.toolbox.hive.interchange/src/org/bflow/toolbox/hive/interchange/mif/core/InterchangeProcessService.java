package org.bflow.toolbox.hive.interchange.mif.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.hive.interchange.events.ImportListenerRegistry;
import org.bflow.toolbox.hive.interchange.utils.ModelDigger;

/**
 * Provides an interchange process service.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03/10/12
 * @version 14/12/13
 */
public class InterchangeProcessService {
	
	/**
	 * Processes the export interchange.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @param processor
	 *            the processor
	 * @param descriptor
	 *            the export descriptor
	 * @throws InterchangeProcessingException
	 *             the interchange processing exception
	 */
	public static void processExport(File sourceFile, File targetFile, IInterchangeProcessor processor, 
			IInterchangeDescriptor descriptor) throws InterchangeProcessingException {
		// Creating the interchange process info object
		InterchangeProcessInfo interchangeProcessInfo = new InterchangeProcessInfo();
		interchangeProcessInfo.SourceFile = sourceFile;
		interchangeProcessInfo.TargetFile = targetFile;
		
		// Initialize
		processor.initialize(descriptor);
		descriptor.getProcessListener().onInitialize(interchangeProcessInfo);
		
		// Create an input stream
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(interchangeProcessInfo.SourceFile);
		} catch (FileNotFoundException e) {
			throw new InterchangeProcessingException("Could not create input stream", e);
		}
		
		// Set the input stream
		processor.setInputStream(fis);
		
		// Resolve target file informations
		String filename = sourceFile.getAbsolutePath();
		String path = targetFile.getAbsolutePath().concat("\\");
		String name = FilenameUtils.getBaseName(filename);
		String extension = descriptor.getFileExtensions()[0];
		
		// Lock target file
		processor.lockFile(path, name, extension);
		
		// Resolve model data
		IModelData modelData = ModelDigger.resolveModelData(interchangeProcessInfo.SourceFile);
		
		// Process the interchange
		descriptor.getProcessListener().beforeProcessing(interchangeProcessInfo.SourceFile, interchangeProcessInfo.TargetFile);
		processor.process(modelData.getModel(), modelData.getShapes(), modelData.getEdges());
		descriptor.getProcessListener().afterProcessing(interchangeProcessInfo.SourceFile, interchangeProcessInfo.TargetFile);
		
		// Release the locked file
		processor.releaseFile();
		descriptor.getProcessListener().onFinished(interchangeProcessInfo);
		
		// Close the stream
		IOUtils.closeQuietly(fis);
	}
	
	/**
	 * Processes the import interchange.
	 * 
	 * @param sourceFile
	 *            The source file
	 * @param targetFile
	 *            The target file
	 * @param processor
	 *            The processor
	 * @param descriptor
	 *            The import descriptor
	 * @throws InterchangeProcessingException
	 */
	public static void processImport(File sourceFile, File targetFile, IInterchangeProcessor processor, 
			IInterchangeDescriptor descriptor) throws InterchangeProcessingException {
		// Creating the interchange process info object
		InterchangeProcessInfo interchangeProcessInfo = new InterchangeProcessInfo();
		interchangeProcessInfo.SourceFile = sourceFile;
		interchangeProcessInfo.TargetFile = targetFile;
		
		// Resolve target file informations
		String filename = sourceFile.getAbsolutePath();
		String path = targetFile.getAbsolutePath().concat("\\");
		String name = FilenameUtils.getBaseName(filename);
		String extension = descriptor.getApplicableDiagramEditorTypes()[0];
		
		// Check if there is already a file with the same name
		name = getUniqueFileName(path, name, extension);
		
		// Initialize
		processor.initialize(descriptor);
		descriptor.getProcessListener().onInitialize(interchangeProcessInfo);
		ImportListenerRegistry.dispatchOnInitialized(interchangeProcessInfo);
		
		// Create an input stream
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(interchangeProcessInfo.SourceFile);
		} catch (FileNotFoundException ex) {
			throw new InterchangeProcessingException("Could not create input stream!", ex);
		}
		
		// Set the input stream
		processor.setInputStream(fis);
		
		// Lock target file
		processor.lockFile(path, name, extension);

		// Process the interchange
		descriptor.getProcessListener().beforeProcessing(interchangeProcessInfo.SourceFile, interchangeProcessInfo.TargetFile);
		ImportListenerRegistry.dispatchOnBeforeProcessing(sourceFile, targetFile);
		
		processor.process(null, null, null);
		
		descriptor.getProcessListener().afterProcessing(interchangeProcessInfo.SourceFile, interchangeProcessInfo.TargetFile);
		ImportListenerRegistry.dispatchOnAfterProcessing(sourceFile, targetFile);
		
		// Release the locked file
		processor.releaseFile();
		descriptor.getProcessListener().onFinished(interchangeProcessInfo);
		ImportListenerRegistry.dispatchOnFinished(interchangeProcessInfo);
		
		// Close the stream
		IOUtils.closeQuietly(fis);
	}
	
	/**
	 * Checks if there is already a file within the target folder which would be
	 * overwritten by performing the process. If it is a new unique name will be 
	 * returned. Otherwise the original value will be returned.
	 * 
	 * @param path Path of the target file
	 * @param name Name of the target file
	 * @param extension File name extension of the target file
	 * @return A unique name for the target file
	 */
	private static String getUniqueFileName(String path, final String name, String extension) {
		String absoluteFilePath = String.format("%s%s.%s", path, name, extension);
		File targetFile = new File(absoluteFilePath);
		// Everything is fine
		if(!targetFile.exists())
			return name;
		
		// TODO Ask the user what to do
		// Using default strategy, add a number to the name
		File targetFolder = targetFile.getParentFile();
		String[] equalFileNames = targetFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.contains(name);
		}});
		
		int number = equalFileNames.length + 1;
		String newFileName = String.format("%s(%d)", name, number);
		return newFileName;
	}
}
