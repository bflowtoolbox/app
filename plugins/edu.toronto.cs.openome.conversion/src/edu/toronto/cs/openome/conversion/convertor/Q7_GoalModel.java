package edu.toronto.cs.openome.conversion.convertor;

import parser.Q7;
import edu.toronto.cs.openome.conversion.codegen.GoalModel;
import edu.toronto.cs.openome.core.convertor.IConvertor;

public class Q7_GoalModel implements IConvertor {
	public void convert(String input, String output) {
		Q7.main(new String[] {input});
		try {
			GoalModel cg = new GoalModel(Q7.a);
//			System.out.println("Intention model loaded");
			cg.generateGoalModel(output);
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
}
