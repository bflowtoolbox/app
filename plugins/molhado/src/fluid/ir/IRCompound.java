/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRCompound.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;

/** Compound values are containers whose contents can change
 * without affecting equals(...) on the container.
 * NB: If a compound has internal slots, then when read from a file,
 * the "old" factory should be used.
 */
public interface IRCompound {
  /** Write the contents of the container to output. */
  public void writeContents(IRCompoundType t, IROutput out) throws IOException;

  /** Read the contents of a container from input. */
  public void readContents(IRCompoundType t, IRInput in) throws IOException;

  /** Have the contents changed from their "previous values"
   * (initial values) ?
   */
  public boolean isChanged();

  /** Write the contents of a changed container to output. */
  public void writeChangedContents(IRCompoundType t, IROutput out)
       throws IOException;

  /** Read the contents of a changed container from input. */
  public void readChangedContents(IRCompoundType t, IRInput in)
       throws IOException;
}
  
