package edu.toronto.cs.openome.conversion.action;


import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.Generator;
import org.osgi.framework.Bundle;

import edu.toronto.cs.openome.core.convertor.IGenerator;

public class EMFProjectsGeneration implements IGenerator {

	private String model;
	private String project;
	private String target;
	private String overwrite[];
	public EMFProjectsGeneration(String project, String model, String target,
			String overwrite[]) {
		this.project = project;
		this.model = model;
		this.target = target;
		this.overwrite = overwrite;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void cleanup() {
		System.out.println("Cleanup...");
		IWorkspace w = ResourcesPlugin.getWorkspace();
		delete_project(w, "/" + target);
		delete_project(w, "/" + target + ".edit");
		delete_project(w, "/" + target + ".editor");
		delete_project(w, "/" + target + ".test");
	}

	public static void copy_file_to(IWorkspace w, String project, String name) {
		try {
			String[] words = name.split("/");
			String pathName = "";
			IFolder folder = null;
			if (words.length<2) return;
			String target = words[0]; 
			IProject p = w.getRoot().getProject(target);
			p.open(null);
			for (int i = 1; i < words.length - 1; i++) {
				String string = words[i];
				pathName = pathName + string + "/";
				folder = p.getFolder(pathName);
				if (!folder.exists())
					folder.create(IResource.FORCE, true, null);
			}
			Bundle bundle = Platform.getBundle(project);		
			InputStream stream;
			stream = FileLocator.openStream(bundle, 
					new Path("model" + "/" + name), false);
			IFile file;
			if (folder == null)
				file = p.getFile(name);
			else
				file = folder.getFile(words[words.length - 1]);
			if (!file.exists())
				file.create(stream, false, null);
			stream.close();
		} catch (CoreException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	static void delete_project(IWorkspace w, String string) {
		IProject p = w.getRoot().getProject(string);
        if (p.exists())
			try {
				p.delete(true, null);
			} catch (CoreException e) {
				// e.printStackTrace();
				System.out.println("The project " + string + " cannot be removed.");
			}
	}
	
	static void overwrite_projects(IWorkspace w, String project, String overwrite[]) {
		for (int i=0; i<overwrite.length; i++ ) {
			copy_file_to(w, project, overwrite[i]);					
		}		
	}
	
	public void generate() {
		// TODO Auto-generated method stub
		System.out.println("Generate...");
		IWorkspace w = ResourcesPlugin.getWorkspace();
		IFile file = w.getRoot().getProject("/" + project).getFile(model);
		if (file==null || !file.exists())
			return;
		Generator g = new Generator();
		String args[] = new String[6];
		args[0] = "-autoBuild";
		args[1] = "true";
		args[2] = "-model";
		args[3] = "-edit";
		args[4] = "-editor";
		args[5] = file.getLocation().toPortableString(); 
		g.run(args);
		overwrite_projects(w, project, overwrite);
	}
}
