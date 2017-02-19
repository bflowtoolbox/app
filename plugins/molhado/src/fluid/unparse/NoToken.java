/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/unparse/NoToken.java,v 1.1 2006/03/21 23:20:56 dig Exp $ */
package fluid.unparse;

import fluid.ir.IRNode;

public class NoToken extends Token {
  public final static NoToken prototype = new NoToken();

  public NoToken() { }
  public void emit(TokenStream ts, IRNode aloc) { }
}
