/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/template/AbstractFieldConsultant.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.template;


/**
 * A partial implementation of a field consultant.
 * The implementation of {@link #isObjectAcceptable( Field, Object[] )} 
 * returns true if every element of the array is acceptable based on
 * {@link #isObjectAcceptable( Field, int, Object )} given the appropriate
 * position.  
 */
public abstract class AbstractFieldConsultant
implements FieldConsultant
{
  public abstract boolean isObjectAcceptable( Field f, int pos, Object o );

  public boolean isObjectAcceptable( Field f, Object[] o )
  {
    for( int i = 0; i < o.length; i++ ) {
      if( !isObjectAcceptable( f, i, o[i] ) ) return false;
    }
    return true;
  }
}
