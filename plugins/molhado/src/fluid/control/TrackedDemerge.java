/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/TrackedDemerge.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** Class for splitting parallel control flows that share
 * edges into separate edges.  Each TrackedMerge must
 * be balanced by exactly one TrackedDemerge.
 * @author John Tang Boyland
 * @see TrackedMerge
 */

public class TrackedDemerge extends Split
{
  public TrackedDemerge() { }
}
