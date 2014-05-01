package org.bflow.toolbox.hive.addons.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;

/**
 * contains a method to translate a prolog id to a emf id
 * @author Arian Storch
 * @since 29/08/09
 * @version 18/09/09
 *
 */
public class IdResolver 
{
	/**
	 * returns the emf id associated by the uri fragment
	 * @param file file that contains the emf object
	 * @param uriFragment uri fragment
	 * @return emf id associated by the uri fragment
	 */
	public static String getId(IFile file, String uriFragment)
	{		
		File f = file.getLocation().toFile();
		
		String searchTerm = "element=\""+uriFragment+"\"";
		
		try
		{			
			for(Object oLine:FileUtils.readLines(f))
			{			
				String line = (String)oLine;
				
				if(line.indexOf(searchTerm) < 1)
					continue;			
			
				int begin = line.indexOf("xmi:id=\"")+8;
			
				String partStream = line.substring(begin);
			
				int end = partStream.indexOf("\"");
			
				String targetStream = partStream.substring(0, end);
				
				//System.out.println(targetStream);
			
				return targetStream;
			}
			
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
