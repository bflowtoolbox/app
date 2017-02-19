// $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedSequenceProjection.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.version;

import java.util.Enumeration;

import fluid.ir.IRLocation;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.InsertionPoint;

/**
 * Projects the contents of a (versioned) sequence into a particular version
 * version.  The version at which the sequence is presented is managing using
 * a {@link VersionTracker}, enabling the version to be altered outside of
 * the control of the projection itself.  If you want the version to be fixed
 * for all time, use the
 * {@link #VersionedSequenceProjection(IRSequence,Version)}
 * constructor, which causes an unshared VersionTracker to be used.
 *
 * @author Aaron Greenhouse
 */
public class VersionedSequenceProjection
extends IRSequenceWrapper
{
  /**
   * Indirect reference to the version at which the sequence should be 
   * presented.
   */
  private final VersionTracker fixedVersion;
  
  public VersionedSequenceProjection(
    final IRSequence seq, final VersionTracker vt )
  {
    super( seq );
    fixedVersion = vt;
  }
  
  public VersionedSequenceProjection( final IRSequence seq, final Version v )
  {
    this( seq, new VersionMarker( v ) );
  }
   
  public int size()
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return super.size();
    } finally {
      Version.restoreVersion();
    }
  }
    
  public boolean hasElements()
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return super.hasElements();
    } finally {
      Version.restoreVersion();
    }
  }
    
  public Enumeration elements()
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return super.elements();
    } finally {
      Version.restoreVersion();
    }
  }
    
  public boolean validAt( final IRLocation loc )
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return super.validAt( loc );
    } finally {
      Version.restoreVersion();
    }
  }
    
  public Object elementAt( final IRLocation loc )
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return super.elementAt( loc );
    } finally {
      Version.restoreVersion();
    }
  }
    
  // This is stupid...
  public void setElementAt( final Object element, final IRLocation loc )
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      super.setElementAt( element, loc );
    } finally {
      Version.restoreVersion();
    }
  }
    
  // This is stupid...
  public IRLocation insertElementAt( final Object element, final InsertionPoint ip)
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      return ip.insert( sequence, element );
    } finally {
      Version.restoreVersion();
    }
  }
    
  // This is stupid...
  public void removeElementAt( final IRLocation i )
  {
    try {
      Version.saveVersion( fixedVersion.getVersion() );
      super.removeElementAt( i );
    } finally {
      Version.restoreVersion();
    }
  }
}

