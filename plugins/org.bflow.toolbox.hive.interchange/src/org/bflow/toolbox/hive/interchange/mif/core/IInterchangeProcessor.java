package org.bflow.toolbox.hive.interchange.mif.core;

import java.io.InputStream;


/**
 * Defines an Interchange Processor.
 * <p/>
 * Such processors are used to make the export of models. They can be used to
 * provide a broad range of varying export types like XSLT-based, template-based
 * or even to create graphical formats like JPG or others.
 * <p/>
 * The export process itself is controlled by an instance of
 * {@link InterchangeProcessService}. Every process contains the same steps,
 * which means that the following order of methods is called.
 * 
 * <p>
 * 1.) <code>initialize()</code><br/>
 * 2.) <code>setInputStream()</code><br/>
 * 3.) <code>lockFile()</code><br/>
 * 4.) <code>process()</code><br/>
 * 5.) <code>releaseFile()</code>
 * </p>
 * 
 * <b>Note:</b> If some goes wrong your implementation should throw an
 * {@link InterchangeProcessingException}. This guarantees that the process exit
 * will be handled correctly and the user gets an error message which helps him
 * to find out what's wrong.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01/10/12
 * @version 18/07/13
 */
public interface IInterchangeProcessor {

	/**
	 * Initializes the processor. Will be called first.
	 * 
	 * @param exportDescriptor
	 *            the export descriptor that is used for this export process
	 * @throws InterchangeProcessingException
	 *             If something went wrong
	 */
	public void initialize(IInterchangeDescriptor descriptor)
			throws InterchangeProcessingException;

	/**
	 * Sets the input stream. The input stream points to the source file. In
	 * most cases this will be the model file.
	 * <p/>
	 * This will be called after <code>initialize()</code>.
	 * 
	 * @param inputStream
	 *            the new input stream which points to the source file
	 * @throws InterchangeProcessingException
	 *             If something went wrong
	 */
	public void setInputStream(InputStream inputStream)
			throws InterchangeProcessingException;

	/**
	 * Tells the processor to lock the target file.
	 * <p/>
	 * The given parameters path, name and extensions are describing the target
	 * file. With these informations implementations should lock the addressed
	 * file so that no IO errors can occur later during the export process.
	 * <p/>
	 * 
	 * This will be called after <code>setInputStream()</code>.
	 * 
	 * @param path
	 *            the target file path
	 * @param name
	 *            the target file name
	 * @param extension
	 *            the target file extension
	 * @throws InterchangeProcessingException
	 *             If something went wrong
	 */
	public void lockFile(String path, String name, String extension)
			throws InterchangeProcessingException;

	/**
	 * Tells the processor to do his work.
	 * <p/>
	 * The given parameters contain many informations about the model (source)
	 * that is being handled.
	 * <p/>
	 * This will be called after <code>lockFile()</code>.
	 * 
	 * @param model
	 *            the model
	 * @param shapes
	 *            the shapes of the model
	 * @param edges
	 *            the edges´of the model
	 * @throws InterchangeProcessingException
	 *             If something went wrong
	 * @see IModel
	 * @see IEdge
	 * @see IShape
	 */
	public void process(IModel model, IShape[] shapes, IEdge[] edges)
			throws InterchangeProcessingException;
	
	/**
	 * Tells the processor to do his work.
	 * <p/>
	 * The given parameters contain many informations about the model (source)
	 * that is being handled.
	 * <p/>
	 * This will be called after <code>lockFile()</code>.
	 * 
	 * @param modelData
	 *            The model data
	 * @throws InterchangeProcessingException
	 *             If something went wrong
	 * @see IModelData
	 */
	public void process(IModelData modelData) throws InterchangeProcessingException;

	/**
	 * Tells the processor to release the target file after the process has been
	 * finished. Any existing stream or pointers to files must be released here
	 * at least!
	 * <p/>
	 * This will be called after <code>process()</code> and is the last step within the whole process.
	 */
	public void releaseFile();
}
