/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaUnparser.java,v 1.1 2006/03/21 23:20:59 dig Exp $ */
package fluid.java;

import fluid.ir.IRNode;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.TokenStream;

public interface JavaUnparser extends TokenStream {
  public abstract void unparse(IRNode node);
  public abstract JavaUnparseStyle getStyle();
  public abstract SyntaxTreeInterface getTree();
}
