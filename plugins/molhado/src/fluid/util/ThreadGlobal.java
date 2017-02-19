/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/ThreadGlobal.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;


/** A global variable whose value may differ from thread to thread.
 * We cache the latest value to speed access.
 * The value "null" is a legal default and a lgeal value to a variable to.
 */
public class ThreadGlobal {
  private Object defaultValue;
  private ThreadBinding bindings;

  /** Create a new thread global variable.
   * @param def default value in case not set for thread.
   */
  public ThreadGlobal(Object def) {
    defaultValue = def;
    bindings = new ThreadBinding(Thread.currentThread(),def,null);
  }

  /** Get the current value of this global for the current thread.
   * If not set for this thread, use the default value.
   * We also move the value up to the front for faster access next time.
   * This useful feature is required to make setValue and popValue work.
   * @see #setValue
   * @see #popValue
   */
  public synchronized Object getValue() {
    Thread t = Thread.currentThread();
    ThreadBinding last = null;
    for (ThreadBinding b = bindings; b != null; b = b.next) {
      if (b.thread == t) {
	if (last != null) { // reorder
	  last.next = b.next;
	  b.next = bindings;
	  bindings = b;
	}
	return b.value;
      }
      last = b;
    }
    bindings = new ThreadBinding(t,defaultValue,bindings);
    return defaultValue;
  }

  /** Set the current value of this global for the current thread.
   * @return the old value (the default, if not set yet).
   */
  public synchronized Object setValue(Object newValue) {
    Object oldValue = getValue();
    bindings.value = newValue;
    return oldValue;
  }

  /** Set the current value while remembering the current value.
   * Must be matched by a popValue in the same thread.
   * @see #popValue()
   */
  public synchronized void pushValue(Object newValue) {
    Thread t = Thread.currentThread();
    // bindings = new ThreadBinding(t,newValue,bindings);
    bindings = ThreadBinding.newBinding(t,newValue,bindings);
  }

  /** Restore the remembered value of the last pushValue.
   * Must have been matched by a pushValue in the same thread.
   * We do <em>not</em> require proper nesting between threads.
   * @return value before pop.
   */
  public synchronized Object popValue() {
    Object oldValue = getValue();
    ThreadBinding tb = bindings;
    bindings = bindings.next;
    ThreadBinding.reclaimBinding(tb);
    return oldValue;
  }

  public synchronized String toString() {
    getValue(); // force current thread to front
    StringBuffer sb = new StringBuffer();
    sb.append("{");
    for (ThreadBinding b = bindings; b != null; b=b.next) {
      if (b != bindings) sb.append(",");
      sb.append(b.thread);
      sb.append("=>");
      sb.append(b.value);
    }
    sb.append("}");
    return sb.toString();
  }
}

class ThreadBinding {
  Thread thread;
  Object value;
  ThreadBinding next = null;

  ThreadBinding(Thread t, Object v, ThreadBinding b) {
    thread = t;
    value = v;
    next = b;
  }

  // reusing objects
  static ThreadBinding prealloc = null;

  static ThreadBinding newBinding(Thread t, Object v, ThreadBinding b) {
    if (prealloc == null) {
      return new ThreadBinding(t,v,b);
    }
    // take off stack
    ThreadBinding tb = prealloc;
    prealloc = tb.next;

    // init object
    tb.thread = t;
    tb.value  = v;
    tb.next   = b;
    return tb;
  }

  static void reclaimBinding(ThreadBinding tb) {
    tb.next = prealloc;
    prealloc = tb;
  }
}
  
