/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRInput.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.DataInput;
import java.io.IOException;

/** Extend input capabilities with IR specific ones. */
public interface IRInput extends DataInput {
  /** Return the first number of the revision information for
   * the datafile being read.   This number is unrelated to
   * the versioning of the IR.
   */
  public int getVersion();
  /** Return the second number of the revision information
   * for the datafile being read.
   */
  public int getRevision();

  /** Read in an IRNode from the input stream. */
  public IRNode readNode() throws IOException;

  /** Read an object written by writeCachedObject.
   * If the object has not been read before the client must
   * perform the read itself and insert it into the cache.
   * @return the object if cached, otherwise return null.
   * @see IROutput#writeCachedObject
   * @see #cacheReadObject
   * @see CachedType
   */
  public Object readCachedObject() throws IOException;

  /** Inset newly read object into cache.
   * If <tt>readCachedObject</tt> returns null, the client must call
   * this method before any other calls to <tt>readCachedObject</tt>.
   */
  public void cacheReadObject(Object object);

  /** Read in signifier for an IRType. */
  public IRType readIRType() throws IOException;

  /** Read in signifier for a slot factory. */
  public SlotFactory readSlotFactory() throws IOException;

  /** Read in a reference to a persistent object, 
   * creating an undefined one if necessary.
   * @throws IOException if error reading stream or loading object.
   */
  public IRPersistent readPersistentReference() throws IOException;

  /** Return whether debugging is active */
  public boolean debug();

  /** Inform debugging that a structure is beginning */
  public void debugBegin(String x);

  /** Inform debugging that a structure is ending */
  public void debugEnd(String x);

  /** Inform debugging that a point is passing */
  public void debugMark(String x);
}