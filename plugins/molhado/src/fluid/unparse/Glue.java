
package fluid.unparse;

public class Glue {
  int size;
  boolean fixed;

  private static int indentdefault = 2;

  static public final Glue UNIT = new Glue(true);
  static public final Glue JUXT = new Glue(false);
  static public final Glue INDENT = new Glue(indentdefault);

  public Glue(int size) { 
    fixed = true; 
    this.size = size; 
  }

  public Glue(boolean uni) {
    fixed = false; 
    size = 0;
    if (uni) size = 1;
  }

  public int getLength() {
    return size;
  }

  public String toString() {
    StringBuffer s = new StringBuffer(size);
    s.setLength(size);
    int i = 0;
    while (i < size) {
      s.setCharAt(i,' ');
      i++;
    }
    return s.toString();
  }

  /** Augment a glue by the amount specified in another glue.
   * <b>This method side-effects a glue object!!! (JTB)</b>
   * @deprecated use <tt>g = Glue.addGlue(g,v)</tt> instead.
   */
  public void augGlue(Glue v) {
    if (fixed && v.fixed)
      size = size + v.size;
    else if (v.fixed) {
      size = v.size;
      fixed = true;
    }
    else if ((v.size == 1) || (size == 1)) 
      size = 1;
    return;
  }

  static Glue addGlue(Glue u, Glue v) {
    if (u.fixed && v.fixed)
      return new Glue(v.size + u.size);
    else if (u.fixed) 
      return new Glue(u.size); //? why not "return u" ?
    else if (v.fixed)
      return new Glue(v.size); //? why not "return v" ?
    else if ((v.size == 1) || (u.size == 1)) // either is a unit
      return new Glue(true); //? why not "return UNIT" ?
    else // both juxts
      return new Glue(false); //? why not "return JUXT" or "return u" ?
  }
}
