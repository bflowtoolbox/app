/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/ProxyNode.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

/** Concrete placeholders for other IRNodes.
 * The referred to node is stored within this proxy node.
 */
public class ProxyNode extends AbstractProxyNode {
  private final IRNode node;
  public ProxyNode(IRNode n) {
    node = n;
  }
  protected IRNode getIRNode() {
    return node;
  }
}
