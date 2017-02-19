/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/FieldStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Interface for defining a strategy for defining what 
 * values may be stored by fields.  A strategy comes in
 * two flavors: scalar and vector.  A field with a scalar
 * strategy has a value of length one, and the scalar value
 * is stored in position 0.  A field with a vector strategy
 * may have a value of length > 1.  The <code>MaxSize</code> property
 * determines the maximum size the field value may have.
 * A vector stragety uses other strategies to determine
 * what values may be placed in each position of the 
 * field.  The indexed property <code>StrategyForPos</code>
 * returns the strategy used for a given position.  This 
 * allows strategies to be composed and decomposed.
 *
 * <P>The minimum and maximum sizes of vector strategies
 * are fixed, and may not, therefore, change over the lifetime
 * of the strategy.
 *
 * <P>The value of a vector stategy has type <code>Object[]</code>.
 *
 * <P>In what follows the phrase <i>the value</i> has the following
 * meaning: if the strategy is being used by a field directly,
 * it refers to the field's value.  If the strategy is being used
 * by a vector strategy to describe the legal values of a position,
 * it refers to the value that may be placed in the position 
 * described by the strategy.
 *
 * @author Aaron Greenhouse
 */
public interface FieldStrategy
{
  /**
   * Value indicating that a field has no maximum size.
   */
  public static int NO_MAXIMUM_SIZE = -1;

  /**
   * Is the strategy a scalar strategy?  If this returns <code>false</code>
   * {@link #isVector} must return <code>true</code>.
   */
  public boolean isScalar();

  /**
   * Is the strategy a vector strategy?  If this returns <code>false</code>
   * {@link #isScalar} must return <code>true</code>.
   */
  public boolean isVector();

  /**
   * Determine if the value may be set to the given array of objects.
   * If the strategy is scalar, any array whose length is not 1 must
   * be rejected.  If the strategy is vector, any array whose length
   * is less than <code>MinSize</code> or greater than <code>MaxSize</code>
   * must be rejected.
   * @param objs The value to consider
   * @return <code>true</code> if the value can be set to <code>objs</code>,
   * <code>false</code> if the <code>objs</code> has been rejected.
   */
  public boolean acceptableObject( Object[] objs );

  /**
   * Determine if a given position of the value may be set to the
   * given object.  If the strategy is scalar it must return <code>false</code>
   * if the position is not 0.  If the strategy is vector it must
   * return <code>false</code> if pos is not in [0, <code>MaxSize</code>).
   * @param pos The position in the value to test
   * @param obj The object to consider
   * @return <code>true</code> if <code>obj</code> can be stored in position
   * <code>pos</code> of the value.
   */
  public boolean acceptableObject( int pos, Object obj );

  /**
   * Get the minimum size of the value.  If the strategy is
   * scalar, this must return <code>1</code>.  The minimum
   * size may not be less than 0.
   */
  public int getMinSize();

  /**
   * Get the maximum size of the value.  If the strategy is
   * scalar, this must return <code>1</code>.  If there is no
   * maximum size, then the value {@link #NO_MAXIMUM_SIZE} is returned.
   */
  public int getMaxSize();

  /**
   * Do all the positions in the value have the same strategy?
   * This must return <code>true</code> for scalar stragies.
   * If the strategy is vector and this returns <code>true</code>
   * then {@link #getStrategyForPos} returns the same strategy
   * object for all legal positions.
   */
  public boolean sameForAllPositions();

  /**
   * Get the strategy used to describe an element at a given position.
   * If the strategy is scalar this must return null.
   * @param pos The position whose stategy is desired
   * @return The strategy for the given position if the strategy is
   * vector.  If the strategy is scalar and <code>pos</code> is 0,
   * then <code>null</code> is returned.
   * @exception ArrayIndexOutOfBoundsException Thrown if 
   * <code>MaxSize</code> is not <code>NO_MAXIMUM_SIZE</CODE> and the
   * given position is greater than or equal to <code>MaxSize</code>.
   */
  public FieldStrategy getStrategyForPos( int pos );
}
