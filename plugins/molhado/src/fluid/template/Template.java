/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/Template.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.beans.PropertyChangeListener;
import java.util.Vector;

import fluid.version.Version;
import fluid.version.VersionMarker;
import fluid.version.VersionTracker;

/** A Template encapsulates the "front end" of a computation as a form.  The form
 * has fields (inputs to the computation) some of which need to be
 * filled in before the form can be executed.  The Template is associated
 * with a specific version of the IR, which is the version of the IR from
 * which its operand come.  
 * @see Field
 */
public abstract class Template
{
  /**
   * The name of the particular template instance.
   */
  private final String name;

  /**
   * Listeners for <code>TemplateEvent</code> messages.
   */
  private Vector listeners;
  
  /**
   * Listeners for <code>TemplateSpawnedEvent</code> messages.
   */
  private Vector spawnedListeners;

  /**
   * If <code>completed</code> is <code>true</code>, then
   * <code>startVersion</code> is the version in which
   * the template began execution, <code>endVersion</code> is the
   * version in in which execution terminated, and <code>tracker</code>
   * is a {@link fluid.version.VersionMarker} set to <code>endVersion</code>.
   * <code>tracker</code> will be <em>unaliased</em> at the time of
   * creation.
   * <P>If <code>completed</code> is <code>false</code>, then
   * <code>startVersion</code> and <code>endVersion</code> are 
   * volatile, and <code>tracker</code> is the version tracker
   * that was given to the constructor.
   */
  private boolean completed;
  private Version startVersion = null;
  private Version endVersion = null;
  private VersionTracker tracker;

  /**
   * Create a new Template.
   * @param n The name of the template
   * @param vc The version tracker used to track the IR
   */
  public Template( final String n, final VersionTracker vc ) 
  {
    name = n;
    tracker = vc;
    completed = false;
    listeners = new Vector();
    spawnedListeners = new Vector();
  }

  /**
   * Get the name of the Template
   * @return The name of the template instance
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get if the template has successfully completed.
   * @return <code>true</code> iff the Template has 
   * executed successfully.
   */
  public boolean getCompleted()
  {
    return isCompleted();
  }

  /**
   * Get if the template has successfully completed.
   * @return <code>true</code> iff the Template has 
   * executed successfully.
   */
  public boolean isCompleted()
  {
    return completed;
  }

  /**
   * Set the completion status of the template to true, and 
   * set the template's start version, end version, and version
   * tracker to their final values.
   * @param vm A version indicating where in the
   * version space the template completed.
   * @exception UnsupportedOperationException Thrown if the template
   * has already been marked as completed.
   * @exception IllegalArgumentException Thrown if the end version does not 
   * descend from the template's start version.
   */
  protected void setCompleted( final Version start, final Version end )
  {
    if( completed ) {
      throw new UnsupportedOperationException( "setCompleted has already been called on template: " + this );
    }
    if( !end.comesFrom( start ) ) {
      throw new IllegalArgumentException( "The end version does not come from the start version" );
    }

    completed = true;
    startVersion = start;
    endVersion = end;
    tracker = new VersionMarker( end );

    Field[] fields = getFields();
    for( int i = 0; i < fields.length; i++ ) {
      fields[i].fixVersion();
    }
  }

  /**
   * Get the version in which the template began execution.
   * @return The version in which the template began execution.
   * @exception UnsupportedOperationException Thrown if the template
   * has not yet completed execution.
   */
  public Version getStartVersion()
  {
    if( !completed ) {
      throw new UnsupportedOperationException( "Cannot get startVersion of an uncompleted template" );
    } else {
      return startVersion;
    }
  }

  /**
   * Get the version in which the template finished execution.
   * @return The version in which the template finished execution.
   * @exception UnsupportedOperationException Thrown if the template
   * has not yet completed execution.
   */
  public Version getEndVersion()
  {
    if( !completed ) {
      throw new UnsupportedOperationException( "Cannot get endVersion of an uncompleted template" );
    } else {
      return endVersion;
    }
  }

  /**
   * Get the VersionTracker associated with the Template.  If
   * {@link #getCompleted} is <code>true</code> the return
   * value is also unaliased.
   * @return The version tracker given to the constructor if the 
   * template has not yet completed, or a version tracker referring to
   * the version in which the template completed execution otherwise.
   */
  public VersionTracker getVersionTracker()
  {
    if( isCompleted() ) {
      return new VersionMarker( tracker.getVersion() );
    } else {
      return tracker;
    }
  }

  /**
   * Get the Fields of the Template.
   * @return An array of the Fields in the Template
   */
  public abstract Field[] getFields();

  /**
   * Query if the Template has enough fields filled to execute.
   * @return <tt>true</tt> if the Template has enough information to run.
   */
  public abstract boolean readyToRun();

  /**
   * Execute the Template.  The method is responsible for
   * using the proper <Code>VersionTracker</code> idioms.
   * The implementation must fire a <code>TemplateEvent</code> indicating
   * it is about to begin execution before any work is done.
   * The implemenation
   * must fire a <code>TemplateEvent</code> indicating whether execution was
   * successful or not.  If any subsidiary templates are created during
   * execution, <code>TemplateSpawnedEvent</code>s must be sent. The
   * implementation is also responsible for making sure that the
   * <code>startVersion</code> and <code>endVersion</code>
   * properties are properly set.
   * @exception UnsupportedOperationException Thrown if 
   * {@link #readyToRun()} is <code>false</code>, or if 
   * {@link #getCompleted()} is <code>true</code>.
   * @see #setCompleted
   */
  public abstract void run();

  /**
   * Add template listener.
   * @param l The listener to add.
   */
  public void addTemplateListener( final TemplateListener l )
  {
    listeners.addElement( l );
  }

  /**
   * Remove template listener.
   * @param l The listener to remove.
   */
  public void removeTemplateListener( final TemplateListener l )
  {
    listeners.removeElement( l );
  }

  /**
   * Notify all the template listeners that the execution has
   * completed.
   * @param e The event to send to the listeners.
   */
  protected void fireTemplateStartEvent( final TemplateEvent e )
  {
    final Vector l;
    synchronized( listeners ) {
      l = (Vector)listeners.clone();
    }
    for( int i = 0; i < l.size(); i++ ) {
      ((TemplateListener)l.elementAt( i )).templateIsRunning( e );
    }
  }

  /**
   * Notify all the template listeners that the execution has
   * completed.
   * @param e The event to send to the listeners.
   */
  protected void fireTemplateDoneEvent( final TemplateEvent e )
  {
    final Vector l;
    synchronized( listeners ) {
      l = (Vector)listeners.clone();
    }
    for( int i = 0; i < l.size(); i++ ) {
      ((TemplateListener)l.elementAt( i )).templateIsDone( e );
    }
  }

  /**
   * Add template spawned listener.
   * @param l The listener to add.
   */
  public void addTemplateSpawnedListener( final TemplateSpawnedListener l )
  {
    spawnedListeners.addElement( l );
  }

  /**
   * Remove template spawned listener.
   * @param l The listener to remove.
   */
  public void removeTemplateSpawnedListener( final TemplateSpawnedListener l )
  {
    spawnedListeners.removeElement( l );
  }

  /**
   * Notify all the template spawned listeners that that
   * a subsidiary template has been spawned.
   * @param template The subsidiary template being spawned.
   */
  protected void fireTemplateSpawnedEvent( final Template template )
  {
    final TemplateSpawnedEvent e = new TemplateSpawnedEvent( this, template );

    final Vector l;
    synchronized( spawnedListeners ) {
      l = (Vector)spawnedListeners.clone();
    }
    for( int i = 0; i < l.size(); i++ ) {
      ((TemplateSpawnedListener)l.elementAt( i )).templateSpawned( e );
    }
  }

  /**
   * Register a <code>PropertyChangeListener</code> that is notified whenever the
   * value of a field changes.  The source of the event will be the
   * Field whose value has changed.  Two different properties of
   * the Field can change: <code>Field.VALUE_PROPERTY</code> which indicates
   * that the actual value of the field has changed, and
   * <code>Field.EMPTY_PROPERTY</code> which indicates whether the field has
   * a value. The new/old values of a <code>Field.VALUE_PROPERTY</code> change
   * events are <code>Vector</code> objects, while the new/old values
   * of <code>Field.EMPTY_PROPERTY</code> change events are <code>Boolean</code> 
   * objects.
   * @param l The listener to add
   */
  public abstract void addFieldListener( PropertyChangeListener l );

  /**
   * Remove a PropertyChangeListener.
   * @param l The listener to remove.
   */
  public abstract void removeFieldListener( PropertyChangeListener l );
}
  
