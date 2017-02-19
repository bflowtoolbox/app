// $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/EnumerationAlreadyDefinedException.java,v 1.1 2006/03/21 23:20:54 dig Exp $

package fluid.ir;

import fluid.FluidException;

public class EnumerationAlreadyDefinedException
extends FluidException
{
  public EnumerationAlreadyDefinedException( final String name )
  {
    super( "An IREnumrationType of name \"" + name + "\" is already defined." );
  }
}
