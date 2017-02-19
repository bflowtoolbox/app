/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/NodeVersionPair.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.IRNode;

/**
 * Wrapper for an IRNode that includes the Version in which
 * the node should be interpreted.  Allows the receiver of a 
 * paste or drop operation to determine where the node
 * came from.
 */
public class NodeVersionPair
{
  /** The node component */
  private final IRNode node;
  /** The version component */
  private final Version version;

  /** Create a new pair */
  public NodeVersionPair( final IRNode n, final Version ver )
  {
    node = n;
    version = ver;
  }

  /** Get the node component */
  public IRNode getNode()
  {
    return node;
  }

  /** Get the version component */
  public Version getVersion()
  {
    return version;
  }

  /**
   * Override equals because the class is immutable.
   * Two pairs are equal if they are component-wise equal.
   */
  public boolean equals( final Object obj )
  {
    if( obj instanceof NodeVersionPair ) {
      final NodeVersionPair nvp = (NodeVersionPair)obj;
      return node.equals( nvp.getNode() ) && version.equals( nvp.getVersion() );
    }
    return false;
  }

  /**
   * Override equals because the class is immutable.
   * The hashcode is the sum of the hashcodes of the components.
   */
  public int hashCode()
  {
    return node.hashCode() + version.hashCode();
  }
}
