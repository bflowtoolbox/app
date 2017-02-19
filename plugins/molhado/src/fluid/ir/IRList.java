/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IRList.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;

import fluid.FluidError;

/** Generic Doubly-Linked list class to be used for IRList.
 * We permit both circularly and non-circularly linked lists.
 *<bold>This class has race conditions in it concerning slots.
 * Whenever we have <blockquote>
 *          <tt>slot = slot.setValue(<i>e</i>)
 * </blockquote>, this needs to be protected somehow.
 *</bold>
 */
class IRListElement extends IRLocation {
  Slot prev, next, elem;
  IRList list;

  IRListElement(int id, IRList l, Slot aSlot) {
    super(id);
    prev = next = elem = aSlot;
    list = l;
  }

  IRListElement getPrev() {
    return (IRListElement)(prev.getValue());
  }

  IRListElement getNext() {
    return (IRListElement)(next.getValue());
  }

  IRList getHeader() {
    return list;
  }

  boolean isValid() {
    return elem.isValid();
  }

  Object getElement() {
    return elem.getValue();
  }
    
  void setElement(Object value) {
    elem = elem.setValue(value);
  }

  public void writeContents(IRType et, IROutput out) throws IOException
  {
    int flags = 0;
    if (prev.isValid()) flags |= 1;
    if (next.isValid()) flags |= 2;
    if (elem.isValid()) flags |= 4;
    out.writeByte(flags);
    if ((flags & 1) != 0) {
      prev.writeValue(list.getLocationType(),out);
    }
    if ((flags & 2) != 0) {
      next.writeValue(list.getLocationType(),out);
    }
    if ((flags & 4) != 0) {
      elem.writeValue(et,out);
    }
  }

  public void readContents(IRType et, IRInput in) throws IOException {
    int flags = in.readByte();
    if ((flags & 1) != 0) {
      prev = prev.readValue(list.getLocationType(),in);
    }
    if ((flags & 2) != 0) {
      next = next.readValue(list.getLocationType(),in);
    }
    if ((flags & 4) != 0) {
      elem = elem.readValue(et,in);
      if (in.debug()) System.out.println("    read elem " + elem.getValue());
    }
  }

  public boolean isChanged() {
    return prev.isChanged() || next.isChanged() || elem.isChanged();
  }

  public void writeChangedContents(IRType et, IROutput out) throws IOException
  {
    int flags = 0;
    if (prev.isChanged()) flags |= 1;
    if (next.isChanged()) flags |= 2;
    if (elem.isChanged()) flags |= 4;
    out.writeByte(flags);
    if ((flags & 1) != 0) {
      prev.writeValue(list.getLocationType(),out);
    }
    if ((flags & 2) != 0) {
      next.writeValue(list.getLocationType(),out);
    }
    if ((flags & 4) != 0) {
      elem.writeValue(et,out);
    }
  }

  public void readChangedContents(IRType et, IRInput in) throws IOException {
    int flags = in.readByte();
    if (in.debug()) {
      System.out.println("        prev=" +
			 ((flags & 1) != 0 ? "changed" : "unchanged") +
			 " next=" +
			 ((flags & 2) != 0 ? "changed" : "unchanged") +
			 " elem=" +
			 ((flags & 4) != 0 ? "changed" : "unchanged"));
    }
    if ((flags & 1) != 0) {
      prev = prev.readValue(list.getLocationType(),in);
    }
    if ((flags & 2) != 0) {
      next = next.readValue(list.getLocationType(),in);
    }
    if ((flags & 4) != 0) {
      elem = elem.readValue(et,in);
    }
  }

  public void describe(PrintStream out) {
    elem.describe(out);
    out.print("  prev => ");
    prev.describe(out);
    out.print("  next => ");
    next.describe(out);
  }
}

class IRListHeader {
  private IRList list;
  private Slot head, tail;

  public IRListHeader(IRList l, SlotFactory sf, IRListElement[] elems) {
    list = l;
    if (elems == null || elems.length == 0) {
      head = sf.predefinedSlot(null);
      tail = sf.predefinedSlot(null);
    } else {
      IRListElement elem0 = elems[0];
      head = sf.predefinedSlot(elem0);
      elem0.prev = sf.predefinedSlot(null);
      for (int i=1; i < elems.length; ++i) {
	IRListElement p = elems[i-1];
	IRListElement n = elems[i];
	p.next = sf.predefinedSlot(n);
	n.prev = sf.predefinedSlot(p);
      }
      IRListElement elemn = elems[elems.length-1];
      elemn.next = sf.predefinedSlot(null);
      tail = sf.predefinedSlot(elemn);
    }
  }

  public boolean isEmpty() {
    return head.getValue() == null;
  }

  public int size() {
    int i=0;
    IRListElement e = (IRListElement)head.getValue();
    while (e != null) {
      e = (IRListElement)e.next.getValue();
      ++i;
    }
    return i;
  }

  public int locate(IRListElement elem) {
    int i=0;
    IRListElement e = (IRListElement)head.getValue();
    while (e != null && e != elem) {
      e = (IRListElement)e.next.getValue();
      ++i;
    }
    if (e == null) return -1;
    else return i;
  }

  public IRListElement at(int i) {
    IRListElement e = (IRListElement)head.getValue();
    if (i == -1) return null;
    while (i > 0 && e != null) {
      e = (IRListElement)e.next.getValue();
      --i;
    }
    return e;
  }

  public IRListElement first() {
    return (IRListElement)head.getValue();
  }

  public IRListElement last() {
    return (IRListElement)tail.getValue();
  }

  public void index(Vector v) {
    IRListElement e = (IRListElement)head.getValue();
    while (e != null) {
      v.setElementAt(e,e.getID());
      e = (IRListElement)e.next.getValue();
    }
  }

  public int comparePlacements(IRListElement e1, IRListElement e2) {
    // could do a little better, but always worst case O(n)
    return locate(e1) - locate(e2);
  }

  public void addFirst(IRListElement elem) {
    head = head.setValue(elem);
    tail = tail.setValue(elem);
    elem.prev = elem.prev.setValue(null);
    elem.next = elem.next.setValue(null);
  }

  public void insert(IRListElement elem) {
    IRListElement h = (IRListElement)head.getValue();
    if (h == null) {
      addFirst(elem);
    } else {
      head = head.setValue(elem);
      h.prev = h.prev.setValue(elem);
      elem.prev = elem.prev.setValue(null);
      elem.next = elem.next.setValue(h);
    }
  }

  public void append(IRListElement elem) {
    IRListElement t = (IRListElement)tail.getValue();
    if (t == null) {
      addFirst(elem);
    } else {
      t.next = t.next.setValue(elem);
      tail = tail.setValue(elem);
      elem.prev = elem.prev.setValue(t);
      elem.next = elem.next.setValue(null);
    }
  }

  public void insertBefore(IRListElement n, IRListElement elem) {
    IRListElement p = (IRListElement)n.prev.getValue();
    if (p == null) {
      insert(elem);
    } else {
      p.next = p.next.setValue(elem);
      n.prev = n.prev.setValue(elem);
      elem.prev = elem.prev.setValue(p);
      elem.next = elem.next.setValue(n);
    }
  }

  public void insertAfter(IRListElement p, IRListElement elem) {
    IRListElement n = (IRListElement)p.next.getValue();
    if (n == null) {
      append(elem);
    } else {
      p.next = p.next.setValue(elem);
      n.prev = n.prev.setValue(elem);
      elem.prev = elem.prev.setValue(p);
      elem.next = elem.next.setValue(n);
    }
  }

  public IRListElement pop() {
    IRListElement elem = (IRListElement)head.getValue();
    IRListElement nh = (IRListElement)elem.next.getValue();
    if (nh == null) {
      tail = tail.setValue(null);
    } else {
      nh.prev = nh.prev.setValue(null);
    }
    head = head.setValue(nh);
    return elem;
  }

  public IRListElement chop() {
    IRListElement elem = (IRListElement)tail.getValue();
    IRListElement nt = (IRListElement)elem.prev.getValue();
    if (nt == null) {
      head = head.setValue(null);
    } else {
      nt.next = nt.next.setValue(null);
    }
    tail = tail.setValue(nt);
    return elem;
  }

  public void remove(IRListElement le) {
    IRListElement p = le.getPrev();
    IRListElement n = le.getNext();
    if (p == null) {
      if (head.getValue() != le)
	throw new IRSequenceException("removing from wrong list");
      head = head.setValue(n);
      if (n == null) {
	tail = tail.setValue(p);
      } else {
	n.prev = n.prev.setValue(p);
      }
    } else {
      if (n == null) {
	if (tail.getValue() != le)
	  throw new IRSequenceException("removing from wrong list");
	tail = tail.setValue(p);
      } else {
	n.prev = n.prev.setValue(p);
      }
      p.next = p.next.setValue(n);
    }
  }


  public void writeContents(IROutput out) throws IOException {
    head.writeValue(list.getLocationType(),out);
    tail.writeValue(list.getLocationType(),out);
  }

  public void readContents(IRInput in) throws IOException {
    head = head.readValue(list.getLocationType(),in);
    tail = tail.readValue(list.getLocationType(),in);
  }   

  public boolean isChanged() {
    return head.isChanged() || tail.isChanged();
  }

  public void writeChangedContents(IROutput out) throws IOException {
    int flags = 0;
    if (head.isChanged()) flags |= 1;
    if (tail.isChanged()) flags |= 2;
    out.writeByte(flags);
    if ((flags & 1) != 0) {
      head.writeValue(list.getLocationType(),out);
    }
    if ((flags & 2) != 0) {
      tail.writeValue(list.getLocationType(),out);
    }
  }

  public void readChangedContents(IRInput in) throws IOException {
    int flags = in.readByte();
    if (in.debug()) {
      System.out.println("    head=" +
			 ((flags & 1) != 0 ? "changed" : "unchanged") +
			 " tail=" +
			 ((flags & 2) != 0 ? "changed" : "unchanged"));
    }
    if ((flags & 1) != 0) {
      head = head.readValue(list.getLocationType(),in);
    }
    if ((flags & 2) != 0) {
      tail = tail.readValue(list.getLocationType(),in);
    }
  }   
}


/** Variable size sequences with locations that stay valid under reshaping.
 * We use a doubly-linked list for now.  If performance problems arise,
 * perhaps a skip-list can be used instead.
 * <p>
 * This implementation uses a free list.
 * This allows us to assign fixed IRLocation values
 * that persist over list changes, without having a pool of
 * nodes that grows larger and larger with each list change.
 * In order to have this free list work correctly,
 * the following invariants are needed:
 * <ol>
 * <li> The last element in the free list
 * is always the element with the highest ID.
 * This permits us to attach new nodes to the end of the free list
 * in constant time.
 * <li> The links between elements in the free list are always valid:
 * they are initial values.  This allows us to treat the free list as
 * infinite in length, it just hasn't all been revealed yet.
 * </ol>
 * We keep the first invariant by putting newly allocated
 * elements on the end of the list and reclaimed nodes on the
 * front of the list.  The list is never permitted to go empty.
 * The first invariant is handled by always allocated a predefined
 * slot for the "next" field of the last free element
 * just before attaching a newly allocated node to the end of the list.
 * </p>
 */
public class IRList implements IRSequence {
  private final SlotFactory slotFactory;
  Slot emptySlot;

  private final IRListHeader seq;	// dl-list of cells in sequence
  private Slot free;			// "infinite" list of new cells.
  private final int initialSize;	// starting size (often 0)
  private int nextid = 0;		// next cell id in free list
  private Vector elements = new Vector(); // index from cell-id to cell

  public IRList(SlotFactory sf) {
    this(sf,0);
  }

  public IRList(SlotFactory sf, int startingSize) {
    super();
    slotFactory = sf;
    emptySlot = sf.undefinedSlot();
    IRListElement[] elems = new IRListElement[startingSize];
    for (int i=0; i<startingSize; ++i) {
      elems[i] = new IRListElement(i,this,emptySlot);
      elements.addElement(elems[i]);
    }
    nextid = startingSize;
    seq = new IRListHeader(this,sf,elems);
    initialSize = startingSize;
    allocFirst();
  }

  private final IRType locationType = new IRListLocationType(this);

  public IRType getLocationType() {
    return locationType;
  }

  public int size() {
    return seq.size();
  }

  public boolean isVariable() {
    return true;
  }

  public Enumeration elements() {
    return slotFactory.newEnumeration(new IRSequenceEnumeration(this));
  }

  public boolean validAt(int i) {
    return validAt(location(i));
  }  
  public boolean validAt(IRLocation loc) {
    return validateLocation(loc).isValid();
  }

  public Object elementAt(int i) {
    return elementAt(location(i));
  }  
  public Object elementAt(IRLocation loc) {
    return validateLocation(loc).getElement();
  }

  public void setElementAt(Object element, int i) {
    setElementAt(element,location(i));
  }  
  public void setElementAt(Object element, IRLocation loc) {
    validateLocation(loc).setElement(element);
  }

  public IRLocation insertElement(Object element) {
    IRListElement le = newElement(element);
    seq.insert(le);
    return le;
  }

  public IRLocation appendElement(Object element) {
    IRListElement le = newElement(element);
    seq.append(le);
    return le;
  }

  public IRLocation insertElementBefore(Object element, IRLocation loc) {
    IRListElement le = newElement(element);
    seq.insertBefore(validateLocation(loc),le);
    return le;
  }

  public IRLocation insertElementAfter(Object element, IRLocation loc) {
    IRListElement le = newElement(element);
    seq.insertAfter(validateLocation(loc),le);
    return le;
  }

  public void removeElementAt(IRLocation loc) {
    IRListElement le = validateLocation(loc);
    seq.remove(le);
    freeElement(le);
  }

  public IRLocation location(int i) {
    IRListElement le = (IRListElement)seq.at(i);
    if (le == null) {
      throw new IRListException("no location for given index");
    }
    return le;
  }

  public int locationIndex(IRLocation loc) {
    return seq.locate(validateLocation(loc));
  }

  IRListElement validateLocation(IRLocation loc) {
    if (loc instanceof IRListElement &&
	((IRListElement)loc).getHeader() == this) {
      return (IRListElement)loc;
    } else {
      return lookup(loc.getID());
    }
  }

  IRListElement lookup(int id) {
    while (id >= nextid) {
      alloc();
    }
    return (IRListElement)elements.elementAt(id);
  }

  /** Allocate a new sequence cell and put at end of free list. */
  synchronized void alloc() {
    if (nextid != elements.size())
      throw new FluidError("nextid out of sync");
    IRListElement le =
      new IRListElement(nextid++,this,emptySlot);
    elements.addElement(le);
    le.elem = slotFactory.predefinedSlot(null);
    if (le.getID() == initialSize) {
      le.prev = slotFactory.predefinedSlot(null);
      free = slotFactory.predefinedSlot(le);
    } else {
      IRListElement last = (IRListElement)elements.elementAt(nextid-2);
      le.prev = slotFactory.predefinedSlot(last);
      // NB: last.next needs to be made a predefined value:
      last.next = slotFactory.predefinedSlot(le);
    }
    /* le.next is *not* set.  It will need to be converted
     * into a predefined slot before being used.
     */
  }
    
  /** Allocate the first element in the infinite sequence.
   * Its prev field is preset to null.
   * Its next field is <em>not</em> set until later,
   * until we know what its <em>initial</em> value will be,
   * @see #newElement()
   */
  void allocFirst() {
    alloc(); // does all the work for us.
  }

  /** Return a sequence cell from the free list.
   * We allocate from our "infinite" list, adding new ones on the
   * "end" as necessary.  The way slots are set is carefully managed
   * to allow versioning to work correctly.
   */
  IRListElement newElement() {
    IRListElement first = (IRListElement)free.getValue();
    if (first.getID() == (nextid-1)) {
      // we need to unroll our "infinite" list a little further
      // It doesn't hurt, if this happens too early.
      alloc();
    }
    IRListElement next = (IRListElement)first.next.getValue();
    /* Now unhook first from free list, and make next the first one */
    synchronized (this) {
      free = free.setValue(next);
    }
    first.next = first.next.setValue(null);
    next.prev = next.prev.setValue(null);
    return first;
  }

  /** Allocate a new sequence cell and overwrite the value in it
   * to the parameter value.
   */
  IRListElement newElement(Object value) {
    IRListElement le = newElement();
    le.setElement(value);
    return le;
  }

  /** Return a cell to the free list. */
  void freeElement(IRListElement le) {
    IRListElement first = (IRListElement)free.getValue();
    le.next = le.next.setValue(first);
    le.prev = le.prev.setValue(null);
    first.prev = first.prev.setValue(le);
    // make sure free is not subject to race conditions.
    synchronized (this) { free = free.setValue(le); }
  }
    

  public boolean hasElements() {
    return !seq.isEmpty();
  }

  public IRLocation firstLocation() {
    return seq.first();
  }

  public IRLocation lastLocation() {
    return seq.last();
  }

  public IRLocation nextLocation(IRLocation loc) {
    return validateLocation(loc).getNext();
  }

  public IRLocation prevLocation(IRLocation loc) {
    return validateLocation(loc).getPrev();
  }

  public int compareLocations(IRLocation loc1, IRLocation loc2) {
    return seq.comparePlacements(validateLocation(loc1),
				 validateLocation(loc2));
  }

  public void writeValue(IROutput out)
       throws IOException
  {
    out.writeSlotFactory(slotFactory);
    out.writeInt(initialSize);
  }

  public static IRSequence readValue(IRInput in, IRSequence current)
       throws IOException
  {
    SlotFactory sf = in.readSlotFactory();
    int initialSize = in.readInt();
    if (current != null) {
      if (!current.isVariable())
	throw new IOException("re-reading list with new parameters");
      return current;
    }
    return sf.getOldFactory().newSequence(~initialSize);
  }


  public void writeContents(IRCompoundType t, IROutput out) throws IOException
  {
    /* Some of the values read here may be redundant,
     * since we just created the list with its initial size.
     * But to avoid this problem would be very tricky.
     * We'd have to do something like "changed" contents
     * for the head,tail,free,prev&next fields
     * but do the regular contents thing for the elements.
     * If/when we make this change, we will need to
     * "up" the revision number in IRPersistent, because
     * it is not forward compatible.
     */
    if (out.debug()) System.out.println("writeContents(...) called");
    IRType et = t.getType(0); // must be homogenous
    if (free.getValue() == seq.last())
      throw new FluidError("free has problems!");
    seq.writeContents(out);
    free.writeValue(getLocationType(),out);
    if (out.debug()) System.out.println("List has " + nextid + " elements");
    out.writeInt(nextid);
    for (int i=0; i < nextid; ++i) {
      if (out.debug()) System.out.println("  " + i);
      IRListElement le = (IRListElement)elements.elementAt(i);
      le.writeContents(et,out);
    }
    if (out.debug()) System.out.println();
  }

  public void readContents(IRCompoundType t, IRInput in) throws IOException {
    if (in.debug()) System.out.println("readContents(...) called");
    IRType et = t.getType(0); // must be homogenous
    seq.readContents(in);
    synchronized (this) { free = free.readValue(getLocationType(),in); }
    int n = in.readInt();
    if (in.debug()) System.out.println("List has " + n + " elements");
    for (int i=0; i < n; ++i) {
      lookup(i).readContents(et,in);
    }
  }

  public boolean isChanged() {
    if (seq.isChanged()) return true;
    if (free.isChanged()) return true;
    for (int i=0; i < nextid; ++i) {
      IRListElement le = (IRListElement)elements.elementAt(i);
      if (le.isChanged()) return true;
    }
    return false;
  }

  public void writeChangedContents(IRCompoundType t, IROutput out)
       throws IOException
  {
    IRType et = t.getType(0); // must be homogenous
    seq.writeChangedContents(out);
    if (free.isChanged()) {
      out.writeBoolean(true);
      free.writeValue(getLocationType(),out);
    } else {
      out.writeBoolean(false);
    }
    boolean allChanged = true;
    for (int i=0; i < nextid; ++i) {
      IRListElement le = (IRListElement)elements.elementAt(i);
      if (!le.isChanged()) {
	allChanged = false;
	break;
      }
    }
    out.writeBoolean(allChanged);
    if (allChanged) out.writeInt(nextid);
    for (int i=0; i < nextid; ++i) {
      IRListElement le = (IRListElement)elements.elementAt(i);
      if (allChanged) {
	le.writeContents(et,out);
      } else {
	if (le.isChanged()) {
	  out.writeInt(i);
	  le.writeChangedContents(et,out);
	}
      }
    }
    if (!allChanged) out.writeInt(-1);
  }

  public void readChangedContents(IRCompoundType t, IRInput in)
       throws IOException 
  {
    IRType et = t.getType(0); // must be homogenous
    seq.readChangedContents(in);
    if (in.readBoolean()) {
      if (in.debug()) System.out.println("    free=changed");
      free = free.readValue(getLocationType(),in);
    } else {
      if (in.debug()) System.out.println("    free=unchanged");
    }
    boolean allChanged = in.readBoolean();
    if (in.debug())
      System.out.println("    (" + (allChanged ? "all" : "not all") +
			 " changed)");
    if (allChanged) {
      int n = in.readInt();
      for (int i=0; i < n; ++i) {
	if (in.debug()) System.out.println("    for loc #" + i);
	lookup(i).readContents(et,in);
      }
    } else {
      int i;
      while ((i = in.readInt()) != -1) {
	if (in.debug()) System.out.println("    for loc #" + i);
	lookup(i).readChangedContents(et,in);
      }
    }
  }

  public void describe(PrintStream out) {
    int n = elements.size();
    out.println("IRList[" + n + "]");
    for (int i=0; i < n; ++i) {
      IRListElement le = (IRListElement)elements.elementAt(i);
      out.print(" " + i + " => ");
      le.describe(out);
    }
  }
}

class IRListLocationType extends IRLocationType {
  private final IRList list;
  public IRListLocationType(IRList l) {
    super();
    list = l;
  }
  public Object readValue(IRInput in) throws IOException {
    IRLocation loc = (IRLocation)super.readValue(in);
    if (in.debug()) {
      System.out.println("    location " +
			 (loc == null
			  ? "null"
			  : Integer.toString(loc.getID())));
    }
    if (loc == null) return loc;
    return list.lookup(loc.getID());
  }
  public void writeValue(Object l, IROutput out) throws IOException {
    if (out.debug()) {
      System.out.println("    location " +
			 (l == null
			  ? "null"
			  : Integer.toString(((IRLocation)l).getID())));
    }
    super.writeValue(l,out);
  }
}

class IRListException extends IRSequenceException {
  public IRListException() { super(); }
  public IRListException(String s) { super(s); }
}
