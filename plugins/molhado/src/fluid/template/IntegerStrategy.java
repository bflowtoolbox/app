/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/IntegerStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Strategy that only accepts {@link java.lang.Integer} objects.
 * @author Aaron Greenhouse
 */
public class IntegerStrategy
extends JavaClassStrategy
{
  /** Flyweight */
  public static final FieldStrategy prototype = new IntegerStrategy();

  /** 
   * Create a new Integer Strategy 
   */
  public IntegerStrategy()
  {
    super( new Integer( 1 ).getClass() );
  }
}
