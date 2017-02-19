// $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/IREnumeratedType.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.ir;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The type of enumerations&mdash;
 * an enumeration type contains the representations of up to N
 * different named values.  This will consume a lot of memory
 * if the number of values in the enumeration is very large.
 * The name of each element of the enumeration is a String.  The
 * index of each element of the enumeration can be queried.  Elements
 * of an enumeration can be compared against each other, but elements
 * of different enumerations cannot.  
 *
 * <P>Instances of this class are thread safe.
 */
public class IREnumeratedType extends CachedType implements Comparator {
  /** The label used to identify an enumeration type */
  public static final char ENUM_TYPE = 'E';

  /**
   * Hashtable mapping type name to enumeration types.  This
   * map is mutable, and must only be accessed by the
   * lock on <code>IREnumerationType.class</code> is held.
   * This field contains a unique reference.
   */
  private static final Map types;

  /**
   * The name of the type.  Because <code>String</code> instances
   * are immutable, no synchronization is required to access this
   * field, or contents of the string.  This field is not
   * assumed to be unique.
   */
  private final String name;

  /**
   * The list of canonical representations of members of
   * the type.  The elements are created lazily, and 
   * therefore require synchronization on the
   * the enumeration object before being accessed.
   * This field contains a unique reference.
   */
  private final Element[] reps;

  /**
   * The list of names of the members of the type.  The
   * length of the array is the size of the enumeration.
   * The elements of this array should be treated as 
   * immutable.  Under this assumption, no synchronization
   * is required to access elements of the array.
   * This field contains a unique reference.
   */
  private final String[] names;

  //============================================================
  //== Static Initializers
  //============================================================

  /* Register the type with the persistence mechinism */
  static {
    IRPersistent.registerIRType(new IREnumeratedType(), ENUM_TYPE);
    types = new HashMap();
  }

  /**
   * Check if an enumeration of a given name has already been
   * defined.
   */
  public synchronized static boolean typeExists(final String name) {
    return types.containsKey(name);
  }

  /**
   * Get the enumerated type with the given name.
   */
  public synchronized static IREnumeratedType getEnumeration(final String name) {
    return (IREnumeratedType) types.get(name);
  }

  /**
   * Define a new enumerated type with the given name.
   * @exception EnumerationAlreadyDefinedException Thrown if an
   * enumeration with the given name is already defined.
   */
  protected synchronized static void defineEnumeration(
    final String name,
    final IREnumeratedType type)
    throws EnumerationAlreadyDefinedException {
    if (typeExists(name)) {
      throw new EnumerationAlreadyDefinedException(name);
    } else {
      types.put(name, type);
    }
  }

  //============================================================
  //== Initializers
  //============================================================

  /**
   * Create a new enumeration.
   * @param typeName The name of the enumeration
   * @param n The names of the elements of the enumeration.  The
   * order of the names is the same as the order in which they
   * appear in the array.
   * @param register <code>true</code> iff the type should be registered
   * @exception EnumerationAlreadyDefinedException Thrown if an
   * enumrated type with the same name is already defined.
   */
  private IREnumeratedType(
    final String typeName,
    final String[] n,
    final boolean register)
    throws EnumerationAlreadyDefinedException {
    name = typeName;
    names = new String[n.length];
    System.arraycopy(n, 0, names, 0, n.length);

    reps = new Element[names.length];
    for (int i = 0; i < names.length; i++)
      reps[i] = null;

    if (register)
      defineEnumeration(name, this);
  }

  /**
   * Create a new enumeration.
   * @param typeName The name of the enumeration
   * @param n The names of the elements of the enumeration.  The
   * order of the names is the same as the order in which they
   * appear in the array.
   * @exception EnumerationAlreadyDefinedException Thrown if an
   * enumrated type with the same name is already defined.
   */
  public IREnumeratedType(final String typeName, final String[] n)
    throws EnumerationAlreadyDefinedException {
    this(typeName, n, true);
  }

  private IREnumeratedType() {
    name = "special null enumeration";
    names = new String[0];
    reps = new Element[0];
  }

  //============================================================
  //== Enumeration related methods
  //============================================================

  /**
   * Get the name of the type.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the number of elements in the enumeration.
   */
  public int size() {
    return names.length;
  }

  /**
   * Get the canonical representation of the <code>i</code><sup>th</sup>
   * member of the enumeration.
   * @exception IllegalArgumentException if <code>i</code> is outside of
   * the range <code>0</code> to <code>size()-1</code>.
   */
  public synchronized Element getElement(final int i) {
    try {
      if (reps[i] == null)
        reps[i] = new Element(name, i);
    } catch (final ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException(
        i + " is not a value in this enumeration (size = " + reps.length + ")");
    }
    return reps[i];
  }

  /**
   * Get the first element of the enumeration.
   */
  public Element getFirst() {
    return getElement(0);
  }

  /**
   * Get the last element of the enumeration.
   */
  public Element getLast() {
    return getElement(size() - 1);
  }

  /**
   * Get the next element of the enumeration.
   * @exception NoSuchElementException Thrown if <code>v</code> is the
   * last element of the enumeration.
   */
  private Element getNext(final Element v) throws NoSuchElementException {
    if (v.equals(getLast()))
      throw new NoSuchElementException();
    return getElement(v.getIndex() + 1);
  }

  /**
   * Get the previous element of the enumeration.
   * @exception NoSuchElementException Thrown if <code>v</code> is the
   * first element of the enumeration.
   */
  private Element getPrev(final Element v) throws NoSuchElementException {
    if (v.equals(getFirst()))
      throw new NoSuchElementException();
    return getElement(v.getIndex() - 1);
  }

  /**
   * Get the name of the i<sup>th</sup> element.
   */
  public String elementName(final int i) {
    return names[i];
  }

  /**
   * Get the index, given the name. does sequencial search.
   * @return -1 if given string is not in list of enumeration values
   */
  public int getIndex(final String n) {
    if (n != null) {
      for (int i = 0; i < names.length; i++) {
        if (n.compareTo(names[i]) == 0)
          return i;
      }
    }
    return -1;
  }

  public String toString() {
    String str = "Enumeration " + name + ":";
    for (int i = 0; i < names.length; i++) {
      str += (" \"" + names[i] + "\"");
    }
    return str;
  }

  //============================================================
  //== IRType related methods
  //============================================================

  public boolean isValid(final Object value) {
    if (value == null) {
      return false;
    } else if (value.getClass() == Element.class) {
      final Element val = (Element) value;
      return (val.getType() == this);
    } else {
      return false;
    }
  }

  public Comparator getComparator() {
    return this;
  }

  protected void writeValueInternal(final Object value, final IROutput out)
    throws IOException {
    //final Element elt = (Element) value;
    // out.writeUTF( name );
    out.writeInt(((Element) value).getIndex());
  }

  protected Object createValue(final IRInput in) throws IOException {
    // final String type = in.readUTF();
    // if( !type.equals( name ) ) {
    //   throw new IOException( "Element of enumeration " + type
    //                           + " encountered when reading for enumeration "
    //                           + name  );
    // }
    return getElement(in.readInt());
  }

  public void writeType(final IROutput out) throws IOException {
    out.writeByte(ENUM_TYPE);
    out.writeUTF(name);
    out.writeInt(reps.length);
    for (int i = 0; i < names.length; i++) {
      out.writeUTF(names[i]);
    }
  }

  public IRType readType(final IRInput in) throws IOException {
    final String type = in.readUTF();
    final int s = in.readInt();
    final String[] n = new String[s];
    for (int i = 0; i < s; i++)
      n[i] = in.readUTF();

    try {
      final IREnumeratedType en = new IREnumeratedType(type, n, false);
      if (IREnumeratedType.typeExists(type)) {
        final IREnumeratedType existingType =
          IREnumeratedType.getEnumeration(type);
        if (!existingType.equals(en)) {
          throw new IOException(
            "Found an enumeration named \""
              + type
              + "\", but name is already taken.");
        } else {
          return existingType;
        }
      } else {
        IREnumeratedType.defineEnumeration(type, en);
        return en;
      }
    } catch (final EnumerationAlreadyDefinedException e) {
      // won't happen
      return null;
    }
  }

  public Object fromString(String s) {
    return Element.valueOf(this, s);
  }

  public String toString(Object o) {
    final Element e = (Element) o;
    return e.toString();
  }

  //============================================================
  //== Comparator Methods
  //============================================================

  /**
   * Compare two elements of the enumeration.
   * @return A value less than zero if <code>o1</code> comes before <code>o2</code>;
   *         zero if <code>o1</code> is equal to <code>o2</code>;
   *         A value greater than zero if <code>o1</code> comes after <code>o2</code>.
   * @exception ClassCastException Thrown if neither argument is
   *            {@link java.lang.Integer}, or if one of the values is out of
   *            range for the enumeration.
   */
  public int compare(final Object o1, final Object o2)
    throws ClassCastException {
    final Element e1 = (Element) o1;
    final Element e2 = (Element) o2;
    return e1.compareTo(e2);
  }

  /**
   * Two enumerations are equal if and only they have the same name,
   * the name number of elements, and the elements have the same names,
   * in the same order.
   */
  public boolean equals(final Object o) {
    if (o == null) {
      return false;
    }
    if (o.getClass() == IREnumeratedType.class) {
      final IREnumeratedType t = (IREnumeratedType) o;
      if (t.size() == size()) {
        for (int i = 0; i < size(); i++) {
          if (!elementName(i).equals(t.elementName(i))) {
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

  public int hashCode() {
    return name.hashCode();
  }

  //============================================================
  //== An Enumeration Element
  //============================================================

  /**
   * An element of an IREnumeratedType.
   * This type is immutable and is thus thread safe.
   */
  public static class Element implements Comparable {
    /**
     * The name of the type this element belongs to.
     * This field is not assumed to be unique.
     */
    private final String type;

    /** The index of this element within its enumeration. */
    private final int value;

    /** Create a new enumeration element. */
    Element(final String t, final int v) {
      type = t;
      value = v;
    }

    /**
     * Get the type this element belongs to.
     */
    public IREnumeratedType getType() {
      return IREnumeratedType.getEnumeration(type);
    }

    /**
     * Get the index of the element within its enumeration.
     */
    public int getIndex() {
      return value;
    }

    /**
     * Query if the element is the first element of its enumeration.
     */
    public boolean isFirst() {
      return (value == 0);
    }

    /**
     * Query if the element is the last element of its enumeration.
     */
    public boolean isLast() {
      return (value + 1) == getType().size();
    }

    /**
     * Get the next element.
     * @exception NoSuchElementException Thrown if there is no next element.
     */
    public Element next() {
      return getType().getNext(this);
    }

    /**
     * Get the previous element.
     * @exception NoSuchElementException Thrown if there is no previous element.
     */
    public Element prev() {
      return getType().getPrev(this);
    }

    /**
     * Returns the name of the element.
     */
    public String toString() {
      return getType().elementName(value);
    }

    /**
     * Query if another element is comparable to (in the same
     * type as) this one.
     */
    public boolean isComparable(final Element e) {
      return type.equals(e.type);
    }

    /**
     * Two elements are equal if they are the same index within
     * the same enumeration.
     */
    public boolean equals(final Object o) {
      if (this == null || o == null) {
        return (this == o);
      }
      if (o.getClass() == Element.class) {
        final Element e = (Element) o;
        return type.equals(e.type) && (value == e.value);
      } else {
        return false;
      }
    }

    public int hashCode() {
      return value;
    }

    public int compareTo(final Object o) throws ClassCastException {
      final Element e = (Element) o;
      if (!isComparable(e))
        throw new ClassCastException();
      return value - e.value;
    }

    public static Element valueOf(final IREnumeratedType t, final int vi) {
      return t.getElement(vi);
    }

    /** @exception IllegalArgumentException */
    public static Element valueOf(final IREnumeratedType t, final String vs) {
      int vi = t.getIndex(vs);
      if (vi < 0) {
        throw new IllegalArgumentException(
          "'" + t.getName() + "' has no '" + vs + "' as enumeration");
      }
      return Element.valueOf(t, vi);
    }
  }

  //============================================================
  //== Test driver
  //============================================================

  public static void main(String[] args)
    throws EnumerationAlreadyDefinedException {
    final IREnumeratedType enum1 =
      new IREnumeratedType(
        "Type 1",
        new String[] { "one", "two", "three", "four", "five" });
    final IREnumeratedType enum2 =
      new IREnumeratedType("Type 2", new String[] { "a", "b", "c", "d" });

    try {
      new IREnumeratedType("Type 2", new String[] { "e", "f" });
    } catch (EnumerationAlreadyDefinedException e) {
      System.out.println("Already defined.");
    }

    System.out.println(enum1.toString());
    Element e = enum1.getFirst();
    do {
      System.out.println(
        "Element "
          + e
          + " "
          + e.getIndex()
          + " "
          + e.isFirst()
          + " "
          + e.isLast()
          + " "
          + e.compareTo(e));
      try {
        final Element f = e.next();
        System.out.println(
          toEnglish(e.compareTo(f)) + " " + toEnglish(f.compareTo(e)));
        e = f;
      } catch (NoSuchElementException ex) {
        System.out.println("caught err");
        break;
      }
    } while (true);

    System.out.println("\n" + enum1.toString());
    e = enum2.getLast();
    do {
      System.out.println(
        "Element "
          + e
          + " "
          + e.getIndex()
          + " "
          + e.isFirst()
          + " "
          + e.isLast()
          + " "
          + e.compareTo(e));
      try {
        final Element f = e.prev();
        System.out.println(
          toEnglish(e.compareTo(f)) + " " + toEnglish(f.compareTo(e)));
        e = f;
      } catch (NoSuchElementException ex) {
        System.out.println("caught err");
        break;
      }
    } while (true);

    try {
      enum1.getElement(2).compareTo(enum2.getElement(3));
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  private static String toEnglish(final int i) {
    if (i < 0)
      return "before";
    else if (i == 0)
      return "equal";
    else
      return "after";
  }
}