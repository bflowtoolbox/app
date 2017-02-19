/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaFmtStream.java,v 1.2 2006/06/13 20:56:23 dig Exp $ */
package fluid.java;

import fluid.ir.IRNode;
import fluid.ir.SlotUndefinedException;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.FmtStream;


public class JavaFmtStream extends FmtStream implements JavaUnparser {
  static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("JAVA.unparse");

  private final JavaUnparseStyle style;
  private final SyntaxTreeInterface tree;

  public JavaFmtStream(boolean debug) {
    super(debug);
    style = new JavaUnparseStyle();
    tree  = JavaNode.tree;
  }

  public JavaFmtStream(boolean debug, SyntaxTreeInterface tree) {
    super(debug);
    style      = new JavaUnparseStyle();
    this.tree  = tree;
  }

  public JavaFmtStream(boolean debug, SyntaxTreeInterface tree, JavaUnparseStyle unparseStyle) {
    super(debug);
    style      = unparseStyle;
    this.tree  = tree;
  }
  
  public void unparse(IRNode node) {
    try {
      JavaNode.unparse(node, this);
    } catch(SlotUndefinedException e) {
      LOG.error("Died on "+DebugUnparser.toString(node));
      /*
      if (tree instanceof SyntaxForestModel) {
        SyntaxForestModel model = (SyntaxForestModel) tree;
      }
      */
      throw e;
    }
  }

  public JavaUnparseStyle getStyle() {
    return style;
  }

  public SyntaxTreeInterface getTree() {
    return tree;
  }
}
