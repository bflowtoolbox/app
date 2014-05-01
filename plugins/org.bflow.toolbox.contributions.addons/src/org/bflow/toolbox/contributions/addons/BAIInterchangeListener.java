package org.bflow.toolbox.contributions.addons;

import java.io.File;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessInfo;

/**
 * Implements {@link IInterchangeProcessListener} to provide special features 
 * needed by the BPM Academic Initiative models. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 23/07/13
 */
public class BAIInterchangeListener implements IInterchangeProcessListener {
	
	private File iTmpXmlFile;

	@Override
	public void onInitialize(InterchangeProcessInfo interchangeProcessInfo) {  
		try {
			File sourceFile = interchangeProcessInfo.SourceFile;
			String jsonFileContent = FileUtils.readFileToString(sourceFile);
			JSONObject json = JSONObject.fromObject(jsonFileContent);
			String xml = new XMLSerializer().write(json);
			
			iTmpXmlFile = File.createTempFile("json2xml", null);
			FileUtils.writeStringToFile(iTmpXmlFile, xml);
			
			interchangeProcessInfo.SourceFile = iTmpXmlFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void beforeProcessing(File sourceFile, File targetFile) {
	}

	@Override
	public void afterProcessing(File sourceFile, File targetFile) {
	}

	@Override
	public void onFinished(InterchangeProcessInfo interchangeProcessInfo) {
		iTmpXmlFile.delete();
		iTmpXmlFile = null;
	}

}
