// $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/MarkedIRNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.ir;

/** A variant of PlainIRNode that includes a marker for it's origin
 */
public class MarkedIRNode extends PlainIRNode {
  final String marker;

  /** Create a new IRNode.  Add it to current region, if any.
   */
  public MarkedIRNode(String mark) {
    this(getCurrentRegion(), mark);
  }

  /** Create a new IRNode.
   * @param region region to add node to.
   */
  public MarkedIRNode(IRRegion region, String mark) {
    super(region);
    marker = mark;
  }

  /** If in a region, then self-identify */
  public String toString() {
    return super.toString() + "--" + marker;
  }
}