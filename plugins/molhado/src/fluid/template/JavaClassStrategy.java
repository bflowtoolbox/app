/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/JavaClassStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Scalar strategy that accepts an object based on its class.
 *
 * @author Aaron Greenhouse
 */
public class JavaClassStrategy
extends AbstractScalarStrategy
{
  /** The representation of the class accepted by this strategy. */
  private Class goodClass;

  /**
   * Create a new strategy that accepts instances of the
   * given class.
   */
  public JavaClassStrategy( final Class c )
  {
    goodClass = c;
  }

  /**
   * Get the representation of the class accepted by this strategy
   */
  public Class getAcceptedClass()
  {
    return goodClass;
  }

  /**
   * Returns <code>true</code> iff obj is an
   * instance of the class accepted by this stratgy.
   */
  protected boolean acceptableScalar( final Object obj )
  {
    return goodClass.isInstance( obj );
  }
}
