
package fluid.unparse;



public class IndepBP extends Breakpoint {
	IndepBP(int prio, Glue horz, Glue indent) {
		this.prio = prio; horzGlue = horz; indentGlue = indent;
	}
	IndepBP(int prio) {
		this(prio, Glue.UNIT, Glue.INDENT);
	}
	static public IndepBP DONTBP = 
		new IndepBP(MAXPRIO, Glue.JUXT, Glue.JUXT);
	static public IndepBP JUXTBP = DONTBP;
	static public IndepBP SIMPLEBP = 
		new IndepBP(9);
	static public IndepBP DEFAULTBP = SIMPLEBP;
	public String toString() {
		return ("{I:" + prio + "}");
	}
}
