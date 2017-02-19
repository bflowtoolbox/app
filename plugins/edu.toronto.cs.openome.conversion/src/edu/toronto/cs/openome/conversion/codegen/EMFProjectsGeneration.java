package edu.toronto.cs.openome.conversion.codegen;

public class EMFProjectsGeneration extends edu.toronto.cs.openome.conversion.action.EMFProjectsGeneration {

	public EMFProjectsGeneration() {
		super("openome_model", 
			  "/model/openome_model.genmodel", 
			  "edu.toronto.cs.openome_model",
			  new String [] {
				// For including dependency on openome_model.figures plugin
				// otherwise, the edu.toronto.cs.openome_model.diagrams plugin
				// will always complain !
				"edu.toronto.cs.openome_model/META-INF/MANIFEST.MF",				
				// For the getParent() method derived from getParentDecompositions
				// should be replaced by OCL 
				"edu.toronto.cs.openome_model/src/edu/toronto/cs/openome_model/Intention.java",
				"edu.toronto.cs.openome_model/src/edu/toronto/cs/openome_model/impl/IntentionImpl.java",
				});
	}

}
