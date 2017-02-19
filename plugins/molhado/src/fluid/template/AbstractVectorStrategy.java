/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/AbstractVectorStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Abstract class for implementing vector strategies.
 * Subclasses only need to implement 
 * {@link #acceptableInPos}, {@link #sameForAllPositions},
 * and {@link #getStrategyForPosImpl}.
 *
 * @author Aaron Greenhouse
 */

public abstract class AbstractVectorStrategy
implements FieldStrategy
{
  private final int minSize;
  private final int maxSize;

  /**
   * Create a new vector strategy with the given
   * minimum and maximum sizes.
   */
  public AbstractVectorStrategy( final int min, final int max )
  {
    if( min < 0 ) {
      throw new IllegalArgumentException( "minimum length must >= 0, got " + min );
    } else if( (max != NO_MAXIMUM_SIZE) && (max < min) ) {
      throw new IllegalArgumentException( "maximum length must be >= minimum length, got max = " + max + ", min = " + min );
    }

    minSize = min;
    maxSize = max;
  }
  
  /**
   * Create a new vector strategy with minimum size of 0 and
   * the given maximum size.
   */
  public AbstractVectorStrategy( final int max )
  {
    this( 0, max );
  }

  /**
   * Create a new vector strategy with minimum size of 0 and
   * no maximum size.
   */
  public AbstractVectorStrategy()
  {
    this( NO_MAXIMUM_SIZE );
  }

  /**
   * Strategy must be vector so return <code>false</code>.
   */
  public boolean isScalar()
  {
    return false;
  }

  /**
   * Strategy must be vector so return <code>true</code>.
   */
  public boolean isVector()
  {
    return true;
  }

  // Inherit JavaDoc
  public boolean acceptableObject( final Object[] objs )
  {
    if( objs.length < minSize ) {
      return false;
    } else if( (maxSize != NO_MAXIMUM_SIZE) && (objs.length > maxSize) ) {
      return false;
    } else {
      return acceptableVector( objs );
    }
  }

  // Inherit JavaDoc
  public boolean acceptableObject( final int pos, final Object obj )
  {
    if( pos < 0 ) {
      return false;
    } else if( (maxSize != NO_MAXIMUM_SIZE) && (pos >= maxSize) ) {
      return false;
    } else {
      return acceptableInPos( pos, obj );
    }
  }

  /**
   * Test if an array may be used to set the value.  
   * @param objs The array to test.  The caller must
   * insure that the array's length is within the proper bounds.
   * @return <code>true</code> iff the array may be used to 
   * set the value.
   */
  protected boolean acceptableVector( final Object[] objs )
  {
    for( int i = 0; i < objs.length; i++ )
    {
      if( !acceptableInPos( i, objs[i] ) ) {
        return false;
      }
    }
    return true;
  }

  /**
   * Test if an object by a placed in the given position of the value.
   * @param pos The position of interest.  The caller must insure
   * that the position is between 0 and <code>MaxSize</code>-1.
   * @param obj The object to test.
   * @return <code>true</code> iff the object can be placed at
   * the given position in the value.
   */
  protected abstract boolean acceptableInPos( int pos, Object obj );

  // Inherit JavaDoc
  public int getMinSize()
  {
    return minSize;
  }

  // Inherit JavaDoc
  public int getMaxSize()
  {
    return maxSize;
  }

  // Inherit JavaDoc
  public abstract boolean sameForAllPositions();

  // Inherit JavaDoc
  public FieldStrategy getStrategyForPos( final int pos )
  {
    if( pos < 0 ) {
      throw new ArrayIndexOutOfBoundsException( "Cannot request strategy for pos " + pos );
    } else if( (maxSize != NO_MAXIMUM_SIZE) && (pos >= maxSize) ) {
      throw new ArrayIndexOutOfBoundsException( "Cannot request strategy for pos " + pos );
    } else {
      return getStrategyForPosImpl( pos );
    }
  }

  /**
   * Get the stragie used to describe an element at a given position.
   * @param pos The position whose stategy is desired; the caller
   * must insure this is within [0, <code>MaxSize</code>).
   * @return The strategy for the given position
   */
  protected abstract FieldStrategy getStrategyForPosImpl( int pos );
}
