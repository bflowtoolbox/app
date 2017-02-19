/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/JavaNodeStrategy.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */
package fluid.java.template;

import fluid.ir.IRNode;
import fluid.java.JavaNode;
import fluid.template.FieldStrategy;
import fluid.template.IRNodeStrategy;
import fluid.tree.Operator;

/**
 * Scalar strategy that accepts JavaNodes based on their operator type.
 *
 * @author Aaron Greenhouse
 */
public class JavaNodeStrategy
extends IRNodeStrategy
{
  /**
   * Create a new strategy that accepts JavaNodes with the given operators.
   * @param ops Array of acceptable operators.
   */
  public JavaNodeStrategy( final Operator[] ops )
  {
    super( JavaNode.tree, ops );
  }

  /**
   * Create a new strategy that accepts any IRNode.
   */
  public JavaNodeStrategy()
  {
    this( ANY_IRNODE );
  }

  // Inherit JavaDoc
  protected boolean correctNodeType( final IRNode node )
  {
    return JavaNode.tree.isNode( node );
  }

  protected static class JavaNodeVectorDescriptor
  extends IRNodeVectorDescriptor
  {
    public JavaNodeVectorDescriptor( final int min, final int max,
                          Operator[][] fN, final Operator[] r )
    {
      super( min, max, fN, r, JavaNode.tree );
    }

    public FieldStrategy getStrategyForPos( final int n )
    {
      return new JavaNodeStrategy( fN[n] );
    }

    public FieldStrategy getStrategyForRest()
    {
      return new JavaNodeStrategy( rest );
    }

  }
}
