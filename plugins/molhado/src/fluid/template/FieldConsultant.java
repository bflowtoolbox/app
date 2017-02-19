/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/FieldConsultant.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Interface to objects with whom Fields can consult to
 * determine if an Object can be stored in it.
 */
public interface FieldConsultant
{
  /**
   * This is only called after the field has
   * determined that the object is acceptable based on its
   * own criteria.  This method allows a template to use
   * additional criteria that relies on information the field
   * would not have access to.
   * @param f The field that is being set.
   * @param pos The position in the field that is being set.
   * @param o The object being placed in the field.
   * @return <TT>true</TT> iff the field should accept
   * object <tt>o</tt> at the given position
   */
  public boolean isObjectAcceptable( Field f, int pos, Object o );

  /**
   * This is only called after the field has
   * determined that the object is acceptable based on its
   * own criteria.  This method allows a template to use
   * additional criteria that relies on information the field
   * would not have access to.
   * @param f The field that is being set.
   * @param o The object being placed in the field.
   * @return <TT>true</TT> iff the field should accept
   * object <tt>o</tt>.
   */
  public boolean isObjectAcceptable( Field f, Object[] o );
}
