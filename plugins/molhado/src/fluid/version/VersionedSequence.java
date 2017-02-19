/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/VersionedSequence.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import java.io.IOException;
import java.util.Enumeration;

import fluid.ir.IRCompoundType;
import fluid.ir.IRInput;
import fluid.ir.IRLocation;
import fluid.ir.IROutput;
import fluid.ir.IRSequence;
import fluid.ir.IRSequenceWrapper;
import fluid.ir.InsertionPoint;

class VersionedSequence extends IRSequenceWrapper {
  // we don't synchronize on this field
  // but that is OK because it will be set on creation
  // in all JVMs after the one which creates it.
  protected VersionedStructure structure;

  VersionedSequence(/*@ unique */
  IRSequence base) {
    super(base);
    structure = VersionedStructureFactory.getVS(); // often null
  }

  public void setVS(VersionedStructure vs) {
    structure = vs;
  }

  protected boolean checkVS() {
    if (structure != null) {
      Version v = Version.getVersionLocal();
      return structure.isDefined(v);
    }
    // else System.out.println("No VS for " + this);
    return true;
  }

  protected void assertVS(boolean isWrite) {
    while (!checkVS()) {
      Version v = Version.getVersionLocal();
      new SlotUnknownVersionException(
        isWrite ? "not loaded for write" : "not loaded for read",
        null,
      //! when we know slot, use here
      v).handle();
    }
  }

  protected void informVS() {
    if (structure != null) {
      structure.noteChange(Version.getVersionLocal());
    }
  }

  // now override sequence functions to do VS things:

  // the query:

  public boolean validAt(IRLocation loc) {
    return checkVS() && super.validAt(loc);
  }

  // reading:

  public Object elementAt(IRLocation loc) {
    assertVS(false);
    return super.elementAt(loc);
  }
  public Enumeration elements() {
    assertVS(false);
    return super.elements();
  }

  // writing:

  public void setElementAt(Object e, IRLocation loc) {
    assertVS(true);
    super.setElementAt(e, loc);
    informVS();
  }

  public IRLocation insertElementAt(Object e, InsertionPoint ip) {
    assertVS(true);
    IRLocation loc = super.insertElementAt(e, ip);
    informVS();
    return loc;
  }

  public void removeElementAt(IRLocation loc) {
    assertVS(true);
    super.removeElementAt(loc);
    informVS();
  }

  // I/O:

  public void writeValue(IROutput out) throws IOException {
    // No locking problems because always set to same value:
    if (structure == null)
      structure = VersionedStructureFactory.getVS();
    sequence.writeValue(out);
  }

  public void writeContents(IRCompoundType t, IROutput out)
    throws IOException {
    // No locking problems because always set to same value:
    if (structure == null)
      structure = VersionedStructureFactory.getVS();
    sequence.writeContents(t, out);
  }

  public void readContents(IRCompoundType t, IRInput in) throws IOException {
    // No locking problems because always set to same value:
    if (structure == null)
      structure = VersionedStructureFactory.getVS();
    sequence.readContents(t, in);
  }

  public boolean isChanged() {
    return sequence.isChanged();
  }

  public void writeChangedContents(IRCompoundType t, IROutput out)
    throws IOException {
    sequence.writeChangedContents(t, out);
  }

  public void readChangedContents(IRCompoundType t, IRInput in)
    throws IOException {
    sequence.readChangedContents(t, in);
  }
}
