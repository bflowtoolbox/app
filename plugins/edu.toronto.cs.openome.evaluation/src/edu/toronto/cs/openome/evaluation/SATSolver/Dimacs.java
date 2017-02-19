package edu.toronto.cs.openome.evaluation.SATSolver;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.eclipse.core.runtime.Path;
import org.sat4j.core.VecInt;

import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.OrDecomposition;

public class Dimacs {
	private Vector<Axioms> axioms;
	private String filename;
		
	public Dimacs(String f) {
		axioms = new Vector<Axioms>();
		filename = f;
		
	}
	
	public String getFileName() {
		return filename;
	}
	
	public boolean addAxioms(Axioms la) {
		//System.out.println("adding link axioms");
		if (la != null)
			return axioms.add(la);
		else
			return false;
	}
	
	public boolean containsBackward(VecInt vi) {	
		 for (Axioms la : axioms) {
			 if (la.isEnabled()) {
				 if (la.containsBackward(vi))
					return true;	
			 }
		 }
		 return false;
	}
	
	public boolean containsForward(VecInt vi) {	
		 for (Axioms la : axioms) {
			 if (la.isEnabled()) {
				 if (la.containsForward(vi))
					 return true;
			 }
		 }
		 return false;
	}
	
	public boolean contains(VecInt vi) {	
		 for (Axioms la : axioms) {
			 if (la.isEnabled()) {
				 if (la.contains(vi))
					 return true;
			 }
		 }
		 return false;
	}
	
	public VecInt getClauseByIndex(int index)  {
		for (Axioms la : axioms) {
			if (la.isEnabled()) {
				VecInt vi = la.getClauseByIndex(index);
				if (vi != null)
					return vi;
			}
		}
		return null;
	}
	
	public String writeToFile(String path) {
		
		try {
		// Create file 
	    FileWriter fstream = new FileWriter(path);
	    BufferedWriter out = new BufferedWriter(fstream);
	    
	    out.write("p cnf " + getNumVariables() + " " + getNumClauses() + "\n");
	    int count = 0;
	    for (Axioms la : axioms) {
	    	if (la.isEnabled()) {
	    		la.setClauseIndex(count);
	    		out.write("c " + la.getDescription() + " " + count + "\n");
		    	for (String str : la.getClauses())  {
		    		out.write(str + "\n");
		    		count++;
		    	}
		    	//for (String str : la.getBackwardClauses())  {
		    	//	out.write(str + "\n");
		    	//}
	    	}
	    }
	    
	    
	    //Close the output stream
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }

	    return path;
	}

	public int getNumClauses() {
		int numClauses = 0;
		 for (Axioms la : axioms) {
			 if (la.isEnabled()) {
				 numClauses += la.getNumClauses();
				 //System.out.println("numClauses: " + numClauses);
			 }
		 }
		return numClauses;
	}

	public int getNumVariables() {
		if (axioms.size() > 0) {
			return axioms.get(0).getNumVars();
		}
		else return 0;
	}

	public void disableAxioms(Vector<Link> links) {
		for (Axioms la : axioms) {
			if (la.containsLinks(links))
				la.disable();
		}
		
	}

	public Vector<Axioms> getAxioms(Vector<Link> links) {
		Vector<Axioms> axs = new Vector<Axioms>();
		for (Axioms la : axioms) {
			if (la.containsLinks(links))
				axs.add(la);
		}
		return axs;
	}

	public void removeAxiom(Axioms ax) {
		//System.out.println("trying to remove axiom: " + ax.getDescription());
		for (Axioms la : axioms) {
			if (ax.equals(la)) {
				//System.out.println("Removing axioms: " + la.getDescription());
				axioms.remove(la);
				return;
			}
		}		
	}

	public void disableAxiom(Axioms ax) {
		for (Axioms la : axioms) {
			if (ax.equals(la))
				la.disable();
		}
	}
	
	public void enableAxiom(Axioms ax) {
		for (Axioms la : axioms) {
			if (ax.equals(la))
				la.enable();
		}
	}

	

}
