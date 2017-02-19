/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/IRNodeField.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import fluid.ir.IRNode;
import fluid.tree.GraphLabel;
import fluid.tree.Operator;
import fluid.version.VersionTracker;

/**
 * Field that holds a single IRNode.  The IRNode to be placed in the
 * field can be filtered by operator type.
 */
public class IRNodeField
extends StrategizedField
{
  public static final Operator[] ANY_IRNODE = null;

  protected IRNodeField( final String n, final Template t,
                         final VersionTracker vt, final FieldConsultant c,
                         final IRNodeStrategy s )
  {
    super( n, t, true, vt, c, s );
  }

  /**
   * Create a new field that accepts IRNodes based on
   * the node's operator.
   * @param n The field's name.
   * @param t The Template the field belongs to.
   * @param vt The verion tracker to use to get the version that
   * the data should be from.
   * @param c The Field's consultant.
   * @param ops Array of operators to use as filters.  An IRNode
   * will only be accepted if it's operator type is a descendent
   * of an operator in the list.  If the list is <tt>null</tt>
   * then any IRNode will be accepted.
   * @param gl The GraphLabel instance to use to get the operator of
   * a node.  Must be non-<code>null</code> if <code>ops</code> is
   * non-<code>null</code>.
   * @exception IllegalArgumentException Thrown if <code>c</code> is <code>null</code>.
   */
  public IRNodeField( final String n, final Template t,
                      final VersionTracker vt, final FieldConsultant c,
                      final Operator[] ops, final GraphLabel gl )
  {
    this( n, t, vt, c, new IRNodeStrategy( gl, ops ) );
  }

  /**
   * Create a new field that accepts IRNodes based on
   * the node's operator.
   * @param n The field's name.
   * @param t The Template the field belongs to.
   * @param vt The verion tracker to use to get the version that
   * the data should be from.
   * @param ops Array of operators to use as filters.  An IRNode
   * will only be accepted if it's operator type is a descendent
   * of an operator in the list.  If the list is <tt>null</tt>
   * then any IRNode will be accepted.
   * @param gl The GraphLabel instance to use to get the operator of
   * a node.  Must be non-<code>null</code> if <code>ops</code> is
   * non-<code>null</code>.
   */
  public IRNodeField( final String n, final Template t,
                      final VersionTracker vt,
                      final Operator[] ops, final GraphLabel gl )
  {
    this( n, t, vt, DEFAULT_CONSULTANT, ops, gl );
  }

  /**
   * Create a new field that accepts any IRNode.
   * @param n The field's name.
   * @param t The Template the field belongs to.
   * @param vt The verion tracker to use to get the version that
   * the data should be from.
   */
  public IRNodeField( final String n, final Template t,
                      final VersionTracker vt )
  {
    this( n, t, vt, DEFAULT_CONSULTANT, ANY_IRNODE, null );
  }



  // Convienence Methods
  
  /**
   * Get the operators accepted by the field.  The field
   * Will accept only those IRNode's whose operator type
   * is a included by one of the return operators.
   * @return An array of the operators accepted by the field.
   */
  public Operator[] getOperators()
  {
    return ((IRNodeStrategy)getStrategy()).getOperators();
  }

  /**
   * Get the IRNode stored in the field.  This is the 
   * same as <tt>(IRNode)getValue( 0 )</TT>.
   * @return The IRNode stored in the field, or <code>null</code>
   * if the field is empty.
   */
  public IRNode getValueAsIRNode()
  {
    return (IRNode)getValue( 0 );
  }

  /**
   * Set the IRNode stored in the field.  This is the 
   * same as <tt>setValue( 0, n )</TT>.
   * @param n The IRNode to store in the field.
   * @return <code>true</code> if the field was set successfully,
   * <code>false</code> if <tt>n</tt>
   * doesn't have the appropriate operator, or if the 
   * field's consultant rejects it.
   */
  public boolean setValueAsIRNode( IRNode n )
  {
    return setValue( 0, n );
  }
}