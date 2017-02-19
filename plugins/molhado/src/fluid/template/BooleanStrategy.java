/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/BooleanStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Strategy that only accepts {@link java.lang.Boolean} objects.
 * @author Aaron Greenhouse
 */
public class BooleanStrategy
extends JavaClassStrategy
{
  /** Flyweight */
  public static final FieldStrategy prototype = new BooleanStrategy();

  /** 
   * Create a new Boolean Strategy 
   */
  public BooleanStrategy()
  {
    super( Boolean.TRUE.getClass() );
  }
}
