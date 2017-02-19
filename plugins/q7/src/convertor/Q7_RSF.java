package convertor;

import parser.Q7;
import codegen.RSF;
import edu.toronto.cs.openome.core.convertor.IConvertor;

public class Q7_RSF implements IConvertor {
	public void convert(String input, String output) {
		Q7.main(new String[] {input});	
		RSF cg = new RSF(Q7.a);
		cg.generateRSF(output);		
	}
	
	public static void main(String[] args) {
		Q7_RSF c = new Q7_RSF();
		c.convert(args[0], args[1]);
	}
}
