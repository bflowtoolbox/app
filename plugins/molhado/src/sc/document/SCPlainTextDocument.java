package sc.document;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;

import sc.xml.IRDTD;
import sc.xml.NullDTD;
import sc.xml.XMLParser;
import fluid.ir.Bundle;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotInfo;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;
import fluid.util.Pair;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
import fluid.version.VersionedChunk;

/**
 * SCPlainTextDocument: plain text document
 * @author Tien
 *
 */
public class SCPlainTextDocument extends SCDocument {
  
  private static final int           magic = 0x54455854; // 'TEXT'
  
  private IRDTD                        dtd;
  // private XMLParser                    parser;
  
  /** to provide tree manipulation operations */
  public static final SyntaxTree tree = DocTreeBundle.tree;

  /** Tree structure bundle containing (tree, treechanged) */
  public static final Bundle          docTreeBundle = DocTreeBundle.getBundle();

  private static Bundle               xmlAttrBundle = XmlAttrBundle.getBundle();

  
  // Constructors
  /**
   * Temporarily do NOT support any other DTD than NullDTD
   * d must be a NullDTD.prototype
   */
  // A new document
  public SCPlainTextDocument(IRDTD d) {
    super(magic);
    if (d == null) d = NullDTD.prototype;
    else dtd = d;
  }
  
  // Existing one
  protected SCPlainTextDocument(UniqueID id) {
    super(magic, id);
    dtd = NullDTD.prototype;
  }
  
  // Access methods

  /** get the DTD associated with the document
   * A document cannot change its DTD
   * @return dtd
   */
  public IRDTD getDTD() { return dtd; }

  public ComponentFactory getFactory() {
    return Factory.prototype;
  }

  public String getFileName() {
    return this.getID()
            + ".cmp";
  }

  // IO : read/write and import/export
  /** loadDelta */
  public void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
    super.loadDelta(era,floc);
    
//    System.out.println("Loading delta for DOCUMENT TREE STRUCTURE ...");
    VersionedChunk vc = VersionedChunk.get(region,docTreeBundle);
    vc.getDelta(era).load(floc);
//    vc.describe(System.out);
    
//    System.out.println("Loading delta for DOCUMENT ATTRIBUTES ...");
    vc = VersionedChunk.get(region,xmlAttrBundle);
    vc.getDelta(era).load(floc);
    vc.describe(System.out);
  }

  /** saveDelta */
  public void saveDelta(Era era, fluid.util.FileLocator floc)
    throws IOException {
    super.saveDelta(era,floc);
    
//    System.out.println("Saving chunk delta for docTreeBundle ...");
    VersionedChunk ch = VersionedChunk.get(region,docTreeBundle);
    IRPersistent vcd = ch.getDelta(era);
    vcd.store(floc);
//    vcd.describe(System.out);
    
//    System.out.println("Saving version CHUNK DELTA FOR DOCUMENT ATTRIBUTES ... ");
    ch = VersionedChunk.get(region,xmlAttrBundle);
    vcd = ch.getDelta(era);
    vcd.store(floc);
//    vcd.describe(System.out);
  }

  /** Load the snapshot of this component for the given version. */
  public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    super.loadSnapshot(v,floc);
    
//    System.out.println("Loading snapshot for DOCUMENT TREE STRUCTURE ...");
    VersionedChunk vc = VersionedChunk.get(region,docTreeBundle);
    ((IRPersistent) vc.getSnapshot(v)).load(floc); 
//    vc.describe(System.out);
    
//    System.out.println("Loading snapshot for DOCUMENT ATTRIBUTES ...");
    vc = VersionedChunk.get(region,xmlAttrBundle);
    ((IRPersistent) vc.getSnapshot(v)).load(floc);
//    vc.describe(System.out);
  }
  
  /** Store a snapshot of this component for the given version. */
  public void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
    super.saveSnapshot(v,floc);
    
//    System.out.println("Saving snapshot for docTreeBundle ... ");
    VersionedChunk ch = VersionedChunk.get(region,docTreeBundle);
    IRPersistent vcs = ch.getSnapshot(v);
    vcs.store(floc);
//    vcs.describe(System.out);
    
    ch = VersionedChunk.get(region,xmlAttrBundle);
//    System.out.println("Saving version snapshot FOR DOCUMENT ATTRIBUTES ... ");
    vcs = ch.getSnapshot(v);
    vcs.store(floc);
//    vcs.describe(System.out);
  }
  
  /** read (and parse) the document from given input stream
   *
   * @param is input stream
   */
  public void readDocument(Reader r) throws SCDocumentParseException {
    importDocument(r);
  }
  
  /** read the document from given Reader
   * @param is input stream
   */
  public void importDocument(Reader r) throws SCDocumentParseException {
    try {
      /* GOOD: use this.createNode(...)
      //  Read the text    
      BufferedReader buffer = new BufferedReader(r);
      StringBuffer text = new StringBuffer();
      for (String line = buffer.readLine(); line != null; 
          line = buffer.readLine()) {
          text.append(line + "\n");
      }         
      // Create a root
      NullDTD nullDtd = (NullDTD) dtd;      
      Operator rootOp = nullDtd.findOperator("SCPlainTextRoot");
      IRNode root_node = this.createNode(rootOp);
      
      // Create a text node under root node    
      Operator rawtextOp = nullDtd.getRawTextOperator();
      IRNode text_node = this.createNode(rawtextOp);
      // Set the textSlot of the textnode to read string
      //? this.setText(text_node,text.toString());
      SlotInfo thisAttr = rawtextOp.getAttribute(nullDtd.getRawTextOperatorAttrName());
      if (thisAttr == null) {
        throw new SCDocumentParseException("Critical error");
      }
      text_node.setSlotValue(thisAttr,thisAttr.type().fromString(text.toString()));
      // Add text_node under root node
      rootOp.tree().addChild(root_node,text_node);
      setRoot(root_node);
      */
      // Read the text    
      BufferedReader buffer = new BufferedReader(r);
      StringBuffer text = new StringBuffer();
      for (String line = buffer.readLine(); line != null; 
          line = buffer.readLine()) {
          text.append(line + "\n");
      }         
      // Create a root
      NullDTD nullDtd = (NullDTD) dtd;
      PlainIRNode.setCurrentRegion(region);
      Operator rootOp = nullDtd.findOperator("SCPlainTextRoot");
      IRNode root_node = rootOp.createNode();
      
      // Create a text node under root node    
      Operator rawtextOp = nullDtd.getRawTextOperator();
      IRNode text_node = rawtextOp.createNode();    
      // Set the textSlot of the textnode to read string
      //? this.setText(text_node,text.toString());
      SlotInfo thisAttr = rawtextOp.getAttribute(nullDtd.getRawTextOperatorAttrName());
      if (thisAttr == null) {
        throw new SCDocumentParseException("Critical error");
      }
      text_node.setSlotValue(thisAttr,thisAttr.type().fromString(text.toString()));
      // Add text_node under root node
      rootOp.tree().addChild(root_node,text_node);
      setRoot(root_node);      
    } catch (IOException e) {
      throw new SCDocumentParseException("IO error" + e.getMessage());
    }
  }  
  
  /** dump the document in given Writer
   *
   * @param w Writer
   */
  public void writeDocument(Writer w, Version v) throws IOException {
    exportDocument(w,v);
  }

  /** export the document to given Writer
   *
   * @param w Writer
   */
  public void exportDocument(Writer w, Version v) throws IOException {    
    IRNode root = getRoot(v);
    if (root==null)
      throw new SCDocumentException("given node is null.");
      
    Version.saveVersion();
    Version.setVersion(v);
    // Dump it out
    IRNode text_node = ithChild(root,0);
    w.write(getText(text_node));
    w.flush();
    Version.restoreVersion();    
  }

  /** at the current version */
  public void exportDocument(Writer w) throws IOException {    
    IRNode root = getRoot();
    if (root==null)
      throw new SCDocumentException("given node is null.");        
    // Dump it out
    IRNode text_node = ithChild(root,0);
    w.write(getText(text_node));
    w.flush();    
  }

  /** Dump this doc into XML format */
  public void dumpInXmlFormat(Writer w, Version v) throws IOException {
    IRNode root = getRoot(v);
    if (root==null)
      throw new SCDocumentException("given node is null.");      
    Version.saveVersion();
    Version.setVersion(v);
    try {
      XMLParser.dumpXmlTree(w, getRoot(v), getDTD(), 1);
    } catch (sc.xml.XMLParserException e) {
      Version.restoreVersion();
      throw new SCDocumentException(e.getMessage());
    }
    w.flush();
    Version.restoreVersion();
  }

  /** Get the plain text of this document */
  public String getPlainText(Version v) {
    IRNode root = getRoot(v);
    if (root==null)
      throw new SCDocumentException("given node is null.");
    Version.saveVersion();
    Version.setVersion(v);
    // Dump it out
    IRNode text_node = ithChild(root,0);
    String text = getText(text_node);
    Version.restoreVersion();
    return text;
  }

  /** Get the plain text of this document */
  public String getPlainText() {
    IRNode root = getRoot();
    if (root==null)
      throw new SCDocumentException("given node is null.");
    // Dump it out
    IRNode text_node = ithChild(root,0);
    return getText(text_node);
  }

  // Write/ReadContents and write/readChangedContents
  public void writeContents(IROutput out) 
    throws IOException
  {
    super.writeContents(out);
    
  }
  
  public void readContents(IRInput in) 
    throws IOException
  {
    super.readContents(in);
  }
  
  public void writeChangedContents(IROutput out)
    throws IOException
  {
    super.writeChangedContents(out);
  }
  
  public void readChangedContents(IRInput in) 
    throws IOException
  {
    super.readChangedContents(in);
  }

  public boolean isChanged()
  {
    return super.isChanged();
  }

  /** OBSOLETE: export the given node in the Writer
   *
   * @param w Writer
   * @throws {@link sc.document.SCDocumentException} if invalid n
   */
  public void exportNode(Writer w, IRNode n, Version v) throws IOException {    
    // DO  NOTHING 
    // if (n==null)
    //  throw new SCDocumentException("given node is null.");
      
    // Version.saveVersion();
    // Version.setVersion(v);
    // try {
    //  XMLParser.dumpXmlTree(w, n, getDTD(), 1);
    // } catch (XMLParserException e) {
    //  Version.restoreVersion();
    //  throw new SCDocumentException(e.getMessage());
    //}
    // w.flush();
    // Version.restoreVersion();
  }
  
  // NODE MANIPULATION
  
  public static boolean isRawTextNode(IRNode n) {
    String name = tree.getOperator(n).name();
    if (name.equals("RawTextOperator")) return true;
    else return false;
  }
    
  public static int numChildren(IRNode n) {
    return tree.numChildren(n);    
  }
  
  public static String elementName(IRNode n) {
    return tree.getOperator(n).name();
  }

  public static Operator getOperator(IRNode n) {
    return tree.getOperator(n);
  }
  
  public static IRNode ithChild(IRNode n, int i) {
    if ((i < 0) || (i >= numChildren(n)))
      throw new SCDocumentException("Index is out of bound !");
    return tree.getChild(n,i);
  }

  public static IRNode parent(IRNode n) {
    return tree.getParentOrNull(n);
  }
  
  public static int childNum(IRNode n) {
    IRNode parent = parent(n);
    if (parent == null) return -1;
    else return childNumOf(parent,n);
  }

  public static int childNumOf(IRNode p, IRNode n) {
    if ((p == null) || (n == null)) 
      throw new SCDocumentException("Provided node is null !");
    Enumeration children = tree.children(p);
    int i = -1;
    for (int j = 0; i < 0 && children.hasMoreElements(); j++) {
      if (n.identity().equals(((IRNode) children.nextElement()).identity()))
        i = j;
    }
    if (i < 0)
      throw new SCDocumentException("Both of nodes are not in parent relationship");
    return i;
  }

  /** index of the first child with given element name
   *
   * @return  index of the first child with given element name, returns
   * -1 if not found
   */
  public static int firstChildIndex(IRNode n, String name) {
    if ( name != null) {
      int num = numChildren(n);
      for (int i=0; i<num; i++)
        if (elementName(ithChild(n,i)).equalsIgnoreCase(name))
          return i;
    }
    return -1;
  }
  
  /** to get the depth of the sub-tree having this node as root.
   *  Sub-tree at a leaf node (node without any children) has depth 0.
   *
   * @return depth of the sub-tree
   * @see    #isDeep(int)
   */
  public static int depth(IRNode n) {
    int num = numChildren(n);

    int d = -1;
    for (int i=0; i<num; i++) {
      int tmp = depth(ithChild(n,i));
      if (tmp>d)
        d = tmp;
    }
    return d+1;
  }
  
  // EDITABLE, DELETABLE, ...
    
  public static boolean isDeletable(IRNode n) {
    return false;    
  }
  
  
  public static boolean isEditable(IRNode n) {
    if (isRawTextNode(n) == true) return true;
    else return false;
  }
  
  
  public static boolean isDocumentable(IRNode n) {
    return false;
  }
  
  
  public static boolean isHyperlinkable(IRNode n) {
    if (isRawTextNode(n) == false) return true;
    else return false;
  }
  
  
  public static boolean isInsertable(IRNode n) {
    return false;
  }
  
  
  // ATTRIBUTE MANIPULATION
    
  /** Get attribute value for a node */
  public static String getAttributeValue(IRNode n, String attrName) {
    Operator op = tree.getOperator(n);
    SlotInfo thisAttr = op.getAttribute(attrName);
    if (thisAttr != null) {
      if (n.valueExists(thisAttr))
        return thisAttr.type().toString(n.getSlotValue(thisAttr));
      else return null;
    } else {
      // throw new SCDocumentNodeAttributeUnknownException(attrName);
      return null;
    }
  }
  
  /** Set attribute value for a node */ 
  public static void setAttributeValue(IRNode n, String attrName, String attrVal) {
    SlotInfo thisAttr = tree.getOperator(n).getAttribute(attrName);
    if (thisAttr != null) {
      n.setSlotValue(thisAttr, thisAttr.type().fromString(attrVal));
    } else {
      throw new SCDocumentNodeAttributeUnknownException(attrName);
    }
  }
  
  public static Enumeration attributeEnumeration(IRNode n) {
    return new AttributeEnumeration(n);
  }

  // Special attributes : target,anchor, text
  // TARGET
  public static String getTarget(IRNode n) {
    String val = null;
    try {
      val = getAttributeValue(n,"target");
    } catch (SCDocumentNodeAttributeUnknownException e) {
    } catch (fluid.ir.SlotUndefinedException e) {
      return null;
    }
    return val;
  }
  
  public static void setTarget(IRNode n, String t) {
    if (t != null) setAttributeValue(n,"target",t);
    else setAttributeValue(n,"target","");
  }
  
  // ANCHOR
  public static String getAnchor(IRNode n) {
    String val = null;
    try {
      val = getAttributeValue(n,"anchor");
    } catch (SCDocumentNodeAttributeUnknownException e) {
    } catch (fluid.ir.SlotUndefinedException e) {
      return null;
    }
    return val;
  }
  
  public static void setAnchor(IRNode n, String t) {
    if (t != null) setAttributeValue(n,"anchor",t);
    else setAttributeValue(n,"anchor","");
  }
  
  // TEXT
  public static String getText(IRNode n) {
    String val = null;
    try {
      val = getAttributeValue(n, NullDTD.prototype.getRawTextOperatorAttrName());
    } catch (SCDocumentNodeAttributeUnknownException e) {
    } catch (fluid.ir.SlotUndefinedException e) {
      return null;
    }
    return val;
  }

  public static void setText(IRNode n, String t) {
    if (t != null) setAttributeValue(n,NullDTD.prototype.getRawTextOperatorAttrName(),t);
    else setAttributeValue(n,NullDTD.prototype.getRawTextOperatorAttrName(),"");
  }

  // Attribute Enumeration class  
  private static class AttributeEnumeration implements Enumeration {
    private Iterator itr;
    private Pair nextAttribute;
    private IRNode irNode;

    public AttributeEnumeration(IRNode n) {
      irNode = n;
      itr = tree.getOperator(n).getAttributes().iterator();
      nextAttribute = null;
    }

    public boolean hasMoreElements() {
      if (nextAttribute != null)
        return true;

      SlotInfo nextAttr;
      String   attrVal;

      while (itr.hasNext()) {
        nextAttr = (SlotInfo) itr.next();
        if (irNode.valueExists(nextAttr)) {
          attrVal = nextAttr.type().toString(irNode.getSlotValue(nextAttr));
          nextAttribute = new Pair(nextAttr.name(), attrVal);
          return true;
        }
      }

      return false;
    }
    
    public Object nextElement() {
      if (!hasMoreElements())
        throw new SCDocumentException("No more attributes");

      Pair retVal = nextAttribute;
      nextAttribute = null;
      return retVal;
    }
  }; /* end of AttributeEnumeration class */

  // TREE MANIPULATION (non-static methods)

  public IRNode createNode() {
    IRNode nd = new PlainIRNode(region);
    tree.initNode(nd,~0);
    tree.removeSubtree(nd);
    return nd;
  }
  
  public IRNode createNode(Operator o) {
    PlainIRNode.setCurrentRegion(region);
    IRNode nd = o.createNode(tree);
    tree.initNode(nd,o);
    tree.removeSubtree(nd);
    return nd;
  }
  
  public void insertChild(IRNode n, IRNode c, int pos) {
    if (getDocument(c) != this)
      throw new SCDocumentException("The given node is not in the same document");
    if (parent(c) != null)
      throw new SCDocumentException("The given node has a parent");
    if (pos < 0 || pos > numChildren(n))
      throw new SCDocumentException("Index is out of bound");
    if (pos == 0) {
      tree.insertChild(n,c);
    } else {
      IRNode ithChild = tree.getChild(n,tree.childLocation(n, pos - 1));
      tree.insertChildAfter(n,c,ithChild);
    }
  }
  
  public IRNode deleteChild(IRNode n, int pos) {
    if (pos <0 || pos >= numChildren(n))
      throw new SCDocumentException("Index is out of bound");
    IRNode child = tree.getChild(n,pos);
    tree.removeChild(n,child);
    return child;
  }
  
  public IRNode replaceChild(IRNode n, IRNode old, IRNode[] newNodes) {
    if (parent(old) != n)
      throw new SCDocumentException("The given node has different parent");
    if (newNodes == null || newNodes.length <= 0)
      throw new SCDocumentException("new nodes are null");
    
    for (int i = 0; i < newNodes.length; i++) {
      if (newNodes[i] == null)
        throw new SCDocumentException("node at " + i + "'th index is invalid");
    }

    IRNode first = newNodes[0];
    tree.replaceChild(n,old,first);
    for (int i = newNodes.length - 1; i > 0; i--) {
      tree.insertChildAfter(n,newNodes[i],first);
    }
    return old;
  }
  
  // FACTORY
  private static class Factory extends ComponentFactory 
  {
    public static final Factory prototype = new Factory();
    private Factory() {
      super();
    }

    /** Create an SCPlainTextDocument based on input in */
    public Component create(UniqueID id, DataInput in)
    {
      SCPlainTextDocument document = new SCPlainTextDocument(id);
      return document;
    }
  }
}
