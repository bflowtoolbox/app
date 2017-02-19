package fluid.unparse;

import fluid.ir.IRNode;


public abstract class Token {
  // Lexical tokens in the unparse output stream
  String stok;
  public void emit(TokenStream ts, IRNode aloc) {
    // Emit this token, whose owner is aloc.
    // Only Token.emit (and overrides) do emits.
    ts.append(this, aloc);
  }
  public int getLength() {return stok.length();}
  // overridden in Breakpoint, OpenClose
  public String toString() {return stok;}
}
