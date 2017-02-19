package fluid.unparse;


import fluid.ir.IRNode;

public class OpenClose extends Token {
  boolean open;
  public OpenClose(boolean open) {
    this.open = open;
  }
  public int getLength() {
    return 0;
  }
  public boolean getOpen() {
    return open;
  }
  public void emit(TokenStream ts, IRNode aloc) {
    ts.append(open, this, aloc);
  }
  public String toString () {
    if (open) return "<";
    return ">";
  }
	      
}
