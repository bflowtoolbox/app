package model;

import java.util.ArrayList;

public class Advice {
	public String op;
	public String when;
	public Entity e;
	public String comment;
	public ArrayList where;
	public How how;
	public ArrayList how_much;
	public String label;
	
	public Advice(String _when, String _who, String _why,
	       String _what, String _alias, ArrayList _where, How _how,
	       ArrayList _how_much, String _comment, String _label) {
		if (_when!=null && _when.indexOf(":")>=0) {
			op = _when.substring(0,_when.indexOf(":"));
			when = _when.substring(_when.indexOf(":")+1);
		} else { // by default, op = make
			op = "++";
			when = _when;
		}
		e = new Entity(_who, _why, _what, _alias);
		where = _where;
		how = _how;
		how_much = _how_much;
		label = _label;
		comment = _comment;
	}
	
	/* For telos generation */
	public int id;    
	public int getID() {
		return id;
	}
	public void setID(int i) {
		id = i;
	}
	
	public String toString() {
		String s = "Advice";
		s += "\n\tid:\t"+id;
		s += "\n\tentity:\t" + e;
		s += "\n\twhen:\t"+when;
		s += "\n\twhere:\t"+where;
		s += "\n\thow:\t"+how;
		s += "\n\thow much:\t"+how_much;
		s += "\n\tcomment:\t"+comment;
		return s;
	}
}