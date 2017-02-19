/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/ScalarField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import fluid.version.VersionTracker;

/**
 * A field that holds exactly one value.  The value is stored at 
 * position 0 of the <tt>value</tt> property.
 */

public class ScalarField extends Field
{
  private final Object[] value;
  private boolean empty;

  /**
   * Create a new ScalarField with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param c The Field's consultant.
   * @param versioned <code>true</code> if the field should be
   * sensitive to versions.
   * @param vt The verion tracker to use to get the version that
   * the data should be from.  (Only interesting if <code>versioned</code>
   * is <code>true</code>.
   * @exception IllegalArgumentException
   * Thrown if <code>c</code> is <code>null</code>.
   */
  public ScalarField( final String n, final Template t,
                      final boolean versioned, final VersionTracker vt,
                      final FieldConsultant c )
  {
    super( n, t, versioned, vt, c );
    value = new Object[1];
    value[0] = null;
    empty = true;
  }

  public int getSize()
  {
    return 1;
  }

  /**
   * Does the field have a value? 
   * @return <tt>true</tt> iff the field has a value.
   */
  public boolean isEmpty()
  {
    synchronized( this )
    {
      return empty;
    }
  }

  /**
   * Private setter method for the "empty" property.  Used to
   * encapsulate the sending of PropertyChangedEvents.
   */
  private void setEmpty( final boolean b )
  {
    boolean changed = false;
    synchronized( this )
    {
      if( b != empty )
      {
        empty = b;
        changed = true;
      }
    }
    // This part doesn't need to be synchronized
    // but, it often will be because of the 
    // synchronization of the caller of setEmpty()
    if( changed )
    {
      fireEmptyChanged( b );
    }
  }

  /**
   * Make the field empty--- causes the empty property to be true.
   */
  public void clearField()
  {
    // Synchronize to lock access to value, and to
    // keep both statements atomic
    synchronized( this )
    {
      value[0] = null;
      setEmpty( true );
    }
  }

  /**
   * Determine if a value can be placed into the field.
   * @param objs The value to test
   * @return <tt>true</tt> iff <tt>objs</tt> has a length of 1 and
   * <tt>acceptableToField( 0, objs[0] )</tt> is true.
   */
  public boolean acceptableToField( final Object[] objs )
  {
    // No need to lock the object because
    // no local state is being used
    return (objs.length == 1) && canAccept( 0, objs[0] );
  }

  /**
   * Determine if a value can be placed in a given position
   * in the field.
   * @param pos The position in the field.
   * @param obj The value to test.
   * @return <tt>true</tt> iff <tt>pos</tt> is 0.
   */
  public boolean acceptableToField( final int pos, final Object obj )
  {
    // No need to lock the object because
    // no local state is being used
    return (pos == 0);
  }

  /**
   * Set the value of the field. 
   * @param objs The value to place in the field.
   * @return <code>false</code> if <tt>objs.length</tt> is not 1, or if 
   * <tt>canAccept( objs )</tt> is false.  Otherwise returns <code>true</code>,
   * indicating the that the value was successfully set.
   */
  public boolean setValue( final Object[] objs )
  {
    if( canAccept( objs ) )
    {
      // Synchronize to lock access to value, and to
      // keep both statements atomic
      synchronized( this )
      {
        value[0] = objs[0];
        setEmpty( false );
      }
      fireValueChanged( value );
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Set the value of a position in the field. 
   * @param pos The position of the field to set.
   * @param obj The value to put in the given position.
   * @return <code>false</code> if <tt>canAccept( 0, obj )</tt>
   * would return <tt>false</tt>, and <code>true</code> otherwise,
   * indicating that the value was set successfully.
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not zero.
   */
  public boolean setValue( final int pos, final Object obj )
  {
    if( pos != 0 )
    {
      throw new ArrayIndexOutOfBoundsException();
    }
    if( canAccept( pos, obj ) )
    {
      // Synchronize to lock access to value, and to
      // keep both statements atomic
      synchronized( this )
      {
        value[0] = obj;
        setEmpty( false );
      }
      fireValueChanged( value );
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Get the value of the field.
   * @return The value of the field, or <code>null</code> if the
   * field is empty.
   */
  public Object[] getValue()
  {
    // Lock to prvent the value from being cleared between isEmpty()
    // and cloning
    synchronized( this )
    {
      if( isEmpty() )
      {
        return null;
      }
      else
      {
        return (Object[])(value.clone());
      }
    }
  }

  /**
   * Get the value of a position in the field.  An exception is thrown
   * if the position is not 0.
   * @return The value of the given position in the field, or
   * <code>null</code> if the field is empty.  If <code>null</code> is
   * a legal value for the field, then the caller should call
   * <code>isEmpty()</code> when <code>null</code> is returned.
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not zero.
   */
  public Object getValue( final int pos )
  {
    // Lock to prvent the value from being cleared between isEmpty()
    // and return of value
    synchronized( this )
    {
      if( isEmpty() )
      {
        return null;
      }
      else if( pos != 0 )
      {
        throw new ArrayIndexOutOfBoundsException();
      }
      else
      {
        return value[0];
      }
    }
  }

  public boolean insertValue( final int pos, final Object obj )
  {
    synchronized( this )
    {
      if( isEmpty() ) {
        return setValue( pos, obj );
      } else {
        return false;
      }
    }
  }

  public boolean addValue( final Object obj )
  {
    synchronized( this )
    {
      if( isEmpty() ) {
        return setValue( 0, obj );
      } else {
        return false;
      }
    }
  }

  public boolean removeValue( final int pos )
  {
    synchronized( this )
    {
      if( isEmpty() ) {
        return true;
      } else {
        setEmpty( true );
        return true;
      }
    }
  }
}
