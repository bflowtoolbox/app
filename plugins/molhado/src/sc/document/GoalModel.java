package sc.document;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Hashtable;

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

public class GoalModel extends Component {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5108637166200388114L;
	private static final int        magic = 0x474F414C; // 'GOAL'
    protected VersionedRegion       region; // need to be saved
  
    public static final SymmetricEdgeDigraph graph = GoalModelGraphBundle.graph;

	//a map holding the number of operations for reporting
	private static Hashtable<String, Integer> elementCounts = new Hashtable<String, Integer>(); 
	
    public static        Bundle   goalModelGraphBundle = GoalModelGraphBundle.getBundle();
    public static        Bundle   goalModelAttrBundle  = GoalModelAttrBundle.getBundle();

    // Name property
    private static       SlotInfo   gmNameAttr = GoalModelAttrBundle.name_si;
    
    private static		 Hashtable<VersionedRegion,GoalModel> 	region2GoalModelTable = new Hashtable<VersionedRegion,GoalModel>();
      
    private static int   BRANCH_FACTOR = 0;  
  
    private final static int   HARDGOAL      = 0;
    private final static int   SOFTGOAL      = 1;
    private final static int   EDGE          = 2;
	
    public final static int   PLUS          = 0;
    public final static int   PLUSPLUS      = 1;
    public final static int   MINUS         = 2;
    public final static int   MINUSMINUS    = 3;
    public final static int   AND           = 4;
    public final static int   OR            = 5;
  
    // PSEUDO ROOT
    private Slot                 rootSlot;  
    protected static final int ROOTBIT      = 1;
	
    /** Existing one */
    public GoalModel(UniqueID id) {
      super (magic,id);
      region = null;
      
      // PSEUDO ROOT
      // rootSlot = SimpleSlotFactory.prototype.predefinedSlot(null);    
      //?
      rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
    }
    
    /** Totally new one */
    public GoalModel() {
      super(magic,true);
      clearCount(); //initializes Hashtable... make sure it doesn't destroy old versions.
      region = new VersionedRegion();
      region2GoalModelTable.put(region, this);
    }
    
    public void setRoot(IRNode root) {
      // PSEUDO ROOT
      // IRNode root = new PlainIRNode(region);
      // graph.initNode(root,~0,~0);
      // setGMNodeName(root,"GMRoot");
      rootSlot = VersionedSlotFactory.prototype.predefinedSlot(null);
      rootSlot = rootSlot.setValue(root);
    }
    
    public Bundle getGraphBundle () {
    	return goalModelGraphBundle;
    }
    
    public Bundle getAttrBundle() {
    	return goalModelAttrBundle;
    }
    
//  PSEUDO ROOT
    /** Get the pseudo root of this graph */
    public IRNode getRoot() {
      if (rootSlot.isValid()) return (IRNode) rootSlot.getValue();
      else return null;
    }
    
    public ComponentFactory getFactory() {
      return Factory.prototype;
    }
    
    public String getFileName() {
      return this.getID() + ".gmo";
    }
    
    /** Get the region of the nodes */
    public VersionedRegion getRegion() {
    	return region;
    }
    
    /** get the graph */
    public SymmetricEdgeDigraph getGraph() {
    	return graph;
    }
       
  	/**
  	 * Load the versioned region
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
  				region2GoalModelTable.put(region, this);
  			}
  			else
  				if ((region != null) && (!region.isDefined())) region.load(floc); 
  		} catch (IOException e) {
  			System.err.println("Exception occured: " + e);
  			e.printStackTrace();
  		}
  	}

  	/**
  	 * Save the region
  	 */
  	protected void saveRegion(fluid.util.FileLocator floc) {
  		if (region.isStored() == false) {
//  			System.out.println("Saving REGION ...");
  			try {
  				region.store(floc);
//  				region.describe(System.out);				 
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
  			    
//  			System.out.println("Loading delta for GOALMODEL GRAPH STRUCTURE ...");
  			VersionedChunk vc = VersionedChunk.get(region,goalModelGraphBundle);
  			vc.getDelta(era).load(floc);
//  			vc.describe(System.out);

//  			System.out.println("Loading delta for GOALMODEL NODE ATTRIBUTE ...");
  			vc = VersionedChunk.get(region,goalModelAttrBundle);
  			vc.getDelta(era).load(floc);
 // 			vc.describe(System.out);
        
        // /?
        // fillTableInAnEra(era);
    }

    /** saveDelta */
    public void saveDelta(Era era, fluid.util.FileLocator floc)
      throws IOException {
        
        saveRegion(floc);
        
//        System.out.println("Saving chunk delta for goalModelGraphBundle ...");
        VersionedChunk ch = VersionedChunk.get(region,goalModelGraphBundle);
        IRPersistent vcd = ch.getDelta(era);
        vcd.store(floc);
//        vcd.describe(System.out);
        
//        System.out.println("Saving version CHUNK DELTA FOR GM NODE ATTRIBUTE ... ");
        ch = VersionedChunk.get(region,goalModelAttrBundle);
        vcd = ch.getDelta(era);
        vcd.store(floc);
//        vcd.describe(System.out);
    }

    /** Load the snapshot of this component for the given version. */
    public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
      throws IOException {
        loadRegion(floc);
//        System.out.println("Loading snapshot for GM GRAPH STRUCTURE ...");
        VersionedChunk vc = VersionedChunk.get(region,goalModelGraphBundle);
        ((IRPersistent) vc.getSnapshot(v)).load(floc); 
//        vc.describe(System.out);
        
//        System.out.println("Loading snapshot for GM NODE ATTRIBUTE ...");
        vc = VersionedChunk.get(region, goalModelAttrBundle);
        ((IRPersistent) vc.getSnapshot(v)).load(floc);
 //       vc.describe(System.out);
              
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
//        System.out.println("Saving snapshot for goalModelGraphBundle ... ");
        VersionedChunk ch = VersionedChunk.get(region,goalModelGraphBundle);
        IRPersistent vcs = ch.getSnapshot(v);
        vcs.store(floc);
//        vcs.describe(System.out);
        
//        System.out.println("Saving version snapshot FOR GM NODE ATTRIBUTE ... ");
        ch = VersionedChunk.get(region,goalModelAttrBundle);      
        vcs = ch.getSnapshot(v);
        vcs.store(floc);
//        vcs.describe(System.out);
    }
    
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
        region2GoalModelTable.put(region, this);
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
    
    //=======================================================================
    
    /** Return the name of an entity in this GM */
    public String getGMNodeName(IRNode gmnode) {
      if (gmnode.valueExists(gmNameAttr))
        return (String) gmnode.getSlotValue(gmNameAttr);
      else return null;
    }
        
    /** Set name for an entity (node or edge) */
    private void setGMNodeName(IRNode gmnode, String name) {
      gmnode.setSlotValue(gmNameAttr,name);
      //if(!name.equals("virtual")) System.out.println("rename entity: " + name);
    }
    
    /** Set name for a hard goal node */
    public void setHardGoalName(IRNode node, String name) {      
        setGMNodeName(node,"HardGoal_" + name);
        if(!name.equals("virtual")) {  //what about "VIRTUAL_ROOT"
        	elementCounts.put("modify-node", elementCounts.get("modify-node") + 1); 
        }
    }
    
    /** Set name for a soft goal node */
    public void setSoftGoalName(IRNode node, String name) {      
        setGMNodeName(node,"SoftGoal_" + name);
        if(!name.equals("virtual")) {
        	elementCounts.put("modify-node", elementCounts.get("modify-node") + 1);
        }
    }
    
    /** Get the type of a GMNode */
    public int getGMNodeType(IRNode gmnode) {
      String name = getGMNodeName(gmnode);
      if (name == null) return -1;
      if (name.startsWith("HardGoal_")) return HARDGOAL;
      if (name.startsWith("SoftGoal_")) return SOFTGOAL;
      if (name.startsWith("GMEdge_"))   return EDGE;
      return -1;
    }
    
    /** Create a Goal */
    /** Do not check an existing name yet */
    public IRNode createAGoal(String name, boolean hardgoal) {
      if (region == null) return null;
      // Create an IRNode within the "region"
      PlainIRNode node = new PlainIRNode(region);
      // Set the name
      if (hardgoal) 
    	  setHardGoalName(node, name);
      else 
    	  setSoftGoalName(node,name);
      // Init this irnode within the graph
      graph.initNode(node,~BRANCH_FACTOR,~BRANCH_FACTOR);
      elementCounts.put("add-node", elementCounts.get("add-node") + 1);
      return node;
    }
    
    /** Create edges in this graph */
    /** @name: either AND, OR, +, -, ++, -- */
    public IRNode createEdge(String name) {
      if (region == null) return null;
      // Create an IRNode for within "region"
      PlainIRNode edgenode = new PlainIRNode(region);
      // Set the name of the edge
      setGMNodeName(edgenode,"GMEdge_" + name);
      // Init the edge in the graph
      graph.initEdge(edgenode);
      if(!name.equals("virtual")) elementCounts.put("add-edge", elementCounts.get("add-edge") + 1); //vuirtual?
      return edgenode;
    }
    
    /** Change the type of an edge */
    public void setEdgeType(IRNode edgenode, int type) {
    	String name;
    	switch (type) {
    		case AND:  name = "GMEdge_AND"; break;
    		case OR:   name = "GMEdge_OR";  break;
    		case PLUS: name = "GMEdge_+"; break;
    		case MINUS: name = "GMEdge_-"; break;
    		case PLUSPLUS: name = "GMEdge_++"; break;
    		case MINUSMINUS: name = "GMEdge_--"; break;
    		default: return;
    	}
    	setGMNodeName(edgenode,name);
    	elementCounts.put("modify-edge", elementCounts.get("modify-edge") + 1);
    }
    
    /** Connect from n1 to n2 */ 
    public void connect(IRNode n1, IRNode n2, IRNode edgenode) {
      graph.setSource(edgenode,n1);
      graph.setSink(edgenode,n2);
      elementCounts.put("modify-edge", elementCounts.get("modify-edge") + 1); //increment
    }
    
    /** Delete an edge */
    public boolean deleteAnEdge(IRNode edgenode) {
        if (edgenode == null) return false;
        graph.setSource(edgenode, null);
        graph.setSink(edgenode, null);
        elementCounts.put("delete-edge", elementCounts.get("delete-edge") + 1); //increment
        return true;
    }
    
    /** Delete the specified node */
    /** Remove a goal and all of its connecting edges */
    public boolean deleteAGoal(IRNode node) {
      if (node == null) return false;
      int i = graph.numChildren(node);
      int j = graph.numParents(node) ;
      if (i>1) i--; if(j>1) j--; //don't double count 'virtual' edges
      elementCounts.put("delete-edge", elementCounts.get("delete-edge") + i + j); //increment
      elementCounts.put("delete-node", elementCounts.get("delete-node") + 1); //increment
      graph.removeNode(node);
      return true;
    }
    
    // Traversal functions through the graph
    public void printChildrenName(IRNode node) {    	
    	int numChildren = graph.numChildren(node);
        for (int i = 0; i < numChildren; i++) {   	
          IRNode edgenode = graph.getChildEdge(node, i);
          IRNode childnode = graph.getChild(node,i);
          String nodename = getGMNodeName(childnode);
          String edgetype = getGMNodeName(edgenode);
          System.out.println("Child number " + i + " is " + nodename + "; connected by " + edgetype);
        }
    }
    
    public void traverseGM (IRNode node, IRNode edgenode) {
    	if (node == null) return;
    	String nodename = getGMNodeName(node);
    	if (edgenode == null) {
    		System.out.println(nodename); 
    	} else {
    		//String edgetype = getGMNodeName(edgenode);
    		//System.out.println(nodename + " is connected by " + edgetype);
    	}    	
    	int numChildren = graph.numChildren(node);
        for (int i = 0; i < numChildren; i++) {   	
        	IRNode childedgenode = graph.getChildEdge(node, i);
            IRNode childnode = graph.getChild(node,i);
            traverseGM(childnode,childedgenode);
        }
    }
    
    /**
     * @author nernst
     * Clear the diff statistics Hashtable
     */
    public static void clearCount() {
    	elementCounts.put("delete-edge", new Integer(0));
    	elementCounts.put("delete-node", new Integer(0));
    	elementCounts.put("add-node", new Integer(0));
    	elementCounts.put("add-edge", new Integer(0));
    	elementCounts.put("modify-node", new Integer(0));
    	elementCounts.put("modify-edge", new Integer(0));
    }
    
    /**
     * @author nernst
     * @return String - a string repr of the counts of various elements
     */
    public static String getCount() {
    	String s =  elementCounts.toString();
    	return s;
    }
    
    public IRNode getIthChild(IRNode node, int i) {
    	if (node == null) return null;
    	else {
    		IRNode c = graph.getChild(node, i);
    		return c;
    	}
    }
    
    // Import (Parser) /Export functions?
    
    // FACTORY
	private static class Factory extends ComponentFactory {
		
		 public static final Factory prototype = new Factory();
		 private Factory() {
			 super();
		 }
	
		 /** Create Goal Model instance from the input */
		 public Component create(UniqueID id, DataInput in) {
			 GoalModel goalmodel = new GoalModel(id);
			 return goalmodel;
		 }
	}
}
