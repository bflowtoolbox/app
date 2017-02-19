/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/IdentifierConsultant.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */
package fluid.java.template;

import fluid.java.JavaPredicates;
import fluid.template.AbstractFieldConsultant;
import fluid.template.Field;

/**
 * A field consultant that only allows <tt>String</tt>s representing
 * legal Java identifiers to be placed in the field.
 */
public class IdentifierConsultant
extends AbstractFieldConsultant
{
  public static IdentifierConsultant prototype = new IdentifierConsultant();

  private IdentifierConsultant() 
  {
    super();
  }

  public boolean isObjectAcceptable( Field f, int pos, Object o )
  {    
    if( o instanceof String ) {
      final String s = (String)o;
      return JavaPredicates.isIdentifier( s );
    }
    return false;
  }
}
