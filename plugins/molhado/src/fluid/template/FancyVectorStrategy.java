/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/FancyVectorStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * A vector strategy that uses different strategies for
 * each position.
 * 
 * @author Aaron Greenhouse
 */

public class FancyVectorStrategy
extends AbstractVectorStrategy
{
  /** The strategies used for the vector's first N elements */
  private final FieldStrategy[] strategies;

  /** The strategy used for elements after the first N */
  private final FieldStrategy rest;

  /** 
   * Create a new vector strategy with the given minimum
   * and maximum sizes, and given strategies.
   * @param min The minimum size
   * @param max The maximum size
   * @param s Array of strategies for the first <code>s.length</code> 
   * positions.
   * @param r The strategy to use for the rest of the positions.
   */
  public FancyVectorStrategy( final int min, final int max,
                              final FieldStrategy[] s, final FieldStrategy r )
  {
    super( min, max );
    strategies = s;
    rest = r;
    if( s == null ) {
      throw new IllegalArgumentException( "Strategies is null!" );
    } else if( ((max == NO_MAXIMUM_SIZE) || (s.length < max)) && (r == null) ) {
      throw new IllegalArgumentException( "rest can't be null when strategies array doesn't cover the maximum size" );
    }
  }
  
  /**
   * Create a new strategy that based on a vector description.
   */
  public FancyVectorStrategy( final VectorDescriptor vd )
  {
    super( vd.getMin(), vd.getMax() );

    final int max = vd.getMax();
    final int len = vd.getDescribedLength();
    rest = vd.getStrategyForRest();

    if( len < 0 ) {
      throw new IllegalArgumentException( "Described length must be >= 0" );
    } else if( ((max == NO_MAXIMUM_SIZE) || (len < max)) && (rest == null) ) {
      throw new IllegalArgumentException( "rest can't be null when strategies array doesn't cover the maximum size" );
    }

    if( len != 0 )
    {
      strategies = new FieldStrategy[vd.getDescribedLength()];
      for( int i = 0; i < strategies.length; i++ ) {
        strategies[i] = vd.getStrategyForPos( i );
      }
    }
    else
    {
      strategies = new FieldStrategy[0];
    }
  }


  // Inherit JavaDoc
  protected boolean acceptableInPos( final int pos, final Object obj )
  {
    final FieldStrategy strategy = getStrategyForPos( pos );

    if( strategy.isScalar() ) {
      return strategy.acceptableObject( 0, obj );
    } else {
      try {
        return strategy.acceptableObject( (Object [])obj );
      } catch( final ClassCastException e ) {
        return false;
      }
    }
  }

  /**
   * Returns <code>true</code> indicating that the same strategy object
   * is used for each element of the vector.
   */
  public boolean sameForAllPositions()
  {
    return false;
  }

  // Inherit JavaDoc
  protected FieldStrategy getStrategyForPosImpl( final int pos )
  {
    if( pos < strategies.length ) {
      return strategies[pos];
    } else {
      return rest;
    }
  }

  /**
   * Get the strategy's vector description.
   */
  public VectorDescriptor getVectorDescriptor()
  {
    return new VectorDescriptor() 
    {
      public int getMin() { return getMinSize(); }
      public int getMax() { return getMaxSize(); }
      public int getDescribedLength() { return strategies.length; }
      public FieldStrategy getStrategyForPos( int n ) { return strategies[n]; }
      public FieldStrategy getStrategyForRest() { return rest; }
    };
  }
  
  
  /**
   * Description of a fancy vector.
   */
  public static interface VectorDescriptor
  {
    /** Get the minimum size of the vector. */
    public int getMin();
    /** Get the maximum size of the vector. */
    public int getMax();
    /** Get the number of vector positions described by individual strategies. */
    public int getDescribedLength();
    /**
     * Get the strategy for a vector position.
     * exception ArrayIndexOutOfBoundsException Thrown if
     * <code>n >= getDescribedLength()</code>.
     */
    public FieldStrategy getStrategyForPos( int n );
    /** Get the strategy used by positions not individually described. */
    public FieldStrategy getStrategyForRest();
  }
}
