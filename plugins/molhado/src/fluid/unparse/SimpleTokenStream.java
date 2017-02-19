/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/unparse/SimpleTokenStream.java,v 1.1 2006/03/21 23:20:56 dig Exp $ */
package fluid.unparse;

import fluid.ir.IRNode;

/** A token stream that simply builds up a string without any breaks.
 * A simple technique omits sections that are too deep.
 * @see FmtStream
 */
public class SimpleTokenStream implements TokenStream {
  private final int maxDepth;
  private StringBuffer sb = new StringBuffer();
  private final Token eToken;

  /** Create a token stream that puts all tokens on one line
   * and elides if we try to go deeper than maximum depth.
   * @param elisionToken use this token when eliding.
   */
  public SimpleTokenStream(Token elisionToken, int maximumDepth) {
    eToken = elisionToken;
    maxDepth = maximumDepth;
  }
  
  /** Create a token stream that puts all tokens on one
   * line and does not elide anything.
   */
  public SimpleTokenStream() {
    maxDepth = -1;
    eToken = null;
  }
  
  private boolean lastNotBP = false;
  private int depth = 0;
  private boolean eliding = true;

  public void resetStream() {
    lastNotBP = false;
    depth = 0;
    eliding = false;
    sb.setLength(0);
  }

  public void append(Breakpoint bp, IRNode aloc) {
    if (!eliding) {  
      lastNotBP = false;
      int len = bp.getLength();
      if (len > 0) sb.append(' ');
    }
  }

  public void append(Token tok, IRNode aloc) {
    if (!eliding) {
      if (lastNotBP)
	this.append(IndepBP.DEFAULTBP, aloc);
      lastNotBP = true;
      sb.append(tok.toString());
    }
  }

  public void append(boolean open, Token tok, IRNode aloc) {
    if (!eliding && open && lastNotBP)
      this.append(IndepBP.DEFAULTBP, aloc);
    if (open) {
      if (depth++ == maxDepth) {
	append(eToken,aloc);
	eliding = true;
      }
    } else {
      if (--depth == maxDepth) {
	eliding = false;
      }
    }
  }

  public String toString() {
    return sb.toString();
  }
}
