package fluid.unparse;

import java.util.Stack;

import fluid.ir.IRNode;

public abstract class FmtStream implements TokenStream {
  TokenArray t;
  boolean lastNotBP;
  Stack astStack = new Stack();

  boolean debug;
  StringBuffer s; // For debug

  public FmtStream(boolean debug) {
    this.debug = debug;
    t = new TokenArray();
    resetStream();
  }

  public void resetStream() {
    t.init();
    if (debug) s = new StringBuffer("");
    lastNotBP = false;
  }

  public String toString() {
    if (debug)
      return s.toString();
    else return "Not debugging.";
  }

  public TokenArray getTokenArray() {
    return t;
  }

  public void append(Breakpoint bp, IRNode aloc) {
    lastNotBP = false;
    if (debug) s.append(".");
    t.insertToken(aloc, bp);
  }

  public void append(Token tok, IRNode aloc) {
    if (lastNotBP)
      this.append(IndepBP.DEFAULTBP, aloc);
    lastNotBP = true;
    if (debug) s.append(tok.toString());
    t.insertToken(aloc, tok);
  }

  public void append(boolean open, Token tok, IRNode aloc) {
    if (open && lastNotBP)
      this.append(IndepBP.DEFAULTBP, (IRNode)astStack.peek());
    if (open) astStack.push(aloc); else astStack.pop();
    if (debug) {if (open) s.append("["); else s.append("]");}
    t.insertToken(aloc, open, tok);
  }

  public abstract void unparse(IRNode node);
}
