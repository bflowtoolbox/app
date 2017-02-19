// $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaGlobals.java,v 1.1 2006/03/21 23:20:59 dig Exp $
package fluid.java;

import fluid.control.ControlFlowGraph;
import fluid.ir.IRNode;
import fluid.tree.SyntaxTree;
import fluid.util.ImmutableHashOrderSet;

public interface JavaGlobals {
  static final ControlFlowGraph cfg = ControlFlowGraph.prototype;
  static final SyntaxTree jtree = JavaNode.tree;
  static final ImmutableHashOrderSet empty = ImmutableHashOrderSet.empty;

  // mostly used for creating code
  static final IRNode[] noNodes = new IRNode[0];
  static final IRNode[] oneNode = new IRNode[1];

  static final org.apache.log4j.Logger JAVA =
    org.apache.log4j.Logger.getLogger("JAVA");
  static final org.apache.log4j.Logger SEARCH =
    org.apache.log4j.Logger.getLogger("JAVA.searchCP");
  static final org.apache.log4j.Logger BIND =
    org.apache.log4j.Logger.getLogger("JAVA.bind");
  static final org.apache.log4j.Logger PARSE =
    org.apache.log4j.Logger.getLogger("JAVA.parse");
    
  static final org.apache.log4j.Logger XFORM =
    org.apache.log4j.Logger.getLogger("JAVA.xform");    
}
