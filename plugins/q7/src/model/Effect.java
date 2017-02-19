/*
 * Created on Jan 31, 2005
 */
package model;


/**
 * @author Yijun Yu
 *
 */
public class Effect {

	public String op;
	public Entity e;
	/**
	 * @param string
	 * @param string2
	 */
	public Effect(String _op, String _who, String _why, String _what, String _alias) {
		op = _op;
		e = new Entity(_who, _why, _what, _alias);
	}
	
	public String toString() {
		return toOp() + toID() ;
	}
	
	public String toOp() {
		return (e.why==null? "" : op);
	}

	public String toID() {
		return e.getName();
	}
}
