/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/StringStrategy.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * Strategy that only accepts {@link java.lang.String} objects.
 * @author Aaron Greenhouse
 */
public class StringStrategy
extends JavaClassStrategy
{
  /** Flyweight */
  public static final FieldStrategy prototype = new StringStrategy();

  /** 
   * Create a new String Strategy 
   */
  public StringStrategy()
  {
    super( "foo".getClass() );
  }
}
