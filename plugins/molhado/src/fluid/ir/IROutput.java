/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IROutput.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataOutput;
import java.io.IOException;

/** Extend output capabilities with IR specific ones. */
public interface IROutput extends DataOutput {
  public void writeNode(IRNode node) throws IOException;
  /** Write a reference to a shared object to the stream.
   * @return true if object already written, false if
   * client must perform the write itself.
   * @see IRInput#readCachedObject
   * @see CachedType
   * @precondition object != null
   */
  public boolean writeCachedObject(Object object) throws IOException;

  /** Write an IRType to the output file. */
  public void writeIRType(IRType ty) throws IOException;

  /** Write a reference to a slot factory to the output file. */
  public void writeSlotFactory(SlotFactory sf) throws IOException;

  /** Write a reference to a persistent object to the output file. */
  public void writePersistentReference(IRPersistent p) throws IOException;

  /** Return whether debugging is active */
  public boolean debug();

  /** Inform debugging that a structure is beginning */
  public void debugBegin(String x);

  /** Inform debugging that a structure is ending */
  public void debugEnd(String x);

  /** Inform debugging that a point is passing */
  public void debugMark(String x);
}
