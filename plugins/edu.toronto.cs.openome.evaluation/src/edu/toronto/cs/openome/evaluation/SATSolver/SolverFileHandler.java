package edu.toronto.cs.openome.evaluation.SATSolver;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import edu.toronto.cs.openome.core.action.ExtensionReader;


public class SolverFileHandler extends ExtensionReader {

	Vector<IConfigurationElement> solvers = null;
	String path;
	

	/**
	 * @author Showzeb
	 * Method to basically get the path of the folder of SAT solver's.
	 */
	public String folderPath() {
		String osName = System.getProperty("os.name");
		String fileType = osName.contains("Linux") ? "Linux" : (osName.contains("Mac") ? "Mac" : ".exe");
		try {
			solvers = new Vector<IConfigurationElement>();
			URL installURL = Platform.getConfigurationLocation().getURL();
			path = FileLocator.toFileURL(installURL).getPath() + "Solvers/";
			if (!new File(path).exists() && new File(path).mkdir()) {
				readExtensions("edu.toronto.cs.openome.evaluation.solver", "solver", solvers);
				for (IConfigurationElement solver: solvers) {
					String pluginname = solver.getDeclaringExtension().getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginname);
					String filename = solver.getAttribute("file");
					if (filename.contains(fileType) || filename.equals("script.sh"))
						writeToFile(pluginname, filename,bundle);
				
				}
				if (!fileType.contains(".exe"))	{
					Process p = new ProcessBuilder("sh", path + "script.sh", path).start();				
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!fileType.contains(".exe"))	{
			return path;			
		} else {
			return path.replaceFirst("/", "");
		}
	}
	
	private void writeToFile(String pluginname, String filename, Bundle bundle) {
		try {
			String name = pluginname + "/" + filename;
			InputStream stream = FileLocator.openStream(bundle, new Path("Solvers/" + filename), false);
			File dst = new File(path + filename);
		    OutputStream out = new FileOutputStream(dst);
	
		    // Transfer bytes from in to out
		    byte[] buf = new byte[1024];
		    int len;
		    while ((len = stream.read(buf)) > 0) {
		        out.write(buf, 0, len);
		    }
		    stream.close();
		    out.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
