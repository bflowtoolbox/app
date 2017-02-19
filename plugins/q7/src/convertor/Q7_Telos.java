package convertor;

import parser.Q7;
import codegen.IStar;
import edu.toronto.cs.openome.core.convertor.IConvertor;

public class Q7_Telos implements IConvertor {
	public void convert(String input, String output) {
		Q7.main(new String[] {input});	
		IStar cg = new IStar(Q7.a);
		cg.generateGoalModel(output);
	}
	
	public static void main(String[] args) {
		Q7_Telos c = new Q7_Telos();
		c.convert(args[0], args[1]);
	}
}
