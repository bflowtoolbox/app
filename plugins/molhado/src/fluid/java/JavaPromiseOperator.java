/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaPromiseOperator.java,v 1.1 2006/03/21 23:20:59 dig Exp $ */
package fluid.java;

import java.util.Observable;
import java.util.Observer;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.tree.Operator;

public abstract class JavaPromiseOperator extends JavaOperator {
	static {
		JavaPromise.treeChanged.addRootObserver(new Observer() {
			public void update(Observable obs, Object x) {
				IRNode node = (IRNode) x;
				try {
          if (JavaPromise.tree.opExists(node)) {          
  					Operator op = JavaPromise.tree.getOperator(node);
  					if (op instanceof JavaPromiseOperator) {
	  					IRNode promisedFor = JavaPromise.getPromisedFor(node);
		  				JavaPromise.treeChanged.noteChange(promisedFor);
			  		}
          }
				}
				catch (SlotUndefinedException e) {}
			}
		});
	}
  
  public JavaPromise createPromise() {
    return new JavaPromise(this);
  }
}
