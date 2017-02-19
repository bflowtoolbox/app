/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/IRNodeStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;

import fluid.ir.IRNode;
import fluid.tree.GraphLabel;
import fluid.tree.Operator;

/**
 * Scalar strategy that accepts IRNodes based on their operator type.
 * By overriding {@link #correctNodeType} a subclass can further
 * restrict the set of acceptable nodes.
 *
 * @author Aaron Greenhouse
 */
public class IRNodeStrategy
extends AbstractScalarStrategy
{
  /** Constant that indicates the node may have any operator */
  public static final Operator[] ANY_IRNODE = null;

  /** The object that knows how to get the IRNode's operator */
  private GraphLabel graphLabel;

  /** The list of acceptable operators */
  private Operator[] operators;

  /**
   * Create a new strategy that accepts IRNodes with the given operators.
   * @param gl The object that knows to get the node's operator.
   * @param ops Array of acceptable operators.
   */
  public IRNodeStrategy( final GraphLabel gl, final Operator[] ops )
  {
    graphLabel = gl;
    operators = ops;

    if( (gl == null) && (ops != null) ) {
      throw new IllegalArgumentException( "graphLabel can only be null if ops is null" );
    }
  }

  /**
   * Create a new strategy that accepts any IRNode.
   */
  public IRNodeStrategy()
  {
    this( null, ANY_IRNODE );
  }

  /**
   * Get the acceptable operator types.
   */
  public Operator[] getOperators()
  {
    return (Operator[])operators.clone();
  }

  // Inherit JavaDoc
  protected boolean acceptableScalar( final Object obj )
  {
    if( obj == null ) {
      return false;
    } else if( (obj instanceof IRNode) && correctNodeType( (IRNode)obj ) ) {
      if( operators == ANY_IRNODE ) {
        return true;
      } else {
        final Operator op = graphLabel.getOperator( (IRNode)obj );
        for( int i = 0; i < operators.length; i++ ) {
          if( operators[i].includes( op ) ) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if the node is of the correct type.
   * May be used to restrict that type of node accepted (JavaNodes, for example).
   * Initial implementation always returns true.
   * @param node The IRNode to test.  Caller must insure that this is
   * not <code>null</code>.
   */
  protected boolean correctNodeType( final IRNode node )
  {
    return true;
  }


  /**
   * A vector description of created vectors of 
   * IRNodeStrategies.
   */
  protected static class IRNodeVectorDescriptor
  implements FancyVectorStrategy.VectorDescriptor
  {
    protected final int min, max;
    protected final Operator[][] fN;
    protected final Operator[] rest;
    protected final GraphLabel gl;

    /**
     * Create a new vector description.
     * @param min The minimum length of the vector.
     * @param max The maximum length of the vector.
     * @param fN The lists of operators accepted by the first
     * <code>fN.length</code> positions of the vector.
     * @param r The list of operators accepted by vector
     * positions not described by <code>fN</code>.
     * @param gl The object that knows how to get an IRNode's
     * operator.
     */
    public IRNodeVectorDescriptor( final int min, final int max,
                          Operator[][] fN, final Operator[] r,
                          final GraphLabel gl )
    {
      if( gl == null ) {
        throw new IllegalArgumentException( "gl cannot be null" );
      } else if( fN == null ) {
        if( r == null ) {
          throw new IllegalArgumentException( "fN cannot be null if r == null" );
        } else {
          if( max == -1 ) {
            fN = new Operator[0][];
          } else {
            fN = new Operator[max][];
            for( int i = 0; i < max; i++ ) {
              fN[i] = r;
            }
          }
        }
      }

      this.min = min;
      this.max = max;
      this.fN = fN;
      this.rest = r;
      this.gl = gl;
    }

    public int getMin()
    {
      return min;
    }

    public int getMax()
    {
      return max;
    }

    public int getDescribedLength()
    {
      return fN.length;
    }

    public FieldStrategy getStrategyForPos( final int n )
    {
      return new IRNodeStrategy( gl, fN[n] );
    }

    public FieldStrategy getStrategyForRest()
    {
      return new IRNodeStrategy( gl, rest );
    }
  }
}
