package org.bflow.toolbox.contributions.addons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessInfo;

/**
 * Implements {@link IInterchangeProcessListener} to provide special features 
 * needed by the ARIS AML import.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 18/07/13
 * @version 23/07/13
 */
public class ArisAMLInterchangeListener implements IInterchangeProcessListener {
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#onInitialize()
	 */
	@Override
	public void onInitialize(InterchangeProcessInfo interchangeProcessInfo) {
		String currentDirectory = System.getProperty("user.dir");
		File tgtFile = new File(String.format("%s/%s", currentDirectory, "ARIS-Export.dtd"));
		if(tgtFile.exists())
			return;
		tgtFile.deleteOnExit();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(tgtFile);
			InputStream inputStream = getClass().getResourceAsStream("/importscripts/files/ARIS-Export.dtd");
			IOUtils.copy(inputStream, fileOutputStream);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(fileOutputStream);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#beforeProcessing(java.io.File, java.io.File)
	 */
	@Override
	public void beforeProcessing(File sourceFile, File targetFile) {
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#afterProcessing(java.io.File, java.io.File)
	 */
	@Override
	public void afterProcessing(File sourceFile, File targetFile) {
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#onFinished()
	 */
	@Override
	public void onFinished(InterchangeProcessInfo interchangeProcessInfo) {		
	}

}
