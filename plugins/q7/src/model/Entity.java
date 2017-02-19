package model;
import java.util.Hashtable;

import util.Computing;


public class Entity {

	public static Hashtable<String, String> aliases = new Hashtable<String, String>();
	
	public Entity(String _who, String _why, String _what, String _alias) {
		who = _who;
		why = _why;
		what = _what;
		alias = _alias;
		if (why !=null && who == null && what == null) {
			String a = why; 
			if (aliases.get(a)!=null) {
				alias = why;
			}
			while (aliases.get(a)!=null) {
//				D.o("alias " + a + "->");
				a = (String) aliases.get(a);
			}			
//			D.o("value="+a);
			if (a.indexOf("<")>=0 && a.indexOf(">::")>=0) {
					who = a.substring(a.indexOf("<")+1, a.indexOf(">::"));
					a = a.substring(a.indexOf(">::") + 3);
			}
			if (a.indexOf(" [")>=0 && a.indexOf("]")>=0) {
					what = a.substring(a.indexOf(" [")+2, a.indexOf("]")); 
					why = a.substring(0, a.indexOf(" ["));
			} else {
					what = null;
					why = a;
			}
		}
		if (alias != null) {
			String new_why = Computing.unique_goal_name(who, Computing.strip_quote(why), what);
//			D.o("alias " + alias + " is created for " + new_why);
			alias = Computing.strip_quote(alias);
			aliases.put(alias, new_why);
		}
//		D.o(this);
	}
	public String who;
	public String why;
	public String what;
	public String alias;
	public String getName() {
		return Computing.unique_goal_name(who, why, what);
	}
	public String toString() {
		String s = "";
		s += "\n\twho:\t"+who;
		s += "\n\twhy:\t"+why;
		s += "\n\twhat:\t"+what;
		s += "\n\talias:\t"+alias;
		return s;
	}
	public void update(String old_why) {
		if (aliases!=null && alias!=null) {
			String new_why = Computing.unique_goal_name(who, Computing.strip_quote(why), what);
			aliases.put(alias, new_why);
//			D.o("update: alias " + alias + " is created for " + new_why);
			aliases.put(Computing.strip_quote(old_why), new_why);
//			D.o("update: alias " + alias + " is created for " + new_why);
		}
	}
}
