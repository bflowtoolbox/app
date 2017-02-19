/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/StringField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Field that holds a Java String.
 */
public class StringField
extends StrategizedField
{
  /**
   * Create a new ScalarField with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @exception IllegalArgumentException
   * Thrown if <code>c</code> is <code>null</code>.
   */
  public StringField( final String n, final Template t,
                      final FieldConsultant c )
  {
    super( n, t, false, null, c, StringStrategy.prototype );
  }

  /**
   * Create a new ScalarField with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   */
  public StringField( final String n, final Template t )
  {
    this( n, t, DEFAULT_CONSULTANT );
  }



  //--------------------------------------------------------------------
  // Convienence Methods
  //--------------------------------------------------------------------

  /**
   * Get the String stored in the field.  This is the 
   * same as <tt>(String)getValue( 0 )</TT>.
   * @return The String stored in the field, or <code>null</code>
   * if the field is empty.
   */
  public String getValueAsString()
  {
    return (String)getValue( 0 );
  }

  /**
   * Set the String stored in the field.  This is the 
   * same as <tt>setValue( 0, s )</TT>.
   * @param s The String to store in the field.
   * @return <code>true</code> if the field was set successfully,
   * or false if the 
   * field's consultant rejects the string <code>s</code>.
   */
  public boolean setValueAsString( String s )
  {
    return setValue( 0, s );
  }
}