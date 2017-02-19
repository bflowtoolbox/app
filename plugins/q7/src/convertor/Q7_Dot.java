package convertor;

import parser.Q7;
import codegen.Dot;
import edu.toronto.cs.openome.core.convertor.IConvertor;

public class Q7_Dot implements IConvertor {
	public void convert(String input, String output) {
		Q7.main(new String[] {input});	
		Dot cg = new Dot(Q7.a);
		cg.generateDot(output);		
	}
	
	public static void main(String[] args) {
		Q7_Dot c = new Q7_Dot();
		c.convert(args[0], args[1]);
	}
}
