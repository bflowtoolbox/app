/*
 * Created on Jan 31, 2005
 */
package model;

/**
 * @author Yijun Yu
 *
 */
public class Pointcut {

	public String op;
	public Entity e;
	/**
	 * @param string
	 * @param string2
	 */
	public Pointcut(String _op, String _who, String _why, String _what, String _alias) {
		op = _op;
		e = new Entity(_who, _why, _what, _alias);
	}
	public String toString() {
		return op + e.getName();
	}
}
