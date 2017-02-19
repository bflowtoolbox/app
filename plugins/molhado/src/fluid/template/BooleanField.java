/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/BooleanField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Field that holds a Java Boolean.
 */
public class BooleanField
extends StrategizedField
{
  private final String trueLabel;
  private final String falseLabel;

  /**
   * Create a new field with a given name and parent Template, and with
   * the given true and false labels.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @param tl The Field's true label.
   * @param fl The Field's false label.
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public BooleanField( final String n, final Template t,
                       final FieldConsultant c,
                       final String tl, final String fl )
  {
    super( n, t, false, null, c, BooleanStrategy.prototype );
    trueLabel = tl;
    falseLabel = fl;
  }

  /**
   * Create a new field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public BooleanField( final String n, final Template t,
                       final FieldConsultant c )
  {
    this( n, t, c, "True", "False" );
  }

  /**
   * Create a new field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   */
  public BooleanField( final String n, final Template t )
  {
    this( n, t, DEFAULT_CONSULTANT );
  }

  /**
   * Create a new field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param tl The Field's true label.
   * @param fl The Field's false label.
   */
  public BooleanField( final String n, final Template t,
                       final String tl, final String fl )
  {
    this( n, t, DEFAULT_CONSULTANT, tl, fl );
  }


  //--------------------------------------------------------------------
  // True/False label Methods
  //--------------------------------------------------------------------

  /** 
   * Get the description of what true means.
   */
  public String getTrueLabel()
  {
    return trueLabel;
  }

  /**
   * Get the description of what false means.
   */
  public String getFalseLabel()
  {
    return falseLabel;
  }
  
  //--------------------------------------------------------------------
  // Convienence Methods
  //--------------------------------------------------------------------

  /**
   * Get the Boolean stored in the field.  This is the 
   * same as <tt>(Boolean)getValue( 0 )</TT>.
   * @return The Boolean stored in the field, or <code>null</code>
   * if the field is empty.
   */
  public Boolean getValueAsBoolean()
  {
    return (Boolean)getValue( 0 );
  }

  /**
   * Set the Boolean stored in the field.  This is the 
   * same as <tt>setValue( 0, b )</TT>.
   * @param s The Boolean to store in the field.
   * @return <code>true</code> if the field was set successfully,
   * or false if the 
   * field's consultant rejects the Boolean <code>b</code>.
   */
  public boolean setValueAsBoolean( Boolean b )
  {
    return setValue( 0, b );
  }

  /**
   * Set the Boolean stored in the field. 
   * @param s The boolean to store in the field.
   * @return <code>true</code> if the field was set successfully,
   * or false if the 
   * field's consultant rejects the Boolean <code>b</code>.
   */
  public boolean setValueAsBoolean( boolean b )
  {
    return setValue( 0, b ? Boolean.TRUE : Boolean.FALSE );
  }
}
