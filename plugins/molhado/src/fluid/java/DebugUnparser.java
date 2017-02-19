package fluid.java;

import fluid.ir.IRNode;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Keyword;
import fluid.unparse.SimpleTokenStream;
import fluid.unparse.Token;

public class DebugUnparser extends SimpleTokenStream implements JavaUnparser {
  private static final Token deepToken = new Keyword("#");
  private static final Token nullToken = new Keyword("@NULL@");
  private final int maxLevel;
  private final SyntaxTreeInterface tree;

  DebugUnparser() {
    super();
    maxLevel = -1;
    tree = JavaNode.tree;
  }

  DebugUnparser(int max) { 
    super(deepToken,max);
    maxLevel = max;
    tree = JavaNode.tree;
  }

  DebugUnparser(int max, SyntaxTreeInterface tree) {
    super(deepToken,max);
    maxLevel = max;
    this.tree = tree;
  }

  private int currLevel = 0;

  public void unparse(IRNode node) {
    if (node == null) {
      nullToken.emit(this,node);
    } else if (currLevel == maxLevel) { // override if we've gone deep enough
      deepToken.emit(this,node);
    } else {
      // reimplements what SimpleFormatStream does, but more efficient,
      // because we can avoid unparsing the node altogether.
      try {
	++currLevel;
	JavaNode.unparse(node,this);
      } finally {
	--currLevel;
      }
    }
  }

  public JavaUnparseStyle getStyle() {
    return JavaUnparseStyle.prototype;
  }

  private static final int MAX = 5;
  private static DebugUnparser[] reusableUnparsers = new DebugUnparser[MAX+2];
  static {
    for(int i=0; i<reusableUnparsers.length-1; i++) {
        reusableUnparsers[i] = new DebugUnparser(i);
    }
    reusableUnparsers[MAX+1] = new DebugUnparser();
  }

  private static DebugUnparser reusableUnparser    = reusableUnparsers[MAX];
  private static DebugUnparser getUnparser(int max) {
    if (max <= MAX) {
	if (max < 0) { max = MAX+1; }
        reusableUnparsers[max].resetStream();
	return reusableUnparsers[max];
    } else {
	return new DebugUnparser(max);
    }
  }


  public static String toString(IRNode node) {
    return toString(node, MAX);
  }

  public static String toString(IRNode node, int max) {
    DebugUnparser du = getUnparser(max);
    du.unparse(node);
    return du.toString();
  }

  public SyntaxTreeInterface getTree() { return tree; }
}
