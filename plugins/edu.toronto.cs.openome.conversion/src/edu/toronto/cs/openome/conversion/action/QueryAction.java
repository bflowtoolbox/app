package edu.toronto.cs.openome.conversion.action;
import org.eclipse.emf.ecore.resource.Resource;

import edu.toronto.cs.openome.conversion.parser.GoalModelReader;
import edu.toronto.cs.openome.core.action.ObjectAction;

public class QueryAction extends ObjectAction {
	public void performAction(String name) {
		GoalModelReader rdr = new GoalModelReader();
		Resource r = rdr.read(name);
//		QueryVariability2 q = new QueryVariability2(r);
//		q.init(r);		
	}
}