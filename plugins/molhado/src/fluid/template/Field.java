/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/Field.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import fluid.version.VersionMarker;
import fluid.version.VersionTracker;

/**
 * Interface for Template fields.  The "empty" and "value" properties
 * are bound.  The property change events are not guaranteed to
 * contain the old values.  It is guaranteed, however, that a
 * <code>PropertyChangeEvent</code> for "empty" will only be sent when the property
 * has changed, so the old value can be inferred.  An event
 * is sent for "value" every time it is set, or a position 
 * in the value is set, even though the overall value of the
 * "value" property may not have changed.
 *
 * <P>By implementing the methods <CODE>canAccept( Object[] )</CODE> and
 * <CODE>canAccept( int, Object )</CODE> a field can limit what 
 * kinds of objects are placed in the fields.  This can be 
 * used as a general filter, but may not be specific enough for
 * some Templates.  Once a value has been determined to be
 * acceptable to the field, but before the value is placed
 * into the field, the field calls the
 * <CODE>isObjectAcceptable()</CODE> method of its 
 * <CODE>FieldConsultant</CODE>, which allows the
 * template to have the final say on whether the value
 * should be placed in the field.  This allows the Template,
 * for example, to take advantage of semantic information
 * that the Field doesn't have access to.
 *
 * @see FieldConsultant
 * @see Template
 */
public abstract class Field
implements Cloneable
{
  public Object clone()
  throws CloneNotSupportedException
  {
    return super.clone();
  }


  /**
   * Name of the value property.
   */
  public static final String VALUE_PROPERTY = "value";
  
  /**
   * Name of the empty property.
   */
  public static final String EMPTY_PROPERTY = "empty";

  /**
   * The default consultant used by fields; an instance of
   * {@link DefaultConsultant}.
   */
  public static final FieldConsultant DEFAULT_CONSULTANT = new DefaultConsultant();

  /**
   * Value for the version tracker that indicates the field
   * should use the tracker of its containing Template.
   */
  public static final VersionTracker USE_TEMPLATES_TRACKER = null;


  /**
   * The field's name.
   */
  private String name;

  /**
   * The field's description.
   */
  private String description;

  /**
   * The template that contains this field.
   */
  private Template template;

  /**
   * The field's consultant.
   */
  private FieldConsultant consultant;

  /**
   * <code>true</code> iff the data accepted by the field
   * is sensitive to the current version.
   */
  private final boolean usesVersion;

  /**
   * The version tracker used by the field.
   */
  private VersionTracker tracker;

  /**
   * The field's value listeners.
   */
  private PropertyChangeSupport valueListeners;

  /**
   * The field's empty listeners.
   */
  private PropertyChangeSupport emptyListeners;



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
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public Field( final String n, final Template t,
                final boolean versioned, final VersionTracker vt,
                final FieldConsultant c )
  {
    name = n;
    template = t;

    if( c == null )
      throw new IllegalArgumentException( "Consultant cannot be null." );
    consultant = c;

    usesVersion = versioned;
    tracker = vt;

    valueListeners = new PropertyChangeSupport( this );
    emptyListeners = new PropertyChangeSupport( this );
  }

  /**
   * Set the field's description.
   * @exception UnsupportedOperationException Thrown if the description is
   * already set.
   */
  public void setDescription( final String desc )
  {
    if( description == null ) {
      description = desc;
    } else {
      throw new UnsupportedOperationException( "The description of field " + getName() + " is already set!" );
    }
  }

  /** 
   * Get the field's description.
   */
  public String getDescription()
  {
    if( description == null ) {
      return "The field's description has not been set!";
    } else {
      return description;
    }
  }

  /**
   * Determine if the field data is sensitive to versioning.
   * @return <code>true</code> iff the field data is 
   * sensitive to versioning.
   */
  public boolean usesVersionTracker()
  {
    return usesVersion;
  }

  /**
   * Get the version tracker used by the field.  The data in the field
   * must be from the version provided by the tracker.  This attribute is 
   * only interesting if {@link #usesVersionTracker()} is <code>true</code>
   * @return The version tracker used by the field, or
   * <code>null</code> if {@link #usesVersionTracker()} is <code>false</code>.
   */
  public VersionTracker getVersionTracker()
  {
    if( usesVersion ) {
      if( tracker == USE_TEMPLATES_TRACKER ) {
        return getTemplate().getVersionTracker();
      } else {
        return tracker;
      }
    } else {
      return null;
    }
  }

  /**
   * If the field is currently using its template's tracker
   * then this method does nothing.  If the field has it's own
   * tracker, then the field is set to point to a copy of the
   * tracker so that it won't be affected by anyone moving
   * the tracker's version.
   * @param marker The version marker to use.
   */
  protected void fixVersion()
  {
    if( usesVersion && tracker != USE_TEMPLATES_TRACKER ) {
      tracker = new VersionMarker( tracker.getVersion() );
    }
  }

  /**
   * Get the name of the field.
   * @return The name of the field.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get the Template to which the field belongs.
   * @return The Template to which the field belongs.
   */
  public Template getTemplate()
  {
    return template;
  }

  /**
   * Get the field's consultant.
   * @return The fields consultant.
   */
  public FieldConsultant getConsultant()
  {
    return consultant;
  }

  /**
   * Get the size of the field.  A field's size is the
   * number of data items contained in its value.
   */
  public abstract int getSize();

  /**
   * Does the field have a value? 
   * @return <CODE>true</CODE> iff the field has a value.
   */
  public abstract boolean isEmpty();

  /**
   * Make the field empty&mdash; causes the empty property to be true.
   */
  public abstract void clearField();

  /**
   * Determine if a value can be placed into the field.  The result of
   * this method <em>must</em> be the same as 
   * <code>acceptableToField( objs ) && acceptableToConsultant( objs )</code>.
   * @param objs The value to test
   * @return <CODE>true</CODE> iff <CODE>objs</CODE> can be placed in the field
   */
  public final boolean canAccept( final Object[] objs )
  {
    return acceptableToField( objs ) 
        && acceptableToConsultant( objs );
  }

  /**
   * Determine if a value can be placed in a given position
   * in the field.  Returns false if <CODE>pos</CODE> is not a
   * position in the field.  The result of this method <em>must</em>
   * be the same as <code>acceptableToField( pos, obj ) &&
   * acceptableToConsultant( pos, obj )</code>.
   * @param pos The position in the field.
   * @param obj The value to test.
   * @return <CODE>true</CODE> iff <CODE>obj</CODE> can be put in 
   * position <CODE>pos</CODE> of the field.
   */
  public final boolean canAccept( final int pos, final Object obj )
  {
    return acceptableToField( pos, obj )
        && acceptableToConsultant( pos, obj );
  }

  /**
   * Determine if the field's consultant allows for
   * <code>Obj</code> to be place in position <code>pos</code> of
   * the field.
   * @param pos The position in the field.
   * @param obj The value to test.
   * @return <code>true</code> iff the consultant allows <CODE>obj</CODE> to be put in 
   * position <CODE>pos</CODE> of the field.
   */
  public boolean acceptableToConsultant( final int pos, final Object obj )
  {
    return consultant.isObjectAcceptable( this, pos, obj );
  }

  /**
   * Determine if the field's consultant allows for
   * <code>Obj</code> to be placed in the field.
   * @param obj The value to test.
   * @return <code>true</code> iff the consultant allows <CODE>obj</CODE> to be put in 
   * the field.
   */
  public boolean acceptableToConsultant( final Object[] obj )
  {
    return consultant.isObjectAcceptable( this, obj );
  }

  /**
   * Determine if the field allows for the <code>obj</code> to be placed
   * at position <code>pos</code> of the field.  Returns <code>false</code>
   * if <code>pos</code> is not a position in the field.
   * @param pos The position in the field.
   * @param obj The value to test.
   * @return <code>true</code> iff the field allows <CODE>obj</CODE> to be put in 
   * position <CODE>pos</CODE> of the field.
   */
  public abstract boolean acceptableToField( final int pos, final Object obj );

  /**
   * Determine if the field allows for <code>Obj</code> to be placed in the field.
   * @param obj The value to test.
   * @return <code>true</code> iff the field allows <CODE>obj</CODE> to be put in 
   * the field.
   */
  public abstract boolean acceptableToField( final Object[] obj );

  /**
   * Add a listener to the "value" property.
   * @param l The listener to add
   */
  public void addValueListener( PropertyChangeListener l )
  {
    valueListeners.addPropertyChangeListener( l );
  }

  /**
   * Remove a listener from the "value" property.
   * @param l The listener to add
   */
  public void removeValueListener( PropertyChangeListener l )
  {
    valueListeners.removePropertyChangeListener( l );
  }

  /**
   * Send a PropertyChangedEvent for the "value" property.
   * @param newVlaue the new value of property "value".
   */
  protected void fireValueChanged( Object[] newValue )
  {
    valueListeners.firePropertyChange( VALUE_PROPERTY, null, newValue );
  }

  /**
   * Add a listener to the "empty" property.
   * @param l The listener to add
   */
  public void addEmptyListener( PropertyChangeListener l )
  {
    emptyListeners.addPropertyChangeListener( l );
  }

  /**
   * Remove a listener from the "empty" property.
   * @param l The listener to add
   */
  public void removeEmptyListener( PropertyChangeListener l )
  {
    emptyListeners.removePropertyChangeListener( l );
  }

  /**
   * Send a PropertyChangedEvent for the "empty" property.
   * @param newVlaue the new value of property "empty".
   */
  protected void fireEmptyChanged( boolean newValue )
  {
    emptyListeners.firePropertyChange( EMPTY_PROPERTY, null,
      newValue ? Boolean.TRUE : Boolean.FALSE );
  }

  /**
   * Set the value of the field. 
   * @param objs The value to place in the field.
   * @return <code>true</code> if the value was set, <code>false</code>
   * if the <cod>objs</code> is not an acceptable value for the field.
   */
  public abstract boolean setValue( Object[] objs );

  /**
   * Set the value of a position in the field.  
   * @param pos The position of the field to set.
   * @param obj The value to put in the given position.
   * @return <code>true</code>if the valuse at <code>pos</code> was
   * changed, <code>false</code> if <code>obj</code> is not acceptable
   * for the given position.
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not in the field.
   */
  public abstract boolean setValue( int pos, Object obj );

  /**
   * Get the value of the field.  An exception is thrown
   * if the field has not been set.
   * @return The value of the field, or <code>null</code> if the field
   * is empty.
   */
  public abstract Object[] getValue();

  /**
   * Get the value of a position in the field.  An exception is thrown
   * if the field has not been set.
   * @return The value of the given position in the field, or 
   * <code>null</code> if the field is empty.  Because <code>null</code>
   * could be a legal value in the field, the caller should always
   * call <code>isEmpty()</code> when <code>null</code> is returned.
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not in the field.
   */
  public abstract Object getValue( int pos );

  /**
   * Insert a value at the given position.
   * @param pos The position of the field to set.
   * @param obj The value to put in the given position.
   * @return <code>true</code> if the value at the given position was
   * changed successfully, <false> if 
   * <tt>canAccept( i, o_i )</tt> would return <tt>false</tt>
   * for i >= pos and o_pos == obj and o_i == getValue( i-1 ).
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not in the field.
   */
  public abstract boolean insertValue( int pos, Object obj );

  /**
   * Add a value to the end of the field.
   * @param obj The value to append.
   * @return <code>true</code> if the value was added
   * changed successfully, <false> if 
   */
  public abstract boolean addValue( Object obj );

  /**
   * Remove a value from the given position.  Note that if the 
   * field size becomes 0, the field becomes empty.
   * @param pos The position of the field to remove.
   * @return <code>true</code> if the value at the given position was
   * changed successfully, <false> if 
   * <tt>canAccept( i, o_i )</tt> would return <tt>false</tt>
   * for i >= pos and o_i == getValue( i+1 ).
   * @exception ArrayIndexOutOfBoundsException Thrown if the
   * given position is not in the field.
   */
  public abstract boolean removeValue( final int pos );
}
