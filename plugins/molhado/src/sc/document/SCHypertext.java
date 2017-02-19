package sc.document;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import fluid.FluidRuntimeException;
import fluid.ir.Bundle;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IRNodeType;
import fluid.ir.IROutput;
import fluid.ir.IRPersistent;
import fluid.ir.PlainIRNode;
import fluid.ir.Slot;
import fluid.ir.SlotInfo;
import fluid.tree.SymmetricEdgeDigraph;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
import fluid.version.VersionedChunk;
import fluid.version.VersionedRegion;
import fluid.version.VersionedSlotFactory;

// import fluid.ir.*;

/**
 * @author Tien
 */
public class SCHypertext extends Component {
  
  private static final int       magic = 0x48595054; // 'HYPT'
  protected VersionedRegion       region; // need to be saved
  
  public static final SymmetricEdgeDigraph graph = HypertextGraphBundle.graph;

	
  public static final  Bundle    hypertextGraphBundle = HypertextGraphBundle.getBundle();
  private static       Bundle     hypertextAttrBundle  = HypertextAttrBundle.getBundle();

  // Name of anchors and links
  private static       SlotInfo   htNameAttr = HypertextAttrBundle.name_si;
  // Node that an anchor points to
  private static       SlotInfo   htNodeAttr = HypertextAttrBundle.nodeattr_si;
  // Document that anchor points to
  private static       SlotInfo   htDestDocAttr = HypertextAttrBundle.destdoc_si;
  
  private static			Hashtable  	region2HypertextTable = new Hashtable();
  
  // Each hypertext contains a mapping between node -> anchor to facilitate the resuse of anchors
  // public             Hashtable		docNode2AnchorNodeTable = new Hashtable();
  
  private static int BRANCH_FACTOR = 0;  
  
  private static int          ANCHOR       = 0;
  private static int          CAUSAL_LINK  = 1;
  private static int          NON_CAUSAL   = 2;
  private static int          EDGE         = 3;
	private static int          HTROOT       = 4;
  
  // PSEUDO ROOT
  private Slot                 rootSlot;  
  protected static final int ROOTBIT      = 1;
  
  /** Existing one */
  public SCHypertext(UniqueID id) {
    super (magic,id);
    region = null;
    
    // PSEUDO ROOT
    // rootSlot = SimpleSlotFactory.prototype.predefinedSlot(null);    
    //?
    rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
  }
  
  /** Totally new one */
  public SCHypertext() {
    super(magic,true);
    region = new VersionedRegion();
    region2HypertextTable.put(region, this);
    
    // PSEUDO ROOT
    IRNode root = new PlainIRNode(region);
    graph.initNode(root,~0,~0);
    setHTNodeName(root,"SCHTRoot");
    // rootSlot = SimpleSlotFactory.prototype.predefinedSlot(null);
    //?
    rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
    rootSlot = rootSlot.setValue(root);
  }
  
  // PSEUDO ROOT
  /** Get the pseudo root of this HT */
  public IRNode getRoot() {
    if (rootSlot.isValid()) return (IRNode) rootSlot.getValue();
    else return null;
  }
  
  public ComponentFactory getFactory() {
    return Factory.prototype;
  }
  
  public String getFileName() {
    return this.getID()
            + ".hyp";
  }
  
  /** Get the region of the hypertext nodes */
  public VersionedRegion getRegion() {
  	return region;
  }
     
	/**
	 * Load the versioned region of this hypertext
	 */
	protected void loadRegion(fluid.util.FileLocator floc) {
		try {      
			if (region == null) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(floc.openFileRead(this.getID().toString() + ".region")));
				String region_name = br.readLine();
				UniqueID id = UniqueID.parseUniqueID(region_name);
				region = VersionedRegion.loadVersionedRegion(id,floc);
				// Update this table
				region2HypertextTable.put(region, this);
			}
			else
				if ((region != null) && (!region.isDefined())) region.load(floc); 
		} catch (IOException e) {
			System.err.println("Exception occured: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * Save the region of the hypertext
	 */
	protected void saveRegion(fluid.util.FileLocator floc) {
		if (region.isStored() == false) {
//			System.out.println("Saving REGION ...");
			try {
				region.store(floc);
//				region.describe(System.out);				 
				DataOutputStream os = new DataOutputStream(
						floc.openFileWrite(this.getID().toString() + ".region"));
				PrintWriter w = new PrintWriter(os);
				w.write(region.getID().toString());
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
  
  // IO : read/write and import/export
  /** loadDelta */
  public void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
			
			loadRegion(floc);
			    
//			System.out.println("Loading delta for HYPERTEXT GRAPH STRUCTURE ...");
			VersionedChunk vc = VersionedChunk.get(region,hypertextGraphBundle);
			vc.getDelta(era).load(floc);
//			vc.describe(System.out);

//			System.out.println("Loading delta for HYPERTEXT NODE ATTRIBUTE ...");
			vc = VersionedChunk.get(region,hypertextAttrBundle);
			vc.getDelta(era).load(floc);
			vc.describe(System.out);
      
      // /?
      // fillTableInAnEra(era);
  }

  /** saveDelta */
  public void saveDelta(Era era, fluid.util.FileLocator floc)
    throws IOException {
      
      saveRegion(floc);
      
//      System.out.println("Saving chunk delta for hypertextGraphBundle ...");
      VersionedChunk ch = VersionedChunk.get(region,hypertextGraphBundle);
      IRPersistent vcd = ch.getDelta(era);
      vcd.store(floc);
//      vcd.describe(System.out);
      
//      System.out.println("Saving version CHUNK DELTA FOR HYPERTEXT NODE ATTRIBUTE ... ");
      ch = VersionedChunk.get(region,hypertextAttrBundle);
      vcd = ch.getDelta(era);
      vcd.store(floc);
//      vcd.describe(System.out);
  }

  /** Load the snapshot of this component for the given version. */
  public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
      loadRegion(floc);
//      System.out.println("Loading snapshot for HYPERTEXT GRAPH STRUCTURE ...");
      VersionedChunk vc = VersionedChunk.get(region,hypertextGraphBundle);
      ((IRPersistent) vc.getSnapshot(v)).load(floc); 
//      vc.describe(System.out);
      
      System.out.println("Loading snapshot for HYPERTEXT NODE ATTRIBUTE ...");
      vc = VersionedChunk.get(region, hypertextAttrBundle);
      ((IRPersistent) vc.getSnapshot(v)).load(floc);
      vc.describe(System.out);
            
      // /?
      // Version.saveVersion();
      // Version.setVersion(v);
      // fillTable();
      // Version.restoreVersion();
  }
  
  /** Store a snapshot of this component for the given version. */
  public void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
      saveRegion(floc);
//      System.out.println("Saving snapshot for hypertextGraphBundle ... ");
      VersionedChunk ch = VersionedChunk.get(region,hypertextGraphBundle);
      IRPersistent vcs = ch.getSnapshot(v);
      vcs.store(floc);
//      vcs.describe(System.out);
      
//      System.out.println("Saving version snapshot FOR HYPERTEXT NODE ATTRIBUTE ... ");
      ch = VersionedChunk.get(region,hypertextAttrBundle);      
      vcs = ch.getSnapshot(v);
      vcs.store(floc);
//      vcs.describe(System.out);
  }
  
  /** Import/Export facilities from/to XLink database */
  // NOT YET
  
  // Write/ReadContents and write/readChangedContents
  public void writeContents(IROutput out) 
    throws IOException
  {
    super.writeContents(out);
    
    // PSEUDO ROOT
    int flags = 0;
    if (rootSlot.isValid()) flags |= ROOTBIT;
    out.writeByte(flags);
    if ((flags & ROOTBIT) != 0) rootSlot.writeValue(IRNodeType.prototype,out);
    
    // Write the immutable field "region"
    region.writeReference(out);
    
  }
  
  public void readContents(IRInput in) 
    throws IOException
  {
    super.readContents(in);
    
    // PSEUDO ROOT
    int flags = in.readByte();
    if ((flags & ROOTBIT) != 0) {      
       rootSlot = rootSlot.readValue(IRNodeType.prototype,in);
    }
    
    // Read the immutable field "region"
    region = (VersionedRegion) in.readPersistentReference();
    // Not load region yet
    if (region == null)
       throw new FluidRuntimeException("region is null");
    else
    { // Update this table
      region2HypertextTable.put(region, this);
    }
  }
  
  public void writeChangedContents(IROutput out)
    throws IOException
  {
    super.writeChangedContents(out);
    
    // PSEUDO ROOT
    int flags = 0;
    // if (rootSlot.isValid()) flags |= ROOTBIT;
    //?
    if (rootSlot.isChanged()) flags |= ROOTBIT;
    out.writeByte(flags);
    if ((flags & ROOTBIT) != 0) rootSlot.writeValue(IRNodeType.prototype,out);
  }
  
  public void readChangedContents(IRInput in) 
    throws IOException
  {
    super.readChangedContents(in);
    
    // PSEUDO ROOT
    int flags = in.readByte();
    if ((flags & ROOTBIT) != 0) {      
       rootSlot = rootSlot.readValue(IRNodeType.prototype,in);
    }
    
  }

  public boolean isChanged()
  {
    // return super.isChanged();
    //?
    return super.isChanged() || rootSlot.isChanged();
  }

  /* 
  // Update docNode2AnchorNodeTable after loading  
  public void fillTable() {
    Vector anchors = getAnchors();
    int size = anchors.size();
    for (int i = 0; i < size; i++) {
      SCAnchor anchor = (SCAnchor) anchors.get(i);
      IRNode docnode = (IRNode) getDocumentNode(anchor);
      docNode2AnchorNodeTable.put(docnode,anchor.getIRNode());
    }
  }
  
  // May not be needed  
  public void fillTableInAnEra(Era e) {
    Vector anchors = getAnchorsInAnEra(e);
    int size = anchors.size();
    for (int i = 0; i < size; i++) {
      SCAnchor anchor = (SCAnchor) anchors.get(i);
      IRNode docnode = (IRNode) getDocumentNode(anchor);
      docNode2AnchorNodeTable.put(docnode,anchor.getIRNode());
    }
  }  
  */
  
  /** Return an anchor node for a docnode if existing */
  public IRNode docNode2AnchorNode(IRNode docnode) {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);    
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == ANCHOR) {
         // htnode is an anchor node
         if (getDocumentNode(htnode).equals(docnode)) {
            return htnode;
         }
      }
    }
    return null;    
  }
  
  
  
  // ----------------------------
  // ANCHOR, LINK, and HYPERTEXT
  // ----------------------------
  
  /** Return the name of an HT node */
  public String getHTNodeName(IRNode htnode) {
    if (htnode.valueExists(htNameAttr))
      return (String) htnode.getSlotValue(htNameAttr);
    else return null;
  }
  
  /** Set name for an HT node (either an anchor or a link) */
  private void setHTNodeName(IRNode htnode, String name) {
    htnode.setSlotValue(htNameAttr,name);
  }
  
  /** Set name for a link */
  public void setLinkName(SCLink link, String name) {
    if (link instanceof SCCausalLink)
      setHTNodeName(link.getIRNode(),"SCCausalLink_" + name);
    if (link instanceof SCNonCausalLink)
      setHTNodeName(link.getIRNode(),"SCNonCausalLink_" + name);
  }
  /** Set name for an anchor */
  public void setAnchorName(SCAnchor anchor, String name) {
    setHTNodeName(anchor.getIRNode(),"SCAnchor_" + name);
  }
  
  /** Does an HT Node belong to this hypertext */ 
  public boolean hasHTNode(IRNode htnode) {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);    
    for (int i = 0; i < numChildren; i++) {
      IRNode child = graph.getChild(root,i);      
      int nodeType = this.getHTNodeType(child);
      if (nodeType == CAUSAL_LINK || 
          nodeType == NON_CAUSAL  || 
          nodeType == ANCHOR) {
        if (child.equals(htnode)) return true; 
      }
    }
    return false;
    // return region.hasOwner(htnode);
    /*
    VersionedRegion vr = 
      (VersionedRegion) VersionedRegion.getVersionedRegion(htnode);
    if (vr.equals(region)) return true;
    else return false;
    */
  }
  
  /** Get the type (anchor, causal link, non-causal link) of an HTNode */
  public int getHTNodeType(IRNode htnode) {
    String name = getHTNodeName(htnode);
    if (name == null) return -1;
    if (name.startsWith("SCAnchor_")) return ANCHOR;
    if (name.startsWith("SCCausalLink_")) return CAUSAL_LINK;
    if (name.startsWith("SCNonCausalLink_")) return NON_CAUSAL;
    if (name.startsWith("SCEdge_")) return EDGE;
    if (name.startsWith("SCHTRoot")) return HTROOT;
    return -1;
  }
  // Tien
  // ============================================
  // GET ALL LINKS AND ANCHORS IN THIS HYPERTEXT
  // ============================================
	/** Get all of link nodes (IRNodes) contained in 
   *  this hypertext at the current version
	 */
	public Vector getLinkNodes() {
		IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == CAUSAL_LINK || nodeType == NON_CAUSAL) {
        links.addElement(htnode); 
      }
    }
    return links;    
	}

	/** Get all of links contained in this hypertext at 
   *  current version
	 */
	public Vector getLinks() {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == CAUSAL_LINK) {
        SCCausalLink l = new SCCausalLink(htnode,this);
        links.addElement(l); 
      }
      else if (nodeType == NON_CAUSAL) {
        SCNonCausalLink l = new SCNonCausalLink(htnode,this);
        links.addElement(l);
      }
    }
    return links;
	}

	/** Get all of anchor nodes (IRNodes) 
	 *  contained in this hypertext at the current version
	 */
	public Vector getAnchorNodes() {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector anchors = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == ANCHOR) {        
        anchors.addElement(htnode); 
      }
    }
    return anchors;    
	}

	/** Get all of anchors 
	 *  contained in this hypertext at the current version
	 */
	public Vector getAnchors() {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector anchors = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == ANCHOR) {        
        SCAnchor anchor = new SCAnchor(htnode,this);
        anchors.addElement(anchor);
      }
    }
    return anchors;    
	}

	/* Get all of anchors 
	 *  contained in this hypertext at the era e
   *  /? May not be needed
	 
	public Vector getAnchorsInAnEra(Era e) { 
		java.util.Enumeration enum = region.allNodes(e);
		Vector anchors = new Vector();
		while (enum.hasMoreElements()) {
			IRNode htnode = (IRNode) enum.nextElement();
			int nodeType = this.getHTNodeType(htnode);
			if (nodeType == ANCHOR) {
				SCAnchor anchor = new SCAnchor(htnode,this);
				anchors.addElement(anchor); 
			}
		}    
		return anchors;
	}
  */

	// GET all CAUSAL and NONCAUSAL links
	/** Get all of causal link nodes (IRNodes) 
	 *  contained in this hypertext at 
	 *  the current version
	 */
	public Vector getCausalLinkNodes() {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == CAUSAL_LINK) {
        links.addElement(htnode); 
      }
    }
    return links;    
	}

	/** Get all of causal links contained in this hypertext 
	 *  at the current version
	 */
	public Vector getCausalLinks() {
    
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == CAUSAL_LINK) {
        SCCausalLink l = new SCCausalLink(htnode,this);
        links.addElement(l); 
      }      
    }
    return links;    
	}

	/** Get all of non-causal link nodes (IRNodes) 
   *  contained in this hypertext at 
	 *  the current version
	 */
	public Vector getNonCausalLinkNodes() {
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == NON_CAUSAL) {
        links.addElement(htnode); 
      }
    }
    return links;    
	}

	/** Get all of non-causal links contained in this hypertext 
	 *  at the current version
	 */
	public Vector getNonCausalLinks() {
    
    IRNode root = this.getRoot();
    int numChildren = graph.numChildren(root);
    Vector links = new Vector();
    for (int i = 0; i < numChildren; i++) {
      IRNode htnode = graph.getChild(root,i);
      int nodeType = this.getHTNodeType(htnode);
      if (nodeType == NON_CAUSAL) {
        SCNonCausalLink l = new SCNonCausalLink(htnode,this);
        links.addElement(l); 
      }      
    }
    return links;    
	}

	/** Get the number of links contained in this hypertext
	 */
	public int numberOfLinks() {
		Vector links = this.getLinkNodes();
		return links.size();
	}

	/** Get the number of causal links contained in this hypertext
	 */
	public int numberOfCausalLinks(Version v) {
		Vector links = this.getCausalLinkNodes();
		return links.size();
	}

	/** Get the number of non-causal links contained in this hypertext
	 */
	public int numberOfNonCausalLinks(Version v) {
		Vector links = this.getNonCausalLinkNodes();
		return links.size();
	}
	
	/** Returns the number of anchors contained in this hypertext */
	public int numberOfAnchors(Version v) {
		return this.getAnchorNodes().size();
	}
	
  // ========================
  //         ANCHOR 
  // ========================
  /** Create an new Anchor */
  // Should set the name of this anchor to begin with "SCAnchor"
  public SCAnchor createAnchor(SCDocument destdoc, IRNode docnode, String name) {
    if (docNode2AnchorNode(docnode) == null) {
      if (region == null) return null;
      // Create an IRNode within the "region"
      PlainIRNode anchornode = new PlainIRNode(region);
      // Set the name of the anchor, note the type of this HTnode
      setHTNodeName(anchornode,"SCAnchor_" + name);
      // Init this irnode within the graph of this hypertext
      graph.initNode(anchornode,~BRANCH_FACTOR,~BRANCH_FACTOR);
      // Create an anchor to wrap around this irnode in the graph
      SCAnchor anchor = new SCAnchor(anchornode,this);
      // Associate this anchor to the IR node in a document
      setDocumentNode(anchor,docnode);
      
      // NOT NEED: Update the table mapping a docnode (IRnode in a doc) to a IRnode in the graph
      // docNode2AnchorNodeTable.put(docnode,anchornode);
      
      // Associate this anchor to the destination SCDocument
      setDestDocument(anchor,destdoc);
      
      // PSEUDO ROOT
      SCEdge edge = createEdge();
      if (edge == null) return null;
      graph.setSource(edge.getIRNode(),this.getRoot());
      graph.setSink(edge.getIRNode(),anchornode);
      
      return anchor;
    }
    else {
      IRNode n = (IRNode) docNode2AnchorNode(docnode);
      return new SCAnchor(n,this);
    }
  }
  
  // NodeAttr for an anchor
  /** Return the document IR node that an anchor points to */
  public IRNode getDocumentNode(SCAnchor anchor) {
    if (anchor.getIRNode().valueExists(htNodeAttr))
      return (IRNode) anchor.getIRNode().getSlotValue(htNodeAttr);
    else return null;
  }
  
  /** Return the document IR node that an anchor node points to */
  public IRNode getDocumentNode(IRNode anchornode) {
    if (anchornode.valueExists(htNodeAttr))
      return (IRNode) anchornode.getSlotValue(htNodeAttr);
    else return null;
  }
  
  /** Associate an anchor to an IRNode. */
  public void setDocumentNode(SCAnchor anchor, IRNode docnode) {
		anchor.getIRNode().setSlotValue(htNodeAttr,docnode);	
  }
  /** Check if the docnode already has an anchor defined on it */
  public boolean anchorExists(IRNode docnode) {
  	if (docNode2AnchorNode(docnode) != null) return true;
  	else return false;
  }
  
  /** Associate an anchor to a (SCDocument,IRNode) */
  public void setDestDocument(SCAnchor anchor, SCDocument destdoc) {
    anchor.getIRNode().setSlotValue(htDestDocAttr,destdoc);
  }
  
  /** Return the dest doc that anchor node points to */
  public SCDocument getDestDocument(SCAnchor anchor) {
    if (anchor.getIRNode().valueExists(htDestDocAttr))
      return (SCDocument) (anchor.getIRNode().getSlotValue(htDestDocAttr));
    return null;
  }
  
  
  /** Delete the specified anchor in this hypertext
   *  The anchor is removed from any links which contain it    
   */
  public boolean deleteAnchor(SCAnchor anchor) {    
    Vector related_links = getLinksWith(anchor);    
    int num = related_links.size();
    for (int i = 0; i < num; i++) {
      SCLink link = (SCLink) related_links.elementAt(i);
      if (link instanceof SCCausalLink) {
        SCCausalLink causal_link = (SCCausalLink) link;
        if (isSourceOf(anchor,causal_link)) {
          /* I think that we should not check, since it happens when
           * user constructs the hypertext anyway
          if (numberOfSourceAnchors(causal_link) <= 1)
            return false;
          */
        }
        else if (isDestOf(anchor,causal_link)) {
          /*
          if (numberOfDestAnchors(causal_link) <= 1)
            return false;
          */
        }        
      }
      if (link instanceof SCNonCausalLink) {
        // SCNonCausalLink non_causal = (SCNonCausalLink) link;
        /*
        if (numberOfAnchorsInNonCausalLink(non_causal) <= 2)
          return false;
        */
      }
    }  
    // OK to delete it
    // Remove a node and all connecting edges
    // Cut it permanently, if the same docnode asks for an anchor,
    // a new anchor node will be created and added to the root.
    graph.removeNode(anchor.getIRNode());
    
    // /? Add an edge from the root
    // SCEdge edge = createEdge();
    // if (edge == null) return false;
    // graph.setSource(edge.getIRNode(),this.getRoot());
    // graph.setSink(edge.getIRNode(),anchor.getIRNode());
        
    return true;
  }
  
  /** If an anchor is a source of the causal link */
  public boolean isSourceOf(SCAnchor anchor, SCCausalLink link) {
    IRNode anchornode = anchor.getIRNode();
    IRNode linknode = link.getIRNode();    
    int numChildren = graph.numChildren(anchornode);
    for (int i = 0; i < numChildren; i++) {
      IRNode child = graph.getChild(anchornode,i);
      if (child.equals(linknode)) return true;
    }  
    return false;
  }
  
  /** If an anchor is a dest of the causal link */
  public boolean isDestOf(SCAnchor anchor, SCCausalLink link) {
    IRNode anchornode = anchor.getIRNode();
    IRNode linknode = link.getIRNode();    
    int numChildren = graph.numChildren(linknode);
    for (int i = 0; i < numChildren; i++) {
      IRNode child = graph.getChild(linknode,i);
      if (child.equals(anchornode)) return true;
    }  
    return false;
  }
  
  // ===================================
  //                LINK
  // ===================================
  
  /** Create a causal link */
  public SCCausalLink createCausalLink(String name) {
    if (region == null) return null;
    // Create an IRNode within the "region"
    PlainIRNode linknode = new PlainIRNode(region);
    // Set the name of the link, note the type of this HTnode
    setHTNodeName(linknode,"SCCausalLink_" + name);
    // Init the irnode within the graph
    graph.initNode(linknode,~BRANCH_FACTOR,~BRANCH_FACTOR);
    // Create the link to wrap around this linknode
    SCCausalLink link = new SCCausalLink(linknode,this);
    
    // PSEUDO ROOT
    SCEdge edge = createEdge();
    if (edge == null) return null;
    graph.setSource(edge.getIRNode(),this.getRoot());
    graph.setSink(edge.getIRNode(),linknode);
    
    return link;
  }
  
  /** Create a non-causal link */
  public SCNonCausalLink createNonCausalLink(String name) {
    if (region == null) return null;
    // Create an IRNode within the "region"
    PlainIRNode linknode = new PlainIRNode(region);
    // Set the name of the link, note the type of this HTnode
    setHTNodeName(linknode,"SCNonCausalLink_" + name);
    // Init the irnode within the graph
    graph.initNode(linknode,~BRANCH_FACTOR,~BRANCH_FACTOR);
    // Create the link to wrap around this linknode
    SCNonCausalLink link = new SCNonCausalLink(linknode,this);
    
    // PSEUDO ROOT
    SCEdge edge = createEdge();
    if (edge == null) return null;
    graph.setSource(edge.getIRNode(),this.getRoot());
    graph.setSink(edge.getIRNode(),linknode);
    
    return link;
  }
  
  /** Create edges in this graph */
  public SCEdge createEdge() {
    if (region == null) return null;
    // Create an IRNode for within "region"
    PlainIRNode edgenode = new PlainIRNode(region);
    // Set the name of the edge, note the type of this HTnode
    setHTNodeName(edgenode,"SCEdge_");
    // Init the edge in the graph
    graph.initEdge(edgenode);
    // Wrap around
    SCEdge edge = new SCEdge(edgenode,this);
    return edge;
  }
  
  /** Add an anchor to a link */
  public boolean addAnchorToNonCausalLink(SCAnchor anchor,
                                  SCNonCausalLink link) {
    
    if (containsAnchor(link,anchor)) return false;
		SCEdge edge = createEdge();
		if (edge == null) return false;  
    graph.setSource(edge.getIRNode(),anchor.getIRNode());
    graph.setSink(edge.getIRNode(),link.getIRNode());    
    return true;
    // Later: should check some conditions such as cycle.
  }
  
  public boolean addAnchorAsSource(SCAnchor anchor, 
                                  SCCausalLink link) {
    
    if (containsAnchor(link,anchor)) return false;
		SCEdge edge = createEdge();
		if (edge == null) return false;    
    graph.setSource(edge.getIRNode(),anchor.getIRNode());
    graph.setSink(edge.getIRNode(),link.getIRNode());
    return true;
    // Later: should check some conditions such as cycle.
  }
  
  public boolean addAnchorAsDest(SCAnchor anchor,
                                  SCCausalLink link) {
    
    if (containsAnchor(link,anchor)) return false;
		SCEdge edge = createEdge();
		if (edge == null) return false;    
    graph.setSource(edge.getIRNode(),link.getIRNode());
    graph.setSink(edge.getIRNode(),anchor.getIRNode());
    return true;
    // Later: should check some conditions such as cycle. 
  }
  
  /** Get all source anchors of a causal link */
  public Vector getSourceAnchors(SCCausalLink link) {
    Vector anchors = new Vector();
    int numParents = graph.numParents(link.getIRNode());
    for (int i = 0; i < numParents; i++) {
      IRNode sourcenode = graph.getParent(link.getIRNode(),i);
      int nodeType = this.getHTNodeType(sourcenode);
      if (nodeType == ANCHOR) {
        SCAnchor anchor = new SCAnchor(sourcenode,this);
        anchors.addElement(anchor);
      }     
    }
    return anchors;
  }
  
  /** Get all source anchor nodes of a causal link */
  public Vector getSourceAnchorNodes(SCCausalLink link) {
    Vector anchornodes = new Vector();
    int numParents = graph.numParents(link.getIRNode());
    for (int i = 0; i < numParents; i++) {
      IRNode sourcenode = graph.getParent(link.getIRNode(),i);
      int nodeType = this.getHTNodeType(sourcenode);
      if (nodeType == ANCHOR) {
        anchornodes.addElement(sourcenode);
      }
    }
    return anchornodes;
  }

  /** return the number of source anchors of a causal link */
  public int numberOfSourceAnchors(SCCausalLink link) {
    return getSourceAnchorNodes(link).size();
  }
  
  /** Get all dest anchors of a causal link */
  public Vector getDestAnchors(SCCausalLink link) {
    Vector anchors = new Vector();
    int numAnchors = graph.numChildren(link.getIRNode());
    for (int i = 0; i < numAnchors; i++) {    	
      IRNode destnode = graph.getChild(link.getIRNode(),i);
      int nodeType = this.getHTNodeType(destnode);
      if (nodeType == ANCHOR) {
        SCAnchor anchor = new SCAnchor(destnode,this);
        anchors.addElement(anchor);
      }
    }
    return anchors;
  }
 
  /** Get all dest nodes of a causal link */
  public Vector getDestAnchorNodes(SCCausalLink link) {
    Vector anchornodes = new Vector();
    int numAnchors = graph.numChildren(link.getIRNode());
    for (int i = 0; i < numAnchors; i++) {
      IRNode destnode = graph.getChild(link.getIRNode(),i);
      int nodeType = this.getHTNodeType(destnode);
      if (nodeType == ANCHOR) {
        anchornodes.addElement(destnode);
      }     
    }
    return anchornodes;
  }

  /** return the number of dest anchors of a causal link */
  public int numberOfDestAnchors(SCCausalLink link) {
    return getDestAnchorNodes(link).size();
  }
  
	/** Get all anchors of a non-causal link */
	public Vector getAnchorsInNonCausalLink(SCNonCausalLink link) {
		Vector anchors = new Vector();
		int numParents = graph.numParents(link.getIRNode());
		for (int i = 0; i < numParents; i++) {
			IRNode sourcenode = graph.getParent(link.getIRNode(),i);
			int nodeType = this.getHTNodeType(sourcenode);
			if (nodeType == ANCHOR) {
				SCAnchor anchor = new SCAnchor(sourcenode,this);
				anchors.addElement(anchor);
			}
		}
		return anchors;
	}

	/** Get all anchor nodes of a non-causal link */
	public Vector getAnchorNodesInNonCausalLink(SCNonCausalLink link) {
		Vector anchornodes = new Vector();
		int numParents = graph.numParents(link.getIRNode());
		for (int i = 0; i < numParents; i++) {
			IRNode sourcenode = graph.getParent(link.getIRNode(),i);
			int nodeType = this.getHTNodeType(sourcenode);
			if (nodeType == ANCHOR) {
				anchornodes.addElement(sourcenode);
			}
		}
		return anchornodes;
	}

  /** Return the number of anchors in a non-causal link */
  public int numberOfAnchorsInNonCausalLink(SCNonCausalLink link) {
    return getAnchorNodesInNonCausalLink(link).size();
  }
 
  /** Retrieve all anchors contained in the specified link */
  public Vector getAnchorsOfLink(SCLink link) {  	
  	Vector anchors = new Vector();
    if (link instanceof SCNonCausalLink) {
			SCNonCausalLink non_causal = (SCNonCausalLink) link;
  		return getAnchorsInNonCausalLink(non_causal);
  	} else
    if (link instanceof SCCausalLink) {  		
  		SCCausalLink causal_link = (SCCausalLink) link;  		
  		Vector sources = this.getSourceAnchors(causal_link);
  		int num = sources.size();
  		for (int i = 0; i < num; i++) anchors.addElement(sources.elementAt(i));
  		
  		Vector dests = this.getDestAnchors(causal_link);
  		num = dests.size();
  		for (int i = 0; i < num; i++) anchors.addElement(dests.elementAt(i));
  		
  		return anchors;
    }
    else return anchors;
  }
  
  /** Returns the number of anchors contained in the specified link */
  public int numberOfAnchorsInLink(SCLink link) {
  	return getAnchorsOfLink(link).size();
  }
  
  /** return TRUE if the link contains the specified anchor */
  public boolean containsAnchor(SCLink link, SCAnchor anchor) {    
    IRNode anchor_node = anchor.getIRNode();
    if (link instanceof SCCausalLink) {
      SCCausalLink causal_link = (SCCausalLink) link;
      Vector sources = this.getSourceAnchorNodes(causal_link);
      if (sources.contains(anchor_node)) return true;
      Vector dests = this.getDestAnchorNodes(causal_link);
      if (dests.contains(anchor_node)) return true;
    }
    if (link instanceof SCNonCausalLink) {
      SCNonCausalLink non_causal = (SCNonCausalLink) link;
      Vector anchors = this.getAnchorNodesInNonCausalLink(non_causal);
      if (anchors.contains(anchor_node)) return true;      
    }
    return false;
  }
  
  /** Retrieve all of the links which contain the specified anchor */
  public Vector getLinksWith(SCAnchor anchor) {
    Vector links = new Vector();
    IRNode anchor_node = anchor.getIRNode();
    int numParents = graph.numParents(anchor_node);
    for (int i = 0; i < numParents; i++) {
      IRNode parent = graph.getParent(anchor_node,i);
      int nodeType = getHTNodeType(parent);
      if (nodeType == CAUSAL_LINK) {
        SCCausalLink causal_link = new SCCausalLink(parent,this);
        links.addElement(causal_link);
      }
    }
    int numChildren = graph.numChildren(anchor_node);
    for (int i = 0; i < numChildren; i++) {
      IRNode child = graph.getChild(anchor_node,i);
      int nodeType = getHTNodeType(child);
      if (nodeType == CAUSAL_LINK) {
        SCCausalLink causal_link = new SCCausalLink(child,this);
        links.addElement(causal_link);
      }
      if (nodeType == NON_CAUSAL) {
        SCNonCausalLink non_causal = new SCNonCausalLink(child,this);
        links.addElement(non_causal);
      }
    }
    return links;
  }
  
  /** Return the number of links which contain the specified anchor */
  public int numberOfLinksWith(SCAnchor anchor) {
  	return getLinksWith(anchor).size();
  }
  
  /** Removes the specified anchor from the specified link 
   *  and checking degeneration conditions
   */
  public boolean removeAnchorFromLink(SCLink link, SCAnchor anchor) {
    
    if (this.containsAnchor(link,anchor) == false) return false;
    
    IRNode linknode = link.getIRNode();
    IRNode anchornode = anchor.getIRNode();
    
    // CAUSAL LINK
    if (link instanceof SCCausalLink) {
      // Search nodes pointing to the link (source set)
      java.util.Enumeration anchornodes = graph.parents(linknode);
      int i = 0;
      while (anchornodes.hasMoreElements()) {
        IRNode anode = (IRNode) anchornodes.nextElement();        
        if (getHTNodeType(anode) == ANCHOR && anchornode.equals(anode)) {
          /*
          // Do not delete if the source set has 1 element
          if (graph.numParents(linknode) <= (1 + 1)) return false;
          */
          IRNode edge = graph.getParentEdge(linknode,i);
          graph.disconnect(edge);					
          return true;
        }
        i++;
        
      }      
      // Search nodes coming from the link (dest set)     
      anchornodes = graph.children(linknode);
      i = 0;
      while (anchornodes.hasMoreElements()) {
        IRNode anode = (IRNode) anchornodes.nextElement();
        if (anchornode.equals(anode)) {
          /*
          // Do not delete if the dest set has 1 element
          if (graph.numChildren(linknode) <= (1 + 1)) return false;
          */
          IRNode edge = graph.getChildEdge(linknode,i);
          graph.disconnect(edge);        
          return true;
        }
        i++;
      }
    }
    // NON-CAUSAL
    if (link instanceof SCNonCausalLink) {      
      /*
      // Do not delete if the number of anchors is leq than 2
      if (graph.numParents(linknode) <= (2 + 1)) return false;
      */
      // Search nodes pointing to the link
      java.util.Enumeration anchornodes = graph.parents(linknode);
      int i = 0;
      while (anchornodes.hasMoreElements()) {
        IRNode anode = (IRNode) anchornodes.nextElement();        
        if (getHTNodeType(anode) == ANCHOR && anchornode.equals(anode)) {
          IRNode edge = graph.getParentEdge(linknode,i);
          graph.disconnect(edge);
          return true;
        }
        i++;
      }
    }
    return false;
  }

  /** Delete the specified link in this hypertext */
  public boolean deleteLink(SCLink link) {
    IRNode linknode = link.getIRNode();
    // Cut this link node out of the hypertext
    // Cut it out of the root reachability
    graph.removeNode(linknode);
    this.setHTNodeName(linknode,"Deleted");
    return true;
  }
  
  
	//FACTORY
	private static class Factory extends ComponentFactory 
	{
		 public static final Factory prototype = new Factory();
		 private Factory() {
			 super();
		 }
	
		 /** Create an SCHypertext based on input in */
		 public Component create(UniqueID id, DataInput in)
		 {
			 SCHypertext htstructure = new SCHypertext(id);
			 return htstructure;
		 }
	}
}
