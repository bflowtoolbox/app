package edu.toronto.cs.openome.core.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.framework.debug.Debug;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.core.convertor.IGenerator;

/**
 *
 * @author Peter Friese
 * @since 03.11.2005
 */
public class ImportProjects extends ExtensionReader implements org.eclipse.core.commands.IHandler {
	/**
	 * Show the subdirectories of the directory
	 * @param file -- the directory
	 * @return -- the matched files
	 */
	public static ArrayList<File> listSubdirectories(String string) {
	   // Assume that dir is a directory.  List its contents.
	   ArrayList<File> listed_files = new ArrayList<File>();
	   File file = new File(string);
	   if (file == null) {
		   return listed_files;
	   }
	   File[] files;  // The names of the files in the directory.
	   files = file.listFiles();
	   if (files ==null)
	   	  return null;
	   for (int i = 0; i < files.length; i++) {
	       File f = files[i]; 
	       	// One of the files in the directory.
	       if ( f.isDirectory() ) {
		      listed_files.add(files[i]);
	       }
	   }
	   return listed_files;
	}

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench,
        IStructuredSelection selection)
    {
    }

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
    	IWorkspace w = importProjects();
    	Vector<IConfigurationElement> generators = new Vector<IConfigurationElement>();
		readExtensions("core.generator", "generator", generators);
		for (IConfigurationElement generator: generators)
			performGeneration(generator);    	
        buildProjects(w);
        return null;
	}

	private void performGeneration(IConfigurationElement generator) {
		String classname = generator.getAttribute("class");
		try {
		  System.out.println(classname);
//		  Debug.DEBUG_LOADER = true;
		  IGenerator cv = (IGenerator) generator.createExecutableExtension("class");
		  Debug.DEBUG_LOADER = false;
		  cv.cleanup();
	      cv.generate();
		} catch (ClassCastException x) {
			x.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (Exception a) {
			a.printStackTrace();
		}
	}

	private IWorkspace importProjects() {
		IWorkspace w = ResourcesPlugin.getWorkspace();
    	String root = w.getRoot().getLocation().toOSString();
    	System.err.println("root = " + root);
        ArrayList<File> dirs = listSubdirectories(root);
        for (int i=0; i < dirs.size(); i++) {
        	File f = dirs.get(i);
        	if (f.getName().equals(".metadata"))
        		continue;
//        	System.out.println(f.getName());
			IProject p = w.getRoot().getProject("/" + f.getName());
        	if (!p.exists()) {
        		try {
					p.create(null);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	} else {
        		System.out.println("The project " + "/" + f.getName() + " has already been created");
        	}
        	try {
				p.open(null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return w;
	}

	private void buildProjects(IWorkspace w) {
		PlatformUI.getPreferenceStore().setValue("/instance/org.eclipse.jdt.core/org.eclipse.jdt.core.compiler.source", 
		"1.5");
		PlatformUI.getPreferenceStore().setValue("/instance/org.eclipse.jdt.core/org.eclipse.jdt.core.compiler.codegen.targetPlatform", 
		"1.5");
		PlatformUI.getPreferenceStore().setValue("/instance/org.eclipse.jdt.core/org.eclipse.jdt.core.compiler.compliance", 
		"1.5");
		// buildProjects
    	try {
			w.build(0, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

}
