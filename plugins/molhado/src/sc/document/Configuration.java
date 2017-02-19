package sc.document;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import fluid.FluidRuntimeException;
import fluid.ir.Bundle;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentReferenceType;
import fluid.ir.IRRegion;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.tree.Tree;
import fluid.util.Base64InputStream;
import fluid.util.Base64OutputStream;
import fluid.util.FileLocator;
import fluid.util.Pair;
import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.OverlappingEraException;
import fluid.version.TreeChanged;
import fluid.version.Version;
import fluid.version.VersionedChunk;
import fluid.version.VersionedRegion;
import fluid.version.VersionedSlotFactory;

public class Configuration {

	// tree of components
	private static Tree componentHierarchy;

	// TC
	private static TreeChanged tc;

	// Root of the componentHierachy tree, root cannot change.
	private IRNode root;

	// component attribute
	private static SlotInfo componentAttr;

	// the bundle of attributes for the component hierarchy
	private static Bundle configBundle;

	/** The current name of a configuration. */
	private String name;

	/** The components, each of which is a node. */
	private VersionedRegion components;

	/** Tables mapping version names to versions and vice versa. */
	private Hashtable version2nameTable = new Hashtable();

	private Hashtable name2versionTable = new Hashtable();

	/**
	 * This table is needed to store the version name mapping as it is read from
	 * an ASCII configuration file.
	 */
	private Hashtable<String,Pair> name2eraoffsetTable = new Hashtable<String, Pair>();

	// Before these two vectors are private
	// But if a new config instance is loaded then
	// the delta and component delta for the same eras
	// could be loaded again. Therefore, I put them static.
	static Vector delta_loaded_eras = new Vector();

	static Vector comp_delta_loaded_eras = new Vector();

	static{
		try {
//			System.out.println("START");
			componentHierarchy = new Tree("Config.components",
					VersionedSlotFactory.prototype);
//			// TC
			tc = new TreeChanged("Config.treeChanged", componentHierarchy);
			componentAttr = VersionedSlotFactory.makeSlotInfo(
					"Config.component.attr",
					IRPersistentReferenceType.prototype);
			configBundle = Bundle.loadBundle(UniqueID.parseUniqueID("config"),
					IRPersistent.fluidFileLocator);
//			System.out.println("END");
		} catch (SlotAlreadyRegisteredException ex) {
			System.out.println("Config attribute names have been registered.");
			System.exit(1);
		} 
		catch (IOException ex) {
			System.out.println(ex.toString());
		} 

	}

	// constructors
	/**
	 * New configuration comp: is root component
	 */
	public Configuration(String name, Component comp) {
		if (comp == null)
			throw new FluidRuntimeException("Null cannot be a root");
		this.name = name;
		components = new VersionedRegion();
		root = newComponent(comp);
	}

	// Existing one
	public Configuration(String name, VersionedRegion vr, IRNode r) {
		if (r == null)
			throw new FluidRuntimeException("Null cannot be a root");
		if (vr == null)
			throw new FluidRuntimeException("Versioned region cannot be null");
		root = r;
		this.name = name;
		components = vr;
	}

	public static void saveAttributes(Bundle b) {
		componentHierarchy.saveAttributes(b);
		// TC
		b.saveAttribute(tc);
		b.saveAttribute(componentAttr);
	}

	// used only to create the initial bundle
	public static void main(String args[]) {
		Bundle b = getBundle();
		if (b != null) {
			b.describe(System.out);
			return;
		}
		b = new Bundle();
		saveAttributes(b);
		try {
			b.store(IRPersistent.fluidFileLocator);
			System.out.println("Saving config.bundles");
		} catch (IOException ex) {
			System.out.println(ex.toString());
			System.out.println("Please press return to try again");
			try {
				System.in.read();
				b.store(IRPersistent.fluidFileLocator);
			} catch (IOException ex2) {
				System.out.println(ex2.toString());
			}
		}
	}

	/**
	 * Create a new component for this configuration.
	 */
	public IRNode newComponent(Component comp) {
		if (components == null)
			return null;
		IRNode n = new PlainIRNode(components);
		componentHierarchy.initNode(n);
		// componentHierarchy.removeSubtree(n);
		setComponent(n, comp);
		return n;
	}

	// accessors and mutators

	/** Return the current name of a configuration. */
	public String getName() {
		return name;
	}

	/** Change the current name of a configuration. */
	public void setName(String s) {
		name = s;
	}

	/** Get the component associated with a node */
	public Component getComponent(IRNode n) {
		if (n.valueExists(componentAttr))
			return (Component) n.getSlotValue(componentAttr);
		else
			return null;
	}

	/** Set component for a node */
	public void setComponent(IRNode n, Component comp) {
		n.setSlotValue(componentAttr, comp);
	}

	/**
	 * Return all components as an enumeration of IR nodes. Return an
	 * enumeration of all nodes in the region from the initial time to the era
	 * of the version given. If the version is not yet in an era, we use the
	 * nodes in it and parent versions back until one *is* in an era.
	 */
	public Enumeration getComponents(Version v) {
		return components.allNodes(v);
	}

	public VersionedRegion getRegion() {
		return components;
	}

	/** Return the config bundle in this configuration. */
	public static Bundle getBundle() {
		return configBundle;
	}

	/** Get the root of componentHierarchy */
	public IRNode getRoot() {
		return root;
	}

	public static Tree getTree() {
		return componentHierarchy;
	}

	// TC
	public static TreeChanged getTC() {
		return tc;
	}

	// MANAGE THE NAME TABLES

	/**
	 * Return the version known by this name in this configuration.
	 * 
	 * @return version associated currently with this name, or null
	 */
	public Version lookupVersion(String name) {
		return (Version) name2versionTable.get(name);
	}

	/**
	 * Return the name this version is currently known by in the configuration.
	 * 
	 * @return string for this version or null
	 */
	public String getVersionName(Version v) {
		return (String) version2nameTable.get(v);
	}

	/**
	 * Get the list of versions' names
	 */
	public Enumeration<String> getAllVersionNames() {
		return name2eraoffsetTable.keys();
	}

	public Hashtable getname2eraoffsetTable() {
		return name2eraoffsetTable;
	}

	public Hashtable getname2versionTable() {
		return name2versionTable;
	}

	// LOAD/SAVE DELTAS and SNAPSHOTS

	/** loadDelta */
	public void loadDelta(Era era, fluid.util.FileLocator floc)
			throws IOException {
		IRPersistent.setTraceIO(true);
		VersionedChunk vc = VersionedChunk.get(components, configBundle);
		vc.getDelta(era).load(floc);
//		System.out.println("Describing the versioned chunk");
//		vc.describe(System.out);
		IRPersistent.setTraceIO(false);
	}

	/*
	 * <DEBUG> ===========================================================
	 * public void loadDeltaDEBUG(Era era, fluid.util.FileLocator floc) throws
	 * IOException {
	 * 
	 * System.out.println("INSIDE: (before loading)"); Enumeration e =
	 * components.allNodes(TestConfig2.v6); while (e.hasMoreElements()) { IRNode
	 * node = (IRNode) e.nextElement();
	 * componentHierarchy.describeNode(node,System.out); } // Look at the
	 * structure of the version "v6" of this project System.out.println("The
	 * structure of v6 (INSIDE: before loading)");
	 * System.out.println("===================================="); PrintWriter w =
	 * new PrintWriter(System.out); TestConfig2.dump(w, this.getRoot(), 3);
	 * w.flush();
	 * 
	 * IRPersistent.setTraceIO(true);
	 * 
	 * VersionedChunk vc = VersionedChunk.get(components,configBundle);
	 * vc.getDelta(era).load(floc);
	 * 
	 * IRPersistent.setTraceIO(false); // Look at the structure of the version
	 * "v6" of this project System.out.println("The structure of v6 (INSIDE:
	 * after loading)");
	 * System.out.println("===================================="); w = new
	 * PrintWriter(System.out); TestConfig2.dump(w, this.getRoot(), 3);
	 * w.flush();
	 * 
	 * 
	 * System.out.println("INSIDE: (after loading)"); e =
	 * components.allNodes(TestConfig2.v6); while (e.hasMoreElements()) { IRNode
	 * node = (IRNode) e.nextElement();
	 * componentHierarchy.describeNode(node,System.out); } } // </DEBUG>
	 * ===========================================================
	 */

	/** Load deltas for components */
	public void loadComponentDelta(Era era, int offset, FileLocator floc)
			throws IOException {
		Version.saveVersion();
		Version.setVersion(era.getVersion(offset));

		// Enumeration enum = getComponents(era.getVersion(1));
		Enumeration en = components.allNodes(era.getVersion(offset));
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			if (node.valueExists(componentAttr)) {
				Component comp = (Component) node.getSlotValue(componentAttr);
				comp.getDelta(era).load(floc);
				comp.loadDelta(era, floc);
//				System.out.println("Loaded delta for component "
//						+ comp.getName(era.getVersion(offset)));
			}
			// else throw new IOException("Error in loading this
			// configuration");
		}
		Version.restoreVersion();
		// ?
		// if (this.comp_delta_loaded_eras.contains(era) == false)
		// this.comp_delta_loaded_eras.addElement(era);
	}

	/** saveDelta */
	public void saveDelta(Era era, fluid.util.FileLocator floc)
			throws IOException {
		IRPersistent.setTraceIO(true);
		saveRegion(floc);
		VersionedChunk ch = VersionedChunk.get(components, configBundle);
		IRPersistent vcd = ch.getDelta(era);
		vcd.store(floc);
//		vcd.describe(System.out);
		IRPersistent.setTraceIO(false);
		// ?
		// if (this.delta_loaded_eras.contains(era) == false)
		// this.delta_loaded_eras.addElement(era);
	}

	/** Save deltas for components in a particular version */
	public void saveComponentDelta(Version v, Era era, FileLocator floc)
			throws IOException {
		// save deltas for components
		// Enumeration enum = getComponents(era.getVersion(1));
		// IRPersistent.setTraceIO(true);

		Enumeration en = getComponents(v);
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			if (node.valueExists(componentAttr)) {
				Component comp = (Component) node.getSlotValue(componentAttr);
//				System.out.println("SAVING DELTA for \"" + comp.getName(v)
//						+ "\" ... (Slots)");
				comp.getDelta(era).store(floc);
//				System.out.println("SAVING DELTA for \"" + comp.getName(v)
//						+ "\" ...(Attrs)");
				comp.saveDelta(era, floc);
			}
			// else throw new IOException("Error in storing this
			// configuration");
		}
		// ?
		// if (this.comp_delta_loaded_eras.contains(era) == false)
		// this.comp_delta_loaded_eras.addElement(era);

	}

	/** Save deltas for components in an era , not a particular version */
	public void saveComponentDeltaForEra(Era era, FileLocator floc)
			throws IOException {
		// save deltas for components
		// Enumeration enum = getComponents(era.getVersion(1));
		Enumeration en = components.allNodes(era);
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			if (node.valueExists(componentAttr)) {
				Component comp = (Component) node.getSlotValue(componentAttr);
				//System.out.println("SAVING DELTA for \"" + comp.getName(era.getRoot()) + "\" ... (Slots)");
				comp.getDelta(era).store(floc);
				//System.out.println("SAVING DELTA for \"" + comp.getName(era.getRoot()) + "\" ...(Attrs)");
				comp.saveDelta(era, floc);
			} else
				throw new IOException("Error in storing this configuration");
		}
		// ?
		// if (this.comp_delta_loaded_eras.contains(era) == false)
		// this.comp_delta_loaded_eras.addElement(era);
	}

	/** Load the snapshot of this component for the given version. */
	public void loadSnapshot(Version v, fluid.util.FileLocator floc)
			throws IOException {
		// loadRegion(floc); OUTSIDE
		// VersionedChunk.ensureLoaded();
//		System.out.println("Loading snapshot  ...");
		VersionedChunk vc = VersionedChunk.get(components, configBundle);
		((IRPersistent) vc.getSnapshot(v)).load(floc);
//		vc.describe(System.out);
		Enumeration en = getComponents(v);
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			if (node.valueExists(componentAttr)) {
				Component comp = (Component) node.getSlotValue(componentAttr);
				comp.getSnapshot(v).load(floc);
				comp.loadSnapshot(v, floc);
			} else
				throw new FluidRuntimeException(
						"Error in loading this configuration");
		}
	}

	/** Store a snapshot of this component for the given version. */
	public void saveSnapshot(Version v, fluid.util.FileLocator floc)
			throws IOException {
		saveRegion(floc);
		// Save the deltas for docTreeBundle
//		System.out.println("Saving snapshot  ... ");
		VersionedChunk ch = VersionedChunk.get(components, configBundle);
		IRPersistent vcs = ch.getSnapshot(v);
		vcs.store(floc);
//		vcs.describe(System.out);
		Enumeration en = getComponents(v);
		while (en.hasMoreElements()) {
			IRNode node = (IRNode) en.nextElement();
			if (node.valueExists(componentAttr)) {
				Component comp = (Component) node.getSlotValue(componentAttr);
				comp.saveSnapshot(v, floc);
				comp.getSnapshot(v).store(floc);
			} else
				throw new FluidRuntimeException(
						"Error in storing this configuration");
		}
	}

	// ASCII FILE READ/WRITE

	public void storeASCII(Writer w) throws IOException, FluidRuntimeException {
		// 1. write the configuration's name
		w.write(name + "\n");
		// 2. write versioned region's name
		w.write(components.getID().toString() + "\n");
		// 3. Write the reference to the region that the root node belongs to
		DataOutputStream os = new DataOutputStream(new Base64OutputStream(w));
		IRRegion.getOwner(root).writeReference(os);
		os.flush();
		// 4. Write the index of the root node within the region
		w.write(IRRegion.getOwnerIndex(root) + "\n");
		// 5. Write version name mapping infos
		Enumeration en = name2eraoffsetTable.keys();
		while (en.hasMoreElements()) {
			String vname = (String) en.nextElement();
			w.write(vname + ", ");
			Pair pair = (Pair) name2eraoffsetTable.get(vname);
			String eraname = (String) pair.first();
			String offset = (String) pair.second();
			w.write(eraname + ", ");
			w.write(offset + "\n");
		}
		w.flush();
	}

	public static Configuration loadASCII(Reader r, FileLocator floc)
			throws IOException {
		// Parse the ASCII file to get name, region's name
		// and mapping tables
		BufferedReader br = new BufferedReader(r);
		// 1. read the configuration's name
		String config_name = br.readLine();
		// 2. read versioned region's name, then load versioned region
		String region_name = br.readLine();
		VersionedRegion vr = loadRegionFromName(region_name, floc);
//		if (vr != null)
//			vr.describe(System.out);
//		else
//			throw new IOException("Versioned region is null !");
		// 3. read in the region
		StringReader sr = new StringReader(br.readLine());
		Base64InputStream base64 = new Base64InputStream(sr);
		DataInputStream is = new DataInputStream(base64);
		IRRegion ir_region = (IRRegion) IRPersistent.readReference(is);
		// 4. read the index of the root
		int index = Integer.parseInt(br.readLine());
		IRNode root_node = ir_region.getNode(index);
		Configuration config = new Configuration(config_name, vr, root_node);
		// 5. read the mapping info
		String s = br.readLine();
		while (s != null) {
			StringTokenizer stokenizer = new StringTokenizer(s, ",");
			String vname = stokenizer.nextToken().trim();
			String eraname = stokenizer.nextToken().trim();
			String offset = stokenizer.nextToken().trim();
			config.getname2eraoffsetTable().put(vname,
					new Pair(eraname, offset));
			// ?
			Era era = Configuration.loadEraFromName(eraname, floc);
			Version v = era.getVersion(Integer.parseInt(offset));
			config.assignVersionName(v, vname);
			s = br.readLine();
		}
		return config;
	}

	/**
	 * Save the region of the document
	 */
	public void saveRegion(FileLocator floc) {
		if (!components.isStored()) {
//			System.out.println("Saving REGION ...");
			try {
				components.store(floc);
//				components.describe(System.out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Load a region from a name */
	public static VersionedRegion loadRegionFromName(String rname,
			FileLocator floc) {
		try {
			// VersionedRegion.ensureLoaded();
			UniqueID id = UniqueID.parseUniqueID(rname);
			VersionedRegion vr = VersionedRegion.loadVersionedRegion(id, floc);
			return vr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// VERSIONING OPERATIONS

	/** Return a list of unassigned versions */
	public String[] unAssignedVersions() {
		Enumeration en = version2nameTable.keys();
		ArrayList result = new ArrayList();
		while (en.hasMoreElements()) {
			Version v = (Version) en.nextElement();
			if (isInASavedEra(v) == false)
				result.add((String) version2nameTable.get(v));
		}
		return (String[]) result.toArray();
	}

	/**
	 * Associate this version with the given name. Breaks any other previous
	 * association for this name. (But a version may keep former names.)
	 */
	public void assignVersionName(Version version, String name) {
		Version oldVersion = lookupVersion(name);
		if (oldVersion != null)
			version2nameTable.remove(oldVersion);

		name2versionTable.put(name, version);
		version2nameTable.put(version, name);
	}

	public boolean isInASavedEra(Version v) {
		Era e = v.getEra();
		if ((e != null) && e.isStored())
			return true;
		else
			return false;
	}

	/** Update the name2eraoffsetTable after saveVersion() */
	// Fixed: a version that is intermediate should
	// not be in name2eraoffsetTable
	private void updatename2eraoffsetTable(Era e, Version r, Version v) {
		Version t = v;
		String eraname = e.getID().toString();
		while (t != r) {
			String vname = (String) version2nameTable.get(t);
			// Old: if (vname == null) vname = v.toString();
			if (vname != null) // new
				name2eraoffsetTable.put(vname, new Pair(eraname, new Integer(t
						.getEraOffset()).toString()));
			t = t.parent();
		}
	}

	/**
	 * Save an era and its ancestors if they have not been saved (Recursive)
	 */
	private void saveEraAndDelta(Era era, FileLocator floc) throws IOException {
		boolean isParentEraSaved = false;
		Version r = era.getRoot();
		if (r == Version.getInitialVersion())
			isParentEraSaved = true;
		Era parent_era = r.getEra();
		if (parent_era != null && parent_era.isStored())
			isParentEraSaved = true;

		if (!isParentEraSaved)
			saveEraAndDelta(parent_era, floc);
		try {
			era.complete();
			saveDelta(era, floc);
			saveComponentDeltaForEra(era, floc);
			era.store(floc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save a version of this configuration
	 */
	public void saveVersionByDelta(Version v, FileLocator floc)
			throws IOException {

		// if the version v is already saved in an era, done.
		if (isInASavedEra(v))
			return;
		// if it is in an era but not saved, then save the era and make sure all
		// ancestor
		// eras are saved.
		Era era = v.getEra();
		if (era != null && (!era.isStored())) {
			saveEraAndDelta(era, floc);
			return;
		}
		// Here, era is null, create a new era
		Version r = v.parent();
		// find the root of this editing session
		Version alpha = Version.getInitialVersion();
		while (r != alpha) {
//			System.out.println("Examine " + version2nameTable.get(r));
			if (r.getEra() != null)
				break;
			r = r.parent();
		}

		// if r.getEra() has not been saved
		if (r != alpha && (!r.getEra().isStored()))
			saveEraAndDelta(r.getEra(), floc);

		// create and save the era
		try {

			Era e = new Era(r, new Version[] { v });
			// e.complete();

			// Save delta in this era
//			System.out.println("SAVING the DELTA for the new ERA");
			saveDelta(e, floc);
			saveComponentDelta(v, e, floc); // ? or
			// saveComponentDeltaForEra(e,floc);

//			System.out.println("Saving ERA ...");
			e.store(floc);
//			e.describe(System.out);

			// update name2eraoffsetTable
			updatename2eraoffsetTable(e, r, v);
		} catch (OverlappingEraException ex) {
			System.err.println("Overlapping eras!");
			ex.printStackTrace();
		}
	}

	/**
	 * Save an era and its ancestors if they have not been saved using snapshot
	 * (Recursive)
	 */
	private void saveEraAndSnapshot(Era era, FileLocator floc)
			throws IOException {
		boolean isParentEraSaved = false;
		Version r = era.getRoot();
		if (r == Version.getInitialVersion())
			isParentEraSaved = true;
		if (r!=null) {
			Era parent_era = r.getEra();
			if (parent_era != null && parent_era.isStored())
				isParentEraSaved = true;
	
			if (!isParentEraSaved)
				saveEraAndSnapshot(parent_era, floc);
		}
		try {
			era.complete();
			// Save snapshots for all versions in this era
			for (int i = 0; i < era.maxVersionOffset(); i++)
				saveSnapshot(era.getVersion(i), floc);
			era.store(floc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save a version of this configuration using snapshot
	 */
	public void saveVersionBySnapshot(Version v, FileLocator floc)
			throws IOException {
		// if the version v is already saved in an era, done.
		if (isInASavedEra(v))
			return;
		// if it is in an era but not saved, then save it and make sure all
		// ancestor
		// eras are saved.
		Era era = v.getEra();
		if (era != null && (!era.isStored())) {
			saveEraAndSnapshot(era, floc);
			return;
		}
		// Here, era is null, create a new era
		Version r = v.parent();
		// find the root of this editing session
		Version alpha = Version.getInitialVersion();
		while (r != alpha) {
			if (r.getEra() != null)
				break;
			r = r.parent();
		}
		// if r.getEra() has not been saved
		if (r != alpha && (!r.getEra().isStored()))
			saveEraAndSnapshot(r.getEra(), floc);

		// create and save the era
		try {
			Era e = new Era(r, new Version[] { v });
			e.complete();
			// Save all the snapshots of this era
			for (int i = 0; i < e.maxVersionOffset(); i++)
				saveSnapshot(e.getVersion(i), floc);
			e.store(floc);
			// update name2eraoffsetTable
			updatename2eraoffsetTable(e, r, v);
		} catch (OverlappingEraException ex) {
			System.out.println("Overlapping eras!");
			ex.printStackTrace();
		}
	}

	/**
	 * Load an era
	 */
	public static Era loadEraFromName(String era_name, FileLocator floc)
			throws IOException {
		// Era.ensureLoaded();
		UniqueID id = UniqueID.parseUniqueID(era_name);
		Era era = Era.loadEra(id, floc);
//		era.describe(System.out);
		return era;
	}

	/**
	 * Load versioned chunk delta for this config in this era and all above eras
	 */
	private void loadDeltaForEras(Era era, FileLocator floc) throws IOException {
		if (era == Era.getInitialEra())
			return;
		else {
			Era parent_era = era.getParentEra();
			loadDeltaForEras(parent_era, floc);
		}
		if (delta_loaded_eras.contains(era) == false) {
//			System.out.println("Loading versionedchunk delta for the era "
//					+ era.getID());
			loadDelta(era, floc);
			delta_loaded_eras.addElement(era);
		}
	}

	/*
	 * <DEBUG>
	 * ==================================================================
	 * private void loadDeltaForErasDEBUG(Era era, FileLocator floc) throws
	 * IOException { if (era == Era.getInitialEra()) return; else { // Era
	 * parent_era = era.getParentEra(); //
	 * loadDeltaForErasDEBUG(parent_era,floc); } System.out.println("Loading
	 * versionedchunk delta for the era " + era.getID());
	 * loadDeltaDEBUG(era,floc); } // </DEBUG>
	 * ==================================================================
	 */

	/**
	 * Load deltas for components of this config in this era and all above eras
	 */
	private void loadComponentDeltaForEras(Era era, FileLocator floc)
			throws IOException {
		if (era == Era.getInitialEra())
			return;
		else {
			Era parent_era = era.getParentEra();
			loadComponentDeltaForEras(parent_era, floc);
		}
		if (comp_delta_loaded_eras.contains(era) == false) {
			//System.out.println("Loading components' deltas for the era " + era.getID());
			loadComponentDelta(era, era.maxVersionOffset(), floc);
			comp_delta_loaded_eras.addElement(era);
		}
	}

	/**
	 * Load a version of this configuration
	 */
	public Version loadVersionByDelta(String version_name, FileLocator floc)
			throws IOException {
		// Get the era and offset name from name2eraoffsetTable
		Pair pair = (Pair) name2eraoffsetTable.get(version_name);
		String era_name = (String) pair.first();
		String offset_name = (String) pair.second();
		int offset = Integer.parseInt(offset_name);

		// Load era (I think that ancestor eras will be loaded too)
		Era era = loadEraFromName(era_name, floc);
		if (era == null)
			return null;

		Version v = era.getVersion(offset);
		// Version.setVersion(v);

		// Load delta for this config given this era and ALL above eras
		loadDeltaForEras(era, floc);

		// ? Version v = era.getVersion(offset);
		// ? Version.setVersion(v);

		// Load all components' deltas for this era and ALL above eras
		Era parent_era = era.getParentEra();
		loadComponentDeltaForEras(parent_era, floc);
		loadComponentDelta(era, offset, floc);
		// Fix: Bug
		if (comp_delta_loaded_eras.contains(era) == false)
			comp_delta_loaded_eras.addElement(era);

		// update name2version/version2nameTable
		// ? Version.setVersion(v);
		name2versionTable.put(version_name, v);
		version2nameTable.put(v, version_name);
		return v;
	}

	/*
	 * <DEBUG>
	 * =====================================================================
	 * public Version loadVersionByDeltaDEBUG(String version_name, FileLocator
	 * floc) throws IOException { Pair pair = (Pair)
	 * name2eraoffsetTable.get(version_name); String era_name = (String)
	 * pair.first(); String offset_name = (String) pair.second(); int offset =
	 * Integer.parseInt(offset_name);
	 * 
	 * Era era = loadEraFromName(era_name,floc); if (era == null) return null;
	 * 
	 * Version v = era.getVersion(offset); // Load delta for this config given
	 * this era and ALL above eras loadDeltaForErasDEBUG(era,floc); // Load all
	 * components' deltas for this era and ALL above eras Era parent_era =
	 * era.getParentEra(); loadComponentDeltaForEras(parent_era,floc);
	 * 
	 * loadComponentDelta(era,offset,floc);
	 * 
	 * name2versionTable.put(version_name,v);
	 * version2nameTable.put(v,version_name); return v; } // </DEBUG>
	 * =====================================================================
	 */

	/**
	 * Load a version of this configuration by Snapshot
	 */
	public Version loadVersionBySnapshot(String version_name, FileLocator floc)
			throws IOException {
		// Get the era and offset name from name2eraoffsetTable
		Pair pair = (Pair) name2eraoffsetTable.get(version_name);
		String era_name = (String) pair.first();
		String offset_name = (String) pair.second();
		int offset = Integer.parseInt(offset_name);
		Era era = loadEraFromName(era_name, floc);
		if (era == null)
			return null;
		Version v = era.getVersion(offset);
		// load snapshot for this config given this era
		loadSnapshot(v, floc);
		// ? Version.setVersion(v);
		// update name2version/version2nameTable
		name2versionTable.put(version_name, v);
		version2nameTable.put(v, version_name);
		return v;
	}

	public static void ensureLoaded() {
		VersionedRegion.ensureLoaded();
		Era.ensureLoaded();
		VersionedChunk.ensureLoaded();
		Bundle.ensureLoaded();
		Component.ensureLoaded();
	}
}
