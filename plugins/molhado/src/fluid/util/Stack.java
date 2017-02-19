/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/Stack.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.util;

import java.util.NoSuchElementException;
import java.util.Vector;

public class Stack {
  Vector contents = new Vector();
  public Stack() {}

  public boolean isEmpty() {
    return contents.isEmpty();
  }

  public int size() {
    return contents.size();
  }

  public void push(Object element) {
    contents.addElement(element);
  }

  public Object pop() throws NoSuchElementException {
    Object element = contents.lastElement();
    contents.removeElementAt(contents.size()-1);
    return element;
  }
}

