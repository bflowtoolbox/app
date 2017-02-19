/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/UnknownLabel.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

/** The label used in backward analysis when going back over a
 * <tt>PendingLabelStrip</tt> node.  An unknown label
 * does <em>not</em> cover tracking labels.
 * @see PendingLabelStrip
 * @see BackwardAnalysis
 * @see TrackLabel
 */
public class UnknownLabel implements ControlLabel {
  public static final UnknownLabel prototype = new UnknownLabel();
  public UnknownLabel() {}
  public String toString() { return "unknown"; }
}
