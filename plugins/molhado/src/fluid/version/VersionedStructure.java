// $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedStructure.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.version;

/** A persistent entity that may include a versioned slot.
 * This structure is used to determine when a versioned slot
 * has a well-defined value.  If the versioned structure is not
 * loaded then we don't know whether it may have some information
 * not currently available, unless the we know that such
 * information does not exist.  The <tt>noteChange</tt> method 
 * is used to inform
 * the versioned structure of changes under progress.
 * <p>
 * A versioned structure cannot be changed for version in which
 * it is not defined, even if this change results in a new era.
 * Otherwise, this class is useless: not all of the slots of
 * the structure would be equally defined or undefined.
 * @see VersionedSlot
 * @see VersionedChunk
 */
public interface VersionedStructure {
  /** Is the information for this structure up-to-date with
   * respect to this version?
   */
  public abstract boolean isDefined(Version v);

  /** Is the information for this structure up-to-date with
   * respect to this era?
   */
  public abstract boolean isDefined(Era e);

  /** Note change that has already happened for this version. */
  public abstract void noteChange(Version v);
}
