/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/unparse/Combined.java,v 1.1 2006/03/21 23:20:56 dig Exp $ */
package fluid.unparse;

import fluid.ir.IRNode;

public class Combined extends Token {
  private final Token token1, token2;
  public Combined(Token tok1, Token tok2) {
    token1 = tok1;
    token2 = tok2;
  }
  public void emit(TokenStream ts, IRNode aloc) {
    token1.emit(ts,aloc);
    token2.emit(ts,aloc);
  }
}
