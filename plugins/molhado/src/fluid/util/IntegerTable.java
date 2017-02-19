package fluid.util;

/** This class provides a way to store and reuse boxed integers.
 * It can be used to store other boxed types.  The only requirement
 * is that the hashCode() method return a unique value.
 */
public class IntegerTable {
  private Bucket[] buckets;
    { //@ unique(buckets); //Can't handle yet: unique(buckets[0]);
    }
  private int size = 0;

  public IntegerTable() { //@ limited(this);
    buckets = new Bucket[10]; 
  }

  /** Return a boxed integer for the value given.
   * If necessary, create a new boxed integer.
   * @postcondition return != null
   */
  public synchronized Object get(int v) { //@ limited(this);
    int h = (v&0x7FFFFFFF)%buckets.length;
    for (Bucket b = buckets[h]; b != null; b=b.next) {
      if (b.value.hashCode() == v) return b.value;
    }
    Object value = box(v);
    buckets[h] = new Bucket(value,buckets[h]);
    if (++size > buckets.length) rehash(); // rehash when full
    return value;
  }

  /** Create a new boxed integer (of the desired type). */
  protected Object box(int v) {
    return new Integer(v);
  }

  protected void rehash() { //@ limited(this);
    Bucket[] newBuckets = new Bucket[buckets.length*2];
    for (int i=0; i < buckets.length; ++i) {
      while (buckets[i] != null) {
        Bucket b = buckets[i];
        buckets[i] = b.next;
        int h = (b.value.hashCode()&0x7FFFFFFF) % newBuckets.length;
        b.next = newBuckets[h];
        newBuckets[h] = b;
      }
    }
    buckets = newBuckets;
  }

  class Bucket {
    Object value;
    Bucket next;
      { //@ unique(next);
      }
    Bucket(Object v, Bucket n) { //@ limited(this); unique(n);
      value=v; next=n;
    }
  }

  public static final IntegerTable integers = new IntegerTable();

  /** Return <tt>new Integer(i)</tt> except cache
   * creations to avoid duplication.
   */
  public static Integer newInteger(int i) {
    return (Integer)integers.get(i);
  }

  public static Integer incrInteger(Integer i) {
    return newInteger(i.intValue()+1);
  }
  public static Integer decrInteger(Integer i) {
    return newInteger(i.intValue()-1);
  }
  public static Integer incrInteger(Integer i, int iv) {
    return newInteger(i.intValue()+iv);
  }
}

class TestIntegerTable {
  public static void main(String[] args) {
    Integer[] ints = new Integer[args.length];
    for (int i=0; i < args.length; ++i) {
      int v;
      try {
	v = Integer.parseInt(args[i]);
      } catch (NumberFormatException ignored) {
	v = 0;
      }
      ints[i] = IntegerTable.newInteger(v);
      /// System.out.println("Read: " + ints[i]);
    }
    for (int i=0; i < args.length; ++i) {
      if (ints[i] != IntegerTable.newInteger(ints[i].intValue()))
	System.out.println("!! error: no caching: " + ints[i]);
    }
    System.out.println("TestIntegerTable done.");
  }
}
