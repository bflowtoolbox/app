package fluid.unparse;

import fluid.ir.IRNode;

/** Interface for what tokens can be inserted in.
 * These methods should only be called by Token emit methods.
 * @see FmtStream
 * @see Token
 */
public interface TokenStream {
  /** Insert a breakpont token. */
  public abstract void append(Breakpoint bp, IRNode aloc);
  /** Insert a normal token, after first inserting a default
   * breakpoint, if no breakpoint since last token.
   */
  public abstract void append(Token tok, IRNode aloc);
  /** Insert an open/close token representing the start/end
   * of the tokens for a node.  A default breakpoint will be inserted
   * before the open unless a breakpoint has been inserted since the
   * last "real" token.
   */
  public abstract void append(boolean open, Token tok, IRNode aloc);
}
