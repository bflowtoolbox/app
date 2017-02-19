/* $Header: /usr/local/refactoring/molhadoRef/src/sc/document/SCXmlDocument.java,v 1.1 2006/03/21 23:20:57 dig Exp $ */

package sc.document;

import java.io.DataInput;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;

import sc.xml.IRDTD;
import sc.xml.NullDTD;
import sc.xml.XMLParser;
import sc.xml.XMLParserException;
import fluid.ir.Bundle;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotInfo;
import fluid.parse.ParseException;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;
import fluid.util.Pair;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
import fluid.version.VersionedChunk;

/** SC XML Document
 * Author : Tien N. Nguyen
 */

public class SCXmlDocument extends SCDocument {

  
  private static final int           magic = 0x586D6C00; // 'XML\0'
  
  private IRDTD                        dtd;
  private XMLParser                    parser;

  //?
  /** to provide tree manipulation operations */
  public static final SyntaxTree tree = DocTreeBundle.tree;

  /** Tree structure bundle containing (tree, treechanged) */
  public static final Bundle          docTreeBundle = DocTreeBundle.getBundle();
  
  /** xmlAttrBundle is for attribute of XML tags in all XML documents
   * Temporarily shared betweem all XML documents due to the use of NullDTD
   */
  private static Bundle               xmlAttrBundle = XmlAttrBundle.getBundle();

  
  // Constructors
  /**
   * Temporarily do NOT support any other DTD than NullDTD
   * d must be a NullDTD.prototype
   */
  // A new document
  public SCXmlDocument(IRDTD d) {
    super(magic);
    if (d == null) d = NullDTD.prototype;
    else dtd = d;
  }
  
  // Existing one
  protected SCXmlDocument(UniqueID id) {
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
  
  /** PARSE the document from given Reader
   * @param is input stream
   */
  public void importDocument(Reader r) throws SCDocumentParseException {    
    
    parser = new XMLParser(new StringReader(new String("")), this, dtd);
    parser.ReInit(r);
    IRNode[] irNodes;

    try {
      irNodes = parser.parseNodes(null);
    } catch (IOException e) {
      throw new SCDocumentParseException("IO Error: " + e.getMessage());
    } catch (XMLParserException e) {
      throw new SCDocumentParseException(e.getMessage());
    } catch (ParseException e) {
      throw new SCDocumentParseException(e.getMessage());
    }
    
    if (irNodes.length != 1)
      throw new SCDocumentParseException("parse error in the document");
    setRoot(irNodes[0]);
  }
	
  public IRNode[] importNodes(Reader r, Operator o) throws SCDocumentParseException
  {
    parser = new XMLParser(new StringReader(new String("")), this, dtd);
    parser.ReInit(r);
    IRNode[] irNodes;
    try {
      irNodes = parser.parseNodes(o);
    } catch (IOException e) {
      throw new SCDocumentParseException("IO Error: " + e.getMessage());
    } catch (XMLParserException e) {
      throw new SCDocumentParseException(e.getMessage());
    } catch (ParseException e) {
      throw new SCDocumentParseException(e.getMessage());
    }
    return irNodes;
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
    exportNode(w, getRoot(v), v);
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

  /** export the given node in the Writer
   *
   * @param w Writer
   * @throws {@link sc.document.SCDocumentException} if invalid n
   */
  public void exportNode(Writer w, IRNode n, Version v) throws IOException {
    if (n==null)
      throw new SCDocumentException("given node is null.");
      
    Version.saveVersion();
    Version.setVersion(v);
    try {
      XMLParser.dumpXmlTree(w, n, getDTD(), 1);
    } catch (XMLParserException e) {
      Version.restoreVersion();
      throw new SCDocumentException(e.getMessage());
    }
    w.flush();
    Version.restoreVersion();
  }

  // For current version
  public void exportNode(Writer w, IRNode n) throws IOException {
    if (n==null)
      throw new SCDocumentException("given node is null.");
    
    try {
      XMLParser.dumpXmlTree(w, n, getDTD(), 1);
    } catch (XMLParserException e) {
      Version.restoreVersion();
      throw new SCDocumentException(e.getMessage());
    }
    w.flush();
  }

  // NODE MANIPULATION
  
  public static boolean isRawTextNode(IRNode n) {
    String name = tree.getOperator(n).name();
    if (name.equals("RawTextOperator")) return true;
    else return false;
  }
  
  //? What's version
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
  
  public static IRNode getNextPostOrderNode(IRNode n) {
    IRNode next = null;
    if (parent(n) != null) {
      int index = childNum(n);
      try {
        next = ithChild(parent(n), index + 1);
        while (numChildren(next) > 0) next = ithChild(next,0);
      } catch (SCDocumentException e) {
        next = parent(next);
      }
    }
    return next;
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
   * Sub-tree at a leaf node (node without any children) has depth 0.
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
    // return (parent(n) != null);
    if (isRawTextNode(n)) return false;
    else if (parent(n) == null) return false;
    else return true;
  }
  
  public static boolean isEditable(IRNode n) {
    return true;
  }
  
  public static boolean isDocumentable(IRNode n) {
    return false;
  }
  
  public static boolean isHyperlinkable(IRNode n) {
    if (isRawTextNode(n) == false) return true;
    else return false;
  }
  
  public static boolean isInsertable(IRNode n) {
    return (isRawTextNode(n) == false && parent(n) != null);
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

    /** Create an SCXmlDocument based on input in */
    public Component create(UniqueID id, DataInput in)
    {
      SCXmlDocument document = new SCXmlDocument(id);
      return document;
    }
  }
}
