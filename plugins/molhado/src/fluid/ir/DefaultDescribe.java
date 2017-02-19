// $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/DefaultDescribe.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.ir;

import java.io.PrintStream;

/** A mixim class providing a default
 * implementation of the describe method.
 */
public class DefaultDescribe {
  public void describe(PrintStream out) {
    out.println(this.toString());
  }
}
