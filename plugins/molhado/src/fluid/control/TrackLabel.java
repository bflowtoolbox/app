/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/control/TrackLabel.java,v 1.1 2006/03/21 23:20:55 dig Exp $ */
package fluid.control;

public class TrackLabel implements ControlLabel {
  private boolean condition;
  private TrackLabel(boolean b) {
    condition = b;
  }

  public String toString() {
    return condition ? "true" : "false";
  }

  public static final TrackLabel trueTrack = new TrackLabel(true);
  public static final TrackLabel falseTrack = new TrackLabel(false);

  public static TrackLabel getLabel(boolean b) {
    if (b) {
      return trueTrack;
    } else {
      return falseTrack;
    }
  }

  public boolean getCondition() {
    return condition;
  }
}
