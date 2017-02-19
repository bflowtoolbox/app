package fluid.util;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/** Association list dictionary:
 * Low space overhead but O(n) search time.
 * @typeparam Key type of keys in the list
 * <dl purpose=fluid>
 *   <dt>capabilities<dd> store, read, equal
 * </dl>
 * @typeparam Value
 */
public class AssocList extends Dictionary {
  /** The key here (or null in an empty alist)
   * @type Key
   */
  protected Object key;
  /** The value associated with this key
   * @type Value
   * @invariant private (nonNull(key)||Null(value))&&
   *                    (Null(key)||NonNull(value))
   */
  protected Object value;

  /** The other entries.
   * @invariant private nonNull(key)||Null(rest)
   * @structure
   */
  protected AssocList rest;

  /** Create an empty association list */
  public AssocList() { }

  /** Create an association list with a single entry
   * @param key initial key
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   * @param value initial value
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @precondition nonNull(key)
   */
  public AssocList(Object key, Object value) {
    if (value != null) {
      this.key = key;
      this.value = value;
    }
  }

  /** return number of distinct entries
   * @pure
   */
  public int size() {
    int here = 0;
    if (key != null) ++here;
    if (rest == null) return here;
    return here + rest.size();
  }

  /** return whether no distinct entries
   * @pure
   */
  public boolean isEmpty() {
    return key == null && (rest == null || rest.isEmpty());
  }

  /** Look up the value for this key
   * @pure
   * @precondition nonNull(key)
   * @param key key to use
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   * @return the value associated with the key (or null)
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  public Object get(Object key) {
    if (this.key != null && key.equals(this.key)) return value;
    if (rest == null) return null;
    return rest.get(key);
  }

  /** Change the value for this key
   * @precondition nonNull(key) && nonNull(newValue)
   * @param key key to use
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   * @param newValue value to associate with the key
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   * @return the value formerly associated with the key (or null)
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  public Object put(Object key, Object newValue) {
    if (this.key == null) {
      this.key = key;
      this.value = newValue;
      return null;
    } else if (key.equals(this.key)) {
      Object oldValue = this.value;
      this.value = newValue;
      return oldValue;
    } else if (rest == null) {
      rest = new AssocList(key,newValue);
      return null;
    } else {
      return rest.put(key,newValue);
    }
  }

  /** Remove the value for this key
   * @precondition nonNull(key)
   * @param key key to use
   * <dl purpose=fluid>
   *   <dt>type<dd> Key
   * </dl>
   * @return the value formerly associated with the key (or null)
   * <dl purpose=fluid>
   *   <dt>type<dd> Value
   * </dl>
   */
  public Object remove(Object key) {
    if (this.key == null) {
      return null;
    } else if (key.equals(this.key)) {
      Object oldValue = this.value;
      if (rest == null) {
	this.key = null;
	this.value = null;
      } else {
	this.key = rest.key;
	this.value = rest.value;
	this.rest = rest.rest;
      }
      return oldValue;
    } else if (rest == null) {
      return null;
    } else {
      return rest.remove(key);
    }
  }

  /** Return the keys for which we have entries.
   * @pure
   * @return an enumeration of keys
   * <dl purpose=fluid>
   *   <dt>type<dd> Enumeration[Key]
   *   <dt>capabilities<dd> cast, read, write
   * </dl>
   */
  public Enumeration keys() {
    return new AssocKeyEnumeration(this);
  }

  /** Return the values in all entries.
   * @pure
   * @return an enumeration of the elements (values associated with keys)
   * <dl purpose=fluid>
   *   <dt>type<dd> Enumeration[Value]
   *   <dt>capabilities<dd> cast, read, write
   * </dl>
   */
  public Enumeration elements() {
    return new AssocValueEnumeration(this);
  }
}

class AssocKeyEnumeration implements Enumeration {
  AssocList cursor;
  AssocKeyEnumeration(AssocList start) {
    cursor = start;
  }

  /** Return whether the enumeration is nonempty.
   * @pure
   * @read cursor
   */
  public boolean hasMoreElements() {
    return cursor != null && !cursor.isEmpty();
  }

  /** Return the next element in the enumeration.
   * @exception NoSuchElementException
   * If the enumeration is empty.
   * <dl purpose=fluid>
   *  <dt>condition<dd> equal(hasMoreElements(),false)
   * </dl>
   * @read cursor
   * @write cursor
   */
  public Object nextElement() {
    if (cursor == null || cursor.key == null) {
      throw new NoSuchElementException("end of association list");
    } else {
      Object key=cursor.key;
      cursor = cursor.rest;
      return key;
    }
  }
}

class AssocValueEnumeration implements Enumeration {
  AssocList cursor;
  AssocValueEnumeration(AssocList start) {
    cursor = start;
  }

  /** Return whether the enumeration is nonempty.
   * @pure
   * @read cursor
   */
  public boolean hasMoreElements() {
    return cursor != null && !cursor.isEmpty();
  }

  /** Return the next element in the enumeration.
   * @exception NoSuchElementException
   * If the enumeration is empty.
   * <dl purpose=fluid>
   *  <dt>condition<dd> equal(hasMoreElements(),false)
   * </dl>
   * @read cursor
   * @write cursor
   */
  public Object nextElement() {
    if (cursor == null || cursor.key == null) {
      throw new NoSuchElementException("end of association list");
    } else {
      Object value=cursor.value;
      cursor = cursor.rest;
      return value;
    }
  }
}

