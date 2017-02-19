package edu.toronto.cs.openome.conversion.action;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.util.CodegenEmitters;
import org.eclipse.gmf.codegen.util.Generator;

import edu.toronto.cs.openome.core.convertor.IGenerator;

public class GMFProjectGeneration implements IGenerator {

	private String model;
	private String project;
	private String target;
	private String overwrite[];
	public GMFProjectGeneration(String project, String model, String target,
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
	}

	public void cleanup() {
		System.out.println("Cleanup...");
		// Because there are customization in the diagram project,
		// let's do not delete it !
	    IWorkspace w = ResourcesPlugin.getWorkspace();
	    EMFProjectsGeneration.delete_project(w, "/" + target + ".diagram");
	}

	@SuppressWarnings("unchecked")
	public Resource loadGenDiagram(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(GMFGenPackage.eNS_URI, GMFGenPackage.eINSTANCE);
		File file = new File(filename);
		System.out.println("file=" + filename);
		URI uri = file.isFile() ? URI.createFileURI(file.getAbsolutePath()): URI.createURI(filename);		
		return resourceSet.getResource(uri, true);
	} 

	
	public void generate() {
		System.out.println("Generate...");
		IWorkspace w = ResourcesPlugin.getWorkspace();
		IProject p = w.getRoot().getProject("/" + this.project);
		if (p==null)
			return;
		IFile file = p.getFile(model);
		if (file==null)
			return;
		IPath location = file.getLocation();
		if (location==null)
			return;
		String file2 = location.toPortableString();
		if (file2==null)
			return;
		Resource resource2 = loadGenDiagram(file2);
		GenEditorGenerator geg = (GenEditorGenerator) resource2.getContents().get(0);
		CodegenEmitters b = new CodegenEmitters(false, null, false);
		Generator gen = new Generator(geg, b);
		gen.run();
		EMFProjectsGeneration.overwrite_projects(w, project, overwrite);
	}
}
