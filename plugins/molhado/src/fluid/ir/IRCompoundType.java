/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRCompoundType.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** These types have compound parts.
 * @see IRCompound
 */
public interface IRCompoundType extends IRType {
  /** Return the type associated with compound element i */
  public IRType getType(int i);

  /** Read in the value given current state of compound.
   * This differs from @{link IRCompound#readContents}
   * because it handles the (re)reading of the container value
   * as well.  This method is needed to handle reading
   * persistent files all of which save the value of the compound
   * as well the contents.
   * @see #readValue(IRInput)
   */
  public Object readValue(IRInput in, Object currentValue) throws IOException;
}
