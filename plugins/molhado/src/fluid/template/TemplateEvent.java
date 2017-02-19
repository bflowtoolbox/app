/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/TemplateEvent.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import java.util.EventObject;
import java.util.Vector;

public abstract class TemplateEvent
extends EventObject
{
  private final boolean isStartEvent;

  protected TemplateEvent( final Template src, final boolean isStart )
  {
    super( src );
    isStartEvent = isStart;
  }

  /**
   * Is the event a template start event?
   * @return <code>true</code> iff the event indicates the template
   * is started execution; <code>false</code>iff the event indicates the
   * template has completed execution.
   */
  public boolean isStart()
  {
    return isStartEvent;
  }

  /**
   * Get the event sources as a Template.  Returns the same
   * object as {@link EventObject#getSource}.
   * @return The source as a <CODE>Template</CODE>.
   */
  public Template getSourceAsTemplate()
  {
    return (Template)getSource();
  }

  /** 
   * Did the execution complete successfully?
   * @return <tt>true</tt> iff the execution completed without an error
   * @exception UnsupportedOperationException thrown if 
   * {@link #isStart()} is <code>true</code>.
   */
  public abstract boolean getSuccess();

  /**
   * Get the message.
   * @return The message associated with the template result.
   * @exception UnsupportedOperationException thrown if 
   * {@link #isStart()} is <code>true</code>.
   */
  public abstract String getMessage();

  /**
   * Get the node sets.
   * @exception UnsupportedOperationException thrown if 
   * {@link #isStart()} is <code>true</code>.
   */
  public abstract Vector getNodeSets();

  /**
   * Did the template modify any state?
   * @return <code>true</code> iff the template completed 
   * successfully and it modified state.
   * @exception UnsupportedOperationException thrown if 
   * {@link #isStart()} is <code>true</code>.
   */
  public abstract boolean getModifiedState();




  public static class TemplateStartEvent
  extends TemplateEvent
  {
    public TemplateStartEvent( final Template src )
    {
      super( src, true );
    }

    public boolean getSuccess()
    {
      throw new UnsupportedOperationException( "Event is start event." );
    }

    public String getMessage()
    {
      throw new UnsupportedOperationException( "Event is start event." );
    }

    public Vector getNodeSets()
    {
      throw new UnsupportedOperationException( "Event is start event." );
    }

    public boolean getModifiedState()
    {
      throw new UnsupportedOperationException( "Event is start event." );
    }      
  }

  public static class TemplateDoneEvent
  extends TemplateEvent
  {
    /**
     * Flag indicating whether the execution of the template
     * was successful or not.
     */
    private final boolean success;

    /**
     * A message to display describing the results of execution.
     */
    private final String message;

    /**
     * A Vector of {@link NodeSet} objects for providing
     * error feedback.
     */
    private final Vector nodeSets;

    /**
     * <code>true</code> iff the execution of the template was 
     * successful and the execution modified the IR.
     */
    private final boolean changedState;

    /**
     * Create a new TemplateEvent without any NodeSet information,
     * that assumes the template modified state if it was 
     * successful.
     * @param source The Template that has completed.
     * @param flag <CODE>true</CODE> iff the template completed successfully.
     * @param msg Message associated with the result.
     */
    public TemplateDoneEvent( final Template source, final boolean flag,
                          final String msg )
    {
      this( source, flag, flag, msg );
    }

    /**
     * Create a new TemplateEvent without any NodeSet information.
     * @param source The Template that has completed.
     * @param flag <CODE>true</CODE> iff the template completed successfully.
     * @param changed <CODE>true</code> iff the template completed successfully
     * <em>and</em> it modified state
     * @param msg Message associated with the result.
     */
    public TemplateDoneEvent( final Template source, final boolean flag,
                          final boolean changed, final String msg )
    {
      this( source, flag, changed, msg, new Vector() );
    }

    /**
     * Create a new TemplateEvent that assumes the template modified
     * state if it was successful.
     * @param source The Template that has completed.
     * @param flag <CODE>true</CODE> iff the template completed successfully.
     * @param changed <CODE>true</code> iff the template completed successfully
     * <em>and</em> it modified state
     * @param msg Message associated with the result.
     * @param sets A Vector of {@link NodeSet} objects 
     */
    public TemplateDoneEvent( final Template source, final boolean flag,
                          final String msg, final Vector sets )
    {
      this( source, flag, flag, msg, sets );
    }

    /**
     * Create a new TemplateEvent.
     * @param source The Template that has completed.
     * @param flag <CODE>true</CODE> iff the template completed successfully.
     * @param changed <CODE>true</code> iff the template completed successfully
     * <em>and</em> it modified state
     * @param msg Message associated with the result.
     * @param sets A Vector of {@link NodeSet} objects 
     */
    public TemplateDoneEvent( final Template source, final boolean flag,
                          final boolean changed, final String msg,
                          final Vector sets )
    {
      super( source, false );
      success = flag;
      changedState = changed;
      message = msg;
      nodeSets = sets;
    }

    public boolean getSuccess()
    {
      return success;
    }

    public String getMessage()
    {
      return message;
    }

    public Vector getNodeSets()
    {
      return nodeSets;
    }

    /**
     * Did the template modify any state?
     * @return <code>true</code> iff the template completed 
     * successfully and it modified state.
     */
    public boolean getModifiedState()
    {
      return changedState;
    }
  }
}
