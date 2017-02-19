package edu.toronto.cs.openome.evaluation.SATSolver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Vector;

import edu.toronto.cs.openome_model.Intention;

public class zMinimalSolver extends SATSolver{
	/**
	 * @author jenhork
	 * This is where the reasoning actually occurs; however, it should be overridden by it's childen, as this is a general type of reasoner. 
	 * It's not clear what the parent reasoner should do.
	 * 
	 * 1 cnf is SAT
	 * 0 cnf is Unsat
	 * -1 problem with reasoner, wrong file format or some such
	 */
	public int solve(Dimacs cnf) {
		String path = homedir + cnf.getFileName();
		cnf.writeToFile(path); 
		//System.out.println(path);
		results = new Vector<Integer>();
		
		try	{
			//System.out.println("Trying to run solver");
			Runtime rt = Runtime.getRuntime() ;
	
			Process p = null;
			String osName = System.getProperty("os.name");
			String fileName = osName.contains("Linux") ? "zminimalLinux" : (osName.contains("Mac") ? "zminimalMac" : "zminimal.exe");
			String[] command = {homedir + fileName, path};
			p = rt.exec(command);
		    //p.waitFor();

		    String line;
		    String[] vars;// = new String[cnf.getNumVariables() + 3];
		  
		   // System.out.println("Solver output:");
		    BufferedReader input =
		        new BufferedReader
		          (new InputStreamReader(p.getInputStream()));
		    	
		      while ((line = input.readLine()) != null) {
		    	  //System.out.println(line);
		    	  if (line.startsWith("Unneeded clauses are:")) {
		    		  while ((line = input.readLine()) != null) {
		    			  //System.out.println(line);
			    		  vars = line.split(" ");
			    		  
			    		  Vector<Integer> v = convertToInts(vars, 20);
			    		  
			    		  /*for (Integer i : v) {
			    			  System.out.print(i);
			    		  }
			    		  System.out.println();*/
			    		  results.addAll(v);
			    		  //System.out.println(results.size());
			    		 
		    		  }
		    		  return 1;
		    	  }
		    	  
		      }
		      	      
		      
		      BufferedReader error =
			        new BufferedReader
			          (new InputStreamReader(p.getErrorStream()));
			    	
			      while ((line = error.readLine()) != null) {
			        System.out.println(line);
			      }
		   
		      input.close();

		      //do whatever you want
		   //some more code
		    p.destroy() ;
		 }
		catch(Exception exc){
			
			 /*handle exception*/
			return -1;
		}
		return -1;
		
	}

	
	

}
