// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/util/TreeUtil.java,v 1.1 2006/03/21 23:21:00 dig Exp $
package fluid.java.util;

import java.util.Stack;

import fluid.ir.IRNode;
import fluid.java.JavaGlobals;
import fluid.java.JavaPromise;

public class TreeUtil implements JavaGlobals {
  public static Stack findPathUp(IRNode root, IRNode here) {
    Stack stack = new Stack();

    // based on bsi.findRoot
    IRNode parent = JavaPromise.getParentOrPromisedFor(here);
    while (parent != null) {
      if (root == parent) {
	return stack;
      }
      stack.push(parent);
      here = parent;
      parent = JavaPromise.getParentOrPromisedFor(here);
    }
    return null; // ran out of parents w/o matching root
  }
}
