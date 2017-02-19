/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/StringVectorField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * A field that can hold a list of Strings.  The list may have 
 * a maximum length.
 */
public class StringVectorField
extends StrategizedField
{
  /**
   * Create a new Field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @param max The maximum length of the field.  -1 indicates
   * no maximum length.
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public StringVectorField( final String n, final Template t,
                            final FieldConsultant c, final int max )
  {
    super( n, t, false, null, c, makeStrategy( max ) );
  }

  /**
   * Create a new Field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param max The maximum length of the field.  -1 indicates
   * no maximum length.
   */
  public StringVectorField( final String n, final Template t,
                            final int max )
  {
    this( n, t, DEFAULT_CONSULTANT, max );
  }

  /**
   * Create a new Field with no maximum length, and
   * with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public StringVectorField( final String n, final Template t,
                            final FieldConsultant c )
  {
    this( n, t, c, NO_LIMIT );
  }

  /**
   * Create a new Field with no maximum length, and
   * with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   */
  public StringVectorField( final String n, final Template t )
  {
    this( n, t, DEFAULT_CONSULTANT, NO_LIMIT );
  }
 

  //--------------------------------------------------------------------
  //--------------------------------------------------------------------

  private static FieldStrategy makeStrategy( final int max )
  {
    return new SimpleVectorStrategy( max, StringStrategy.prototype );
  }
}
