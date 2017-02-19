package fluid.ir;

import java.io.IOException;
import java.util.Comparator;

import fluid.util.UniqueID;

public class IRPersistentReferenceType implements IRType
{
  private IRPersistentReferenceType() {}
  public static IRPersistentReferenceType prototype =
    new IRPersistentReferenceType();
  static {
    IRPersistent.registerIRType(prototype,'P');
  }

  public boolean isValid(Object ref) {
    return ref instanceof IRPersistent || ref == null;
  }

  public void writeValue(Object ref, IROutput out) 
    throws IOException
  {
    out.writePersistentReference((IRPersistent)ref);
  }

  public Object readValue(IRInput in) throws IOException
  {
    return in.readPersistentReference();
  }

  public void writeType(IROutput out) throws IOException
  {
    out.writeByte('P');
  }

  public IRType readType(IRInput in)
  {
    return this;
  }

  public Comparator getComparator() {
    return null;
  }

  public String toString(Object o) {
    IRPersistent p = (IRPersistent)o;
    if (p == null) return "";
    else return p.getID().toString();
  }

  public Object fromString(String s) {
    return IRPersistent.find(UniqueID.parseUniqueID(s));
  }
}
