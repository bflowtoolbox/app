/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/ImmutableHashOrderSet.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Vector;

/** An implementation of sets with some provision for infinite sets
 * whose inverse is finite.  The sets are very general: they may
 * contain any objects, but those with useful hash codes are
 * handled more efficiently.
 * <p> The sets is maintained as an "almost sorted" array of elements
 * enabling set inclusion to be tested in O(lg n) time.  "Almost
 * sorted" because the hash code is used to sort the elements
 * and elements with the same hash code are unordered. </p>
 */
public class ImmutableHashOrderSet implements ImmutableSet
{
  /** This boolean value is true for infinite sets with finite
   * inverses, in which case the elements array holds the elements
   * in the inverse.
   */
  protected final boolean inverse;

  /** This array holds exactly the elements in the set (or not in
   * the set if inverse is true.  They are sorted by hashCode.
   */
  protected final Object[] elements;
  
  /**
   * Create an empty set.
   */
  public ImmutableHashOrderSet()
  {
    elements = SortedArray.empty;
    inverse = false;
  }

  /**
   * Create a set using the elements of an existing
   * <code>Collection</code>.  Currently has a really
   * stupid & inefficient implementation so that
   * it can prevent duplicate elements from being
   * placed in the set.
   * @param c The collection to use.
   */
  public ImmutableHashOrderSet( final Collection c )
  {
    final Iterator elts = c.iterator();
    ImmutableHashOrderSet s = empty;

    while( elts.hasNext() )
      s = s.addElement( elts.next() );

    elements = s.elements;
    inverse = false;
    s = null;
  }

  /**
   * Create a set with the given (distinct) elements
   * The array must be unique.
   */
  public ImmutableHashOrderSet(Object[] elems) {
    //@ unique(elems);
    // Java 1.0 doesn't realize
    // this initializes the blank finals:
    // this(elems,false);
    SortedArray.sort(elems);
    elements = elems;
    inverse = false;
  }

  /**
   * Create a set.
   * @param elems a unique array of elements to be in (not in)
   * the set.
   * @param inv true if we want the infinite set which has every
   * element not in the array.
   */
  public ImmutableHashOrderSet(Object[] elems, boolean inv) {
    SortedArray.sort(elems);
    elements = elems;
    inverse = inv;
  }

  public static final ImmutableHashOrderSet empty = new ImmutableHashOrderSet(SortedArray.empty,false);
  public static final ImmutableHashOrderSet universe = new ImmutableHashOrderSet(SortedArray.empty,true);

  /** Return the set including every element but the ones in this set.
   */
  public ImmutableHashOrderSet invert() {
    return new ImmutableHashOrderSet(elements, !inverse);
  }

  public ImmutableSet invertCopy() {
    return invert();
  }

  public int hashCode() {
    int h = SortedArray.hashCode(elements);
    if (inverse) return -h;
    else return h;
  }

  public boolean equals(Object other) {
    if (other instanceof ImmutableHashOrderSet) {
      return equals((ImmutableHashOrderSet)other);
    } else {
      return other.equals( this );
    }
  }
  
  public boolean equals(ImmutableHashOrderSet other) {
    return equals(this,other);
  }

  public static boolean equals(ImmutableHashOrderSet s1, ImmutableHashOrderSet s2) {
    if (s1.inverse != s2.inverse) return false;
    return SortedArray.equals(s1.elements,s2.elements);
  }

  public boolean isEmpty() {
    return !inverse && elements.length == 0;
  }

  public boolean isInfinite() {
    return inverse;
  }

  /** Index into the set (which must be finite) and
   * get an element.
   */
  public Object elementAt(int i) throws SetException {
    if (isInfinite()) throw new SetException("can't index in an infinite set");
    if (i < 0 || elements.length <= i)
      throw new SetException("index out of range");
    return elements[i];
  }

  /**
   * Return the cardinality of the set.  To conform to the java.util.Set
   * contract, the size of an infinite set is <code>Integer.MAX_VALUE</code>.
   * If the return value is <code>Integer.MAX_VALUE</code>, a call
   * should be made to {@link #isInfinite} to check if the set is 
   * indeed infinite.
   * @return The number of elements in the set, or
   * <code>Integer.MAX_VALUE</code> if the set is infinite.
   */
  public int size()
  {
    if( inverse )
      return Integer.MAX_VALUE;
    else
      return elements.length;
  }

  public Enumeration elements() throws SetException {
    if (inverse) throw new SetException("infinite enumeration");
    return SortedArray.elements(elements);
  }

  /**
   * Returns an iterator over the elements of the set.  The
   * elements are sorted by hash code.  The iterator does not
   * support the remove operation.
   *
   * <P>Because this method is not allowed to throw an exception,
   * even if the set is infinite, it instead returns 
   * a useless iterator that churns out a series of
   * objects not in the set.  The iterator never completes.
   * @return An iterator over the elements.
   */
  public Iterator iterator()
  {
    if (inverse) return new Iterator() {
      public boolean hasNext() { return true; }
      public Object next() { return new Object(); }
      public void remove() {
	throw new UnsupportedOperationException("not mutable");
      }
    };
    return SortedArray.iterator( elements );
  }

  /**
   * Get an array of all the elements in the set.  The array is
   * sorted in hash code order.
   *
   * @return An array containing all the elements of the set.
   *
   * @exception OutOfMemoryError Thrown if the set is infinite.
   */
  public Object[] toArray()
  {
    if (inverse) return toArray(new Object[size()]);
    return (Object[])(elements.clone());
  }

  /**
   * Get an array of all the elements in the set.  The array is sorted
   * in hashcode order.  If the set is infinite, we try to allocate an
   * infinite array, yielding OutOfMemoryError
   * contains the inverse of the set [huh? this makes no sense&mdash;Aaron].
   *
   * @param  a the array into which the elements of the collection are to
   * 	       be stored, if it is big enough; otherwise, a new array of the
   * 	       same runtime type is allocated for this purpose.
   * @return an array containing the elements of the collection.
   * 
   * @exception NullPointerException if the specified array is <tt>null</tt>.
   * 
   * @exception ArrayStoreException if the runtime type of the specified array
   *         is not a supertype of the runtime type of every element in this
   *         collection.
   *
   * @exception OutOfMemoryError Thrown if the set is infinite.
   */
  public Object[] toArray( Object a[] )
  {
    if( a.length < size())
      a = (Object[])java.lang.reflect.Array.newInstance(
            a.getClass().getComponentType(), size() );

    if (inverse) { // this code should be unreachable
      throw new OutOfMemoryError("cannot allocate infinite array");
    }

    System.arraycopy( elements, 0, a, 0, elements.length );

    if( a.length > elements.length )
      a[elements.length] = null;
    return a;
  }

  /**
   * Test if the set contains a given element.
   * @param elem Object to test
   * @return <code>true</code> iff the set contains an element
   * <code>e</code> s.t. <code>e.equals( elem )</code>
   */
  public boolean contains( final Object elem )
  {
    return inverse != SortedArray.contains(elements,elem);
  }

  /**
   * Test if the set contains all the elements of a given collection.
   * @param c The collection to test.
   * @return <code>true</code> iff all the elements of <code>c</code>
   * are contained in this set.
   */
  public boolean containsAll( final Collection c )
  {
    boolean flag = true;
    final Iterator elts = c.iterator();
    while( flag && elts.hasNext() )
    {
      flag = contains( elts.next() );
    }
    return flag;
  }

  /**
   * Add all the elements of a collection to this set.
   * Unsupported operation.
   * @throws UnsupportedOperationException Always thrown
   */
  public boolean addAll( final Collection c )
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support addAll()" );
  }

  /**
   * Keep only the elements present in the given collection.
   * Unsupported operation.
   * @throws UnsupportedOperationException Always thrown
   */
  public boolean retainAll( final Collection c )
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support retainAll()" );
  }

  /**
   * Remove all the elements present in a given collection.
   * Unsupported operation.
   * @throws UnsupportedOperationException Always thrown
   */
  public boolean removeAll( final Collection c )
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support removeAll()" );
  }

  /**
   * Remove all the elements of this set.
   * Unsupported operation.
   * @throws UnsupportedOperationException Always thrown
   */
  public void clear()
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support clear()" );
  }

  /** True if this set includes every element
   * in the argument set.
   */
  public boolean includes(ImmutableHashOrderSet other) {
    if (inverse) {
      if (other.inverse) {
	return SortedArray.includes(other.elements,elements);
      } else {
	return !SortedArray.overlaps(elements,other.elements);
      }
    } else {
      if (other.inverse) {
	return false;
      } else {
	return SortedArray.includes(elements,other.elements);
      }
    }
  }

  /** True if this set includes some element
   * also included by the argument set.
   */
  public boolean overlaps(ImmutableHashOrderSet other) {
    if (inverse) {
      if (other.inverse) {
	return true;
      } else {
	return !SortedArray.includes(elements,other.elements);
      }
    } else {
      if (other.inverse) {
	return !SortedArray.includes(other.elements,elements);
      } else {
	return SortedArray.overlaps(elements,other.elements);
      }
    }
  }

  /**
   * Add an element to the set.  Unsupported.
   * @throws UnsupportedOperationException Always thrown
   * @see #addElement(Object)
   */
  public boolean add( final Object elem )
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support add()" );
  }

  /** 
   * Return a new set that includes the given element.
   */
  public ImmutableSet addCopy(Object elem) {
    return addElement(elem);
  }
  
  /** 
   * Return a new set that includes the given element.
   * (Concrete version.)
   */
  public ImmutableHashOrderSet addElement(Object elem) {
    if (inverse) {
      return new ImmutableHashOrderSet(SortedArray.removeElement(elements,elem),true);
    } else {
      return new ImmutableHashOrderSet(SortedArray.addElement(elements,elem),false);
    }
  }

  /**
   * Remove an element from the set.  Unsupported.
   * @throws UnsupportedOperationException Always thrown
   * @see #removeElement(Object)
   */
  public boolean remove( final Object elem )
  {
    throw new UnsupportedOperationException( getClass().getName()
                                           + " does not support remove()" );
  }

  /**
   * Return a new set that does not include the given element.
   */
  public ImmutableSet removeCopy(Object elem) {
    return removeElement(elem);
  }

  /**
   * Return a new set that does not include the given element.
   * (Concrete version)
   */
  public ImmutableHashOrderSet removeElement(Object elem) {
    if (inverse) {
      return new ImmutableHashOrderSet(SortedArray.addElement(elements,elem),true);
    } else {
      return new ImmutableHashOrderSet(SortedArray.removeElement(elements,elem),false);
    }
  }

  /** Return an immutable hash order set with these elements. */
  protected static ImmutableHashOrderSet asThis(Set other) {
    if (other instanceof ImmutableHashOrderSet)
      return (ImmutableHashOrderSet)other;
    // assume not infinite:
    return new ImmutableHashOrderSet(other.toArray());
  }

  /** Return a new set that includes all the elements of this
   * set and the argument.
   */
  public ImmutableSet union(Set other) {
    return union(asThis(other));
  }

  /** Return a new set that includes all the elements of this
   * set and the argument.
   * (Concrete version.)
   */
  public ImmutableHashOrderSet union(ImmutableHashOrderSet other) {
    if (inverse) {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.intersect(elements,other.elements),true);
      } else {
	return new ImmutableHashOrderSet(SortedArray.difference(elements,other.elements),true);
      }
    } else {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.difference(other.elements,elements),true);
      } else {
	return new ImmutableHashOrderSet(SortedArray.union(elements,other.elements),false);
      }
    }
  }

  /** Return a set of all elements in both this set
   * and the argument.
   */
  public ImmutableSet intersection(Set other) {
    return intersect(asThis(other));
  }

  /** Return a set of all elements in both this set
   * and the argument.
   * (Concrete Version.)
   */
  public ImmutableHashOrderSet intersect(ImmutableHashOrderSet other) {
    if (inverse) {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.union(elements,other.elements),true);
      } else {
	return new ImmutableHashOrderSet(SortedArray.difference(other.elements,elements),false);
      }
    } else {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.difference(elements,other.elements),false);
      } else {
	return new ImmutableHashOrderSet(SortedArray.intersect(elements,other.elements),false);
      }
    }
  }

  /** Return a set of all elements in this set that are not in
   * the argument.
   */
  public ImmutableSet difference(Set set) {
    return difference(asThis(set));
  }

  /** Return a set of all elements in this set that are not in
   * the argument.
   * (Concrete Version.)
   */
  public ImmutableHashOrderSet difference(ImmutableHashOrderSet other) {
    if (inverse) {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.difference(other.elements,elements),false);
      } else {
	return new ImmutableHashOrderSet(SortedArray.union(elements,other.elements),true);
      }
    } else {
      if (other.inverse) {
	return new ImmutableHashOrderSet(SortedArray.intersect(elements,other.elements),false);
      } else {
	return new ImmutableHashOrderSet(SortedArray.difference(elements,other.elements),false);
      }
    }
  }

  public String toString() {
    String contents = SortedArray.toString(elements);
    if (inverse)
      return "~" + contents;
    else
      return contents;
  }
}

/** Routines for handling arrays of objects sorted by hashCode.
 * All routines are static, because we can't inherit from Object[]
 */
class SortedArray {
  static final Object[] empty = new Object[0];

  public static void main(String[] args) {
    int num = 250; 
    Object[] a = new Object[num];
    for(int i=0; i<a.length; i++) {
      a[i] = new Object();
    }
    Object[] b = new Object[num];
    for(int i=0; i<a.length; i++) {
      b[i] = a[i];
    }

    final long start = System.currentTimeMillis();
    sort(a);
    System.out.println("Total time (in ms) = "+
		       (System.currentTimeMillis() - start));

    final long start2 = System.currentTimeMillis();
    sort3(b);
    System.out.println("Total time (in ms) = "+
		       (System.currentTimeMillis() - start2));

    for(int i=0; i<a.length; i++) {
      if (b[i] != a[i]) {
	System.out.println("Element "+i+" is not the same");
      }
    }
  }
  
  /** Sort in place the contents of an unsorted array. */
  static void sort3(Object a[]) {
    //! right now, use simple bubble sort.
    boolean done;
    do {
      done = true;
      for (int i=1; i < a.length; ++i) {
	//! we should cache the calls to hashCode
	if (a[i].hashCode() < a[i-1].hashCode()) {
	  Object tmp = a[i];
	  a[i] = a[i-1];
	  a[i-1] = tmp;
	  done = false;
	}
      }
    } while (!done);
  }

  static final Comparator hashCompare = new Comparator() {
      public int compare(Object o1, Object o2) {
	return (o1.hashCode() - o2.hashCode());
      }
    };

  static void sort2(Object[] a) {
    Arrays.sort(a, hashCompare);
  }

  static void sort(Object[] a) {
    Object[] aux = (Object[]) a.clone(); // Reuse these?
    mergeSort(aux, a, 0, a.length);
  }

  // Copied from java.util.Arrays, and customized
  static void mergeSort(Object src[], Object dest[],
			int low, int high) {
    int length = high - low;
    
    // Insertion sort on smallest arrays
    if (length < 7) {
      for (int i=low; i<high; i++)
	for (int j=i; j>low &&

	       dest[j-1].hashCode() > dest[j].hashCode(); j--)
	  swap(dest, j, j-1);
      return;
    }
    
    // Recursively sort halves of dest into src
    int mid = (low + high)/2;
    mergeSort(dest, src, low, mid);
    mergeSort(dest, src, mid, high);
    
    // If list is already sorted, just copy from src to dest.  This is an
    // optimization that results in faster sorts for nearly ordered lists.
    if (src[mid-1].hashCode() <= src[mid].hashCode()) {
      System.arraycopy(src, low, dest, low, length);
      return;
    }
    
    // Merge sorted halves (now in src) into dest
    for(int i = low, p = low, q = mid; i < high; i++) {
      if (q>=high || p<mid && src[p].hashCode() <= src[q].hashCode())
	dest[i] = src[p++];
      else
	dest[i] = src[q++];
    }
  }
  
  /**
   * Swaps x[a] with x[b].
   */
  private static void swap(Object x[], int a, int b) {
    Object t = x[a];
    x[a] = x[b];
    x[b] = t;
  }

  static int hashCode(Object[] a) {
    int h = 0;
    for (int i=0; i < a.length; ++i) {
      h += a[i].hashCode();
    }
    return h;
  }

  static Enumeration elements(Object[] a) {
    return new SortedArrayEnumeration(a);
  }
  
  static Iterator iterator( Object[] a )
  {
    return new SortedArrayIterator( a );
  }

  /** Return true if the element is found inside the sorted array. */
  static boolean contains(Object[] a, Object elem) {
    if (a.length == 0) return false;
    int h = elem.hashCode();
    // We use a modified version of binary search
    // to find two points: start and stop
    // a[j].hashCode >= h  ==>  j >= start
    // a[j].hashCode <= h  ==>  j < stop
    // and furthermore start is the largest it can be
    // and stop is the smallest it can be.

    int startlow = 0;
    int starthigh = 0;
    while (starthigh > startlow) {
      int mid = (starthigh+startlow)/2; // == startlow if one separate
      int m = a[mid].hashCode();
      if (m < h) {
	startlow = mid+1;
      } else {
	starthigh = mid;
      }
    }
    // NB: starthigh == 0 and starthigh == a.length are both possible
    if (starthigh == a.length) return false; // all are smaller

    int stoplow = starthigh;
    int stophigh = a.length;
    while (stophigh > stoplow) {
      int mid = (stophigh+stoplow)/2; // == stoplow if one separate
      int m = a[mid].hashCode();
      if (m <= h) {
	stoplow = mid+1;
      } else {
	stophigh = mid;
      }
    }

    for (int i=starthigh; i < stoplow; ++i) {
      if (a[i].equals(elem)) return true;
    }

    return false;
  }

  static boolean equals(Object[] a, Object[] b) {
    if (a == b) return true;
    if (a.length != b.length) return false;
    int low = 0;
    while (low < a.length) {
      int h = a[low].hashCode();
      int high = low+1;
      for (; high < a.length && a[high].hashCode() == h; ++high)
	;
      // NB: common case high = low+1
      // In this situation, each loop has only one iteration
      for (int i=low; i < high; ++i) {
	boolean found = false;
	for (int j = low; !found && j < high; ++j) {
	  if (a[i].equals(b[j])) found = true;
	}
	if (!found) return false;
      }
      low = high;
    }
    return true;
  }

  static boolean includes(Object[] a, Object[] b) {
    if (a == b || b.length == 0) return true;
    if (a.length < b.length) return false;
    if (b.length == 1) return contains(a,b[0]);
    // Otherwise, use O((A+B)H) algorithm where H is the
    // the hash duplication factor (1 for perfect hashes).
    int lowa = 0;
    int lowb = 0;
    while (lowa < a.length && lowb < b.length) {
      int h = b[lowb].hashCode();
      for (; lowa < a.length && a[lowa].hashCode() < h; ++lowa)
	;
      int higha = lowa;
      for (; higha < a.length && a[higha].hashCode() == h; ++higha)
	;
      if (higha == lowa) return false; // no elements match
      int highb = lowb+1;
      for (; highb < b.length && b[highb].hashCode() == h; ++highb)
	;
      if (higha-lowa < highb-lowb) return false; // not enough elements match
      // NB: common case highX = lowX+1
      // In this situation, each loop has only one iteration
      for (int i=lowb; i < highb; ++i) {
	boolean found = false;
	for (int j = lowa; !found && j < higha; ++j) {
	  if (a[j].equals(b[i])) found = true;
	}
	if (!found) return false;
      }
      lowa = higha;
      lowb = highb;
    }
    return lowb == b.length;
  }

  static boolean overlaps(Object[] a, Object[] b) {
    if (a == b) return a.length != 0;
    int lowa = 0;
    int lowb = 0;
    while (lowa < a.length && lowb < b.length) {
      int ah = a[lowa].hashCode();
      int bh = b[lowb].hashCode();
      if (ah == bh) {
	int higha = lowa;
	int highb = lowb;
	for (higha = lowa+1;
	     higha < a.length && a[higha].hashCode() == ah;
	     ++higha)
	  ;
	for (highb = lowb+1;
	     highb < b.length && b[highb].hashCode() == ah;
	     ++highb)
	  ;
	for (int i=lowb; i < highb; ++i) {
	  for (int j = lowa; j < higha; ++j) {
	    if (a[j].equals(b[i])) return true;
	  }
	}
	lowa = higha;
	lowb = highb;
      } else if (ah < bh)  {
	// move lowa along
	for (; lowa < a.length && a[lowa].hashCode() < bh; ++lowa)
	  ;
      } else {
	// move lowb along
	for (; lowb < b.length && b[lowb].hashCode() < ah; ++lowb)
	  ;
      }
    }
    return false;
  }

  static Object[] addElement(Object[] a, Object elem) {
    if (contains(a,elem)) return a;
    Object[] b = new Object[a.length+1];
    int i=0;
    int h = elem.hashCode();
    while (i<a.length) {
      if (a[i].hashCode() < h) {
	break;
      }
      b[i] = a[i];
      ++i;
    }
    b[i] = elem;
    while (i<a.length) {
      b[i+1] = a[i];
      ++i;
    }
    return b;
  }

  static Object[] removeElement(Object[] a, Object elem) {
    if (!contains(a,elem)) return a;
    Object[] b = new Object[a.length-1];
    int j=0;
    int i=0;
    while (i<a.length) {
      if (a[i].equals(elem)) {
	break;
      }
      b[i] = a[i];
      ++i;
    }
    ++i;
    while (i<a.length) {
      b[i-1] = a[i];
      ++i;
    }
    return b;
  }

  public static final int A_EXCL = 1;
  public static final int B_EXCL = 2;
  public static final int OVERLAP = 4;
  public static final int A_INCL = A_EXCL|OVERLAP;
  public static final int B_INCL = B_EXCL|OVERLAP;
  
  static Object[] combine(Object a[], Object b[], int flags) {
    Vector v = new Vector(a.length+b.length);

    int lowa = 0;
    int lowb = 0;
    while (lowa < a.length && lowb < b.length) {
      int ah = a[lowa].hashCode();
      int bh = b[lowb].hashCode();
      if (ah == bh) {
	int higha, highb;

	/* move through a's elements adding if guaranteed to want them */
	for (higha = lowa;
	     higha < a.length && a[higha].hashCode() == ah;
	     ++higha) {
	  if ((flags & A_INCL) == A_INCL) {
	    v.addElement(a[higha]);
	  }
	}

	for (highb = lowb;
	     highb < b.length && b[highb].hashCode() == bh;
	     ++highb) {
	  /* only if the elements are potentially relevant
	     do we see if it is found */
	  if (((flags & OVERLAP) == OVERLAP &&
	       (flags & A_INCL) != A_INCL) ||
	      (flags & B_EXCL) == B_EXCL) {
	    boolean found = false;
	    Object elem = b[highb];
	    for (int i = lowa; !found && i < higha; ++i) {
	      found = (a[i].equals(elem));
	    }
	    if ((found && ((flags & OVERLAP) == OVERLAP &&
			   (flags & A_INCL) != A_INCL)) ||
		(!found && (flags & B_EXCL) == B_EXCL)) {
	      v.addElement(elem);
	    }
	  }
	}

	/* now if we need A_EXCL, we make a separate loop */
	if ((flags & A_EXCL) == A_EXCL &&
	    (flags & A_INCL) != A_INCL) {
	  for (int i = lowa; i < higha; ++i) {
	    boolean found = false;
	    Object elem = a[i];
	    for (int j = lowb; !found && j < highb; ++j) {
	      found = elem.equals(b[j]);
	    }
	    if (!found) v.addElement(elem);
	  }
	}

	lowa = higha;
	lowb = highb;
      } else if (ah < bh) {
	// move lowa along
	for (; lowa < a.length && a[lowa].hashCode() < bh; ++lowa) {
	  if ((flags & A_EXCL) == A_EXCL) v.addElement(a[lowa]);
	}
      } else { // ah > bh
	// move lowb along
	for (; lowb < b.length && b[lowb].hashCode() < ah; ++lowb) {
	  if ((flags & B_EXCL) == B_EXCL) v.addElement(b[lowb]);
	}
      }
    }
    /* finish off both sequences */
    if ((flags & A_EXCL) == A_EXCL) {
      for (; lowa < a.length; ++lowa) {
	v.addElement(a[lowa]);
      }
    }
    if ((flags & B_EXCL) == B_EXCL) {
      for (; lowb < b.length; ++lowb) {
	v.addElement(b[lowb]);
      }
    }
    if (v.size() == 0) return empty;
    Object[] c = new Object[v.size()];
    v.copyInto(c);
    return c;
  }    
			  
  static Object[] union(Object a[], Object b[]) {
    // first, try to avoid creating a new set
    if (includes(b,a)) return b;
    if (includes(a,b)) return a;
    return combine(a,b,A_INCL|B_INCL);
  }

  static Object[] intersect(Object a[], Object b[]) {
    // first, try to avoid creating a new set
    if (includes(b,a)) return a;
    if (includes(a,b)) return b;
    return combine(a,b,OVERLAP);
  }

  static Object[] difference(Object a[], Object b[]) {
    // first, try to avoid creating a new set
    if (includes(b,a)) return empty;
    if (!overlaps(a,b)) return a;
    return combine(b,a,B_EXCL); // B_EXCL is more efficient
  }

  static Object[] symmetricDifference(Object a[], Object b[]) {
    return combine(a,b,A_EXCL|B_EXCL);
  }

  public static String toString(Object a[]) {
    StringBuffer sb = new StringBuffer("{");
    for (int i=0; i < a.length; ++i) {
      if (i != 0) sb.append(",");
      sb.append(a[i].toString());
    }
    sb.append("}");
    return sb.toString();
  }
}
 
class SortedArrayEnumeration implements Enumeration {
  Object[] array;
  int index = 0;
  SortedArrayEnumeration(Object[] a) {
    array = a;
  }

  public boolean hasMoreElements() {
    return index < array.length;
  }

  public Object nextElement() throws NoSuchElementException {
    if (index >= array.length)
      throw new NoSuchElementException("set enumeration exhausted");
    return array[index++];
  }
}

class SortedArrayIterator
implements Iterator
{
  Object[] array;
  int index = 0;
  SortedArrayIterator(Object[] a) {
    array = a;
  }

  public boolean hasNext() {
    return index < array.length;
  }

  public Object next() throws NoSuchElementException {
    if (index >= array.length)
      throw new NoSuchElementException("set iterator exhausted");
    return array[index++];
  }

  public void remove()
  {
    throw new UnsupportedOperationException( "remove not supported" );
  }
}
