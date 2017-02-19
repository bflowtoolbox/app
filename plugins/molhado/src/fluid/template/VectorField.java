/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/VectorField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.util.Vector;

import fluid.version.VersionTracker;

/**
 * A field that can hold a list of items.  The list may have 
 * a maximum length.
 */
public class VectorField extends Field
{
  public static final int NO_LIMIT = -1;

  private Vector value;
  private boolean empty;
  private int maxSize;

  /**
   * Create a new Field with a given name and parent Template.
   * @param n The name of the field.
   * @param t The Template to which the field belongs.
   * @param versioned <code>true</code> if the field should be
   * sensitive to versions.
   * @param vt The verion tracker to use to get the version that
   * the data should be from.  (Only interesting if <code>versioned</code>
   * is <code>true</code>.
   * @param c The Field's consultant.
   * @param max The maximum length of the field.  -1 indicates
   * no maximum length.
   * @exception IllegalArgumentException
   * Thrown if <code>c</code> is <code>null</code>, or
   * if <code>max</code> < -1.
   */
  public VectorField( final String n, final Template t, 
                      final boolean versioned, final VersionTracker vt,
                      final FieldConsultant c, final int max )
  {
    super( n, t, versioned, vt, c );
    value = new Vector();
    empty = true;

    if( max < -1 ) {
      throw new IllegalArgumentException( "maximum size must be >= -1" );
    }
    maxSize = max;
  }



  //--------------------------------------------------------------------
  // New Methods
  //--------------------------------------------------------------------

  /**
   * Set the value of the field.
   * @param objs The value to place in the field.  Cannot be null.
   * @return <code>true</code> if the field was set successfully,
   * <code>false</code> if 
   * <tt>canAccept( obj )</tt> would return <tt>false</tt>.
   */
  public boolean setValueAsVector( final Vector objs )
  {
    final Object[] array = new Object[objs.size()];
    objs.copyInto( array );
    return setValue( array );
  }

  /**
   * Get the value of the field as a Vector.
   * @return The value of the field as a Vector, or <code>null</code>
   * if the field is empty.
   */
  public Vector getValueAsVector()
  {
    // Lock to prevent clearing of value between isEmpty() and
    // copyInto()
    synchronized( this )
    {
      if( isEmpty() ) {
        return null;
      } else {
        return (Vector)value.clone();
      }
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

  protected void fireValueChanged( Vector v )
  {
    Object[] o = new Object[v.size()];
    v.copyInto( o );
    super.fireValueChanged( o );
  }



  //--------------------------------------------------------------------
  // Methods from Field
  //--------------------------------------------------------------------

  /**
   * Determine if a value can be placed into the field.  Simply
   * checks to see if the lenght of <tt>objs</tt> is less
   * than the maximum size and for all <tt>i</tt>,
   * <tt>canAccept( i, objs[i] )</tt>.
   * @param objs The value to test
   * @return <tt>true</tt> iff <tt>objs</tt> can be placed in the field
   */
  public boolean acceptableToField( Object[] objs )
  {
    // No locking is needed because this doesn't use
    // local state
    if( maxSize != NO_LIMIT && objs.length > maxSize )
      return false;
    for( int i = 0; i < objs.length; i++ )
      if( !acceptableToField( i, objs[i] ) )
        return false;
    return true;
  }

  /**
   * Determine if a value can be placed in a given position
   * in the field.
   * @param pos The position in the field.
   * @param obj The value to test.
   * @return <tt>true</tt> iff the position is within the size 
   * constraints.
   */
  public boolean acceptableToField( int pos, Object obj )
  {
    // No locking is needed because this doesn't use
    // local state
    return (maxSize == NO_LIMIT) || (pos <= maxSize);
  }

  // Inherit JavaDoc
  public int getSize()
  {
    return value.size();
  }

  // Inherit JavaDoc
  public boolean setValue( final Object[] objs )
  {
    if( canAccept( objs ) )
    {
      // Lock to protect value and to make actions atomic
    lock:
      synchronized( this )
      {
        value.setSize( objs.length );
        for( int i = 0; i < objs.length; i++ )
          value.setElementAt( objs[i], i );
        setEmpty( false );
        fireValueChanged( value );
        return true;
      }
    }
    return false;
  }

  // Inherit JavaDoc
  public boolean setValue( final int pos, final Object obj )
  {
    if( canAccept( pos, obj ) )
    {
      // Lock to protect value and to make actions atomic
      synchronized( this )
      {
        value.setSize( pos + 1 );
        value.setElementAt( obj, pos );
        setEmpty( false );
        fireValueChanged( value );
      }
      return true;
    }
    return false;
  }

  // Inherit JavaDoc
  public Object[] getValue()
  {
    // Lock to prevent clearing of value between isEmpty() and
    // copyInto()
    synchronized( this )
    {
      if( isEmpty() ) {
        return null;
      } else {
        return value.toArray();
      }
    }
  }

  // Inherit JavaDoc
  public Object getValue( final int pos )
  {
    // Lock to prevent clearing of value between isEmpty()
    // and getElementAt();
    synchronized( this )
    {
      if( isEmpty() ) {
        return null;
      } else {
        return value.elementAt( pos );
      }
    }
  }

  // Inherit JavaDoc
  public boolean insertValue( final int pos, final Object obj )
  {
    boolean canProceed = canAccept( pos, obj );
    for( int i = pos+1; canProceed && i < value.size(); i++ ) {
      canProceed = canAccept( i, value.elementAt( i-1 ) ); 
    }

    if( canProceed )
    {
      // Lock to protect value and to make actions atomic
      synchronized( this )
      {
        value.setSize( pos + 1 );
        value.insertElementAt( obj, pos );
        setEmpty( false );
        fireValueChanged( value );
      }
      return true;
    }
    return false;
  }

  // Inherit JavaDoc
  public boolean addValue( final Object obj )
  {
    final boolean canProceed = canAccept( value.size(), obj );

    if( canProceed )
    {
      // Lock to protect value and to make actions atomic
      synchronized( this )
      {
        value.addElement( obj );
        setEmpty( false );
        fireValueChanged( value );
      }
      return true;
    }
    return false;
  }

  // Inherit JavaDoc
  public boolean removeValue( final int pos )
  {
    boolean canProceed = true;
    for( int i = pos; canProceed && i < value.size() - 1; i++ ) {
      canProceed = canAccept( i, value.elementAt( i+1 ) ); 
    }

    if( canProceed )
    {
      // Lock to protect value and to make actions atomic
      synchronized( this )
      {
        value.removeElementAt( pos );
        if( value.size() == 0 ) {
          setEmpty( true );
        } else {
          fireValueChanged( value );
        }
        return true;
      }
    }
    return false;
  }

  // Inherit JavaDoc
  public boolean isEmpty()
  {
    synchronized( this ) {
      return empty;
    }
  }

  // Inherit JavaDoc
  public void clearField()
  {
    // Synchronized to make this atomic
    synchronized( this )
    {
      value.clear();
      setEmpty( true );
    }
  }
}
