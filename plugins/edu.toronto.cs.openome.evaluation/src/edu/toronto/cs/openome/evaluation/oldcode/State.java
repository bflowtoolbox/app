package edu.toronto.cs.openome.evaluation.oldcode;

import java.util.Vector;
public abstract class State {
	public abstract Vector<State> generateChildren(); 
	public abstract boolean goalp(); 
	public abstract int estimate();
}


 
 

