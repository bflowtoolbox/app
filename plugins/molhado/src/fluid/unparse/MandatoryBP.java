package fluid.unparse;



public class MandatoryBP extends Breakpoint {
	MandatoryBP(int prio, Glue horz, Glue indent) {
		this.prio = prio; horzGlue = horz; indentGlue = indent;
	}
	static public MandatoryBP BREAKBP = 
		new MandatoryBP(1, Glue.JUXT, Glue.JUXT);
	public String toString() {
		return ("{M}");
	}
}
