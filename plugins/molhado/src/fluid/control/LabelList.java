package fluid.control;

import fluid.util.AssocList;

public class LabelList {
  private LabelList() {}
  public static final LabelList empty = new LabelList();

  ControlLabel label = null;
  LabelList shorter = null;
  AssocList longer = new AssocList();

  public LabelList addLabel(ControlLabel l) {
    LabelList ll = (LabelList)longer.get(l);
    if (ll == null) {
      ll = new LabelList();
      ll.label = l;
      ll.shorter = this;
      longer.put(l,ll);
    }
    return ll;
  }

  public LabelList dropLabel(ControlLabel l) {
    if (label.equals(l)) return shorter;
    else return null;
  }

  public LabelList dropLabel() {
    return shorter;
  }

  public ControlLabel firstLabel() {
    return label;
  }

  /** Return whether this is an extension of another
   * label list.
   */
  public boolean includes(LabelList other) {
    if (this == other) return true;
    if (shorter == null) return false;
    return shorter.includes(other);
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("<");
    LabelList ll = this;
    while (ll != empty) {
      if (ll != this) sb.append(",");
      sb.append(ll.label);
      ll = ll.dropLabel();
    }
    sb.append(">");
    return sb.toString();
  }
}
