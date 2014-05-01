package org.bflow.toolbox.hive.interchange.mif.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.IModel;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.model.InterchangeExportException;
import org.bflow.toolbox.hive.interchange.model.XMLBasedExportDescriptor;


/**
 * Provides an implementation of {@link IInterchangeProcessor} for processing XML/XSLT based
 * export descriptions that were introduced by version 1.0
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03/10/12
 * @version 18/07/13
 */
@SuppressWarnings("deprecation")
public class XSLTInterchangeExportProcessor implements IInterchangeProcessor {
	
	/** The XML based export descriptor. */
	private XMLBasedExportDescriptor xmlBasedExportDescriptor;
	
	/** The target file. */
	private File targetFile;
	
	/** The source file. */
	private File sourceFile;

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#lockFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void lockFile(String path, String name, String extension)
			throws InterchangeProcessingException {
		String filename = path.concat(name).concat(".").concat(extension);
		targetFile = new File(filename);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#process(org.bflow.toolbox.interchange.mif.core.IModelData)
	 */
	@Override
	public void process(IModelData modelData) throws InterchangeProcessingException {
		process(modelData.getModel(), modelData.getShapes(), modelData.getEdges());	
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#process(org.bflow.toolbox.interchange.mif.core.IModel, org.bflow.toolbox.interchange.mif.core.IShape[], org.bflow.toolbox.interchange.mif.core.IEdge[])
	 */
	@Override
	public void process(IModel model, IShape[] shapes, IEdge[] edges)
			throws InterchangeProcessingException {
		try {
			xmlBasedExportDescriptor.run(sourceFile, targetFile, false, false);
		} catch (InterchangeExportException e) {
			throw new InterchangeProcessingException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#releaseFile()
	 */
	@Override
	public void releaseFile() {
		sourceFile.delete();
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#initialize(org.bflow.toolbox.interchange.mif.core.IExportDescriptor)
	 */
	@Override
	public void initialize(IInterchangeDescriptor exportDescriptor) throws InterchangeProcessingException {
		xmlBasedExportDescriptor = (XMLBasedExportDescriptor) exportDescriptor;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#setInputStream(java.io.InputStream)
	 */
	@Override
	public void setInputStream(InputStream inputStream)
			throws InterchangeProcessingException {
		try {
			File tmpfile = File.createTempFile("cpyfile", null);
			tmpfile.deleteOnExit();
			
			FileOutputStream fos = new FileOutputStream(tmpfile);
			IOUtils.copy(inputStream, fos);
			IOUtils.closeQuietly(fos);
			
			sourceFile = tmpfile;
		} catch(Exception ex) {
			throw new InterchangeProcessingException("Could not copy input stream", ex);
		}
	}

}
