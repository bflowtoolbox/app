package edu.toronto.cs.openome.conversion.parser;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import util.Computing;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.openome_modelPackage;

public class GoalModelReader {
	
	protected String getActorName(Intention g) {
		Container a = g.getContainer();
		String n = "";
		if (a!=null)
			n = "<" + Computing.quotation(a.getName()) + ">::" + n;
		else { // Actor must be inherited from the ancestor Intentions
			Intention p = getParent(g);
			while (p!=null) {
				Container pa = p.getContainer();
	    		if (pa!=null) {
	    			n = "<" + Computing.quotation(pa.getName()) + ">::" + n;
	    			break;
	    		}
				p = getParent(p);
			}
		}
		return n;
	}

	private Intention getParent(Intention g) {
		EList<Decomposition> pl = g.getParentDecompositions();
		Intention p = null;
		if (pl.size()>0)
			p = ((Decomposition) pl.get(0)).getSource();
		return p;
	}

	@SuppressWarnings("unchecked")
	public Resource read(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(openome_modelPackage.eNS_URI, openome_modelPackage.eINSTANCE);
		File file = new File(filename);
		URI uri = file.isFile() ? URI.createFileURI(file.getAbsolutePath()): URI.createURI(filename);		
		return resourceSet.getResource(uri, true);
	} 
}
