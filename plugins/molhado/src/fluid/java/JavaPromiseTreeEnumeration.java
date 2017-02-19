/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaPromiseTreeEnumeration.java,v 1.1 2006/03/21 23:20:59 dig Exp $ */
package fluid.java;


import fluid.ir.IRLocation;
import fluid.ir.IRNode;
import fluid.ir.IRSequence;
import fluid.ir.SlotInfo;
import fluid.tree.DepthFirstSearch;

public class JavaPromiseTreeEnumeration extends DepthFirstSearch {
  /* more state */
  private int promiseIndex;
  private IRLocation ploc;
  private Object promise;

  private static Integer[] integers =
      new Integer[JavaPromise.promiseChildrenInfo.length+1];
      
  static {
    for (int i=0; i <= JavaPromise.promiseChildrenInfo.length; ++i) {
      integers[i] = new Integer(i);
    }
  }

  public JavaPromiseTreeEnumeration(IRNode root, boolean bottomUp) {
    super(JavaNode.tree,root,bottomUp);
  }

  protected void pushState() {
    super.pushState();
    stack.push(integers[promiseIndex]);
    stack.push(ploc);
    stack.push(promise);
  }

  protected void popState() {
    promise = stack.pop();
    ploc = (IRLocation)stack.pop();
    promiseIndex = ((Integer)stack.pop()).intValue();
    super.popState();
  }

  protected void visit(IRNode node) {
    super.visit(node);
    promiseIndex = 0;
    setPromiseLocation(node);
  }

  protected final void setPromiseLocation(IRNode node) {
    while (promiseIndex < JavaPromise.promiseChildrenInfo.length) {
      SlotInfo si = JavaPromise.promiseChildrenInfo[promiseIndex];
      if (node.valueExists(si)) {
	promise = node.getSlotValue(si);
	if (promise instanceof IRSequence) {
	  if (((IRSequence)promise).hasElements()) {
	    ploc = ((IRSequence)promise).firstLocation();
	    return;
	  }
	} else if (promise != null) {
	  return;
	}
      }
      ++promiseIndex;
    }
  }

  protected boolean additionalChildren(IRNode node) {
    while (promiseIndex < JavaPromise.promiseChildrenInfo.length) {
      if (promise instanceof IRSequence) {
        if (ploc == null) {
          ++promiseIndex;
          setPromiseLocation(node);
        }
        else {
          IRNode newNode = (IRNode) ((IRSequence) promise).elementAt(ploc);
          ploc = ((IRSequence) promise).nextLocation(ploc);
          if (newNode != null) {
            visit(newNode);
            return true;
          }
        }
      }
      else {
        IRNode newNode = (IRNode) promise;
        ++promiseIndex;
        setPromiseLocation(node);
        visit((IRNode) newNode);
      }
    }
    return false;
  }

  protected boolean mark(IRNode node) {
    return node != null;
  }
}
