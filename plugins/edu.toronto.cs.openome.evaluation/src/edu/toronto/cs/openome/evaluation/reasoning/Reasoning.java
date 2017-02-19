package edu.toronto.cs.openome.evaluation.reasoning;

import java.util.Vector;


/**
 * @author jenhork
 * A class in the Strategy pattern.
 * 
 * This is the main class that uses a reasoner to do reasoning.
 * You pass this class a type of reasoner and then tell it to reason.
 */
public class Reasoning {
	
	private Reasoner reasoner;
	
	
	/**
	 * @author jenhork
	 * Default constructor, does nothing
	 */
	public Reasoning() {
		
		
		reasoner = null;
	}
	 
	/**
	 * @author jenhork
	 * @param Takes in a model
	 * @param Takes in an enumerated type representing the type of evaluation to do
	 */
	public Reasoning(Reasoner rsnr) {
	
		reasoner = rsnr;
	}
	
	/**
	 * @author jenhork
	 * @param Takes in an Reasoner class describing the type of reasoning to do
	 */
	public void setReasoner(Reasoner rsnr) {
		reasoner = rsnr;
	}
	
	/**
	 * @author jenhork
	 * @param Returns the Reasoner class currently being used
	 */
	public Reasoner getReasoner() {
		return reasoner;
	}
	
	
	/**
	 * @author jenhork
	 * @return  There should probably be some more intelligent return value, like an error or something
	 * 
	 * This is where the magic happens, this will perform reasoning over whatever model is currently stored.
	 */
	public void reason() {
		
		long start = System.currentTimeMillis();
		
		reasoner.reason();
		
		long end = System.currentTimeMillis();

		System.out.println("Execution time for analysis was "+(end-start)+" ms.");		
	
		
	}
	

	
}
