// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/HistoryUnparser.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import fluid.ir.IRNode;
import fluid.unparse.TokenStream;

public interface HistoryUnparser extends TokenStream {
  public abstract void unparse(IRNode node);
  public abstract HistoryUnparseStyle getStyle();
}
