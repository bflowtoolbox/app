/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/DefaultConsultant.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

/**
 * This class is the default <code>FieldConsultant</code> used by 
 * Fields if no consultant is provided to the constructor.  It
 * always returns <code>true</code>.
 */

public class DefaultConsultant
implements FieldConsultant
{
  /**
   * Always returns true.
   */
  public boolean isObjectAcceptable( Field f, int pos, Object o )
  {
    return true;
  }

  /**
   * Always returns true.
   */
  public boolean isObjectAcceptable( Field f, Object[] o )
  {
    return true;
  }
}
