/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/JavaNode.java,v 1.1 2006/03/21 23:20:59 dig Exp $ */
package fluid.java;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import fluid.FluidError;
import fluid.FluidRuntimeException;
import fluid.ir.Bundle;
import fluid.ir.ConstantSlotFactory;
import fluid.ir.IRIntegerType;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IRPersistent;
import fluid.ir.IRStringType;
import fluid.ir.IRType;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.parse.JJNode;
import fluid.tree.IROperatorType;
import fluid.tree.Operator;
import fluid.tree.SyntaxTreeInterface;
import fluid.unparse.Combined;
import fluid.unparse.Delim;
import fluid.unparse.Glue;
import fluid.unparse.Identifier;
import fluid.unparse.IndepBP;
import fluid.unparse.Keyword;
import fluid.unparse.Token;
import fluid.unparse.UnitedBP;
import fluid.util.UniqueID;

/** The class used by the parser to build Java IR.
 * Not all nodes of Java IR (for example, IRNodes stored and then
 * loaded) need have this type.
 * @see JavaOperator
 */
public class JavaNode extends JJNode {
  /*
  %- Existing stuff
  */
  public JavaNode(Operator operator) {
    super(operator);
  }

  /** Constructor for bottom-up tree creation. */
  public JavaNode(Operator operator, IRNode[] children) {
    super(operator, children);
  }

  public JavaNode(SyntaxTreeInterface tree, Operator operator) {
    super(tree, operator);
  }

  /** Constructor for bottom-up tree creation. */
  public JavaNode(
    SyntaxTreeInterface tree,
    Operator operator,
    IRNode[] children) {
    super(tree, operator, children);
  }

  /*
  %- SlotInit stuff
  */
  public static SlotInfo getSlotInfo(String slotName) {
    try {
      //! Will need to give a real type when we want it to persist.
      return SimpleSlotFactory.makeSlotInfo(slotName, null);
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidRuntimeException(slotName + " slot already allocated");
    }
  }

  public static SlotInfo getConstantSlotInfo(String slotName, IRType ty) {
    try {
      return ConstantSlotFactory.makeSlotInfo(slotName, ty);
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidRuntimeException(slotName + " slot already allocated");
    }
  }

  public static SlotInfo getVersionedSlotInfo(String slotName, IRType ty) {
    try {
      // HACK TO MAKE JAVA UNVERSIONED -- NEEDS TO BE FIXED
      SlotInfo si = treeSlotFactory.newAttribute(slotName, ty);
      si.addObserver(treeChanged);
      return si;
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidRuntimeException(slotName + " slot already allocated");
    }
  }

  public static SlotInfo getVersionedSlotInfo(
    String slotName,
    IRType ty,
    Object defaultValue) {
    try {
      // HACK TO MAKE JAVA UNVERSIONED -- NEEDS TO BE FIXED
      SlotInfo si = treeSlotFactory.newAttribute(slotName, ty, defaultValue);
      si.addObserver(treeChanged);
      return si;
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidRuntimeException(slotName + " slot already allocated");
    }
  }

  /*
  %- Modifiers stuff
  */
  private static final SlotInfo modifiersSlotInfo =
    getVersionedSlotInfo(
      "Java.modifiers",
      IRIntegerType.prototype,
      new Integer(0));

  public final void setModifiers(int i) {
    setModifiers(this, i);
  }

  public final int getModifiers() {
    return getModifiers(this);
  }

  public static void setModifiers(IRNode node, int i) {
    node.setSlotValue(modifiersSlotInfo, i);
  }

  public static int getModifiers(IRNode node) {
    return node.getIntSlotValue(modifiersSlotInfo);
  }

  public static final int ALL_FALSE = 0;
  public static final int ABSTRACT = (1 << 0);
  public static final int FINAL = (1 << 1);
  public static final int NATIVE = (1 << 2);
  public static final int PRIVATE = (1 << 3);
  public static final int PROTECTED = (1 << 4);
  public static final int PUBLIC = (1 << 5);
  public static final int STATIC = (1 << 6);
  public static final int SYNCHRONIZED = (1 << 7);
  public static final int TRANSIENT = (1 << 8);
  public static final int VOLATILE = (1 << 9);
  public static final int STRICTFP = (1 << 10);

  private static final int LASTMODIFIER = STRICTFP;

  static final String[] modifiers =
    {
      "abstract",
      "final",
      "native",
      "private",
      "protected",
      "public",
      "static",
      "synchronized",
      "transient",
      "volatile",
      "strictfp" };

  // verify that the mask is good 
  private static void checkMod(int mod) {
    int mask = mod - 1;
    int check = mod & mask;

    if ((check == 0) && (mod > 0) && (mod <= LASTMODIFIER)) {
      return;
    }
    throw new FluidRuntimeException("Bad modifier flag");
  }

  public static boolean getModifier(IRNode node, int mod) {
    checkMod(mod);
    int mods = getModifiers(node);

    return ((mods & mod) > 0);
  }

  // FIX?
  public static int setModifier(int mods, int mod, boolean accumulate) {
    checkMod(mod);

    if (accumulate) {
      mods |= mod;
    } else {
      mods &= mod;
    }
    return mods;
  }

  public static void setModifier(IRNode node, int mod, boolean value) {
    int mods = setModifier(getModifiers(node), mod, value);
    setModifiers(node, mods);
  }

  /*
  %- Op stuff
  */
  private static final SlotInfo opSlotInfo =
    getVersionedSlotInfo("Java.op", IROperatorType.prototype);

  public static void setOp(IRNode node, JavaOperator i) {
    node.setSlotValue(opSlotInfo, i);
  }

  public static JavaOperator getOp(IRNode node) {
    return (JavaOperator) node.getSlotValue(opSlotInfo);
  }

  /*
  %- Dimensions
  */
  private static final SlotInfo dimsSlotInfo =
    getVersionedSlotInfo("Java.dims", IRIntegerType.prototype);

  public static void setDimInfo(IRNode node, int dims) {
    node.setSlotValue(dimsSlotInfo, dims);
  }

  public static int getDimInfo(IRNode node) {
    return node.getIntSlotValue(dimsSlotInfo);
  }

  /// Node (for refs to other nodes without re-parenting
  private static final SlotInfo nodeSlotInfo =
    getConstantSlotInfo("Java.node", IRNodeType.prototype);

  public static void setConstantNode(IRNode node, IRNode n) {
    node.setSlotValue(nodeSlotInfo, n);
  }

  public static IRNode getConstantNode(IRNode node) {
    return (IRNode) node.getSlotValue(nodeSlotInfo);
  }

  public static void unparseConstantNode(IRNode node, JavaUnparser u) {
    throw new FluidError("Not implemented");
  }

  /// Int 
  private static final SlotInfo intSlotInfo =
    getConstantSlotInfo("Java.int", IRIntegerType.prototype);

  public static void setConstantInt(IRNode node, int i) {
    node.setSlotValue(intSlotInfo, i);
  }

  public static int getConstantInt(IRNode node) {
    return node.getIntSlotValue(intSlotInfo);
  }

  public static void unparseConstantInt(IRNode node, JavaUnparser u) {
    throw new FluidError("Not implemented");
  }

  /*
    %- code for compiled methods.
   */

  /** The code slot holds the byte code and extra information
   * such as exception ranges necessary to execute a method on
   * the virtual machine.  
   *! Currently, we assume it is a string. This will be changed.
   */
  private static final SlotInfo codeSlotInfo =
    getVersionedSlotInfo("Java.code", IRStringType.prototype);

  public static void setCode(IRNode node, Object code) {
    node.setSlotValue(codeSlotInfo, code);
  }

  public static Object getCode(IRNode node) {
    return node.getSlotValue(codeSlotInfo);
  }

  /*
    %- comment
   */
  /** This slot holds text of comments.
   *! Not currently used, but I want it in the bundle
   * since Bundle's are immutable.
   */
  public static final SlotInfo commentSlotInfo =
    // new RootNamedSlotInfoWrapper(
      getVersionedSlotInfo("Java.comment", IRStringType.prototype);

  public static void setComment(IRNode node, String comment) {
    node.setSlotValue(commentSlotInfo, comment);
  }

  public static String getComment(IRNode node) {
    return (String) node.getSlotValue(commentSlotInfo);
  }

  public static String getCommentOrNull(IRNode node) {
    if (node.valueExists(commentSlotInfo)) {
      return (String) node.getSlotValue(commentSlotInfo);
    }
    return null;
  }

  // Copied from JavaPromise formatting
  private static Glue commentIndent = new Glue(2);
  private static Token startComments =
    new Combined(
      new Keyword(""),
      new UnitedBP(1, "comment", Glue.UNIT, commentIndent));
  private static Token startComment =
    new Combined(
      new UnitedBP(2, "comment", Glue.UNIT, Glue.UNIT),
      new Combined(new Keyword(""), IndepBP.JUXTBP));
  private static Token stopComments =
    new Combined(
      new UnitedBP(1, "comment", Glue.UNIT, Glue.JUXT),
      new Combined(new Keyword(""), IndepBP.DEFAULTBP));

  public static void unparseComment(IRNode node, JavaUnparser u) {
    String comment = getCommentOrNull(node);
    if (comment == null) {
      return;
    }
    StringTokenizer st = new StringTokenizer(comment, "\n\r\f");
    if (st.hasMoreTokens()) {
      // does it starts with Javadoc?
      // omit leading * and spaces if present
      // detect @ tags
      startComments.emit(u, node);
      do {
        String t = st.nextToken();
        String s = t.trim();
        if (s.length() >= 2 && s.charAt(0) == '*') {
          char c = s.charAt(1);
          if (c == ' ') {
            t = s = s.substring(2).trim();
          } else if (c == '/') {
            t = "*/";
          }
        }
        /*
        if (s.charAt(0) == '@') {
          t = "TAG " + s;
        }
        */
        startComment.emit(u, node);
        (new Identifier(t)).emit(u, node);
      } while (st.hasMoreTokens());

      stopComments.emit(u, node);
    }
  }

  /*
  %- Unparsing  
   */
  private static Token ellipsis = new Delim("...");

  public static void unparse(IRNode node, JavaUnparser u) {

    //! TODO: add comments
    if (node == null) {
      // Assume that it is elided
      // What node should I associate it with?
      ellipsis.emit(u, null);
      return;
    }
    unparseComment(node, u);
    ((JavaOperator) u.getTree().getOperator(node)).unparseWrapper(node, u);
  }

  // FIX SlotInfos for SyntaxTreeInterface??

  public static void unparseInfo(IRNode node, JavaUnparser u) {
    (new Identifier(getInfo(node).toString())).emit(u, node);
  }

  private static Vector dimInfos = new Vector();
  public static void unparseDimInfo(IRNode node, JavaUnparser u) {
    int dims = getDimInfo(node);
    if (dims > 0) {
      if (dimInfos.size() < dims + 1)
        dimInfos.setSize(dims + 1);
      Token tok = (Token) dimInfos.elementAt(dims);
      if (tok == null) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dims; ++i)
          sb.append("[]");
        tok = new Keyword(sb.toString());
        dimInfos.setElementAt(tok, dims);
      }
      tok.emit(u, node);
    }
  }

  public static Token getDimToken(IRNode node) {
    int dims = getDimInfo(node);
    if (dims > 0) {
      if (dimInfos.size() < dims + 1)
	       dimInfos.setSize(dims + 1);
      Token tok = (Token) dimInfos.elementAt(dims);
      if (tok == null) {
	       StringBuffer sb = new StringBuffer();
	       for (int i = 0; i < dims; ++i)
	         sb.append("[]");
	       tok = new Keyword(sb.toString());
	       dimInfos.setElementAt(tok, dims);
      }
      return tok;
    }
    else return null;
  }

  static final Token[] modifierTokens = new Token[modifiers.length];
  static {
    for (int i = 0; i < modifiers.length; ++i) {
      modifierTokens[i] = new Keyword(modifiers[i]);
    }
  }

  public static void unparseModifiers(IRNode node, JavaUnparser u) {
    int mods = getModifiers(node);
    for (int i = 0; mods != 0; ++i, mods >>>= 1) {
      if ((mods & 1) != 0) {
        modifierTokens[i].emit(u, node);
      }
    }
  }

  public static Token[] getModiferTokens(IRNode node) {
    Vector modifierVector = new Vector();
    int mods = getModifiers(node);
    for (int i = 0; mods != 0; ++i, mods >>>= 1) {
      if ((mods & 1) != 0) {
        modifierVector.add(modifierTokens[i]);
      }
    }

    Token[] modifierTokens = new Token[modifierVector.size()];
    modifierTokens = (Token[]) modifierVector.toArray(modifierTokens);

    return modifierTokens;
  }

  public static void unparseOp(IRNode node, JavaUnparser u) {
    getOp(node).asToken().emit(u, node);
  }

  private static Token codeToken = new Keyword("<compiled>;");

  public static void unparseCode(IRNode node, JavaUnparser u) {
    codeToken.emit(u, node);
  }

  /** Print a node with Java-specific AST information */
  public static String toString(IRNode node) {
    if (node == null) {
      return "null";
    } else {
      StringBuffer sb = new StringBuffer();
      try {
        int mods = getModifiers(node);
        for (int i = 0; mods != 0; ++i, mods >>>= 1) {
          if ((mods & 1) != 0) {
            sb.append(modifiers[i]);
            sb.append(' ');
          }
        }
      } catch (SlotUndefinedException e) {
      }
      sb.append(JJNode.toString(node));
      try {
        JavaOperator op = getOp(node);
        sb.append(' ');
        sb.append(op.name());
      } catch (SlotUndefinedException e) {
      }
      /* OBSOLETE
      try {
      IRSequence names = getNames(node);
      sb.append(' ');
      for (int i=0; i < names.size(); ++i) {
      if (i > 0) sb.append('.');
      sb.append((String)names.elementAt(i));
      }
      } catch (SlotUndefinedException e) {
      }
      */
      return sb.toString();
    }
  }

  /** Dump a tree made up of Java AST nodes.
   * We add Java-specific AST information.
   * @see JJNode#dumpTree
   */
  public static void dumpTree(PrintStream s, IRNode root, int indent) {
    doindent(s, indent);
    if (root == null) {
      s.println("null");
    } else {
      s.println(toString(root));
      Enumeration children = tree.children(root);
      while (children.hasMoreElements()) {
        IRNode child = (IRNode) children.nextElement();
        dumpTree(s, child, indent + 1);
      }
    }
  }

  /*
   %- Storage methods.
   */

  public static void saveAttributes(Bundle b) {
    // NB: Do not call JJNode.saveAttributes(b);
    b.saveAttribute(modifiersSlotInfo);
    b.saveAttribute(opSlotInfo);
    b.saveAttribute(dimsSlotInfo);
    b.saveAttribute(codeSlotInfo);
    b.saveAttribute(nodeSlotInfo);
    b.saveAttribute(intSlotInfo);
    b.saveAttribute(commentSlotInfo);
  }

  /*
  public static void main(String args[]) {
    Bundle b = new Bundle();
    saveAttributes(b);
    try {
      b.store(floc);
    } catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Please press return to try again");
      try {
  System.in.read();
  b.store(floc);
      } catch (IOException ex2) {
  System.out.println(ex2.toString());
      }
    }
  }
  */

  private static Bundle javabundle = null;
  static {
    try {
      javabundle =
        Bundle.loadBundle(
          UniqueID.parseUniqueID("javanode"),
          IRPersistent.fluidFileLocator);
    } catch (IOException ex) {
      JavaGlobals.PARSE.info(ex.toString());
    }
  }

  public static Bundle getBundle() {
    return javabundle;
  }

  public static void main(String args[]) {
    JJNode.main(args);
    Bundle b = getBundle();
    if (b != null)
      b.describe(System.out);
  }
}
