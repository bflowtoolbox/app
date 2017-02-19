package model;

/**
 * @author Yijun Yu
 */
public class How {
		public String rule;
		public String enrich;
		public java.util.ArrayList<Advice> advices;
		public How(String r, String e, java.util.ArrayList<Advice> a) {
			rule = r;
			enrich = e;
			advices = a;
		}
}
