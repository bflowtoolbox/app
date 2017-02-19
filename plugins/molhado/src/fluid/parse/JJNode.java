package fluid.parse;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;

import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.ir.IRRegion;
import fluid.ir.IRStringType;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotInfo;
import fluid.ir.SlotUndefinedException;
import fluid.java.JavaGlobals;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;
import fluid.tree.SyntaxTreeInterface;
import fluid.util.FileLocator;
import fluid.util.UniqueID;
import fluid.version.TreeChanged;
import fluid.version.VersionedSlotFactory;

/** An IRNode being created by the parser.
 * We have extra methods to interface with the parser.
 * @see JJOperator
 * @see SyntaxTree
 */
public class JJNode extends PlainIRNode implements Node {
  /** Slot Factory used to define tree
   */
	public static final SlotFactory treeSlotFactory = JJNodeBoot.slotFactory;
	
  /** We define a specific syntax tree to be used for
   * all parseable languages.  The corresponding operators can
   * all inherit from JJOperator.
   * @see JJOperator
   */
  public static final SyntaxTree tree = JJNodeBoot.tree;

  /** An attribute to store tokens for each node.
   */
  private static final SlotInfo infoSlotInfo = JJNodeBoot.infoSlotInfo;

  /** Things interested in knowing whether a subtree has changed,
   * can use the methods in this object.
   */
  public static final TreeChanged treeChanged = JJNodeBoot.treeChanged;

  /** This flag is true if the children are variable.
   * and so to add a child, one uses appendChild, rather than
   * setChild.
   */
  protected final boolean appendChild;

  /** The index of the next child to be set.
   * It is used only when appendChild is false.
   * It is incremented after every child is added.
   */
  protected int nextChild = 0;

  public JJNode(Operator operator) {
    this(tree, operator);
  }

  /** Create a new node with a minimum number of children
   * (when varying).
   */
  public JJNode(Operator operator, int min) {
    this(tree, operator, min);
  }

  /** Constructor for bottom-up tree creation. */
  public JJNode(Operator operator, IRNode[] children) {
    this(tree, operator, children);
  }

  public JJNode(IRRegion region, Operator operator) {
    this(region, tree, operator);
  }

  /** Create a new node with a minimum number of children
   * (when varying).
   */
  public JJNode(IRRegion region, Operator operator, int min) {
    this(region, tree, operator, min);
  }

  /** Constructor for bottom-up tree creation. */
  public JJNode(IRRegion region, Operator operator, IRNode[] children) {
    this(region, tree, operator, children);
  }

  public JJNode(SyntaxTreeInterface tree, Operator operator) {
    super();
    tree.initNode(this, operator);
    appendChild = operator.numChildren() < 0;
  }

  /** Create a new node with a minimum number of children
   * (when varying).
   */
  public JJNode(SyntaxTreeInterface tree, Operator operator, int min) {
    super();
    tree.initNode(this, operator, min);
    appendChild = false;
  }

  /** Constructor for bottom-up tree creation. */
  public JJNode(
    SyntaxTreeInterface tree,
    Operator operator,
    IRNode[] children) {
    super();
    tree.initNode(this, operator, children);
    appendChild = false;
  }

  public JJNode(IRRegion region, SyntaxTreeInterface tree, Operator operator) {
    super(region);
    tree.initNode(this, operator);
    appendChild = operator.numChildren() < 0;
  }

  /** Create a new node with a minimum number of children
   * (when varying).
   */
  public JJNode(
    IRRegion region,
    SyntaxTreeInterface tree,
    Operator operator,
    int min) {
    super(region);
    tree.initNode(this, operator, min);
    appendChild = false;
  }

  /** Constructor for bottom-up tree creation. */
  public JJNode(
    IRRegion region,
    SyntaxTreeInterface tree,
    Operator operator,
    IRNode[] children) {
    super(region);
    tree.initNode(this, operator, children);
    appendChild = false;
  }

  // unused methods
  public void jjtOpen() {
  }
  public void jjtClose() {
  }
  public void jjtSetParent(Node n) {
  }

  public void jjtAddChild(Node n) {
    if (appendChild)
      tree.appendSubtree(this, (JJNode) n);
    else
      tree.setChild(this, nextChild++, (JJNode) n);
  }
  public void jjtAddChild(Node n, int i) {
    if (appendChild)
      // assume reverse order
      // FIX insert nulls if necessary
      tree.insertSubtree(this, (JJNode) n);
    else
      tree.setChild(this, i, (JJNode) n);
  }

  public void setInfo(String s) {
    setSlotValue(infoSlotInfo, s);
  }

  public static void setInfo(IRNode node, String s) {
    node.setSlotValue(infoSlotInfo, s);
  }

  public static String getInfo(IRNode node) {
    return (String) node.getSlotValue(infoSlotInfo);
  }
  
  public static String getInfoOrNull(IRNode node) {
  	if (!node.valueExists(infoSlotInfo)) {
  		return null;
  	}
  	return getInfo(node);
  }

  public static void doindent(PrintStream s, int i) {
    for (; i > 0; --i) {
      s.print("  ");
    }
  }

  public static String toString(IRNode node) {
    if (node == null) {
      return "null";
    } else {
      String opName = tree.getOperator(node).name();
      try {
        String info = getInfo(node);
        if (info == null)
          return opName + " null";
        else
          return opName + " " + info;
      } catch (SlotUndefinedException e) {
        return opName;
      }
    }
  }

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

  public static IRNode copyTree(IRNode root) {
    if (root == null)
      return root;
    return ((JJOperator) tree.getOperator(root)).copyTree(root);
  }

  private static Bundle jjbundle = null;
  static {
    try {
      jjbundle =
        Bundle.loadBundle(
          UniqueID.parseUniqueID("parse"),
          IRPersistent.fluidFileLocator);
    } catch (IOException ex) {
      JavaGlobals.PARSE.info(ex.toString());
    }
  }

  public static Bundle getBundle() {
    return jjbundle;
  }

  public static void main(String args[]) {
    Bundle b = getBundle();
    if (b != null)
      b.describe(System.out);
  }
}

/** This class holds the definitions of the attributes
 * associated with JJNode.  it is in a separate class so that
 * we can have code that generates the Bundle.  In JJNode,
 * the bundle is assumed to be already stored, and is loaded.
 */
class JJNodeBoot {
  /** Slot Factory used to define tree
   */
	//public static final SlotFactory slotFactory = SimpleSlotFactory.prototype;
  public static final SlotFactory slotFactory = VersionedSlotFactory.prototype;

  /** We define a specific syntax tree to be used for
   * all parseable languages.  The corresponding operators can
   * all inherit from JJOperator.
   * @see JJOperator
   */
  public static final SyntaxTree tree;

  /** An attribute to store tokens for each node.
   */
  static final SlotInfo infoSlotInfo;

  /** Things interested in knowing whether a subtree has changed,
   * can use the methods in this object.
   */
  public static final TreeChanged treeChanged;

  static {
    try {
    	// Hack TO TEMP SOLVE VERSIONING PROBLEM (1 Aug 02)
    	// Changed from VersionSlotFactory to SimpleSlotFactory
    	// during Eclipse demo work -- effectively made the IR nodes
    	// representing the Java files and JARS non-versioned

      tree = new SyntaxTree("Parse", slotFactory);
      
      // ALSO
      treeChanged = new TreeChanged("Parse.changed", tree);
      
      /*
      treeChanged = new TreeChanged("Parse.changed", tree) {
      	public void addObserver(Observer o) {}
      	public void update(Observable obs, Object node) {}
      	public void noteChange(IRNode node) {}
      };
      */           
      infoSlotInfo =
        slotFactory.newAttribute("JJNode.info", IRStringType.prototype);
      infoSlotInfo.addObserver(treeChanged);
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidError("Parse slots already registered " + e);
    }
  }

  static void saveAttributes(Bundle b) {
    tree.saveAttributes(b);
    b.saveAttribute(infoSlotInfo);
    b.saveAttribute(treeChanged);
  }

  public static void main(String args[]) {
    Bundle b = new Bundle();
    FileLocator floc = IRPersistent.fluidFileLocator;
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
        return;
      }
    }
    new File(b.getID().toString() + ".ab").renameTo(new File("parse.ab"));
  }
}
